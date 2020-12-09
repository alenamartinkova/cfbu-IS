package desktopapp;

import business.Team;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;

import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
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

    @FXML
    public void editTeam(ActionEvent event) {
        Parent root = null;
        FXMLLoader loader = new FXMLLoader();
        Stage stage = new Stage();
        try {
            URL url = new File("src/main/java/desktopapp/team_detail.fxml").toURI().toURL();
            loader.setLocation(url);
            root = loader.load();
            stage.setScene(new Scene(root, 645, 501));
        } catch (IOException e) {
            e.printStackTrace();
        }

        TeamDetailController controller = loader.getController();
        controller.initData(team_table.getSelectionModel().getSelectedItem());

        Stage oldWindow = (Stage) ((Node)event.getSource()).getScene().getWindow();
        oldWindow.close();
        stage.setTitle("VIS TEAM DETAIL");
        stage.show();
    }

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
}
