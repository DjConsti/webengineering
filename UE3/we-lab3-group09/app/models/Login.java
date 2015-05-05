package models;

import views.html.authentication;
import controllers.Application;

public class Login {
	public String username;
	public String password;

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

		if(Application.fetchUser(username) != null) {
			System.out.println("Login erfolgreich");
			Application.session().clear();
			return null;
		} else {
			System.err.println("Diesen User gibt es nicht!");
		}
		
		return "Invalid user or password";
	}
}
