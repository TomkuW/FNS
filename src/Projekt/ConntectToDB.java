package Projekt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Tomek on 2017-11-14.
 */
public class ConntectToDB {

    public static Connection Connector(){

        try {

            Class.forName("org.mariadb.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/fns_db", "root", "bazasql");
            Statement stmt = conn.createStatement();
            return conn;
        }catch (ClassNotFoundException e) {
            System.err.println("Brak sterownika JDBC");
            e.printStackTrace();
            return  null;
        }catch (SQLException e) {
        System.err.println("Problem z otwarciem polaczenia");
        e.printStackTrace();
            return  null;
    }

    }
}
