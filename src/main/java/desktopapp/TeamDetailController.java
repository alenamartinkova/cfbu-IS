package desktopapp;

import business.Team;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TeamDetailController implements Initializable {
    @FXML
    private TextField team_name;
    @FXML
    private TextField team_rank;
    @FXML
    private TextField quarantined_from;
    @FXML
    private TextField covid;

    private Team selectedTeam;

    public void initData(Team team){
        this.selectedTeam = team;
        this.team_name.setText(this.selectedTeam.getName());
        this.team_rank.setText(this.selectedTeam.getRank().toString());
        if(this.selectedTeam.getQuarantinedFrom() == null) {
            this.quarantined_from.setText("null");
        } else {
            this.quarantined_from.setText(this.selectedTeam.getQuarantinedFrom().toString());
        }
        this.covid.setText(this.selectedTeam.getCovid().toString());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) { }

    public void openAllMatches(ActionEvent event) {
        Parent root = null;
        Stage stage = new Stage();
        try {
            URL url = new File("src/main/java/desktopapp/matches_all.fxml").toURI().toURL();
            root = FXMLLoader.load(url);
            stage.setScene(new Scene(root,645, 501));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage oldWindow = (Stage) ((Node)event.getSource()).getScene().getWindow();
        oldWindow.close();
        stage.setTitle("VIS MATCHES");
        stage.show();
    }

    /**
     * Function that handles submit event
     * @param event
     * @throws IOException
     */
    public void onTeamSubmit(ActionEvent event) throws IOException {
        Integer doUpdate = Team.proceedUpdate(this.selectedTeam, this.quarantined_from.getText(), this.covid.getText());

        Parent root = null;
        URL url = null;
        FXMLLoader loader = new FXMLLoader();

        if(doUpdate == 0) {
            openAllMatches(event);
        } else if (doUpdate == -1) {
            System.out.println("Error");
        } else if (doUpdate == -2) {
            url = new File("src/main/java/desktopapp/inform_players.fxml").toURI().toURL();
            loader.setLocation(url);
            root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root, 500, 200));
            InformPlayersController controller = loader.getController();
            controller.initData(this.selectedTeam, this.quarantined_from.getText(), this.covid.getText());
            stage.show();
        }
    }

    /**
     * Function that handles button event
     * @param event
     */
    public void handleButtons(ActionEvent event) {
        String buttonID = ((Button)event.getSource()).getId();
        Parent root = null;
        Stage stage = new Stage();
        Stage oldWindow = null;
        URL url = null;

        switch(buttonID) {
            case "matches_all_button":
                try {
                    url = new File("src/main/java/desktopapp/matches_all.fxml").toURI().toURL();
                    root = FXMLLoader.load(url);
                    stage.setScene(new Scene(root,645, 501));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case "teams_all_button":
                try {
                    url = new File("src/main/java/desktopapp/teams_all.fxml").toURI().toURL();
                    root = FXMLLoader.load(url);
                    stage.setScene(new Scene(root,645, 501));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }

        oldWindow = (Stage) ((Node)event.getSource()).getScene().getWindow();
        oldWindow.close();
        stage.show();
    }
}
