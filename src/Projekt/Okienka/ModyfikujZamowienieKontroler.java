package Projekt.Okienka;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

/**
 * Created by Tomek on 2017-11-17.
 */
public class ModyfikujZamowienieKontroler implements Initializable {

    @FXML
    private DatePicker data_od;
    @FXML
    private DatePicker data_do;
    @FXML
    private ComboBox status_s;
    @FXML
    private Button modyfikuj;
    @FXML
    private Button anuluj;



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String stat;
        String umowa_od;
        String umowa_do;
        status_s.getItems().addAll("Oczekuje", "W trakcie", "Zrealizowane");




        int zamowienia_id = ZamowieniaKontroler.getSelectedZamowienieId();
        try {
            ResultSet rs = ZamowieniaDAO.wyswietlDaneZamowienia(zamowienia_id);
            rs.next();
            stat = rs.getString("status");

            umowa_od = rs.getString("umowa_od");

            umowa_do = rs.getString("umowa_do");


           data_od.setValue(LocalDate.parse(umowa_od));
           data_do.setValue(LocalDate.parse(umowa_do));
           status_s.setValue(stat);




        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void modykikujZamowienie() throws SQLException, ClassNotFoundException {
        try {

           ZamowieniaDAO.updateZamowienie(ZamowieniaKontroler.getSelectedZamowienieId(), status_s.getSelectionModel()
                   .getSelectedItem().toString(),data_od
                   .getValue(),data_do.getValue());

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
ZamowieniaDAO.pokazZamowienie();
    }

    @FXML
    private void przyciskAnulujModyfikacje() {
        Stage stage = (Stage) anuluj.getScene().getWindow();
        stage.close();

    }


}

