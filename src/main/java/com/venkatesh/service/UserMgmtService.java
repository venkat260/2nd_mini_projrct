package com.venkatesh.service;

import java.util.Map;

import com.venkatesh.bindings.LoginForm;
import com.venkatesh.bindings.UnlockAccForm;
import com.venkatesh.bindings.UserRegForm;

public interface UserMgmtService {
	
    
	
	//log in functionality
	public String login(LoginForm loginForm);
	
    //Registration functionality
	
	//checking for mail uniqueness
	public String emailCheck(String email);
	
	public Map<Integer ,String > loadCountry();
	
	public Map<Integer ,String > loadState(Integer country_Id);
	
	public Map<Integer ,String > loadCity(Integer state_Id);
	
	public String registerUser(UserRegForm userForm);
	
	public String unlockAcc(UnlockAccForm form);
	
	public String forgotPwd(String email);
	
	




}
