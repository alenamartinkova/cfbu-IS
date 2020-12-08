package desktopapp;

import business.Team;
import gateways.TeamGateway;
import javafx.beans.property.Property;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;

import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TeamController implements Initializable {
    @FXML
    private TableView<Team> team_table;
    @FXML
    private TableColumn<Team, Integer> team_ID;
    @FXML
    private TableColumn<Team, String> team_name;
    @FXML
    private TableColumn<Team, Integer> team_rank;
    @FXML
    private TableColumn<Team, Integer> team_league;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        team_ID.setCellValueFactory(new PropertyValueFactory<Team, Integer>("id"));
        team_name.setCellValueFactory(new PropertyValueFactory<Team, String>("name"));
        team_rank.setCellValueFactory(new PropertyValueFactory<Team, Integer>("rank"));
        team_league.setCellValueFactory(new PropertyValueFactory<Team, Integer>("leagueID"));

        try {
            ArrayList<Team> teams = Team.fetch();
            team_table.getItems().setAll(teams);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
