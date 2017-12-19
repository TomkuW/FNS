package Projekt.Okienka;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.swing.text.TabableView;
import javax.swing.text.TableView;
import java.sql.*;


/**
 * Created by Fabian on 2017-12-05.
 */
public class UslugaDAO {


    public static ObservableList<Usluga> pokazUsluga() throws SQLException, ClassNotFoundException {

        String selectStmt = "SELECT * FROM pakiety";

        try {


            Connection conn = ConntectToDB.Connector();
            PreparedStatement  preparedStatement = conn.prepareStatement(selectStmt);
            ResultSet rs = preparedStatement.executeQuery(selectStmt);

            //Send ResultSet to the getContractorList method and get Contractor object
            ObservableList<Usluga> Uslugalist = getUslugaList(rs);
            System.out.print(rs.next());
            //Return Contractor object
            return Uslugalist;
        } catch (Exception e) {
            System.out.println("SQL select operation has been failed: " + e);
            //Return exception
            throw e;

        }

    }

    //-----
    public static ObservableList<Usluga> wyszukajUsluga (String wyrazenie) throws SQLException,
            ClassNotFoundException {
        //Declare a SELECT statement
        String selectStmt = "SELECT DISTINCT * FROM pakiety WHERE nazwa LIKE \"%" + wyrazenie + "%\" OR "
                + "technologia =\""+ wyrazenie+ "\" OR "
                + "predkosc =\"" + wyrazenie +"\" OR "
                + "cena =\""+ wyrazenie +"\" OR "
                + "okres =\""+ wyrazenie + "\" OR ";

               // + "GROUP BY nazwa ";




        //Execute SELECT statement
        try {
            Connection conn = ConntectToDB.Connector();
            PreparedStatement  preparedStatement = conn.prepareStatement(selectStmt);
            ResultSet rs = preparedStatement.executeQuery(selectStmt);

            //Send ResultSet to the getCargoList method and get Cargo object
            ObservableList<Usluga> UslugaList = getUslugaList(rs);

            //Return Cargo object
            return UslugaList;
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
    private static ObservableList<Usluga> getUslugaList(ResultSet rs) throws SQLException, ClassNotFoundException {

        ObservableList<Usluga> uslugaList = FXCollections.observableArrayList();

        while (rs.next()) {
            Usluga usluga = new Usluga();
            usluga.setPakiet_id(rs.getInt("pakiet_id"));
            usluga.setNazwa(rs.getString("nazwa"));
            usluga.setTechnologia(rs.getString("technologia"));
            usluga.setPredkosc(rs.getString("predkosc"));
            usluga.setCena(rs.getString("cena"));
            usluga.setOkres(rs.getString("okres"));
            //Add Cargo to the ObservableList
            uslugaList.add(usluga);
        }
        //return crgList (ObservableList of Contractors)
        return uslugaList;
    }

    /**
     * Method to update user information in database
     *
     * @throws SQLException           - Throw when occurs problem with SQL query
     * @throws ClassNotFoundException - Throws when occurs problem with using
     *                                another class
     */
    public static void updateUsluga( int pakiet_id, String nazwa, String technologia, String predkosc, String
            cena, String okres) throws
            SQLException, ClassNotFoundException {


            String sql="UPDATE pakiety set nazwa=?, technologia=?, predkosc=?,  cena=?,  okres=? WHERE klient_id=?";


            try {
                Connection conn = ConntectToDB.Connector();
                PreparedStatement prepStmt = null;
            prepStmt = conn.prepareStatement(sql);
            prepStmt.setString(1, nazwa);
            prepStmt.setString(2, technologia);
            prepStmt.setString(3, predkosc);
            prepStmt.setString(4, cena);
            prepStmt.setString(5, okres);
            prepStmt.setInt(6, pakiet_id);

            prepStmt.executeUpdate();
                prepStmt.close();
                conn.close();

        } catch (SQLException e) {
            System.err.println("Blad przy aktualizacji danych pakietu" +e);
            throw e;
        }

    }

    /**
     * Method to delete user from database
     *
     * @param pakiet_id - ID of user to delete
     * @throws SQLException - Throws when occurs problem with SQL query
     */
    public static void deleteUserWithId(int pakiet_id) throws SQLException {

        String sql = "DELETE FROM pakiety WHERE pakiet_id = ?";
        Connection conn = ConntectToDB.Connector();
        try {

            PreparedStatement prepStmt = conn.prepareStatement(sql);
            prepStmt.setInt(1, pakiet_id);
            prepStmt.executeUpdate();

        } catch (SQLException e) {
            System.out.print("Błąd podczas usuwania pakietu" + e);
            throw e;
        }

    }

    /**
     * Method to getting data about concrete user
     *
     * @param pakiet_id - ID of user to getting data
     * @return - ResultSet with information about user
     * @throws SQLException - Throws when occurs problem with SQL query
     */

    public static ResultSet wyswietlDaneUsluga(int pakiet_id) throws SQLException {

        String query = "SELECT * FROM pakiety WHERE pakiet_id= ?";


            Connection conn = ConntectToDB.Connector();
            PreparedStatement prepStmt = conn.prepareStatement(query);
            prepStmt.setInt(1, pakiet_id);
             prepStmt.executeQuery();

            return prepStmt.executeQuery();


    }

}
