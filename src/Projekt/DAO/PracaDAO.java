package Projekt.DAO;

import Projekt.Modele.Zamowienie;
import Projekt.PodlaczenieDoBazy.ConntectToDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

/**
 * Klasa zapewniająca połączenie między kontrolerem a bazą danych
 *
 * Created by Tomek on 2017-12-26.
 */
public class PracaDAO {

    /**
     * Metoda uzyskania informacji o Pracy z bazy danych
     * @return Lista obiektów Zamowienia
     * @throws SQLException Zgłasza, gdy występuje problem z zapytaniem SQL
     * @throws ClassNotFoundException Zgłasza, gdy występuje problem z użyciem innej klasy
     */
    public static ObservableList<Zamowienie> pokazPraca () throws SQLException, ClassNotFoundException {

        Integer war =Integer.valueOf(ConntectToDB.getCurrentUser()[0]);
        String selectStmt = "SELECT DISTINCT z.zamowienia_id, k.imie,k.nazwisko,k.ulica, k.miejscowosc, k.nr_dom, k" +
                ".nr_telefon, u.nazwa, u.technologia, u.predkosc, z.status, z.pracownik_id"
                + " from zamowienia z,klienci k,pracownicy p, pakiety u " +
                "WHERE k.klient_id = z.klient_id AND u" +
                ".pakiet_id = z.pakiet_id and p.pracownik_id = z.pracownik_id AND z.pracownik_id =\""+ war + "\" " +
                " AND (status LIKE 'Oczekuje' OR status LIKE 'W trakcie') GROUP BY z.zamowienia_id";

        try {
            Connection conn = ConntectToDB.Connector();
            PreparedStatement preparedStatement = conn.prepareStatement(selectStmt);
            ResultSet rs = preparedStatement.executeQuery(selectStmt);

            ObservableList<Zamowienie> ZamowienList = getZamowienList(rs);

            return ZamowienList;
        } catch (SQLException e) {
            System.out.println("Operacja SELECT nie powiodła się: " + e);

            throw e;
            
        }
    }

    /**
     * Metoda zwraca liste obiektów ResultSet
     * @param rs z bazy danych
     * @return Lista obiektów Zamowienie
     * @throws SQLException Zgłasza, gdy występuje problem z zapytaniem SQL
     * @throws ClassNotFoundException Zgłasza, gdy występuje problem z użyciem innej klasy
     */
    private static ObservableList<Zamowienie> getZamowienList(ResultSet rs) throws SQLException,
            ClassNotFoundException {

        ObservableList<Zamowienie> List = FXCollections.observableArrayList();

        while (rs.next()) {
            Zamowienie z = new Zamowienie();
            z.setZamowienia_id(rs.getInt("zamowienia_id"));
            z.setPracownik_id(rs.getInt("zamowienia.pracownik_id"));
            z.setImieKZ(rs.getString("klienci.imie"));
            z.setNazwiskoKZ(rs.getString("klienci.nazwisko"));
            z.setUlicaKZ(rs.getString("klienci.ulica"));
            z.setNr_domKZ(rs.getString("klienci.nr_dom"));
            z.setMiejscowoscKZ(rs.getString("klienci.miejscowosc"));
            z.setNr_telefonKZ(rs.getString("klienci.nr_telefon"));
            z.setNazwaPakietZ(rs.getString("pakiety.nazwa"));
            z.setPredkoscPakietZ(rs.getString("pakiety.predkosc"));
            z.setTechnologiaPakietZ(rs.getString("pakiety.technologia"));
            z.setStatus(rs.getString("status"));

            List.add(z);
        }

        return List;
    }


    /**
     * Metoda uzyskiwania danych o konkretnym zamowieniu by uzyska informacje o statusie wykonanej pracy
     * @param zamowienia_id - Id zamowienia do pobrania danych
     * @return dane o pracy na podstawie zamowienia
     * @throws SQLException Zgłasza, gdy występuje problem z zapytaniem SQL
     */
    public static ResultSet wyswietlDaneZamowienia( int zamowienia_id) throws SQLException {

        String query = "SELECT status from zamowienia where zamowienia_id =?";

        Connection conn = ConntectToDB.Connector();
        PreparedStatement prepStmt = conn.prepareStatement(query);
        prepStmt.setInt(1, zamowienia_id);
        prepStmt.executeQuery();

        return prepStmt.executeQuery();

    }

    /**
     * Metoda aktualizacji informacji o zamowieniu w bazie danych
     * @param zamowienia_id - Id zamowienia
     * @param status - Status wykonania uslugi instalacji pakietu przez Pracownika-Wykonawce
     * @throws SQLException Zgłasza, gdy występuje problem z zapytaniem SQL
     * @throws ClassNotFoundException Zgłasza, gdy występuje problem z użyciem innej klasy
     */
    public static void updatePZamowienie(int zamowienia_id,String status) throws
            SQLException, ClassNotFoundException {

        String sql="UPDATE zamowienia set status=?" +
                " WHERE zamowienia_id=?";

        try {
            Connection conn = ConntectToDB.Connector();
            PreparedStatement prepStmt = null;
            prepStmt = conn.prepareStatement(sql);
            prepStmt.setString(1,status);
            prepStmt.setInt(2,zamowienia_id);
            prepStmt.executeUpdate();
            prepStmt.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println("Blad przy aktualizacji danych klienta" +e);
            throw e;
        }

    }
}
