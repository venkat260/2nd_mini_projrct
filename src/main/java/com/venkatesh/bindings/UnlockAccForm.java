package com.venkatesh.bindings;

import lombok.Data;

@Data
public class UnlockAccForm {
	
	private String userEmail;
	
	private String tempPwd;
	
	private String newPwd;
	
	private String conformNewPwd;

}
