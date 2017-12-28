package Projekt.Okienka;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Tomek on 2017-11-17.
 */
public class OknoModowolKontroler implements Initializable{

    Stage dialogeStage = new Stage();



    @FXML
    public void wyloguj(ActionEvent event) {
        try {
            ((Node) event.getSource()).getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../KontroleryLogowania/Logowanie.fxml"));
            Pane layout = loader.load();
            Scene scene = new Scene(layout);
            dialogeStage.setScene(scene);
            dialogeStage.show();

        } catch (Exception e) {
            System.err.println("Problem z zamknieciem polaczenia");
            e.printStackTrace();
        }
    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }
}
