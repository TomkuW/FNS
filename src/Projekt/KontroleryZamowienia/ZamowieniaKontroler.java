package Projekt.KontroleryZamowienia;


import Projekt.DAO.ZamowieniaDAO;
import Projekt.Modele.Zamowienie;
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
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Klasa kontrolera FXML zawierająca metody zarządzania zamowieniami.
 *
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
    private int id;
    @FXML
    private Label wybraapozycja;

    /**
     * Metoda wybierająca zaznaczony wiersz z tabeli
     * @return Id zamowienia zaznaczonego w tablei Zamowienia
     */
    public static int getSelectedZamowienieId() {
        return wybierzZamowienieId;
    }

    /**
     * Metoda ustawiająca wartości w nagłówkach tabeli.
     * @param location - nieużywany
     * @param resources - nieużywany
     */
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
    }

    /**
     * Metoda uzyskująca dane o zamowieniach i pokazująca je w tabeli
     * @throws SQLException Zgłasza, gdy występuje problem z zapytaniem SQL
     * @throws ClassNotFoundException Zgłasza, gdy występuje problem z użyciem innej klasy
     */
    @FXML
    private void pokazZamowienie() throws SQLException, ClassNotFoundException {
        try {
            ObservableList<Zamowienie> ZamowienieData = ZamowieniaDAO.pokazZamowienie();
            miejsceWTabeliZamowienia(ZamowienieData);

        } catch (SQLException e) {
            System.out.println("Wystąpił błąd podczas pobierania informacji o Zamowieniach z bazy danych.\n" + e);
            throw e;
        }
    }

    /**
     * Metoda wyszukująca dane zamowienia i wyświetlająca te wybrane w tabeli
     * @throws SQLException Zgłasza, gdy występuje problem z zapytaniem SQL
     * @throws ClassNotFoundException Zgłasza, gdy występuje problem z użyciem innej klasy
     */
    @FXML
    private void szukajZamowienie() throws SQLException, ClassNotFoundException {
        try {
            String pharse = poleszukania4.getText();

            ObservableList<Zamowienie> ZamowienieData = ZamowieniaDAO.wyszukajZamowienie(pharse);

            miejsceWTabeliZamowienia(ZamowienieData);
        } catch (SQLException e) {
            System.out.println("Wystąpił błąd podczas pobierania informacji o Zamowieniach z bazy danych.\n" + e);
            throw e;
        }
    }

    /**
     * Metoda wyświetlająca pozycje w tabeli
     * @param rptData Lista obiektów pokazywanych w tabeli
     */
    @FXML
    private void miejsceWTabeliZamowienia(ObservableList<Zamowienie> rptData) {
        zamowieniaTable.setItems(rptData);
    }

    /**
     * Metoda wyświetlająca okno z możliwością potwierdzenia usuwania zamowienia
     * @throws Exception Zgłasza, gdy pojawi się nieoczekiwany błąd
     */
    @FXML
    public void openPotwierdzenieUsuwaniaZamowienia() throws Exception {
        try {

            if(getSelectedZamowienieId() !=0) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PotwierdzenieUsuwaniaZamowienia.fxml"));
                Parent root = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("Usuwanie zamówienia");
                stage.showAndWait();

            }else{

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Błąd usuwania zamowienia");
                alert.setHeaderText("Zamówienie nie zostało usunięte!");
                alert.setContentText("Wybierz dane zamowienie z tabeli aby go usunąć");
                alert.showAndWait();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        pokazZamowienie();
    }

    /**
     * Metoda wyświetlająca okno z możliwością modyfikacji zamowienia
     * @throws Exception Zgłasza, gdy wystapi nieoczekiwany błąd.
     */
    @FXML
    public void openEdycjaModyfikacjaZamowienia() throws Exception {
        try {

            if(getSelectedZamowienieId() !=0) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("OkienkaFNS - modyfikuj_zamowienie.fxml"));

                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));
                stage.setTitle("Modyfikacja zamowienia");
                stage.showAndWait();
            }
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Modyfikacja zamowienia");
                alert.setHeaderText(null);
                alert.setContentText("Aby zmodyfikować dane zamówienie, wybierz konkretne zamówienie z tabeli");

                alert.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Metoda obsługująca zdarzenia myszy, pozwalająca użyskać id zamowienia po kliknieciu w dany wiersz tabeli
     * Zamowienia
     */
    private void setValueLabel() {
        zamowieniaTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            /**
             *Pobranie wartosci id zamowienia po kliknieciu w dany wiersz tabeli
             */
            public void handle(MouseEvent event) {
                try {
                    Zamowienie z = zamowieniaTable.getSelectionModel().getSelectedItem();
                    wybraapozycja.setText(String.valueOf(z.getZamowienia_id()));
                    id = z.getZamowienia_id();
                }
                catch (NullPointerException e){
                    System.out.println("false");
                }
            }
        });
    }

    /**
     * Metoda tworzenia Faktury w oparciu o sprzedane zamówienia
     * @throws SQLException Zgłasza, gdy występuje problem z zapytaniem SQL
     * @throws IOException Zgłasza, gdy nie można utworzyć nowego pliku z Faktura Vat
     */
    @FXML
    public void tworzFakturePDF() throws SQLException, IOException {
        try {

            if(getSelectedZamowienieId() != 0) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDateTime date = LocalDateTime.now();
                String currentDate = formatter.format(date);
                String date1 = formatter.format(LocalDate.now());
                String[] dates = {date1, currentDate};

                ResultSet rs2 = ConntectToDB.getData("SELECT u.nazwa, u.technologia, u.predkosc, COUNT" +
                        "(z.zamowienia_id) as ilosc, z.umowa_od, z.umowa_do, u.cena" +
                        " from zamowienia z, pakiety u WHERE zamowienia_id = '" + id + "' AND u.pakiet_id = z.pakiet_id");

                ResultSet rs3 = ConntectToDB.getData("SELECT *" +
                        " from zamowienia z, klienci k WHERE z.zamowienia_id = '" + id + "' AND k.klient_id = z.klient_id");

                Faktura f1 = new Faktura(ConntectToDB.getCurrentUser(), rs2, rs3);
                f1.create();


                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Informacja");
                alert.setHeaderText(null);
                alert.setContentText("Utworzono Fakture VAT");

                alert.showAndWait();
            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Błąd generowania faktury");
                alert.setHeaderText(null);
                alert.setContentText("Wybierz zamówienie, którego fakturę chcesz wygenerować!");

                alert.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}


