package com.venkatesh.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.venkatesh.service.UserMgmtService;

@RestController
public class ForgotPwdRestController {
	
	@Autowired
	public UserMgmtService UserMgmtServiceImpl;
	
	@GetMapping("/forgotPwd/{emailId}")
	public String forgotPwd(@PathVariable("emailId") String email) 
	{
		return UserMgmtServiceImpl.forgotPwd(email);
	}
	
	

}
