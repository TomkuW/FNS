package Projekt.Okienka;


import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import java.io.File;
import java.io.FileWriter;
import java.net.URL;
import java.sql.*;
import java.sql.Date;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;


//-


/**
 * Created by Tomek on 2017-12-16.
 */
public class ZamowieniaKontroler implements Initializable {


    private Connection conn = null;
    private PreparedStatement stmt = null;

    @FXML
    private TableColumn<Zamowienie, Integer> zamowienie_id_col;
    @FXML
    private TableColumn<Zamowienie, Integer> klient_id_col;
    @FXML
    private TableColumn<Zamowienie, String> imieKZ_col;
    @FXML
    private TableColumn<Zamowienie, String> nazwiskoKZ_col;
    @FXML
    private TableColumn<Zamowienie, String> miejscowoscKZ_col;
    @FXML
    private TableColumn<Zamowienie, String> ulicaKZ_col;
    @FXML
    private TableColumn<Zamowienie, String> nazwaPakietZ_col;
    @FXML
    private TableColumn<Zamowienie, String> pracownikImieZ_col;
    @FXML
    private TableColumn<Zamowienie, String> pracownikNazwiskoZ_col;
    @FXML
    private TableColumn<Zamowienie, Integer> pakiet_id_col;
    @FXML
    private TableColumn<Zamowienie, Integer> pracownik_id_col;

    @FXML
    private TableColumn<Zamowienie, Date> data_od_col;
    @FXML
    private TableColumn<Zamowienie, Date> data_do_col;
    @FXML
    private TableColumn<Zamowienie, String> status_col;
    @FXML
    private TableView<Zamowienie> zamowieniaTable = new TableView<>();
    @FXML
    private TextField poleszukania4;
    @FXML
    private static int wybierzZamowienieId;
    @FXML
    private Button buton1;


    public static int getSelectedZamowienieId() {
        return wybierzZamowienieId;
    }

    /**
     * Method to clear selected value ID
     */
    public static void czyscID() {
        wybierzZamowienieId = 0;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        zamowienie_id_col.setCellValueFactory(cellData -> cellData.getValue().zamowienia_idProperty().asObject());
        klient_id_col.setCellValueFactory(cellData -> cellData.getValue().klient_idProperty().asObject());
        imieKZ_col.setCellValueFactory(cellData -> cellData.getValue().imieKZProperty());
        nazwiskoKZ_col.setCellValueFactory(cellData -> cellData.getValue().nazwiskoKZProperty());
        miejscowoscKZ_col.setCellValueFactory(cellData -> cellData.getValue().miejscowoscKZProperty());
        ulicaKZ_col.setCellValueFactory(cellData -> cellData.getValue().ulicaKZProperty());
        pakiet_id_col.setCellValueFactory(cellData -> cellData.getValue().pakiet_idProperty().asObject());
        nazwaPakietZ_col.setCellValueFactory(cellData -> cellData.getValue().nazwaPakietZProperty());
        pracownik_id_col.setCellValueFactory(cellData -> cellData.getValue().pracownik_idProperty().asObject());
        pracownikImieZ_col.setCellValueFactory(cellData -> cellData.getValue().pracownikImieZProperty());
        pracownikNazwiskoZ_col.setCellValueFactory(cellData -> cellData.getValue().pracownikNazwiskoZProperty());
        data_od_col.setCellValueFactory(cellData -> cellData.getValue().umowa_odProperty());
        data_do_col.setCellValueFactory(cellData -> cellData.getValue().umowa_doProperty());
        status_col.setCellValueFactory(cellData -> cellData.getValue().statusProperty());
        zamowieniaTable.getSelectionModel().getSelectedItems().addListener(new ListChangeListener<Zamowienie>() {
            public void onChanged(ListChangeListener.Change<? extends Zamowienie> c) {

                for (Zamowienie o : c.getList()) {
                    wybierzZamowienieId = o.getZamowienia_id();

                }

            }
        });

        setValueLabel();
    }

    @FXML
    private void pokazZamowienie() throws SQLException, ClassNotFoundException {
        try {
            //Get all Contractorsinformation
            ObservableList<Zamowienie> ZamowienieData = ZamowieniaDAO.pokazZamowienie();
            //Populate Contractors on TableView
            miejsceWTabeliZamowienia(ZamowienieData);


        } catch (SQLException e) {
            System.out.println("Error occurred while getting Contractors information from DB.\n" + e);
            throw e;
        }
    }

    @FXML
    private void szukajZamowienie() throws SQLException, ClassNotFoundException {
        try {
            String pharse = poleszukania4.getText();
            //Get all Contractorsinformation
            ObservableList<Zamowienie> ZamowienieData = ZamowieniaDAO.wyszukajZamowienie(pharse);
            //Populate Contractors on TableView
            miejsceWTabeliZamowienia(ZamowienieData);
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
    private void miejsceWTabeliZamowienia(ObservableList<Zamowienie> rptData) {
        //Set items to the ContractorTable
        zamowieniaTable.setItems(rptData);
    }

    /**
     * Method to open OrderWindow.fxml
     *
     * @throws Exception - To show information about unexpected error
     */
    @FXML
    public void openPotwierdzenieUsuwaniaZamowienia() throws Exception {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PotwierdzenieUsuwaniaZamowienia.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Usuwanie zamówienia");
            stage.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
        }
        pokazZamowienie();
    }

    @FXML
    public void openEdycjaModyfikacjaZamowienia() throws Exception {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("OkienkaFNS - modyfikuj_zamowienie.fxml"));

            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.showAndWait();
            stage.setTitle("Modyfikacja zamowienia");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


//    private Connection conn = null;
//    private PreparedStatement stmt = null;
//
//    @FXML
//    private TableColumn<Zamowienie, Integer> zamowienie_id_col;
//    @FXML
//    private TableColumn<Zamowienie, Integer> klient_id_col;
//    @FXML
//    private TableColumn<Zamowienie, Integer> pakiet_id_col;
//    @FXML
//    private TableColumn<Zamowienie, Integer> pracownik_id_col;
//    @FXML
//    private TableColumn<Zamowienie, Date> data_od_col;
//    @FXML
//    private TableColumn<Zamowienie, Date> data_do_col;
//    @FXML
//    private TableColumn<Zamowienie, String> status_col;
//
//
//    @FXML
//    private TableView<Zamowienie> zamowienieTable = new TableView<>();
//
//    @FXML
//    private TextField poleszukania4;
//    @FXML
//    private static int wybierzZamowienie_id;
//
//
//    public static int getSelectedZamowienieId(){ return wybierzZamowienie_id;}
//
//    @FXML
//    private void szukajZamowienie() throws SQLException, ClassNotFoundException {
//        try {
//            String pharse = poleszukania4.getText();
//
//            ObservableList<Zamowienie> Zamowieniedata = ZamowieniaDAO.wyszukajZamowienie(pharse);
//            //Populate Employees on TableView
//            miejsceWTabeliZamowienie(Zamowieniedata);
//        } catch (SQLException e) {
//            System.out.println("Error occurred while getting cargos information from DB.\n" + e);
//            throw e;
//        }
//    }
//    //------------------
//
//    @FXML
//    private void miejsceWTabeliZamowienie(ObservableList<Zamowienie> prcData) {
//        //Set items to the ContractorTable
//        zamowienieTable.setItems(prcData);
//
//    }
//
//    @FXML
//    private void pokazZamowienie() throws SQLException, ClassNotFoundException {
//        try {
//            //Get all Contractorsinformation
//            ObservableList<Zamowienie> ZamowienieData = ZamowieniaDAO.pokazZamowienie();
//
//
//            //Populate Contractors on TableView
//            miejsceWTabeliZamowienie(ZamowienieData);
//
//
//
//        } catch (SQLException e) {
//            System.out.println("Error occurred while getting Contractors information from DB.\n" + e);
//            throw e;
//        }
//    }
//
//    @FXML
//    public void openPotwierdzenieUsuwaniaZamowienia() throws Exception {
//        try {
//            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PotwierdzenieUsuwaniaPracownik.fxml"));
//            Parent root = (Parent) fxmlLoader.load();
//            Stage stage = new Stage();
//            stage.setScene(new Scene(root));
//            stage.setTitle("Usuwanie pracownika");
//            stage.showAndWait();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
//        conn = ConntectToDB.Connector();
//        zamowienie_id_col.setCellValueFactory(cellData -> cellData.getValue().zamowienia_idProperty().asObject());
//        klient_id_col.setCellValueFactory(cellData -> cellData.getValue().klient_idProperty().asObject());
//        pakiet_id_col.setCellValueFactory(cellData -> cellData.getValue().pakiet_idProperty().asObject());
//        pracownik_id_col.setCellValueFactory(cellData -> cellData.getValue().pracownik_idProperty().asObject());
//        data_od_col.setCellValueFactory(cellData -> cellData.getValue().umowa_odProperty());
//        data_do_col.setCellValueFactory(cellData -> cellData.getValue().umowa_doProperty());
//        status_col.setCellValueFactory(cellData -> cellData.getValue().statusProperty());
//
//
//        zamowienieTable.getSelectionModel().getSelectedItems().addListener(new ListChangeListener<Zamowienie>() {
//            @Override
//            public void onChanged(ListChangeListener.Change<? extends Zamowienie> c) {
//                for (Zamowienie u: c.getList()) {
//                    wybierzZamowienie_id = u.getZamowienia_id();
//                }
//            }
//        });
//
//
//    }



    private Stage parentStage = new Stage();

    private void setValueLabel() {
        zamowieniaTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Zamowienie z = zamowieniaTable.getSelectionModel().getSelectedItem();
                z.getZamowienia_id();
            }
        });
    }


//
//    @FXML
//    private void createReport() throws SQLException, IOException {
//        try {
//
//
//
//            Integer id = getSelectedZamowienieId();
//            String selectStmt =("SELECT z.zamowienia_id, p.nazwa,p.technologia,p" +
//                    ".predkosc,p" +
//                    ".cena, p" +
//                    ".okres, p.umowa_od, p.umowa_do FROM"
//                    + " zamowienia z, pakiety p WHERE "
//                    + "p.pakiet_id = z.pakiet_id AND "
//                    + "zamowienia_id =" + id +"" );
//
//            Connection conn = ConntectToDB.Connector();
//            PreparedStatement  preparedStatement = conn.prepareStatement(selectStmt);
//            ResultSet rs = preparedStatement.executeQuery(selectStmt);
//
//
//        } catch(Exception e){
//
//        }
//
//    }
//
//
//
//    @FXML
//    public void save()  {
//        FileChooser fileChooser = new FileChooser();
//        fileChooser.setTitle("Zapisz Raport");
//        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Document", Arrays.asList("*.pdf", "*.PDF")));
//        File file = fileChooser.showSaveDialog(parentStage);
//        if (file != null) {
//            if (file.getName().endsWith(".pdf")) {
//
//
//            }
//        }
//    }
}


