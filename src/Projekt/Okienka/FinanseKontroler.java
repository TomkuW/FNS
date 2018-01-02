package Projekt.Okienka;

import Projekt.Modele.Pracownik;
import Projekt.Modele.Zamowienie;
import Projekt.PodlaczonieDoBazy.ConntectToDB;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.*;
import java.net.URL;
import java.sql.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.ResourceBundle;

/**
 * Created by Tomek on 2017-12-16.
 */
public class FinanseKontroler implements Initializable {


    Connection conn = null;
    PreparedStatement stmt = null;

    @FXML
    private TableColumn<Zamowienie, Integer> pakietIdZ_col;

    @FXML
    private TableColumn<Zamowienie, String> nazwaPakietZ_col;
    @FXML
    private TableColumn<Zamowienie, Double> cena_col;
    @FXML
    private TableColumn<Zamowienie, Integer> ilosc_col;
    @FXML
    private TableColumn<Zamowienie, Date> data_od_col;
    @FXML
    private TableColumn<Zamowienie, Date> data_do_col;
    @FXML
    private TableColumn<Zamowienie, Double> cenazbiorcza_col;
    @FXML
    private Button raport1;
    @FXML
    private Button raport2;

    @FXML
    private TableView finanseuslugiTable;
    @FXML
    private TextField poleszukaj;
    @FXML
    private static int wybierzFinasnseUslugiId;

    @FXML
    private Label labelid;
    //-----------pracownik

    @FXML
    private TableColumn<Pracownik, String> imieColumn;
    @FXML
    private TableColumn<Pracownik, String> nazwiskoColumn;
    @FXML
    private TableColumn<Pracownik, String> zawodColumn;
    @FXML
    private TableColumn<Pracownik, String> ulicaColumn;
    @FXML
    private TableColumn<Pracownik, String> nr_domuColumn;
    @FXML
    private TableColumn<Pracownik, String> miejscowoscColumn;
    @FXML
    private TableColumn<Pracownik, Double> wynagrodzenieColumn;
    @FXML
    private TableColumn<Pracownik, Double> wynagrodzenieRoczneColumn;

    @FXML
    private TableView finansePracownikaTable;
    @FXML
    private TextField poleSzukaniaPraconika;
    @FXML
    private static int wybierzFinansePracownikId;

    public static int getSelectedPracownikId() {
        return wybierzFinansePracownikId;
    }


    @FXML
    private void pokazFinansePracownik() throws SQLException, ClassNotFoundException {
        try {
            //Get all Contractorsinformation
            ObservableList<Pracownik> PracownikData = FinanseDAO.pokazFinansePracownik();
            //Populate Contractors on TableView
            miejsceWTabeliFinansePracownika(PracownikData);

        } catch (SQLException e) {
            System.out.println("Error occurred while getting Contractors information from DB.\n" + e);
            throw e;
        }
    }

    // -----------------------------
    @FXML
    private void szukajFinansePracownik() throws SQLException, ClassNotFoundException {
        try {
            String pharse = poleSzukaniaPraconika.getText();

            ObservableList<Pracownik> Pracownikdata = FinanseDAO.wyszukajPracownikFinanse(pharse);
            //Populate Employees on TableView
            miejsceWTabeliFinansePracownika(Pracownikdata);
        } catch (SQLException e) {
            System.out.println("Error occurred while getting cargos information from DB.\n" + e);
            throw e;
        }
    }
    //  ------------------

    @FXML
    private void miejsceWTabeliFinansePracownika(ObservableList<Pracownik> prcData) {
        //Set items to the ContractorTable
        finansePracownikaTable.setItems(prcData);
    }


    public static int getSelectedFinansUslugiId() {
        return wybierzFinasnseUslugiId;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        conn = ConntectToDB.Connector();
        pakietIdZ_col.setCellValueFactory(cellData -> cellData.getValue().pakiet_idProperty().asObject());
        cena_col.setCellValueFactory(cellData -> cellData.getValue().cenaUProperty().asObject());
        nazwaPakietZ_col.setCellValueFactory(cellData -> cellData.getValue().nazwaPakietZProperty());
        data_od_col.setCellValueFactory(cellData -> cellData.getValue().umowa_odProperty());
        data_do_col.setCellValueFactory(cellData -> cellData.getValue().umowa_doProperty());
        cenazbiorcza_col.setCellValueFactory(cellData -> cellData.getValue().cenaZbUProperty().asObject());
        ilosc_col.setCellValueFactory(cellData -> cellData.getValue().iloscUProperty().asObject());


        finanseuslugiTable.getSelectionModel().getSelectedItems().addListener(new ListChangeListener<Zamowienie>() {
            public void onChanged(ListChangeListener.Change<? extends Zamowienie> c) {

                for (Zamowienie o : c.getList()) {
                    wybierzFinasnseUslugiId = o.getZamowienia_id();

                }

            }
        });
        conn = ConntectToDB.Connector();
        imieColumn.setCellValueFactory(cellData -> cellData.getValue().imieProperty());
        nazwiskoColumn.setCellValueFactory(cellData -> cellData.getValue().nazwiskoProperty());
        zawodColumn.setCellValueFactory(cellData -> cellData.getValue().zawodProperty());
        ulicaColumn.setCellValueFactory(cellData -> cellData.getValue().ulicaProperty());
        nr_domuColumn.setCellValueFactory(cellData -> cellData.getValue().nr_domuProperty());
        miejscowoscColumn.setCellValueFactory(cellData -> cellData.getValue().miejscowoscProperty());
        wynagrodzenieColumn.setCellValueFactory(cellData -> cellData.getValue().wynagrodzenieProperty().asObject());
        wynagrodzenieRoczneColumn.setCellValueFactory(cellData -> cellData.getValue().wynagrodzenieRoczneProperty().asObject());

        finansePracownikaTable.getSelectionModel().getSelectedItems().addListener(new ListChangeListener<Pracownik>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends Pracownik> c) {
                for (Pracownik u : c.getList()) {
                    wybierzFinansePracownikId = u.getPracownik_id();
                }
            }
        });
    }


    @FXML
    private void pokazAllFinanse() throws SQLException, ClassNotFoundException {
        try {
            //Get all Contractorsinformation
            ObservableList<Zamowienie> ZamowienieData = FinanseDAO.pokazFinanseUslugi();
            ObservableList<Pracownik> PracownikData = FinanseDAO.pokazFinansePracownik();

            //Populate Contractors on TableView
            miejsceWTabeliFinanseUslugi(ZamowienieData);
            miejsceWTabeliFinansePracownika(PracownikData);

        } catch (SQLException e) {
            System.out.println("Error occurred while getting Contractors information from DB.\n" + e);
            throw e;
        }
    }

    @FXML
    private void szukajFinanseUslugi() throws SQLException, ClassNotFoundException {
        try {
            String pharse = poleszukaj.getText();
            //Get all Contractorsinformation
            ObservableList<Zamowienie> ZamowienieData = FinanseDAO.wyszukajFinansePakiet(pharse);
            //Populate Contractors on TableView
            miejsceWTabeliFinanseUslugi(ZamowienieData);

        } catch (SQLException e) {
            System.out.println("Error occurred while getting Contractors information from DB.\n" + e);
            throw e;
        }
    }

    /**
     * Method to show values in table
     *
     * @param rptData - List of objects to show in table
     */
    @FXML
    private void miejsceWTabeliFinanseUslugi(ObservableList<Zamowienie> rptData) {
        //Set items to the ContractorTable
        finanseuslugiTable.setItems(rptData);
    }


    /**
     * Method to create reports according to value on comboBox
     *
     * @throws SQLException - Throws when occurs problem with SQL query
     * @throws IOException - Throws when can't create new file with report
     * @see WPDF
     */

    @FXML
    public void tworzPDF() throws SQLException, IOException {
        try {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDateTime date = LocalDateTime.now();
            String currentDate = formatter.format(date);
            String date1 = formatter.format(LocalDate.now().minusYears(1));
            String date2 = formatter.format(LocalDate.now());
            String[] dates = {date1, date2, currentDate};

            ResultSet rs = ConntectToDB.getData("SELECT z.pakiet_id, u.nazwa, z.umowa_od, COUNT" +
                    "(u.pakiet_id) as ilosc, u.cena" +
                    " from zamowienia z, pakiety u WHERE z.umowa_od BETWEEN '" + dates[0] + "' AND '" +
                    dates[1] + "' AND u.pakiet_id = z.pakiet_id GROUP BY pakiet_id");

            WPDF edc = new WPDF(dates, ConntectToDB.getCurrentUser(), rs);
            edc.create();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void tworzPDFx() throws SQLException, IOException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDateTime date = LocalDateTime.now();
            String currentDate = formatter.format(date);
            String date1 = formatter.format(LocalDate.now().minusMonths(1));
            String date2 = formatter.format(LocalDate.now());
            String[] dates = {date1, date2, currentDate};


            ResultSet rs1 = ConntectToDB.getData("SELECT pracownik_id, imie, nazwisko, ulica, nr_domu, " +
                    "miejscowosc, " +
                    "wynagrodzenie, (wynagrodzenie * 0.1371) as skladki" +
                    " from pracownicy");

            WxPDF edc = new WxPDF(dates, ConntectToDB.getCurrentUser(), rs1);
            edc.create();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}

