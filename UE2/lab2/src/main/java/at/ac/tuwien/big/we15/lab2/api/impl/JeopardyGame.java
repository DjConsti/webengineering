package at.ac.tuwien.big.we15.lab2.api.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import at.ac.tuwien.big.we15.lab2.api.Category;
import at.ac.tuwien.big.we15.lab2.api.Question;

public class JeopardyGame {
	private List<Question> questions;
	private List<Question> askedQuestions;
	private Category category;
	private int questionCounter = 0;

	public JeopardyGame(List<Question> questions, Category category) {
		this.questions = questions;
		askedQuestions = new ArrayList<Question>();
		this.category = category;
	}
	
	public Question getQuestion() {
		if(questionCounter++ > 10) {
			// ?
		}
		
		Random random = new Random();
		int randomQuestionNumber = random.nextInt() % questions.size();
		Question question = null;
		do {
			question = questions.get(randomQuestionNumber);
		}while(!askedQuestions.contains(question));
		askedQuestions.add(question);
	
		return question;
	}

}
