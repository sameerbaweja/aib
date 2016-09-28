package com.afteribuy.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the make database table.
 * 
 */
@Entity
@NamedQuery(name="Make.findAll", query="SELECT m FROM Make m")
public class Make implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="make_id")
	private int makeId;

	@Column(name="make_name")
	private String makeName;

	//bi-directional many-to-one association to Product
	@OneToMany(mappedBy="make")
	@JsonIgnore
	private List<Product> products;

	/*@ManyToMany(mappedBy="makes", fetch=FetchType.EAGER)
	private List<ProductType> productTypes;
	public List<ProductType> getProductTypes() {
		return productTypes;
	}

	public void setProductTypes(List<ProductType> productTypes) {
		this.productTypes = productTypes;
	}*/


	public Make() {
	}

	public int getMakeId() {
		return this.makeId;
	}

	public void setMakeId(int makeId) {
		this.makeId = makeId;
	}

	public String getMakeName() {
		return this.makeName;
	}

	public void setMakeName(String makeName) {
		this.makeName = makeName;
	}

	public List<Product> getProducts() {
		return this.products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Product addProduct(Product product) {
		getProducts().add(product);
		product.setMake(this);

		return product;
	}

	public Product removeProduct(Product product) {
		getProducts().remove(product);
		product.setMake(null);

		return product;
	}

}