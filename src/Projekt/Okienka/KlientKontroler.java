package Projekt.Okienka;

import Projekt.ConntectToDB;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Created by Tomek on 2017-12-13.
 */
public class KlientKontroler implements Initializable {

    private Connection conn = null;
    private PreparedStatement stmt=null;


    @FXML
    private TextField imie_k;
    @FXML
    private TextField nazwisko_k;
    @FXML
    private TextField pesel_k;
    @FXML
    private TextField miejscowosc_k;
    @FXML
    private TextField ulica_k;
    @FXML
    private TextField nr_dom_k;
    @FXML
    private TextField nr_tel_k;
    @FXML
    private TableColumn<Klient, String> imieColumn;
    @FXML
    private TableColumn<Klient, String> nazwiskoColumn;
    @FXML
    private TableColumn<Klient, String> peselColumn;
    @FXML
    private TableColumn<Klient, String> miejscowoscColumn;
    @FXML
    private TableColumn<Klient, String> ulicaColumn;
    @FXML
    private TableColumn<Klient, String> nrdomColumn;
    @FXML
    private TableColumn<Klient, String> nrtelefonuColumn;
    @FXML
    private TableView klientTable;
    @FXML
    private TextField poleszukania;
    @FXML
    private static int wybierzKlient_id;

    public static int getSelectedKlientId() {
        return wybierzKlient_id;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        conn = ConntectToDB.Connector();
        imieColumn.setCellValueFactory(cellData -> cellData.getValue().imieProperty());
        nazwiskoColumn.setCellValueFactory(cellData -> cellData.getValue().nazwiskoProperty());
        peselColumn.setCellValueFactory(cellData -> cellData.getValue().PESELProperty());
        miejscowoscColumn.setCellValueFactory(cellData -> cellData.getValue().miejscowoscProperty());
        ulicaColumn.setCellValueFactory(cellData -> cellData.getValue().ulicaProperty());
        nrdomColumn.setCellValueFactory(cellData -> cellData.getValue().nr_domProperty());
        nrtelefonuColumn.setCellValueFactory(cellData -> cellData.getValue().nr_telefonProperty());
        klientTable.getSelectionModel().getSelectedItems().addListener(new ListChangeListener<Klient>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends Klient> c) {
                for (Klient u : c.getList()) {
                    wybierzKlient_id = u.getKlient_id();
                }
            }
        });

    }

    @FXML
    private void pokazKlient() throws SQLException, ClassNotFoundException {
        try {
            //Get all Contractorsinformation
            ObservableList<Klient> UserData = KlientDAO.pokazKlient();
            //Populate Contractors on TableView
            miejsceWTabeliKlienta(UserData);

        } catch (SQLException e) {
            System.out.println("Error occurred while getting Contractors information from DB.\n" + e);
            throw e;
        }
    }

    //-----------------------------
    @FXML
    private void szukajKlienta() throws SQLException, ClassNotFoundException {
        try {
            String pharse = poleszukania.getText();

            ObservableList<Klient> Klientdata = KlientDAO.wyszukajKlient(pharse);
            //Populate Employees on TableView
            miejsceWTabeliKlienta(Klientdata);
        } catch (SQLException e) {
            System.out.println("Error occurred while getting cargos information from DB.\n" + e);
            throw e;
        }
    }
    //------------------

    @FXML
    private void miejsceWTabeliKlienta(ObservableList<Klient> usrData) {
        //Set items to the ContractorTable
        klientTable.setItems(usrData);
    }

    @FXML
    public void openPotwierdzenieUsuwaniaUsera() throws Exception {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("OkienkaFNS - Usun_klienta.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Usuwanie klienta");
            stage.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
        }
        pokazKlient();
    }



    @FXML
    public void openEdycjaDodawanieUsera() throws Exception {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("OkienkaFNS - Dodaj_klienta.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
        }
        pokazKlient();
    }
    @FXML
    public void openEdycjaModyfikacjaUsera() throws Exception {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("OkienkaFNS - Modyfikuj_klienta.fxml"));

            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.showAndWait();



        } catch (Exception e) {
            e.printStackTrace();
        }
        pokazKlient();
    }

}