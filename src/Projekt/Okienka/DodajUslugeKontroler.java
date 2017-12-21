package Projekt.Okienka;

import Projekt.ConntectToDB;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Fabian on 2017-12-20.
 */
public class DodajUslugeKontroler {


    @FXML
    private TextField nazwa_pakietu;
    @FXML
    private RadioButton technologia_pakietu1;
    @FXML
    private RadioButton technologia_pakietu2;
    @FXML
    private RadioButton predkosc_pakietu1;
    @FXML
    private RadioButton predkosc_pakietu2;
    @FXML
    private RadioButton predkosc_pakietu3;
    @FXML
    private RadioButton predkosc_pakietu4;
    @FXML
    private RadioButton predkosc_pakietu5;
    @FXML
    private RadioButton predkosc_pakietu6;
    @FXML
    private TextField cena_pakietu;
    @FXML
    private RadioButton okres_pakietu1;
    @FXML
    private RadioButton okres_pakietu2;
    @FXML
    private Button dodaj;
    @FXML
    private Button anuluj;

    @FXML
    protected void dodawanie_uslugi() throws SQLException {

        String nazwa = nazwa_pakietu.getText();
        String technologia = technol();
        String predkosc = predkosc();
        String cena = cena_pakietu.getText();
        String okres = okres();

        String sql = "insert into pakiety (nazwa,technologia,predkosc,cena,okres) values(?,?,?,?,?)";
        Connection conn = ConntectToDB.Connector();
        PreparedStatement prepStmt = null;
        try {

            prepStmt = conn.prepareStatement(sql);
            prepStmt.setString(1, nazwa);
            prepStmt.setString(2, technologia);
            prepStmt.setString(3, predkosc);
            prepStmt.setString(4, cena);
            prepStmt.setString(5, okres);
            prepStmt.executeUpdate();


            Stage stage = (Stage) dodaj.getScene().getWindow();
            stage.close();


        } catch (SQLException e) {

            System.out.print("Błąd podczas dodawania usługi" + e);
            throw e;
        }




    }

    public String technol() {
        if(technologia_pakietu1.isSelected()) return "FTTP";
        else  if(technologia_pakietu2.isSelected()) return "ADLS+";
        else return null;

    }

    public String predkosc() {
        if(predkosc_pakietu1.isSelected()) return "20Mbit/s - /2Mbit/s";
        else  if(predkosc_pakietu2.isSelected()) return "40 Mbit/s - 2Mbit/s";
        if(predkosc_pakietu3.isSelected()) return "60 Mbit/s - 2Mbit/s";
        else  if(predkosc_pakietu4.isSelected()) return "6 Mbit/s - 512 kbit/s";
        if(predkosc_pakietu5.isSelected()) return "10 Mbit/s - 512 kbit/s ";
        else  if(predkosc_pakietu6.isSelected()) return "15 Mbit/s - 512 kbit/s";
        else return null;
    }

    public String okres() {
        if(okres_pakietu1.isSelected()) return "12";
        else  if(okres_pakietu2.isSelected()) return "24";
        else return null;

    }
    @FXML
    private void przyciskAnulujDodawanie() {
        Stage stage = (Stage) anuluj.getScene().getWindow();
        stage.close();

    }



}






