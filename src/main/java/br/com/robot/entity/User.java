package br.com.robot.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="USER_")
public class User extends GenericEntity {
	
	@Column(name="NAME", nullable = false, length = 100)
	private String name;
	
	public User() {
		super();
	}

	public User(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "User [name=" + name + "]";
	}
}