package com.venkatesh.entity;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Table(name ="USER_DTLS")
@Data
public class UserDtlsEntity {
	@Id
	@GeneratedValue
	@Column(name="USER_ID")
	private Integer userId ;
	@Column(name="FIRST_NAME")
	private String fName;
	@Column(name="LAST_NAME")
	private String lName;
	@Column(name="USER_EMAIL")
	private String userEmail;
	@Column(name="USER_PWD")
	private String userPwd;
	@Column(name="USER_MOBILE")
	private long userMobile;
	@Column(name="DOB")
	private LocalDate DOB;
	@Column(name="GENDER")
	private String gender;
	@Column(name="CITY_ID")
	private Integer cityId;
	@Column(name="STATE_ID")
	private Integer  stateId;
	@Column(name="COUNTRY_ID")
	private Integer countryId;
	@Column(name="ACC_STATUS")
	private String  accStatus;
	@Column(name ="CREATED_DATE" , updatable=false)
	@CreationTimestamp
	private LocalDate CREATED_DATE;
	@Column(name ="UPDATED_DATE" , insertable=false)
	@UpdateTimestamp
	private LocalDate UPDATED_DATE;
	
	

}
