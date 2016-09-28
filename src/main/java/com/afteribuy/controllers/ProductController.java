package com.afteribuy.controllers;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.afteribuy.entities.Make;
import com.afteribuy.entities.Product;
import com.afteribuy.entities.ProductType;
import com.afteribuy.entities.User;
import com.afteribuy.services.ProductService;
import com.afteribuy.web.dto.ProductDTO;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;

	@RequestMapping(value = "/recentList", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public List<Product> getRecentProductList(HttpSession session) {
		List<Product> prodList = productService.getRecentProductList(1);
		System.out.println("Number of products: " + prodList.size());
		return prodList;
	}

	@RequestMapping(value = "/productList", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public List<Product> getProductList(HttpSession session) {
		List<Product> prodList = productService.getProductList(1);
		System.out.println("Number of products: " + prodList.size());
		return prodList;
	}

	@RequestMapping(value = "/productDetails/{productId}", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public ProductDTO getProductDetails(HttpSession session, @PathVariable("productId") String productId) {
		System.out.println("productId: " + productId);
		Product prodObj = productService.getProductDetails(Integer.valueOf(productId));

		ProductDTO product = new ProductDTO();
		System.out.println("productId: " + prodObj.getProductId());
		product.setProductId(prodObj.getProductId());
		product.setProductNickName(prodObj.getProductNickName());
		product.setProductBillNumber(prodObj.getProductBillNumber());
		product.setProductModel(prodObj.getProductModel());
		product.setProductRemarks(prodObj.getProductRemarks());
		product.setProductSpecifications(prodObj.getProductSpecifications());
		product.setProductWarrantyPeriod(prodObj.getProductWarrantyPeriod());
		product.setUserId(prodObj.getUser().getUserId());
		product.setProductTypeId(prodObj.getProductType().getProductTypeId());
		product.setMakeId(prodObj.getMake().getMakeId());
		product.setProductPurchaseDate(prodObj.getProductPurchaseDate());
		
		return product;
	}

	@RequestMapping(value = "/submitproduct", method = RequestMethod.POST)
	public ResponseEntity<Void> saveProduct(@RequestBody ProductDTO productDto) {
		System.out.println("submit product: "+productDto.getProductPurchaseDate());
		Product product = new Product();
		if(productDto.getProductId() > 0){
			product.setProductId(productDto.getProductId());
		}
		product.setProductNickName(productDto.getProductNickName());
		product.setProductBillNumber(productDto.getProductBillNumber());
		product.setProductModel(productDto.getProductModel());
		product.setProductRemarks(productDto.getProductRemarks());
		product.setProductSpecifications(productDto.getProductSpecifications());
		product.setProductWarrantyPeriod(productDto.getProductWarrantyPeriod());
		product.setProductActive((byte) 1);
		product.setProductPurchaseDate(productDto.getProductPurchaseDate());
		User user = new User();
		user.setUserId(1);
		product.setUser(user);
		Make make = new Make();
		make.setMakeId(productDto.getMakeId());
		product.setMake(make);
		ProductType prdType = new ProductType();
		prdType.setProductTypeId(productDto.getProductTypeId());
		product.setProductType(prdType);

		Date dt = new Date();
		product.setProductCreatedAt(Long.valueOf(dt.getTime()).intValue());
		product.setProductLastUpdatedAt(Long.valueOf(dt.getTime()).intValue());
		
		productService.saveProduct(product);

		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(value = "/removeProduct/{productId}", method = RequestMethod.POST)
	public ResponseEntity<Void> removeProduct(HttpSession session, @PathVariable("productId") String productId) {
		System.out.println("Remove productId: " + productId);
		productService.removeProduct(Integer.valueOf(productId));
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
