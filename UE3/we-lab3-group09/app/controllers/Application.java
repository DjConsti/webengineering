package controllers;

import javax.naming.AuthenticationException;
import javax.persistence.EntityManager;

import ch.qos.logback.core.Context;
import models.Login;
import models.Register;
import models.UserImpl;
import play.Routes;
import play.data.*;
import play.db.jpa.JPA;
import play.mvc.*;
import views.html.*;
import at.ac.tuwien.big.we15.lab2.api.*;

public class Application extends Controller {
	
	public static Result index() {
		
		return redirect("authentication");
	}
	
	@play.db.jpa.Transactional
	public static void storeUser(UserImpl user) {
		EntityManager em = JPA.em();
		em.persist(user);
	}
	
	@play.db.jpa.Transactional
	public static UserImpl fetchUser(String username) {
		EntityManager em = JPA.em();
		return em.find(UserImpl.class, username);
	}

	@play.db.jpa.Transactional
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
		
		UserImpl user = new UserImpl();
		user.setFirstname(registerData.getFirstname());
		user.setLastname(registerData.getLastname());
		user.setAvatar(registerData.getAvatar());
		user.setUsername(registerData.getUsername());
		user.setPassword(registerData.getPassword());
		user.setBirthdate(registerData.getBirthdate());
		user.setGender(registerData.getGender());
		storeUser(user);
		
		System.out.println("Register/User: " + registerData.getUsername());
		return ok(registration.render(Form.form(Register.class)));
	}

	@play.db.jpa.Transactional
	public static Result login() {
		Form<Login> loginForm = Form.form(Login.class).bindFromRequest();
		System.out.println("User: " + loginForm.get().username);
		try {	
			if (loginForm.hasErrors()) {
				return badRequest(authentication.render(loginForm));
			}else
			{
				Login loginData = loginForm.get();

				if(fetchUser(loginData.username) != null) {
					System.out.println("Login erfolgreich");
					session().clear();
				} else {
					System.out.println("Diesen User gibt es nicht!");
				}

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
