package at.ac.tuwien.big.we15.lab2.api.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import at.ac.tuwien.big.we15.lab2.api.Answer;
import at.ac.tuwien.big.we15.lab2.api.Category;
import at.ac.tuwien.big.we15.lab2.api.Question;

public class JeopardyGame {
	private List<Question> questions;
	private List<Question> askedQuestions;
	private Category category;
	private int humanPlayerScore = 0;
	private int aiScore = 0;

	public JeopardyGame(List<Question> questions, Category category) {
		this.questions = questions;
		askedQuestions = new ArrayList<Question>();
		this.category = category;
	}

	/**
	 * Spiel neustarten; alle Operationen dafuer sollen hier rein
	 */
	public void restart() {
		this.askedQuestions.clear();
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public int askedQuestionCount() {
		return (this.askedQuestions == null ? 0 : this.askedQuestions.size());
	}

	public Question getQuestion() {
		// TODO teilweise sind nicht 10 Fragen in questions ... deshalb
		// haengt hoert es dann nicht auf zu laden
		Random random = new Random();

		Question question = null;
		do {
			int randomQuestionNumber = Math.abs(random.nextInt())
					% questions.size();
			question = questions.get(randomQuestionNumber);
			// System.out.println("xxx" + randomQuestionNumber + " " +
			// questions.size());
		} while (askedQuestions.contains(question));
		askedQuestions.add(question);

		return question;
	}

	public void makeAiSelections(List<Answer> correctAnswers) {
		List<String> selectedAnswerIds = new ArrayList<String>();

		if (Math.random() < 0.6) {
			//50% probability: correct answers
			for (Answer a : correctAnswers) {
				selectedAnswerIds.add(a.getId() + "");
			}
		}

		this.checkAnswers(selectedAnswerIds, correctAnswers, false);
	}

	public boolean checkAnswers(List<String> selectedAnswerIds,
			List<Answer> correctAnswers, boolean isHuman) {
		List<Integer> correctAnswerIds = new ArrayList<Integer>();
		for (Answer answer : correctAnswers) {
			correctAnswerIds.add(answer.getId());
		}
		System.out.println(correctAnswerIds);
		System.out.println(selectedAnswerIds);
		if (selectedAnswerIds.size() != correctAnswerIds.size()) {
			System.out.println("Falsch geantwortet");
			return false;
		}

		for (String selectedAnswerId : selectedAnswerIds) {
			if (!correctAnswerIds.contains(Integer.valueOf(selectedAnswerId))) {
				System.out.println("Falsch geantwortet");
				return false;
			}
		}

		if (isHuman)
			this.humanPlayerScore += this.askedQuestions.get(
					this.askedQuestions.size() - 1).getValue();
		else
			this.aiScore += this.askedQuestions.get(
					this.askedQuestions.size() - 1).getValue();

		System.out.println("Richtig geantwortet");
		return true;
	}

	public int getHumanPlayerScore() {
		return this.humanPlayerScore;
	}

	public int getAiScore() {
		return this.aiScore;
	}
}
