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
 * Created by Fabian on 2017-12-21.
 */


public class UslugaKontroler implements Initializable {

    @FXML
    private static int wybierzUsluga_id;
    private Connection conn = null;
    private PreparedStatement stmt = null;

    @FXML
    private TableColumn<Usluga, String> nazwaColumn;
    @FXML
    private TableColumn<Usluga, String> technologiaColumn;
    @FXML
    private TableColumn<Usluga, String> predkoscColumn;
    @FXML
    private TableColumn<Usluga, String> cenaColumn;
    @FXML
    private TableColumn<Usluga, String> okresColumn;
    @FXML
    private TableView uslugaTable;
    @FXML
    private TextField poleszukania;
    @FXML
    public static int getSelectedUslugaId() {
        return wybierzUsluga_id;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        conn = ConntectToDB.Connector();

        nazwaColumn.setCellValueFactory(cellData -> cellData.getValue().nazwaProperty());
        technologiaColumn.setCellValueFactory(cellData -> cellData.getValue().technologiaProperty());
        predkoscColumn.setCellValueFactory(cellData -> cellData.getValue().predkoscProperty());
        cenaColumn.setCellValueFactory(cellData -> cellData.getValue().cenaProperty());
        okresColumn.setCellValueFactory(cellData -> cellData.getValue().okresProperty());

        uslugaTable.getSelectionModel().getSelectedItems().addListener(new ListChangeListener<Usluga>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends Usluga> c) {
                for (Usluga u : c.getList()) {
                    wybierzUsluga_id = u.getPakiet_id();
                }
            }
        });

    }

    @FXML
    private void pokazUsluga() throws SQLException, ClassNotFoundException {
        try {
            //Get all Contractorsinformation
            ObservableList<Usluga> UslugaData = UslugaDAO.pokazUsluga();
            //Populate Contractors on TableView
            miejsceWTabeliUsluga(UslugaData);

        } catch (SQLException e) {
            System.out.println("Error occurred while getting Contractors information from DB.\n" + e);
            throw e;
        }
    }

    //-----------------------------
    @FXML
    private void szukajUsluga() throws SQLException, ClassNotFoundException {
        try {
            String pharse = poleszukania.getText();

            ObservableList<Usluga> Uslugadata = UslugaDAO.wyszukajUsluga(pharse);
            //Populate Employees on TableView
            miejsceWTabeliUsluga(Uslugadata);
        } catch (SQLException e) {
            System.out.println("Error occurred while getting cargos information from DB.\n" + e);
            throw e;
        }
    }
    //------------------

    @FXML
    private void miejsceWTabeliUsluga(ObservableList<Usluga> usrData) {
        //Set items to the ContractorTable
        uslugaTable.setItems(usrData);
    }

    @FXML
    public void openPotwierdzenieUsuwaniaUsera() throws Exception {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("OkienkaFNS - Usun_usługę.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Usuwanie uslugi");
            stage.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();

        }
        pokazUsluga();

    }


    @FXML
    public void openEdycjaDodawanieUsera() throws Exception {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("OkienkaFNS - dodaj_usługę.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
        }
        pokazUsluga();
    }

}
