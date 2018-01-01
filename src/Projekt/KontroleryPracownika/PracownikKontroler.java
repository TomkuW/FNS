package Projekt.KontroleryPracownika;

import Projekt.DAO.PracownikDAO;
import Projekt.Modele.Pracownik;
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
 * Klasa kontrolera FXML zawierająca metody zarządzania pracownikami, oraz dodawania pracownika.
 *
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
    private TableColumn<Pracownik, Date> data_zatrudnieniaColumn;
    @FXML
    private TableView pracownikTable;
    @FXML
    private TextField poleszukania;
    @FXML
    private static int wybierzPracownikt_id;

    /**
     * Metoda wybierająca zaznaczony wiersz z tabeli
     * @return Id pracownika zaznaczonego w tablei pracownicy
     */
    public static int getSelectedPracownikId() {
        return wybierzPracownikt_id;
    }

    /**
     * Metoda ustawiająca wartości w nagłówkach tabeli.
     * @param location - nieużywany
     * @param resources - nie€zywany
     */
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
        data_zatrudnieniaColumn.setCellValueFactory(cellData -> cellData.getValue().data_zatrudnieniaProperty());
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

            typ_pracownika.getItems().removeAll(typ_pracownika.getItems());
            typ_pracownika.getItems().addAll("Pracownik", "Kierownik", "Administrator","Ksiegowy", "Handlowiec");
            typ_pracownika.getSelectionModel().select("Pracownik");

    }

    /**
     * Metoda uzyskująca dane o pracownikach i pokazująca je w tabeli
     * @throws SQLException Zgłasza, gdy występuje problem z zapytaniem SQL
     * @throws ClassNotFoundException Zgłasza, gdy występuje problem z użyciem innej klasy
     */
    @FXML
    private void pokazPracownik() throws SQLException, ClassNotFoundException {
        try {
            ObservableList<Pracownik> PracownikData = PracownikDAO.pokazPracownik();

            miejsceWTabeliPracownika(PracownikData);

        } catch (SQLException e) {
            System.out.println("Error occurred while getting Contractors information from DB.\n" + e);
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
            String pharse = poleszukania.getText();

            ObservableList<Pracownik> Pracownikdata = PracownikDAO.wyszukajPracownik(pharse);

            miejsceWTabeliPracownika(Pracownikdata);
        } catch (SQLException e) {
            System.out.println("Wystąpił błąd podczas pobierania informacji z bazy danych.\n" + e);
            throw e;
        }
    }

    /**
     * Metoda wyświetlająca pozycje w tabeli
     * @param prcData Lista obiektów pokazywanych w tabeli
     */
    @FXML
    private void miejsceWTabeliPracownika(ObservableList<Pracownik> prcData) {
        pracownikTable.setItems(prcData);
    }

    /**
     * Metoda wyświetlająca okno z możliwością potwierdzenia usuwania pracownika
     * @throws Exception Zgłasza, gdy pojawi się nieoczekiwany błąd
     */
    @FXML
    public void openPotwierdzenieUsuwaniaPracownika() throws Exception {
        try {

            if(getSelectedPracownikId() != 0) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PotwierdzenieUsuwaniaPracownik.fxml"));
                Parent root = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("Usuwanie pracownika");
                stage.showAndWait();
            }
            else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Informacja");
                alert.setHeaderText(null);
                alert.setContentText("Aby usunąć danego pracownika, zaznacz docelowy wiersz w tabeli!");

                alert.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        pokazPracownik();
    }

    /**
     * Metoda wyświetlająca okno z możliwością modyfikacji danych pracownika
     * @throws Exception Zgłasza, gdy pojawi się nieoczekiwany błąd
     */
    @FXML
    public void openEdycjaModyfikacjaPracownika() throws Exception {
        try {

            if(getSelectedPracownikId() != 0) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("OkienkaFNS - modyfikuj_pracownika.fxml"));

                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));
                stage.setTitle("Modyfikacja pracownika");
                stage.showAndWait();
            }
            else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Informacja");
                alert.setHeaderText(null);
                alert.setContentText("Aby modyfikować danego pracownika, zaznacz docelowy wiersz w tabeli!");

                alert.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        pokazPracownik();
    }

// Kontlroler dodawania pracownika

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

    /**
     * Metoda dodająca nowego pracownika do bazy danych
     * @throws SQLException Zgłasza, gdy występuje problem z zapytaniem SQL
     */
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
            if(data_zatrudnienia_pracownika.getValue() == null || wynagrodzenie_pracownika == null){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Informacja");
                alert.setHeaderText(null);
                alert.setContentText("Aby dodać pracownika, wprowadź wszystkie potrzebne dane!");

                alert.showAndWait();
            }else {

                prepStmt = conn.prepareStatement(sql);
                prepStmt.setString(1, imie);
                prepStmt.setString(2, nazwisko);
                prepStmt.setString(3, zawod);
                prepStmt.setString(4, PESEL);
                prepStmt.setString(5, ulica);
                prepStmt.setString(6, nr_domu);
                prepStmt.setString(7, miejscowosc);
                prepStmt.setString(8, nr_telefon);
                prepStmt.setString(9, email);
                prepStmt.setString(10, login);
                prepStmt.setString(11, haslo);
                prepStmt.setString(12, wynagrodzenie);
                prepStmt.setString(13, t_pracownika);
                prepStmt.setDate(14, Date.valueOf(data));
                prepStmt.executeUpdate();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Informacja");
                alert.setHeaderText(null);
                alert.setContentText("Dodano pracownika!");

                alert.showAndWait();
                pokazPracownik();
            }
        } catch (SQLException |ClassNotFoundException e) {

            System.out.print("Błąd podczas dodawania klienta" + e);
            e.printStackTrace();
        }

    }

}
