package at.ac.tuwien.big.we15.lab2.api.impl;

import java.util.ArrayList;
import java.util.List;

import at.ac.tuwien.big.we15.lab2.api.Answer;
import at.ac.tuwien.big.we15.lab2.api.Question;

public class JeopardyBean {
	
	private JeopardyGame game;
	private Question q;
	private List<Answer> currentAnswers;
	private ArrayList<Integer> clickedButtonList;

	public JeopardyBean() {
		
	}
	
	public void setClickedButtonList(ArrayList<Integer> clickedButtonList)
	{
		this.clickedButtonList = clickedButtonList;
	}
	
	public void setGame(JeopardyGame game) {
		this.game = game;
	}
	
	public Question getQuestion() {
		this.q = game.getQuestion();
		return this.q;
	}
	
	public int getAskedQuestionCount(){
		return game.askedQuestionCount();
	}
	
	public void getAllAnswers()
	{
		currentAnswers = q.getAllAnswers();
	}
	
	public List<Answer> getCurrentAnswers()
	{
		return this.currentAnswers;
	}
	
	public String toString() {
		return "asdfasdf";
	}
	
	public String isClickedButton(int id)
	{
		if( this.clickedButtonList.contains(id) )
			return "disabled=\"disabled\"";
		else return "";	
	}

}
