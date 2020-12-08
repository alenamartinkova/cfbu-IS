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
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MatchDetailController implements Initializable {
    @FXML
    private ChoiceBox<Team> firstTeamSelect;
    @FXML
    private ChoiceBox<Team> secondTeamSelect;
    @FXML
    private ChoiceBox<Pitch> pitchSelect;
    @FXML
    private TextField date;
    private TeamMatch selectedMatch;

    public void initData(TeamMatch match) throws SQLException {
        this.selectedMatch = match;
        ArrayList<Team> teams = Team.fetch();
        for (int i = 0; i < teams.size(); i++) {
            this.firstTeamSelect.getItems().add(teams.get(i));
        }
        this.firstTeamSelect.setValue(this.selectedMatch.getFirstTeam());
        this.secondTeamSelect.getItems().addAll(Team.fetch());
        this.secondTeamSelect.setValue(this.selectedMatch.getSecondTeam());
        this.pitchSelect.getItems().addAll(Pitch.fetch());
        this.pitchSelect.setValue(this.selectedMatch.getPitch());
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
}
