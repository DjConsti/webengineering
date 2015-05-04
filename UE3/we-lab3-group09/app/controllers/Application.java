package controllers;

import javax.naming.AuthenticationException;

import model.Login;
import model.Register;
import play.data.*;
import play.mvc.*;
import views.html.*;
import at.ac.tuwien.big.we15.lab2.api.*;

public class Application extends Controller {

	public static Result index() {
		
		return redirect("authentication");
	}
	
	public static Result completeReg(){
		Form<Register> registerForm = Form.form(Register.class).bindFromRequest();
		
		try {
			if (registerForm.hasErrors()) {
				return badRequest(registration.render(registerForm));
			}
		} catch (Exception e) {
			return badRequest(registration.render(registerForm));
		}

		Register registerData = registerForm.get();
		System.out.println("Register/User: " + registerData.getUsername());
		return ok(registration.render(Form.form(Register.class)));
	}

	public static Result login() {
		Form<Login> loginForm = Form.form(Login.class).bindFromRequest();

		try {
			if (loginForm.hasErrors()) {
				return badRequest(authentication.render(loginForm));
			}
		} catch (Exception e) {
			return badRequest(authentication.render(loginForm));
		}

		Login loginData = loginForm.get();

		System.out.println("Login erfolgreich");
		session().clear();
		session("username", loginData.username);
		return redirect("jeopardy");

	}

	public static Result auth() {
		return ok(authentication.render(Form.form(Login.class)));
	}

	public static Result jeopardy() {
		return ok(jeopardy.render());
	}

	public static Result question() {
		return ok(question.render());
	}

	public static Result registration() {
		return ok(registration.render(Form.form(Register.class)));
	}

	public static Result winner() {
		return ok(winner.render());
	}
}
