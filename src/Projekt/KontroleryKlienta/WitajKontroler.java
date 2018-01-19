package Projekt.KontroleryKlienta;

import Projekt.PodlaczenieDoBazy.ConntectToDB;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Klasa zawierająca wywołanie metod z informacjami o zalogowanym użytkowniku
 *
 * Created by Tomek on 2017-12-20.
 */

public class WitajKontroler implements Initializable {

    @FXML
    private Label isconn;
    @FXML
    private Label imieNazwisko;
    @FXML
    private Label zalogowanyjako;
    @FXML
    private Label uprawnienia;


    /**
     * Metoda ustawiająca wartości w labelkach.
     *
     * @param location  - nieużywany
     * @param resources - nieużywany
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        imieNazwisko.setText(ConntectToDB.getCurrentUser()[1] + " " + ConntectToDB.getCurrentUser()[2]);
        uprawnienia.setText(ConntectToDB.getCurrentUser()[3]);
        zalogowanyjako.setText(ConntectToDB.getCurrentUser()[4]);
        isconn.setText(ConntectToDB.getCurrentUser()[5]);

    }

}
