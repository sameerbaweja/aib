package com.afteribuy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.afteribuy.entities.ProductType;

public interface ProductTypeRepository extends JpaRepository<ProductType, Integer> {

}
