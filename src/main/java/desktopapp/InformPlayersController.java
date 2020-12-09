package desktopapp;

import business.Team;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class InformPlayersController implements Initializable {

    private Team team;
    private String q;
    private String c;

    public void initData(Team t, String q, String c) {
        this.team = t;
        this.q = q;
        this.c = c;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { }

    public void handleButtons(ActionEvent event) throws IOException {
        String buttonID = ((Button)event.getSource()).getId();
        Parent root = null;
        Stage stage = new Stage();
        Stage oldWindow = null;
        URL url = null;
        FXMLLoader loader = new FXMLLoader();

        switch(buttonID) {
            case "yes":
                Team.storeInfo();
                break;
        }

        try {
            url = new File("src/main/java/desktopapp/stop_matches.fxml").toURI().toURL();
            loader.setLocation(url);
            root = loader.load();
            stage.setScene(new Scene(root, 400, 200));
            // need new controller for this info window and do the same as here
            StopMatchController controller = loader.getController();
            controller.initData(this.team, this.q, this.c);

        } catch (IOException e) {
            e.printStackTrace();
        }

        oldWindow = (Stage) ((Node)event.getSource()).getScene().getWindow();
        oldWindow.close();
        stage.show();
    }
}
