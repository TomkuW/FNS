package Projekt.Okienka;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UsunUslugeKontroler implements Initializable {
    @FXML
    private Button closeButton;
    @FXML
    private Button usunButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    private void deleteUsluga() throws SQLException {
        try {
            UslugaDAO.deleteUserWithId(UslugaKontroler.getSelectedUslugaId());
            Stage stage = (Stage) usunButton.getScene().getWindow();
            stage.close();



        } catch (Exception e) {
            throw e;

        }
    }
    @FXML
    private void closeButtonAction() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();

    }
}
