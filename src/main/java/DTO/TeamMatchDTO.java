package DTO;

import business.TeamMatch;

public class TeamMatchDTO {
    Integer teamMatchID;
    Integer matchID;
    Integer firstTeamID;
    Integer secondTeamID;
    Integer firstRefereeID;
    Integer secondRefereeID;
    Integer firstTeamGoals;
    Integer secondTeamGoals;

    public TeamMatchDTO(){};
    public TeamMatchDTO(Integer tmID, Integer mID, Integer ftID, Integer stID, Integer frID, Integer srID, Integer ftGoals, Integer stGoals) {
        this.teamMatchID = tmID;
        this.matchID = mID;
        this.firstTeamID = ftID;
        this.secondTeamID = stID;
        this.firstRefereeID = frID;
        this.secondRefereeID = srID;
        this.firstTeamGoals = ftGoals;
        this.secondTeamGoals = stGoals;
    }

    public TeamMatch toBO() {
        TeamMatch teamMatch = new TeamMatch(this.teamMatchID, this.matchID, this.firstTeamID, this.secondTeamID, this.firstRefereeID, this.secondRefereeID, this.firstTeamGoals, this.secondTeamGoals);
        return teamMatch;
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

}
