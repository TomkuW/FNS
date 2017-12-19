package Projekt.Okienka;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created by Tomek on 2017-11-17.
 */

public class PracownikDAO {


    public static ObservableList<Pracownik> pokazPracownik() throws SQLException, ClassNotFoundException {

        String selectStmt = "SELECT * FROM pracownicy";

        try {


            Connection conn = ConntectToDB.Connector();
            PreparedStatement preparedStatement = conn.prepareStatement(selectStmt);
            ResultSet rs = preparedStatement.executeQuery(selectStmt);

            //Send ResultSet to the getContractorList method and get Contractor object
            ObservableList<Pracownik> Pracowniklist = getPracownikList(rs);
            System.out.print(rs.next());
            //Return Contractor object
            return Pracowniklist;
        } catch (Exception e) {
            System.out.println("SQL select operation has been failed: " + e);
            //Return exception
            throw e;

        }

    }

    //-----
    public static ObservableList<Pracownik> wyszukajPracownik(String wyrazenie) throws SQLException,
            ClassNotFoundException {
        //Declare a SELECT statement
        String selectStmt = "SELECT * FROM pracownicy WHERE imie LIKE \"%" + wyrazenie + "%\" OR "
                + "nazwisko LIKE\"%" + wyrazenie + "%\" OR "
                + "zawod =\"%" + wyrazenie + "%\" OR "
                + "PESEL =\"" + wyrazenie + "\" OR "
                + "ulica =\"%" + wyrazenie + "%\" OR "
                + "nr_domu =\"" + wyrazenie + "\" OR "
                + "miejscowosc=\"%" + wyrazenie + "%\" OR "
                + "email = \"" + wyrazenie + "\" OR "
                + "nr_telefon =\"" + wyrazenie + "%\" OR "
                + "wynagrodzenie = \"" + wyrazenie + "\"";


        //Execute SELECT statement
        try {
            Connection conn = ConntectToDB.Connector();
            PreparedStatement preparedStatement = conn.prepareStatement(selectStmt);
            ResultSet rs = preparedStatement.executeQuery(selectStmt);

            //Send ResultSet to the getCargoList method and get Cargo object
            ObservableList<Pracownik> PracownikList = getPracownikList(rs);

            //Return Cargo object
            return PracownikList;
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

    private static ObservableList<Pracownik> getPracownikList(ResultSet rs) throws SQLException,
            ClassNotFoundException {

        ObservableList<Pracownik> pracownikList = FXCollections.observableArrayList();

        while (rs.next()) {
            Pracownik pracownik = new Pracownik();
            pracownik.setPracownik_id(rs.getInt("pracownik_id"));
            pracownik.setImie(rs.getString("Imie"));
            pracownik.setNazwisko(rs.getString("nazwisko"));
            pracownik.setZawod(rs.getString("zawod"));
            pracownik.setPESEL(rs.getString("PESEL"));
            pracownik.setUlica(rs.getString("ulica"));
            pracownik.setNr_dom(rs.getString("nr_domu"));
            pracownik.setMiejscowosc(rs.getString("miejscowosc"));
            pracownik.setEmail(rs.getString("email"));
            pracownik.setNr_telefon(rs.getString("nr_telefon"));
            pracownik.setWynagrodzenie(rs.getString("wynagrodzenie"));
            pracownik.setLogin(rs.getString("login"));
            pracownik.setHaslo(rs.getString("haslo"));
            pracownik.setTyp_pracownika(rs.getString("typ_pracownika"));
           // pracownik.setData_zatrudnienia(rs.getDate("data_zatrudnienia"));
            //Add Cargo to the ObservableList
            pracownikList.add(pracownik);
        }
        //return crgList (ObservableList of Contractors)
        return pracownikList;
    }


    public static void updatePracownik(int pracownik_id, String imie, String nazwisko, String zawod, String ulica,
                                       String nr_dom, String miejscowosc, String email, String nr_telefon,
                                       String wynagrodzenie, String login, String haslo, String typ_pracownika) throws
            SQLException, ClassNotFoundException {


        String sql = "UPDATE pracownicy set imie=?, nazwisko=?, zawod=?, ulica=?, nr_domu=?, miejscowosc=?," +
                "email=? , nr_telefon=? , wynagrodzenie=? , login=? , haslo=?, typ_pracownika=? WHERE pracownik_id=?";


        try {
            Connection conn = ConntectToDB.Connector();
            PreparedStatement prepStmt = null;
            prepStmt = conn.prepareStatement(sql);
            prepStmt.setString(1, imie);
            prepStmt.setString(2, nazwisko);
            prepStmt.setString(3, zawod);
            prepStmt.setString(4, ulica);
            prepStmt.setString(5, nr_dom);
            prepStmt.setString(6, miejscowosc);
            prepStmt.setString(7, email);
            prepStmt.setString(8, nr_telefon);
            prepStmt.setString(9, wynagrodzenie);
            prepStmt.setString(10, login);
            prepStmt.setString(11, haslo);
            prepStmt.setString(12, typ_pracownika);
            prepStmt.setInt(13, pracownik_id);


            prepStmt.executeUpdate();
            prepStmt.close();
            conn.close();

        } catch (SQLException e) {
            System.err.println("Blad przy aktualizacji danych pracownika" + e);
            throw e;
        }

    }

    /**
     * Method to delete user from database
     *
     * @param pracownik_id - ID of user to delete
     * @throws SQLException - Throws when occurs problem with SQL query
     */
    public static void deletePracownikWithId(int pracownik_id) throws SQLException {

        String sql = "DELETE FROM pracownicy WHERE pracownik_id = ?";
        Connection conn = ConntectToDB.Connector();
        try {

            PreparedStatement prepStmt = conn.prepareStatement(sql);
            prepStmt.setInt(1, pracownik_id);
            prepStmt.executeUpdate();

        } catch (SQLException e) {
            System.out.print("Błąd podczas usuwania pracownika!" + e);
            throw e;
        }

    }

    /**
     * Method to getting data about concrete user
     *
     * @param pracownik_id - ID of user to getting data
     * @return - ResultSet with information about user
     * @throws SQLException - Throws when occurs problem with SQL query
     */

    public static ResultSet wyswietlDanePracownika(int pracownik_id) throws SQLException {

        String query = "SELECT * FROM pracownicy WHERE pracownik_id= ?";


        Connection conn = ConntectToDB.Connector();
        PreparedStatement prepStmt = conn.prepareStatement(query);
        prepStmt.setInt(1, pracownik_id);
        prepStmt.executeQuery();

        return prepStmt.executeQuery();


    }
}
