/**
 * <copyright>
 *
 * Copyright (c) 2014 http://www.big.tuwien.ac.at All rights reserved. This
 * program and the accompanying materials are made available under the terms of
 * the Eclipse Public License v1.0 which accompanies this distribution, and is
 * available at http://www.eclipse.org/legal/epl-v10.html
 *
 * </copyright>
 */
package at.ac.tuwien.big.we.dbpedia.example;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import at.ac.tuwien.big.we.dbpedia.api.DBPediaService;
import at.ac.tuwien.big.we.dbpedia.api.DBPediaService.OutputFormat;
import at.ac.tuwien.big.we.dbpedia.api.SelectQueryBuilder;
import at.ac.tuwien.big.we.dbpedia.api.SelectQueryBuilder.MatchOperation;
import at.ac.tuwien.big.we.dbpedia.vocabulary.DBPProp;
import at.ac.tuwien.big.we.dbpedia.vocabulary.DBPedia;
import at.ac.tuwien.big.we.dbpedia.vocabulary.DBPediaOWL;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.sparql.vocabulary.FOAF;
import com.hp.hpl.jena.vocabulary.RDF;
import com.hp.hpl.jena.vocabulary.RDFS;

public class DBPediaExample {
	
	private static final String englishMovieQuestionFormat = 
			"On which of the following movies starring %s did %s act as a director?";
	
	private static final String germanMovieQuestionFormat = 
			"In welchen der folgenden Filme mit %s hat %s Regie geführt?";
	
	private static QuestionBuilder buildQuestion(Locale language, String category, String question, Model rightChoices, Model wrongChoices) {
		return new QuestionBuilder(category, question)
			.setMaximumRightChoicesUsed(4)
			.setMaximumWrongChoicesUsed(4)
			.addRightChoices(DBPediaService.getResourceNames(rightChoices, language))
			.addWrongChoices(DBPediaService.getResourceNames(wrongChoices, language));
	}
	
	public static void actors() {
		// get existing actor and director (locale name must be retrieved manually)
		Resource johnnyDepp = DBPediaService.loadStatements(DBPedia.createResource("Johnny_Depp"));
		Resource timBurton = DBPediaService.loadStatements(DBPedia.createResource("Tim_Burton"));
				
		String englishJohnnyDepp = DBPediaService.getResourceName(johnnyDepp, Locale.ENGLISH);
		System.out.println(englishJohnnyDepp);
				
		// get movies
		SelectQueryBuilder queryBuilder = DBPediaService.createQueryBuilder()
			.setLimit(2000)
			.addWhereClause(RDF.type, DBPediaOWL.Film)
			.addPredicateExistsClause(FOAF.name)
			.addPredicateExistsClause(RDFS.label)
			.addWhereClause(DBPediaOWL.starring, johnnyDepp)
			.addMinusClause(DBPediaOWL.director, timBurton)
			.addFilterClause(RDFS.label, Locale.ENGLISH)
			.addFilterClause(RDFS.label, Locale.GERMAN);
				
		System.out.println(queryBuilder);
				
				
		SelectQueryBuilder unmarriedActors = DBPediaService.createQueryBuilder()
				.setLimit(100)
				.addWhereClause(RDF.type, DBPediaOWL.Actor)
				.addPredicateNotExistsClause(DBPProp.createProperty("spouse"))
				.addPredicateNotExistsClause(DBPediaOWL.spouse);
		
		Calendar year2000 = Calendar.getInstance();
		year2000.set(Calendar.YEAR, 2000);
		year2000.set(Calendar.MONTH, 1);
		year2000.set(Calendar.DAY_OF_YEAR, 1);
			
		SelectQueryBuilder youngActors = DBPediaService.createQueryBuilder()
				.setLimit(100)
				.addWhereClause(RDF.type, DBPediaOWL.Actor)
				.addFilterClause(DBPediaOWL.birthDate, year2000, MatchOperation.GREATER_OR_EQUAL);
		
		System.out.println(youngActors);
		Model actorsWithA = DBPediaService.loadStatements(youngActors.toQueryString());	
		System.out.println(DBPediaService.getResourceNames(actorsWithA));
					
		Model moviesWithoutDirector = DBPediaService.loadStatements(queryBuilder.toQueryString());		
			
		System.out.println(moviesWithoutDirector);
				
		List<String> englishNames = 
			DBPediaService.getResourceNames(moviesWithoutDirector, Locale.JAPANESE.getLanguage());
		List<String> germanNames = 
			DBPediaService.getResourceNames(moviesWithoutDirector, Locale.CHINESE.getLanguage());
			
		System.out.println("english: " + englishNames.size() + ": " +  englishNames);
		System.out.println("german: " + germanNames.size() + ": " +  germanNames);
			
		queryBuilder.removeMinusClause(DBPediaOWL.director, timBurton);
		queryBuilder.addWhereClause(DBPediaOWL.director, timBurton);
				
		Model moviesWithDirector = DBPediaService.loadStatements(queryBuilder.toQueryString());
				
		// create question
		String englishQuestionText = String.format(englishMovieQuestionFormat,
		DBPediaService.getResourceName(johnnyDepp), 
		DBPediaService.getResourceName(timBurton));
		
		QuestionBuilder englishQuestionBuilder = buildQuestion(
						Locale.ENGLISH, 
						"Movies", 
						englishQuestionText, 
						moviesWithDirector, 
						moviesWithoutDirector);
				
				System.out.println("\n" + englishQuestionBuilder.createQuestion());
				
				String germanQuestionText = String.format(germanMovieQuestionFormat,
						DBPediaService.getResourceName(johnnyDepp, Locale.GERMAN), 
						DBPediaService.getResourceName(timBurton, Locale.GERMAN));
				
				
				QuestionBuilder germanQuestionBuilder = buildQuestion(
						Locale.GERMAN, 
						"Filme", 
						germanQuestionText, 
						moviesWithDirector, 
						moviesWithoutDirector);
				
				System.out.println(germanQuestionBuilder.createQuestion());
	}
	
	public static void main(String[] args) {
		// check db-pedia
		System.out.println("DBPediaService Available: " + DBPediaService.isAvailable());
		SelectQueryBuilder populationQuery = DBPediaService.createQueryBuilder()
			    .setLimit(4)  
			   .addWhereClause(RDF.type, DBPediaOWL.Planet)
			   .addWhereClause(DBPediaOWL.meanRadius_PROP, 69911);
		System.out.println(populationQuery.toQueryString());
		
		Model planets = DBPediaService.loadStatements(populationQuery.toQueryString());	
		System.out.println(DBPediaService.writeModel(planets, OutputFormat.N3));
	}
}
