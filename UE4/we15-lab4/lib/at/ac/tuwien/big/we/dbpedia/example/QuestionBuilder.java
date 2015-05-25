package at.ac.tuwien.big.we.dbpedia.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import at.ac.tuwien.big.we15.lab2.api.Category;
import at.ac.tuwien.big.we15.lab2.api.Answer;
import at.ac.tuwien.big.we15.lab2.api.Question;
import at.ac.tuwien.big.we15.lab2.api.impl.SimpleCategory;
import at.ac.tuwien.big.we15.lab2.api.impl.SimpleAnswer;
import at.ac.tuwien.big.we15.lab2.api.impl.SimpleQuestion;

public class QuestionBuilder {
	private static int MINIMUM_RIGHT_CHOICES = 1;
	private static int MINIMUM_WRONG_CHOICES = 1;
	
	private Category category;
	private String text;
	private int maxTime = 30;
	private List<String> rightChoices;
	private List<String> wrongChoices;
	
	private int minimumRightChoicesUsed = 3;
	private int maximumRightChoicesUsed = 3;
	
	private int minimumWrongChoicesUsed = 3;
	private int maximumWrongChoicesUsed = 3;
	
	public QuestionBuilder() {
		rightChoices = new ArrayList<String>();
		wrongChoices = new ArrayList<String>();
	}
	
	public QuestionBuilder(String questionText) {
		this();
		setText(questionText);
	}
	
	public QuestionBuilder(String categoryName, String questionText) {
		this(questionText);
		setCategory(categoryName);
	}
	
	public QuestionBuilder(Category category, String questionText) {
		this(questionText);
		setCategory(category);
	}
	
	public QuestionBuilder setText(String text) {
		this.text = text;
		return this;
	}
	
	public String getText() {
		return text;
	}
	
	public QuestionBuilder addRightChoice(String choice) {
		rightChoices.add(choice);
		return this;
	}
	
	public QuestionBuilder addRightChoices(List<String> choices) {
		rightChoices.addAll(choices);
		return this;
	}
	
	public QuestionBuilder addWrongChoice(String choice) {
		wrongChoices.add(choice);
		return this;
	}
	
	public QuestionBuilder addWrongChoices(List<String> choices) {
		wrongChoices.addAll(choices);
		return this;
	}
	
	public List<String> getRightChoices() {
		return rightChoices;
	}
	
	public List<String> getWrongChoices() {
		return wrongChoices;
	}
	
	public int getMaximumRightChoicesUsed() {
		return maximumRightChoicesUsed;
	}

	public QuestionBuilder setMaximumRightChoicesUsed(int maximumRightChoicesUsed) {
		this.maximumRightChoicesUsed = maximumRightChoicesUsed;
		return this;
	}

	public int getMaximumWrongChoicesUsed() {
		return maximumWrongChoicesUsed;
	}

	public QuestionBuilder setMaximumWrongChoicesUsed(int maximumWrongChoicesUsed) {
		this.maximumWrongChoicesUsed = maximumWrongChoicesUsed;
		return this;
	}
	
	public int getMinimumRightChoicesUsed() {
		return minimumRightChoicesUsed;
	}

	public QuestionBuilder setMinimumRightChoicesUsed(int minimumRightChoicesUsed) {
		this.minimumRightChoicesUsed = minimumRightChoicesUsed;
		return this;
	}

	public int getMinimumWrongChoicesUsed() {
		return minimumWrongChoicesUsed;
	}

	public QuestionBuilder setMinimumWrongChoicesUsed(int minimumWrongChoicesUsed) {
		this.minimumWrongChoicesUsed = minimumWrongChoicesUsed;
		return this;
	}
	
	public Category getCategory() {
		return category;
	}
	
	public QuestionBuilder setCategory(Category category) {
		this.category = category;
		return this;
	}
	
	public QuestionBuilder setCategory(String categoryName) {
		return setCategory(new SimpleCategory(categoryName, new ArrayList<Question>()));
	}
	
	public int getMaxTime() {
		return maxTime;
	}
	
	public QuestionBuilder setMaxTime(int maxTime) {
		this.maxTime = maxTime;
		return this;
	}
	
	public Question createQuestion() {
		if(getMaxTime() < 0)
			throw new IllegalArgumentException("MaxTime must be greater than zero.");
		
		if(getCategory() == null)
			throw new IllegalArgumentException("Category can not be empty!");
		
		if(getRightChoices().size() < MINIMUM_RIGHT_CHOICES || 
				getMaximumRightChoicesUsed() < MINIMUM_RIGHT_CHOICES)
			throw new IllegalArgumentException("At least one correct choice must be presented.");
		
		if(getWrongChoices().size() < MINIMUM_WRONG_CHOICES || 
				getMaximumWrongChoicesUsed() < MINIMUM_WRONG_CHOICES)
			throw new IllegalArgumentException("At least one wrong choice must be presented.");
		
		if(getMinimumRightChoicesUsed() < MINIMUM_RIGHT_CHOICES)
			throw new IllegalArgumentException("Can not set number of minimum right choices below " + MINIMUM_RIGHT_CHOICES);
		
		if(getMinimumWrongChoicesUsed() < MINIMUM_WRONG_CHOICES)
			throw new IllegalArgumentException("Can not set number of minimum wrong choices below " + MINIMUM_WRONG_CHOICES);
		
		if(getMinimumRightChoicesUsed() > getRightChoices().size())
			System.err.println("Minimum Right Choices set to " + getMinimumRightChoicesUsed() + ", but only " + getRightChoices().size() + " available. Setting will be ignored.");
		
		if(getMinimumWrongChoicesUsed() > getWrongChoices().size())
			System.err.println("Minimum Wrong Choices set to " + getMinimumWrongChoicesUsed() + ", but only " + getWrongChoices().size() + " available. Setting will be ignored.");
		
		Random random = new Random();
		int nrRightChoices = Math.min(getRightChoices().size(), getMinimumRightChoicesUsed() + 
				random.nextInt(Math.min(getMaximumRightChoicesUsed(), getRightChoices().size())));
		
		int nrWrongChoices = Math.min(getWrongChoices().size(), getMinimumWrongChoicesUsed() + 
				random.nextInt(Math.min(getMaximumWrongChoicesUsed(), getWrongChoices().size())));
		
		Collections.shuffle(getRightChoices());
		Collections.shuffle(getWrongChoices());
		
		Question question = new SimpleQuestion();
		question.setCategory(getCategory());
		question.setValue(getMaxTime());
		question.setText(getText());
		
		Answer choice;
		int choiceId = 0;
		for(int i = 0; i < nrRightChoices; i++) {
			choice = new SimpleAnswer();
			choice.setText(getRightChoices().get(i));
			choice.setId(choiceId++);
			question.addAnswer(choice, true);
		}
		
		for(int i = 0; i < nrWrongChoices; i++) {
			choice = new SimpleAnswer();
			choice.setText(getWrongChoices().get(i));
			choice.setId(choiceId++);
			question.addAnswer(choice, false);
		}
		
		return question;
	}
}
