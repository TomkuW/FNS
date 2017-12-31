package Projekt.UslugaKontroler;

import Projekt.DAO.UslugaDAO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Klasa kontrolera FXML, aby zapewnić metodę usuwania pakietu
 *
 * Created by Tomek on 2017-11-17.
 */
public class UsunUslugaKontroler implements Initializable {

    @FXML
    private Button closeButton;
    @FXML
    private Button usunButton;

    /**
     * Metoda wyświetlająca dane pakiety w oknie tabeli
     *
     * @param location  - nieużywany
     * @param resources - nieużywany
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    /**
     * Metoda usuwająca pakiety z bazy danych
     *
     * @throws SQLException Zgłasza, gdy występuje problem z zapytaniem SQL
     */
    @FXML
        private void deleteUsluga() throws SQLException {
            try {
               UslugaDAO.deleteUslugaWithId(UslugaKontroler.getSelectedUslugaId());
                Stage stage = (Stage) usunButton.getScene().getWindow();
                stage.close();
                stage.setTitle("Usuwanie uslugi");

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Usuwanie pakietu");
                alert.setHeaderText(null);
                alert.setContentText("Pakiet został usunięty!");

                alert.showAndWait();

            } catch (Exception e) {
                throw e;

            }
        }

    /**
     * Metoda umożliwiająca anulacje potwierdzenia usuwania, zamyka okno usuwania pakietu bez wprowadzonych zmian
     */
    @FXML
    private void closeButtonAction() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
        stage.setTitle("Anuluj");




    }


    }


