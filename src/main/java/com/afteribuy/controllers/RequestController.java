package com.afteribuy.controllers;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.afteribuy.entities.Make;
import com.afteribuy.entities.Product;
import com.afteribuy.entities.ProductType;
import com.afteribuy.entities.Request;
import com.afteribuy.entities.RequestStatus;
import com.afteribuy.entities.RequestType;
import com.afteribuy.entities.User;
import com.afteribuy.exceptions.BusinessException;
import com.afteribuy.services.RequestService;
import com.afteribuy.web.dto.ProductDTO;
import com.afteribuy.web.dto.RequestDTO;


@RestController
@RequestMapping("/request")
public class RequestController {

	@Autowired
	private RequestService requestService;

	@RequestMapping(value = "/recentList", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public List<Request> getRecentRequestsList(HttpSession session) {
		List<Request> reqList = null;
		reqList = requestService.getRecentRequestList(1);
		System.out.println("Number of requests: " + reqList.size());
		return reqList;
	}

	@RequestMapping(value = "/requestList", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public List<Request> getRequestsList(HttpSession session) {
		List<Request> reqList = null;
		reqList = requestService.getRequestList(1);
		System.out.println("Number of requests: " + reqList.size());
		return reqList;
	}

	@RequestMapping(value = "/submitrequest", method = RequestMethod.POST)
	public ResponseEntity<Void> addRequest(@RequestBody RequestDTO requestDto) {
		System.out.println("submit request: "+requestDto.getRequestDesc());
		Request request = new Request();
		Product assocProduct = new Product();
		assocProduct.setProductId(requestDto.getProductId());
		request.setProduct(assocProduct);
		request.setRequestTitle(requestDto.getRequestTitle());
		request.setRequestDescription(requestDto.getRequestDesc());
		request.setRequestActive(1);
		
		RequestStatus reqStatus = new RequestStatus();
		// ToDo change the Request Status hard coding  
		reqStatus.setRequestStatusId(1);
		request.setRequestStatus(reqStatus);

		RequestType reqType = new RequestType();
		// ToDo change the Request Type hard coding  
		reqType.setRequestTypeId(1);
		request.setRequestType(reqType);
		
		Date dt = new Date();
		request.setRequestCreatedAt(Long.valueOf(dt.getTime()).intValue());
		request.setRequestUpdatedAt(Long.valueOf(dt.getTime()).intValue());
		
		requestService.saveRequest(request);
		

		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(value = "/withdrawRequest/{requestId}", method = RequestMethod.POST)
	public ResponseEntity<String> withdrawRequest(HttpSession session, @PathVariable("requestId") String requestId) {
		try {
			requestService.withdrawRequest(Integer.valueOf(requestId));
			return new ResponseEntity<String>("Request Withdrawn", HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
	}
}
