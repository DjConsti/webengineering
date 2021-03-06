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
		clickedButtonList = new ArrayList<Integer>();
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
	
	public List<Answer> getCorrectAnswers()
	{
		return q.getCorrectAnswers();
	}
	
	public String toString() {
		return "JeopardyBean";
	}
	
	public void addClickedButton(int id)
	{
		this.clickedButtonList.add(id);
		game.addClickedButton(id);
	}
	
	public String isClickedButton(int id)
	{
		if( this.clickedButtonList.contains(id) )
			return "disabled=\"disabled\"";
		else return "";	
	}
	
	public ArrayList<Integer> getClickedButtonList()
	{
		return this.clickedButtonList;
	}
	
	public int getHumanScore()
	{
		return game.getHumanPlayerScore();
	}
	
	public int getAiScore()
	{
		return game.getAiScore();
	}
	
	/*public int getHumanScoreChange()
	{
		return game.getHumanPlayerScoreChange();
	}
	
	public int getAiScoreChange()
	{
		return game.getAiScoreChange();
	}*/

	/**
	 * Wenn der Mensch gewonnen hat, liefert die Methode
	 * true zurueck, sonst falsch.
	 * 
	 */
	public boolean getWinner()
	{
		if(this.getHumanScore() > this.getAiScore())
			return true;

		return false;
	}
	
	public String getUserCorrectStatus() {
		return game.getUserCorrectStatus();
	}
	
	public String getUserCorrectStatusText() {
		return game.getUserCorrectStatusText();
	}
	
	public String getAiCorrectStatus() {
		return game.getAiCorrectStatus();
	}
	
	public String getAiCorrectStatusText() {
		return game.getAiCorrectStatusText();
	}
	
	public int getCurrentEuroValue() {
		return game.getCurrentEuroValue();
	}
	
	public String getUserEuroChangeStatus() {
		return game.getUserEuroChangeStatus();
	}
	
	public String getAiEuroChangeStatus() {
		return game.getAiEuroChangeStatus();
	}
	
	public boolean hasAiLowerScore() {
		return game.getAiScore() < game.getHumanPlayerScore();
	}
	
	public String getAiChosenTopic() {
		return game.getAiChosenTopic();
	}
	
	public int getAiChosenValue() {
		return game.getAiChosenValue();
	}
	
	public String getUserName() {
		return game.getUserName();
	}
	
	public void setUserName(String userName) {
		game.setUserName(userName);
	}
}
