package desktopapp;

import business.Match;
import business.Pitch;
import business.Team;
import business.TeamMatch;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MatchDetailController implements Initializable {
    @FXML
    private ChoiceBox<String> firstTeamSelect;
    @FXML
    private ChoiceBox<String> secondTeamSelect;
    @FXML
    private ChoiceBox<String> pitchSelect;
    @FXML
    private TextField date;
    private TeamMatch selectedMatch;

    public void initData(TeamMatch match) throws SQLException {
        this.selectedMatch = match;
        ArrayList<Team> teams = Team.fetch();
        ArrayList<Pitch> pitches = Pitch.fetch();
        for (int i = 0; i < teams.size(); i++) {
            this.firstTeamSelect.getItems().add(teams.get(i).getName());
        }
        this.firstTeamSelect.setValue(this.selectedMatch.getFirstTeam().getName());

        for (int i = 0; i < teams.size(); i++) {
            this.secondTeamSelect.getItems().add(teams.get(i).getName());
        }
        this.secondTeamSelect.setValue(this.selectedMatch.getSecondTeam().getName());

        for (int i = 0; i < pitches.size(); i++) {
            this.pitchSelect.getItems().add(pitches.get(i).getName());
        }

        this.pitchSelect.setValue(this.selectedMatch.getPitch().getName());
        this.date.setText(this.selectedMatch.getDate());
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

        stage.setTitle("VIS MATCHES");
        stage.show();
    }

    public void onMatchSubmit(ActionEvent event) throws SQLException, IOException {
        Integer doUpdate = TeamMatch.proceedUpdate(this.selectedMatch, this.firstTeamSelect.getValue(), this.secondTeamSelect.getValue(), this.pitchSelect.getValue(), this.date.getText());

        if(doUpdate == 0) {
            TeamMatch.update(this.selectedMatch, this.firstTeamSelect.getValue(), this.secondTeamSelect.getValue(), this.pitchSelect.getValue(), this.date.getText());
        } else {
            Parent root = null;
            URL url = null;

            if(doUpdate == -3) {
                url = new File("src/main/java/desktopapp/same_team.fxml").toURI().toURL();
            } else if (doUpdate == -2) {
                url = new File("src/main/java/desktopapp/date_collision.fxml").toURI().toURL();
            } else if (doUpdate == -1) {
                url = new File("src/main/java/desktopapp/pitch_collision.fxml").toURI().toURL();
            }
            root = FXMLLoader.load(url);
            Stage stage = new Stage();
            stage.setScene(new Scene(root, 300, 150));
            stage.show();
        }
    }
}
