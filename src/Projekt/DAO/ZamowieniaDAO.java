package Projekt.DAO;

import Projekt.Modele.Klient;
import Projekt.Modele.Pracownik;
import Projekt.Modele.Usluga;
import Projekt.Modele.Zamowienie;
import Projekt.PodlaczenieDoBazy.ConntectToDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;

/**
 * Klasa zapewniająca połączenie między kontrolerem a bazą danych
 *
 * Created by Tomek on 2017-12-13.
 */
public class ZamowieniaDAO {

    //Tabela Zamowienie

    /**
     * Metoda uzyskania informacji o zamowieniach z bazy danych
     * @return Lista obiektów zamowienia
     * @throws SQLException Zgłasza, gdy występuje problem z zapytaniem SQL
     * @throws ClassNotFoundException Zgłasza, gdy występuje problem z użyciem innej klasy
     */
    public static ObservableList<Zamowienie> pokazZamowienie() throws SQLException, ClassNotFoundException {

        String selectStmt = "SELECT z.zamowienia_id,z.klient_id, k.imie,k.nazwisko,k.ulica,k.nr_dom, k.nr_telefon, k" +
                ".miejscowosc, u.predkosc, u.technologia, " +
                "z.pakiet_id, u.nazwa, z.pracownik_id, p.imie, p.nazwisko, z.umowa_od, z.umowa_do, z.status"
                + " from zamowienia z,klienci k,pracownicy p, pakiety u WHERE k.klient_id = z.klient_id AND u" +
                ".pakiet_id = z.pakiet_id and p.pracownik_id = z.pracownik_id ORDER BY z.zamowienia_id";

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
     * Metoda wyszukiwania rekordu z bazy danych zawierającego słowa podane przez użytkownika
     * @param wyrazenie - Szukane wyrażenie
     * @return Lista danych pasujących do wprowadzonego wyrażenia
     * @throws SQLException Zgłasza, gdy występuje problem z zapytaniem SQL
     * @throws ClassNotFoundException Zgłasza, gdy występuje problem z użyciem innej klasy
     */
    public static ObservableList<Zamowienie> wyszukajZamowienie(String wyrazenie) throws SQLException, ClassNotFoundException {

        String selectStmt = "SELECT z.zamowienia_id,z.klient_id, k.imie,k.nazwisko,k.ulica,k.nr_dom, k.miejscowosc," +
                " k.nr_telefon, z.pakiet_id, u.nazwa,u.technologia, u.predkosc, z.pracownik_id, p.imie, p.nazwisko, z" +
                ".umowa_od, z.umowa_do, z.status"
                + " FROM zamowienia z,klienci k,pracownicy p, pakiety u WHERE k.klient_id = z.klient_id AND u" +
                ".pakiet_id = z.pakiet_id and p.pracownik_id = z.pracownik_id" +
                " AND zamowienia_id LIKE \"%" + wyrazenie + "%\"";

        try {
            Connection conn = ConntectToDB.Connector();
            PreparedStatement preparedStatement = conn.prepareStatement(selectStmt);
            ResultSet rs = preparedStatement.executeQuery(selectStmt);

            ObservableList<Zamowienie> ZamowienList1 = getZamowienList(rs);

            return ZamowienList1;

        } catch (SQLException e) {
            System.out.println("Operacja SELECT nie powiodła się: " + e);
            throw e;
        }
    }

    /**
     * Metoda zwraca liste obiektów ResultSet
     * @param rs z bazy danych
     * @return Lista obiektów zamowienia
     * @throws SQLException Zgłasza, gdy występuje problem z zapytaniem SQL
     * @throws ClassNotFoundException Zgłasza, gdy występuje problem z użyciem innej klasy
     */
    private static ObservableList<Zamowienie> getZamowienList(ResultSet rs) throws SQLException, ClassNotFoundException {

        ObservableList<Zamowienie> List = FXCollections.observableArrayList();

        while (rs.next()) {
            Zamowienie z = new Zamowienie();
            z.setZamowienia_id(rs.getInt("zamowienia_id"));
            z.setKlient_id(rs.getInt("klient_id"));
            z.setImieKZ(rs.getString("klienci.imie"));
            z.setNazwiskoKZ(rs.getString("klienci.nazwisko"));
            z.setUlicaKZ(rs.getString("klienci.ulica"));
            z.setNr_domKZ(rs.getString("klienci.nr_dom"));
            z.setNr_telefonKZ(rs.getString("klienci.nr_telefon"));
            z.setMiejscowoscKZ(rs.getString("klienci.miejscowosc"));
            z.setPakiet_id(rs.getInt("pakiet_id"));
            z.setNazwaPakietZ(rs.getString("pakiety.nazwa"));
            z.setTechnologiaPakietZ(rs.getString("pakiety.technologia"));
            z.setPredkoscPakietZ(rs.getString("pakiety.predkosc"));
            z.setPracownik_id(rs.getInt("pracownik_id"));
            z.setPracownikImieZ(rs.getString("pracownicy.imie"));
            z.setPracownikNazwiskoZ(rs.getString("pracownicy.nazwisko"));
            z.setUmowa_od(rs.getDate("umowa_od"));
            z.setUmowa_do(rs.getDate("umowa_do"));
            z.setStatus(rs.getString("status"));
            List.add(z);
        }
        return List;
    }

    /**
     * Metoda usuwająca zamowienia z bazy danych
     * @param zamowienie_id - Id zamowienia do usunięcia
     * @throws SQLException Zgłasza, gdy występuje problem z zapytaniem SQL
     */
    public static void deleteZamowienieWithId(int zamowienie_id) throws SQLException {

        String sql = "DELETE FROM zamowienia WHERE zamowienia_id = ?";
        Connection conn = ConntectToDB.Connector();
        try {

            PreparedStatement prepStmt = conn.prepareStatement(sql);
            prepStmt.setInt(1, zamowienie_id);
            prepStmt.executeUpdate();

        } catch (SQLException e) {
            System.out.print("Błąd podczas usuwania pracownika!" + e);
            throw e;
        }

    }

    /**
     * Metoda uzyskiwania informacji o konkretnym zamuwieniu
     * @param zamowienia_id - Id zamowienia do pobrania danych
     * @return dane o zamowieniu
     * @throws SQLException Zgłasza, gdy występuje problem z zapytaniem SQL
     */
    public static ResultSet wyswietlDaneZamowienia(int zamowienia_id) throws SQLException {

        String query = "SELECT * FROM zamowienia WHERE zamowienia_id= ?";

        Connection conn = ConntectToDB.Connector();
        PreparedStatement prepStmt = conn.prepareStatement(query);
        prepStmt.setInt(1, zamowienia_id);
        prepStmt.executeQuery();

        return prepStmt.executeQuery();

    }

    /**
     *
     * Metoda aktualizacji informacji o zamowieniu w bazie danych

     * @throws SQLException Zgłasza, gdy występuje problem z zapytaniem SQL
     * @param zamowienia_id - Id zamowienia
     * @param status - Status zamowienia
     * @param umowa_od - Poczatek zawarcia umowy
     * @param umowa_do - Koniec okresu umowy
     * @throws ClassNotFoundException Zgłasza, gdy występuje problem z użyciem innej klasy
     */
    public static void updateZamowienie(int zamowienia_id, String status, LocalDate
            umowa_od, LocalDate umowa_do) throws
            SQLException, ClassNotFoundException {

            String sql = "UPDATE zamowienia set status=?, umowa_od=?, umowa_do=? " +
                " WHERE zamowienia_id=?";

        try {
            Connection conn = ConntectToDB.Connector();
            PreparedStatement prepStmt = null;
            prepStmt = conn.prepareStatement(sql);
            prepStmt.setString(1, status);
            prepStmt.setDate(2, Date.valueOf(umowa_od));
            prepStmt.setDate(3, Date.valueOf(umowa_do));
            prepStmt.setInt(4, zamowienia_id);
            prepStmt.executeUpdate();
            prepStmt.close();
            conn.close();

        } catch (SQLException e) {
            System.err.println("Blad przy aktualizacji danych klienta" + e);
            throw e;
        }
        pokazZamowienie();
    }

    //Tabela PracownikZamowienie

    /**
     * Metoda uzyskania informacji o pracownikach z bazy danych
     * @return Lista obiektów pracownicy
     * @throws SQLException Zgłasza, gdy występuje problem z zapytaniem SQL
     * @throws ClassNotFoundException Zgłasza, gdy występuje problem z użyciem innej klasy
     */
    public static ObservableList<Pracownik> pokazPracownik() throws SQLException, ClassNotFoundException {

        String selectStmt = "SELECT * FROM pracownicy WHERE typ_pracownika LIKE 'Pracownik'";

        try {

            Connection conn = ConntectToDB.Connector();
            PreparedStatement preparedStatement = conn.prepareStatement(selectStmt);
            ResultSet rs = preparedStatement.executeQuery(selectStmt);

            ObservableList<Pracownik> Pracownikist = getPracownikList(rs);
            System.out.print(rs.next());

            return Pracownikist;
        } catch (Exception e) {
            System.out.println("Operacja SELECT nie powiodła się:" + e);

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
    public static ObservableList<Pracownik> wyszukajPracownik(String wyrazenie) throws SQLException,
            ClassNotFoundException {

        String selectStmt = "SELECT * FROM pracownicy WHERE typ_pracownika ='Wykonawca'"
                + "AND imie LIKE\"%" + wyrazenie + "%\" OR "
                + "nazwisko LIKE \"" + wyrazenie + "\"";

        try {
            Connection conn = ConntectToDB.Connector();
            PreparedStatement preparedStatement = conn.prepareStatement(selectStmt);
            ResultSet rs = preparedStatement.executeQuery(selectStmt);

            ObservableList<Pracownik> PracownikList = getPracownikList(rs);

            return PracownikList;
        } catch (SQLException e) {
            System.out.println("Operacja SELECT nie powiodła się: " + e);
            throw e;
        }
    }

    /**
     * Metoda zwraca liste obiektów ResultSet
     * @param rs z bazy danych
     * @return Lista obiektów pracownik
     * @throws SQLException Zgłasza, gdy występuje problem z zapytaniem SQL
     * @throws ClassNotFoundException Zgłasza, gdy występuje problem z użyciem innej klasy
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
            pracownik.setNr_domu(rs.getString("nr_domu"));
            pracownik.setMiejscowosc(rs.getString("miejscowosc"));
            pracownik.setEmail(rs.getString("email"));
            pracownik.setNr_telefon(rs.getString("nr_telefon"));
            pracownik.setWynagrodzenie(rs.getInt("wynagrodzenie"));
            pracownik.setLogin(rs.getString("login"));
            pracownik.setData_zatrudnienia(rs.getDate("data_zatrudnienia"));
            pracownik.setHaslo(rs.getString("haslo"));
            pracownik.setTyp_pracownika(rs.getString("typ_pracownika"));
            pracownikList.add(pracownik);
        }
        return pracownikList;
    }

    //Tabela UslugaZamowienie

    /**
     * Metoda uzyskania informacji o uslugach z bazy danych
     * @return Lista obiektów uslug
     * @throws SQLException Zgłasza, gdy występuje problem z zapytaniem SQL
     * @throws ClassNotFoundException Zgłasza, gdy występuje problem z użyciem innej klasy
     */
    public static ObservableList<Usluga> pokazUsluga() throws SQLException, ClassNotFoundException {

        String selectStmt = "SELECT * FROM pakiety";

        try {

            Connection conn = ConntectToDB.Connector();
            PreparedStatement preparedStatement = conn.prepareStatement(selectStmt);
            ResultSet rs = preparedStatement.executeQuery(selectStmt);

            ObservableList<Usluga> UslugaList = getUslugaList(rs);
            System.out.print(rs.next());

            return UslugaList;
        } catch (Exception e) {
            System.out.println("Operacja SELECT nie powiodła się:" + e);

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
    public static ObservableList<Usluga> wyszukajUsluga(String wyrazenie) throws SQLException,
            ClassNotFoundException {

        String selectStmt = "SELECT * FROM pakiety WHERE nazwa LIKE \"%" + wyrazenie + "%\" OR "
                + "technologia =\"" + wyrazenie + "\" OR "
                + "predkosc =\"" + wyrazenie + "\" OR "
                + "cena = \"%" + wyrazenie + "%\" OR "
                + "okres = \"%" + wyrazenie + "%\"";

        try {
            Connection conn = ConntectToDB.Connector();
            PreparedStatement preparedStatement = conn.prepareStatement(selectStmt);
            ResultSet rs = preparedStatement.executeQuery(selectStmt);

            ObservableList<Usluga> UslugaList = getUslugaList(rs);

            return UslugaList;
        } catch (SQLException e) {
            System.out.println("Operacja SELECT nie powiodła się:" + e);

            throw e;
        }
    }

    /**
     * Metoda zwraca liste obiektów ResultSet
     * @param rs z bazy danych
     * @return Lista obiektów usluga
     * @throws SQLException Zgłasza, gdy występuje problem z zapytaniem SQL
     * @throws ClassNotFoundException Zgłasza, gdy występuje problem z użyciem innej klasy
     */
    private static ObservableList<Usluga> getUslugaList(ResultSet rs) throws SQLException, ClassNotFoundException {

        ObservableList<Usluga> uslugaList = FXCollections.observableArrayList();

        while (rs.next()) {
            Usluga usluga = new Usluga();
            usluga.setPakiet_id(rs.getInt("pakiet_id"));
            usluga.setNazwa(rs.getString("nazwa"));
            usluga.setTechnologia(rs.getString("technologia"));
            usluga.setPredkosc(rs.getString("predkosc"));
            usluga.setCena(rs.getDouble("cena"));
            usluga.setOkres(rs.getInt("okres"));

            uslugaList.add(usluga);
        }
        return uslugaList;
    }

    //Tabela KlientZamowienie

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
            System.out.println("Operacja SELECT nie powiodła się:" + e);

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
            System.out.println("Operacja SELECT nie powiodła się: " + e);

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
}
