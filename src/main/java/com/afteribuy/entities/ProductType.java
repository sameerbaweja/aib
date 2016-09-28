package com.afteribuy.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the product_type database table.
 * 
 */
@Entity
@Table(name="product_type")
@NamedQuery(name="ProductType.findAll", query="SELECT p FROM ProductType p")
public class ProductType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="product_type_id")
	private int productTypeId;

	@Column(name="product_type_value")
	private String productTypeValue;

	//bi-directional many-to-one association to Product
	@OneToMany(mappedBy="productType")
	@JsonIgnore
	private List<Product> products;

	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="producttype_make",
	    joinColumns={@JoinColumn(name="product_type_id")},
	    inverseJoinColumns={@JoinColumn(name="make_id")})
	private List<Make> makes;// = new ArrayList<Make>();
	public List<Make> getMakes() {
		return makes;
	}

	public void setMakes(List<Make> makes) {
		this.makes = makes;
	}


	public ProductType() {
	}

	public int getProductTypeId() {
		return this.productTypeId;
	}

	public void setProductTypeId(int productTypeId) {
		this.productTypeId = productTypeId;
	}

	public String getProductTypeValue() {
		return this.productTypeValue;
	}

	public void setProductTypeValue(String productTypeValue) {
		this.productTypeValue = productTypeValue;
	}

	public List<Product> getProducts() {
		return this.products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Product addProduct(Product product) {
		getProducts().add(product);
		product.setProductType(this);

		return product;
	}

	public Product removeProduct(Product product) {
		getProducts().remove(product);
		product.setProductType(null);

		return product;
	}

}