package models;

import java.util.Date;

public class Register {
	public String firstname;
	public String lastname;
	public Date birthdate;
	public String avatar; // public AVATARS_ENU ...
	public String gender;
	public String username;
	public String password;
	

	
	public String getFirstname() {
		return firstname;
	}



	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}



	public String getLastname() {
		return lastname;
	}



	public void setLastname(String lastname) {
		this.lastname = lastname;
	}



	public Date getBirthdate() {
		return birthdate;
	}



	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}



	public String getAvatar() {
		return avatar;
	}



	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}



	public String getGender() {
		return gender;
	}



	public void setGender(String gender) {
		this.gender = gender;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String validate() {
		if (true) {
			// TODO hier überprüfen ob die register daten OK sind
			return null;
		}
		return "Invalid user or password";
	}
	
	/*
	enum AVATARS_ENU{
		aldrich_killian = 0
	}*/
}
