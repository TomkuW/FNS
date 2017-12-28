package Projekt.Okienka;

import Projekt.PodlaczonieDoBazy.ConntectToDB;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

/**
 * Created by Tomek on 2017-11-25.
 */
public class PracownikKontroler implements Initializable {

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
    private TableColumn<Pracownik, Double> wynagrodzenieColumn;
    @FXML
    private TableColumn<Pracownik, String> loginColumn;
    @FXML
    private TableColumn<Pracownik, Date> data_zatrudnieniaColumn;
    @FXML
    private TableColumn<Pracownik, String> hasloColumn;
    @FXML
    private TableColumn<Pracownik, String> typ_pracownikaColumn;
    @FXML
    private DatePicker data_zatrudnienia;
    @FXML
    private TableView pracownikTable;
    @FXML
    private TextField poleszukania;
    @FXML
    private static int wybierzPracownikt_id;

    public static int getSelectedPracownikId() {
        return wybierzPracownikt_id;
    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        conn = ConntectToDB.Connector();
        imieColumn.setCellValueFactory(cellData -> cellData.getValue().imieProperty());
        nazwiskoColumn.setCellValueFactory(cellData -> cellData.getValue().nazwiskoProperty());
        zawodColumn.setCellValueFactory(cellData -> cellData.getValue().zawodProperty());
        peselColumn.setCellValueFactory(cellData -> cellData.getValue().PESELProperty());
        ulicaColumn.setCellValueFactory(cellData -> cellData.getValue().ulicaProperty());
        nr_domuColumn.setCellValueFactory(cellData -> cellData.getValue().nr_domuProperty());
        miejscowoscColumn.setCellValueFactory(cellData -> cellData.getValue().miejscowoscProperty());
        emailColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        nr_telefonColumn.setCellValueFactory(cellData -> cellData.getValue().nr_telefonProperty());
        wynagrodzenieColumn.setCellValueFactory(cellData ->cellData.getValue().wynagrodzenieProperty().asObject());
//        loginColumn.setCellValueFactory(cellData -> cellData.getValue().loginProperty());
        data_zatrudnieniaColumn.setCellValueFactory(cellData -> cellData.getValue().data_zatrudnieniaProperty());
     //   hasloColumn.setCellValueFactory(cellData -> cellData.getValue().hasloProperty());
     //   typ_pracownikaColumn.setCellValueFactory(cellData -> cellData.getValue().typ_pracownikaProperty());
        pracownikTable.getSelectionModel().getSelectedItems().addListener(new ListChangeListener<Pracownik>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends Pracownik> c) {
                for (Pracownik u : c.getList()) {
                    wybierzPracownikt_id = u.getPracownik_id();
                }
            }
        });


            typ_pracownika.getItems().removeAll(typ_pracownika.getItems());
            typ_pracownika.getItems().addAll("Pracownik", "Kierownik", "Administrator","Ksiegowy", "Handlowiec");
            typ_pracownika.getSelectionModel().select("Pracownik");


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
    public void openPotwierdzenieUsuwaniaPracownika() throws Exception {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PotwierdzenieUsuwaniaPracownik.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Usuwanie pracownika");
            stage.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//--------------dodawanie pracownika---------------

    @FXML
    private TextField imie_pracownika;
    @FXML
    private TextField nazwisko_pracownika;
    @FXML
    private TextField zawod_pracownika;
    @FXML
    private TextField pesel_pracownika;
    @FXML
    private TextField ulica_pracownika;
    @FXML
    private TextField nr_domu_pracownika;
    @FXML
    private TextField miejscowosc_pracownika;
    @FXML
    private TextField email_pracownika;
    @FXML
    private TextField nr_telfono_pracownika;
    @FXML
    private TextField wynagrodzenie_pracownika;
    @FXML
    private TextField login_pracownika;
    @FXML
    private  TextField haslo_pracownika;
    @FXML
    private DatePicker data_zatrudnienia_pracownika;
    @FXML
    private ComboBox typ_pracownika;

    @FXML
    private Button dodaj;
    @FXML
    private Button anuluj;

    @FXML
    protected void dodawanie() throws SQLException {


        String imie = imie_pracownika.getText();
        String nazwisko = nazwisko_pracownika.getText();
        String zawod = zawod_pracownika.getText();
        String PESEL = pesel_pracownika.getText();
        String ulica = ulica_pracownika.getText();
        String nr_domu = nr_domu_pracownika.getText();
        String miejscowosc = miejscowosc_pracownika.getText();
        String nr_telefon = nr_telfono_pracownika.getText();
        String email = email_pracownika.getText();
        String login =  login_pracownika.getText();
        String haslo = haslo_pracownika.getText();
        String wynagrodzenie = wynagrodzenie_pracownika.getText();
        String t_pracownika = typ_pracownika.getSelectionModel().getSelectedItem().toString();
        LocalDate data = data_zatrudnienia_pracownika.getValue();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime date = LocalDateTime.now();
        String currentDate = formatter.format(date);



        String sql = "insert into pracownicy (imie,nazwisko,zawod,PESEL,ulica,nr_domu,miejscowosc,nr_telefon, email, " +
                "login, haslo, wynagrodzenie,typ_pracownika,data_zatrudnienia) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        Connection conn = ConntectToDB.Connector();
        PreparedStatement prepStmt = null;
        try {

            prepStmt = conn.prepareStatement(sql);
            prepStmt.setString(1, imie);
            prepStmt.setString(2, nazwisko);
            prepStmt.setString(3, zawod);
            prepStmt.setString(4, PESEL);
            prepStmt.setString(5, ulica);
            prepStmt.setString(6, nr_domu);
            prepStmt.setString(7, miejscowosc);
            prepStmt.setString(8,nr_telefon);
            prepStmt.setString(9,email);
            prepStmt.setString(10,login);
            prepStmt.setString(11,haslo);
            prepStmt.setString(12,wynagrodzenie);
            prepStmt.setString(13,t_pracownika);
            prepStmt.setDate(14, Date.valueOf(data));
            prepStmt.executeUpdate();


        } catch (SQLException e) {

            System.out.print("Błąd podczas dodawania klienta" + e);
            throw e;
        }

    }

    //--------------------

    @FXML
    public void openEdycjaModyfikacjaPracownika() throws Exception {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("OkienkaFNS - modyfikuj_pracownika.fxml"));

            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.showAndWait();
            stage.setTitle("Modyfikacja pracownika");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
