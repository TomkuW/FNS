package Projekt.DAO;

import Projekt.Modele.Usluga;
import Projekt.PodlaczenieDoBazy.ConntectToDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Klasa zapewniająca połączenie między kontrolerem a bazą danych
 *
 * Created by Tomek on 2017-11-23.
 */
public class UslugaDAO {

    /**
     * Metoda uzyskania informacji o pakietach z bazy danych
     * @return Lista obiektów pakiety
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
            System.out.println("Operacja wyboru SQL nie powiodła sie:  " + e);

            throw e;
        }
    }

    /**
     * Metoda zwraca liste obiektów ResultSet
     * @param rs z bazy danych
     * @return Lista obiektów pakiety
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

    /**
     * Metoda usuwająca pakiet z bazy danych
     * @param pakiet_id - Id pakietu do usunięcia
     * @throws SQLException Zgłasza, gdy występuje problem z zapytaniem SQL
     */
    public static void deleteUslugaWithId(int pakiet_id) throws SQLException {

        String sql = "DELETE FROM pakiety WHERE pakiet_id = ?";
        Connection conn = ConntectToDB.Connector();
        try {

            PreparedStatement prepStmt = conn.prepareStatement(sql);
            prepStmt.setInt(1, pakiet_id);
            prepStmt.executeUpdate();

        } catch (SQLException e) {
            System.out.print("Błąd podczas usuwania klienta" + e);
            throw e;
        }

    }

}
