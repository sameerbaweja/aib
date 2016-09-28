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
 * The persistent class for the product database table.
 * 
 */
@Entity
@NamedQuery(name="Product.findAll", query="SELECT p FROM Product p")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="product_id")
	private int productId;

	@Column(name="product_active")
	private int productActive;

	@Column(name="product_bill_number")
	private String productBillNumber;

	@Column(name="product_created_at")
	private int productCreatedAt;

	@Column(name="product_last_updated_at")
	private int productLastUpdatedAt;

	@Column(name="product_model")
	private String productModel;

	@Column(name="product_nick_name")
	private String productNickName;

	@Column(name="product_purchase_date")
	private String productPurchaseDate;

	@Lob
	@Column(name="product_remarks")
	private String productRemarks;

	@Lob
	@Column(name="product_specifications")
	private String productSpecifications;

	@Column(name="product_warranty_period")
	private int productWarrantyPeriod;

	//bi-directional many-to-one association to Make
	@ManyToOne
	@JoinColumn(name="product_make_id")
	private Make make;

	//bi-directional many-to-one association to ProductType
	@ManyToOne
	@JoinColumn(name="product_type_id")
	private ProductType productType;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="product_user_id")
	private User user;

	//bi-directional many-to-one association to Request
	@OneToMany(mappedBy="product")
	@JsonIgnore
	private List<Request> requests;

	public Product() {
	}

	public int getProductId() {
		return this.productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getProductActive() {
		return this.productActive;
	}

	public void setProductActive(int productActive) {
		this.productActive = productActive;
	}

	public String getProductBillNumber() {
		return this.productBillNumber;
	}

	public void setProductBillNumber(String productBillNumber) {
		this.productBillNumber = productBillNumber;
	}

	public int getProductCreatedAt() {
		return this.productCreatedAt;
	}

	public void setProductCreatedAt(int productCreatedAt) {
		this.productCreatedAt = productCreatedAt;
	}

	public int getProductLastUpdatedAt() {
		return this.productLastUpdatedAt;
	}

	public void setProductLastUpdatedAt(int productLastUpdatedAt) {
		this.productLastUpdatedAt = productLastUpdatedAt;
	}

	public String getProductModel() {
		return this.productModel;
	}

	public void setProductModel(String productModel) {
		this.productModel = productModel;
	}

	public String getProductNickName() {
		return this.productNickName;
	}

	public void setProductNickName(String productNickName) {
		this.productNickName = productNickName;
	}

	public String getProductPurchaseDate() {
		return this.productPurchaseDate;
	}

	public void setProductPurchaseDate(String productPurchaseDate) {
		this.productPurchaseDate = productPurchaseDate;
	}

	public String getProductRemarks() {
		return this.productRemarks;
	}

	public void setProductRemarks(String productRemarks) {
		this.productRemarks = productRemarks;
	}

	public String getProductSpecifications() {
		return this.productSpecifications;
	}

	public void setProductSpecifications(String productSpecifications) {
		this.productSpecifications = productSpecifications;
	}

	public int getProductWarrantyPeriod() {
		return this.productWarrantyPeriod;
	}

	public void setProductWarrantyPeriod(int productWarrantyPeriod) {
		this.productWarrantyPeriod = productWarrantyPeriod;
	}

	public Make getMake() {
		return this.make;
	}

	public void setMake(Make make) {
		this.make = make;
	}

	public ProductType getProductType() {
		return this.productType;
	}

	public void setProductType(ProductType productType) {
		this.productType = productType;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Request> getRequests() {
		return this.requests;
	}

	public void setRequests(List<Request> requests) {
		this.requests = requests;
	}

	public Request addRequest(Request request) {
		getRequests().add(request);
		request.setProduct(this);

		return request;
	}

	public Request removeRequest(Request request) {
		getRequests().remove(request);
		request.setProduct(null);

		return request;
	}

}