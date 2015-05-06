package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import at.ac.tuwien.big.we15.lab2.api.Avatar;
import at.ac.tuwien.big.we15.lab2.api.User;
import play.data.validation.Constraints.Required;

@Entity
public class UserImpl implements User {
	
	private String firstname;
	private String lastname;
	private Date birthdate;
	private Avatar avatar;
	private String gender;
	@Id @Required private String username;
	@Required private String password;

	public UserImpl() {
		// TODO Auto-generated constructor stub
	}

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

	public Avatar getAvatar() {
		return avatar;
	}

	public void setAvatar(Avatar avatar) {
		this.avatar = avatar;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getName() {
		return username;
	}

	public void setName(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
}
