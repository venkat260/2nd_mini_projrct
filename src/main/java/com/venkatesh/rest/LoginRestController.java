package com.venkatesh.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.venkatesh.bindings.LoginForm;
import com.venkatesh.service.UserMgmtService;

@RestController
public class LoginRestController {
	
	@Autowired
	public UserMgmtService service;
	
	@PostMapping("/login")
	public String logIn(@RequestBody LoginForm form)
	{
		return service.login(form);
		
	}

}
