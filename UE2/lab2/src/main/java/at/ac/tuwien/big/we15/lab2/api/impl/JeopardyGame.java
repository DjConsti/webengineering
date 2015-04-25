package at.ac.tuwien.big.we15.lab2.api.impl;

import java.util.ArrayList;
import java.util.Collections;
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
	private String userName;
	private ArrayList<Integer> clickedButtonList;
	private int chosenCategory = 0;

	public JeopardyGame(List<Question> questions, Category category) {
		this.questions = questions;
		askedQuestions = new ArrayList<Question>();
		this.category = category;
		this.clickedButtonList = new ArrayList<Integer>();
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
		this.clickedButtonList.clear();
		chosenCategory = 0;
	}
	
	public void addClickedButton(int id)
	{
		this.clickedButtonList.add(id);
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

		Question question = null;
		do {
			int randomQuestionNumber = (int)(Math.random()*questions.size());
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
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getAiChosenTopic() {
		List<String> list = new ArrayList<String>();
		if (!this.clickedButtonList.contains(1)||
				!this.clickedButtonList.contains(2)||
				!this.clickedButtonList.contains(3)||
				!this.clickedButtonList.contains(4)) {
			list.add("Internet");
		}
		if (!this.clickedButtonList.contains(5)||
				!this.clickedButtonList.contains(6)||
				!this.clickedButtonList.contains(7)||
				!this.clickedButtonList.contains(8)||
				!this.clickedButtonList.contains(9)) {
			list.add("SSD");
		}
		if (!this.clickedButtonList.contains(10)||
				!this.clickedButtonList.contains(11)||
				!this.clickedButtonList.contains(12)||
				!this.clickedButtonList.contains(13)||
				!this.clickedButtonList.contains(14)) {
			list.add("Web Eng.");
		}
		if (!this.clickedButtonList.contains(15)||
				!this.clickedButtonList.contains(16)||
				!this.clickedButtonList.contains(17)||
				!this.clickedButtonList.contains(18)) {
			list.add("Web Tech.");
		}
		if (!this.clickedButtonList.contains(19)||
				!this.clickedButtonList.contains(20)||
				!this.clickedButtonList.contains(21)||
				!this.clickedButtonList.contains(22)||
				!this.clickedButtonList.contains(23)) {
			list.add("TUWIEN");
		}
		Collections.shuffle(list);
		String chosen = list.get(0);
		if (chosen.equals("Internet")) {
			chosenCategory = 1;
		} else if (chosen.equals("SSD")) {
			chosenCategory = 2;
		} else if (chosen.equals("Web Tech.")) {
			chosenCategory = 3;
		} else if (chosen.equals("Web Eng.")) {
			chosenCategory = 4;
		} else if (chosen.equals("TUWIEN")) {
			chosenCategory = 5;
		}
		return chosen;
	}
	
	public int getAiChosenValue() {
		List<Integer> list = new ArrayList<Integer>();
		switch (chosenCategory) {
		case 1:
			if (!this.clickedButtonList.contains(1)) {
				list.add(100);
			}
			if (!this.clickedButtonList.contains(2)) {
				list.add(200);
			}
			if (!this.clickedButtonList.contains(3)) {
				list.add(500);
			}
			if (!this.clickedButtonList.contains(4)) {
				list.add(750);
			}
			break;
		case 2:
			if (!this.clickedButtonList.contains(5)) {
				list.add(100);
			}
			if (!this.clickedButtonList.contains(6)) {
				list.add(200);
			}
			if (!this.clickedButtonList.contains(7)) {
				list.add(500);
			}
			if (!this.clickedButtonList.contains(8)) {
				list.add(750);
			}
			if (!this.clickedButtonList.contains(9)) {
				list.add(1000);
			}
			break;
		case 3:
			if (!this.clickedButtonList.contains(10)) {
				list.add(100);
			}
			if (!this.clickedButtonList.contains(11)) {
				list.add(200);
			}
			if (!this.clickedButtonList.contains(12)) {
				list.add(500);
			}
			if (!this.clickedButtonList.contains(13)) {
				list.add(750);
			}
			if (!this.clickedButtonList.contains(14)) {
				list.add(1000);
			}
			break;
		case 4:
			if (!this.clickedButtonList.contains(15)) {
				list.add(100);
			}
			if (!this.clickedButtonList.contains(16)) {
				list.add(200);
			}
			if (!this.clickedButtonList.contains(17)) {
				list.add(500);
			}
			if (!this.clickedButtonList.contains(18)) {
				list.add(750);
			}
			break;
		case 5:
			if (!this.clickedButtonList.contains(19)) {
				list.add(100);
			}
			if (!this.clickedButtonList.contains(20)) {
				list.add(200);
			}
			if (!this.clickedButtonList.contains(21)) {
				list.add(500);
			}
			if (!this.clickedButtonList.contains(22)) {
				list.add(750);
			}
			if (!this.clickedButtonList.contains(23)) {
				list.add(1000);
			}
			break;
		default:
			break;
		}
		Collections.shuffle(list);
		return list.get(0);
	}
}
