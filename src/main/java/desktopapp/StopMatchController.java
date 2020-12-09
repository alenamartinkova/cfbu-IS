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

public class StopMatchController implements Initializable {

    private Team team;
    private String q;
    private String c;

    public void initData(Team t, String q, String c) {
        this.team = t;
        this.q = q;
        this.c = c;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {};

    /**
     * Function that handles button event
     * @param event
     * @throws IOException
     */
    public void handleStopMatch(ActionEvent event) throws IOException {
        String buttonID = ((Button)event.getSource()).getId();
        Parent root = null;
        Stage stage = new Stage();
        Stage oldWindow = null;
        URL url = null;
        switch(buttonID) {
            case "stop":
                Team.stopMatchesAndUpdate(this.team, this.q, this.c);
                try {
                    url = new File("src/main/java/desktopapp/matches_all.fxml").toURI().toURL();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case "dont_stop":
                try {
                    url = new File("src/main/java/desktopapp/matches_all.fxml").toURI().toURL();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }

        root = FXMLLoader.load(url);
        stage.setScene(new Scene(root, 645, 501));
        oldWindow = (Stage) ((Node)event.getSource()).getScene().getWindow();
        oldWindow.close();
        stage.show();
    }
}
