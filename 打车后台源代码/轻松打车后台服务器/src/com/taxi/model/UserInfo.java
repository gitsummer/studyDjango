package com.taxi.model;


//乘客， 司机信息的父类
public class UserInfo {
	public String ID;
	public String Name;
	public  String Sex;
	public  String Email;
	public  int Age;
	public  int Score;
	public  User Tuser;
	

	
	public UserInfo() {
	}
	public UserInfo(String iD, String name, String sex, String email, int age,
			int score, User tuser) {
		super();
		ID = iD;
		Name = name;
		Sex = sex;
		Email = email;
		Age = age;
		Score = score;
		Tuser = tuser;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getSex() {
		return Sex;
	}
	public void setSex(String sex) {
		Sex = sex;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public int getAge() {
		return Age;
	}
	public void setAge(int age) {
		Age = age;
	}
	public int getScore() {
		return Score;
	}
	public void setScore(int score) {
		Score = score;
	}
	public User getTuser() {
		return Tuser;
	}
	public void setTuser(User tuser) {
		Tuser = tuser;
	}
	
	
	
	
	
	
	
	
	
	
	
}
