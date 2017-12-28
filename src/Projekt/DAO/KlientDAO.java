package Projekt.DAO;

import Projekt.Modele.Klient;
import Projekt.PodlaczonieDoBazy.ConntectToDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

/**
 * Klasa zapewniająca połączenie między kontrolerem a bazą danych
 *
 * Created by Tomek on 2017-11-17.
 */

public class KlientDAO {

    /**
     * Metoda uzyskania informacji o klientach z bazy danych
     * @return Lista obiektów klient
     * @throws SQLException Zgłasza, gdy występuje problem z zapytaniem SQL
     * @throws ClassNotFoundException Zgłasza, gdy występuje problem z użyciem innej klasy
     */
    public static ObservableList<Klient> pokazKlient() throws SQLException, ClassNotFoundException {

        String selectStmt = "SELECT * FROM klienci";

        try {

            Connection conn = ConntectToDB.Connector();
            PreparedStatement preparedStatement = conn.prepareStatement(selectStmt);
            ResultSet rs = preparedStatement.executeQuery(selectStmt);

            ObservableList<Klient> KlientList = getKlientList(rs);
            System.out.print(rs.next());

            return KlientList;

        } catch (Exception e) {

            System.out.println("Operacja wyboru SQL nie powiodła sie: " + e);

            throw e;

        }
    }

    /**
     * Metoda wyszukiwania rekordu z bazy danych zawierającego słowa podane przez użytkownika
     * @param wyrazenie - Szukane wyrażenie
     * @return Lista danych pasujących do wprowadzonego wyrażenia
     * @throws SQLException Zgłasza, gdy występuje problem z zapytaniem SQL
     * @throws ClassNotFoundException Zgłasza, gdy występuje problem z użyciem innej klasy
     */
    public static ObservableList<Klient> wyszukajKlient(String wyrazenie) throws SQLException,
            ClassNotFoundException {

        String selectStmt = "SELECT DISTINCT * FROM klienci WHERE imie LIKE \"%" + wyrazenie + "%\" OR "
                + "nazwisko =\"" + wyrazenie + "\" OR "
                + "PESEL =\"" + wyrazenie + "\" OR "
                + "miejscowosc LIKE \"%" + wyrazenie + "%\" OR "
                + "ulica LIKE \"%" + wyrazenie + "%\" OR "
                + "nr_dom LIKE \"%" + wyrazenie + "%\" OR "
                + "nr_telefon LIKE \"%" + wyrazenie + "%\" "
                + "GROUP BY imie ";

        try {
            Connection conn = ConntectToDB.Connector();
            PreparedStatement preparedStatement = conn.prepareStatement(selectStmt);
            ResultSet rs = preparedStatement.executeQuery(selectStmt);

            ObservableList<Klient> KlientList = getKlientList(rs);

            return KlientList;
        } catch (SQLException e) {

            System.out.println("Operacja wyboru SQL nie powiodła sie: " + e);

            throw e;
        }
    }

    /**
     * Metoda zwraca liste obiektów ResultSet
     * @param rs z bazy danych
     * @return Lista obiektów klient
     * @throws SQLException Zgłasza, gdy występuje problem z zapytaniem SQL
     * @throws ClassNotFoundException Zgłasza, gdy występuje problem z użyciem innej klasy
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
            klientList.add(klient);
        }
        return klientList;
    }

    /**
     * Metoda aktualizacji informacji o kliencie w bazie danych
     * @param klient_id - Id klienta
     * @param imie - Imie klienta
     * @param nazwisko - Nazwisko klienta
     * @param PESEL - PESEL klienta
     * @param miejscowosc - Miejścowość / Miasto klienta
     * @param ulica - Ulica klienta
     * @param nr_dom - Nr domu klienta
     * @param nr_telefon - Nr telefonu klienta
     * @throws SQLException Zgłasza, gdy występuje problem z zapytaniem SQL
     * @throws ClassNotFoundException Zgłasza, gdy występuje problem z użyciem innej klasy
     */
    public static void updateKlient(int klient_id, String imie, String nazwisko, String PESEL, String
            miejscowosc, String ulica, String nr_dom, String nr_telefon) throws
            SQLException, ClassNotFoundException {

        String sql = "UPDATE klienci set imie=?, nazwisko=?, PESEL=?,  miejscowosc=?,  ulica=?, " +
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
            prepStmt.setInt(8, klient_id);

            prepStmt.executeUpdate();
            prepStmt.close();
            conn.close();

        } catch (SQLException e) {

            System.err.println("Blad przy aktualizacji danych klienta:" + e);

            throw e;
        }
    }

    /**
     * Metoda usuwająca klienta z bazy danych
     * @param klient_id - Id klienta do usunięcia
     * @throws SQLException Zgłasza, gdy występuje problem z zapytaniem SQL
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
     * Metoda uzyskiwania danych o konkretnym kliencie
     * @param klient_id - Id klienta do pobrania danych
     * @return dane o kliencie
     * @throws SQLException Zgłasza, gdy występuje problem z zapytaniem SQL
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
