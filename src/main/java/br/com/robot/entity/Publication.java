package br.com.robot.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="PUBLICATION")
public class Publication extends GenericEntity {
	
	@Column(name="TITLE", nullable = false, length = 100)
	private String title;
	
	@Column(name="DESCRIBE", nullable = false, length = 200)
	private String describe;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="SENDER")
	private User sender;
	
	@Column(name="VALIDATE", nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date validate;
	
	public Publication() {
		super();
	}

	public Publication(String title, String describe, User sender, Date validate) {
		this.title = title;
		this.describe = describe;
		this.sender = sender;
		this.validate = validate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public Date getValidate() {
		return validate;
	}

	public void setValidate(Date validate) {
		this.validate = validate;
	}

	@Override
	public String toString() {
		return "Publication [title=" + title + ", describe=" + describe + ", sender=" + sender + ", validate="
				+ validate + "]";
	}
}