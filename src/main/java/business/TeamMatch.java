package business;

import gateways.MatchGateway;
import gateways.PitchGateway;
import gateways.TeamGateway;
import gateways.TeamMatchGateway;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class TeamMatch {
    Integer teamMatchID;
    Integer matchID;
    Integer firstTeamID;
    Integer secondTeamID;
    Integer firstRefereeID;
    Integer secondRefereeID;
    Integer firstTeamGoals;
    Integer secondTeamGoals;

    public TeamMatch(){};
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

    public static Integer proceedUpdate(TeamMatch match, String firstTeamName, String secondTeamName, String pitchName, String date) throws SQLException {
        if(firstTeamName == secondTeamName) {
            return -3;
        }

        Pitch pitch = PitchGateway.fetchByName(pitchName);
        Team firstTeam = TeamGateway.fetchByName(firstTeamName);
        Team secondTeam = TeamGateway.fetchByName(secondTeamName);

        ArrayList<Match> matches = MatchGateway.fetch();

        for(int i = 0; i < matches.size(); i++) {
            if(matches.get(i).getMatchID() != match.getMatchID()) {

                if(matches.get(i).getDate().toString().equalsIgnoreCase(date)) {
                   TeamMatch tm = TeamMatchGateway.fetchByMatchID(matches.get(i).getMatchID());

                   if(checkTeamCollisions(tm, firstTeam) || checkTeamCollisions(tm, secondTeam)) {
                       return -2;
                   }
                }

                if(matches.get(i).getPitchID() == pitch.getPitchID() && matches.get(i).getDate().toString().equalsIgnoreCase(date)) {
                    return -1;
                }
            }
        }

        return 0;
    }

    private static boolean checkTeamCollisions(TeamMatch teamMatch, Team team) {
        if(teamMatch.getFirstTeamID() == team.getId() || teamMatch.getSecondTeamID() == team.getId()) {
            return  true;
        }

        return false;
    }

    public static void update(TeamMatch match, String firstTeamName, String secondTeamName, String pitchName, String date) throws SQLException {
        Integer pitch = PitchGateway.fetchByName(pitchName).getPitchID();
        Integer firstTeam = TeamGateway.fetchByName(firstTeamName).getId();
        Integer secondTeam = TeamGateway.fetchByName(secondTeamName).getId();
        Match oldMatchData = MatchGateway.fetchByID(match.getMatchID());

        TeamMatch teamMatch = new TeamMatch(match.getTeamMatchID(), match.getMatchID(), firstTeam, secondTeam, match.getFirstRefereeID(), match.getSecondRefereeID(), match.getFirstTeamGoals(), match.getSecondTeamGoals());
        Match matchNew = new Match(match.getMatchID(), oldMatchData.getPostponed(), date, pitch);
        TeamMatchGateway.update(teamMatch);
        MatchGateway.update(matchNew);
    }

    public static void storeError() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("./logs/test.txt"));
        lines.add("Update of match aborted.");

        Files.write(Paths.get("./logs/test.txt"), lines,
                StandardCharsets.UTF_8);
    }
}


