package Projekt.Okienka;

import Projekt.Modele.Pracownik;
import Projekt.Modele.Zamowienie;
import Projekt.PodlaczonieDoBazy.ConntectToDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

/**
 * Created by Tomek on 2017-12-13.
 */
public class FinanseDAO {



    public static ObservableList<Zamowienie> pokazFinanseUslugi () throws SQLException, ClassNotFoundException {
        //Declare a SELECT statement
        String selectStmt = "SELECT DISTINCT u.nazwa, z.umowa_od, z" +
                ".umowa_do, u.cena, u.pakiet_id "
                + " from zamowienia z, pakiety u WHERE u" +
                ".pakiet_id = z.pakiet_id";


        String sql1 = "SELECT COUNT(pakiet_id) from zamowienia GROUP BY pakiet_id";

        //Execute SELECT statement
        try {
            Connection conn = ConntectToDB.Connector();
            PreparedStatement preparedStatement = conn.prepareStatement(selectStmt);
            ResultSet rs = preparedStatement.executeQuery(selectStmt);


            Connection conn2 = ConntectToDB.Connector();
            PreparedStatement preparedStatement2 = conn2.prepareStatement(sql1);
            ResultSet rs2 = preparedStatement2.executeQuery(sql1);



            //Send ResultSet to the getContractorList method and get Contractor object
            ObservableList<Zamowienie> ZamowienList = getZamowienList(rs, rs2);

            //Return Contractor object
            return ZamowienList;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            //Return exception
            throw e;
        }
    }

    /**
     * Method to get data about order with "New" state
     *
     * @return List of objects
     * @throws SQLException - Throws when occurs problem with SQL query
     * @throws ClassNotFoundException - Throws when occurs problem with another class
     */
    public static ObservableList<Zamowienie> wyszukajFinansePakiet (String wyrazenie) throws SQLException,
            ClassNotFoundException {
        //Declare a SELECT statement

        String selectStmt = "SELECT u.nazwa, u.cena, z.umowa_od, z.umowa_do, z.pakiet_id"
                + " FROM zamowienia z, pakiety u WHERE u" +
                ".pakiet_id = z.pakiet_id" +
                " AND u.nazwa LIKE \"%"+ wyrazenie +"%\"";


        String sql1 = "SELECT COUNT(pakiet_id) from zamowienia";

        //Execute SELECT statement
        try {
            Connection conn = ConntectToDB.Connector();
            PreparedStatement preparedStatement = conn.prepareStatement(selectStmt);
            ResultSet rs = preparedStatement.executeQuery(selectStmt);

            Connection conn2 = ConntectToDB.Connector();
            PreparedStatement preparedStatement2 = conn2.prepareStatement(sql1);
            ResultSet rs2 = preparedStatement2.executeQuery(sql1);



            //Send ResultSet to the getContractorList method and get Contractor object
            ObservableList<Zamowienie> ZamowienList1 = getZamowienList(rs, rs2);

            //Return Contractor object
            return ZamowienList1;
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
    private static ObservableList<Zamowienie> getZamowienList(ResultSet rs, ResultSet rs2) throws
            SQLException,
            ClassNotFoundException {
        //Declare a observable List which comprises of Contractor objects
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

            //Add Cargo to the ObservableList
            List.add(z);
        }
        //return crgList (ObservableList of Contractors)
        return List;
    }



      public static ResultSet wyswietlDaneZamowienia( int zamowienia_id) throws SQLException {

        String query = "SELECT * FROM zamowienia WHERE zamowienia_id= ?";


        Connection conn = ConntectToDB.Connector();
        PreparedStatement prepStmt = conn.prepareStatement(query);
        prepStmt.setInt(1, zamowienia_id);
        prepStmt.executeQuery();

        return prepStmt.executeQuery();



    }

    //----pr

    public static ObservableList<Pracownik> pokazFinansePracownik() throws SQLException, ClassNotFoundException {

        String selectStmt = "SELECT *" +
                " FROM " +
                "pracownicy";

        try {


            Connection conn = ConntectToDB.Connector();
            PreparedStatement preparedStatement = conn.prepareStatement(selectStmt);
            ResultSet rs = preparedStatement.executeQuery(selectStmt);

            //Send ResultSet to the getContractorList method and get Contractor object
            ObservableList<Pracownik> Pracownikist = getPracownikFinanseList(rs);
            System.out.print(rs.next());
            //Return Contractor object
            return Pracownikist;
        } catch (Exception e) {
            System.out.println("SQL select operation has been failed: " + e);
            //Return exception
            throw e;

        }

    }


    public static ObservableList<Pracownik> wyszukajPracownikFinanse (String wyrazenie) throws SQLException,
            ClassNotFoundException {
        //Declare a SELECT statement
        String selectStmt = "SELECT * FROM pracownicy WHERE imie LIKE \"%" + wyrazenie + "%\" OR "
                + "nazwisko LIKE\"%"+wyrazenie + "%\" OR "
                + "zawod LIKE\"%"+ wyrazenie+ "%\" OR "
                + "ulica LIKE\"%"+ wyrazenie+ "%\" OR "
                + "nr_domu =\""+ wyrazenie+ "\" OR "
                + "miejscowosc LIKE\"%" + wyrazenie +"%\" OR "
                + "wynagrodzenie = \""+ wyrazenie + "\"";






        //Execute SELECT statement
        try {
            Connection conn = ConntectToDB.Connector();
            PreparedStatement  preparedStatement = conn.prepareStatement(selectStmt);
            ResultSet rs = preparedStatement.executeQuery(selectStmt);

            //Send ResultSet to the getCargoList method and get Cargo object
            ObservableList<Pracownik> PracownikList = getPracownikFinanseList(rs);

            //Return Cargo object
            return PracownikList;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            //Return exception
            throw e;
        }
    }


    /**
     * Method to returning list of object from raw ResultSet
     *
     * @param rs - ResultSet from database
     * @return - List of user objects
     * @throws SQLException           - Throws when occurs problem with SQL query
     * @throws ClassNotFoundException - Throws when occurs problem with using
     *                                another class
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
            //Add Cargo to the ObservableList
            pracownikList.add(pracownik);
        }
        //return crgList (ObservableList of Contractors)
        return pracownikList;
    }
    public static ResultSet wyswietlDanePracownikaFinanse( int pracownik_id) throws SQLException {

        String query = "SELECT * FROM pracownicy WHERE pracownik_id= ?";


        Connection conn = ConntectToDB.Connector();
        PreparedStatement prepStmt = conn.prepareStatement(query);
        prepStmt.setInt(1, pracownik_id);
        prepStmt.executeQuery();

        return prepStmt.executeQuery();


    }

}
