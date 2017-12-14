package Projekt.Okienka;

import Projekt.ConntectToDB;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Tomek on 2017-12-13.
 */
public class DodajKlientaKontroler {

    @FXML
    private TextField imie_klienta;
    @FXML
    private TextField nazwisko_klienta;
    @FXML
    private TextField pesel_klienta;
    @FXML
    private TextField miejscowosc_klienta;
    @FXML
    private TextField ulica_klienta;
    @FXML
    private TextField nr_dom_klienta;
    @FXML
    private TextField telefon_klienta;
    @FXML
    private Button dodaj;
    @FXML
    private Button anuluj;

    @FXML
    protected void dodawanie() throws SQLException {

        String imie = imie_klienta.getText();
        String nazwisko = nazwisko_klienta.getText();
        String PESEL = pesel_klienta.getText();
        String miejscowosc = miejscowosc_klienta.getText();
        String ulica = ulica_klienta.getText();
        String nr_dom = nr_dom_klienta.getText();
        String nr_telefon = telefon_klienta.getText();

        String sql = "insert into klienci (imie,nazwisko,PESEL,miejscowosc,ulica,nr_dom,nr_telefon) values(?,?,?,?,?," +
                "?,?)";
        Connection conn = ConntectToDB.Connector();
        PreparedStatement prepStmt = null;
        try {

            prepStmt = conn.prepareStatement(sql);
            prepStmt.setString(1, imie);
            prepStmt.setString(2, nazwisko);
            prepStmt.setString(3, PESEL);
            prepStmt.setString(4, miejscowosc);
            prepStmt.setString(5, ulica);
            prepStmt.setString(6, nr_dom);
            prepStmt.setString(7, nr_telefon);
            prepStmt.executeUpdate();


            Stage stage = (Stage) dodaj.getScene().getWindow();
            stage.close();


        } catch (SQLException e) {

            System.out.print("Błąd podczas dodawania klienta" + e);
            throw e;
        }

    }

    @FXML
    private void przyciskAnulujDodawanie() {
        Stage stage = (Stage) anuluj.getScene().getWindow();
        stage.close();

    }

}
