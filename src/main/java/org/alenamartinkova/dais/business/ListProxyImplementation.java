package org.alenamartinkova.dais.business;

import java.sql.SQLException;
import java.util.ArrayList;

public class ListProxyImplementation implements MyList {
    private MyList playerList;
    private MyList teamList;
    private MyList teamMatchList;
    private MyList pitchList;
    private MyList matchList;

    @Override
    public ArrayList<Player> getPlayerList() throws SQLException {
        if (this.playerList == null) {
            System.out.println("Fetching player list");
            this.playerList = new ListsImplementation();
        }
        return this.playerList.getPlayerList();
    }

    @Override
    public ArrayList<TeamMatch> getTeamMatchList() throws SQLException {
        if (this.teamMatchList == null) {
            System.out.println("Fetching team match list");
            this.teamMatchList = new ListsImplementation();
        }
        return this.teamMatchList.getTeamMatchList();
    }

    @Override
    public ArrayList<Team> getTeamList() throws SQLException {
        if (this.teamList == null) {
            System.out.println("Fetching team list");
            this.teamList = new ListsImplementation();
        }
        return this.teamList.getTeamList();
    }

    @Override
    public ArrayList<Pitch> getPitchList() throws SQLException {
        if (this.pitchList == null) {
            System.out.println("Fetching pitch list");
            this.pitchList = new ListsImplementation();
        }
        return this.pitchList.getPitchList();
    }

    @Override
    public ArrayList<Match> getMatchList() throws SQLException {
        if (this.matchList == null) {
            System.out.println("Fetching player list");
            this.matchList = new ListsImplementation();
        }
        return this.matchList.getMatchList();
    }
}
