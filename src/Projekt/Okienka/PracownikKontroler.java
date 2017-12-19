package Projekt.Okienka;

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
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Created by Kamil on 2017-12-06.
 */


public class PracownikKontroler implements Initializable {

    @FXML
    private static int wybierzPracownik_id;
    private Connection conn = null;
    private PreparedStatement stmt = null;
    @FXML
    private TableColumn<Pracownik, String> imieColumn;
    @FXML
    private TableColumn<Pracownik, String> nazwiskoColumn;
    @FXML
    private TableColumn<Pracownik, String> zawodColumn;
    @FXML
    private TableColumn<Pracownik, String> peselColumn;
    @FXML
    private TableColumn<Pracownik, String> ulicaColumn;
    @FXML
    private TableColumn<Pracownik, String> nr_domuColumn;
    @FXML
    private TableColumn<Pracownik, String> miejscowoscColumn;
    @FXML
    private TableColumn<Pracownik, String> emailColumn;
    @FXML
    private TableColumn<Pracownik, String> nr_telefonColumn;
    @FXML
    private TableColumn<Pracownik, String> wynagrodzenieColumn;
    @FXML
    private TableColumn<Pracownik, Date> data_zatrudnieniaColumn;
    @FXML
    private TableView pracownikTable;
    @FXML
    private TextField poleszukania;

    public static int getSelectedPracownikId() {
        return wybierzPracownik_id;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        conn = ConntectToDB.Connector();
        imieColumn.setCellValueFactory(cellData -> cellData.getValue().imieProperty());
        nazwiskoColumn.setCellValueFactory(cellData -> cellData.getValue().nazwiskoProperty());
        zawodColumn.setCellValueFactory(cellData -> cellData.getValue().zawodProperty());
        peselColumn.setCellValueFactory(cellData -> cellData.getValue().PESELProperty());
        ulicaColumn.setCellValueFactory(cellData -> cellData.getValue().ulicaProperty());
        nr_domuColumn.setCellValueFactory(cellData -> cellData.getValue().nr_domProperty());
        miejscowoscColumn.setCellValueFactory(cellData -> cellData.getValue().miejscowoscProperty());
        emailColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        nr_telefonColumn.setCellValueFactory(cellData -> cellData.getValue().nr_telefonProperty());
        wynagrodzenieColumn.setCellValueFactory(cellData -> cellData.getValue().wynagrodzenieProperty());
        data_zatrudnieniaColumn.setCellValueFactory(cellData -> cellData.getValue().data_zatrudnieniaProperty());
        pracownikTable.getSelectionModel().getSelectedItems().addListener(new ListChangeListener<Pracownik>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends Pracownik> c) {
                for (Pracownik u : c.getList()) {
                    wybierzPracownik_id = u.getPracownik_id();
                }
            }
        });

    }

    @FXML
    private void pokazPracownik() throws SQLException, ClassNotFoundException {
        try {
            //Get all Contractorsinformation
            ObservableList<Pracownik> PracownikData = PracownikDAO.pokazPracownik();
            //Populate Contractors on TableView
            miejsceWTabeliPracownika(PracownikData);

        } catch (SQLException e) {
            System.out.println("Error occurred while getting Contractors information from DB.\n" + e);
            throw e;
        }
    }

    //-----------------------------
    @FXML
    private void szukajPracownika() throws SQLException, ClassNotFoundException {
        try {
            String pharse = poleszukania.getText();

            ObservableList<Pracownik> Pracownikdata = PracownikDAO.wyszukajPracownik(pharse);
            //Populate Employees on TableView
            miejsceWTabeliPracownika(Pracownikdata);
        } catch (SQLException e) {
            System.out.println("Error occurred while getting cargos information from DB.\n" + e);
            throw e;
        }
    }
    //------------------

    @FXML
    private void miejsceWTabeliPracownika(ObservableList<Pracownik> prcData) {
        //Set items to the ContractorTable
        pracownikTable.setItems(prcData);
    }


    @FXML
    public void openPotwierdzenieUsuwaniaUsera() throws Exception {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PotwierdzenieUsuwaniaPracownika.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Usuwanie pracownika");
            stage.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
        }
        pokazPracownik();
    }


    @FXML
    public void openEdycjaDodawanieUsera() throws Exception {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("OkienkaFNS-dodaj_pracownika.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
        }
        pokazPracownik();
    }

    @FXML
    public void openEdycjaModyfikacjaUsera() throws Exception {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("OkienkaFNS-modyfikuj_pracownika.fxml"));

            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
        }
        pokazPracownik();
    }

}
