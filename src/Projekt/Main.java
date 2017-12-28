package Projekt;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * Created by Tomek on 2017-10-18.
 *
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {


        //obiekt umożliwajacy zaladowanie pliku fxml
        //(OknoModulow.class.getResource("Logowanie do systemu.fxml") - pozwala odnieśc się do lokalizacji pliku
        // fxml, lokalizacja jego jest ta sama co naszej klasy OknoModolow
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("KontroleryLogowania/Logowanie.fxml"));
        //laduje plik fxml i przypisuje do obiektu typu Parent który moze przyjmować wartości tj. kontenery np
        // .BorderPane


        Parent layout= fxmlLoader.load();
        // tworze obiekt scene i przesyłam w parametrze nasz layout z szablonu fxml
        Scene scene = new Scene(layout);
        primaryStage.setTitle("FNS");
        primaryStage.setScene(scene);
        primaryStage.show();


    }

}
