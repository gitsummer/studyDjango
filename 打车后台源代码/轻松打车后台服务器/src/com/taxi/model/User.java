package com.taxi.model;

public class User {
	
 private String ID;
 private String Password;
 private String Type;

 
 

public User() {

}
public User(String iD, String password, String type) {
	super();
	ID = iD;
	Password = password;
	Type = type;
}
public String getID() {
	return ID;
}
public void setID(String iD) {
	ID = iD;
}
public String getPassword() {
	return Password;
}
public void setPassword(String password) {
	Password = password;
}
public String getType() {
	return Type;
}
public void setType(String type) {
	Type = type;
}

public boolean equals(User other) {
	if (this == other)
		return true;
	if (other == null)
		return false;

	if (ID == null) {
		if (other.ID != null)
			return false;
	} else if (!ID.equals(other.ID))
		return false;
	if (Password == null) {
		if (other.Password != null)
			return false;
	} else if (!Password.equals(other.Password))
		return false;
	if (Type == null) {
		if (other.Type != null)
			return false;
	} else if (!Type.equals(other.Type))
		return false;
	return true;
}
 
 

}
