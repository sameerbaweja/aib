package com.afteribuy.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.afteribuy.entities.Product;
import com.afteribuy.entities.Request;
import com.afteribuy.entities.RequestStatus;
import com.afteribuy.exceptions.BusinessException;
import com.afteribuy.repositories.RequestRepository;

@Service
public class RequestService {
	
	@Autowired
	private RequestRepository requestRepo;

	@Transactional
	public List<Request> getRecentRequestList(Integer userId) {
		Pageable page = new PageRequest(0, 5, Sort.Direction.DESC, "requestCreatedAt");
		return requestRepo.findRecentRequestsByUserID(userId, page);
	}

	@Transactional
	public List<Request> getRequestList(Integer userId) {
		return requestRepo.findRequestsByUserID(userId);
	}

	@Transactional
	public void saveRequest(Request request){
		requestRepo.save(request);
	}

	@Transactional
	public void withdrawRequest(Integer requestId) throws BusinessException{
		Request request = requestRepo.findOne(requestId);
		if(request.getRequestStatus().getRequestStatusId() != RequestStatus.STATUS_WITHDRAWN){
			System.out.println("Withdrawing request: "+request.getRequestId());
			RequestStatus status = new RequestStatus();
			status.setRequestStatusId(RequestStatus.STATUS_WITHDRAWN);
			request.setRequestStatus(status);
			
			requestRepo.save(request);
		} else{
			throw new BusinessException("Request is already withdrawn");
		}
	}
}
