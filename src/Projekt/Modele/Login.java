package Projekt.Modele;

import Projekt.PodlaczonieDoBazy.ConntectToDB;

import java.sql.Connection;
import java.sql.*;

/**
 * Klasa zawierająca model logowania
 *
 * Created by Tomek on 2017-11-14.
 */
public class Login {
    Connection connection;

    /**
     * Konstruktor Logowania
     */
    public Login() {
        connection = ConntectToDB.Connector();
        if (connection == null)
            System.exit(1);
    }

    /**
     * Metoda zamykająca połączenie z bazą
     * @return 0 lub 1
     */
    public boolean isDbConnected() {
        try {
            return !connection.isClosed();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

}
