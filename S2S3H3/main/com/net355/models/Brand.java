package com.net355.models;

// Generated Dec 21, 2011 3:28:17 PM by Hibernate Tools 3.4.0.CR1

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
 * Brand generated by hbm2java
 */
@Entity
@Table(name = "brand", catalog = "net355")
public class Brand implements java.io.Serializable {

	private String brandId;
	private User user;
	private String brandName;
	private String brandDesc;
	private Date editTime;
	private String editIp;
	private Date addTime;
	private String state;
	private Set<Product> products = new HashSet<Product>(0);

	public Brand() {
	}

	public Brand(String brandId) {
		this.brandId = brandId;
	}

	public Brand(String brandId, User user, String brandName, String brandDesc,
			Date editTime, String editIp, Date addTime, String state,
			Set<Product> products) {
		this.brandId = brandId;
		this.user = user;
		this.brandName = brandName;
		this.brandDesc = brandDesc;
		this.editTime = editTime;
		this.editIp = editIp;
		this.addTime = addTime;
		this.state = state;
		this.products = products;
	}

	@Id
	@Column(name = "brand_id", unique = true, nullable = false, length = 32)
	public String getBrandId() {
		return this.brandId;
	}

	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "brand_name", length = 50)
	public String getBrandName() {
		return this.brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	@Column(name = "brand_desc", length = 500)
	public String getBrandDesc() {
		return this.brandDesc;
	}

	public void setBrandDesc(String brandDesc) {
		this.brandDesc = brandDesc;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "edit_time", length = 19)
	public Date getEditTime() {
		return this.editTime;
	}

	public void setEditTime(Date editTime) {
		this.editTime = editTime;
	}

	@Column(name = "edit_ip", length = 20)
	public String getEditIp() {
		return this.editIp;
	}

	public void setEditIp(String editIp) {
		this.editIp = editIp;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "brand")
	public Set<Product> getProducts() {
		return this.products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

}
