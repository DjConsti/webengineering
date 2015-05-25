package controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import at.ac.tuwien.big.we.dbpedia.api.DBPediaService;
import at.ac.tuwien.big.we.dbpedia.api.SelectQueryBuilder;
import at.ac.tuwien.big.we.dbpedia.vocabulary.DBPedia;
import at.ac.tuwien.big.we.dbpedia.vocabulary.DBPediaOWL;
import at.ac.tuwien.big.we.dbpedia.vocabulary.DBPProp;
import at.ac.tuwien.big.we.dbpedia.vocabulary.Skos;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.sparql.vocabulary.FOAF;
import com.hp.hpl.jena.vocabulary.RDF;
import com.hp.hpl.jena.vocabulary.RDFS;

import models.Category;
import models.JeopardyDAO;
import models.Question;
import models.Answer;

public class DBPediaClient {
	public static void createDBPediaQuestions() {
		
		//Create new category
		Category category = new Category();
		category.setName("Musik", "de");
		category.setName("Music", "en");

		//Add questions
		addMusicQuestion("Welche dieser Lieder sind von den Beatles?",
				"Which of these songs are from The Beatles?", 50,
				"The_Beatles", "Creedence_Clearwater_Revival", 3, category);
		addMusicQuestion("Welche dieser Lieder sind von Eminem?",
				"Which of these songs are from Eminem?", 40,
				"Eminem", "50_Cent", 2, category);
		addMusicQuestion("Welche dieser Lieder sind von den Rolling Stones?",
				"Which of these songs are from The Rolling Stones?", 30,
				"The_Rolling_Stones", "The_Beatles", 3, category);
		addMusicQuestion("Welche dieser Lieder sind von Creedence Clearwater Revival?",
				"Which of these songs are from Creedence Clearwater Revival?", 20,
				"Creedence_Clearwater_Revival", "The_Beatles", 4, category);
		addMusicQuestion("Welche dieser Lieder sind von Daft Punk?",
				"Which of these songs are from Daft Punk?", 10,
				"Daft_Punk", "The_Prodigy", 2, category);
		
		// Persist categories
		JeopardyDAO.INSTANCE.persist(category);
		JeopardyDAO.INSTANCE.persist(category);
		JeopardyDAO.INSTANCE.persist(category);
	}

	private static void addMusicQuestion(String deText, String enText, int value,
			String resourceName, String wrongResourceName, int limit, Category category) {
		// Create question
		Question question = new Question();
		question.setText(deText, "de");
		question.setText(enText, "en");
		question.setValue(value);

		if (!DBPediaService.isAvailable())
			return;

		// Create and execute query
		Resource resource = DBPediaService.loadStatements(DBPedia
				.createResource(resourceName));
		Resource wrongResource = DBPediaService.loadStatements(DBPedia
				.createResource(wrongResourceName));
		String englishResourceName = DBPediaService.getResourceName(resource,
				Locale.ENGLISH);
		String germanResourceName = DBPediaService.getResourceName(resource,
				Locale.GERMAN);
		SelectQueryBuilder itemQuery = DBPediaService.createQueryBuilder()
				.setLimit(limit).addWhereClause(RDF.type, DBPediaOWL.MusicalWork)
				.addPredicateExistsClause(FOAF.name)
				.addWhereClause(DBPediaOWL.artist, resource)
				.addFilterClause(RDFS.label, Locale.GERMAN)
				.addFilterClause(RDFS.label, Locale.ENGLISH);
		Model resourceItems = DBPediaService.loadStatements(itemQuery
				.toQueryString());
		List<String> englishResourceItemNames = DBPediaService
				.getResourceNames(resourceItems, Locale.ENGLISH);
		List<String> germanResourceItemNames = DBPediaService
				.getResourceNames(resourceItems, Locale.GERMAN);

		// Alter and execute query
		itemQuery.removeWhereClause(DBPediaOWL.artist, resource);
		itemQuery.addMinusClause(DBPediaOWL.artist, resource);
		itemQuery.addWhereClause(DBPediaOWL.artist, wrongResource);
		Model noResourceItems = DBPediaService.loadStatements(itemQuery
				.toQueryString());
		List<String> englishNoResourceItemNames = DBPediaService
				.getResourceNames(noResourceItems, Locale.ENGLISH);
		List<String> germanNoResourceItemNames = DBPediaService
				.getResourceNames(noResourceItems, Locale.GERMAN);

		// Add right answers
		List<Answer> answers = new ArrayList<Answer>();
		for (int i = 0; i < englishResourceItemNames.size(); i++) {
			Answer answer = new Answer();
			answer.setText(englishResourceItemNames.get(i), "en");
			answer.setText(germanResourceItemNames.get(i), "de");
			answer.setCorrectAnswer(true);
			JeopardyDAO.INSTANCE.persist(answer);
			question.addRightAnswer(answer);
			answers.add(answer);
		}

		// Add wrong answers
		for (int i = 0; i < englishNoResourceItemNames.size(); i++) {
			Answer answer = new Answer();
			answer.setText(englishNoResourceItemNames.get(i), "en");
			answer.setText(germanNoResourceItemNames.get(i), "de");
			answer.setCorrectAnswer(false);
			JeopardyDAO.INSTANCE.persist(answer);
			question.addWrongAnswer(answer);
			answers.add(answer);
		}

		// Finish
		question.setAnswers(answers);
		JeopardyDAO.INSTANCE.persist(question);
		question.setCategory(category);
		category.addQuestion(question);
	}
}
