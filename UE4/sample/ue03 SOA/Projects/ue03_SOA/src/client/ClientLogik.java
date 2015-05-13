package client;

import client.erstelleEintrag.ErstelleEintragStub;
import client.sucheEintrag.SucheEintragStub;
import client.exceptionHandling.erstellen.Exception;
import client.exceptionHandling.erstellen.SQLException;
import client.sucheEintrag.SucheEintragStub.Articles;
import java.rmi.RemoteException;
import org.apache.axis2.AxisFault;
/**
 * This class provides a method to create a article, and one to search for
 * them.
 * 
 * @author Harun Koyuncu(hkoyuncu@student.tgm.ac.at)
 * @version 24-Nov-2012
 */

public class ClientLogik {

    /**
     * This method will create a new article.
     *
     * @param title title of the article
     * @param content content of the article
     * @param host the serveradress
     * @param port the serverport
     * @return true if successful, else false
     * @throws AxisFault if an axisfault occurs
     * @throws RemoteException if a remoteexception occurs
     * @throws Exception if an unknow exception occurs
     * @throws SQLException if there is a SQL failure in the service
     */
    public boolean callErstelleEintrag(String title, String content, String host, int port) throws AxisFault, RemoteException, Exception, SQLException {
        // create new stub
        ErstelleEintragStub stub = new ErstelleEintragStub(host, port);
        // create new request object
        ErstelleEintragStub.ErstellEintrag request = new ErstelleEintragStub.ErstellEintrag();
        // set the parameters
        request.setTitle(title);
        request.setContent(content);
        // call the service
        ErstelleEintragStub.ErstellEintragResponse response = stub.erstellEintrag(request);
        // check the response
        boolean status = response.get_return();
        return status;
    }

    /**
     * This method will search for articles.
     *
     * @param searchFor The string that should be searched for ( searching in title and content!)
     * @param host the serveraddress
     * @param port the serverport
     * @return a list of article if any found, else null
     * @throws AxisFault if an axisfault occurs
     * @throws RemoteException if a remoteexception occurs
     * @throws Exception if an unknow exception occurs
     * @throws SQLException if there is a SQL failure in the service
     */
    public Articles[] callSucheEintrag(String searchFor, String host, int port) throws AxisFault, RemoteException, client.exceptionHandling.suchen.Exception, client.exceptionHandling.suchen.Exception, client.exceptionHandling.suchen.SQLException {
        // create new stub
        SucheEintragStub stub = new SucheEintragStub(host, port);
        // create new request object
        SucheEintragStub.SucheEintrag request = new SucheEintragStub.SucheEintrag();
        // set parameters
        request.setSearchFor(searchFor);
        // call the service
        SucheEintragStub.SucheEintragResponse response = stub.sucheEintrag(request);
        // check the response
        SucheEintragStub.Articles[] articles = response.get_return();
        return articles;
    }
}
