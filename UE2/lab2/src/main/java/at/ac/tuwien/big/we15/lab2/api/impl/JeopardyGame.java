package at.ac.tuwien.big.we15.lab2.api.impl;

import java.util.List;

import at.ac.tuwien.big.we15.lab2.api.Category;
import at.ac.tuwien.big.we15.lab2.api.Question;

public class JeopardyGame {
	private List<Question> questions;
	private Category category;

	public JeopardyGame(List<Question> questions, Category category) {
		this.questions = questions;
		this.category = category;
	}

}
