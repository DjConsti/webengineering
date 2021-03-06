package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.persistence.*;
/**
 * Represents a category, which is stored in the DB
 */
@Entity
public class Category extends BaseEntity {
	
	@Column(name = "nameDE")
    private String nameDE;
	@Column(name = "nameEN")
    private String nameEN;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
    private List<Question> questions = new ArrayList<Question>();
    
    /**
     * Add a new question to the category
     * @param question
     */
 //   @Access(AccessType.PROPERTY)
    public void addQuestion(Question question) {
        question.setCategory(this);
        questions.add(question);
    }

    /**
     * Set the name attribute based on the given language. Default to English if no or an invalid language is passed
     * @param name
     * @param lang
     */
    public void setName(String name, String lang) {
        if ("de".equalsIgnoreCase(lang)) {
            this.nameDE = name;
        }
        else {
            this.nameEN = name;
        }
    }

    /**
     * Get the name attribute based on the given language. Default to English if no or an invalid language is passed
     * @param lang
     * @return
     */
    @Access(AccessType.PROPERTY)
    public String getName(String lang) {
        if ("de".equalsIgnoreCase(lang)) {
            return this.nameDE;
        }
        else {
            return this.nameEN;
        }
    }
    
    @Access(AccessType.PROPERTY)
    public String getNameDE() {
        return nameDE;
    }

    public void setNameDE(String nameDE) {
        this.nameDE = nameDE;
    }

    @Access(AccessType.PROPERTY)
    public String getNameEN() {
        return nameEN;
    }

    public void setNameEN(String nameEN) {
        this.nameEN = nameEN;
    }

   // @Access(AccessType.PROPERTY)
    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
    
   // @Access(AccessType.PROPERTY)
    public void sort() {
    	Collections.sort(questions, new Comparator<Question>() {
			@Override
			public int compare(Question questionA, Question questionB) {
				return Integer.compare(questionA.getValue(), questionB.getValue());
			}

		});
    }
}
