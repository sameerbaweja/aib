package com.afteribuy.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the notification database table.
 * 
 */
@Entity
@NamedQuery(name="Notification.findAll", query="SELECT n FROM Notification n")
public class Notification implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="notification_id")
	private int notificationId;

	@Column(name="notification_created_at")
	private int notificationCreatedAt;

	@Lob
	@Column(name="notification_description")
	private String notificationDescription;

	@Column(name="notification_entity_id")
	private int notificationEntityId;

	@Column(name="notification_entity_type")
	private String notificationEntityType;

	@Column(name="notification_read")
	private byte notificationRead;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="notification_user_id")
	private User user;

	public Notification() {
	}

	public int getNotificationId() {
		return this.notificationId;
	}

	public void setNotificationId(int notificationId) {
		this.notificationId = notificationId;
	}

	public int getNotificationCreatedAt() {
		return this.notificationCreatedAt;
	}

	public void setNotificationCreatedAt(int notificationCreatedAt) {
		this.notificationCreatedAt = notificationCreatedAt;
	}

	public String getNotificationDescription() {
		return this.notificationDescription;
	}

	public void setNotificationDescription(String notificationDescription) {
		this.notificationDescription = notificationDescription;
	}

	public int getNotificationEntityId() {
		return this.notificationEntityId;
	}

	public void setNotificationEntityId(int notificationEntityId) {
		this.notificationEntityId = notificationEntityId;
	}

	public String getNotificationEntityType() {
		return this.notificationEntityType;
	}

	public void setNotificationEntityType(String notificationEntityType) {
		this.notificationEntityType = notificationEntityType;
	}

	public byte getNotificationRead() {
		return this.notificationRead;
	}

	public void setNotificationRead(byte notificationRead) {
		this.notificationRead = notificationRead;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}