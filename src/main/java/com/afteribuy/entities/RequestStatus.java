package com.afteribuy.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the request_status database table.
 * 
 */
@Entity
@Table(name="request_status")
@NamedQuery(name="RequestStatus.findAll", query="SELECT r FROM RequestStatus r")
public class RequestStatus implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final int STATUS_OPEN = 1;
	public static final int STATUS_ASSIGNED = 2;
	public static final int STATUS_ASSIGNEDOEM = 3;
	public static final int STATUS_OEMINPROGRESS = 4;
	public static final int STATUS_ASSIGNEDSERVICECENTER = 5;
	public static final int STATUS_RESOLVED = 6;
	public static final int STATUS_WITHDRAWN = 7;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="request_status_id")
	private int requestStatusId;

	@Column(name="request_status_value")
	private String requestStatusValue;

	//bi-directional many-to-one association to Request
	@OneToMany(mappedBy="requestStatus")
	@JsonIgnore
	private List<Request> requests;

	public RequestStatus() {
	}

	public int getRequestStatusId() {
		return this.requestStatusId;
	}

	public void setRequestStatusId(int requestStatusId) {
		this.requestStatusId = requestStatusId;
	}

	public String getRequestStatusValue() {
		return this.requestStatusValue;
	}

	public void setRequestStatusValue(String requestStatusValue) {
		this.requestStatusValue = requestStatusValue;
	}

	public List<Request> getRequests() {
		return this.requests;
	}

	public void setRequests(List<Request> requests) {
		this.requests = requests;
	}

	public Request addRequest(Request request) {
		getRequests().add(request);
		request.setRequestStatus(this);

		return request;
	}

	public Request removeRequest(Request request) {
		getRequests().remove(request);
		request.setRequestStatus(null);

		return request;
	}

}