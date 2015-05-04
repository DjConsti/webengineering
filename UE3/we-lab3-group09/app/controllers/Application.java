package controllers;

import javax.naming.AuthenticationException;

import ch.qos.logback.core.Context;
import models.Login;
import models.Register;
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

	@Security.Authenticated(Secured.class)
	public static Result login() {
		Form<Login> loginForm = Form.form(Login.class).bindFromRequest();

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
	
	@Security.Authenticated(Secured.class)
	public static Result chooseQuestion()
	{
		// selected question id
		int questionId =  -1;
		try{
		questionId = Integer.parseInt(request().body().asFormUrlEncoded().get("question_selection")[0]);
		}catch(Exception e) { 
			System.err.println("USERERROR: INVALID QUESTION SELECT NUMBER");
			return ok(jeopardy.render());
		}
		System.out.println("Selected Question ID: " + questionId);
		
		return ok(question.render());
	}
	@Security.Authenticated(Secured.class)
	public static Result commitAnswer()
	{
		System.out.println("NUMBER OF SELECTED ANSWERS: " + request().body().asFormUrlEncoded().get("answers").length);
		
		int [] selectedAnswers = new int[request().body().asFormUrlEncoded().get("answers").length];
		
		for(int i = 0; i < request().body().asFormUrlEncoded().get("answers").length; i++)
		{
			selectedAnswers[i] = Integer.parseInt(request().body().asFormUrlEncoded().get("answers")[i]);
		}
		
		// TODO in selectedAnswers sind nun die ID enthalten, von den Fragen, die ausgewählt wurden
		// jetzt muss noch die Logik entsprechend implementiert werden.
		
		
		return ok(jeopardy.render());
	}
	
	public static Result auth() {
		return ok(authentication.render(Form.form(Login.class)));
	}
	@Security.Authenticated(Secured.class)
	public static Result jeopardy() {
		return ok(jeopardy.render());
	}
	@Security.Authenticated(Secured.class)
	public static Result question() {
		return ok(question.render());
	}
	
	public static Result registration() {
		return ok(registration.render(Form.form(Register.class)));
	}
	@Security.Authenticated(Secured.class)
	public static Result winner() {
		return ok(winner.render());
	}
}
