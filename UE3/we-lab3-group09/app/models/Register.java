package models;

import java.util.Date;

import play.data.validation.Constraints.MaxLength;
import play.data.validation.Constraints.MinLength;
import play.data.validation.Constraints.Required;
import at.ac.tuwien.big.we15.lab2.api.Avatar;
import controllers.Application;

public class Register {
	public String firstname;
	public String lastname;
	public Date birthdate;
	public String male;
	public String female;
	public String avatar;
	//@Required
	//@MinLength(4)
	//@MaxLength(8)
	public String username;
	//@Required
	//@MinLength(4)
	//@MaxLength(8)
	public String password;
	
	/*private UserImpl user;
	
	public Register() {
		user = new UserImpl();
	}
	
	public String getFirstname() {
		return user.getFirstname();
	}

	public void setFirstname(String firstname) {
		user.setFirstname(firstname);
	}

	public String getLastname() {
		return user.getLastname();
	}

	public void setLastname(String lastname) {
		user.setLastname(lastname);
	}

	public Date getBirthdate() {
		return user.getBirthdate();
	}

	public void setBirthdate(Date birthdate) {
		user.setBirthdate(birthdate);
	}

	public Avatar getAvatar() {
		return user.getAvatar();
	}

	public void setAvatar(Avatar avatar) {
		user.setAvatar(avatar);
	}
	
	public String getGender() {
		return user.getGender();
	}

	public void setGender(String gender) {
		user.setGender(gender);
	}

	public String getUsername() {
		return user.getName();
	}

	public void setUsername(String username) {
		user.setName(username);
	}

	public String getPassword() {
		return user.getPassword();
	}

	public void setPassword(String password) {
		user.setPassword(password);
	}
*/
	public String validate() {
		/*if(Application.fetchUser(user.getName())!=null)
		{
			return "Username already in use!";
		}*/
		
		
		return null;
	}
	
	/*
	enum AVATARS_ENU{
		aldrich_killian = 0
	}*/
}