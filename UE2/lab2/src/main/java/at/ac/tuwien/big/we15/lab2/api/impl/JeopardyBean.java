package at.ac.tuwien.big.we15.lab2.api.impl;

import java.util.List;

import at.ac.tuwien.big.we15.lab2.api.Question;

public class JeopardyBean {
	
	private JeopardyGame game;

	public JeopardyBean() {
		
	}
	
	public Question getQuestion() {
		return game.getQuestion();
	}

}
