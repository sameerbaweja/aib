package com.afteribuy.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;


/**
 * The persistent class for the file database table.
 * 
 */
@Entity
@NamedQuery(name="File.findAll", query="SELECT f FROM File f")
public class File implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="file_id")
	private int fileId;

	@Column(name="file_entity_id")
	private int fileEntityId;

	@Column(name="file_entity_type")
	private String fileEntityType;

	@Column(name="file_filemime")
	private String fileFilemime;

	@Column(name="file_filename")
	private String fileFilename;

	@Column(name="file_filesize")
	private BigInteger fileFilesize;

	@Column(name="file_timestamp")
	private int fileTimestamp;

	@Column(name="file_url")
	private String fileUrl;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="file_user_id")
	private User user;

	public File() {
	}

	public int getFileId() {
		return this.fileId;
	}

	public void setFileId(int fileId) {
		this.fileId = fileId;
	}

	public int getFileEntityId() {
		return this.fileEntityId;
	}

	public void setFileEntityId(int fileEntityId) {
		this.fileEntityId = fileEntityId;
	}

	public String getFileEntityType() {
		return this.fileEntityType;
	}

	public void setFileEntityType(String fileEntityType) {
		this.fileEntityType = fileEntityType;
	}

	public String getFileFilemime() {
		return this.fileFilemime;
	}

	public void setFileFilemime(String fileFilemime) {
		this.fileFilemime = fileFilemime;
	}

	public String getFileFilename() {
		return this.fileFilename;
	}

	public void setFileFilename(String fileFilename) {
		this.fileFilename = fileFilename;
	}

	public BigInteger getFileFilesize() {
		return this.fileFilesize;
	}

	public void setFileFilesize(BigInteger fileFilesize) {
		this.fileFilesize = fileFilesize;
	}

	public int getFileTimestamp() {
		return this.fileTimestamp;
	}

	public void setFileTimestamp(int fileTimestamp) {
		this.fileTimestamp = fileTimestamp;
	}

	public String getFileUrl() {
		return this.fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}