package com.afteribuy.controllers;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.afteribuy.entities.Make;
import com.afteribuy.entities.ProductType;
import com.afteribuy.entities.RequestStatus;
import com.afteribuy.entities.RequestType;
import com.afteribuy.services.MasterDataService;

@Controller
@RequestMapping("/")
public class DefaultController {

	@Autowired
	private MasterDataService masterDataService;
	
	@RequestMapping(value = "*", method = RequestMethod.GET)
	public String defaultHandler(){
		return "redirect:/site/index.html";
	}
	
	@RequestMapping(value = "/dashboarddata", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public HashMap getDashboardData(){
		
		HashMap dataObj = new HashMap();
		
		dataObj.put("totalProducts", masterDataService.getProductCount(1));
		dataObj.put("totalBrands", masterDataService.getBrandCount(1));
		dataObj.put("resolvedRequests", masterDataService.getResolvedRequestCount(1));
		dataObj.put("unresolvedRequests", masterDataService.getUnResolvedRequestCount(1));
		
		return dataObj;
	}

	@RequestMapping(value = "/producttypes", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public List<ProductType> getProductTypes(){
		return masterDataService.getProductTypes();
	}

	@RequestMapping(value = "/makes", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public List<Make> getMakes(){
		return masterDataService.getMakes();
	}
	
	@RequestMapping(value = "/requesttypes", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public List<RequestType> getRequestTypes(){
		return masterDataService.getRequestTypes();
	}

	@RequestMapping(value = "/requeststatusvalues", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public List<RequestStatus> getRequestStatusValues(){
		return masterDataService.getRequestStatusValues();
	}
}
