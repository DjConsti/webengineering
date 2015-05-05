package models;

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
		if (username.equals("test") && password.equals("test")) {
			return null;
		}
		return "Invalid user or password";
	}
}
