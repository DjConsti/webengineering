package controllers;

import play.*;
import play.mvc.*;
import play.mvc.Http.*;
import models.*;
/**
 * Diese Klasse kümmert sich um die Authentifikation in der Session
 * 
 * @author
 *
 */
public class Secured extends Security.Authenticator {

    @Override
    public String getUsername(Context curr) {
    	System.err.println("Current user: " + curr.session().get("user"));
        return curr.session().get("user");
    }

    @Override
    public Result onUnauthorized(Context ctx) {
    	System.err.println("UNAUTHORIZED ACCESS");
        return redirect(routes.Application.login());
    }
}