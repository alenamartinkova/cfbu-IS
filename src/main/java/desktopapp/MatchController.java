package desktopapp;

import business.ListProxyImplementation;
import business.MyList;
import business.TeamMatch;
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

public class MatchController implements Initializable {
    @FXML
    private TableView<TeamMatch> match_table;
    @FXML
    private TableColumn<TeamMatch, Integer> match_ID;
    @FXML
    private TableColumn<TeamMatch, String> first_team;
    @FXML
    private TableColumn<TeamMatch, String> second_team;
    @FXML
    private TableColumn<TeamMatch, String> date;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        match_ID.setCellValueFactory(new PropertyValueFactory<>("MatchID"));
        first_team.setCellValueFactory(new PropertyValueFactory<>("firstTeamName"));
        second_team.setCellValueFactory(new PropertyValueFactory<>("secondTeamName"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));

        try {
            MyList list = new ListProxyImplementation();
            ArrayList<TeamMatch> data = list.getTeamMatchList();
            match_table.getItems().setAll(data);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Function that handles edit match button event
     * @param event
     * @throws SQLException
     */
    public void editMatch(ActionEvent event) throws SQLException {
        Parent root;
        FXMLLoader loader = new FXMLLoader();
        Stage stage = new Stage();
        try {
            URL url = new File("src/main/java/desktopapp/match_detail.fxml").toURI().toURL();
            loader.setLocation(url);
            root = loader.load();
            stage.setScene(new Scene(root, 645, 501));
        } catch (IOException e) {
            e.printStackTrace();
        }

        MatchDetailController controller = loader.getController();
        controller.initData(match_table.getSelectionModel().getSelectedItem());
        Stage oldWindow = (Stage) ((Node)event.getSource()).getScene().getWindow();
        oldWindow.close();
        stage.setTitle("VIS MATCH DETAIL");
        stage.show();
    }
}
