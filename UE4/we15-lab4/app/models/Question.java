package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * Represents a question, which is stored in the DB
 */
@Entity
public class Question extends BaseEntity {
	
	@Column(name = "textDE")
    private String textDE;
	
	@Column(name = "textEN")
    private String textEN;
	
	@Column(name = "value")
    private int value;

    //The category to which this question belongs to
    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;


    //A list of right choices in this category
   
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "question", cascade = CascadeType.ALL)
    private List<Answer> answers = new ArrayList<Answer>();


    /**
     * Add a wrong choice
     * @param choice
     */
   // @Access(AccessType.PROPERTY)
    public void addWrongAnswer(Answer choice) {
        choice.setQuestion(this);
        choice.setCorrectAnswer(Boolean.FALSE);
        answers.add(choice);
    }


    /**
     * Add a right choice
     * @param choice
     */
  //  @Access(AccessType.PROPERTY)
    public void addRightAnswer(Answer choice) {
        choice.setQuestion(this);
        choice.setCorrectAnswer(Boolean.TRUE);
        answers.add(choice);
    }


    /**
     * Set the text attribute based on the given language. Default to English if no or an invalid language is passed
     * @param name
     * @param lang
     */
    public void setText(String name, String lang) {
        if ("de".equalsIgnoreCase(lang)) {
            this.textDE = name;
        }
        else {
            this.textEN = name;
        }
    }

    /**
     * Get the text attribute based on the given language. Default to English if no or an invalid language is passed
     * @param lang
     * @return
     */
   // @Access(AccessType.PROPERTY)
    public String getText(String lang) {
        if ("de".equalsIgnoreCase(lang)) {
            return this.textDE;
        }
        else {
            return this.textEN;
        }
    }


    @Access(AccessType.PROPERTY)
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Access(AccessType.PROPERTY)
    public String getTextDE() {
        return textDE;
    }

    public void setTextDE(String textDE) {
        this.textDE = textDE;
    }

     @Access(AccessType.PROPERTY)
    public String getTextEN() {
        return textEN;
    }

    public void setTextEN(String textEN) {
        this.textEN = textEN;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> choices) {
        this.answers = choices;
    }
    
    public List<Answer> getCorrectAnswers() {
    	List<Answer> correct = new ArrayList<Answer>();
    	for(Answer c : answers)
    		if(c.isRight())
    			correct.add(c);
    	return correct;
    }
    
    
    public List<Answer> getWrongAnswers() {
    	List<Answer> wrong = new ArrayList<Answer>();
    	for(Answer c : answers)
    		if(c.isWrong())
    			wrong.add(c);
    	return wrong;
    }
    
  
    public List<Answer> getShuffledAnswers() {
    	List<Answer> answers = new ArrayList<>(getAnswers());
    	Collections.shuffle(answers);
    	return answers;
    }
}
