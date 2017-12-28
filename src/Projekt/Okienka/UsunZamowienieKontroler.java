package Projekt.Okienka;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Created by Tomek on 2017-12-17.
 */
public class UsunZamowienieKontroler implements Initializable {


    @FXML
    private Button anulujButtonZ;
    @FXML
    private Button usunButtonZ;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    private void deleteZamowienie() throws SQLException, ClassNotFoundException {
        try {
            ZamowieniaDAO.deleteZamowienieWithId(ZamowieniaKontroler.getSelectedZamowienieId());
            Stage stage = (Stage) usunButtonZ.getScene().getWindow();
            stage.close();
            stage.setTitle("Usuwanie Zamówienia");

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

    @FXML
    private void closeButtonAction() {
        Stage stage = (Stage) anulujButtonZ.getScene().getWindow();
        stage.close();
        stage.setTitle("Anuluj");


    }


}
