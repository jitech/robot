package br.com.robot.entity;

public enum UserProfileEnum {

	ADM("ADM"),
	COMUM("COMUM"),
	ENTERPRISE("ENTERPRISE");
	
	public String value;
	
	private UserProfileEnum(String value) {
		this.value = value;
	}
}
