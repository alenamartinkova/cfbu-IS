package desktopapp;

import business.Match;
import business.Team;
import business.TeamMatch;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;

public class MatchController implements Initializable {
    @FXML
    private TableView<TeamMatch> match_table;
    @FXML
    private TableColumn<TeamMatch, Integer> match_ID;
    @FXML
    private TableColumn<TeamMatch, Integer> first_team;
    @FXML
    private TableColumn<TeamMatch, Integer> second_team;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        match_ID.setCellValueFactory(new PropertyValueFactory<TeamMatch, Integer>("MatchID"));
        first_team.setCellValueFactory(new PropertyValueFactory<TeamMatch, Integer>("firstTeamID"));
        second_team.setCellValueFactory(new PropertyValueFactory<TeamMatch, Integer>("secondTeamID"));

        try {
            ArrayList<TeamMatch> data = TeamMatch.fetch();
            match_table.getItems().setAll(data);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
