package br.com.robot.entity;

public enum UserStatusEnum {

	ACTIVE("ACTIVE"),
	INATIVE("INATIVE");
	
	public String value;
	
	private UserStatusEnum(String value) {
		this.value = value;
	}
}
