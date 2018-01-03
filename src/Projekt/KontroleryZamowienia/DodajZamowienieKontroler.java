package Projekt.KontroleryZamowienia;


import Projekt.DAO.ZamowieniaDAO;
import Projekt.Modele.Klient;
import Projekt.Modele.Pracownik;
import Projekt.Modele.Usluga;
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
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

/**
 * Klasa zawierająca metodę dodawania zamowienia oraz kontroler FXML pozwalający na wyświetlanie sprzedanych uslug.
 *
 * Created by Tomek on 2017-12-13.
 */
public class DodajZamowienieKontroler implements Initializable {

    private Connection conn = null;
    private PreparedStatement stmt = null;

    @FXML
    private DatePicker umowa_od;
    @FXML
    private DatePicker umowa_do;
    @FXML
    private Label klient;
    @FXML
    private Label pakiet;
    @FXML
    private Label pracownik;

    /**
     * Metoda dodająca nowe zamowienie(sprzedaż) do bazy danych
     * @throws SQLException Zgłasza, gdy występuje problem z zapytaniem SQL
     */
    @FXML
    protected void dodawanie() throws SQLException {


        String status = "Oczekuje";
        LocalDate data = umowa_od.getValue();
        LocalDate data1 = umowa_do.getValue();

        String sql = "insert into zamowienia (klient_id,pakiet_id,pracownik_id,umowa_od,umowa_do,status) values(?,?," +
                "?,?,?,?)";
        Connection conn = ConntectToDB.Connector();
        PreparedStatement prepStmt = null;
        try {
            if(getSelectedKlientId() != 0 && getSelectedPracownikId() != 0 && getSelectedUslugaId() !=0) {

                prepStmt = conn.prepareStatement(sql);
                prepStmt.setInt(1, DodajZamowienieKontroler.getSelectedKlientId());
                prepStmt.setInt(2, DodajZamowienieKontroler.getSelectedUslugaId());
                prepStmt.setInt(3, DodajZamowienieKontroler.getSelectedPracownikId());
                prepStmt.setDate(4, Date.valueOf(data));
                prepStmt.setDate(5, Date.valueOf(data1));
                prepStmt.setString(6, status);
                prepStmt.executeUpdate();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Zamówienie");
                alert.setHeaderText("Sprzedano Usługę");
                alert.setContentText("W systemie została zapisana informacja o zamówieniu. Możesz sprawdzić listę " +
                        "zamówień klikając w przycisk Lista sprzedanych usług");

                alert.showAndWait();

            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Dodawanie zamówienia");
                alert.setHeaderText("Błąd dodawania zamówienia!");
                alert.setContentText("Aby sprzedać daną usługę, wskaz pracownika, klienta oraz usługe wybierając dany" +
                        " wiersz tabeli");

                alert.showAndWait();
            }
        } catch (SQLException e) {

            System.out.print("Błąd podczas dodawania klienta" + e);
            throw e;
        }

    }

    /**
     * Metoda obsługująca zdarzenia myszy, pozwalająca użyskać id zamowienia po kliknieciu w dany wiersz tabeli
     * Zamowienia
     */
    private void setValueLabel(){
        klientTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            /**
             * Pobranie wartosci imienia i nazwiska po kliknieciu w dany wiersz tabeli
             */
            public void handle(MouseEvent event) {
                Klient person = klientTable.getSelectionModel().getSelectedItem();
                klient.setText(person.getImie()+ " "+ person.getNazwisko());
            }
        });
        uslugaTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            /**
             * Ustalenie okresu umowy po kliknieciu w dany wiersz tabeli oraz ustawienie pola z datą na domyslny
             * czas umowy.
             */
            public void handle(MouseEvent event) {
                Usluga offer = uslugaTable.getSelectionModel().getSelectedItem();
                pakiet.setText(offer.getNazwa());

                if(offer.getOkres() == 24) {

                    umowa_od.setValue(LocalDate.now());
                    umowa_do.setValue(umowa_od.getValue().plusYears(2));

                }
                if (offer.getOkres() == 12){

                    umowa_od.setValue(LocalDate.now());
                    umowa_do.setValue(umowa_od.getValue().plusYears(1));
                }

            }
        });
        pracownikTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            /**
             * Pobranie wartosci imienia i nazwiska po kliknieciu w dany wiersz tabeli
             */
            public void handle(MouseEvent event) {
                Pracownik jobber = pracownikTable.getSelectionModel().getSelectedItem();
                pracownik.setText(jobber.getImie()+ " "+ jobber.getNazwisko());

            }
        });
    }

    /**
     * Metoda wyświetlająca okno z lista sprzedanych uslug(zamowieniami)
     * @throws Exception Zgłasza, gdy pojawi się nieoczekiwany błąd
     */
    @FXML
    public void otworzTabeleZamowien() throws Exception {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("OkienkaFNS - lista_zamowien.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Tabela zamówień");
            stage.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private TableColumn<Klient, String> imieColumn1;
    @FXML
    private TableColumn<Klient, String> nazwiskoColumn1;
    @FXML
    private TableColumn<Klient, String> peselColumn1;
    @FXML
    private TableColumn<Klient, String> miejscowoscColumn1;
    @FXML
    private TableColumn<Klient, String> ulicaColumn1;
    @FXML
    private TableColumn<Klient, String> nrdomColumn1;
    @FXML
    private TableColumn<Klient, String> nrtelefonuColumn1;
    @FXML
    private  TableView<Klient> klientTable = new TableView<>();
    @FXML
    private TextField poleszukania1;
    @FXML
    private static int wybierzKlient_id;
    @FXML
    private TableColumn<Usluga, String> nazwaColumnn;
    @FXML
    private TableColumn<Usluga, String> technologiaColumn;
    @FXML
    private TableColumn<Usluga, String> predkoscColumn;
    @FXML
    private TableColumn<Usluga, Double> cenaColumn;
    @FXML
    private TableColumn<Usluga, Integer> okresColumn;
    @FXML
    private TableView<Usluga> uslugaTable = new TableView<>();
    @FXML
    private TextField poleszukania2;
    @FXML
    private static int wybierzUsluga_id;
    @FXML
    private TableColumn<Pracownik, String> imieColumn;
    @FXML
    private TableColumn<Pracownik, String> nazwiskoColumn;
    @FXML
    private TableColumn<Pracownik, String> typ_pracownikaColumn;
    @FXML
    private TableView<Pracownik> pracownikTable = new TableView<>();
    @FXML
    private TextField poleszukania3;
    @FXML
    private static int wybierzPracownikt_id;

    /**
     * Metoda wybierająca zaznaczony wiersz z tabeli
     * @return Id pracownika zaznaczonego w tablei
     */
    public static int getSelectedPracownikId() {
        return wybierzPracownikt_id;
    }

    /**
     * Metoda wybierająca zaznaczony wiersz z tabeli
     * @return Id uslugi zaznaczonego w tablei
     */
    public static int getSelectedUslugaId() {
        return wybierzUsluga_id;
    }

    /**
     * Metoda wybierająca zaznaczony wiersz z tabeli
     * @return Id klienta zaznaczonego w tablei
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
        imieColumn1.setCellValueFactory(cellData -> cellData.getValue().imieProperty());
        nazwiskoColumn1.setCellValueFactory(cellData -> cellData.getValue().nazwiskoProperty());
        peselColumn1.setCellValueFactory(cellData -> cellData.getValue().PESELProperty());
        miejscowoscColumn1.setCellValueFactory(cellData -> cellData.getValue().miejscowoscProperty());
        ulicaColumn1.setCellValueFactory(cellData -> cellData.getValue().ulicaProperty());
        nrdomColumn1.setCellValueFactory(cellData -> cellData.getValue().nr_domProperty());
        nrtelefonuColumn1.setCellValueFactory(cellData -> cellData.getValue().nr_telefonProperty());
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
        conn = ConntectToDB.Connector();
        nazwaColumnn.setCellValueFactory(cellData -> cellData.getValue().nazwaProperty());
        technologiaColumn.setCellValueFactory(cellData -> cellData.getValue().technologiaProperty());
        predkoscColumn.setCellValueFactory(cellData -> cellData.getValue().predkoscProperty());
        cenaColumn.setCellValueFactory(cellData -> cellData.getValue().cenaProperty().asObject());
        okresColumn.setCellValueFactory(cellData -> cellData.getValue().okresProperty().asObject());
        uslugaTable.getSelectionModel().getSelectedItems().addListener(new ListChangeListener<Usluga>() {
            /**
             * Metoda dodająca ID do każdego wiersza i wybierająca go
             * @param c - parametr nasłuchiwający
             */
            @Override
            public void onChanged(ListChangeListener.Change<? extends Usluga> c) {
                for (Usluga u : c.getList()) {
                    wybierzUsluga_id = u.getPakiet_id();
                }
            }
        });
        conn = ConntectToDB.Connector();
        imieColumn.setCellValueFactory(cellData -> cellData.getValue().imieProperty());
        nazwiskoColumn.setCellValueFactory(cellData -> cellData.getValue().nazwiskoProperty());
        typ_pracownikaColumn.setCellValueFactory(cellData -> cellData.getValue().typ_pracownikaProperty());
        pracownikTable.getSelectionModel().getSelectedItems().addListener(new ListChangeListener<Pracownik>() {
            /**
             * Metoda dodająca ID do każdego wiersza i wybierająca go
             * @param c - parametr nasłuchiwający
             */
            @Override
            public void onChanged(ListChangeListener.Change<? extends Pracownik> c) {
                for (Pracownik u : c.getList()) {
                    wybierzPracownikt_id = u.getPracownik_id();
                }
            }
        });

                setValueLabel();
    }

    /**
     * Metoda wyszukująca dane klienta i wyświetlająca te wybrane w tabeli
     * @throws SQLException Zgłasza, gdy występuje problem z zapytaniem SQL
     * @throws ClassNotFoundException Zgłasza, gdy występuje problem z użyciem innej klasy
     */
    @FXML
    private void szukajKlienta() throws SQLException, ClassNotFoundException {
        try {
            String pharse = poleszukania1.getText();

            ObservableList<Klient> Klientdata = ZamowieniaDAO.wyszukajKlient(pharse);
            klientTable.setItems(Klientdata);
        } catch (SQLException e) {
            System.out.println("Wystąpił błąd podczas pobierania informacji z bazy danych.\n" + e);
            throw e;
        }
    }

    /**
     * Metoda wyszukująca dane uslugi i wyświetlająca te wybrane w tabeli
     * @throws SQLException Zgłasza, gdy występuje problem z zapytaniem SQL
     * @throws ClassNotFoundException Zgłasza, gdy występuje problem z użyciem innej klasy
     */
    @FXML
    private void szukajUsluga() throws SQLException, ClassNotFoundException {
        try {
            String pharse = poleszukania2.getText();
            ObservableList<Usluga> Uslugadata = ZamowieniaDAO.wyszukajUsluga(pharse);
            uslugaTable.setItems(Uslugadata);
        } catch (SQLException e) {
            System.out.println("Wystąpił błąd podczas pobierania informacji z bazy danych.\n" + e);
            throw e;
        }
    }

    /**
     * Metoda wyszukująca dane pracownika i wyświetlająca te wybrane w tabeli
     * @throws SQLException Zgłasza, gdy występuje problem z zapytaniem SQL
     * @throws ClassNotFoundException Zgłasza, gdy występuje problem z użyciem innej klasy
     */
    @FXML
    private void szukajPracownika() throws SQLException, ClassNotFoundException {
        try {
            String pharse = poleszukania3.getText();
            ObservableList<Pracownik> Pracownikdata = ZamowieniaDAO.wyszukajPracownik(pharse);
            pracownikTable.setItems(Pracownikdata);
        } catch (SQLException e) {
            System.out.println("Wystąpił błąd podczas pobierania informacji z bazy danych.\n" + e);
            throw e;
        }
    }

    /**
     * Metoda uzyskuje dane o klientach, uslugach oraz pakietach i pokazuje je w tabeli
     * @throws SQLException Zgłasza, gdy występuje problem z zapytaniem SQL
     * @throws ClassNotFoundException Zgłasza, gdy występuje problem z użyciem innej klasy
     */
    @FXML
    private void pokazAll() throws SQLException, ClassNotFoundException {
        try {
            ObservableList<Pracownik> PracownikData = ZamowieniaDAO.pokazPracownik();
            ObservableList<Usluga> UslugaData = ZamowieniaDAO.pokazUsluga();
            ObservableList<Klient> UserData = ZamowieniaDAO.pokazKlient();

            klientTable.setItems(UserData);
            uslugaTable.setItems(UslugaData);
            pracownikTable.setItems(PracownikData);

        } catch (SQLException e) {
            System.out.println("Wystąpił błąd podczas pobierania informacji z bazy danych.\n" + e);
            throw e;
        }

    }

}

