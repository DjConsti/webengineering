package at.ac.tuwien.big.we.dbpedia.api;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Set;
import java.util.TimeZone;
import java.util.TreeSet;

import com.hp.hpl.jena.datatypes.RDFDatatype;
import com.hp.hpl.jena.datatypes.xsd.XSDDatatype;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.XSD;

/***
 * A SelectQueryBuilder provides an easy way to create a SPARQL SELECT query.
 * This builder supports the definition of the following clauses:
 * WHERE, MINUS, FILTER, EXISTS, and NOTEXISTS. Furthermore the number of
 * retrieved statements (LIMIT) and the position from which statements are
 * collected (OFFSET) can be set as well as whether the statements must be
 * unique (DISTINCT) or not.
 * 
 * @author Martin Fleck
 *
 */
public class SelectQueryBuilder {
	
	private static final int FAIR_USE_LIMIT = 2000; // due to fair use
	
	private static SimpleDateFormat dateFormat;
	
	/**
	 * Flags used for matching text in the FILTER clauses.
	 */
	public enum MatchCase {
		/**
		 * The matching will be case insensitive, i.e., 'A' == 'a'.
		 */
		CASE_INSENSITIVE("i"),
		/**
		 * The matching will be case sensitive, i.e., 'A' != 'a'.
		 */
		CASE_SENSITIVE("");
		
		private String txt;
		
		private MatchCase(String txt) {
			this.txt = txt;
		}
		
		@Override
		public String toString() {
			return txt;
		}
	}
	
	/**
	 * Flags used for simple matching pattern in FILTER clauses.
	 */
	public enum MatchWhere {
		/**
		 * The phrase must match the beginning of the String.
		 */
		BEGINNING("^"),
		/**
		 * The phrase must match the end of the String.
		 */
		END("$"),
		/**
		 * The phrase must be contained anywhere within String.
		 */
		ANYWHERE("");
		
		private String txt;
		
		private MatchWhere(String txt) {
			this.txt = txt;
		}
		
		@Override
		public String toString() {
			return txt;
		}
	}
	
	public enum MatchOperation {
		EQUAL("="),
		UNEQUAL("!="),
		LESS("<"),
		LESS_OR_EQUAL("<="),
		GREATER(">"),
		GREATER_OR_EQUAL(">=");
		
		private String txt;
		
		private MatchOperation(String txt) {
			this.txt = txt;
		}
		
		@Override
		public String toString() {
			return txt;
		}
	}
	
	private static String RESOURCE_TEMPLATE = "?" + DBPediaService.VARIABLE_SUBJECT + " <%s> <%s> . ";
	private static String STRING_TEMPLATE = "?" + DBPediaService.VARIABLE_SUBJECT + " <%s> \"%s\" . ";
	private static String STRING_LANG_TEMPLATE = "?" + DBPediaService.VARIABLE_SUBJECT + " <%s> \"%s\"@%s . ";
	private static String LITERAL_TEMPLATE = "?" + DBPediaService.VARIABLE_SUBJECT + " <%s> \"%s\"^^<%s> . ";
	private static String OBJECT_VARIABLE_TEMPLATE = "?" + DBPediaService.VARIABLE_SUBJECT + " <%s> ?%s . ";
	
	private static String TEXT_FILTER_TEMPLATE = "FILTER regex(?%s, '%s', '%s') . "; // variable, pattern, flags
	private static String NUMBER_FILTER_TEMPLATE = "FILTER (?%s %s %s) . ";
	private static String DATE_FILTER_TEMPLATE = "FILTER (?%s %s <" + XSD.getURI() + "dateTime>('%s')) . ";
	private static String LANG_FILTER_TEMPLATE = "FILTER langMatches( lang(?%s), '%s') . ";
	
	private static String EXISTS_TEMPLATE = "FILTER EXISTS { ?" + DBPediaService.VARIABLE_SUBJECT + " <%s> ?%s } . ";
	private static String NOT_EXISTS_TEMPLATE = "FILTER NOT EXISTS { ?" + DBPediaService.VARIABLE_SUBJECT + " <%s> ?%s } . ";
	
	private static String MINUS_TEMPLATE = "MINUS { %s } .";
	
	private static String QUERY_TEMPLATE = "SELECT%s ?" + DBPediaService.VARIABLE_SUBJECT + " WHERE { %s } LIMIT %s OFFSET %s";
	
	private Set<String> whereClauses = new TreeSet<String>();
	private Set<String> minusClauses = new TreeSet<String>();
	private Set<String> filterClauses = new TreeSet<String>();
	private int limit = 10;
	private int offset = 0;
	private boolean distinct = true;
	
	private int variableCounter = 0;
	
	SelectQueryBuilder() {
		dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
	}
	
	SelectQueryBuilder(SelectQueryBuilder queryBuilder) {
		this.whereClauses.addAll(queryBuilder.whereClauses);
		this.whereClauses.addAll(queryBuilder.minusClauses);
		this.whereClauses.addAll(queryBuilder.filterClauses);
		this.limit = queryBuilder.limit;
		this.offset = queryBuilder.offset;
		this.distinct = queryBuilder.distinct;
		this.variableCounter = queryBuilder.variableCounter;
	}
	
	/***
	 * Returns the limit on the number of statements that should be retrieved
	 * by the SELECT query. (Default value: 10)
	 * 
	 * @return Limit of retrieved statements instances
	 */
	public int getLimit() {
		return limit;
	}

	/***
	 * Set the number of statements that should be retrieved by the SELECT query.
	 * (Default value: 10)
	 * 
	 * @param limit Limit of retrieved statement instances
	 * @return The calling object with the set limit.
	 */
	public SelectQueryBuilder setLimit(int limit) {
		if(limit > FAIR_USE_LIMIT)
			System.err.println(
				"Warning: The limit can not be set greater than " + FAIR_USE_LIMIT +
				" due to a fair use policy. Setting of " + limit  + " will " +
				"be ignored.");
		else
			this.limit = limit;
		return this;
	}

	/***
	 * Returns the position from which the statements instances will be 
	 * retrieved.
	 * 
	 * @return Position to start the retrieval of instances from.
	 */
	public int getOffset() {
		return offset;
	}

	/***
	 * Sets the position from which the statement instances will be collected.
	 * 
	 * @param offset Position from which the statement instances will be 
	 * retrieved.
	 * @return  The calling object with the set offset.
	 */
	public SelectQueryBuilder setOffset(int offset) {
		if(offset >= 0)
			this.offset = offset;
		return this;
	}
	
	/***
	 * Sets whether the retrieved statements will be unique or not.
	 * @param distinct
	 * @return The calling object with the set distinct setting.
	 */
	public SelectQueryBuilder setDistinct(boolean distinct) {
		this.distinct = distinct;
		return this;
	}
	
	/***
	 * Returns whether the retrieved statements will be unique or not.
	 * @return true if retrieved statements will be unique, false otherwise.
	 */
	public boolean isDistinct() {
		return distinct;
	}
	
	/**
	 * Create a new unique temporary variable name.
	 * @return unique variable name.
	 */
	private String createNewVariableName() {
		return "var" + variableCounter++;
	}
	
	/**
	 * Manage block collections (WHERE, MINUS, FILTER)
	 */

	private SelectQueryBuilder addToWhereSet(String template, Object... args) {
		whereClauses.add(String.format(template, args));
		return this;
	}
	
	private SelectQueryBuilder removeFromWhereSet(String template, Object... args) {
		whereClauses.remove(String.format(template, args));
		return this;
	}
	
	private SelectQueryBuilder addToFilterSet(String template, Object... args) {
		filterClauses.add(String.format(template, args));
		return this;
	}
	
	private SelectQueryBuilder addToMinusSet(String template, Object... args) {
		minusClauses.add(String.format(template, args));
		return this;
	}
	
	private SelectQueryBuilder removeFromMinusSet(String template, Object... args) {
		minusClauses.remove(String.format(template, args));
		return this;
	}
	
	/**
	 * WHERE Clauses - Add
	 */
	
	/**
	 * Only retrieve those statements which have a predicate matching the given
	 * predicate and which have as value for this predicate the given object.
	 * 
	 * Resulting clause: ?subject {@literal <}predicate{@literal >} 
	 * {@literal <}object{@literal >}
	 * 
	 * @param predicate Predicate to be matched against
	 * @param object Value for the predicate
	 * @return The SelectQueryBuilder object with the additional clause.
	 */
	public SelectQueryBuilder addWhereClause(Resource predicate, Resource object) {
		return addToWhereSet(RESOURCE_TEMPLATE, predicate.getURI(), object);
	}

	/**
	 * Only retrieve those statements which have a predicate matching the given
	 * predicate and which have as value for this predicate the given string.
	 * 
	 * Resulting clause: ?subject {@literal <}predicate{@literal >} "object"
	 * 
	 * @param predicate Predicate to be matched against
	 * @param object Value for the predicate
	 * @return The SelectQueryBuilder object with the additional clause.
	 */
	public SelectQueryBuilder addWhereClause(Resource predicate, String object) {
		return addToWhereSet(STRING_TEMPLATE, predicate.getURI(), object);
	}

	/**
	 * Only retrieve those statements which have a predicate matching the given
	 * predicate and which have as value for this predicate the given string in
	 * the specified language.
	 * 
	 * Resulting clause: ?subject {@literal <}predicate{@literal >} 
	 * "object"{@literal @}language
	 * 
	 * @param predicate Predicate to be matched against
	 * @param object Value for the predicate
	 * @param language Language the string value must be specified in
	 * @return The SelectQueryBuilder object with the additional clause.
	 */
	public SelectQueryBuilder addWhereClause(Resource predicate, String object, String language) {
		return addToWhereSet(STRING_LANG_TEMPLATE, predicate.getURI(), object, language);
	}
	
	/**
	 * Only retrieve those statements which have a predicate matching the given
	 * predicate and which have as value for this predicate the given string in
	 * the specified language.
	 * 
	 * Resulting clause: ?subject {@literal <}predicate{@literal >} 
	 * "object"{@literal @}language
	 * 
	 * @param predicate Predicate to be matched against
	 * @param object Value for the predicate
	 * @param language Locale containing the language the string value must be
	 * specified in
	 * @return The SelectQueryBuilder object with the additional clause.
	 */
	public SelectQueryBuilder addWhereClause(Resource predicate, String object, Locale language) {
		return addToWhereSet(STRING_LANG_TEMPLATE, predicate.getURI(), object, language.getLanguage());
	}
	
	/**
	 * Only retrieve those statements which have a predicate matching the given
	 * predicate and which have as value for this predicate the given literal, 
	 * which is an instance of the datatype given by dataTypeUri.
	 * For predefined datatypes, please use the respective methods of this 
	 * builder.
	 * 
	 * Resulting clause: ?subject {@literal <}predicate{@literal >} 
	 * "object"^^{@literal <}dataTypeUri{@literal >}
	 * 
	 * @param predicate Predicate to be matched against
	 * @param object Value for the predicate
	 * @param dataTypeUri The URI of the datatype of the object, e.g., 
	 * http://www.w3.org/2001/XMLSchema#integer
	 * @return The SelectQueryBuilder object with the additional clause.
	 */
	public SelectQueryBuilder addWhereClause(Resource predicate, Object object, String dataTypeUri) {
		return addToWhereSet(LITERAL_TEMPLATE, predicate.getURI(), object, dataTypeUri);
	}
	
	/**
	 * Only retrieve those statements which have a predicate matching the given
	 * predicate and which have as value for this predicate the given literal, 
	 * which is an instance of the datatype given by dataTypeUri.
	 * For predefined datatypes, please use the respective methods of this 
	 * builder.
	 * 
	 * Resulting clause: ?subject {@literal <}predicate{@literal >} 
	 * "object"^^{@literal <}dataTypeUri{@literal >}
	 * 
	 * @param predicate Predicate to be matched against
	 * @param object Value for the predicate
	 * @param type The RDFDatatype of the object
	 * @return The SelectQueryBuilder object with the additional clause.
	 */
	public SelectQueryBuilder addWhereClause(Resource predicate, Object object, RDFDatatype type) {
		return addWhereClause(predicate, object, type.getURI());
	}

	/**
	 * Only retrieve those statements which have a predicate matching the given
	 * predicate and which have as value for this predicate the given literal 
	 * integer.
	 * 
	 * Resulting clause: ?subject {@literal <}predicate{@literal >} 
	 * "object"^^http://www.w3.org/2001/XMLSchema#integer
	 * 
	 * @param predicate Predicate to be matched against
	 * @param object Value for the predicate
	 * @return The SelectQueryBuilder object with the additional clause.
	 */
	public SelectQueryBuilder addWhereClause(Resource predicate, Integer object) {
		return addWhereClause(predicate, object, XSDDatatype.XSDinteger);
	}

	/**
	 * Only retrieve those statements which have a predicate matching the given
	 * predicate and which have as value for this predicate the given literal 
	 * boolean.
	 * 
	 * Resulting clause: ?subject {@literal <}predicate{@literal >} 
	 * "object"^^http://www.w3.org/2001/XMLSchema#boolean
	 * 
	 * @param predicate Predicate to be matched against
	 * @param object Value for the predicate
	 * @return The SelectQueryBuilder object with the additional clause.
	 */
	public SelectQueryBuilder addWhereClause(Resource predicate, Boolean object) {
		return addWhereClause(predicate, object, XSDDatatype.XSDboolean);
	}

	/**
	 * Only retrieve those statements which have a predicate matching the given
	 * predicate and which have as value for this predicate the given literal 
	 * double.
	 * 
	 * Resulting clause: ?subject {@literal <}predicate{@literal >} 
	 * "object"^^http://www.w3.org/2001/XMLSchema#double
	 * 
	 * @param predicate Predicate to be matched against
	 * @param object Value for the predicate
	 * @return The SelectQueryBuilder object with the additional clause.
	 */
	public SelectQueryBuilder addWhereClause(Resource predicate, Double object) {
		return addWhereClause(predicate, object, XSDDatatype.XSDdouble);
	}

	/**
	 * Only retrieve those statements which have a predicate matching the given
	 * predicate and which have as value for this predicate the given literal 
	 * float.
	 * 
	 * Resulting clause: ?subject {@literal <}predicate{@literal >} 
	 * "object"^^http://www.w3.org/2001/XMLSchema#float
	 * 
	 * @param predicate Predicate to be matched against
	 * @param object Value for the predicate
	 * @return The SelectQueryBuilder object with the additional clause.
	 */
	public SelectQueryBuilder addWhereClause(Resource predicate, Float object) {
		return addWhereClause(predicate, object, XSDDatatype.XSDdecimal);
	}

	/**
	 * Only retrieve those statements which have a predicate matching the given
	 * predicate and which have as value for this predicate the given literal 
	 * date.
	 * 
	 * Resulting clause: ?subject {@literal <}predicate{@literal >} 
	 * "object"^^http://www.w3.org/2001/XMLSchema#date
	 * 
	 * @param predicate Predicate to be matched against
	 * @param object Value for the predicate
	 * @return The SelectQueryBuilder object with the additional clause.
	 */
	public SelectQueryBuilder addWhereClause(Resource predicate, Calendar object) {
		return addWhereClause(predicate, object, XSDDatatype.XSDdate);
	}
	
	/**
	 * WHERE Clauses - Remove
	 */
	
	/***
	 * Removes the WHERE clause specified by the respective addWhereClause-
	 * method. If no such clause is found, nothing is removed.
	 * 
	 * @param predicate Predicate to be matched against
	 * @param object Value for the predicate
	 * @return The SelectQueryBuilder object with the removed clause.
	 */
	public SelectQueryBuilder removeWhereClause(Resource predicate, Resource object) {
		return removeFromWhereSet(RESOURCE_TEMPLATE, predicate.getURI(), object);
	}

	/***
	 * Removes the WHERE clause specified by the respective addWhereClause-
	 * method. If no such clause is found, nothing is removed.
	 * 
	 * @param predicate Predicate to be matched against
	 * @param object Value for the predicate
	 * @return The SelectQueryBuilder object with the removed clause.
	 */
	public SelectQueryBuilder removeWhereClause(Resource predicate, String object) {
		return removeFromWhereSet(STRING_TEMPLATE, predicate.getURI(), object);
	}

	/***
	 * Removes the WHERE clause specified by the respective addWhereClause-
	 * method. If no such clause is found, nothing is removed.
	 * 
	 * @param predicate Predicate to be matched against
	 * @param object Value for the predicate
	 * @return The SelectQueryBuilder object with the removed clause.
	 */
	public SelectQueryBuilder removeWhereClause(Resource predicate, String object, String language) {
		return removeFromWhereSet(STRING_LANG_TEMPLATE, predicate.getURI(), object, language);
	}
	
	/***
	 * Removes the WHERE clause specified by the respective addWhereClause-
	 * method. If no such clause is found, nothing is removed.
	 * 
	 * @param predicate Predicate to be matched against
	 * @param object Value for the predicate
	 * @return The SelectQueryBuilder object with the removed clause.
	 */
	public SelectQueryBuilder removeWhereClause(Resource predicate, String object, Locale language) {
		return removeFromWhereSet(STRING_LANG_TEMPLATE, predicate.getURI(), object, language.getLanguage());
	}
	
	/***
	 * Removes the WHERE clause specified by the respective addWhereClause-
	 * method. If no such clause is found, nothing is removed.
	 * 
	 * @param predicate Predicate to be matched against
	 * @param object Value for the predicate
	 * @return The SelectQueryBuilder object with the removed clause.
	 */
	public SelectQueryBuilder removeWhereClause(Resource predicate, Object object, String dataTypeUri) {
		return removeFromWhereSet(LITERAL_TEMPLATE, predicate.getURI(), object, dataTypeUri);
	}
	
	/***
	 * Removes the WHERE clause specified by the respective addWhereClause-
	 * method. If no such clause is found, nothing is removed.
	 * 
	 * @param predicate Predicate to be matched against
	 * @param object Value for the predicate
	 * @return The SelectQueryBuilder object with the removed clause.
	 */
	public SelectQueryBuilder removeWhereClause(Resource predicate, Object object, RDFDatatype type) {
		return removeWhereClause(predicate, object, type.getURI());
	}

	/***
	 * Removes the WHERE clause specified by the respective addWhereClause-
	 * method. If no such clause is found, nothing is removed.
	 * 
	 * @param predicate Predicate to be matched against
	 * @param object Value for the predicate
	 * @return The SelectQueryBuilder object with the removed clause.
	 */
	public SelectQueryBuilder removeWhereClause(Resource predicate, Integer object) {
		return removeWhereClause(predicate, object, XSDDatatype.XSDinteger);
	}

	/***
	 * Removes the WHERE clause specified by the respective addWhereClause-
	 * method. If no such clause is found, nothing is removed.
	 * 
	 * @param predicate Predicate to be matched against
	 * @param object Value for the predicate
	 * @return The SelectQueryBuilder object with the removed clause.
	 */
	public SelectQueryBuilder removeWhereClause(Resource predicate, Boolean object) {
		return removeWhereClause(predicate, object, XSDDatatype.XSDboolean);
	}

	/***
	 * Removes the WHERE clause specified by the respective addWhereClause-
	 * method. If no such clause is found, nothing is removed.
	 * 
	 * @param predicate Predicate to be matched against
	 * @param object Value for the predicate
	 * @return The SelectQueryBuilder object with the removed clause.
	 */
	public SelectQueryBuilder removeWhereClause(Resource predicate, Double object) {
		return removeWhereClause(predicate, object, XSDDatatype.XSDdouble);
	}

	/***
	 * Removes the WHERE clause specified by the respective addWhereClause-
	 * method. If no such clause is found, nothing is removed.
	 * 
	 * @param predicate Predicate to be matched against
	 * @param object Value for the predicate
	 * @return The SelectQueryBuilder object with the removed clause.
	 */
	public SelectQueryBuilder removeWhereClause(Resource predicate, Float object) {
		return removeWhereClause(predicate, object, XSDDatatype.XSDdecimal);
	}

	/***
	 * Removes the WHERE clause specified by the respective addWhereClause-
	 * method. If no such clause is found, nothing is removed.
	 * 
	 * @param predicate Predicate to be matched against
	 * @param object Value for the predicate
	 * @return The SelectQueryBuilder object with the removed clause.
	 */
	public SelectQueryBuilder removeWhereClause(Resource predicate, Calendar object) {
		return removeWhereClause(predicate, object, XSDDatatype.XSDdate);
	}
	
	/**
	 * MINUS Clauses - Add
	 */
	
	/**
	 * Exclude those statements which have a predicate matching the given
	 * predicate and which have as value for this predicate the given object.
	 * 
	 * Resulting clause: MINUS { ?subject {@literal <}predicate{@literal >} 
	 * {@literal <}object{@literal >} }
	 * 
	 * @param predicate Predicate to be matched against
	 * @param object Value for the predicate
	 * @return The SelectQueryBuilder object with the additional clause.
	 */
	public SelectQueryBuilder addMinusClause(Resource predicate, Resource object) {
		return addToMinusSet(MINUS_TEMPLATE,
				String.format(RESOURCE_TEMPLATE, predicate.getURI(), object));
	}

	/**
	 * Exclude those statements which have a predicate matching the given
	 * predicate and which have as value for this predicate the given string.
	 * 
	 * Resulting clause: MINUS { ?subject {@literal <}predicate{@literal >} 
	 * "object" }
	 * 
	 * @param predicate Predicate to be matched against
	 * @param object Value for the predicate
	 * @return The SelectQueryBuilder object with the additional clause.
	 */
	public SelectQueryBuilder addMinusClause(Resource predicate, String object) {
		return addToMinusSet(MINUS_TEMPLATE,
				String.format(STRING_TEMPLATE, predicate.getURI(), object));
	}

	/**
	 * Exclude those statements which have a predicate matching the given
	 * predicate and which have as value for this predicate the given string in
	 * the specified language.
	 * 
	 * Resulting clause: MINUS { ?subject {@literal <}predicate{@literal >} 
	 * "object"{@literal @}language }
	 * 
	 * @param predicate Predicate to be matched against
	 * @param object Value for the predicate
	 * @param language Language the string value must be specified in
	 * @return The SelectQueryBuilder object with the additional clause.
	 */
	public SelectQueryBuilder addMinusClause(Resource predicate, String object, String language) {
		return addToMinusSet(MINUS_TEMPLATE,
				String.format(STRING_LANG_TEMPLATE, predicate.getURI(), object, language));
	}
	
	/**
	 * Exclude those statements which have a predicate matching the given
	 * predicate and which have as value for this predicate the given string in
	 * the specified language.
	 * 
	 * Resulting clause: MINUS { ?subject {@literal <}predicate{@literal >} 
	 * "object"{@literal @}language }
	 * 
	 * @param predicate Predicate to be matched against
	 * @param object Value for the predicate
	 * @param language Locale which contains the language the string value must
	 *  be specified in
	 * @return The SelectQueryBuilder object with the additional clause.
	 */
	public SelectQueryBuilder addMinusClause(Resource predicate, String object, Locale language) {
		return addToMinusSet(MINUS_TEMPLATE,
				String.format(STRING_LANG_TEMPLATE, predicate.getURI(), object, language.getLanguage()));
	}
	
	/**
	 * Exclude those statements which have a predicate matching the given
	 * predicate and which have as value for this predicate the given literal, 
	 * which is an instance of the datatype given by dataTypeUri.
	 * For predefined datatypes, please use the respective methods of this 
	 * builder.
	 * 
	 * Resulting clause: MINUS { ?subject {@literal <}predicate{@literal >} 
	 * "object"^^{@literal <}dataTypeUri{@literal >} }
	 * 
	 * @param predicate Predicate to be matched against
	 * @param object Value for the predicate
	 * @param dataTypeUri The URI of the datatype of the object, e.g., 
	 * http://www.w3.org/2001/XMLSchema#integer
	 * @return The SelectQueryBuilder object with the additional clause.
	 */
	public SelectQueryBuilder addMinusClause(Resource predicate, Object object, String dataTypeUri) {
		return addToMinusSet(MINUS_TEMPLATE,
				String.format(LITERAL_TEMPLATE, predicate.getURI(), object, dataTypeUri));
	}
	
	/**
	 * Exclude those statements which have a predicate matching the given
	 * predicate and which have as value for this predicate the given literal, 
	 * which is an instance of the datatype given by dataTypeUri.
	 * For predefined datatypes, please use the respective methods of this 
	 * builder.
	 * 
	 * Resulting clause: MINUS { ?subject {@literal <}predicate{@literal >} 
	 * "object"^^{@literal <}dataTypeUri{@literal >} }
	 * 
	 * @param predicate Predicate to be matched against
	 * @param object Value for the predicate
	 * @param type The RDFDatatype of the object
	 * @return The SelectQueryBuilder object with the additional clause.
	 */
	public SelectQueryBuilder addMinusClause(Resource predicate, Object object, RDFDatatype type) {
		return addMinusClause(predicate, object, type.getURI());
	}

	/**
	 * Exclude those statements which have a predicate matching the given
	 * predicate and which have as value for this predicate the given literal 
	 * integer.
	 * 
	 * Resulting clause: MINUS { ?subject {@literal <}predicate{@literal >} 
	 * "object"^^http://www.w3.org/2001/XMLSchema#integer }
	 * 
	 * @param predicate Predicate to be matched against
	 * @param object Value for the predicate
	 * @return The SelectQueryBuilder object with the additional clause.
	 */
	public SelectQueryBuilder addMinusClause(Resource predicate, Integer object) {
		return addMinusClause(predicate, object, XSDDatatype.XSDinteger);
	}

	/**
	 * Exclude those statements which have a predicate matching the given
	 * predicate and which have as value for this predicate the given literal 
	 * boolean.
	 * 
	 * Resulting clause: MINUS { ?subject {@literal <}predicate{@literal >} 
	 * "object"^^http://www.w3.org/2001/XMLSchema#boolean }
	 * 
	 * @param predicate Predicate to be matched against
	 * @param object Value for the predicate
	 * @return The SelectQueryBuilder object with the additional clause.
	 */
	public SelectQueryBuilder addMinusClause(Resource predicate, Boolean object) {
		return addMinusClause(predicate, object, XSDDatatype.XSDboolean);
	}

	/**
	 * Exclude those statements which have a predicate matching the given
	 * predicate and which have as value for this predicate the given literal 
	 * double.
	 * 
	 * Resulting clause: MINUS { ?subject {@literal <}predicate{@literal >} 
	 * "object"^^http://www.w3.org/2001/XMLSchema#double }
	 * 
	 * @param predicate Predicate to be matched against
	 * @param object Value for the predicate
	 * @return The SelectQueryBuilder object with the additional clause.
	 */
	public SelectQueryBuilder addMinusClause(Resource predicate, Double object) {
		return addMinusClause(predicate, object, XSDDatatype.XSDdouble);
	}

	/**
	 * Exclude those statements which have a predicate matching the given
	 * predicate and which have as value for this predicate the given literal 
	 * float.
	 * 
	 * Resulting clause: MINUS { ?subject {@literal <}predicate{@literal >} 
	 * "object"^^http://www.w3.org/2001/XMLSchema#float }
	 * 
	 * @param predicate Predicate to be matched against
	 * @param object Value for the predicate
	 * @return The SelectQueryBuilder object with the additional clause.
	 */
	public SelectQueryBuilder addMinusClause(Resource predicate, Float object) {
		return addMinusClause(predicate, object, XSDDatatype.XSDdecimal);
	}

	/**
	 * Exclude those statements which have a predicate matching the given
	 * predicate and which have as value for this predicate the given literal 
	 * date.
	 * 
	 * Resulting clause: MINUS { ?subject {@literal <}predicate{@literal >} 
	 * "object"^^http://www.w3.org/2001/XMLSchema#date }
	 * 
	 * @param predicate Predicate to be matched against
	 * @param object Value for the predicate
	 * @return The SelectQueryBuilder object with the additional clause.
	 */
	public SelectQueryBuilder addMinusClause(Resource predicate, Calendar object) {
		return addMinusClause(predicate, object, XSDDatatype.XSDdate);
	}
	
	/**
	 * MINUS Clauses - Remove
	 */
	
	/***
	 * Removes the MINUS clause specified by the respective addMinusClause-
	 * method. If no such clause is found, nothing is removed.
	 * 
	 * @param predicate Predicate to be matched against
	 * @param object Value for the predicate
	 * @return The SelectQueryBuilder object with the removed clause.
	 */
	public SelectQueryBuilder removeMinusClause(Resource predicate, Resource object) {
		return removeFromMinusSet(MINUS_TEMPLATE,
				String.format(RESOURCE_TEMPLATE, predicate.getURI(), object));
	}

	/***
	 * Removes the MINUS clause specified by the respective addMinusClause-
	 * method. If no such clause is found, nothing is removed.
	 * 
	 * @param predicate Predicate to be matched against
	 * @param object Value for the predicate
	 * @return The SelectQueryBuilder object with the removed clause.
	 */
	public SelectQueryBuilder removeMinusClause(Resource predicate, String object) {
		return removeFromMinusSet(MINUS_TEMPLATE,
				String.format(STRING_TEMPLATE, predicate.getURI(), object));
	}

	/***
	 * Removes the MINUS clause specified by the respective addMinusClause-
	 * method. If no such clause is found, nothing is removed.
	 * 
	 * @param predicate Predicate to be matched against
	 * @param object Value for the predicate
	 * @return The SelectQueryBuilder object with the removed clause.
	 */
	public SelectQueryBuilder removeMinusClause(Resource predicate, String object, String language) {
		return removeFromMinusSet(MINUS_TEMPLATE,
				String.format(STRING_LANG_TEMPLATE, predicate.getURI(), object, language));
	}
	
	/***
	 * Removes the MINUS clause specified by the respective addMinusClause-
	 * method. If no such clause is found, nothing is removed.
	 * 
	 * @param predicate Predicate to be matched against
	 * @param object Value for the predicate
	 * @return The SelectQueryBuilder object with the removed clause.
	 */
	public SelectQueryBuilder removeMinusClause(Resource predicate, String object, Locale language) {
		return removeFromMinusSet(MINUS_TEMPLATE,
				String.format(STRING_LANG_TEMPLATE, predicate.getURI(), object, language.getLanguage()));
	}
	
	/***
	 * Removes the MINUS clause specified by the respective addMinusClause-
	 * method. If no such clause is found, nothing is removed.
	 * 
	 * @param predicate Predicate to be matched against
	 * @param object Value for the predicate
	 * @return The SelectQueryBuilder object with the removed clause.
	 */
	public SelectQueryBuilder removeMinusClause(Resource predicate, Object object, String dataTypeUri) {
		return removeFromMinusSet(MINUS_TEMPLATE,
				String.format(LITERAL_TEMPLATE, predicate.getURI(), object, dataTypeUri));
	}
	
	/***
	 * Removes the MINUS clause specified by the respective addMinusClause-
	 * method. If no such clause is found, nothing is removed.
	 * 
	 * @param predicate Predicate to be matched against
	 * @param object Value for the predicate
	 * @return The SelectQueryBuilder object with the removed clause.
	 */
	public SelectQueryBuilder removeMinusClause(Resource predicate, Object object, RDFDatatype type) {
		return removeMinusClause(predicate, object, type.getURI());
	}

	/***
	 * Removes the MINUS clause specified by the respective addMinusClause-
	 * method. If no such clause is found, nothing is removed.
	 * 
	 * @param predicate Predicate to be matched against
	 * @param object Value for the predicate
	 * @return The SelectQueryBuilder object with the removed clause.
	 */
	public SelectQueryBuilder removeMinusClause(Resource predicate, Integer object) {
		return removeMinusClause(predicate, object, XSDDatatype.XSDinteger);
	}

	/***
	 * Removes the MINUS clause specified by the respective addMinusClause-
	 * method. If no such clause is found, nothing is removed.
	 * 
	 * @param predicate Predicate to be matched against
	 * @param object Value for the predicate
	 * @return The SelectQueryBuilder object with the removed clause.
	 */
	public SelectQueryBuilder removeMinusClause(Resource predicate, Boolean object) {
		return removeMinusClause(predicate, object, XSDDatatype.XSDboolean);
	}

	/***
	 * Removes the MINUS clause specified by the respective addMinusClause-
	 * method. If no such clause is found, nothing is removed.
	 * 
	 * @param predicate Predicate to be matched against
	 * @param object Value for the predicate
	 * @return The SelectQueryBuilder object with the removed clause.
	 */
	public SelectQueryBuilder removeMinusClause(Resource predicate, Double object) {
		return removeMinusClause(predicate, object, XSDDatatype.XSDdouble);
	}

	/***
	 * Removes the MINUS clause specified by the respective addMinusClause-
	 * method. If no such clause is found, nothing is removed.
	 * 
	 * @param predicate Predicate to be matched against
	 * @param object Value for the predicate
	 * @return The SelectQueryBuilder object with the removed clause.
	 */
	public SelectQueryBuilder removeMinusClause(Resource predicate, Float object) {
		return removeMinusClause(predicate, object, XSDDatatype.XSDdecimal);
	}

	/***
	 * Removes the MINUS clause specified by the respective addMinusClause-
	 * method. If no such clause is found, nothing is removed.
	 * 
	 * @param predicate Predicate to be matched against
	 * @param object Value for the predicate
	 * @return The SelectQueryBuilder object with the removed clause.
	 */
	public SelectQueryBuilder removeMinusClause(Resource predicate, Calendar object) {
		return removeMinusClause(predicate, object, XSDDatatype.XSDdate);
	}
	
	/**
	 * FILTER Clauses - Add
	 */
	
	/***
	 * Retrieve only statements that have a value for the specified predicate 
	 * that matches the given language. This will first retrieve all statements
	 * with the specified predicate via a WHERE clause and then filter the
	 * retrieved statements based on the predicate values and the language.
	 * 
	 * @param predicate Predicate which the statement must have a value for.
	 * @param language Locale which contains the language name.
	 * @return The SelectQueryBuilder object with the added clause.
	 */
	public SelectQueryBuilder addFilterClause(Resource predicate, Locale language) {
		String variableName = createNewVariableName();
		addToWhereSet(OBJECT_VARIABLE_TEMPLATE, predicate.getURI(), variableName);
		addToFilterSet(LANG_FILTER_TEMPLATE, variableName, language.getLanguage());
		return this;
	}
	
	/***
	 * Retrieve only statements that have a value for the specified predicate 
	 * that matches the given pattern. This will first retrieve all statements
	 * with the specified predicate via a WHERE clause and then filter the
	 * retrieved statements based on the predicate values and the pattern.
	 * 
	 * @param predicate Predicate which the statement must have a value for.
	 * @param pattern Pattern the predicate value must match.
	 * @return The SelectQueryBuilder object with the added clause.
	 */
	public SelectQueryBuilder addFilterClause(Resource predicate, String pattern) {
		return addFilterClause(predicate, pattern, MatchCase.CASE_SENSITIVE);
	}
	
	/***
	 * Retrieve only statements that have a value for the specified predicate 
	 * that matches the given pattern. This will first retrieve all statements
	 * with the specified predicate via a WHERE clause and then filter the
	 * retrieved statements based on the predicate values and the pattern.
	 * 
	 * @param predicate Predicate which the statement must have a value for.
	 * @param pattern Pattern the predicate value must match.
	 * @param matchCase Specification whether the pattern matches case 
	 * sensitive or not.
	 * @return The SelectQueryBuilder object with the added clause.
	 */
	public SelectQueryBuilder addFilterClause(Resource predicate, String pattern, MatchCase matchCase) {
		String variableName = createNewVariableName();
		addToWhereSet(OBJECT_VARIABLE_TEMPLATE, predicate.getURI(), variableName);
		addToFilterSet(TEXT_FILTER_TEMPLATE, variableName, pattern, matchCase);
		return this;
	}
	
	/***
	 * Retrieve only statements that have a value for the specified predicate 
	 * that matches the given pattern. This will first retrieve all statements
	 * with the specified predicate via a WHERE clause and then filter the
	 * retrieved statements based on the predicate values and the pattern.
	 * 
	 * @param predicate Predicate which the statement must have a value for.
	 * @param pattern Pattern the predicate value must match.
	 * @param matchWhere Specification whether the pattern matches at the 
	 * beginning, end or anywhere in the value.
	 * @return The SelectQueryBuilder object with the added clause.
	 */
	public SelectQueryBuilder addFilterClause(Resource predicate, String text, MatchWhere matchWhere) {
		return addFilterClause(predicate, text, matchWhere, MatchCase.CASE_SENSITIVE);
	}
	
	/***
	 * Retrieve only statements that have a value for the specified predicate 
	 * that matches the given pattern. This will first retrieve all statements
	 * with the specified predicate via a WHERE clause and then filter the
	 * retrieved statements based on the predicate values and the pattern.
	 * 
	 * @param predicate Predicate which the statement must have a value for.
	 * @param pattern Pattern the predicate value must match.
	 * @param matchWhere Specification whether the pattern matches at the 
	 * beginning, end or anywhere in the value.
	 * @param matchCase Specification whether the pattern matches case 
	 * sensitive or not.
	 * @return The SelectQueryBuilder object with the added clause.
	 */
	public SelectQueryBuilder addFilterClause(Resource predicate, String text, MatchWhere matchWhere, MatchCase matchCase) {
		String pattern = text;
		switch(matchWhere) {
		case BEGINNING:
			pattern = MatchWhere.BEGINNING + text;
			break;
		case END:
			pattern = text + MatchWhere.END;
			break;
		default:
			break;
		}
		addFilterClause(predicate, pattern, matchCase);
		return this;
	}
	
	/***
	 * Retrieve only statements that have a value for the specified predicate 
	 * that matches the given value pattern. This will first retrieve all 
	 * statements with the specified predicate via a WHERE clause and then 
	 * filter the retrieved statements based on the predicate values and the 
	 * value and operation.
	 * 
	 * @param predicate Predicate which the statement must have a value for.
	 * @param value Value the predicate value must match.
	 * @param operation Specification whether the retrieved value must be 
	 * smaller, greater, ... than the specified value.
	 * @return The SelectQueryBuilder object with the added clause.
	 */
	public SelectQueryBuilder addFilterClause(Resource predicate, Integer value, MatchOperation operation) {
		String variableName = createNewVariableName();
		addToWhereSet(OBJECT_VARIABLE_TEMPLATE, predicate.getURI(), variableName);
		addToFilterSet(NUMBER_FILTER_TEMPLATE, variableName, operation, value);
		return this;
	}
	
	/***
	 * Retrieve only statements that have a value for the specified predicate 
	 * that matches the given value pattern. This will first retrieve all 
	 * statements with the specified predicate via a WHERE clause and then 
	 * filter the retrieved statements based on the predicate values and the 
	 * value and operation.
	 * 
	 * @param predicate Predicate which the statement must have a value for.
	 * @param value Value the predicate value must match.
	 * @param operation Specification whether the retrieved value must be 
	 * smaller, greater, ... than the specified value.
	 * @return The SelectQueryBuilder object with the added clause.
	 */
	public SelectQueryBuilder addFilterClause(Resource predicate, Double value, MatchOperation operation) {
		String variableName = createNewVariableName();
		addToWhereSet(OBJECT_VARIABLE_TEMPLATE, predicate.getURI(), variableName);
		addToFilterSet(NUMBER_FILTER_TEMPLATE, variableName, operation, value);
		return this;
	}
	
	/***
	 * Retrieve only statements that have a value for the specified predicate 
	 * that matches the given value pattern. This will first retrieve all 
	 * statements with the specified predicate via a WHERE clause and then 
	 * filter the retrieved statements based on the predicate values and the 
	 * value and operation.
	 * 
	 * @param predicate Predicate which the statement must have a value for.
	 * @param value Value the predicate value must match.
	 * @param operation Specification whether the retrieved value must be 
	 * smaller, greater, ... than the specified value.
	 * @return The SelectQueryBuilder object with the added clause.
	 */
	public SelectQueryBuilder addFilterClause(Resource predicate, Float value, MatchOperation operation) {
		String variableName = createNewVariableName();
		addToWhereSet(OBJECT_VARIABLE_TEMPLATE, predicate.getURI(), variableName);
		addToFilterSet(NUMBER_FILTER_TEMPLATE, variableName, operation, value);
		return this;
	}
	
	/***
	 * Retrieve only statements that have a value for the specified predicate 
	 * that matches the given value pattern. This will first retrieve all 
	 * statements with the specified predicate via a WHERE clause and then 
	 * filter the retrieved statements based on the predicate values and the 
	 * value and operation.
	 * 
	 * @param predicate Predicate which the statement must have a value for.
	 * @param value Value the predicate value must match.
	 * @param operation Specification whether the retrieved value must be 
	 * smaller, greater, ... than the specified value.
	 * @return The SelectQueryBuilder object with the added clause.
	 */
	public SelectQueryBuilder addFilterClause(Resource predicate, Calendar value, MatchOperation operation) {
		String variableName = createNewVariableName();
		addToWhereSet(OBJECT_VARIABLE_TEMPLATE, predicate.getURI(), variableName);
		addToFilterSet(DATE_FILTER_TEMPLATE, variableName, operation, dateFormat.format(value.getTime()));
		return this;
	}

	/**
	 * EXISTS / NOT EXISTS Clauses
	 */
	
	/***
	 * Retrieve only statements where the given predicate is set.
	 * @param predicate Predicate that must exist in the statement.
	 * @return The SelectQueryBuilder object with the added existence filter.
	 */
	public SelectQueryBuilder addPredicateExistsClause(Resource predicate) {
		String variableName = createNewVariableName();
		addToFilterSet(EXISTS_TEMPLATE, 
				predicate.getURI(), variableName);
		return this;
	}
	
	/***
	 * Retrieve only statements where the given predicate is not set.
	 * @param predicate Predicate that must not exist in the statement.
	 * @return The SelectQueryBuilder object with the added existence filter.
	 */
	public SelectQueryBuilder addPredicateNotExistsClause(Resource predicate) {
		String variableName = createNewVariableName();
		addToFilterSet(NOT_EXISTS_TEMPLATE, 
				predicate.getURI(), variableName);
		return this;
	}
	
	/**
	 * toString Methods
	 */
	
	/***
	 * Returns the SPARQL SELECT query string for the specified options and 
	 * clauses.
	 * @return SPARQL SELECT query string
	 */
	public String toQueryString() {
		String whereBlockString = "";
		for(String whereClause : whereClauses)
			whereBlockString += whereClause;
		for(String filterClause : filterClauses)
			whereBlockString += filterClause;
		for(String minusClause : minusClauses)
			whereBlockString += minusClause;
		
		String distinct = isDistinct() ? " DISTINCT" : "";
		
		return String.format(QUERY_TEMPLATE,
				distinct, whereBlockString, getLimit(), getOffset());
	}
	
	@Override
	public String toString() {
		return toQueryString();
	}
}
