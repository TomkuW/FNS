package Projekt.PracownikKontroler;

import Projekt.DAO.PracownikDAO;
import Projekt.PracownikKontroler.PracownikKontroler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ResourceBundle;

/**
 * Klasa zawierająca metode modyfikacji danych pracownika w bazie danych.
 * <p>
 * Created by Tomek on 2017-11-17.
 */
public class ModyfikujPracownikaKontroler implements Initializable {

    @FXML
    private TextField imie_k;
    @FXML
    private TextField nazwisko_k;
    @FXML
    private TextField zawod_k;
    @FXML
    private TextField ulica_k;
    @FXML
    private TextField nr_domu_k;
    @FXML
    private TextField miejscowosc_k;
    @FXML
    private TextField nr_telefon_k;
    @FXML
    private TextField email_k;
    @FXML
    private TextField login_k;
    @FXML
    private TextField haslo_k;
    @FXML
    private TextField wynagrodzenie_k;
    @FXML
    private ComboBox typ_pracownika_k;

    @FXML
    private Button modyfikuj;
    @FXML
    private Button anuluj;

    /**
     * Metoda wyświetlająca dane pracownika w oknie tabeli
     *
     * @param url - nieużywany
     * @param rb  - nieużywany
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        typ_pracownika_k.getItems().addAll("Pracownik", "Kierownik", "Administrator", "Ksiegowy", "Handlowiec");

        String imie;
        String nazwisko;
        String zawod;
        String ulica;
        String nr_domu;
        String miejscowosc;
        String nr_telefon;
        String email;
        String login;
        String haslo;
        String wynagrodzenie;
        String pracownik;

        int pracownik_id = PracownikKontroler.getSelectedPracownikId();
        try {
            ResultSet rs = PracownikDAO.wyswietlDanePracownika(pracownik_id);
            rs.next();
            imie = rs.getString("imie");

            nazwisko = rs.getString("nazwisko");

            zawod = rs.getString("zawod");

            ulica = rs.getString("ulica");

            nr_domu = rs.getString("nr_domu");

            miejscowosc = rs.getString("miejscowosc");

            nr_telefon = rs.getString("nr_telefon");

            email = rs.getString("email");

            login = rs.getString("login");

            haslo = rs.getString("haslo");

            wynagrodzenie = rs.getString("wynagrodzenie");

            pracownik = rs.getString("typ_pracownika");

            imie_k.setText(imie);
            nazwisko_k.setText(nazwisko);
            zawod_k.setText(zawod);
            miejscowosc_k.setText(miejscowosc);
            ulica_k.setText(ulica);
            nr_domu_k.setText(nr_domu);
            nr_telefon_k.setText(nr_telefon);
            email_k.setText(email);
            login_k.setText(login);
            haslo_k.setText(haslo);
            wynagrodzenie_k.setText(wynagrodzenie);
            typ_pracownika_k.setValue(pracownik);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metoda umożliwiająca modyfikacje danych pracownika w bazie danych
     *
     * @throws SQLException           Zgłasza, gdy występuje problem z zapytaniem SQL
     * @throws ClassNotFoundException Zgłasza, gdy występuje problem z użyciem innej klasy
     */
    @FXML
    private void modykikujPracownika() throws SQLException, ClassNotFoundException {
        try {


            PracownikDAO.updatePracownik(PracownikKontroler.getSelectedPracownikId(), imie_k.getText(), nazwisko_k
                            .getText(), zawod_k.getText(), ulica_k.getText(),
                    nr_domu_k.getText(), miejscowosc_k.getText(), email_k.getText(), nr_telefon_k.getText(),
                    wynagrodzenie_k.getText(), login_k.getText(), haslo_k.getText(), typ_pracownika_k.getSelectionModel().getSelectedItem().toString());

            Stage stage = (Stage) modyfikuj.getScene().getWindow();
            stage.close();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Modyfikacja pracownika");
            alert.setHeaderText(null);
            alert.setContentText("Zmodyfikowano pracownika !");

            alert.showAndWait();

            } catch (SQLException e) {
            System.out.print("Błąd podczas modyfikacji klienta" + e);
        }
        PracownikDAO.pokazPracownik();
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

