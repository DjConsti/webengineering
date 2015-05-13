/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package soa;

import java.sql.SQLException;

/**
 * This class will search for an article.
 * 
 * @author Paul Pfeiffer-Vogl(ppfeiffer@student.tgm.ac.at)
 */
public class sucheEintrag {
    //private Articles article = new Articles();
    private Articles[] articles;

    /**
     * This serviceoperations enables the ability to search
     * for a specific article.
     * 
     * @param searchFor the string to search for
     * @return array of Article objects if any found, else null
     * @throws SQLException if a sql error occured
     * @throws Exception if any unkown error occured
     */
    public Articles[] sucheEintrag(String searchFor) throws SQLException, Exception {
        // create object for database connection
        SQLConnect sqlcon = new SQLConnect();
        // connect to database
        sqlcon.connect();

        Object[][] data = null;
        // search for the articles
        if (searchFor != null) {
            data = (Object[][]) sqlcon.query("SELECT id, title, text FROM "+sqlcon.getTable()+" "
                    + "WHERE title LIKE '%"
                    + searchFor
                    + "%' OR text LIKE '%"
                    + searchFor + "%'", sqlcon.getDb());
        } else {
            // if no searchfor was given, get all objects
            data = (Object[][]) sqlcon.query("SELECT id, title, text FROM "+sqlcon.getTable(), sqlcon.getDb());
        }
        // create new array of articles
        articles = new Articles[data.length];

        // iterate the response array
        for (int i = 0; i < data.length; i++) {
            // get the current id of the select tupel
            Integer x = (Integer) data[i][0];
            // get the blob data to the corresponding id
            byte[] blobData = sqlcon.getBLOB("SELECT text FROM " + sqlcon.getTable(), "text", x, sqlcon.getDb());
            // create the new articles object
            articles[i] = new Articles((String)data[i][1],new String(blobData));
        }
        // close database connection
        sqlcon.close();
        // return articles array...
        return articles;
    }
}
