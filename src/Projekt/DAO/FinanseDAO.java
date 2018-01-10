package Projekt.DAO;

import Projekt.Modele.Pracownik;
import Projekt.Modele.Zamowienie;
import Projekt.PodlaczenieDoBazy.ConntectToDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

/**
 * Klasa zapewniająca połączenie między kontrolerem a bazą danych
 *
 * Created by Tomek on 2017-12-13.
 */
public class FinanseDAO {


    /**
     * Metoda uzyskania informacji o Finansach z bazy danych
     * @return Lista obiektów Zamowienia
     * @throws SQLException Zgłasza, gdy występuje problem z zapytaniem SQL
     * @throws ClassNotFoundException Zgłasza, gdy występuje problem z użyciem innej klasy
     */
    public static ObservableList<Zamowienie> pokazFinanseUslugi () throws SQLException, ClassNotFoundException {

        String selectStmt = "SELECT DISTINCT u.nazwa, z.umowa_od, z" +
                ".umowa_do, u.cena, u.pakiet_id "
                + " from zamowienia z, pakiety u WHERE u" +
                ".pakiet_id = z.pakiet_id";


        String sql1 = "SELECT COUNT(pakiet_id) from zamowienia GROUP BY pakiet_id";

        try {
            Connection conn = ConntectToDB.Connector();
            PreparedStatement preparedStatement = conn.prepareStatement(selectStmt);
            ResultSet rs = preparedStatement.executeQuery(selectStmt);

            Connection conn2 = ConntectToDB.Connector();
            PreparedStatement preparedStatement2 = conn2.prepareStatement(sql1);
            ResultSet rs2 = preparedStatement2.executeQuery(sql1);

            ObservableList<Zamowienie> ZamowienList = getZamowienList(rs, rs2);

            return ZamowienList;
        } catch (SQLException e) {
            System.out.println("Operacja SELECT nie powiodła sie: " + e);

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
    public static ObservableList<Zamowienie> wyszukajFinansePakiet (String wyrazenie) throws SQLException,
            ClassNotFoundException {

        String selectStmt = "SELECT u.nazwa, u.cena, z.umowa_od, z.umowa_do, z.pakiet_id"
                + " FROM zamowienia z, pakiety u WHERE u" +
                ".pakiet_id = z.pakiet_id" +
                " AND u.nazwa LIKE \"%"+ wyrazenie +"%\"";

        String sql1 = "SELECT COUNT(pakiet_id) from zamowienia";

        try {
            Connection conn = ConntectToDB.Connector();
            PreparedStatement preparedStatement = conn.prepareStatement(selectStmt);
            ResultSet rs = preparedStatement.executeQuery(selectStmt);

            Connection conn2 = ConntectToDB.Connector();
            PreparedStatement preparedStatement2 = conn2.prepareStatement(sql1);
            ResultSet rs2 = preparedStatement2.executeQuery(sql1);

            ObservableList<Zamowienie> ZamowienList1 = getZamowienList(rs, rs2);

            return ZamowienList1;
        } catch (SQLException e) {
            System.out.println("Operacja SELECT nie powiodła sie:" + e);

            throw e;
        }
    }

    /**
     * Metoda zwraca liste obiektów ResultSet
     * @param rs z bazy danych
     * @param rs2 z bazy danych
     * @return Lista obiektów Zamowienia
     * @throws SQLException Zgłasza, gdy występuje problem z zapytaniem SQL
     * @throws ClassNotFoundException Zgłasza, gdy występuje problem z użyciem innej klasy
     */
    private static ObservableList<Zamowienie> getZamowienList(ResultSet rs, ResultSet rs2) throws
            SQLException,
            ClassNotFoundException {

        ObservableList<Zamowienie> List = FXCollections.observableArrayList();

        while (rs.next() && rs2.next()) {
            Zamowienie z = new Zamowienie();
            z.setPakiet_id(rs.getInt("pakiet_id"));
            z.setNazwaPakietZ(rs.getString("nazwa"));
            z.setUmowa_od(rs.getDate("umowa_od"));
            z.setUmowa_do(rs.getDate("umowa_do"));
            z.setCenaU(rs.getDouble("cena"));
            z.setIloscU(rs2.getInt("COUNT(pakiet_id)"));
            z.setCenaZbU(z.getCenaU()* z.getIloscU());

            List.add(z);
        }
        return List;
    }

    /**
     * Metoda uzyskania informacji o Finansach z bazy danych
     * @return Lista obiektów Zamowienia
     * @throws SQLException Zgłasza, gdy występuje problem z zapytaniem SQL
     * @throws ClassNotFoundException Zgłasza, gdy występuje problem z użyciem innej klasy
     */
    public static ObservableList<Pracownik> pokazFinansePracownik() throws SQLException, ClassNotFoundException {

        String selectStmt = "SELECT * FROM pracownicy";

        try {
            Connection conn = ConntectToDB.Connector();
            PreparedStatement preparedStatement = conn.prepareStatement(selectStmt);
            ResultSet rs = preparedStatement.executeQuery(selectStmt);

            ObservableList<Pracownik> Pracownikist = getPracownikFinanseList(rs);
            System.out.print(rs.next());

            return Pracownikist;
        } catch (Exception e) {
            System.out.println("Operacja SELECT nie powiodla sie: " + e);

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
    public static ObservableList<Pracownik> wyszukajPracownikFinanse (String wyrazenie) throws SQLException,
            ClassNotFoundException {

        String selectStmt = "SELECT * FROM pracownicy WHERE imie LIKE \"%" + wyrazenie + "%\" OR "
                + "nazwisko LIKE\"%"+wyrazenie + "%\" OR "
                + "zawod LIKE\"%"+ wyrazenie+ "%\" OR "
                + "ulica LIKE\"%"+ wyrazenie+ "%\" OR "
                + "nr_domu =\""+ wyrazenie+ "\" OR "
                + "miejscowosc LIKE\"%" + wyrazenie +"%\" OR "
                + "wynagrodzenie = \""+ wyrazenie + "\"";

        try {
            Connection conn = ConntectToDB.Connector();
            PreparedStatement  preparedStatement = conn.prepareStatement(selectStmt);
            ResultSet rs = preparedStatement.executeQuery(selectStmt);

            ObservableList<Pracownik> PracownikList = getPracownikFinanseList(rs);

            return PracownikList;
        }
            catch (SQLException e) {
            System.out.println("Operacja SELECT nie powiodła się: " + e);

            throw e;
        }
    }

    /**
     * Metoda zwraca liste obiektów ResultSet
     * @param rs z bazy danych
     * @return Lista obiektów Zamowienia
     * @throws SQLException Zgłasza, gdy występuje problem z zapytaniem SQL
     * @throws ClassNotFoundException Zgłasza, gdy występuje problem z użyciem innej klasy
     */
    private static ObservableList<Pracownik> getPracownikFinanseList(ResultSet rs) throws SQLException,
            ClassNotFoundException {

        ObservableList<Pracownik> pracownikList = FXCollections.observableArrayList();

        while (rs.next()) {
            Pracownik pracownik = new Pracownik();

            pracownik.setImie(rs.getString("imie"));
            pracownik.setNazwisko(rs.getString("nazwisko"));
            pracownik.setZawod(rs.getString("zawod"));
            pracownik.setUlica(rs.getString("ulica"));
            pracownik.setNr_domu(rs.getString("nr_domu"));
            pracownik.setMiejscowosc(rs.getString("miejscowosc"));
            pracownik.setWynagrodzenie(rs.getDouble("wynagrodzenie"));
            pracownik.setWynagrodzenieRoczne(pracownik.getWynagrodzenie()* 12);
            pracownikList.add(pracownik);
        }

        return pracownikList;
    }

}
