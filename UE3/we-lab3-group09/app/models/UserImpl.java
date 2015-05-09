package models;

import java.util.Comparator;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.Constraint;

import at.ac.tuwien.big.we15.lab2.api.Avatar;
import play.data.validation.Constraints.Required;
import play.data.validation.Constraints;

@Entity
public class UserImpl {
	
	private String firstname;
	private String lastname;
	private Date birthdate;
	private String avatar;
	
	private String gender;
	
	@Constraints.MinLength(4)
	@Constraints.MaxLength(8)
	@Id @Required private String username;
	
	@Constraints.MinLength(4)
	@Constraints.MaxLength(8)
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
	
}
