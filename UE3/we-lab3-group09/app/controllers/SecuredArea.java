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

/**
 * Hier kommen alle Methoden rein, die nur aufrufbar sein sollen, wenn der User eingeloggt
 * ist.
 * @author amd 6x
 *
 */
@Security.Authenticated(Secured.class)
public class SecuredArea extends Controller{
	
	

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
	
	public static Result logout()
	{
		System.err.println("Deleting session");
		session().clear();
		return ok(authentication.render(Form.form(Login.class)));
	}
	
	public static Result jeopardy() {
		return ok(jeopardy.render());
	}
	

	public static Result question() {
		return ok(question.render());
	}
	
	
	public static Result winner() {
		return ok(winner.render());
	}
}
