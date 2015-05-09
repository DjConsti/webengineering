package models;

import play.i18n.Messages;
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
		UserImpl user = Application.fetchUser(username);
		if (user != null && user.getPassword().equals(this.password)) {
			System.out.println("Login erfolgreich");
			Application.session().clear();
			return null;
		} else {
			System.err.println("Diesen User gibt es nicht!");
		}
		// TODO diese nachricht ist eigentlich hinfällig, da jetzt die des
		// messagesfiles benutz wird
		return Messages.get("view.authentication.loginerror");
	}
}
