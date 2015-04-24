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
	//private int humanPlayerScoreChange = 0;
	//private int aiScoreChange = 0;
	private boolean userIsCorrect = false;
	private boolean aiIsCorrect = false;
	private int[] euroValues = {100, 200, 500, 750,
			100, 200, 500, 750, 1000,
			100, 200, 500, 750, 1000,
			100, 200, 500, 750,
			100, 200, 500, 750, 1000};
	private int currentEuroValue;

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
		humanPlayerScore = 0;
		aiScore = 0;
		userIsCorrect = false;
		aiIsCorrect = false;
		currentEuroValue = 0;
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

	public void checkAnswers(List<String> selectedAnswerIds,
			List<Answer> correctAnswers, boolean isHuman) {
		List<Integer> correctAnswerIds = new ArrayList<Integer>();
		for (Answer answer : correctAnswers) {
			correctAnswerIds.add(answer.getId());
		}

		if (selectedAnswerIds.size() != correctAnswerIds.size()) {
			if(isHuman) {
				userIsCorrect = false;
				humanPlayerScore -= currentEuroValue/2;
			}
			else {
				aiIsCorrect = false;
				aiScore -= currentEuroValue/2;
			}
			return;
		}

		for (String selectedAnswerId : selectedAnswerIds) {
			if (!correctAnswerIds.contains(Integer.valueOf(selectedAnswerId))) {
				if(isHuman) {
					userIsCorrect = false;
					humanPlayerScore -= currentEuroValue/2;
				}
				else {
					aiIsCorrect = false;
					aiScore -= currentEuroValue/2;
				}
				return;
			}
		}

		if (isHuman) {
			/*this.humanPlayerScoreChange =this.askedQuestions.get(
					this.askedQuestions.size() - 1).getValue();
			this.humanPlayerScore += humanPlayerScoreChange;*/
			humanPlayerScore += currentEuroValue;
			userIsCorrect = true;
		}
		else {
			/*this.aiScoreChange = this.askedQuestions.get(
					this.askedQuestions.size() - 1).getValue();
			this.aiScore += this.aiScoreChange;*/
			aiScore += currentEuroValue;
			aiIsCorrect = true;
		}
	}

	public int getHumanPlayerScore() {
		return this.humanPlayerScore;
	}

	public int getAiScore() {
		return this.aiScore;
	}
	
	/*public int getHumanPlayerScoreChange() {
		return humanPlayerScoreChange;
	}

	public int getAiScoreChange() {
		return aiScoreChange;
	}*/
	
	public String getUserCorrectStatus() {
		if(userIsCorrect)
			return "positive";
		else
			return "negative";
	}
	
	public String getUserCorrectStatusText() {
		if(userIsCorrect)
			return "richtig";
		else
			return "falsch";
	}
	
	public String getAiCorrectStatus() {
		if(aiIsCorrect)
			return "positive";
		else
			return "negative";
	}
	
	public String getAiCorrectStatusText() {
		if(aiIsCorrect)
			return "richtig";
		else
			return "falsch";
	}
	
	public void setCurrentEuroValue(int questionNumber) {
		currentEuroValue = euroValues[questionNumber-1];
	}
	
	public int getCurrentEuroValue() {
		return currentEuroValue;
	}
	
	public String getUserEuroChangeStatus() {
		if(userIsCorrect) {
			return "+" + currentEuroValue;
		} else {
			return "-" + (currentEuroValue/2);
		}
	}
	
	public String getAiEuroChangeStatus() {
		if(aiIsCorrect) {
			return "+" + currentEuroValue;
		} else {
			return "-" + (currentEuroValue/2);
		}
	}
}
