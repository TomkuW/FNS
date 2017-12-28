package Projekt.Okienka;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

/**
 * Created by Tomek on 2017-11-17.
 */
public class ModyfikujPracaKontroler implements Initializable {


    @FXML
    private ComboBox status_z;
    @FXML
    private Button modyfikuj;
    @FXML
    private Button anuluj;



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String stat;

        status_z.getItems().addAll("Oczekuje", "W trakcie", "Zrealizowane");




        int zamowienia_id = PracaKontroler.getSelectedPracaZamowienieId();

        try {
            ResultSet rs = PracaDAO.wyswietlDaneZamowienia(zamowienia_id);
            rs.next();
            stat = rs.getString("status");

           status_z.setValue(stat);




        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void modykikujZamowienie() throws SQLException, ClassNotFoundException {
        try {

           PracaDAO.updatePZamowienie(PracaKontroler.getSelectedPracaZamowienieId(), status_z.getSelectionModel()
                   .getSelectedItem().toString());

            Stage stage = (Stage) modyfikuj.getScene().getWindow();
            stage.close();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Modyfikacja Zamowienia");
            alert.setHeaderText(null);
            alert.setContentText("Wybrane zamówienie zostało zmodyfikowane!");

            alert.showAndWait();

        } catch (SQLException e) {
            System.out.print("Błąd podczas modyfikacji klienta" + e);
        }
PracaDAO.pokazPraca();
    }

    @FXML
    private void przyciskAnulujModyfikacje() {
        Stage stage = (Stage) anuluj.getScene().getWindow();
        stage.close();

    }


}

