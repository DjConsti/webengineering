/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package soa;

import java.sql.*;

/**
 * This class will be published on the axis server and represents a service
 *
 * @author Paul Pfeiffer-Vogl(ppfeiffer@student.tgm.ac.at)
 */
public class erstelleEintrag {
    /**
     * This Serviceoperation will create a new article.
     *
     * @param title title of the article
     * @param content content of the article
     * @return true if successful created, else false
     * @throws SQLException if an sql error occurs
     * @throws Exception if an unkown error occurs
     */
    public boolean erstellEintrag(String title, String content) throws SQLException, Exception {
        // create object for database connection
        SQLConnect sqlcon = new SQLConnect();
        // connect to database
        sqlcon.connect();
        // insert a new object 
        if (sqlcon.queryUpdate("INSERT INTO "+sqlcon.getTable()+" (`title`, `text`) VALUES('" + title + "', '" + content + "')", sqlcon.getDb()) == 1) {
            return true;
        }

        return false;
    }
}
