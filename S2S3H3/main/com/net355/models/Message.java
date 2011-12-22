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
 * Message generated by hbm2java
 */
@Entity
@Table(name = "message", catalog = "net355")
public class Message implements java.io.Serializable {

	private String msgId;
	private User userBySenderId;
	private User userByReceiverId;
	private String msgTitle;
	private String msgContent;
	private String msgType;
	private String msgSendState;
	private String msgRecvState;
	private Date addTime;
	private String state;

	public Message() {
	}

	public Message(String msgId) {
		this.msgId = msgId;
	}

	public Message(String msgId, User userBySenderId, User userByReceiverId,
			String msgTitle, String msgContent, String msgType,
			String msgSendState, String msgRecvState, Date addTime, String state) {
		this.msgId = msgId;
		this.userBySenderId = userBySenderId;
		this.userByReceiverId = userByReceiverId;
		this.msgTitle = msgTitle;
		this.msgContent = msgContent;
		this.msgType = msgType;
		this.msgSendState = msgSendState;
		this.msgRecvState = msgRecvState;
		this.addTime = addTime;
		this.state = state;
	}

	@Id
	@Column(name = "msg_id", unique = true, nullable = false, length = 32)
	public String getMsgId() {
		return this.msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sender_id")
	public User getUserBySenderId() {
		return this.userBySenderId;
	}

	public void setUserBySenderId(User userBySenderId) {
		this.userBySenderId = userBySenderId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "receiver_id")
	public User getUserByReceiverId() {
		return this.userByReceiverId;
	}

	public void setUserByReceiverId(User userByReceiverId) {
		this.userByReceiverId = userByReceiverId;
	}

	@Column(name = "msg_title", length = 200)
	public String getMsgTitle() {
		return this.msgTitle;
	}

	public void setMsgTitle(String msgTitle) {
		this.msgTitle = msgTitle;
	}

	@Column(name = "msg_content")
	public String getMsgContent() {
		return this.msgContent;
	}

	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}

	@Column(name = "msg_type", length = 2)
	public String getMsgType() {
		return this.msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	@Column(name = "msg_send_state", length = 2)
	public String getMsgSendState() {
		return this.msgSendState;
	}

	public void setMsgSendState(String msgSendState) {
		this.msgSendState = msgSendState;
	}

	@Column(name = "msg_recv_state", length = 2)
	public String getMsgRecvState() {
		return this.msgRecvState;
	}

	public void setMsgRecvState(String msgRecvState) {
		this.msgRecvState = msgRecvState;
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