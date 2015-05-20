package models;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;


/**
 * Represents an answer which is stored in the DB
 */
@Entity
public class Answer extends BaseEntity {

    private String textDE;
    private String textEN;
    
    private Boolean correctAnswer;
    
    @ManyToOne
    private Question question;

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
   // @Access(AccessType.PROPERTY)
    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
    //@Access(AccessType.PROPERTY)
    public String getTextDE() {
        return textDE;
    }

    public void setTextDE(String textDE) {
        this.textDE = textDE;
    }
    
    //@Access(AccessType.PROPERTY)
    public String getTextEN() {
        return textEN;
    }

    public void setTextEN(String textEN) {
        this.textEN = textEN;
    }
    
   // @Access(AccessType.PROPERTY)
    public Boolean getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(Boolean correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
    
   // @Access(AccessType.PROPERTY)
    public Boolean isWrong() {
    	return !correctAnswer;
    }
    
   // @Access(AccessType.PROPERTY)
    public Boolean isRight() {
    	return correctAnswer;
    }
}
