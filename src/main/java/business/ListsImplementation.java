package business;

import DTO.*;
import gateways.*;

import java.sql.SQLException;
import java.util.ArrayList;

public class ListsImplementation implements MyList {
    @Override
    public ArrayList<PlayerDTO> getPlayerList() throws SQLException {
        return getPList();
    }

    @Override
    public ArrayList<TeamMatchDTO> getTeamMatchList() throws SQLException {
        return getTMList();
    }

    @Override
    public ArrayList<TeamDTO> getTeamList() throws SQLException {
        return getTList();
    }

    @Override
    public ArrayList<PitchDTO> getPitchList() throws SQLException {
        return getPiList();
    }

    @Override
    public ArrayList<MatchDTO> getMatchList() throws SQLException {
        return getMList();
    }

    private static ArrayList<PlayerDTO> getPList() throws SQLException {
        return PlayerGateway.fetch();
    }

    private static ArrayList<TeamMatchDTO> getTMList() throws SQLException {
        return TeamMatchGateway.fetch();
    }

    private static ArrayList<TeamDTO> getTList() throws SQLException {
        return TeamGateway.fetch();
    }

    private static ArrayList<PitchDTO> getPiList() throws SQLException {
        return PitchGateway.fetch();
    }

    private static ArrayList<MatchDTO> getMList() throws SQLException {
        return MatchGateway.fetch();
    }
}
