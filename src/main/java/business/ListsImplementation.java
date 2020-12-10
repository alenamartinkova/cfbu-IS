package business;

import gateways.PitchGateway;
import gateways.PlayerGateway;
import gateways.TeamGateway;
import gateways.TeamMatchGateway;

import java.sql.SQLException;
import java.util.ArrayList;

public class ListsImplementation implements MyList {
    @Override
    public ArrayList<Player> getPlayerList() throws SQLException {
        return getPList();
    }

    @Override
    public ArrayList<TeamMatch> getTeamMatchList() throws SQLException {
        return getTMList();
    }

    @Override
    public ArrayList<Team> getTeamList() throws SQLException {
        return getTList();
    }

    @Override
    public ArrayList<Pitch> getPitchList() throws SQLException {
        return getPiList();
    }

    private static ArrayList<Player> getPList() throws SQLException {
        return PlayerGateway.fetch();
    }

    private static ArrayList<TeamMatch> getTMList() throws SQLException {
        return TeamMatchGateway.fetch();
    }

    private static ArrayList<Team> getTList() throws SQLException {
        return TeamGateway.fetch();
    }

    private static ArrayList<Pitch> getPiList() throws SQLException {
        return PitchGateway.fetch();
    }
}
