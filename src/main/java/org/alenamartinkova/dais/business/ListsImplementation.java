package org.alenamartinkova.dais.business;

import org.alenamartinkova.dais.gateways.*;

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

    @Override
    public ArrayList<Match> getMatchList() throws SQLException {
        return getMList();
    }

    private static ArrayList<Player> getPList() throws SQLException {
        return Player.arrayListToBO(PlayerGateway.fetch());
    }

    private static ArrayList<TeamMatch> getTMList() throws SQLException {
        return TeamMatch.arrayListToBO(TeamMatchGateway.fetch());
    }

    private static ArrayList<Team> getTList() throws SQLException {
        return Team.arrayListToBO(TeamGateway.fetch());
    }

    private static ArrayList<Pitch> getPiList() throws SQLException {
        return Pitch.arrayListToBO(PitchGateway.fetch());
    }

    private static ArrayList<Match> getMList() throws SQLException {
        return Match.arrayListToBO(MatchGateway.fetch());
    }
}
