package com.afteribuy.test;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

import com.afteribuy.config.ApplicationConfiguration;
import com.afteribuy.entities.Make;
import com.afteribuy.entities.Product;
import com.afteribuy.entities.ProductType;
import com.afteribuy.entities.User;
import com.afteribuy.repositories.ProductRepository;
import com.github.springtestdbunit.DbUnitTestExecutionListener;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfiguration.class})
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
				DirtiesContextTestExecutionListener.class,
				TransactionalTestExecutionListener.class,
				DbUnitTestExecutionListener.class })
@WebAppConfiguration
public class ProductRepositoryTest {
	
	@Autowired
	private ProductRepository prodRepo;

	@Test
	public void testNewProduct(){
		Date dt = new Date();
		//System.out.println(Long.valueOf(dt.getTime()).intValue());
		
		Product product = new Product();
		product.setProductNickName("Test 1");
		product.setProductBillNumber("ABC123");
		product.setProductModel("Dell Lenovo");
		product.setProductRemarks("Remarks test");
		product.setProductSpecifications("Specs test");
		product.setProductWarrantyPeriod(10);
		User user = new User();
		user.setUserId(1);
		product.setUser(user);
		Make make = new Make();
		make.setMakeId(1);
		product.setMake(make);
		ProductType prdType = new ProductType();
		prdType.setProductTypeId(2);
		product.setProductType(prdType);
		
		product.setProductCreatedAt(Long.valueOf(dt.getTime()).intValue());
		product.setProductLastUpdatedAt(Long.valueOf(dt.getTime()).intValue());
		
		Product prd = prodRepo.save(product);
		System.out.println(prd.getProductId());
		
		//prodRepo.delete(prd);
	}
}
