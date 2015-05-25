package at.ac.tuwien.big.we.dbpedia.vocabulary;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;

/**
 * The Raw Infobox Dataset is created using our initial, now three year old infobox parsing approach. 
 * This extractor extracts all properties from all infoboxes and templates within all Wikipedia articles.
 * Extracted information is represented using properties in the http://dbpedia.org/property/ namespace. 
 * The names of the these properties directly reflect the name of the Wikipedia infobox property. 
 * Property names are not cleaned or merged. Property types are not part of a subsumption hierarchy 
 * and there is no consistent ontology for the infobox dataset. 
 * 
 * http://wiki.dbpedia.org/Datasets39?v=z2l#h338-10
 */
public class DBPProp {
	/** <p>The RDF model that holds the vocabulary terms</p> */
    private static Model m_model = ModelFactory.createDefaultModel();
	
	public static final String NS = "http://dbpedia.org/property/";
    
	public static String getURI() {	return NS; }
	
	 /** <p>The namespace of the vocabulary as a resource</p> */
    public static final Resource NAMESPACE = m_model.createResource( NS );    
    
    /**
     * Create a property in the DBPedia Property Namespace (NS).
     * @param localName part of the uri without the NS
     * @return created property with an URI consisting of NS and the local name
     */
    public static final Property createProperty(String localName) {
    	return m_model.createProperty(NS, localName);
    }
    
    /**
     * Create a resource in the DBPedia Property Namespace (NS).
     * @param localName part of the uri without the NS
     * @return created resource with an URI consisting of NS and the local name
     */
    public static final Resource createResource(String localName) {
    	return m_model.createResource(NS + localName);
    }
}
