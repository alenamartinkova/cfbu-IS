package desktopapp;

import business.Team;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TeamDetailController implements Initializable {
    @FXML
    private TextField quarantined_from;
    @FXML
    private TextField covid;
    @FXML
    private TextField team_name;
    @FXML
    private TextField team_rank;

    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }

    public void openAllTeams(ActionEvent actionEvent) {
    }

    public void openAllMatches(ActionEvent actionEvent) {
    }
}
