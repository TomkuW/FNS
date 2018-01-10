package Projekt.KontroleryKlienta;

import Projekt.DAO.KlientDAO;
import Projekt.Modele.Klient;
import Projekt.PodlaczenieDoBazy.ConntectToDB;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Klasa kontrolera FXML zawierająca metody zarządzania klientami.
 *
 * Created by Tomek on 2017-11-17.
 */


public class KlientKontroler implements Initializable {

    private Connection conn = null;
    private PreparedStatement stmt = null;


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
    public TableView klientTable;
    @FXML
    private TextField poleszukania;
    @FXML
    private static int wybierzKlient_id;


    /**
     * Metoda wybierająca zaznaczony wiersz z tabeli
     * @return Id klienta zaznaczonego w tablei klienci
     */
    public static int getSelectedKlientId() {
        return wybierzKlient_id;
    }

    /**
     * Metoda ustawiająca wartości w nagłówkach tabeli.
     * @param location - nieużywany
     * @param resources - nieużywany
     */
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

            /**
             * Metoda dodająca ID do każdego wiersza i wybierająca go
             * @param c - parametr nasłuchiwający
             */
            @Override
            public void onChanged(ListChangeListener.Change<? extends Klient> c) {
                for (Klient u : c.getList()) {
                    wybierzKlient_id = u.getKlient_id();
                }
            }
        });

    }

    /**
     * Metoda uzyskująca dane o klientach i pokazująca je w tabeli
     * @throws SQLException Zgłasza, gdy występuje problem z zapytaniem SQL
     * @throws ClassNotFoundException Zgłasza, gdy występuje problem z użyciem innej klasy
     */
    @FXML
    private void pokazKlient() throws SQLException, ClassNotFoundException {
        try {

            ObservableList<Klient> UserData = KlientDAO.pokazKlient();
            miejsceWTabeliKlienta(UserData);

        } catch (SQLException e) {
            System.out.println("Wystąpił błąd podczas pobierania informacji z bazy danych.\n" + e);
            throw e;
        }
    }

    /**
     * Metoda wyszukująca dane klienta i wyświetlająca te wybrane w tabeli
     * @throws SQLException Zgłasza, gdy występuje problem z zapytaniem SQL
     * @throws ClassNotFoundException Zgłasza, gdy występuje problem z użyciem innej klasy
     */
    @FXML
    private void szukajKlienta() throws SQLException, ClassNotFoundException {
        try {
            String pharse = poleszukania.getText();

            ObservableList<Klient> Klientdata = KlientDAO.wyszukajKlient(pharse);

            miejsceWTabeliKlienta(Klientdata);
        } catch (SQLException e) {
            System.out.println("Wystąpił błąd podczas pobierania informacji z bazy danych.\n" + e);
            throw e;
        }
    }

    /**
     * Metoda wyświetlająca pozycje w tabeli
     * @param usrData Lista obiektów pokazywanych w tabeli
     */
    @FXML
    private void miejsceWTabeliKlienta(ObservableList<Klient> usrData) {

        klientTable.setItems(usrData);
    }

    /**
     * Metoda wyświetlająca okno z możliwością potwierdzenia usuwania klienta
     * @throws Exception Zgłasza, gdy pojawi się nieoczekiwany błąd
     */
    @FXML
    public void openPotwierdzenieUsuwaniaUsera() throws Exception {
        try {

            if(getSelectedKlientId() != 0) {

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PotwierdzenieUsuwaniaKlient.fxml"));
                Parent root = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("Usuwanie klienta");
                stage.showAndWait();
            }
            else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Informacja");
                alert.setHeaderText(null);
                alert.setContentText("Aby usunąć danego klienta, zaznacz docelowy wiersz w tabeli!");

                alert.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        pokazKlient();
    }

    /**
     * Metoda wyświetlająca okno z możliwością dodania klienta
     * @throws Exception Zgłasza, gdy wystąpi nieoczekiwany błąd
     */
    @FXML
    public void openEdycjaDodawanieUsera() throws Exception {
        try {

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("OkienkaFNS - dodaj_klienta.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));
                stage.setTitle("Dodawanie klienta");
                stage.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
        }
        pokazKlient();
    }

    /**
     * Metoda wyświetlająca okno z możliwością modyfikacji użytkownika
     * @throws Exception Zgłasza, gdy wystapi nieoczekiwany błąd.
     */
    @FXML
    public void openEdycjaModyfikacjaUsera() throws Exception {
        try {

            if(getSelectedKlientId() != 0) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("OkienkaFNS - modyfikuj_klienta.fxml"));

                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));
                stage.setTitle("Modyfikacja klienta");
                stage.showAndWait();
            }
            else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Informacja");
                alert.setHeaderText(null);
                alert.setContentText("Aby edytowac danego klienta, zaznacz docelowy wiersz w tabeli!");

                alert.showAndWait();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        pokazKlient();
    }


}
