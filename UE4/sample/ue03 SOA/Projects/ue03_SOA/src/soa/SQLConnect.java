package soa;

import java.sql.*;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

/**
 * Name: SQLConnect
 * @author Paul Pfeiffer-Vogl
 * Version: 1.0
 * Beschreibung: In der Klasse SQLConnect wird die SQL Verbindung verwalten und man kann SQL Abfragen absetzt.
 * 	-> unterstützte Abfragen: SELECT, INSERT, UPDATE, DELETE
 */
public class SQLConnect {
    
    private String host;        // Host Adresse wird gespeichert
    private String db;          // Datenbankname wird gespeichert
    private String username;
    private String password;
    private String table;
    private int port;

    private String[] columnName; // Spaltennamen werden gespeichert
    private int colPos; // Position in der Spalten wird gespeichert
    // Benötigte Ressourcen für die Verbindung und Benutzung der Datenbankverbindungen
    private MysqlDataSource ds;
    private Connection con;
    private Statement st;
    private ResultSet rs;
    private DatabaseMetaData md;
    private ReadMysqlData mysqlConnection;

    /**
     * Der Konstruktor der Klasse SQLConnect. In ihm werden die Übergebenen Attribute für die KLasse lokal definiert
     *
     * @param host Host Adresse
     * @param db Datenbankname
     * @param username Benutzername
     * @param password Passwort
     */
    public SQLConnect() {
        mysqlConnection = new ReadMysqlData();

        this.host = mysqlConnection.getHost();
        this.username = mysqlConnection.getUser();
        this.password = mysqlConnection.getPassword();
        this.db = mysqlConnection.getDatabase();
        this.table = mysqlConnection.getTable();
        this.port = mysqlConnection.getPort();
        this.colPos = 0;
    }

    /**
     * In der Methode connect() wird die Verbindung mit dem Datenbankserver hergestellt.
     *
     * @return true wenn erfolgreich sonst false
     * @throws SQLException Fehler bei Verbindungsaufbau
     */
    public boolean connect() {
        try {
            // Datenquelle erzeugen und konfigurieren
            this.ds = new MysqlDataSource();
            ds.setServerName(mysqlConnection.getHost());
            ds.setUser(mysqlConnection.getUser());
            ds.setPassword(mysqlConnection.getPassword());
            // Wenn ein Port angegeben wurde, dann wird dieser gesetzt
            if (mysqlConnection.getPort() != -1) {
                ds.setPort(mysqlConnection.getPort());
            }
            // Verbindung herstellen
            con = ds.getConnection();
            md = con.getMetaData();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Dieser Methode wird der Spaltenname übergeben diese speichert diesen dann Lokal in einem String array
     *
     * @param columnName
     */
    public void defineRowNames(String columnName) {
        this.columnName[colPos] = columnName;
        colPos++;
    }

    /**
     * Getter-Methode für die Spaltennamen
     *
     * @return String-Array für die Spalten
     */
    public String[] getColumnName() {
        return this.columnName;
    }

    /**
     *
     * @param query Die Anfrage für INSERT/UPDATE/DELETE
     * @param db Die Datenbank
     * @return Wie viele Datensätze veränder/hinzugefügt/gelöscht wurden
     * @throws SQLException Fehler bei Verbindungsaufbau
     */
    public int queryUpdate(String query, String db) {
        int x = 0;
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("use " + db);
            x = st.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return x;
    }

    /**
     *
     * @param query query für die Anfrage für SELECT
     * @param db Die Datenbank
     * @return Object-Array der Datensätze
     * @throws SQLException
     */
    public Object[][] query(String query, String db) {
        Object[][] o = null;
        try {
            // Abfrage vorbereiten und ausführen
            st = con.createStatement();
            rs = st.executeQuery("use " + db);
            ResultSetMetaData rsmd;
            // Query wird ausgeführt
            rs = st.executeQuery(query);
            rsmd = rs.getMetaData();
            // Zeiger wird auf das letzte Element gesetzt um die Anzahl der Zeile zu bekommen
            rs.last();
            int row = rs.getRow();
            // Hier wird die Anzahl der Zeilen und Spalten benötigt um das Object Array zu definiert
            o = new Object[row][rsmd.getColumnCount()];
            // columnName wird mit den Anzahl der Spalten definiert
            columnName = new String[rsmd.getColumnCount()];
            // Der Crouser wird wieder auf ein Element vor dem ersten gesetzt
            rs.beforeFirst();

            // Ergebnisse verarbeiten
            for (int i = 0; rs.next(); i++) { // Cursor bewegen
                for (int j = 0; j < rsmd.getColumnCount(); j++) {
                    // immer Beim der ersten Reihe wir das Element in das Array für die columnNames übernommen
                    if (i == 0) {
                        defineRowNames(rsmd.getColumnLabel(j + 1));
                    }
                    // Werte werden in das Array gespeichert
                    o[i][j] = rs.getObject(j + 1);
                }
            }
            this.colPos = 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return o;
    }
    
   /**
     * Will return an entry of format blob.
     *
     * Cited from: http://www.java2s.com/Code/Java/Database-SQL-JDBC/GetBLOBdatafromresultset.htm
     * Accessed on: 24-Nov-2012
     */
    public byte[] getBLOB(String query, String col, int id, String db) {
        byte[] blobResult = null;
        try {
            query = query + " WHERE id = " + id;
            st = con.createStatement();
            rs = st.executeQuery("use " + db);
            rs = st.executeQuery(query);
            try {
                rs.next();
                Blob blob = rs.getBlob(col);
                // materialize BLOB onto client
                blobResult = blob.getBytes(1, (int) blob.length());
            } finally {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return blobResult;
    }

    /**
     * close the streams
     * @throws SQLException
     */
    public void close() {
        try {
            this.rs.close();
            st.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getDb() {
        return db;
    }

    public String getTable() {
        return table;
    }
}
