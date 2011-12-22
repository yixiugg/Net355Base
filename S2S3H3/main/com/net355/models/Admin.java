package com.net355.models;

// Generated Dec 21, 2011 3:28:17 PM by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Indexed;

/**
 * Admin generated by hbm2java
 */
@Entity
@Indexed(index="net355")
@Table(name = "admin", catalog = "net355")
public class Admin implements java.io.Serializable {

	private String adminId;
	private String adminAcc;
	private String adminPwd;
	private String adminType;
	private String adminName;
	private String adminEmail;
	private Date addTime;
	private String state;
	private Set<Ad> ads = new HashSet<Ad>(0);
	private Set<Privilege> privileges = new HashSet<Privilege>(0);
	private Set<Notice> notices = new HashSet<Notice>(0);
	private Set<News> newses = new HashSet<News>(0);

	public Admin() {
	}

	public Admin(String adminId) {
		this.adminId = adminId;
	}

	public Admin(String adminId, String adminAcc, String adminPwd,
			String adminType, String adminName, String adminEmail,
			Date addTime, String state, Set<Ad> ads, Set<Privilege> privileges,
			Set<Notice> notices, Set<News> newses) {
		this.adminId = adminId;
		this.adminAcc = adminAcc;
		this.adminPwd = adminPwd;
		this.adminType = adminType;
		this.adminName = adminName;
		this.adminEmail = adminEmail;
		this.addTime = addTime;
		this.state = state;
		this.ads = ads;
		this.privileges = privileges;
		this.notices = notices;
		this.newses = newses;
	}

	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@DocumentId
	@Column(name = "admin_id", unique = true, nullable = false, length = 32)
	public String getAdminId() {
		return this.adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	@Column(name = "admin_acc", length = 20)
	public String getAdminAcc() {
		return this.adminAcc;
	}

	public void setAdminAcc(String adminAcc) {
		this.adminAcc = adminAcc;
	}

	@Column(name = "admin_pwd", length = 32)
	public String getAdminPwd() {
		return this.adminPwd;
	}

	public void setAdminPwd(String adminPwd) {
		this.adminPwd = adminPwd;
	}

	@Column(name = "admin_type", length = 2)
	public String getAdminType() {
		return this.adminType;
	}

	public void setAdminType(String adminType) {
		this.adminType = adminType;
	}

	@Column(name = "admin_name", length = 20)
	public String getAdminName() {
		return this.adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	@Column(name = "admin_email", length = 100)
	public String getAdminEmail() {
		return this.adminEmail;
	}

	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "admin")
	public Set<Ad> getAds() {
		return this.ads;
	}

	public void setAds(Set<Ad> ads) {
		this.ads = ads;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "admin")
	public Set<Privilege> getPrivileges() {
		return this.privileges;
	}

	public void setPrivileges(Set<Privilege> privileges) {
		this.privileges = privileges;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "admin")
	public Set<Notice> getNotices() {
		return this.notices;
	}

	public void setNotices(Set<Notice> notices) {
		this.notices = notices;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "admin")
	public Set<News> getNewses() {
		return this.newses;
	}

	public void setNewses(Set<News> newses) {
		this.newses = newses;
	}

}
