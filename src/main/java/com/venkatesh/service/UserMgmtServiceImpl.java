package com.venkatesh.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.venkatesh.bindings.LoginForm;
import com.venkatesh.bindings.UnlockAccForm;
import com.venkatesh.bindings.UserRegForm;
import com.venkatesh.entity.CityMasterEntity;
import com.venkatesh.entity.CountryMasterEntity;
import com.venkatesh.entity.StateMasterEntity;
import com.venkatesh.entity.UserDtlsEntity;
import com.venkatesh.reposiotry.CityMasterRepo;
import com.venkatesh.reposiotry.CountryMasterRepo;
import com.venkatesh.reposiotry.StateMasterRepo;
import com.venkatesh.reposiotry.UserDtlsRepo;
import com.venkatesh.utils.EmailUtils;

@Service
public class UserMgmtServiceImpl implements UserMgmtService {

	@Autowired
	public UserDtlsRepo userRepo;

	@Autowired
	public CountryMasterRepo countryRepo;

	@Autowired
	public StateMasterRepo stateRepo;

	@Autowired
	public CityMasterRepo cityRepo;
	
	@Autowired
	public EmailUtils  emailUtils;

	@Override
	public String login(LoginForm loginForm) {

		UserDtlsEntity entity = userRepo.findByUserEmailAndUserPwd(loginForm.getUserMail(),
				loginForm.getUserPwd());

		if (entity == null) {
			return "Invalid Credientials";
		}
		if (entity != null && entity.getAccStatus().equalsIgnoreCase("Locked")) {
			return "Your Account is locked";
		}

		return "SUCESS";
	}

	@Override
	public String emailCheck(String email) {
		UserDtlsEntity entity = userRepo.findByUserEmail(email);

		if (entity == null) {
			return "unique email";
		}

		return "email already registered";
	}

	@Override
	public Map<Integer, String> loadCountry() {

		List<CountryMasterEntity> list = countryRepo.findAll();

		Map<Integer, String> countryMap = new HashMap<>();

		for (CountryMasterEntity entity : list) {
			countryMap.put(entity.getCountryId(), entity.getCountryName());
		}
		return countryMap;
	}

	@Override
	public Map<Integer, String> loadState(Integer country_Id) {

		List<StateMasterEntity> list = stateRepo.findByCountryId(country_Id);

		Map<Integer, String> stateMap = new HashMap<>();

		for (StateMasterEntity entity : list) {
			stateMap.put(entity.getStateId(), entity.getStatename());
		}

		return stateMap;
	}

	@Override
	public Map<Integer, String> loadCity(Integer state_Id) {

		List<CityMasterEntity> list = cityRepo.findByStateId(state_Id);

		Map<Integer, String> cityMap = new HashMap<>();

		for (CityMasterEntity entity : list) {
			cityMap.put(entity.getCityId(), entity.getCityName());
		}

		return cityMap;
	}

	@Override
	public String registerUser(UserRegForm userForm){

		UserDtlsEntity entity = new UserDtlsEntity();

		BeanUtils.copyProperties(userForm, entity);

		entity.setAccStatus("LOCKED");

		entity.setUserPwd(getAlphaNumericString(6));

		UserDtlsEntity save = userRepo.save(entity);

		String email = userForm.getUserEmail();
		
		String subject = "User Registration process";
		
		String file="UNLOCK_ACC_EMAIL_BODY.txt";
		
		String Body = sendEmail(file, entity);
		
		 boolean sendEmail = emailUtils.sendEmail(email, subject, Body);

		 if(save.getUserId()!=null && sendEmail)
		 {
			 return "Sucess";
		 }
		return "fail";
	}

	@Override
	public String unlockAcc(UnlockAccForm form) {
		UserDtlsEntity entity = userRepo.findByUserEmailAndUserPwd(form.getUserEmail(), form.getTempPwd());

		if (entity == null) {
			return "Invalid Temporary Password";
		}
		if (!form.getConformNewPwd().equals(form.getNewPwd())) {
			return "new Pwd and conform Pwd should be same";
		}
		entity.setAccStatus("Unlocked");

		entity.setUserPwd(form.getNewPwd());

		userRepo.save(entity);

		return "Sucess";
	}

	@Override
	public String forgotPwd(String email) {
		UserDtlsEntity entity = userRepo.findByUserEmail(email);

		if (entity == null) {
			return "please enter a valid email";
		}

		
		String subject="forgot password";
		String file="Forgot_pwd.txt";
		
		String body = sendEmail(file, entity);//this line of code is in 207
		
		boolean sendEmail = emailUtils.sendEmail(email, subject, body);
		
		if(sendEmail)
		{
			return "Password is sent to registered Email";
		}
		
		return "error";
	}

	public static String getAlphaNumericString(int n) {

		// lower limit for LowerCase Letters
		int lowerLimit = 97;

		// lower limit for LowerCase Letters
		int upperLimit = 122;

		Random random = new Random();

		// Create a StringBuffer to store the result
		StringBuffer r = new StringBuffer(n);

		for (int i = 0; i < n; i++) {

			// take a random value between 97 and 122
			int nextRandomChar = lowerLimit + (int) (random.nextFloat() * (upperLimit - lowerLimit + 1));

			// append a character at the end of bs
			r.append((char) nextRandomChar);
		}

		// return the resultant string
		return r.toString();
	}
	
	private String sendEmail(String file ,UserDtlsEntity entity)
	{
		
		String mailbody=null;
		try {
		StringBuffer sb= new StringBuffer();
		FileReader fr=new FileReader(file);
		BufferedReader br=new BufferedReader(fr);
		
		String line=br.readLine();//read the first line
		
		while(line!=null)
		{
			sb.append(line);//appending data to the sb
			line=br.readLine();//it will read the next line
		}
		mailbody = sb.toString();//it will convert the buffer object to string
		 mailbody = mailbody.replace("{FNAME}", entity.getFName());
		 
		 mailbody = mailbody.replace("{LNAME}", entity.getLName());
		 
		 mailbody = mailbody.replace("{TEMP-PWD}", entity.getUserPwd());
		 
		 mailbody = mailbody.replace("{EMAIL}", entity.getUserEmail());
		 
		 mailbody = mailbody.replace("{PWD}", entity.getUserPwd());
		 
		br.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return mailbody;
		
	}

}
