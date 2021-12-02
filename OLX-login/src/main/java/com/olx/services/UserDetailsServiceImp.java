package com.olx.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.olx.entity.UserEntity;
import com.olx.repo.UserRepo;


@Service
public class UserDetailsServiceImp implements UserDetailsService {

	@Autowired
	UserRepo userRepo;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<UserEntity> entityList= this.userRepo.findByusername(username);
		
		if(entityList.isEmpty()==true) {
			throw new UsernameNotFoundException(username);
		}
		UserEntity userEntity = entityList.get(0);
		List<GrantedAuthority> authiorities= new ArrayList<>();
		authiorities.add(new SimpleGrantedAuthority(userEntity.getRoles()));
		User user=new User(username, passwordEncoder.encode(userEntity.getPassword()),authiorities);
		return user;
	}

}
