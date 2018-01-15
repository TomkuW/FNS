package Projekt.KontroleryPracy;

import Projekt.Modele.Zamowienie;
import Projekt.DAO.PracaDAO;
import Projekt.PodlaczenieDoBazy.ConntectToDB;
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
 * Klasa kontrolera FXML zawierająca metody zarządzania zamowieniami
 *
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

    /**
     * Metoda wybierająca zaznaczony wiersz z tabeli
     * @return Id zamowienia zaznaczonego w tablei pracownicy
     */
    public static int getSelectedPracaZamowienieId() {
        return wybierzZamowienieId;
    }

    /**
     * Metoda uzyskująca dane o zamowieniach jako pracy i pokazująca je w tabeli
     * @throws SQLException Zgłasza, gdy występuje problem z zapytaniem SQL
     * @throws ClassNotFoundException Zgłasza, gdy występuje problem z użyciem innej klasy
     */
    @FXML
    private void pokazPraca() throws SQLException, ClassNotFoundException {

        try {

            if( ConntectToDB.getCurrentUser()[3].equals("Wykonawca")) {
                ObservableList<Zamowienie> ZamowienieData = PracaDAO.pokazPraca();
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
            System.out.println("Wystąpił błąd podczas pobierania informacji z bazy danych.\n" + e);
            throw e;
        }
    }

    /**
     * Metoda wyświetlająca pozycje w tabeli
     * @param rptData Lista obiektów pokazywanych w tabeli
     */
    @FXML
    private void miejsceWTabeliZamowienia(ObservableList<Zamowienie> rptData) {

        pracaTable.setItems(rptData);
    }

    /**
     *
     * Metoda obsługująca zdarzenia myszy, pozwalająca użyskać id zamowienia po kliknieciu w dany wiersz tabeli
     * Zamowienia
     */
    private void setValueLabel() {
        pracaTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
            /**
             * Pobranie wartosci id zamowienia po kliknieciu w dany wiersz tabeli
             * @param event nieużywany
             */
            @Override
            public void handle(MouseEvent event) {
                try {
                    Zamowienie z = pracaTable.getSelectionModel().getSelectedItem();
                    idwybranego.setText(String.valueOf(z.getZamowienia_id()));
                }
                catch (NullPointerException e){
                    System.out.println("false");
                }
            }
        });
    }

    /**
     * Metoda ustawiająca wartości w nagłówkach tabeli.
     * @param location - nieużywany
     * @param resources - nieużywany
     */
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
        pracaTable.getSelectionModel().getSelectedItems().addListener(new ListChangeListener<Zamowienie>() {
            /**
             * Metoda dodająca ID do każdego wiersza i wybierająca go
             * @param c - parametr nasłuchiwający
             */
            public void onChanged(ListChangeListener.Change<? extends Zamowienie> c) {
                for (Zamowienie o : c.getList()) {
                    wybierzZamowienieId = o.getZamowienia_id();

                }
            }

        });

        setValueLabel();
        czyPracownik();
    }

    /**
     *  Metoda sprawdzajaca czy dany uzytkownik jest Wykonawca czy nie
     */
    @FXML
    public void czyPracownik(){
        if( ConntectToDB.getCurrentUser()[3].equals("Wykonawca")){
            informacja.setText((ConntectToDB.getCurrentUser()[1]+" "+ConntectToDB.getCurrentUser
                    ()[2]) + " ma przypisane następujące usługi: ");
        }else {
            informacja.setText((ConntectToDB.getCurrentUser()[1]+" "+ConntectToDB.getCurrentUser()[2])+ " nie jest wykonwacą!");
        }
    }

    /**
     * Metoda wyświetlająca okno z możliwością modyfikacji statusu zamowienia
     * @throws Exception Zgłasza, gdy wystapi nieoczekiwany błąd.
     */
    @FXML
    public void openEdycjaModyfikacjaZamowienia() throws Exception {
        try {

            if(getSelectedPracaZamowienieId() !=0) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../KontroleryPracy/OkienkaFNS - modyfikuj_praca.fxml"));

                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));
                stage.setTitle("Modyfikacja statusu");
                stage.showAndWait();
                pokazPraca();
            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Błąd modyfikacji statusu zamówienia");
                alert.setHeaderText(null);
                alert.setContentText("Aby zmodyfikować status danego zamówienia, wybierz konkretny wiersz zamówienia " +
                        "z tabeli");

                alert.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        pokazPraca();
    }


}
