package at.ac.tuwien.big.we.dbpedia.vocabulary;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;

public class DBPedia {
	/** <p>The RDF model that holds the vocabulary terms</p> */
	private static Model m_model = ModelFactory.createDefaultModel();
	
	public static final String NS = "http://dbpedia.org/resource/";
    
    public static String getURI() { return NS; }
    
    /** <p>The namespace of the vocabulary as a resource</p> */
    public static final Resource NAMESPACE = m_model.createResource( NS ); 
    
    /**
     * Create a property for the DBPedia Resource Namespace (NS).
     * @param localName part of the uri without the namespace
     * @return created property with an URI consisting of namespace and the 
     * local name
     */
    public static final Property createProperty(String localName) {
    	return m_model.createProperty(NS, localName);
    }
    
    /**
     * Create a resource in the DBPedia Resource Namespace (NS).
     * @param localName part of the uri without the NS
     * @return created resource with an URI consisting of NS and the local name
     */
    public static final Resource createResource(String localName) {
    	return m_model.createResource(NS + localName);
    }
}
