package Projekt.KontroleryLogowania;

import Projekt.Modele.Login;
import Projekt.PodlaczonieDoBazy.ConntectToDB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

/**
 * Metoda obsługująca główne okno z modułami, zawierającą ustawione prawa dostępu jak i metode wyloguj.
 *
 * Created by Tomek on 2017-11-17.
 */
public class OknoModowolKontroler implements Initializable{

    Stage dialogeStage = new Stage();
    @FXML private Tab Witaj;
    @FXML private Tab Klienci;
    @FXML private Tab Usługi;
    @FXML private Tab Pracownicy;
    @FXML private Tab Zamównienia;
    @FXML private Tab Finanse;
    @FXML private Tab Praca;


    /**
     * Metoda wylogowująca
     * @param event
     */
    @FXML
    public void wyloguj(ActionEvent event) {
        try {

            ((Node) event.getSource()).getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../KontroleryLogowania/Logowanie.fxml"));
            ConntectToDB.Connector().isClosed();
            Pane layout = loader.load();
            Scene scene = new Scene(layout);
            dialogeStage.setScene(scene);
            dialogeStage.show();

        } catch (Exception e) {
            System.err.println("Problem z zamknieciem polaczenia");
            e.printStackTrace();
        }
    }


    /**
     * Prawa dostępu do poszczególnych zakładek
     * @param location - nieużywant
     * @param resources - nieużywany
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        if(ConntectToDB.getCurrentUser()[3].equals("Administrator")){
            Witaj.setDisable(false);
            Klienci.setDisable(false);
            Usługi.setDisable(false);
            Pracownicy.setDisable(false);
            Zamównienia.setDisable(false);
            Finanse.setDisable(false);
            Praca.setDisable(false);
        }
        if(ConntectToDB.getCurrentUser()[3].equals("Kierownik")){
            Witaj.setDisable(false);
            Klienci.setDisable(true);
            Usługi.setDisable(false);
            Pracownicy.setDisable(false);
            Zamównienia.setDisable(false);
            Finanse.setDisable(false);
            Praca.setDisable(false);
        }
        if(ConntectToDB.getCurrentUser()[3].equals("Handlowiec")){
            Witaj.setDisable(false);
            Klienci.setDisable(false);
            Usługi.setDisable(false);
            Pracownicy.setDisable(true);
            Zamównienia.setDisable(false);
            Finanse.setDisable(true);
            Praca.setDisable(true);
        }
        if(ConntectToDB.getCurrentUser()[3].equals("Ksiegowy")){
            Witaj.setDisable(false);
            Klienci.setDisable(true);
            Usługi.setDisable(true);
            Pracownicy.setDisable(true);
            Zamównienia.setDisable(true);
            Finanse.setDisable(false);
            Praca.setDisable(true);
        }
        if(ConntectToDB.getCurrentUser()[3].equals("Wykonawca")){
            Witaj.setDisable(false);
            Klienci.setDisable(true);
            Usługi.setDisable(true);
            Pracownicy.setDisable(true);
            Zamównienia.setDisable(true);
            Finanse.setDisable(true);
            Praca.setDisable(false);
        }


    }
}
