package Projekt.Okienka;

import Projekt.DAO.KlientDAO;
import Projekt.KontroleryKlienta.KlientKontroler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Created by Tomek on 2017-12-13.
 */
public class UsunKlientaKontroler implements Initializable {




    @FXML
    private Button closeButton;
    @FXML
    private Button usunButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    private void deleteKlient() throws SQLException {
        try {
            KlientDAO.deleteUserWithId(KlientKontroler.getSelectedKlientId());
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
