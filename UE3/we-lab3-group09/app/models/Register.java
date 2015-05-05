package models;

import java.util.Date;

public class Register {
	private UserImpl user;
	
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

	public String getAvatar() {
		return user.getAvatar();
	}

	public void setAvatar(String avatar) {
		user.setAvatar(avatar);
	}
	
	public String getGender() {
		return user.getGender();
	}

	public void setGender(String gender) {
		user.setGender(gender);
	}

	public String getUsername() {
		return user.getUsername();
	}

	public void setUsername(String username) {
		user.setUsername(username);
	}

	public String getPassword() {
		return user.getPassword();
	}

	public void setPassword(String password) {
		user.setPassword(password);
	}

	public String validate() {
		if (true) {
			// TODO hier Ã¼berprÃ¼fen ob die register daten OK sind
			return null;
		}
		return "Invalid user or password";
	}
	
	/*
	enum AVATARS_ENU{
		aldrich_killian = 0
	}*/
}