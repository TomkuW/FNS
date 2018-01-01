package Projekt.DAO;

import Projekt.Modele.Pracownik;
import Projekt.PodlaczonieDoBazy.ConntectToDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Klasa zapewniająca połączenie między kontrolerem a bazą danych
 *
 * Created by Tomek on 2017-11-25.
 */
public class PracownikDAO {


    /**
     * Metoda uzyskania informacji o pracownikach z bazy danych
     *
     * @return Lista obiektów pracownicy
     * @throws SQLException Zgłasza, gdy występuje problem z zapytaniem SQL
     * @throws ClassNotFoundException Zgłasza, gdy występuje problem z użyciem innej klasy
     */
    public static ObservableList<Pracownik> pokazPracownik() throws SQLException, ClassNotFoundException {

        String selectStmt = "SELECT * FROM pracownicy";

        try {

            Connection conn = ConntectToDB.Connector();
            PreparedStatement preparedStatement = conn.prepareStatement(selectStmt);
            ResultSet rs = preparedStatement.executeQuery(selectStmt);

            ObservableList<Pracownik> Pracownikist = getPracownikList(rs);
            System.out.print(rs.next());

            return Pracownikist;
        } catch (Exception e) {
            System.out.println("Operacja wyboru SQL nie powiodła sie " + e);

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
    public static ObservableList<Pracownik> wyszukajPracownik (String wyrazenie) throws SQLException,
            ClassNotFoundException {

        String selectStmt = "SELECT * FROM pracownicy WHERE imie LIKE \"%" + wyrazenie + "%\" OR "
                + "nazwisko LIKE\"%"+wyrazenie + "%\" OR "
                + "zawod =\"%"+ wyrazenie+ "%\" OR "
                + "PESEL =\""+ wyrazenie+ "\" OR "
                + "ulica =\"%"+ wyrazenie+ "%\" OR "
                + "nr_domu =\""+ wyrazenie+ "\" OR "
                + "miejscowosc=\"%" + wyrazenie +"%\" OR "
                + "email = \""+ wyrazenie +"\" OR "
                + "nr_telefon =\""+ wyrazenie + "%\" OR "
                + "wynagrodzenie = \""+ wyrazenie + "\"";

        try {
            Connection conn = ConntectToDB.Connector();
            PreparedStatement  preparedStatement = conn.prepareStatement(selectStmt);
            ResultSet rs = preparedStatement.executeQuery(selectStmt);

            ObservableList<Pracownik> PracownikList = getPracownikList(rs);

            return PracownikList;
        } catch (SQLException e) {
            System.out.println("Operacja wyboru SQL nie powiodła sie: " + e);

            throw e;
        }
    }

  /**
   * Metoda zwraca liste obiektów ResultSet
   *
   * @param rs z bazy danych
   * @return Lista obiektów pracownicy
   * @throws SQLException Zgłasza, gdy występuje problem z zapytaniem SQL
   * @throws ClassNotFoundException Zgłasza, gdy występuje problem z użyciem innej klasy
   */
    private static ObservableList<Pracownik> getPracownikList(ResultSet rs) throws SQLException,
            ClassNotFoundException {

        ObservableList<Pracownik> pracownikList = FXCollections.observableArrayList();

        while (rs.next()) {
            Pracownik pracownik = new Pracownik();
            pracownik.setPracownik_id(rs.getInt("pracownik_id"));
            pracownik.setImie(rs.getString("imie"));
            pracownik.setNazwisko(rs.getString("nazwisko"));
            pracownik.setZawod(rs.getString("zawod"));
            pracownik.setPESEL(rs.getString("PESEL"));
            pracownik.setUlica(rs.getString("ulica"));
            pracownik.setNr_domu(rs.getString("nr_domu"));
            pracownik.setMiejscowosc(rs.getString("miejscowosc"));
            pracownik.setEmail(rs.getString("email"));
            pracownik.setNr_telefon(rs.getString("nr_telefon"));
            pracownik.setWynagrodzenie(rs.getDouble("wynagrodzenie"));
            pracownik.setLogin(rs.getString("login"));
            pracownik.setData_zatrudnienia(rs.getDate("data_zatrudnienia"));
            pracownik.setHaslo(rs.getString("haslo"));
            pracownik.setTyp_pracownika(rs.getString("typ_pracownika"));
            pracownikList.add(pracownik);
        }

        return pracownikList;
    }

    /**
     * Metoda aktualizacji informacji o pracowniku w bazie danych
     * @param pracownik_id - ID pracownika
     * @param imie - Imie pracownika
     * @param nazwisko - Nazwisko pracownika
     * @param zawod - Zawod pracownika
     * @param ulica - Ulica pracownika
     * @param nr_domu - Nr domu pracownika
     * @param miejscowosc - Miasto pracownika
     * @param email - Adress email pracownika
     * @param nr_telefon - Telefon pracownika
     * @param wynagrodzenie - Wynagrodzenie miesieczne pracownika
     * @param login - Login pracownika, potrzeby do logowani do systemu
     * @param haslo - Hasło pracownika, potrzebne do logowania do systemu
     * @param typ_pracownika - Typ pracownika: Kierownik, Administrator, Handlowiec, Ksiegowy, Pracownik-Wykonawca
     * @throws SQLException Zgłasza, gdy występuje problem z zapytaniem SQL
     * @throws ClassNotFoundException Zgłasza, gdy występuje problem z użyciem innej klasy
     */
    public static void updatePracownik(int pracownik_id, String imie, String nazwisko, String zawod,
                                       String ulica, String nr_domu, String miejscowosc, String email, String
                                                nr_telefon, String wynagrodzenie, String login,
                                       String haslo, String typ_pracownika) throws
            SQLException, ClassNotFoundException {


        String sql="UPDATE pracownicy set imie=?, nazwisko=?, zawod=?, ulica=?, nr_domu=?, miejscowosc=?,"+
        "email=? , nr_telefon=? , wynagrodzenie=? , login=? , " +
                " haslo=?,typ_pracownika=? WHERE pracownik_id=?";


        try {
            Connection conn = ConntectToDB.Connector();
            PreparedStatement prepStmt = null;
            prepStmt = conn.prepareStatement(sql);
            prepStmt.setString(1, imie);
            prepStmt.setString(2, nazwisko);
            prepStmt.setString(3,zawod);
            prepStmt.setString(4, ulica);
            prepStmt.setString(5, nr_domu);
            prepStmt.setString(6, miejscowosc);
            prepStmt.setString(7, email);
            prepStmt.setString(8,nr_telefon);
            prepStmt.setString(9,wynagrodzenie);
            prepStmt.setString(10,login);
            prepStmt.setString(11,haslo);
            prepStmt.setString(12,typ_pracownika);
            prepStmt.setInt(13,pracownik_id);

            prepStmt.executeUpdate();
            prepStmt.close();
            conn.close();

        } catch (SQLException e) {
            System.err.println("Blad przy aktualizacji danych klienta" +e);
            throw e;
        }

    }

    /**
     * Metoda usuwająca pracownika z bazy danych
     * @param pracownik_id - Id pracownika do usunięcia
     * @throws SQLException Zgłasza, gdy występuje problem z zapytaniem SQL
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
     * Metoda uzyskiwania danych o konkretnym pracowniku
     *
     * @param pracownik_id - Id pracownika do pobrania danych
     * @return dane o pracowniku
     * @throws SQLException Zgłasza, gdy występuje problem z zapytaniem SQL
     */
    public static ResultSet wyswietlDanePracownika( int pracownik_id) throws SQLException {

        String query = "SELECT * FROM pracownicy WHERE pracownik_id= ?";

        Connection conn = ConntectToDB.Connector();
        PreparedStatement prepStmt = conn.prepareStatement(query);
        prepStmt.setInt(1, pracownik_id);
        prepStmt.executeQuery();

        return prepStmt.executeQuery();

    }
}
