package desktopapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Desktop extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Parent root = null;
        try {
            URL url = new File("src/main/java/desktopapp/matches_all.fxml").toURI().toURL();
            root = FXMLLoader.load(url);
            primaryStage.setScene(new Scene(root,645, 501));
        } catch (IOException e) {
            e.printStackTrace();
        }
        primaryStage.setTitle("VIS TEAMS");
        primaryStage.show();
    }
}