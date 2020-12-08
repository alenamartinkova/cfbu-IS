package business;

import gateways.TeamMatchGateway;

import java.sql.SQLException;
import java.util.ArrayList;

public class TeamMatch {
    Integer teamMatchID;
    Integer matchID;
    Integer firstTeamID;
    Integer secondTeamID;
    Integer firstRefereeID;
    Integer secondRefereeID;
    Integer firstTeamGoals;
    Integer secondTeamGoals;

    public TeamMatch(Integer tmID, Integer mID, Integer ftID, Integer stID, Integer frID, Integer srID, Integer ftGoals, Integer stGoals) {
        this.teamMatchID = tmID;
        this.matchID = mID;
        this.firstTeamID = ftID;
        this.secondTeamID = stID;
        this.firstRefereeID = frID;
        this.secondRefereeID = srID;
        this.firstTeamGoals = ftGoals;
        this.secondTeamGoals = stGoals;
    }

    public Integer getMatchID() {
        return this.matchID;
    }
    public Integer getFirstTeamID() {
        return this.firstTeamID;
    }

    public Integer getSecondTeamID() {
        return this.secondTeamID;
    }

    public Integer getFirstRefereeID() {
        return this.firstRefereeID;
    }

    public Integer getFirstTeamGoals() {
        return this.firstTeamGoals;
    }

    public Integer getSecondRefereeID() {
        return this.secondRefereeID;
    }

    public Integer getSecondTeamGoals() {
        return this.secondTeamGoals;
    }

    public Integer getTeamMatchID() {
        return this.teamMatchID;
    }

    public static ArrayList<TeamMatch> fetch() throws SQLException {
        return TeamMatchGateway.fetch();
    }
}


