package br.com.robot.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="TALK")
public class Talk extends GenericEntity {
	
	@Column(name="MESSAGE", nullable = false, length = 100)
	private String message;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="SENDER")
	private User sender;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="RECEIVER")
	private User receiver;
	
	public Talk() {
		super();
	}

	public Talk(String message, br.com.robot.entity.User sender, br.com.robot.entity.User receiver) {
		this.message = message;
		this.sender = sender;
		this.receiver = receiver;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}

	@Override
	public String toString() {
		return "Talk [message=" + message + ", sender=" + sender + ", receiver=" + receiver + "]";
	}
}