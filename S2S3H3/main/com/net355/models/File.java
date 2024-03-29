package com.net355.models;

// Generated Dec 21, 2011 3:28:17 PM by Hibernate Tools 3.4.0.CR1

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * File generated by hbm2java
 */
@Entity
@Table(name = "file", catalog = "net355")
public class File implements java.io.Serializable {

	private String fileId;
	private User user;
	private Folder folder;
	private String fileName;
	private String fileType;
	private String fileIp;
	private String fileHref;
	private BigDecimal fileSize;
	private Date addTime;
	private String state;
	private Set<Attachment> attachments = new HashSet<Attachment>(0);

	public File() {
	}

	public File(String fileId) {
		this.fileId = fileId;
	}

	public File(String fileId, User user, Folder folder, String fileName,
			String fileType, String fileIp, String fileHref,
			BigDecimal fileSize, Date addTime, String state,
			Set<Attachment> attachments) {
		this.fileId = fileId;
		this.user = user;
		this.folder = folder;
		this.fileName = fileName;
		this.fileType = fileType;
		this.fileIp = fileIp;
		this.fileHref = fileHref;
		this.fileSize = fileSize;
		this.addTime = addTime;
		this.state = state;
		this.attachments = attachments;
	}

	@Id
	@Column(name = "file_id", unique = true, nullable = false, length = 32)
	public String getFileId() {
		return this.fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "folder_id")
	public Folder getFolder() {
		return this.folder;
	}

	public void setFolder(Folder folder) {
		this.folder = folder;
	}

	@Column(name = "file_name", length = 32)
	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Column(name = "file_type", length = 2)
	public String getFileType() {
		return this.fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	@Column(name = "file_ip", length = 20)
	public String getFileIp() {
		return this.fileIp;
	}

	public void setFileIp(String fileIp) {
		this.fileIp = fileIp;
	}

	@Column(name = "file_href", length = 200)
	public String getFileHref() {
		return this.fileHref;
	}

	public void setFileHref(String fileHref) {
		this.fileHref = fileHref;
	}

	@Column(name = "file_size", precision = 10)
	public BigDecimal getFileSize() {
		return this.fileSize;
	}

	public void setFileSize(BigDecimal fileSize) {
		this.fileSize = fileSize;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "add_time", length = 19)
	public Date getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	@Column(name = "state", length = 2)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "file")
	public Set<Attachment> getAttachments() {
		return this.attachments;
	}

	public void setAttachments(Set<Attachment> attachments) {
		this.attachments = attachments;
	}

}
