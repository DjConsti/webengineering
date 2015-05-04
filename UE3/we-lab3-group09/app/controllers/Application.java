package controllers;

import javax.naming.AuthenticationException;

import play.data.*;
import play.mvc.*;
import views.html.*;

public class Application extends Controller {
	
    public static Result index() {
    	index.render("test");
        return redirect( "authentication" );
    }
    
    public static Result login() {
    	Form<Login> loginForm = Form.form(Login.class).bindFromRequest();
    	Login loginData = loginForm.get();
    	System.out.println("Username: " + loginData.getUsername() + " Password: " + loginData.getPassword());
    	
    	if(loginForm.get().username.equals("test") && loginForm.get().password.equals("test"))
    	{
    		
    	   System.out.println("Login erfolgreich");
 	       session().clear();
 	       session("username", loginForm.get().username);
 	       return redirect("jeopardy");
    	}
    	else
    	{
    		System.out.println("Fehler aufgetreten");
    		return ok(authentication.render());
    		//return badRequest(authentication.render(loginForm));
    	}
    }
    

    public  static Result auth()
    {
    	return ok(authentication.render());
    	//return ok(authentication.render(Form.form(Login.class)));
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
    
    public static  class Login {
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
    	
    	/*
    	public String validate() {
    		System.out.println("BLUB " + username + " " + password);
    	    if (username.equals("test") && password.equals("test")) {
    	    	return "";
    	    }
    	    return "Invalid user or password";
    	} */
        
    }
}
