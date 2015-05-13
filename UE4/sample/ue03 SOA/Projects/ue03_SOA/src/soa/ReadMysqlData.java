/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package soa;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

/**
 * This class reads the connection data to the mysql database
 * from a xml file. If the file doesnt exists the application will
 * use default settings.
 *
 * @author Koyuncu Harun (hkoyuncu@student.tgm.ac.at)
 * @version 2012.12.07
 */
public class ReadMysqlData {

    private String host;
    private String user;
    private String password;
    private String database;
    private String table;
    private int port;

    /**
     * constructor reads the configuration from the xml file
     */
    public ReadMysqlData() {
        readXMLData();
    }

    /**
     * This methods set the mysql connection options
     * to a defined default value
     */
    public void setDefaultConnection() {
        host = "localhost";
        user = "root";
        password = "";
        database = "soa";
        table = "articles";
        port = 3360;
    }

    /**
     * This method returns the calue of a specific xml tag value
     *
     * @param sTag          elementname for search value
     * @param eElement      xml tag value
     * @return              returns the value of the searched xml element
     */
    private String getTagValue(String sTag, Element eElement) {
        NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();
        Node nValue = (Node) nlList.item(0);
        return nValue.getNodeValue();
    }

    /**
     * This method reads a configuration file, which contians
     * the neeeded options to connect to a mysql database.
     */
    public void readXMLData() {
         try {
            File fXmlFile = new File("msqlConnection.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();
            // get root element of the xml file
            String root = doc.getDocumentElement().getNodeName();
            // get child nodes
            NodeList nList = doc.getElementsByTagName(root);
            // iterate thourgh the child nodes and get the value
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    // Getting the values
                    this.host = getTagValue("host", eElement);
                    this.user = getTagValue("user", eElement);
                    this.password = getTagValue("password", eElement);
                    this.database = getTagValue("database", eElement);
                    this.table =  getTagValue("table", eElement);
                    if (Integer.parseInt(getTagValue("port", eElement)) != -1) {
                        this.port = Integer.parseInt(getTagValue("port", eElement));
                    }
                    else {
                        this.port = -1;
                    }
                }
            }
        } catch (Exception e) {
            // setting the default values
            setDefaultConnection();
            e.printStackTrace();
        }
    }

    public String getDatabase() {
        return database;
    }

    public String getHost() {
        return host;
    }

    public String getPassword() {
        return password;
    }

    public int getPort() {
        return port;
    }

    public String getTable() {
        return table;
    }

    public String getUser() {
        return user;
    }
}
