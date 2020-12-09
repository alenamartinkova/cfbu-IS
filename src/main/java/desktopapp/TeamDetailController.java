package desktopapp;

import business.Team;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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

    public void openAllTeams(ActionEvent event) {
        Parent root = null;
        Stage stage = new Stage();
        try {
            URL url = new File("src/main/java/desktopapp/teams_all.fxml").toURI().toURL();
            root = FXMLLoader.load(url);
            stage.setScene(new Scene(root, 645, 501));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage oldWindow = (Stage) ((Node)event.getSource()).getScene().getWindow();
        oldWindow.close();
        stage.setTitle("VIS TEAMS");
        stage.show();
    }

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

    public void onTeamSubmit(ActionEvent event) throws SQLException {
        Integer doUpdate = Team.proceedUpdate();

        if(doUpdate == 0) {
            Team.update(this.selectedTeam);
        }
    }
}
