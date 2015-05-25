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
		
		//Create categories
		Category category1 = new Category();
		category1.setName("Filme", "de");
		category1.setName("Movies", "en");
		
		Category category2 = new Category();
		category2.setName("Musik", "de");
		category2.setName("Music", "en");
		
		Category category3 = new Category();
		category3.setName("Literatur", "de");
		category3.setName("Literature", "en");

		//Add questions
		addMovieQuestion("Welche dieser Filme sind von Tim Burton?",
				"Which of these movies are from Tim Burton?", 100,
				"Tim_Burton", "Steven_Spielberg", 2, category1);
		
		addMusicQuestion("Welche dieser Lieder sind von den Beatles?",
				"Which of these songs are from The Beatles?", 100,
				"The_Beatles", "The_Rolling_Stones", 2, category2);
		
		addLiteratureQuestion("Welche dieser Bücher stammen von Ernest Hemingway?",
				"Which of these novels are written by Ernest Hemingway?", 100,
				"Ernest_Hemingway", "Mark_Twain", 2, category3);
		
		// Persist categories
		JeopardyDAO.INSTANCE.persist(category1);
		JeopardyDAO.INSTANCE.persist(category2);
		JeopardyDAO.INSTANCE.persist(category3);
	}

	private static void addMovieQuestion(String deText, String enText, int value,
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
				.setLimit(limit).addWhereClause(RDF.type, DBPediaOWL.Film)
				.addPredicateExistsClause(FOAF.name)
				.addWhereClause(DBPediaOWL.director, resource)
				.addFilterClause(RDFS.label, Locale.GERMAN)
				.addFilterClause(RDFS.label, Locale.ENGLISH);
		Model resourceItems = DBPediaService.loadStatements(itemQuery
				.toQueryString());
		List<String> englishResourceItemNames = DBPediaService
				.getResourceNames(resourceItems, Locale.ENGLISH);
		List<String> germanResourceItemNames = DBPediaService
				.getResourceNames(resourceItems, Locale.GERMAN);

		// Alter and execute query
		itemQuery.removeWhereClause(DBPediaOWL.director, resource);
		itemQuery.addMinusClause(DBPediaOWL.director, resource);
		itemQuery.addWhereClause(DBPediaOWL.director, wrongResource);
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
	
	private static void addLiteratureQuestion(String deText, String enText, int value,
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
				.setLimit(limit).addWhereClause(RDF.type, DBPediaOWL.WrittenWork)
				.addPredicateExistsClause(FOAF.name)
				.addWhereClause(DBPediaOWL.author, resource)
				.addFilterClause(RDFS.label, Locale.GERMAN)
				.addFilterClause(RDFS.label, Locale.ENGLISH);
		Model resourceItems = DBPediaService.loadStatements(itemQuery
				.toQueryString());
		List<String> englishResourceItemNames = DBPediaService
				.getResourceNames(resourceItems, Locale.ENGLISH);
		List<String> germanResourceItemNames = DBPediaService
				.getResourceNames(resourceItems, Locale.GERMAN);

		// Alter and execute query
		itemQuery.removeWhereClause(DBPediaOWL.author, resource);
		itemQuery.addMinusClause(DBPediaOWL.author, resource);
		itemQuery.addWhereClause(DBPediaOWL.author, wrongResource);
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
