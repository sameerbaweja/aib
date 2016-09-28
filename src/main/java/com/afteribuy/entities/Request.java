package com.afteribuy.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the request database table.
 * 
 */
@Entity
@NamedQuery(name="Request.findAll", query="SELECT r FROM Request r")
public class Request implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="request_id")
	private int requestId;

	@Column(name="request_active")
	private int requestActive;

	@Column(name="request_created_at")
	private int requestCreatedAt;

	@Column(name="request_updated_at")
	private int requestUpdatedAt;

	@Lob
	@Column(name="request_description")
	private String requestDescription;

	@Column(name="request_title")
	private String requestTitle;

	//bi-directional many-to-one association to Comment
	@OneToMany(mappedBy="request")
	@JsonIgnore
	private List<Comment> comments;

	//bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name="request_product_id")
	private Product product;

	//bi-directional many-to-one association to RequestStatus
	@ManyToOne
	@JoinColumn(name="request_status_id")
	private RequestStatus requestStatus;

	//bi-directional many-to-one association to RequestType
	@ManyToOne
	@JoinColumn(name="request_type_id")
	private RequestType requestType;

	public Request() {
	}

	public int getRequestId() {
		return this.requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	public int getRequestActive() {
		return this.requestActive;
	}

	public void setRequestActive(int requestActive) {
		this.requestActive = requestActive;
	}

	public int getRequestCreatedAt() {
		return this.requestCreatedAt;
	}

	public void setRequestCreatedAt(int requestCreatedAt) {
		this.requestCreatedAt = requestCreatedAt;
	}

	public String getRequestDescription() {
		return this.requestDescription;
	}

	public void setRequestDescription(String requestDescription) {
		this.requestDescription = requestDescription;
	}

	public String getRequestTitle() {
		return this.requestTitle;
	}

	public void setRequestTitle(String requestTitle) {
		this.requestTitle = requestTitle;
	}

	public List<Comment> getComments() {
		return this.comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Comment addComment(Comment comment) {
		getComments().add(comment);
		comment.setRequest(this);

		return comment;
	}

	public Comment removeComment(Comment comment) {
		getComments().remove(comment);
		comment.setRequest(null);

		return comment;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public RequestStatus getRequestStatus() {
		return this.requestStatus;
	}

	public void setRequestStatus(RequestStatus requestStatus) {
		this.requestStatus = requestStatus;
	}

	public RequestType getRequestType() {
		return this.requestType;
	}

	public void setRequestType(RequestType requestType) {
		this.requestType = requestType;
	}

	public int getRequestUpdatedAt() {
		return requestUpdatedAt;
	}

	public void setRequestUpdatedAt(int requestUpdatedAt) {
		this.requestUpdatedAt = requestUpdatedAt;
	}

}