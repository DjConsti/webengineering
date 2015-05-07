package controllers;

import java.util.HashMap;

import at.ac.tuwien.big.we15.lab2.api.JeopardyFactory;
import at.ac.tuwien.big.we15.lab2.api.JeopardyGame;
import at.ac.tuwien.big.we15.lab2.api.impl.PlayJeopardyFactory;
import models.UserImpl;

public class GameController {
	public static HashMap<String, GameController> games = new HashMap<String, GameController>();
	
	private JeopardyFactory factory;
	private JeopardyGame game;
	private int round=1;

	public GameController(UserImpl user) {
		factory = new PlayJeopardyFactory("data.de.json");
		game = factory.createGame("test");
		if(game == null )
			System.err.println("GAME IS NULL 1");
	}
	
	public JeopardyGame getGame() {
		
		if(game == null )
			System.err.println("GAME IS NULL 2");
		return game;
	}
	
	public void increaseRound() {
		round++;
	}
	
	public int getRound() {
		return round;
	}

}
