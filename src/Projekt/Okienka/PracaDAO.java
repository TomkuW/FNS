package Projekt.Okienka;

import Projekt.Modele.Zamowienie;
import Projekt.PodlaczonieDoBazy.ConntectToDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

/**
 * Created by Tomek on 2017-12-26.
 */
public class PracaDAO {

    public static ObservableList<Zamowienie> pokazPraca () throws SQLException, ClassNotFoundException {
        //Declare a SELECT statement
        Integer war =Integer.valueOf(ConntectToDB.getCurrentUser()[0]);
        String selectStmt = "SELECT DISTINCT z.zamowienia_id, k.imie,k.nazwisko,k.ulica, k.miejscowosc, k.nr_dom, k" +
                ".nr_telefon, u.nazwa, u.technologia, u.predkosc, z.status, z.pracownik_id"
                + " from zamowienia z,klienci k,pracownicy p, pakiety u " +
                "WHERE k.klient_id = z.klient_id AND u" +
                ".pakiet_id = z.pakiet_id and p.pracownik_id = z.pracownik_id AND z.pracownik_id =\""+ war + "\" " +
                " AND (status LIKE 'Oczekuje' OR status LIKE 'W trakcie') GROUP BY z.zamowienia_id";


       // +"BY z.zamowienia_id"

        //Execute SELECT statement
        try {
            Connection conn = ConntectToDB.Connector();
            PreparedStatement preparedStatement = conn.prepareStatement(selectStmt);
            ResultSet rs = preparedStatement.executeQuery(selectStmt);

            //Send ResultSet to the getContractorList method and get Contractor object
            ObservableList<Zamowienie> ZamowienList = getZamowienList(rs);

            //Return Contractor object
            return ZamowienList;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            //Return exception
            throw e;
        }
    }




    /**
     * Method to return list of object from raw ResultSet
     *
     * @param rs - ResultSet from database
     * @return - List of objects
     * @throws SQLException - Throws when occurs problem with SQL query
     * @throws ClassNotFoundException - Throws when occurs problem with using another class
     */
    //Select * from Contractor operation
    private static ObservableList<Zamowienie> getZamowienList(ResultSet rs) throws SQLException,
            ClassNotFoundException {
        //Declare a observable List which comprises of Contractor objects
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
            //Add Cargo to the ObservableList
            List.add(z);
        }
        //return crgList (ObservableList of Contractors)
        return List;
    }



    public static ResultSet wyswietlDaneZamowienia( int zamowienia_id) throws SQLException {

        String query = "SELECT status from zamowienia where zamowienia_id =?";


        Connection conn = ConntectToDB.Connector();

        PreparedStatement prepStmt = conn.prepareStatement(query);

        prepStmt.setInt(1, zamowienia_id);
        prepStmt.executeQuery();

        return prepStmt.executeQuery();


    }
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
            System.err.println("Blad przy aktualizacji danych klienta" +e);
            throw e;
        }

    }
}
