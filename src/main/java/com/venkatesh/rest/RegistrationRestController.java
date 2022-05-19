package com.venkatesh.rest;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.venkatesh.bindings.UserRegForm;
import com.venkatesh.service.UserMgmtService;

@RestController
public class RegistrationRestController {

	@Autowired
	public UserMgmtService service;

	@GetMapping("/email/{emailId}")
	public String checkEmail(@PathVariable("emailId") String email) {
		return service.emailCheck(email);
	}

	@GetMapping("/countries")
	public Map<Integer, String> getCountries() {
		return service.loadCountry();
	}

	@GetMapping("/states/{country_Id}")
	public Map<Integer, String> getState(@PathVariable("country_Id") Integer country_Id) {
		return service.loadState(country_Id);
	}

	@GetMapping("/cities/{state_Id}")
	public Map<Integer, String> getCity(@PathVariable("state_Id") Integer state_Id) {
		return service.loadCity(state_Id);
	}

	@PostMapping("/user")
	public String userReg(@RequestBody UserRegForm userForm) {
		return service.registerUser(userForm);
	}
}
