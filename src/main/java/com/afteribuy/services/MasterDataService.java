package com.afteribuy.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.afteribuy.entities.Make;
import com.afteribuy.entities.ProductType;
import com.afteribuy.entities.RequestStatus;
import com.afteribuy.entities.RequestType;
import com.afteribuy.repositories.MasterDataRepository;

@Service
public class MasterDataService {
	
	@Autowired
	private MasterDataRepository masterDataRepo;

	public List<ProductType> getProductTypes() {
		return masterDataRepo.getProductTypes();
	}

	public List<Make> getMakes() {
		return masterDataRepo.getMakes();
	}

	public List<RequestType> getRequestTypes() {
		return masterDataRepo.getRequestTypes();
	}

	public List<RequestStatus> getRequestStatusValues() {
		return masterDataRepo.getRequestStatusValues();
	}

	public long getProductCount(Integer userId) {
		return masterDataRepo.getProductCount(userId);
	}

	public long getBrandCount(Integer userId) {
		return masterDataRepo.getBrandCount(userId);
	}

	public long getResolvedRequestCount(Integer userId) {
		return masterDataRepo.getResolvedRequestCount(userId);
	}

	public long getUnResolvedRequestCount(Integer userId) {
		return masterDataRepo.getUnResolvedRequestCount(userId);
	}
}
