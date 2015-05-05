package controllers;

import javax.naming.AuthenticationException;

import ch.qos.logback.core.Context;
import models.Login;
import models.Register;
import play.Routes;
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
		System.out.println("User: " + loginForm.get().username);
		try {	
			if (loginForm.hasErrors()) {
				return badRequest(authentication.render(loginForm));
			}else
			{
				Login loginData = loginForm.get();

				System.out.println("Login erfolgreich");
				session().clear();

				session("user", loginData.username);
				return redirect("jeopardy");
			}
		} catch (Exception e) {
			return badRequest(authentication.render(loginForm));
		}
	}
	
	
	public static Result auth() {
		return ok(authentication.render(Form.form(Login.class)));
	}
	
	public static Result registration() {
		return ok(registration.render(Form.form(Register.class)));
	}

}
