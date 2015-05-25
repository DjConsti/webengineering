package at.ac.tuwien.big.we.dbpedia.api;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import at.ac.tuwien.big.we.dbpedia.vocabulary.DBPProp;
import at.ac.tuwien.big.we.dbpedia.vocabulary.DBPedia;
import at.ac.tuwien.big.we.dbpedia.vocabulary.DBPediaOWL;
import at.ac.tuwien.big.we.dbpedia.vocabulary.Skos;

import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.ResIterator;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.shared.PrefixMapping;
import com.hp.hpl.jena.shared.impl.PrefixMappingImpl;
import com.hp.hpl.jena.sparql.engine.http.QueryEngineHTTP;
import com.hp.hpl.jena.sparql.vocabulary.FOAF;
import com.hp.hpl.jena.vocabulary.DC;
import com.hp.hpl.jena.vocabulary.DCTerms;
import com.hp.hpl.jena.vocabulary.OWL;
import com.hp.hpl.jena.vocabulary.RDF;
import com.hp.hpl.jena.vocabulary.RDFS;
import com.hp.hpl.jena.vocabulary.XSD;

/***
 * Service class which provides methods for interacting with the DBpedia 
 * using the public SPARQL Endpoint provided by DBpedia 
 * (http://dbpedia.org/sparql). Furthermore this class provides some 
 * utility methods to interact with Models, Statements, and Resources. 
 * Internally Apache Jena is used.
 * 
 * @see <a href="http://dbpedia.org/">http://dbpedia.org/</a>
 * @see <a href="http://jena.apache.org/">http://jena.apache.org/</a>
 * 
 * @author Martin Fleck
 *
 */
public class DBPediaService {
	
	private static final String DEFAULT_LANGUAGE = Locale.ENGLISH.getLanguage();
	
	private static final String SERVICE_URL = "http://dbpedia.org/sparql";
	private static final String ASK_QUERY = "ASK { }";
	
	private static final String SINGLE_RESOURCE_TEMPLATE = 
			"CONSTRUCT { <%s> ?%s ?%s } WHERE { <%1$s> ?%2$s ?%3$s }";
	
	private static final String MULTIPLE_RESOURCES_TEMPLATE =
			"CONSTRUCT { ?%s ?%s ?%s } WHERE { { %s } ?%1$s ?%2$s ?%3$s . } ORDER BY ?%1$s";
	
	
	/**
	 * Output format used to choose the serialization language for the RDF 
	 * graph.
	 */
	public enum OutputFormat {
		RDF_XML("RDF/XML"),
	    RDF_XML_ABBREF("RDF/XML-ABBREV"), 
	    N_TRIPLE("N-TRIPLE"),
	    TURTLE("TURTLE"),
	    TTL("TTL"),
	    N3("N3");
		
		private String text;
		
		private OutputFormat(String text) {
			this.text = text;
		}
		
		@Override
		public String toString() {
			return text;
		}
	}

	private static Model model; // internal RDF model
	private static PrefixMapping prefixes;
	
	static String VARIABLE_SUBJECT = "subject";
	static String VARIABLE_PREDICATE = "predicate";
	static String VARIABLE_OBJECT = "object";
	
	/**
	 * Returns the URL of the remote service which is called when executing
	 * queries.
	 * @return URL of the service
	 */
	public static String getServiceURL() {
		return SERVICE_URL;
	}
	
	private static Model createModel() {
		Model model = ModelFactory.createDefaultModel();
		model.setNsPrefixes(getPrefixMapping());
		return model;
	}
	
	/**
	 * Returns the internal RDF model, which holds all loaded resource statements.
	 * @return Internal RDF model
	 */
	public static Model getModel() {
		if(model == null)
			model =  createModel();
		return model;
	}
	
	/**
	 * Factory methods
	 */
	
	/**
	 * Creates a new {@link SelectQueryBuilder} instance, which can 
	 * be used to create the sparql-Query accepted by other methods of this 
	 * class.
	 * @return Newly created {@link SelectQueryBuilder} instance
	 */
	public static SelectQueryBuilder createQueryBuilder() {
		return new SelectQueryBuilder();
	}
	
	/**
	 * Creates a new {@link SelectQueryBuilder} instance based on 
	 * the provided instance (clone). The new created instance can 
	 * be used to create the sparql-Query accepted by other methods of this 
	 * class.
	 * @return Newly created {@link SelectQueryBuilder} instance
	 */
	public static SelectQueryBuilder createQueryBuilder(SelectQueryBuilder queryBuilder) {
		return new SelectQueryBuilder(queryBuilder);
	}
	
	/**
	 * Utility methods
	 */
	
	/**
	 * Create a QueryExecution that will access a SPARQL service (given by the 
	 * service URL) over HTTP. 
	 * @param queryString Query string to execute 
	 * @return Created QueryExecution
	 */
	private static QueryExecution createQueryExecution(String queryString) {
		QueryExecution service = QueryExecutionFactory.sparqlService(SERVICE_URL, queryString);
		if(service instanceof QueryEngineHTTP) {
			((QueryEngineHTTP) service).setModelContentType("application/rdf+xml");
		}
		return service;
	}	
	
	private static String getName(Resource resource, Property nameProperty, String language) {
		Literal name;
		StmtIterator names = resource.listProperties(nameProperty);
		while(names.hasNext()) {			
			name = names.next().getObject().asLiteral();
			if(name.getLanguage().equals(language))
				return name.getString();
		}
		return null;
	}
	
	/***
	 * Get the names of all resources in the model. For each resource the name
	 * is retrieved by first looking for a FOAF name in English, then we search
	 * for a RDF Schema label, if no name has been found. If also no label is found
	 * the local name of the resource is returned (= URI without the 
	 * namespace).
	 * 
	 * This only searches the model, so all statements must be already loaded.
	 * 
	 * @param model Model for which we want to get the resource names.
	 * @return List of resource names in the specified language.
	 */
	public static List<String> getResourceNames(Model model) {
		return getResourceNames(model, DEFAULT_LANGUAGE);
	}
	
	/***
	 * Get the names of all resources in the model. For each resource the name
	 * is retrieved by first looking for a FOAF name in the specified language,
	 * then a RDF Schema label, if no name has been found. If also no label is 
	 * found the local name of the resource is returned (= URI without the 
	 * namespace).
	 * 
	 * This only searches the model, so all statements must be already loaded.
	 * 
	 * @param model Model for which we want to get the resource names.
	 * @param language The 2 character language code.
	 * @return List of resource names in the specified language.
	 */
	public static List<String> getResourceNames(Model model, String language) {
		if(model == null)
			return new ArrayList<>();
		
		ResIterator resources = model.listSubjects();
		
		List<String> resourceNames = new ArrayList<String>();
		while(resources.hasNext()) {
			try {
			resourceNames.add(
					DBPediaService.getResourceName(resources.next(), language));
			} catch(Exception e) {
				System.err.println("Error: " + e.getMessage());
			}
		}
		return resourceNames;
	}
	
	/***
	 * Get the names of all resources in the model. For each resource the name
	 * is retrieved by first looking for a FOAF name in the specified language,
	 * then a RDF Schema label, if no name has been found. If also no label is 
	 * found the local name of the resource is returned (= URI without the 
	 * namespace).
	 * 
	 * This only searches the model, so all statements must be already loaded.
	 * 
	 * @param model Model for which we want to get the resource names.
	 * @param language The Locale containing the 2 character language code.
	 * @return List of resource names in the specified language.
	 */
	public static List<String> getResourceNames(Model model, Locale language) {
		return getResourceNames(model, language.getLanguage());
	}
	
	/**
	 * Retrieves the name of a resources in the following way:
	 * First it looks for the name based on the Friend-of-a-Friend Schema 
	 * (FOAF) for the specified language. If no such name is present, it looks 
	 * for a RDF Schema label in the specified language. If no such label is 
	 * present, the local name of the resource is used (= URI without the 
	 * namespace).
	 * 
	 * This only searches the resource, so all statements must be already 
	 * loaded.
	 * 
	 * @param resource Resource for which the name should be retrieved
	 * @param language The 2 character language code
	 * @return name Name of the resource in the specified language, null if 
	 * resource is null
	 */
	public static String getResourceName(Resource resource, String language) {
		if(resource == null)
			return null;

		String name = getName(resource, FOAF.name, language);
		if(name == null)
			name = getName(resource, RDFS.label, language);
		if(name == null)
			name = resource.getLocalName();
		return name;
	}
	
	/**
	 * Retrieves the name of a resources in the following way:
	 * First it looks for the name based on the Friend-of-a-Friend Schema 
	 * (FOAF) for the specified language. If no such name is present, it looks 
	 * for a RDF Schema label in the specified language. If no such label is 
	 * present, the local name of the resource is used (= URI without the 
	 * namespace).
	 * 
	 * This only searches the resource, so all statements must be already 
	 * loaded.
	 * 
	 * @param resource Resource for which the name should be retrieved
	 * @param language The Locale containing the 2 character language code
	 * @return name Name of the resource in the specified language, null if 
	 * resource is null
	 */
	public static String getResourceName(Resource resource, Locale language) {
		return getResourceName(resource, language.getLanguage());
	}
	
	/**
	 * Retrieves the name of a resources in the following way:
	 * First it looks for the english name based on the Friend-of-a-Friend 
	 * Schema (FOAF). If no such name is present, it looks 
	 * for an english RDF Schema label. If no such label is 
	 * present, the local name of the resource is used (= URI without the 
	 * namespace).
	 * 
	 * This only searches the resource, so all statements must be already 
	 * loaded.
	 * 
	 * @param resource Resource for which the name should be retrieved
	 * @return name Name of the resource in english, null if 
	 * resource is null
	 */
	public static String getResourceName(Resource resource) {
		return getResourceName(resource, DEFAULT_LANGUAGE);
	}
	
	/**
	 * Return an iterator over all the statements in the model that match the
	 * given resource.
	 * @param resource Resource for which statements should be retrieved.
	 * @return Statement Iterator over all statements for the given resource
	 */
	public static StmtIterator listAllStatements(Resource resource) {
		return listAllStatements(getModel(), resource);
	}
	
	/**
	 * Return an iterator over all the statements in the model that match the
	 * given resource.
	 * @param resource Resource for which statements should be retrieved.
	 * @return Statement Iterator over all statements for the given resource
	 */
	public static StmtIterator listAllStatements(Model model, Resource resource) {
		return model.listStatements(resource, null, (RDFNode)null);
	}
	
	/**
	 * Write a serialized represention of a model in a specified output format.
	 * The default value is {@link OutputFormat#RDF_XML} .
	 * @param format Specified {@link OutputFormat}.
	 * @return Model representation in the specified {@link OutputFormat}.
	 */
	public static String writeModel(Model model, OutputFormat format) {
		StringWriter writer = new StringWriter();
		try {
			model.write(writer, format.toString());
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return writer.toString();
	}
	
	/**
	 * Write a serialized representation of a model in the
	 * {@link OutputFormat#RDF_XML} output format.
	 * @return Model representation in the specified {@link OutputFormat}.
	 */
	public static String writeModel(Model model) {
		return writeModel(model, OutputFormat.RDF_XML);
	}
	
	/**
	 * Creates the prefix mapping for known URI namespaces.
	 * @return Complete prefix mapping.
	 */
	private static PrefixMapping getPrefixMapping() {
		if(prefixes == null) {
			prefixes = new PrefixMappingImpl();
			prefixes.setNsPrefix("", "http://dbpedia.org/");
			prefixes.setNsPrefix("dbpedia-owl", DBPediaOWL.NS);
			prefixes.setNsPrefix("dbpedia", DBPedia.NS);
			prefixes.setNsPrefix("dbpprop", DBPProp.NS);
			
			prefixes.setNsPrefix("owl", OWL.NS);
			prefixes.setNsPrefix("xsd", XSD.getURI());
			
			prefixes.setNsPrefix("rdfs", RDFS.getURI());
			prefixes.setNsPrefix("rdf", RDF.getURI());
			
			prefixes.setNsPrefix("foaf", FOAF.NS);
			
			prefixes.setNsPrefix("dc", DC.NS);
			prefixes.setNsPrefix("dcterms", DCTerms.NS);
			
			prefixes.setNsPrefix("skos", Skos.NS);
		}
		return prefixes;
	}
	
	/**
	 * QUERIES
	 */
	
	/**
	 * Return true if the service is available, false otherwise.
	 * @return true if the service is available, false otherwise
	 */
	public static boolean isAvailable() {
		QueryExecution execution = QueryExecutionFactory
				.sparqlService(getServiceURL(), ASK_QUERY);
		try {
			return execution.execAsk();
		} catch(Exception e) {
			e.printStackTrace();
		} finally { 
			execution.close(); 
		}
		return false;
	}	
	
	/**
	 * Loads all resources that match the specified query. These resources
	 * are associated with this model, meaning, changes made to the returned
	 * resources also change this model. Any statements associated with the
	 * matching resources are not retrieved (also see
	 * {@link CompleteResourceLoader#loadCompleteResources()}).
	 * @return list of matching resources
	 */
	public static List<Resource> loadResources(String sparqlQuery) {
		QueryExecution execution = createQueryExecution(sparqlQuery);

		List<Resource> resources = new ArrayList<Resource>();
		try {
			ResultSet resultSet = execution.execSelect();
			
			Resource resource;
			while(resultSet.hasNext()) {
				resource = resultSet.nextSolution().get(VARIABLE_SUBJECT).asResource();
				getModel().createResource(resource.getURI());
				resources.add(resource);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			execution.close();
		}

		return resources;
	}
	
	/**
	 * Loads all statements for the given resource and stores them into the 
	 * model. The statements for a given resource can be retrieved using
	 * {@link DBPediaService#listAllStatements(Resource)}.
	 * @return The updated, specified resource
	 */
	public static Resource loadStatements(Resource resource) {
		if(resource == null)
			return null;
		
		String queryString = String.format(SINGLE_RESOURCE_TEMPLATE, 
				resource.getURI(), VARIABLE_PREDICATE, 
				VARIABLE_OBJECT);
		
		QueryExecution execution = createQueryExecution(queryString);
		
		try {
			execution.execConstruct(getModel());
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			execution.close();
		}
		
		return getModel().getResource(resource.getURI());		
	}
	
	/**
	 * Loads all statements for all resources that match the specified 
	 * query and stores them into the model. All statements retrieved through
	 * this query are returned as a separate model for ease of use.
	 * If the resource statements are not needed, please just create the 
	 * resource through the methods available in the specific classes, e.g.,
	 * {@link FOAF} or {@link DBPedia}.
	 * @return Model containing only the requested statements or null if the 
	 * query failed.
	 */
	public static Model loadStatements(String sparqlQuery) {				
		String queryString = String.format(MULTIPLE_RESOURCES_TEMPLATE, 
				VARIABLE_SUBJECT, VARIABLE_PREDICATE, 
				VARIABLE_OBJECT, sparqlQuery);
		
		QueryExecution execution = createQueryExecution(queryString);
		
		try {
			Model result = createModel();
			execution.execConstruct(result);
			getModel().add(result);
			return result;
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			execution.close();
		}
		return null;
	}
}
