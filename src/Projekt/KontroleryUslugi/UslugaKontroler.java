package Projekt.KontroleryUslugi;

import Projekt.DAO.UslugaDAO;
import Projekt.Modele.Usluga;
import Projekt.PodlaczenieDoBazy.ConntectToDB;
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
import java.util.ResourceBundle;


/**
 * Klasa kontrolera FXML zawierająca metody zarządzania pakietami.
 * <p>
 * Created by Tomek on 2017-11-23.
 */
public class UslugaKontroler implements Initializable {

    private Connection conn = null;
    private PreparedStatement stmt = null;


    @FXML
    protected TextField nazwa_usluga;
    @FXML
    private RadioButton technologia_usluga1;
    @FXML
    private RadioButton technologia_usluga2;
    @FXML
    private RadioButton predkosc_usluga1;
    @FXML
    private RadioButton predkosc_usluga2;
    @FXML
    private RadioButton predkosc_usluga3;
    @FXML
    private RadioButton predkosc_usluga4;
    @FXML
    private RadioButton predkosc_usluga5;
    @FXML
    private RadioButton predkosc_usluga6;
    @FXML
    private TextField cena_usluga;
    @FXML
    private RadioButton okres_usluga1;
    @FXML
    private RadioButton okres_usluga2;

    @FXML
    private TableColumn<Usluga, String> nazwaColumn;
    @FXML
    private TableColumn<Usluga, String> technologiaColumn;
    @FXML
    private TableColumn<Usluga, String> predkoscColumn;
    @FXML
    private TableColumn<Usluga, Double> cenaColumn;
    @FXML
    private TableColumn<Usluga, Integer> okresColumn;

    @FXML
    private TableView uslugaTable;
    @FXML
    private TextField poleszukania;
    @FXML
    private static int wybierzUsluga_id;
    @FXML
    private Button ok;

    /**
     * Metoda wybierająca zaznaczony wiersz z tabeli
     *
     * @return pakiet_id zaznaczonego w tablei pakiety
     */
    public static int getSelectedUslugaId() {
        return wybierzUsluga_id;
    }

    /**
     * Metoda dodająca nowey pakiet do bazy danych
     *
     * @throws SQLException Zgłasza, gdy występuje problem z zapytaniem SQL
     */
    @FXML
    private void dodawanie() throws SQLException {


        String nazwa = nazwa_usluga.getText();
        String technologia = technol();
        String predkosc = predkosc();
        String cena = cena_usluga.getText();
        int okres = okres();


        String sql = "insert into pakiety (nazwa, technologia, predkosc, cena, okres) values(?,?,?,?,?)";
        Connection conn = ConntectToDB.Connector();
        PreparedStatement prepStmt = null;
        try {

            if(technologia == null || predkosc == null || okres == 0){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Dodawanie pakietu");
                alert.setHeaderText(null);
                alert.setContentText("Niewystarczająca ilość podanych informacji by dodać usługę!");
                pokazUsluga();
                alert.showAndWait();
            }
            else {

                prepStmt = conn.prepareStatement(sql);
                prepStmt.setString(1, nazwa);
                prepStmt.setString(2, technologia);
                prepStmt.setString(3, predkosc);
                prepStmt.setString(4, cena);
                prepStmt.setInt(5, okres);

                prepStmt.executeUpdate();


                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Dodawanie pakietu");
                alert.setHeaderText(null);
                alert.setContentText("Nowy pakiet został dodany!");
                pokazUsluga();
                alert.showAndWait();
            }
        } catch (SQLException  | ClassNotFoundException e) {

            System.out.print("Błąd podczas dodawania klienta" + e);
             e.printStackTrace();
        }

    }

    /**
     * Metoda przypisująca pod dany radiobutton wartość String
     * @return napis
     */
    public String technol() {
        if (technologia_usluga1.isSelected()) return "FTTP";
        else if (technologia_usluga2.isSelected()) return "ADLS2+";
        else return null;
    }
    /**
     * Metoda przypisująca pod dany radiobutton wartość String
     * @return napis
     */
    public int okres() {
        if (okres_usluga1.isSelected()) return 12;
        else if (okres_usluga2.isSelected()) return 24;
        else return 0;
    }
    /**
     * Metoda przypisująca pod dany radiobutton wartość String
     * @return napis
     */
    public String predkosc() {
        if (predkosc_usluga1.isSelected()) return "20 Mbit/s - 2Mbit/s";
        else if (predkosc_usluga2.isSelected()) return "40 Mbit/s - 2Mbit/s";
        if (predkosc_usluga3.isSelected()) return "60 Mbit/s - 2Mbit/s";
        else if (predkosc_usluga4.isSelected()) return "6 Mbit/s - 512 kbit/s";
        if (predkosc_usluga5.isSelected()) return "10 Mbit/s - 512 kbit/s";
        else if (predkosc_usluga6.isSelected()) return "15 Mbit/s - 512 kbit/s";
        else return null;
    }


    /**
     * Metoda ustawiająca wartości w nagłówkach tabeli.
     *
     * @param location  - nieużywany
     * @param resources - nieużywany
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        conn = ConntectToDB.Connector();
        nazwaColumn.setCellValueFactory(cellData -> cellData.getValue().nazwaProperty());
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

    }

    /**
     * Metoda uzyskująca dane o pakietach i pokazująca je w tabeli
     *
     * @throws SQLException           Zgłasza, gdy występuje problem z zapytaniem SQL
     * @throws ClassNotFoundException Zgłasza, gdy występuje problem z użyciem innej klasy
     */
    @FXML
    private void pokazUsluga() throws SQLException, ClassNotFoundException {
        try {

            ObservableList<Usluga> UslugaData = UslugaDAO.pokazUsluga();
            miejsceWTabeliUslug(UslugaData);

        } catch (SQLException e) {
            System.out.println("Wystąpił błąd podczas pobierania informacji z bazy danych.\n" + e);
            throw e;
        }
    }

    /**
     * Metoda wyszukująca dany pakiet i wyświetlająca te wybrane pakiety w tabeli
     *
     * @throws SQLException           Zgłasza, gdy występuje problem z zapytaniem SQL
     * @throws ClassNotFoundException Zgłasza, gdy występuje problem z użyciem innej klasy
     */
    @FXML
    private void szukajUsluga() throws SQLException, ClassNotFoundException {
        try {
            String pharse = poleszukania.getText();

            ObservableList<Usluga> Uslugadata = UslugaDAO.wyszukajUsluga(pharse);
            miejsceWTabeliUslug(Uslugadata);
        } catch (SQLException e) {
            System.out.println("Wystąpił błąd podczas pobierania informacji z bazy danych.\n" + e);
            throw e;
        }
    }

    /**
     * Metoda wyświetlająca pozycje w tabeli
     *
     * @param uslData Lista obiektów pokazywanych w tabeli
     */
    @FXML
    private void miejsceWTabeliUslug(ObservableList<Usluga> uslData) {
        uslugaTable.setItems(uslData);
    }

    /**
     * Metoda wyświetlająca okno z możliwością potwierdzenia usuwania pakietu
     *
     * @throws Exception Zgłasza, gdy pojawi się nieoczekiwany błąd
     */
    @FXML
    public void openPotwierdzenieUsuwaniaUslug() throws Exception {
        try {
            if(getSelectedUslugaId() != 0) {

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/PotwierdzenieUsuwaniaUslug.fxml"));
                Parent root = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("Usuwanie usług");
                stage.showAndWait();
            }
            else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Informacja");
                alert.setHeaderText(null);
                alert.setContentText("Aby usunąć usługę, zaznacz docelowy wiersz w tabeli!");

                alert.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        pokazUsluga();
    }


}
