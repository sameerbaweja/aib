package com.afteribuy.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import org.springframework.data.domain.Pageable;

import com.afteribuy.entities.Request;

public interface RequestRepository extends CrudRepository<Request, Integer> {

	@Query("select r from Request r LEFT JOIN FETCH r.product p LEFT JOIN FETCH p.productType LEFT JOIN FETCH p.make LEFT JOIN p.user WHERE p.user.userId = ?1")
	List<Request> findRecentRequestsByUserID(Integer userId, Pageable page);

	@Query("select r from Request r LEFT JOIN FETCH r.product p LEFT JOIN FETCH p.productType LEFT JOIN FETCH p.make LEFT JOIN p.user WHERE p.user.userId = ?1")
	List<Request> findRequestsByUserID(Integer userId);
}
