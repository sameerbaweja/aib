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
 * The persistent class for the request_type database table.
 * 
 */
@Entity
@Table(name="request_type")
@NamedQuery(name="RequestType.findAll", query="SELECT r FROM RequestType r")
public class RequestType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="request_type_id")
	private int requestTypeId;

	@Column(name="request_type_value")
	private String requestTypeValue;

	//bi-directional many-to-one association to Request
	@OneToMany(mappedBy="requestType")
	@JsonIgnore
	private List<Request> requests;

	public RequestType() {
	}

	public int getRequestTypeId() {
		return this.requestTypeId;
	}

	public void setRequestTypeId(int requestTypeId) {
		this.requestTypeId = requestTypeId;
	}

	public String getRequestTypeValue() {
		return this.requestTypeValue;
	}

	public void setRequestTypeValue(String requestTypeValue) {
		this.requestTypeValue = requestTypeValue;
	}

	public List<Request> getRequests() {
		return this.requests;
	}

	public void setRequests(List<Request> requests) {
		this.requests = requests;
	}

	public Request addRequest(Request request) {
		getRequests().add(request);
		request.setRequestType(this);

		return request;
	}

	public Request removeRequest(Request request) {
		getRequests().remove(request);
		request.setRequestType(null);

		return request;
	}

}