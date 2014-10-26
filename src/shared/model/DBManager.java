package shared.model;

import java.sql.*;
/**
 * Created by niralittle on 26.10.2014.
 */
public class DBManager {

    final static String derbyProtocol = "jdbc:derby://localhost:1527/";
    final static String dbName = "Library";
    final static String jdbcURL = derbyProtocol + dbName;
    final static String derbyDriver = "org.apache.derby.jdbc.ClientDriver";

    private static volatile Connection conn;

    private static boolean isConnectionEstablished;

    public static Connection getConnection() {
        if (!isConnectionEstablished) {
            establish();
        }
        return conn;
    }

    private static void establish() {
        System.setProperty("jdbc.drivers", derbyDriver);
        try {
            conn = DriverManager.getConnection(jdbcURL, "app", "root");
        } catch (SQLException se) {
            System.out.println("Connection failed: " + se);
        }
    }
}
