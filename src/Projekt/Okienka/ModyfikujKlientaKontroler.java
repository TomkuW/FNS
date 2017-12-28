package Projekt.Okienka;

import Projekt.DAO.KlientDAO;
import Projekt.KontroleryKlienta.KlientKontroler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Created by Tomek on 2017-12-13.
 */
public class ModyfikujKlientaKontroler implements Initializable {

    @FXML
    private TextField imie_k;
    @FXML
    private TextField nazwisko_k;
    @FXML
    private TextField pesel_k;
    @FXML
    private TextField miejscowosc_k;
    @FXML
    private TextField ulica_k;
    @FXML
    private TextField nr_dom_k;
    @FXML
    private TextField telefon_k;
    @FXML
    private Button modyfikuj;
    @FXML
    private Button anuluj;



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String imie;
        String nazwisko;
        String pesel;
        String miejscowosc;
        String ulica;
        String nrdomu;
        String telefon;


        int klient_id = KlientKontroler.getSelectedKlientId();
        try {

            ResultSet rs = KlientDAO.wyswietlDaneKlient(klient_id);
            rs.next();
            imie = rs.getString("imie");

            nazwisko = rs.getString("nazwisko");

            pesel = rs.getString("PESEL");

            miejscowosc = rs.getString("miejscowosc");

            ulica = rs.getString("ulica");

            nrdomu = rs.getString("nr_dom");

            telefon = rs.getString("nr_telefon");


            imie_k.setText(imie);
            nazwisko_k.setText(nazwisko);
            pesel_k.setText(pesel);
            miejscowosc_k.setText(miejscowosc);
            ulica_k.setText(ulica);
            nr_dom_k.setText(nrdomu);
            telefon_k.setText(telefon);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void modykikujKlienta() throws SQLException, ClassNotFoundException {
        try {

            KlientDAO.updateKlient(KlientKontroler.getSelectedKlientId(), imie_k.getText(), nazwisko_k.getText(),
                    pesel_k.getText(), miejscowosc_k.getText(), ulica_k.getText(), nr_dom_k.getText(),
                    telefon_k.getText());

            Stage stage = (Stage) modyfikuj.getScene().getWindow();
            stage.close();

        } catch (SQLException e) {
            System.out.print("Błąd podczas modyfikacji klienta" + e);
        }
    }
    @FXML
    private void przyciskAnulujModyfikacje() {
        Stage stage = (Stage) anuluj.getScene().getWindow();
        stage.close();

    }

}
