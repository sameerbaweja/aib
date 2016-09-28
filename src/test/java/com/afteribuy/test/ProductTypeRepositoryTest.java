package com.afteribuy.test;

import java.util.Date;
import java.util.List;

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
import com.afteribuy.repositories.MasterDataRepository;
import com.afteribuy.repositories.ProductTypeRepository;
import com.github.springtestdbunit.DbUnitTestExecutionListener;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfiguration.class})
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
				DirtiesContextTestExecutionListener.class,
				TransactionalTestExecutionListener.class,
				DbUnitTestExecutionListener.class })
@WebAppConfiguration
public class ProductTypeRepositoryTest {
	
	@Autowired
	private ProductTypeRepository ptRepo;

	@Autowired
	private MasterDataRepository mdRepo;

	@Test
	public void testProductTypeResults(){
		List<ProductType> list = mdRepo.getProductTypes();

		ProductType type1 = list.get(0);
		assertThat(type1.getMakes()).hasSize(2);
	}
}
