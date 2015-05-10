package controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import at.ac.tuwien.big.we15.lab2.api.Answer;
import at.ac.tuwien.big.we15.lab2.api.JeopardyFactory;
import at.ac.tuwien.big.we15.lab2.api.JeopardyGame;
import at.ac.tuwien.big.we15.lab2.api.Question;
import at.ac.tuwien.big.we15.lab2.api.impl.PlayJeopardyFactory;
import models.UserImpl;
import play.i18n.Messages;

public class GameController {
	public static HashMap<String, GameController> games = new HashMap<String, GameController>();
	
	private JeopardyFactory factory;
	private JeopardyGame game;
	private int round=1;
	private QuestionWrapper qWrapper;

	public GameController(UserImpl user) {
		start();
	}
	
	public void start()
	{
		if(Application.session().get("lan")==null || Application.session().get("lan").equals("de"))
			factory = new PlayJeopardyFactory("data.de.json");
		else
			factory = new PlayJeopardyFactory("data.en.json");
		game = factory.createGame("test");
		if(game == null )
			System.err.println("GAME IS NULL 1");
		qWrapper = new QuestionWrapper();
	}
	
	public JeopardyGame getGame() {
		
		if(game == null )
			System.err.println("GAME IS NULL 2");
		return game;
	}
	
	public void setGame(JeopardyGame game)
	{
		this.game = game;
	}
	
	public void increaseRound() {
		round++;
	}
	
	public int getRound() {
		return round;
	}
	
	public void setRound(int round)
	{
		this.round = round;
	}
	
	public boolean isGameOver() {
		return round > 10;
	}
	
	public void addChosenQuestion(int id) {

		qWrapper.add(id);
	}
	
	public void setQWrapper(QuestionWrapper qWrapper)
	{
		this.qWrapper = qWrapper;
	}
	public QuestionWrapper getQWrapper() {
		return qWrapper;
	}

}
