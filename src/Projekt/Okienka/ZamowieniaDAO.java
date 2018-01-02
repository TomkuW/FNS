package Projekt.Okienka;

import Projekt.Modele.Klient;
import Projekt.Modele.Pracownik;
import Projekt.Modele.Usluga;
import Projekt.Modele.Zamowienie;
import Projekt.PodlaczonieDoBazy.ConntectToDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;

/**
 * Created by Tomek on 2017-12-13.
 */
public class ZamowieniaDAO {



    public static ObservableList<Zamowienie> pokazZamowienie () throws SQLException, ClassNotFoundException {
        //Declare a SELECT statement
        String selectStmt = "SELECT z.zamowienia_id,z.klient_id, k.imie,k.nazwisko,k.ulica,k.nr_dom, k.nr_telefon, k" +
                ".miejscowosc, u.predkosc, u.technologia, " +
                "z.pakiet_id, u.nazwa, z.pracownik_id, p.imie, p.nazwisko, z.umowa_od, z.umowa_do, z.status"
                + " from zamowienia z,klienci k,pracownicy p, pakiety u WHERE k.klient_id = z.klient_id AND u" +
                ".pakiet_id = z.pakiet_id and p.pracownik_id = z.pracownik_id ORDER BY z.zamowienia_id";

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
     * Method to get data about order with "New" state
     *
     * @return List of objects
     * @throws SQLException - Throws when occurs problem with SQL query
     * @throws ClassNotFoundException - Throws when occurs problem with another class
     */
    public static ObservableList<Zamowienie> wyszukajZamowienie (String wyrazenie) throws SQLException, ClassNotFoundException {
        //Declare a SELECT statement

        String selectStmt = "SELECT z.zamowienia_id,z.klient_id, k.imie,k.nazwisko,k.ulica, k.miejscowosc," +
                "z.pakiet_id, u.nazwa, z.pracownik_id, p.imie, p.nazwisko, z.umowa_od, z.umowa_do, z.status"
                + " FROM zamowienia z,klienci k,pracownicy p, pakiety u WHERE k.klient_id = z.klient_id AND u" +
                ".pakiet_id = z.pakiet_id and p.pracownik_id = z.pracownik_id" +
                " AND zamowienia_id LIKE \"%"+ wyrazenie +"%\"";




        //Execute SELECT statement
        try {
            Connection conn = ConntectToDB.Connector();
            PreparedStatement preparedStatement = conn.prepareStatement(selectStmt);
            ResultSet rs = preparedStatement.executeQuery(selectStmt);

            //Send ResultSet to the getContractorList method and get Contractor object
            ObservableList<Zamowienie> ZamowienList1 = getZamowienList(rs);

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
    private static ObservableList<Zamowienie> getZamowienList(ResultSet rs) throws SQLException, ClassNotFoundException {
        //Declare a observable List which comprises of Contractor objects
        ObservableList<Zamowienie> List = FXCollections.observableArrayList();

        while (rs.next()) {
            Zamowienie z = new Zamowienie();
            z.setZamowienia_id(rs.getInt("zamowienia_id"));
            z.setKlient_id(rs.getInt("klient_id"));
            z.setImieKZ(rs.getString("klienci.imie"));
            z.setNazwiskoKZ(rs.getString("klienci.nazwisko"));
            z.setUlicaKZ(rs.getString("klienci.ulica"));
            z.setNr_telefonKZ(rs.getString("klienci.nr_dom"));
            z.setNr_domKZ(rs.getString("klienci.nr_telefon"));
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
            //Add Cargo to the ObservableList
            List.add(z);
        }
        //return crgList (ObservableList of Contractors)
        return List;
    }

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

      public static ResultSet wyswietlDaneZamowienia( int zamowienia_id) throws SQLException {

        String query = "SELECT * FROM zamowienia WHERE zamowienia_id= ?";


        Connection conn = ConntectToDB.Connector();
        PreparedStatement prepStmt = conn.prepareStatement(query);
        prepStmt.setInt(1, zamowienia_id);
        prepStmt.executeQuery();

        return prepStmt.executeQuery();


    }
        public static void updateZamowienie(int zamowienia_id,String status, LocalDate
            umowa_od, LocalDate umowa_do) throws
            SQLException, ClassNotFoundException {


        String sql="UPDATE zamowienia set status=?, umowa_od=?, umowa_do=? " +
                " WHERE zamowienia_id=?";


        try {
            Connection conn = ConntectToDB.Connector();
            PreparedStatement prepStmt = null;
            prepStmt = conn.prepareStatement(sql);
            prepStmt.setString(1,status);
            prepStmt.setDate(2, Date.valueOf(umowa_od));
            prepStmt.setDate(3, Date.valueOf(umowa_do));

            prepStmt.setInt(4,zamowienia_id);

            prepStmt.executeUpdate();
            prepStmt.close();
            conn.close();

        } catch (SQLException e) {
            System.err.println("Blad przy aktualizacji danych klienta" +e);
            throw e;
        }
pokazZamowienie();
    }


    //-----------------new dao --/>


    //-----------------------------Do obslugi tabelek malych

    public static ObservableList<Pracownik> pokazPracownik() throws SQLException, ClassNotFoundException {

        String selectStmt = "SELECT * FROM pracownicy WHERE typ_pracownika = 'Wykonawca'";

        try {


            Connection conn = ConntectToDB.Connector();
            PreparedStatement preparedStatement = conn.prepareStatement(selectStmt);
            ResultSet rs = preparedStatement.executeQuery(selectStmt);

            //Send ResultSet to the getContractorList method and get Contractor object
            ObservableList<Pracownik> Pracownikist = getPracownikList(rs);
            System.out.print(rs.next());
            //Return Contractor object
            return Pracownikist;
        } catch (Exception e) {
            System.out.println("SQL select operation has been failed: " + e);
            //Return exception
            throw e;

        }

    }
    //-----
    public static ObservableList<Pracownik> wyszukajPracownik (String wyrazenie) throws SQLException,
            ClassNotFoundException {
        //Declare a SELECT statement
        String selectStmt = "SELECT * FROM pracownicy WHERE typ_pracownika ='Wykonawca'"
               +"AND imie LIKE\"%" +wyrazenie + "%\" OR "
               + "nazwisko LIKE \""+ wyrazenie + "\"";

        //Execute SELECT statement
        try {
            Connection conn = ConntectToDB.Connector();
            PreparedStatement  preparedStatement = conn.prepareStatement(selectStmt);
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
            pracownik.setNr_domu(rs.getString("nr_domu"));
            pracownik.setMiejscowosc(rs.getString("miejscowosc"));
            pracownik.setEmail(rs.getString("email"));
            pracownik.setNr_telefon(rs.getString("nr_telefon"));
            pracownik.setWynagrodzenie(rs.getInt("wynagrodzenie"));
            pracownik.setLogin(rs.getString("login"));
            pracownik.setData_zatrudnienia(rs.getDate("data_zatrudnienia"));
            pracownik.setHaslo(rs.getString("haslo"));
            pracownik.setTyp_pracownika(rs.getString("typ_pracownika"));
            //Add Cargo to the ObservableList
            pracownikList.add(pracownik);
        }
        //return crgList (ObservableList of Contractors)
        return pracownikList;
    }
//--------------------------------------------------------------------------------------------
    public static ObservableList<Usluga> pokazUsluga() throws SQLException, ClassNotFoundException {

        String selectStmt = "SELECT * FROM pakiety";

        try {


            Connection conn = ConntectToDB.Connector();
            PreparedStatement preparedStatement = conn.prepareStatement(selectStmt);
            ResultSet rs = preparedStatement.executeQuery(selectStmt);

            //Send ResultSet to the getContractorList method and get Contractor object
            ObservableList<Usluga> UslugaList = getUslugaList(rs);
            System.out.print(rs.next());
            //Return Contractor object
            return UslugaList;
        } catch (Exception e) {
            System.out.println("SQL select operation has been failed: " + e);
            //Return exception
            throw e;

        }

    }

    public static ObservableList<Usluga> wyszukajUsluga (String wyrazenie) throws SQLException,
            ClassNotFoundException {
        //Declare a SELECT statement
        String selectStmt = "SELECT * FROM pakiety WHERE nazwa LIKE \"%" + wyrazenie + "%\" OR "
                + "technologia =\""+wyrazenie + "\" OR "
                + "predkosc =\""+ wyrazenie+ "\" OR "
                + "cena = \"%" + wyrazenie +"%\" OR "
                + "okres = \"%"+ wyrazenie +"%\"";




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

            //Add Cargo to the ObservableList
            uslugaList.add(usluga);
        }
        //return crgList (ObservableList of Contractors)
        return uslugaList;
    }
    //---------------------------------------
    public static ObservableList<Klient> pokazKlient() throws SQLException, ClassNotFoundException {

        String selectStmt = "SELECT * FROM klienci";

        try {


            Connection conn = ConntectToDB.Connector();
            PreparedStatement  preparedStatement = conn.prepareStatement(selectStmt);
            ResultSet rs = preparedStatement.executeQuery(selectStmt);

            //Send ResultSet to the getContractorList method and get Contractor object
            ObservableList<Klient> KlientList = getKlientList(rs);
            System.out.print(rs.next());
            //Return Contractor object
            return KlientList;
        } catch (Exception e) {
            System.out.println("SQL select operation has been failed: " + e);
            //Return exception
            throw e;

        }

    }

    //-----
    public static ObservableList<Klient> wyszukajKlient (String wyrazenie) throws SQLException,
            ClassNotFoundException {
        //Declare a SELECT statement
        String selectStmt = "SELECT DISTINCT * FROM klienci WHERE imie LIKE \"%" + wyrazenie + "%\" OR "
                + "nazwisko =\""+wyrazenie + "\" OR "
                + "PESEL =\""+ wyrazenie+ "\" OR "
                + "miejscowosc LIKE \"%" + wyrazenie +"%\" OR "
                + "ulica LIKE \"%"+ wyrazenie +"%\" OR "
                + "nr_dom LIKE \"%"+ wyrazenie + "%\" OR "
                + "nr_telefon LIKE \"%"+ wyrazenie + "%\" "
                + "GROUP BY imie ";




        //Execute SELECT statement
        try {
            Connection conn = ConntectToDB.Connector();
            PreparedStatement  preparedStatement = conn.prepareStatement(selectStmt);
            ResultSet rs = preparedStatement.executeQuery(selectStmt);

            //Send ResultSet to the getCargoList method and get Cargo object
            ObservableList<Klient> KlientList = getKlientList(rs);

            //Return Cargo object
            return KlientList;
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
            //Add Cargo to the ObservableList
            klientList.add(klient);
        }
        //return crgList (ObservableList of Contractors)
        return klientList;
    }
}
