package business;

import gateways.MatchGateway;
import gateways.PitchGateway;
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

    public String getFirstTeamName() {
        return Team.fetchByID(this.firstTeamID).getName();
    }

    public String getSecondTeamName() {
        return Team.fetchByID(this.secondTeamID).getName();
    }

    public String getDate() {
        return Match.fetchByID(this.matchID).getDate().toString();
    }

    public static ArrayList<TeamMatch> fetch() throws SQLException {
        return TeamMatchGateway.fetch();
    }

    public Team getFirstTeam() {
        return Team.fetchByID(this.firstTeamID);
    }

    public Team getSecondTeam() {
        return Team.fetchByID(this.secondTeamID);
    }

    public Pitch getPitch() {
        Integer id = MatchGateway.fetchByID(this.matchID).getPitchID();
        return PitchGateway.fetchByID(id);
    }
}

