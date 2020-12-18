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
    private String c;
    private String n;

    public void initData(Team t, String c, String n) {
        this.team = t;
        this.c = c;
        this.n = n;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { }

    public void handleButtons(ActionEvent event) throws IOException {
        String buttonID = ((Button)event.getSource()).getId();
        Parent root;
        Stage stage = new Stage();
        Stage oldWindow;
        URL url;
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
            StopMatchController controller = loader.getController();
            controller.initData(this.team, this.c, this.n);

        } catch (IOException e) {
            e.printStackTrace();
        }

        oldWindow = (Stage) ((Node)event.getSource()).getScene().getWindow();
        oldWindow.close();
        stage.show();
    }
}
