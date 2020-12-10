package business;

import DTO.*;

import java.sql.SQLException;
import java.util.ArrayList;

public interface MyList {
    ArrayList<PlayerDTO> getPlayerList() throws SQLException;
    ArrayList<TeamMatchDTO> getTeamMatchList() throws SQLException;
    ArrayList<TeamDTO> getTeamList() throws SQLException;
    ArrayList<PitchDTO> getPitchList() throws SQLException;
    ArrayList<MatchDTO> getMatchList() throws SQLException;
}
