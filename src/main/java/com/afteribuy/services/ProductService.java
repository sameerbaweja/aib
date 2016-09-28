package com.afteribuy.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.afteribuy.entities.Product;
import com.afteribuy.repositories.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepo;

	@Transactional
	public List<Product> getRecentProductList(Integer userId) {
		Pageable page = new PageRequest(0, 4, Sort.Direction.DESC, "productPurchaseDate");
		List<Product> prodList = productRepo.findRecentProductsByUserID(userId, page);
		return prodList;
	}

	@Transactional
	public List<Product> getProductList(Integer userId) {
		List<Product> prodList = productRepo.findProductsByUserID(userId);
		return prodList;
	}

	@Transactional
	public Product getProductDetails(Integer productId) {
		Product prod = null;
		List<Product> list = productRepo.findProductsByID(productId);
		if (list.size()>0){
			prod = list.get(0);
		}
		return prod;
	}
	
	@Transactional
	public void saveProduct(Product product){
		productRepo.save(product);
	}

	@Transactional
	public void removeProduct(Integer productId){
		productRepo.delete(productId);
	}
}
