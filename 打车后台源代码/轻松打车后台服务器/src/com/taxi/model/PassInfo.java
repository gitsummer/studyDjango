package com.taxi.model;

public class PassInfo extends UserInfo {

	
	private  String Address;

	public PassInfo() {
      super();
	}
	public  PassInfo (String iD, String name, String sex, String email, int age,
			int score, User tuser,String address)
	{
	   super( iD,  name,  sex,  email,  age,	 score,  tuser);
	   Address=address;
		 
	
	}
	
	
	
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	

	 
	
}
