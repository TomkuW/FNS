package Projekt.Okienka;

import Projekt.KontroleryZamowienia.ZamowieniaKontroler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Klasa zawierająca metode modyfikacji danych Zamowienia w bazie danych.
 *
 * Created by Tomek on 2017-11-17.
 */
public class ModyfikujPracaKontroler implements Initializable {

    @FXML
    private ComboBox status_z;
    @FXML
    private Button modyfikuj;
    @FXML
    private Button anuluj;

    /**
     * Metoda wyświetlająca dane zamowienia w oknie tabeli
     *
     * @param url - nieużywany
     * @param rb  - nieużywany
     */
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

    /**
     * Metoda umożliwiająca modyfikacje danych zamowienia w bazie danych
     *
     * @throws SQLException           Zgłasza, gdy występuje problem z zapytaniem SQL
     * @throws ClassNotFoundException Zgłasza, gdy występuje problem z użyciem innej klasy
     */
    @FXML
    private void modykikujZamowienie() throws SQLException, ClassNotFoundException {

        try {
            if(PracaKontroler.getSelectedPracaZamowienieId() != 0) {
                PracaDAO.updatePZamowienie(PracaKontroler.getSelectedPracaZamowienieId(), status_z.getSelectionModel()
                        .getSelectedItem().toString());

                Stage stage = (Stage) modyfikuj.getScene().getWindow();
                stage.setTitle("Modyfikacja zamówienia");
                stage.close();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("Status wybranego zamówienie został zmodyfikowany!");

                alert.showAndWait();
            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Błąd modyfikacji statusu zamówienia");
                alert.setHeaderText(null);
                alert.setContentText("Aby zmodyfikować status danego zamówienia, wybierz konkretny wiersz zamówienia " +
                        "z tabeli");

                alert.showAndWait();

            }
        } catch (SQLException e) {
            System.out.print("Błąd podczas modyfikacji klienta" + e);
        }
    PracaDAO.pokazPraca();
    }

    /**
     * Metoda umożliwiająca anulacje okna modyfikazji bez wprowadzonych zmian
     */
    @FXML
    private void przyciskAnulujModyfikacje() {

        Stage stage = (Stage) anuluj.getScene().getWindow();
        stage.close();

    }
}

