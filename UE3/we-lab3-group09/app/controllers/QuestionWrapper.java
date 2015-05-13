package controllers;

import java.util.ArrayList;
import java.util.List;

public class QuestionWrapper {
	
	private List<Integer> chosenQuestions;

	public QuestionWrapper() {
		chosenQuestions = new ArrayList<Integer>();
	}
	
	public void add(int id) {
		chosenQuestions.add(id);
	}
	
	public String getQuestionStatus(int id) {
		if(chosenQuestions.contains(id))
			return "disabled=\"disabled\"";
		return "";
	}

}
