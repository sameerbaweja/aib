package com.afteribuy.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the comment database table.
 * 
 */
@Entity
@NamedQuery(name="Comment.findAll", query="SELECT c FROM Comment c")
public class Comment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="comment_id")
	private int commentId;

	@Column(name="comment_created_at")
	private int commentCreatedAt;

	@Lob
	@Column(name="comment_description")
	private String commentDescription;

	//bi-directional many-to-one association to Request
	@ManyToOne
	@JoinColumn(name="comment_request_id")
	private Request request;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="comment_user_id")
	private User user;

	public Comment() {
	}

	public int getCommentId() {
		return this.commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public int getCommentCreatedAt() {
		return this.commentCreatedAt;
	}

	public void setCommentCreatedAt(int commentCreatedAt) {
		this.commentCreatedAt = commentCreatedAt;
	}

	public String getCommentDescription() {
		return this.commentDescription;
	}

	public void setCommentDescription(String commentDescription) {
		this.commentDescription = commentDescription;
	}

	public Request getRequest() {
		return this.request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}