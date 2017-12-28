package Projekt.PodlaczonieDoBazy;

import java.sql.*;

/**
 * Klasa zawierająca metodę połączneniową z bazą danych
 *
 * Created by Tomek on 2017-11-14.
 */
public class ConntectToDB {

    private static Connection con;
    private static ResultSet rs;
    private static Statement st;
    private static String[] currentUser;

    /**
     * Metoda pobierająca aktualne dane o użytkowniku
     * @return Aktualne dane o użytkowniku
     */
    public static String[] getCurrentUser() {
        return currentUser;
    }

    /**
     * Metoda przypisująca aktualne dane o uzytkowniku
     * @param currentUser - Aktualne dane o użytkowniku
     */
    public static void setCurrentUser(String[] currentUser) {
        ConntectToDB.currentUser = currentUser;
    }

    /**
     * Metoda nawiązująca połączenie z bazą danych
     * @return conn
     */
    public static Connection Connector() {

        try {

            Class.forName("org.mariadb.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/fns_db", "root", "bazasql");

            return conn;
        } catch (ClassNotFoundException e) {
            System.err.println("Brak sterownika JDBC");
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            System.err.println("Problem z otwarciem polaczenia");
            e.printStackTrace();
            return null;
        }
    }


}
