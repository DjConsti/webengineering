package controllers;

import javax.naming.AuthenticationException;

import model.Login;
import play.data.*;
import play.mvc.*;
import views.html.*;

public class Application extends Controller {

	public static Result index() {
		jeopardy2.render("string");
		return redirect("authentication");
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
		return ok(registration.render());
	}

	public static Result winner() {
		return ok(winner.render());
	}
}
