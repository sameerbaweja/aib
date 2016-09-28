package com.afteribuy.web.dto;

public class ProductDTO {

	private int productId;

	private String productBillNumber;

	private String productModel;

	private String productNickName;

	private String productPurchaseDate;

	private String productRemarks;

	private String productSpecifications;

	private int productWarrantyPeriod;

	private int makeId;

	private int productTypeId;

	private int userId;

	public ProductDTO() {
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductBillNumber() {
		return productBillNumber;
	}

	public void setProductBillNumber(String productBillNumber) {
		this.productBillNumber = productBillNumber;
	}

	public String getProductModel() {
		return productModel;
	}

	public void setProductModel(String productModel) {
		this.productModel = productModel;
	}

	public String getProductNickName() {
		return productNickName;
	}

	public void setProductNickName(String productNickName) {
		this.productNickName = productNickName;
	}

	public String getProductPurchaseDate() {
		return productPurchaseDate;
	}

	public void setProductPurchaseDate(String productPurchaseDate) {
		this.productPurchaseDate = productPurchaseDate;
	}

	public String getProductRemarks() {
		return productRemarks;
	}

	public void setProductRemarks(String productRemarks) {
		this.productRemarks = productRemarks;
	}

	public String getProductSpecifications() {
		return productSpecifications;
	}

	public void setProductSpecifications(String productSpecifications) {
		this.productSpecifications = productSpecifications;
	}

	public int getProductWarrantyPeriod() {
		return productWarrantyPeriod;
	}

	public void setProductWarrantyPeriod(int productWarrantyPeriod) {
		this.productWarrantyPeriod = productWarrantyPeriod;
	}

	public int getMakeId() {
		return makeId;
	}

	public void setMakeId(int makeId) {
		this.makeId = makeId;
	}

	public int getProductTypeId() {
		return productTypeId;
	}

	public void setProductTypeId(int productTypeId) {
		this.productTypeId = productTypeId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
}
