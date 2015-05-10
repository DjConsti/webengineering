package controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.AuthenticationException;
import javax.persistence.EntityManager;

import ch.qos.logback.core.Context;
import models.Language;
import models.Login;
import models.Register;
import models.UserImpl;
import play.Routes;
import play.api.i18n.Lang;
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
			
			//parserSDF.format(arg0)
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

		System.out.println(registerData.getFirstname() + " " + registerData.getLastname() + " " + registerData.getAvatar() + " " +
							registerData.getUsername() + " " + registerData.getPassword() + " " + registerData.getBirthdate() + " " 
							+ registerData.getGender() );


		System.out.println("Register/User: " + registerData.getUsername());
		return redirect("authentication");
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
		/*
		List <Category> cat = GameController.games.get(loginForm.get().username).getGame().getCategories();
		
		ArrayList<String> categories = new ArrayList<String>();
		for(int i = 0; i < GameController.games.get(loginForm.get().username).getGame().getCategories().size(); i ++)
		{
			categories.add(cat.get(i).getName());
		}*/
		
		session("user", loginForm.get().username);
		GameController.games.put(loginForm.get().username, new GameController(fetchUser(loginForm.get().username)));
		
		System.out.println("Avatarpath: " + GameController.games.get(session().get("user")).getGame().getHumanPlayer().getUser().getAvatar().getImageFull());
		fetchUser(loginForm.get().username).getAvatar();
		if(fetchUser(loginForm.get().username).getAvatar()!=null)
			GameController.games.get(session().get("user")).getGame().getHumanPlayer().getUser().setAvatar(Avatar.getAvatar(fetchUser(loginForm.get().username).getAvatar()));
		
		String enemyChosenCategory = "";
		String enemyChosenValue = "";
		try {
			enemyChosenCategory = GameController.games.get(session().get("user")).getGame().getMarvinPlayer().getChosenQuestion().getCategory().getName()+"";
			enemyChosenValue = GameController.games.get(session().get("user")).getGame().getMarvinPlayer().getChosenQuestion().getValue()+"";
		} catch (NullPointerException e) {
			
		}
		
		System.out.println(GameController.games.get(session().get("user")).getGame().getHumanPlayer().getUser().getAvatar().getImageHead());
		return ok(jeopardy.render(loginForm.get().username, String.valueOf(1), String.valueOf(0), String.valueOf(0), "+0€", true, "+0€", true, 
				new QuestionWrapper(), GameController.games.get(loginForm.get().username).getGame().getCategories(),
				GameController.games.get(session().get("user")).getGame().getHumanPlayer().getUser().getAvatar(),
				GameController.games.get(session().get("user")).getGame().getMarvin().getAvatar(),
				enemyChosenCategory,
				enemyChosenValue,
				null
				));
	}
	
	public static Result changeLanguage()
	{
		Form<Language> langSelectForm;
		langSelectForm = Form.form(Language.class).bindFromRequest();

		request().getQueryString("language");
		
		if(langSelectForm.get().language.equals("en") || langSelectForm.get().language.equals("de"))
		{
			changeLang(langSelectForm.get().language);
			session("lan", langSelectForm.get().language);
			return ok(authentication.render(Form.form(Login.class)));
		}
		
		return badRequest(authentication.render(Form.form(Login.class)));
	}

	public static Result auth() {
		return ok(authentication.render(Form.form(Login.class)));
	}

	public static Result registration() {
		return ok(registration.render(Form.form(Register.class)));
	}

}
