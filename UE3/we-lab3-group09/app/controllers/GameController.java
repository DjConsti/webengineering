package controllers;

import java.util.HashMap;

import at.ac.tuwien.big.we15.lab2.api.JeopardyFactory;
import at.ac.tuwien.big.we15.lab2.api.JeopardyGame;
import at.ac.tuwien.big.we15.lab2.api.impl.PlayJeopardyFactory;
import models.UserImpl;

public class GameController {
	public static HashMap<UserImpl, GameController> games = new HashMap<UserImpl, GameController>();
	
	private JeopardyFactory factory;
	private JeopardyGame game;

	public GameController(UserImpl user) {
		factory = new PlayJeopardyFactory("conf/data.de.json");
		game = factory.createGame(user);
	}
	
	public JeopardyGame getGame() {
		return game;
	}

}
