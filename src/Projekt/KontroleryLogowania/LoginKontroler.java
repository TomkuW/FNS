package Projekt.KontroleryLogowania;

import Projekt.Modele.Login;
import Projekt.PodlaczenieDoBazy.ConntectToDB;
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
 * Klasa zawierająca kontroler logowania, metode sprawdzająca poprawność hasła i loginu
 * <p>
 * Created by Tomek on 2017-11-14.
 */

public class LoginKontroler implements Initializable {

    public Login loginModel = new Login();

    @FXML
    private TextField textLogin;

    @FXML
    private PasswordField textHaslo;

    @FXML
    private Label label;

    Stage dialogeStage = new Stage();
    Connection conn = ConntectToDB.Connector();

    String login;
    String haslo;

    /**
     * Metoda logująca do programu. Gdy użytkownik poda prawidłowy login i hasło, zostanie przekierowany do głównego okna programu
     *
     * @param event - nieużywany
     */
    public void loginAction(ActionEvent event) {
        this.login = textLogin.getText().toString();
        this.haslo = textHaslo.getText().toString();

        try {
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM pracownicy WHERE login = ? and haslo =?");
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, haslo);
            ResultSet rs = preparedStatement.executeQuery();
            if (!rs.next()) {
                label.setText("Niepoprawny login lub hasło!");
            } else {

                String[] currentUser = new String[6];
                currentUser[0] = rs.getString("pracownik_id");
                currentUser[1] = rs.getString("imie");
                currentUser[2] = rs.getString("nazwisko");
                currentUser[3] = rs.getString("typ_pracownika");
                currentUser[4] = rs.getString("login");
                currentUser[5] = "Połączono";
                ConntectToDB.setCurrentUser(currentUser);

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

    /**
     * Metoda wyloguj. Gdy użytkownik uruchomi metodę, zostanie przekierowany do głównego okna programu.
     *
     * @param event - nie używany
     */
    @FXML
    public void wyloguj(ActionEvent event) {
        try {
            ((Node) event.getSource()).getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Logowanie.fxml"));
            Pane layout = loader.load();
            Scene scene = new Scene(layout);
            conn.isClosed();
            dialogeStage.setTitle("Logowanie");
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
