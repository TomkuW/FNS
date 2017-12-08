package Projekt.Okienka;

import Projekt.ConntectToDB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

/**
 * Created by Tomek on 2017-11-14.
 */
public class LoginControler implements Initializable {


    @FXML
    private TextField textLogin;

    @FXML
    private PasswordField textHaslo;

    @FXML
    private Label label;

    Stage dialogeStage = new Stage();
    Connection conn = ConntectToDB.Connector();

    public void loginAction(ActionEvent event) {
        String login = textLogin.getText().toString();
        String haslo = textHaslo.getText().toString();

        try {
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM pracownicy WHERE login = ? and haslo =?");
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, haslo);
            ResultSet rs = preparedStatement.executeQuery();
            if (!rs.next()) {
                label.setText("Niepoprawny login lub hasło!");
            } else {
                label.setText("Zalogowano!");
                Node node = (Node) event.getSource();
                dialogeStage = (Stage) node.getScene().getWindow();
                dialogeStage.close();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("OkienkaFNS.fxml")));
                dialogeStage.setScene(scene);
                dialogeStage.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void wyloguj(ActionEvent event) {
        try {
            ((Node) event.getSource()).getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Logowanie.fxml"));
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
