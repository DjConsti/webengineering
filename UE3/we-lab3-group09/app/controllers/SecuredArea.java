package controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.naming.AuthenticationException;

import ch.qos.logback.core.Context;
import models.Login;
import models.Register;
import play.Routes;
import play.data.*;
import play.mvc.*;
import views.html.*;
import at.ac.tuwien.big.we15.lab2.api.*;
import at.ac.tuwien.big.we15.lab2.api.impl.PlayJeopardyFactory;
import play.i18n.Messages;

/**
 * Hier kommen alle Methoden rein, die nur aufrufbar sein sollen, wenn der User eingeloggt
 * ist.
 * @author amd 6x
 *
 */
@Security.Authenticated(Secured.class)
public class SecuredArea extends Controller{
	public static GameController controller;
	
	@play.db.jpa.Transactional
	public static Result chooseQuestion()
	{
		String username = session().get("user");
		System.out.println("Username: " + username);
		GameController gamectrl = controller.games.get(username);
		// selected question id
		int questionId =  -1;
		try{
		questionId = Integer.parseInt(request().body().asFormUrlEncoded().get("question_selection")[0]);
		}catch(Exception e) { 
			System.err.println("USERERROR: INVALID QUESTION SELECT NUMBER");
			return jeopardy();
		}
		System.out.println("Selected Question ID: " + questionId + "  " + gamectrl);
		
		gamectrl.addChosenQuestion(questionId);
		
		Random rand = new Random();
		int randomQuestionId = -1;
		gamectrl.getGame().chooseHumanQuestion(questionId);
		gamectrl.addChosenQuestion(gamectrl.getGame().getMarvinPlayer().getChosenQuestion().getId());
		/*
		while(true) {
			if(questionId < 5)
				randomQuestionId = rand.nextInt((7 - 1) + 1) + 1;
			else if(questionId < 10)
				randomQuestionId = rand.nextInt((21 - 15) + 1) + 15;
			else if(questionId < 15)
				randomQuestionId = rand.nextInt((35 - 29) + 1) + 29;
			else if(questionId < 19)
				randomQuestionId = rand.nextInt((28 - 22) + 1) + 22;
			else
				randomQuestionId = rand.nextInt((14 - 8) + 1) + 8;
			
			try {
				
			} catch(IllegalArgumentException e) {
				continue;
			}
			break;
		}*/
		
		List<Category> cat = gamectrl.getGame().getCategories();
		// cat.get(0).getQuestions().get(0).get
		List<Answer> ans = cat.get(0).getQuestions().get(0).getAllAnswers();
		
		for( int i = 0; i < cat.size(); i++)
			System.out.println("Catnames: " + cat.get(i).getName() );
		
		for(int i = 0; i < ans.size();i++)
		{
			System.out.println("Ansids: " + ans.get(i).getId());
		}
		
		return question();
	}

	public static Result commitAnswer()
	{
		String username = session().get("user");
		GameController gamectrl = controller.games.get(username);
		
		
		List<Integer> answerList = new ArrayList<Integer>();
		
		for(int i = 0; i < request().body().asFormUrlEncoded().get("answers").length; i++)
		{
			answerList.add(Integer.parseInt(request().body().asFormUrlEncoded().get("answers")[i]));
		}
		
		gamectrl.getGame().answerHumanQuestion(answerList);
		gamectrl.increaseRound();
	
		if(gamectrl.isGameOver())
			return winner();
		
		return jeopardy();
	}
	
	public static Result restart()
	{
		GameController.games.get(session().get("user")).start();
		GameController.games.get(session().get("user")).setRound(0);
		
		return ok(jeopardy.render(session().get("user"), String.valueOf(1), String.valueOf(0), String.valueOf(0), "+0€", true, "+0€", true, 
				new QuestionWrapper(), GameController.games.get(session().get("user")).getGame().getCategories())
				);
	}
	
	public static Result logout()
	{
		System.err.println("Deleting session");
		session().clear();
		return ok(authentication.render(Form.form(Login.class)));
	}
	
	public static Result jeopardy() {
		// TODO wenn die methode jeopardy von winner aufgerufen wird, sind diese Attribute nicht gesetzt... 
		// entsprechend nur diese attribute holen, wenn sie gesetzt sind. 
		int userMoneyChangeNum = controller.games.get(session().get("user")).getGame().getHumanPlayer().getLatestProfitChange();
		int computerMoneyChangeNum = controller.games.get(session().get("user")).getGame().getMarvinPlayer().getLatestProfitChange();
		String userMoneyChange = String.valueOf(userMoneyChangeNum) + " €";
		String computerMoneyChange = String.valueOf(computerMoneyChangeNum)+" €";
		if (userMoneyChangeNum>=0) {
			userMoneyChange = "+"+userMoneyChange;
		}
		if (computerMoneyChangeNum>=0) {
			computerMoneyChange = "+"+computerMoneyChange;
		}
		
		return ok(jeopardy.render(session().get("user"), 
				String.valueOf(controller.games.get(session().get("user")).getRound()),
				String.valueOf(controller.games.get(session().get("user")).getGame().getHumanPlayer().getProfit()),
				String.valueOf(controller.games.get(session().get("user")).getGame().getMarvinPlayer().getProfit()),
				userMoneyChange,
				userMoneyChangeNum >= 0,
				computerMoneyChange,
				computerMoneyChangeNum >= 0,
				controller.games.get(session().get("user")).getQWrapper(),
				GameController.games.get(session().get("user")).getGame().getCategories())
				);
	}
	

	public static Result question() {
		List<Answer> list = controller.games.get(session().get("user")).getGame().getHumanPlayer().getChosenQuestion().getAllAnswers();
		list.get(0).getId();
		return ok(question.render(session().get("user"), 
				String.valueOf(controller.games.get(session().get("user")).getRound()),
				String.valueOf(controller.games.get(session().get("user")).getGame().getHumanPlayer().getProfit()),
				String.valueOf(controller.games.get(session().get("user")).getGame().getMarvinPlayer().getProfit()),
				controller.games.get(session().get("user")).getGame().getHumanPlayer().getChosenQuestion().getText(),
				list));
	}
	
	
	public static Result winner() {
		int userMoneyChangeNum = controller.games.get(session().get("user")).getGame().getHumanPlayer().getLatestProfitChange();
		int computerMoneyChangeNum = controller.games.get(session().get("user")).getGame().getMarvinPlayer().getLatestProfitChange();
		String userMoneyChange = String.valueOf(userMoneyChangeNum) + " €";
		String computerMoneyChange = String.valueOf(computerMoneyChangeNum)+" €";
		if (userMoneyChangeNum>=0) {
			userMoneyChange = "+"+userMoneyChange;
		}
		if (computerMoneyChangeNum>=0) {
			computerMoneyChange = "+"+computerMoneyChange;
		}
		String humanWinner = "";
		String computerWinner = "";
		if(controller.games.get(session().get("user")).getGame().getHumanPlayer().getProfit() > controller.games.get(session().get("user")).getGame().getMarvinPlayer().getProfit())
			humanWinner = "leader";
		else
			computerWinner = "leader";
		return ok(winner.render(session().get("user"),
				String.valueOf(controller.games.get(session().get("user")).getGame().getHumanPlayer().getProfit()),
				String.valueOf(controller.games.get(session().get("user")).getGame().getMarvinPlayer().getProfit()),
				userMoneyChange,
				computerMoneyChange,
				userMoneyChangeNum >= 0,
				computerMoneyChangeNum >= 0,
				humanWinner,
				computerWinner,
				controller.games.get(session().get("user")).getGame().getHumanPlayer().getProfit() > controller.games.get(session().get("user")).getGame().getMarvinPlayer().getProfit()
				));
	}
}
