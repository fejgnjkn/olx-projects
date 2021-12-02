package com.olx.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import com.olx.dto.AuthenticationRequset;
import com.olx.dto.User;
import com.olx.entity.BlackListUserTokenEntity;
import com.olx.entity.UserEntity;
import com.olx.exception.InvalidAuthTokenException;
import com.olx.repo.BlackListTokenRepo;
import com.olx.repo.UserRepo;
import com.olx.security.JwtUtil;

@Service
public class LoginServiceImp implements LoginServices {

	@Autowired
	UserRepo userRepo;

	@Autowired
	BlackListTokenRepo blackListTokenRepo;

	@Autowired
	JwtUtil jwtUtil;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserDetailsServiceImp userDetailsServiceImp;

	@Override
	public User registerUser(User user) {
		UserEntity userEntity = getUserEntityFromUserDto(user);
		this.userRepo.saveAndFlush(userEntity);
		return getUserDtoFromEntity(userEntity);
	}

	@Override
	public User getUser(String authToken) {
		boolean isTokenValid = validateToken(authToken).getBody();
		if (isTokenValid == false) {
			throw new InvalidAuthTokenException(authToken);
		}

		String jwtToken = authToken.substring("bearer".length(), authToken.length());
		String username = jwtUtil.extractUsername(jwtToken);
		List<UserEntity> entityList = this.userRepo.findByusername(username);

		if (entityList.isEmpty() == true) {
			throw new UsernameNotFoundException(username);
		}
		UserEntity userEntity = entityList.get(0);
		return getUserDtoFromEntity(userEntity);
	}

	private UserEntity getUserEntityFromUserDto(User user) {
		if (user == null) {
			return null;
		}
		return new UserEntity(user.getUserName(), user.getPassword(), "ROLE_USER", user.getFirstName(),
				user.getLastName(), user.getEmail(), user.getPhone());
	}

	private User getUserDtoFromEntity(UserEntity userEntity) {
		if (userEntity == null) {
			return null;
		}

		return new User(userEntity.getId(), userEntity.getFirstName(), userEntity.getLastName(),
				userEntity.getUsername(), userEntity.getPassword(), userEntity.getEmail(), userEntity.getPhone());
	}

	@Override
	public ResponseEntity<Boolean> validateToken(String authToken) {
		boolean status = false;
		try {
			String jwtToken = authToken.substring("bearer".length(), authToken.length());
			String username = jwtUtil.extractUsername(jwtToken);
			UserDetails userDeatils = userDetailsServiceImp.loadUserByUsername(username);
			status = jwtUtil.validateToken(jwtToken, userDeatils);
		} catch (Exception e) {
			return new ResponseEntity<Boolean>(status, HttpStatus.BAD_REQUEST);
		}

		BlackListUserTokenEntity optBlackList = this.blackListTokenRepo.findByToken(authToken);
		if (optBlackList != null) {
			throw new InvalidAuthTokenException(authToken);
		}
		return new ResponseEntity<Boolean>(status, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> authenticateUser(AuthenticationRequset authenticationRequset) {
		try {
			this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequset.getUserName(), authenticationRequset.getPassword()));
		} catch (BadCredentialsException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST); // Login failed - Bad credential
		}

		// login successful

		UserDetails userDeatils = userDetailsServiceImp.loadUserByUsername(authenticationRequset.getUserName());
		String token = jwtUtil.generateToken(userDeatils);
		return new ResponseEntity<String>(token, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Boolean> logout(String authToken) {
		this.blackListTokenRepo.save(new BlackListUserTokenEntity(authToken));
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}

	@Override
	public String getLoggedInUserName(String authToken) {
		boolean isTokenValid = validateToken(authToken).getBody();
		if (isTokenValid == false) {
			throw new InvalidAuthTokenException(authToken);
		}

		String jwtToken = authToken.substring("bearer".length(), authToken.length());
		return jwtUtil.extractUsername(jwtToken);
	}

}
