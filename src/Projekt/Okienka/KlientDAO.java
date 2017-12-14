package Projekt.Okienka;

import Projekt.ConntectToDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Tomek on 2017-12-13.
 */
public class KlientDAO {

    public static ObservableList<Klient> pokazKlient() throws SQLException, ClassNotFoundException {

        String selectStmt = "SELECT * FROM klienci";

        try {


            Connection conn = ConntectToDB.Connector();
            PreparedStatement preparedStatement = conn.prepareStatement(selectStmt);
            ResultSet rs = preparedStatement.executeQuery(selectStmt);

            //Send ResultSet to the getContractorList method and get Contractor object
            ObservableList<Klient> KlientList = getKlientList(rs);
            System.out.print(rs.next());
            //Return Contractor object
            return KlientList;
        } catch (Exception e) {
            System.out.println("SQL select operation has been failed: " + e);
            //Return exception
            throw e;

        }

    }

    //-----
    public static ObservableList<Klient> wyszukajKlient (String wyrazenie) throws SQLException,
            ClassNotFoundException {
        //Declare a SELECT statement
        String selectStmt = "SELECT DISTINCT * FROM klienci WHERE imie LIKE \"%" + wyrazenie + "%\" OR "
                + "nazwisko =\""+wyrazenie + "\" OR "
                + "PESEL =\""+ wyrazenie+ "\" OR "
                + "miejscowosc LIKE \"%" + wyrazenie +"%\" OR "
                + "ulica LIKE \"%"+ wyrazenie +"%\" OR "
                + "nr_dom LIKE \"%"+ wyrazenie + "%\" OR "
                + "nr_telefon LIKE \"%"+ wyrazenie + "%\" "
                + "GROUP BY imie ";




        //Execute SELECT statement
        try {
            Connection conn = ConntectToDB.Connector();
            PreparedStatement  preparedStatement = conn.prepareStatement(selectStmt);
            ResultSet rs = preparedStatement.executeQuery(selectStmt);

            //Send ResultSet to the getCargoList method and get Cargo object
            ObservableList<Klient> KlientList = getKlientList(rs);

            //Return Cargo object
            return KlientList;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            //Return exception
            throw e;
        }
    }
    //

    /**
     * Method to returning list of object from raw ResultSet
     *
     * @param rs - ResultSet from database
     * @return - List of user objects
     * @throws SQLException           - Throws when occurs problem with SQL query
     * @throws ClassNotFoundException - Throws when occurs problem with using
     *                                another class
     */
    private static ObservableList<Klient> getKlientList(ResultSet rs) throws SQLException, ClassNotFoundException {

        ObservableList<Klient> klientList = FXCollections.observableArrayList();

        while (rs.next()) {
            Klient klient = new Klient();
            klient.setKlient_id(rs.getInt("klient_id"));
            klient.setImie(rs.getString("Imie"));
            klient.setNazwisko(rs.getString("nazwisko"));
            klient.setPESEL(rs.getString("PESEL"));
            klient.setMiejscowosc(rs.getString("miejscowosc"));
            klient.setUlica(rs.getString("ulica"));
            klient.setNr_dom(rs.getString("nr_dom"));
            klient.setNr_telefon(rs.getString("nr_telefon"));
            //Add Cargo to the ObservableList
            klientList.add(klient);
        }
        //return crgList (ObservableList of Contractors)
        return klientList;
    }

    /**
     * Method to update user information in database
     *
     * @throws SQLException           - Throw when occurs problem with SQL query
     * @throws ClassNotFoundException - Throws when occurs problem with using
     *                                another class
     */
    public static void updateKlient( int klient_id, String imie, String nazwisko, String PESEL, String
            miejscowosc, String ulica, String nr_dom, String nr_telefon) throws
            SQLException, ClassNotFoundException {


        String sql="UPDATE klienci set imie=?, nazwisko=?, PESEL=?,  miejscowosc=?,  ulica=?, " +
                "nr_dom=?, nr_telefon=? WHERE klient_id=?";


        try {
            Connection conn = ConntectToDB.Connector();
            PreparedStatement prepStmt = null;
            prepStmt = conn.prepareStatement(sql);
            prepStmt.setString(1, imie);
            prepStmt.setString(2, nazwisko);
            prepStmt.setString(3, PESEL);
            prepStmt.setString(4, miejscowosc);
            prepStmt.setString(5, ulica);
            prepStmt.setString(6, nr_dom);
            prepStmt.setString(7, nr_telefon);
            prepStmt.setInt(8,klient_id);

            prepStmt.executeUpdate();
            prepStmt.close();
            conn.close();

        } catch (SQLException e) {
            System.err.println("Blad przy aktualizacji danych klienta" +e);
            throw e;
        }

    }

    /**
     * Method to delete user from database
     *
     * @param klient_id - ID of user to delete
     * @throws SQLException - Throws when occurs problem with SQL query
     */
    public static void deleteUserWithId(int klient_id) throws SQLException {

        String sql = "DELETE FROM klienci WHERE klient_id = ?";
        Connection conn = ConntectToDB.Connector();
        try {

            PreparedStatement prepStmt = conn.prepareStatement(sql);
            prepStmt.setInt(1, klient_id);
            prepStmt.executeUpdate();

        } catch (SQLException e) {
            System.out.print("Błąd podczas usuwania klienta" + e);
            throw e;
        }

    }

    /**
     * Method to getting data about concrete user
     *
     * @param klient_id - ID of user to getting data
     * @return - ResultSet with information about user
     * @throws SQLException - Throws when occurs problem with SQL query
     */

    public static ResultSet wyswietlDaneKlient(int klient_id) throws SQLException {

        String query = "SELECT * FROM klienci WHERE klient_id= ?";


        Connection conn = ConntectToDB.Connector();
        PreparedStatement prepStmt = conn.prepareStatement(query);
        prepStmt.setInt(1, klient_id);
        prepStmt.executeQuery();

        return prepStmt.executeQuery();


    }
}
