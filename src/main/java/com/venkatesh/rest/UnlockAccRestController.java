package com.venkatesh.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.venkatesh.bindings.UnlockAccForm;
import com.venkatesh.service.UserMgmtService;

@RestController
public class UnlockAccRestController {

	@Autowired
	public UserMgmtService service;
	
	@PostMapping("/unlock")
	public String unlock(@RequestBody UnlockAccForm form) {
	
		return service.unlockAcc(form);
	}

}
