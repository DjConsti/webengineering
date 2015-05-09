package models;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import controllers.Application;
import play.data.validation.*;
import play.i18n.Messages;

public class Register {
	private UserImpl user;
	private boolean birthdateValid=false;
	
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

	public void setBirthdate(String birthdate) {
		
		if(birthdate.equals("")) { this.birthdateValid = true; return;}
		
		SimpleDateFormat format = new SimpleDateFormat("dd-mm-yyyy");
		format.setLenient(false);
		try {
			format.parse(birthdate);
			System.out.println("GebDate1: " + format.parse(birthdate));
			this.birthdateValid = true;
		} catch (ParseException e) {
			user.setBirthdate(null);
		}
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

	public List<ValidationError> validate() {
		List<ValidationError> errors = new ArrayList<ValidationError>();
		
		if(Application.fetchUser(user.getUsername())!=null)
		{
			System.out.println("username already exisiting");
			errors.add(new ValidationError("username",Messages.get("view.registration.validation.usernameInUse")));
		}
		
		if(user.getUsername().length() < 4 || user.getUsername().length()>8)
		{
			errors.add(new ValidationError("username", Messages.get("view.registration.user.hover")));
		}
		
		if(user.getPassword().length() < 4 || user.getPassword().length()>8)
		{
			errors.add(new ValidationError("password", Messages.get("view.registration.pw.hover")));
		}
		
		if(!this.birthdateValid)
		{

				errors.add(new ValidationError("birthdate", Messages.get("view.registration.birthday.hover")));
		}
		
		return errors.isEmpty() ? null : errors;
	}
}