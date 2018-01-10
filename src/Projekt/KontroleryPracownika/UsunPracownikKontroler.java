package Projekt.KontroleryPracownika;

import Projekt.DAO.PracownikDAO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Klasa kontrolera FXML, aby zapewnić metodę usuwania pracownika
 * Created by Tomek on 2017-11-17.
 */
public class UsunPracownikKontroler implements Initializable {


    @FXML
    private Button closeButton;
    @FXML
    private Button usunButton;

    /**
     * Metoda wyświetlająca dane pracownika w oknie tabeli
     *
     * @param location  - nieużywany
     * @param resources - nieużywany
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }


    /**
     * Metoda usuwająca pracownika z bazy danych
     *
     * @throws SQLException Zgłasza, gdy występuje problem z zapytaniem SQL
     */
    @FXML
    private void deletePracownik() throws SQLException {
        try {
            PracownikDAO.deletePracownikWithId(PracownikKontroler.getSelectedPracownikId());
            Stage stage = (Stage) usunButton.getScene().getWindow();
            stage.close();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Usuwanie pracownika");
            alert.setHeaderText(null);
            alert.setContentText("Usunięto pracownika!");

            alert.showAndWait();

            PracownikDAO.pokazPracownik();
        } catch (Exception e) {
            e.printStackTrace();
			
        }

    }

    /**
     * Metoda umożliwiająca anulacje potwierdzenia usuwania, zamyka okno usuwania klienta bez wprowadzonych zmian
     */
    @FXML
    private void przyciskAnuluj() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();

    }
}



