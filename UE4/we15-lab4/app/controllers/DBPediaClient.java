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
		Category category1 = new Category();
		category1.setName("Filme", "de");
		category1.setName("Movies", "en");
		Category category2 = new Category();
		category2.setName("Musik", "de");
		category2.setName("Music", "en");
		Category category3 = new Category();
		category2.setName("Literatur", "de");
		category2.setName("Literature", "en");

		// QUESTION 1
		Question question1 = new Question();
		question1.setText("Welche dieser Filme sind von Tim Burton?", "de");
		question1.setText("Which of these movies are from Tim Burton?", "en");
		question1.setValue(100);

		if (!DBPediaService.isAvailable())
			return;
		// Resource Tim Burton is available at
		// http://dbpedia.org/resource/Tim_Burton
		// Load all statements as we need to get the name later
		Resource director = DBPediaService.loadStatements(DBPedia
				.createResource("Tim_Burton"));
		// Resource Johnny Depp is available at
		// http://dbpedia.org/resource/Johnny_Depp
		// Load all statements as we need to get the name later
		Resource actor = DBPediaService.loadStatements(DBPedia
				.createResource("Johnny_Depp"));
		// retrieve english and german names, might be used for question text
		String englishDirectorName = DBPediaService.getResourceName(director,
				Locale.ENGLISH);
		String germanDirectorName = DBPediaService.getResourceName(director,
				Locale.GERMAN);
		String englishActorName = DBPediaService.getResourceName(actor,
				Locale.ENGLISH);
		String germanActorName = DBPediaService.getResourceName(actor,
				Locale.GERMAN);
		// build SPARQL-query
		SelectQueryBuilder movieQuery = DBPediaService
				.createQueryBuilder()
				.setLimit(5)
				// at most five statements
				.addWhereClause(RDF.type, DBPediaOWL.Film)
				.addPredicateExistsClause(FOAF.name)
				.addWhereClause(DBPediaOWL.director, director)
				.addFilterClause(RDFS.label, Locale.GERMAN)
				.addFilterClause(RDFS.label, Locale.ENGLISH);
		// retrieve data from dbpedia
		Model timBurtonMovies = DBPediaService.loadStatements(movieQuery
				.toQueryString());
		// get english and german movie names, e.g., for right choices
		List<String> englishTimBurtonMovieNames = DBPediaService
				.getResourceNames(timBurtonMovies, Locale.ENGLISH);
		List<String> germanTimBurtonMovieNames = DBPediaService
				.getResourceNames(timBurtonMovies, Locale.GERMAN);
		// alter query to get movies without tim burton
		movieQuery.removeWhereClause(DBPediaOWL.director, director);
		movieQuery.addMinusClause(DBPediaOWL.director, director);
		// retrieve data from dbpedia
		Model noTimBurtonMovies = DBPediaService.loadStatements(movieQuery
				.toQueryString());
		// get english and german movie names, e.g., for wrong choices
		List<String> englishNoTimBurtonMovieNames = DBPediaService
				.getResourceNames(noTimBurtonMovies, Locale.ENGLISH);
		List<String> germanNoTimBurtonMovieNames = DBPediaService
				.getResourceNames(noTimBurtonMovies, Locale.GERMAN);

		List<Answer> answers = new ArrayList<Answer>();
		for (int i = 0; i < englishTimBurtonMovieNames.size(); i++) {
			Answer answer = new Answer();
			answer.setText(englishTimBurtonMovieNames.get(i), "en");
			answer.setText(germanTimBurtonMovieNames.get(i), "de");
			answer.setCorrectAnswer(true);
			JeopardyDAO.INSTANCE.persist(answer);
			question1.addRightAnswer(answer);
			answers.add(answer);
		}

		for (int i = 0; i < englishNoTimBurtonMovieNames.size(); i++) {
			Answer answer = new Answer();
			answer.setText(englishNoTimBurtonMovieNames.get(i), "en");
			answer.setText(germanNoTimBurtonMovieNames.get(i), "de");
			answer.setCorrectAnswer(false);
			JeopardyDAO.INSTANCE.persist(answer);
			question1.addWrongAnswer(answer);
			answers.add(answer);
		}

		question1.setAnswers(answers);

		JeopardyDAO.INSTANCE.persist(question1);

		question1.setCategory(category1);
		category1.addQuestion(question1);

		// QUESTION 2
		Question question2 = new Question();
		question2.setText("Welche dieser Lieder sind von Robbie Williams?",
				"de");
		question2.setText("Which of these songs are from Robbie Williams?",
				"en");
		question2.setValue(100);

		if (!DBPediaService.isAvailable())
			return;

		Resource robbieWilliams = DBPediaService.loadStatements(DBPedia
				.createResource("Robbie_Williams"));

		Resource theWho = DBPediaService.loadStatements(DBPedia
				.createResource("The_Who"));

		String englishRobbieWilliams = DBPediaService.getResourceName(
				robbieWilliams, Locale.ENGLISH);
		String germanRobbieWilliams = DBPediaService.getResourceName(
				robbieWilliams, Locale.GERMAN);
		String englishTheWho = DBPediaService.getResourceName(theWho,
				Locale.ENGLISH);
		String germanTheWho = DBPediaService.getResourceName(theWho,
				Locale.GERMAN);

		SelectQueryBuilder query = DBPediaService.createQueryBuilder()
				.setLimit(3).addWhereClause(RDF.type, DBPediaOWL.MusicalWork)
				.addPredicateExistsClause(FOAF.name)
				.addWhereClause(DBPediaOWL.artist, robbieWilliams)
				.addFilterClause(RDFS.label, Locale.GERMAN)
				.addFilterClause(RDFS.label, Locale.ENGLISH);

		Model songs = DBPediaService.loadStatements(query.toQueryString());

		List<String> englishRobbieWilliamsSongs = DBPediaService
				.getResourceNames(songs, Locale.ENGLISH);
		List<String> germanRobbieWilliamsSongs = DBPediaService
				.getResourceNames(songs, Locale.GERMAN);

		query.removeWhereClause(DBPediaOWL.MusicalWork, robbieWilliams);
		query.addMinusClause(DBPediaOWL.MusicalWork, robbieWilliams);

		Model noRobbieWilliamsSongs = DBPediaService.loadStatements(query
				.toQueryString());

		List<String> englishNoRobbieWilliamsSongs = DBPediaService
				.getResourceNames(noRobbieWilliamsSongs, Locale.ENGLISH);
		List<String> germanNoRobbieWilliamsSongs = DBPediaService
				.getResourceNames(noRobbieWilliamsSongs, Locale.GERMAN);

		List<Answer> answers = new ArrayList<Answer>();
		for (int i = 0; i < englishRobbieWilliamsSongs.size(); i++) {
			Answer answer = new Answer();
			answer.setText(englishRobbieWilliamsSongs.get(i), "en");
			answer.setText(germanRobbieWilliamsSongs.get(i), "de");
			answer.setCorrectAnswer(true);
			JeopardyDAO.INSTANCE.persist(answer);
			question2.addRightAnswer(answer);
			answers.add(answer);
		}

		for (int i = 0; i < englishNoRobbieWilliamsSongs.size(); i++) {
			Answer answer = new Answer();
			answer.setText(englishNoRobbieWilliamsSongs.get(i), "en");
			answer.setText(germanNoRobbieWilliamsSongs.get(i), "de");
			answer.setCorrectAnswer(false);
			JeopardyDAO.INSTANCE.persist(answer);
			question2.addWrongAnswer(answer);
			answers.add(answer);
		}

		question2.setAnswers(answers);

		JeopardyDAO.INSTANCE.persist(question1);

		question2.setCategory(category1);
		category2.addQuestion(question1);

		// QUESTION 3
		Question question3 = new Question();
		question3.setText("Welche dieser Bücher stammen von Ernest Hemingway?", "de");
		question3.setText("Which of these novels are written by Ernest Hemingway?", "en");
		question3.setValue(100);

		if (!DBPediaService.isAvailable())
			return;
		Resource hemingway = DBPediaService.loadStatements(DBPedia
				.createResource("Ernest_Hemingway"));
		Resource poe = DBPediaService.loadStatements(DBPedia
				.createResource("Edgar_Allan_Poe"));
		String englishHemingwayName = DBPediaService.getResourceName(hemingway,
				Locale.ENGLISH);
		String germanHemingwayName = DBPediaService.getResourceName(hemingway,
				Locale.GERMAN);
		String englishPoeName = DBPediaService.getResourceName(poe,
				Locale.ENGLISH);
		String germanPoeName = DBPediaService.getResourceName(poe,
				Locale.GERMAN);
		
		SelectQueryBuilder workQuery = DBPediaService
				.createQueryBuilder()
				.setLimit(5)
				.addWhereClause(RDF.type, DBPediaOWL.WrittenWork)
				.addPredicateExistsClause(FOAF.name)
				.addWhereClause(DBPediaOWL.author, hemingway)
				.addFilterClause(RDFS.label, Locale.GERMAN)
				.addFilterClause(RDFS.label, Locale.ENGLISH);
		Model hemingwayWorks = DBPediaService.loadStatements(workQuery
				.toQueryString());
		List<String> englishHemingwayWorksNames = DBPediaService
				.getResourceNames(hemingwayWorks, Locale.ENGLISH);
		List<String> germanHemingwayWorksNames = DBPediaService
				.getResourceNames(hemingwayWorks, Locale.GERMAN);

		workQuery.removeWhereClause(DBPediaOWL.author, hemingway);
		workQuery.addMinusClause(DBPediaOWL.author, hemingway);
		Model noHemingwayWorks = DBPediaService.loadStatements(workQuery
				.toQueryString());
		List<String> englishNoHemingwayWorksNames = DBPediaService
				.getResourceNames(noHemingwayWorks, Locale.ENGLISH);
		List<String> germanNoHemingwayWorksNames = DBPediaService
				.getResourceNames(noHemingwayWorks, Locale.GERMAN);

		List<Answer> answers = new ArrayList<Answer>();
		for (int i = 0; i < englishTimBurtonMovieNames.size(); i++) {
			Answer answer = new Answer();
			answer.setText(englishHemingwayWorksNames.get(i), "en");
			answer.setText(germanHemingwayWorksNames.get(i), "de");
			answer.setCorrectAnswer(true);
			JeopardyDAO.INSTANCE.persist(answer);
			question1.addRightAnswer(answer);
			answers.add(answer);
		}

		for (int i = 0; i < englishNoTimBurtonMovieNames.size(); i++) {
			Answer answer = new Answer();
			answer.setText(englishNoHemingwayWorksNames.get(i), "en");
			answer.setText(germanNoHemingwayWorksNames.get(i), "de");
			answer.setCorrectAnswer(false);
			JeopardyDAO.INSTANCE.persist(answer);
			question1.addWrongAnswer(answer);
			answers.add(answer);
		}

		question1.setAnswers(answers);

		JeopardyDAO.INSTANCE.persist(question1);

		question1.setCategory(category1);
		category1.addQuestion(question1);

		// Add categories
		JeopardyDAO.INSTANCE.persist(category1);
		JeopardyDAO.INSTANCE.persist(category2);
		JeopardyDAO.INSTANCE.persist(category3);
	}
}
