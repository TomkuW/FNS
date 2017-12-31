package Projekt.Okienka;

import Projekt.PodlaczonieDoBazy.ConntectToDB;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;


/**
 * Created by Tomek on 2017-12-26.
 */
public class PracaKontroler implements Initializable {


    private Connection conn = null;
    private PreparedStatement stmt = null;


    @FXML
    private TableColumn<Zamowienie, Integer> zamowienie_id_col;
    @FXML
    private TableColumn<Zamowienie, String> imieKZ_col;
    @FXML
    private TableColumn<Zamowienie, String> nazwiskoKZ_col;
    @FXML
    private TableColumn<Zamowienie, String> miejscowoscKZ_col;
    @FXML
    private TableColumn<Zamowienie, String> ulicaKZ_col;
    @FXML
    private TableColumn<Zamowienie, String> nrdomKz_id_col;
    @FXML
    private TableColumn<Zamowienie, String> nrtelKz_id_col;
    @FXML
    private TableColumn<Zamowienie, String> nazwaPakietZ_col;
    @FXML
    private TableColumn<Zamowienie, String> technologiaPK_col;
    @FXML
    private TableColumn<Zamowienie, String> predkoscPK_col;
    @FXML
    private TableColumn<Zamowienie, String> status_col;
    //@FXML
  //  private TableColumn<Zamowienie, Integer> praco_col;
    @FXML
    private TableView<Zamowienie> pracaTable = new TableView<>();
    @FXML
    private static int wybierzZamowienieId;
    @FXML
    private Button zmien;
    @FXML
    private Label idwybranego;
    @FXML
    private Label informacja;



    public static int getSelectedPracaZamowienieId() {
        return wybierzZamowienieId;
    }





    @FXML
    private void pokazPraca() throws SQLException, ClassNotFoundException {
        try {


            if( ConntectToDB.getCurrentUser()[3].equals("Wykonawca")) {
                //Get all Contractorsinformation
                ObservableList<Zamowienie> ZamowienieData = PracaDAO.pokazPraca();
                //Populate Contractors on TableView
                miejsceWTabeliZamowienia(ZamowienieData);
            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Informacja");
                alert.setHeaderText("Jesteś zalogowany jako: " + ConntectToDB.getCurrentUser()[1] + " " + ConntectToDB.getCurrentUser()[2]);
                alert.setContentText("Nie wykonujesz żadnych usług pracująć jako " + ConntectToDB.getCurrentUser()[3]);

                alert.showAndWait();

            }
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
        pracaTable.setItems(rptData);
    }

    private void setValueLabel() {
        pracaTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Zamowienie z = pracaTable.getSelectionModel().getSelectedItem();
                idwybranego.setText(String.valueOf(z.getZamowienia_id()));
            }
        });
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        conn = ConntectToDB.Connector();
        zamowienie_id_col.setCellValueFactory(cellData -> cellData.getValue().zamowienia_idProperty().asObject());
        nazwaPakietZ_col.setCellValueFactory(cellData -> cellData.getValue().nazwaPakietZProperty());
        status_col.setCellValueFactory(cellData -> cellData.getValue().statusProperty());
        predkoscPK_col.setCellValueFactory(cellData -> cellData.getValue().predkoscPakietZProperty());
        technologiaPK_col.setCellValueFactory(cellData -> cellData.getValue().technologiaPakietZProperty());
        imieKZ_col.setCellValueFactory(cellData -> cellData.getValue().imieKZProperty());
        nazwiskoKZ_col.setCellValueFactory(cellData -> cellData.getValue().nazwiskoKZProperty());
        ulicaKZ_col.setCellValueFactory(cellData -> cellData.getValue().ulicaKZProperty());
        nrdomKz_id_col.setCellValueFactory(cellData -> cellData.getValue().nr_domKZProperty());
        miejscowoscKZ_col.setCellValueFactory(cellData -> cellData.getValue().miejscowoscKZProperty());
        nrtelKz_id_col.setCellValueFactory(cellData -> cellData.getValue().nr_telefonKZProperty());
     //   praco_col.setCellValueFactory(cellData -> cellData.getValue().pracownik_idProperty().asObject());
        pracaTable.getSelectionModel().getSelectedItems().addListener(new ListChangeListener<Zamowienie>() {
            public void onChanged(ListChangeListener.Change<? extends Zamowienie> c) {

                for (Zamowienie o : c.getList()) {
                    wybierzZamowienieId = o.getZamowienia_id();

                }

            }

        });
        setValueLabel();

      //  imieNazwisko.setText(ConntectToDB.getCurrentUser()[1]+" "+ConntectToDB.getCurrentUser()[2]);

        czyPracownik();

    }

    public void czyPracownik(){
        if( ConntectToDB.getCurrentUser()[3].equals("Wykonawca")){
            informacja.setText((ConntectToDB.getCurrentUser()[1]+" "+ConntectToDB.getCurrentUser
                    ()[2]) + " ma przypisane następujące usługi: ");
        }else {
            informacja.setText((ConntectToDB.getCurrentUser()[1]+" "+ConntectToDB.getCurrentUser()[2])+ " nie jest pracownikiem/wykonwacą!");
        }
    }

    @FXML
    public void openEdycjaModyfikacjaZamowienia() throws Exception {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("OkienkaFNS - modyfikuj_praca.fxml"));

            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.showAndWait();
            stage.setTitle("Modyfikacja statusu");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
