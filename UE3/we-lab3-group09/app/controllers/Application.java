package controllers;

import java.util.Date;

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
import at.ac.tuwien.big.we15.lab2.api.impl.PlayJeopardyFactory;

public class Application extends Controller {
	@play.db.jpa.Transactional
	public static Result index() {
		// TODO die 4 zeilen vor abgabe löschen!!!
		
		if(fetchUser("test")==null)
		{
			System.err.println("adding user");
			UserImpl testUser = new UserImpl();
			testUser.setFirstname("");
			testUser.setLastname("");
			testUser.setAvatar(null);
			testUser.setBirthdate(new Date());
			testUser.setGender("");
			testUser.setUsername("test"); testUser.setPassword("test");
			storeUser(testUser);
		}
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
		if (em.find(UserImpl.class, username) == null) {
			System.out.println("ERROR NULL");
			return null;
		}
		return em.find(UserImpl.class, username);
	}

	@play.db.jpa.Transactional
	public static Result completeReg() {
		Form<Register> registerForm = Form.form(Register.class).bindFromRequest();

		try {
			if (registerForm.hasErrors()) {
				//return badRequest(registration.render(registerForm));
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
		Form<Login> loginForm;

		loginForm = Form.form(Login.class).bindFromRequest();
		try {
			if (loginForm.hasErrors()) {
				return badRequest(authentication.render(loginForm));
			}
		} catch (Exception e) {
			return badRequest(authentication.render(loginForm));
		}
		
		session("user", loginForm.get().username);
		GameController.games.put(fetchUser(loginForm.get().username), new GameController(fetchUser(loginForm.get().username)));
		return ok(jeopardy.render());
	}

	public static Result auth() {
		return ok(authentication.render(Form.form(Login.class)));
	}

	public static Result registration() {
		return ok(registration.render(Form.form(Register.class)));
	}

}
