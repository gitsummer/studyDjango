package com.taxi.model;

public class DrivInfo extends UserInfo {
	
	private  String Plate;
	private  String License;
	private  String Model;
	
	
	
	
	public  DrivInfo ()
	{
	   super();	
	}
	
	public  DrivInfo (String iD, String name, String sex, String email, int age,
			int score, User tuser,String plate,String license,String model)
	{
	   super( iD,  name,  sex,  email,  age,	 score,  tuser);	
	    
	    Plate=plate;
	    License=license;
	    Model=model;
	}
	
	
	public String getPlate() {
		return Plate;
	}
	public void setPlate(String plate) {
		Plate = plate;
	}
	public String getLicense() {
		return License;
	}
	public void setLicense(String license) {
		License = license;
	}
	public String getModel() {
		return Model;
	}
	public void setModel(String model) {
		Model = model;
	}
	
	

	
	
	
	


}
