package com.net355.models;

// Generated Dec 21, 2011 3:28:17 PM by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Privilege generated by hbm2java
 */
@Entity
@Table(name = "privilege", catalog = "net355")
public class Privilege implements java.io.Serializable {

	private String privId;
	private Admin admin;
	private Module module;
	private String privLevel;
	private Date addTime;
	private String state;

	public Privilege() {
	}

	public Privilege(String privId) {
		this.privId = privId;
	}

	public Privilege(String privId, Admin admin, Module module,
			String privLevel, Date addTime, String state) {
		this.privId = privId;
		this.admin = admin;
		this.module = module;
		this.privLevel = privLevel;
		this.addTime = addTime;
		this.state = state;
	}

	@Id
	@Column(name = "priv_id", unique = true, nullable = false, length = 32)
	public String getPrivId() {
		return this.privId;
	}

	public void setPrivId(String privId) {
		this.privId = privId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "admin_id")
	public Admin getAdmin() {
		return this.admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "mod_id")
	public Module getModule() {
		return this.module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

	@Column(name = "priv_level", length = 2)
	public String getPrivLevel() {
		return this.privLevel;
	}

	public void setPrivLevel(String privLevel) {
		this.privLevel = privLevel;
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

}
