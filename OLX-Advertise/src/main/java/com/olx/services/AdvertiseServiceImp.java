package com.olx.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.xml.bind.ValidationException;

import org.apache.http.util.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.olx.dto.Advertise;
import com.olx.dto.AdvertiseRequest;
import com.olx.entity.AdvertiseEntity;
import com.olx.exception.InvalidAuthTokenException;
import com.olx.repo.AdvertiseRepo;
import com.olx.exception.InvalidAdvertiseIdException;

@Service
public class AdvertiseServiceImp implements AdvertiseService {

	@Autowired
	MasterDataDelegate masterDataDelegate;

	@Autowired
	LoginDelagate loginDelagate;

	@Autowired
	AdvertiseRepo advertiseRepo;

	@Autowired
	EntityManager entityManager;

	@Override
	public Advertise createAdvertise(AdvertiseRequest advertiseRequest, String token) {

		boolean isTokenValid = loginDelagate.validateToken(token);
		if (isTokenValid == false) {
			throw new InvalidAuthTokenException(token);
		}

		String userName = loginDelagate.getLoggedInUserName(token);

		String category = masterDataDelegate.getCategory(advertiseRequest.getCategoryId());
		String status = masterDataDelegate.getStatus(advertiseRequest.getStatusId());
		AdvertiseEntity advertiseEntity = new AdvertiseEntity(advertiseRequest.getTitle(), advertiseRequest.getPrice(),
				advertiseRequest.getDescription(), category, userName, LocalDate.now(), LocalDate.now(), status,
				userName);

		this.advertiseRepo.saveAndFlush(advertiseEntity);
		return generateAdvertiseDtoFromEntity(advertiseEntity);
	}

	@Override
	public Advertise updateAdvertise(int id, AdvertiseRequest advertiseRequest, String token) {
		boolean isTokenValid = loginDelagate.validateToken(token);
		if (isTokenValid == false) {
			throw new InvalidAuthTokenException(token);
		}
		Optional<AdvertiseEntity> optadvertise = this.advertiseRepo.findById(id);
		if (optadvertise.isPresent()) {
			String category = masterDataDelegate.getCategory(advertiseRequest.getCategoryId());
			String status = masterDataDelegate.getStatus(advertiseRequest.getStatusId());

			AdvertiseEntity existingadvertise = optadvertise.get();
			existingadvertise.setTitle(advertiseRequest.getTitle());
			existingadvertise.setPrice(advertiseRequest.getPrice());
			existingadvertise.setDescription(advertiseRequest.getDescription());
			existingadvertise.setCategory(category);
			existingadvertise.setStatus(status);
			existingadvertise.setModifiedDate(LocalDate.now());
			this.advertiseRepo.save(existingadvertise);
			return generateAdvertiseDtoFromEntity(existingadvertise);
		}
		throw new InvalidAdvertiseIdException();
	}

	@Override
	public List<Advertise> searchAdvertisementByFilter(String searchText) {
		List<Advertise> list = new ArrayList<>();
		List<AdvertiseEntity> entities = this.advertiseRepo.findAdvertiseByFilter(searchText);
		for (AdvertiseEntity entity : entities) {
			list.add(generateAdvertiseDtoFromEntity(entity));
		}
		return list;
	}

	@Override
	public boolean deleteAdvertise(int id, String token) {

		boolean isTokenValid = loginDelagate.validateToken(token);
		if (isTokenValid == false) {
			throw new InvalidAuthTokenException(token);
		}
		Optional<AdvertiseEntity> optAdvertiseEntity = this.advertiseRepo.findById(id);
		if (optAdvertiseEntity.isPresent()) {
			this.advertiseRepo.deleteById(optAdvertiseEntity.get().getId());
			return true;
		}
		throw new InvalidAdvertiseIdException();
	}

	private Advertise generateAdvertiseDtoFromEntity(AdvertiseEntity advertiseEntity) {
		if (advertiseEntity == null) {
			return null;
		}
		return new Advertise(advertiseEntity.getId(), advertiseEntity.getTitle(), advertiseEntity.getPrice(),
				advertiseEntity.getDescription(), advertiseEntity.getCategory(), advertiseEntity.getUserName(),
				advertiseEntity.getCreatedDate(), advertiseEntity.getModifiedDate(), advertiseEntity.getStatus());

	}

	@Override
	public List<Advertise> getAllAdvertises(String token) {
		boolean isTokenValid = loginDelagate.validateToken(token);
		if (isTokenValid == false) {
			throw new InvalidAuthTokenException(token);
		}
		List<Advertise> list = new ArrayList<>();
		List<AdvertiseEntity> entities = this.advertiseRepo.findAll();
		if (entities.isEmpty()) {
			return list;
		}
		for (AdvertiseEntity e : entities) {
			list.add(generateAdvertiseDtoFromEntity(e));
		}
		return list;
	}

	@Override
	public Advertise getAdvertise(int id, String token) {
		boolean isTokenValid = loginDelagate.validateToken(token);
		if (isTokenValid == false) {
			throw new InvalidAuthTokenException(token);
		}

		Optional<AdvertiseEntity> optAdvertiseEntity = this.advertiseRepo.findById(id);
		if (optAdvertiseEntity.isPresent()) {
			return generateAdvertiseDtoFromEntity(optAdvertiseEntity.get());
		}
		throw new InvalidAdvertiseIdException();
	}

	@Override
	public List getAllCategories() {
		return masterDataDelegate.getAllCategories();
	}

	@Override
	public List<Advertise> searchAdvertisementByFilters(String searchText, String dateCondition, String category,
			String postedBy, LocalDate onDate, LocalDate fromDate, LocalDate toDate, String sortBy, Integer startIndex,
			Integer records) {
		List<Advertise> advertiseList = new ArrayList<>();

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<AdvertiseEntity> criteriaQuery = criteriaBuilder.createQuery(AdvertiseEntity.class);

		Root<AdvertiseEntity> rotEntity = criteriaQuery.from(AdvertiseEntity.class);

		Predicate titlePredicate = null;
		if (!TextUtils.isEmpty(searchText)) {
			titlePredicate = criteriaBuilder.like(rotEntity.get("title"), searchText);
		}
		Predicate postByPredicate = null;
		if (!TextUtils.isEmpty(postedBy)) {
			postByPredicate = criteriaBuilder.equal(rotEntity.get("postedBy"), postedBy);
		}

		Predicate categoryPredicate = null;
		if (!TextUtils.isEmpty(category)) {
			categoryPredicate = criteriaBuilder.equal(rotEntity.get("category"), category);
		}

		Predicate onDatePredicate = null;
		if (onDate != null) {
			onDatePredicate = criteriaBuilder.greaterThanOrEqualTo(rotEntity.get("createdDate"), onDate);
		}

		Predicate fromDatePredicate = null;
		Predicate lessThanPredicate = null;
		Predicate geaterThanPredicate = null;
		if (fromDate != null) {
			fromDatePredicate = criteriaBuilder.equal(rotEntity.get("createdDate"), fromDate);
			lessThanPredicate = criteriaBuilder.lessThan(rotEntity.get("createdDate"), fromDate);
			geaterThanPredicate = criteriaBuilder.greaterThan(rotEntity.get("createdDate"), fromDate);
		}

		Predicate toDatePredicate = null;
		if (toDate != null) {
			toDatePredicate = criteriaBuilder.greaterThanOrEqualTo(rotEntity.get("createdDate"), toDate);
		}

		Predicate startIndexPredicate = null;
		if (startIndex != null) {
			startIndexPredicate = criteriaBuilder.greaterThanOrEqualTo(rotEntity.get("id"), startIndex);
		}

		if (sortBy != null) {

			Order sortedOrder = criteriaBuilder.desc(rotEntity.get("createdDate"));
			if (sortBy.equalsIgnoreCase("ASC")) {
				sortedOrder = criteriaBuilder.asc(rotEntity.get("createdDate"));
			}
			criteriaQuery.orderBy(sortedOrder);
		}

		List<Predicate> predicates = new ArrayList<>();
		if (titlePredicate != null) {
			predicates.add(titlePredicate);
		}
		if (categoryPredicate != null) {
			predicates.add(categoryPredicate);
		}

		if (postByPredicate != null) {
			predicates.add(postByPredicate);
		}

		if (startIndexPredicate != null) {
			predicates.add(startIndexPredicate);
		}

		if (dateCondition != null) {

			switch (dateCondition) {

			case "between":
				predicates.add(fromDatePredicate);
				predicates.add(toDatePredicate);
				break;
			case "lessthan":
				predicates.add(lessThanPredicate);
				break;
			case "greatethan":
				predicates.add(geaterThanPredicate);
				break;
			case "equals":
				predicates.add(onDatePredicate);
				break;
			default:
				break;
			}
		}

		Predicate finalPredicate = criteriaBuilder.or(predicates.toArray(new Predicate[0]));
		criteriaQuery.where(finalPredicate);
		List<AdvertiseEntity> entities = entityManager.createQuery(criteriaQuery).getResultList();

		for (AdvertiseEntity adv : entities) {
			advertiseList.add(generateAdvertiseDtoFromEntity(adv));
		}

		return advertiseList;
	}
}
