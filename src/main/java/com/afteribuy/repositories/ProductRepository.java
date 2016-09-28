package com.afteribuy.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import com.afteribuy.entities.Product;

public interface ProductRepository extends CrudRepository<Product, Integer> {

	@Query("select p from Product p LEFT JOIN p.user LEFT JOIN FETCH p.productType LEFT JOIN FETCH p.make WHERE p.user.userId = ?1 AND p.productActive=1")
	List<Product> findProductsByUserID(Integer userId);

	@Query("select p from Product p LEFT JOIN FETCH p.productType LEFT JOIN FETCH p.make WHERE p.productId = ?1 AND p.productActive=1")
	List<Product> findProductsByID(Integer productId);
	
	@Query("select p from Product p LEFT JOIN p.user LEFT JOIN FETCH p.productType LEFT JOIN FETCH p.make WHERE p.user.userId = ?1 AND p.productActive=1")
	List<Product> findRecentProductsByUserID(Integer userId, Pageable page);
	
	//Product save(Product persisted);
}
