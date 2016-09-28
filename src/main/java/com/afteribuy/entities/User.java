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

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="user_id")
	private int userId;

	@Column(name="user_alternate_number")
	private String userAlternateNumber;

	@Column(name="user_created_at")
	private int userCreatedAt;

	@Column(name="user_email")
	private String userEmail;

	@Column(name="user_first_name")
	private String userFirstName;

	@Column(name="user_last_name")
	private String userLastName;

	@Column(name="user_mobile_number")
	private String userMobileNumber;

	@Column(name="user_password_hash")
	private String userPasswordHash;

	@Column(name="user_password_reset_token")
	private String userPasswordResetToken;

	@Column(name="user_updated_at")
	private int userUpdatedAt;

	//bi-directional many-to-one association to Comment
	@OneToMany(mappedBy="user")
	@JsonIgnore
	private List<Comment> comments;

	//bi-directional many-to-one association to File
	@OneToMany(mappedBy="user")
	@JsonIgnore
	private List<File> files;

	//bi-directional many-to-one association to Notification
	@OneToMany(mappedBy="user")
	@JsonIgnore
	private List<Notification> notifications;

	//bi-directional many-to-one association to Product
	@OneToMany(mappedBy="user")
	@JsonIgnore
	private List<Product> products;

	public User() {
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserAlternateNumber() {
		return this.userAlternateNumber;
	}

	public void setUserAlternateNumber(String userAlternateNumber) {
		this.userAlternateNumber = userAlternateNumber;
	}

	public int getUserCreatedAt() {
		return this.userCreatedAt;
	}

	public void setUserCreatedAt(int userCreatedAt) {
		this.userCreatedAt = userCreatedAt;
	}

	public String getUserEmail() {
		return this.userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserFirstName() {
		return this.userFirstName;
	}

	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}

	public String getUserLastName() {
		return this.userLastName;
	}

	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}

	public String getUserMobileNumber() {
		return this.userMobileNumber;
	}

	public void setUserMobileNumber(String userMobileNumber) {
		this.userMobileNumber = userMobileNumber;
	}

	public String getUserPasswordHash() {
		return this.userPasswordHash;
	}

	public void setUserPasswordHash(String userPasswordHash) {
		this.userPasswordHash = userPasswordHash;
	}

	public String getUserPasswordResetToken() {
		return this.userPasswordResetToken;
	}

	public void setUserPasswordResetToken(String userPasswordResetToken) {
		this.userPasswordResetToken = userPasswordResetToken;
	}

	public int getUserUpdatedAt() {
		return this.userUpdatedAt;
	}

	public void setUserUpdatedAt(int userUpdatedAt) {
		this.userUpdatedAt = userUpdatedAt;
	}

	public List<Comment> getComments() {
		return this.comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Comment addComment(Comment comment) {
		getComments().add(comment);
		comment.setUser(this);

		return comment;
	}

	public Comment removeComment(Comment comment) {
		getComments().remove(comment);
		comment.setUser(null);

		return comment;
	}

	public List<File> getFiles() {
		return this.files;
	}

	public void setFiles(List<File> files) {
		this.files = files;
	}

	public File addFile(File file) {
		getFiles().add(file);
		file.setUser(this);

		return file;
	}

	public File removeFile(File file) {
		getFiles().remove(file);
		file.setUser(null);

		return file;
	}

	public List<Notification> getNotifications() {
		return this.notifications;
	}

	public void setNotifications(List<Notification> notifications) {
		this.notifications = notifications;
	}

	public Notification addNotification(Notification notification) {
		getNotifications().add(notification);
		notification.setUser(this);

		return notification;
	}

	public Notification removeNotification(Notification notification) {
		getNotifications().remove(notification);
		notification.setUser(null);

		return notification;
	}

	public List<Product> getProducts() {
		return this.products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Product addProduct(Product product) {
		getProducts().add(product);
		product.setUser(this);

		return product;
	}

	public Product removeProduct(Product product) {
		getProducts().remove(product);
		product.setUser(null);

		return product;
	}

}