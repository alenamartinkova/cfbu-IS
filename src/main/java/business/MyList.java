package business;

import java.sql.SQLException;
import java.util.ArrayList;

public interface MyList {
    ArrayList<Player> getPlayerList() throws SQLException;
    ArrayList<TeamMatch> getTeamMatchList() throws SQLException;
    ArrayList<Team> getTeamList() throws SQLException;
    ArrayList<Pitch> getPitchList() throws SQLException;
    ArrayList<Match> getMatchList() throws SQLException;
}
