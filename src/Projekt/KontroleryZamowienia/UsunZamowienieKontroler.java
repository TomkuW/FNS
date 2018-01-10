package Projekt.KontroleryZamowienia;

import Projekt.DAO.ZamowieniaDAO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Klasa kontrolera FXML, aby zapewnić metodę usuwania zamowienia
 *
 * Created by Tomek on 2017-12-17.
 */
public class UsunZamowienieKontroler implements Initializable {


    @FXML
    private Button anulujButtonZ;
    @FXML
    private Button usunButtonZ;


     /**
     * Metoda wyświetlająca dane zamowienia w oknie tabeli
     *
     * @param location  - nieużywany
     * @param resources - nieużywany
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }


    /**
     *  Metoda usuwająca zamowienia z bazy danych
     *
     * @throws SQLException Zgłasza, gdy występuje problem z zapytaniem SQL
     * @throws ClassNotFoundException Zgłasza, gdy występuje problem z użyciem innej klasy
     */
    @FXML
    private void deleteZamowienie() throws SQLException, ClassNotFoundException {
        try {
            ZamowieniaDAO.deleteZamowienieWithId(ZamowieniaKontroler.getSelectedZamowienieId());
            Stage stage = (Stage) usunButtonZ.getScene().getWindow();
            stage.setTitle("Usuwanie Zamówienia");
            stage.close();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Usuwanie zamowienia");
            alert.setHeaderText(null);
            alert.setContentText("Zamówienie zostało usunięte!");
            alert.showAndWait();

        } catch (Exception e) {
            throw e;
        }
        ZamowieniaDAO.pokazZamowienie();
    }

    /**
     * Metoda umożliwiająca anulacje potwierdzenia usuwania, zamyka okno usuwania zamowienia bez wprowadzonych zmian
     */
    @FXML
    private void closeButtonAction() {
        Stage stage = (Stage) anulujButtonZ.getScene().getWindow();
        stage.close();
        stage.setTitle("Anuluj");
    }
}
