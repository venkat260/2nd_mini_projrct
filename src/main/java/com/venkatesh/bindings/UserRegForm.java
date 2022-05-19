package com.venkatesh.bindings;

import java.time.LocalDate;

import lombok.Data;

@Data
public class UserRegForm {
	
	
	private String fName;
	private String lName;
	private String userEmail;
	
	private long userMobile;
	private LocalDate DOB;
	private String gender;
	private Integer cityId;
	private Integer  stateId;
	private Integer countryId; 
	

}
