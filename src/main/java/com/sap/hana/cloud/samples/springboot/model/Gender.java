package com.sap.hana.cloud.samples.springboot.model;

/**
 * Created by Shishkov A.V. on 08.06.18.
 */

public enum Gender {
	FEMALE("Ж"), MALE("М");
	String gender;

	Gender(String gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return gender;
	}
}
