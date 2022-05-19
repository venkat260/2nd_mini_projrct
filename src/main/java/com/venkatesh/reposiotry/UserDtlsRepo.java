package com.venkatesh.reposiotry;

import java.io.Serializable;

import java.util.Map;

import org.springframework.data.annotation.QueryAnnotation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.venkatesh.bindings.LoginForm;

import com.venkatesh.entity.UserDtlsEntity;

@Repository
public interface UserDtlsRepo extends JpaRepository<UserDtlsEntity, Integer> {

	
	
	public UserDtlsEntity findByUserEmailAndUserPwd(String email, String pwd);//findBy method naming conventions is very important means variable should be fomed as methods eg UserEmailAndUserPwd

	UserDtlsEntity findByUserEmail(String email);

}
