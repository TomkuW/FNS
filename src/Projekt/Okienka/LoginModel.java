package Projekt.Okienka;

import Projekt.ConntectToDB;

import java.sql.Connection;
import java.sql.*;

/**
 * Created by Tomek on 2017-11-14.
 */
public class LoginModel {
    Connection connection;

    public LoginModel() {
        connection = ConntectToDB.Connector();
        if (connection == null)
            System.exit(1);
    }

    public boolean isDbConnected() {
        try {
            return !connection.isClosed();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

}
