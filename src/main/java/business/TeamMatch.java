package business;

import DTO.TeamMatchDTO;
import gateways.MatchGateway;
import gateways.PitchGateway;
import gateways.TeamGateway;
import gateways.TeamMatchGateway;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
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
    MyList list;

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
        this.list = new ListProxyImplementation();
    }

    public TeamMatchDTO toDTO() {
        TeamMatchDTO teamMatchDTO = new TeamMatchDTO(this.teamMatchID, this.matchID, this.firstTeamID, this.secondTeamID, this.firstRefereeID, this.secondRefereeID, this.firstTeamGoals, this.secondTeamGoals);
        return teamMatchDTO;
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

    public Team getFirstTeam() {
        return Team.fetchByID(this.firstTeamID);
    }

    public Team getSecondTeam() {
        return Team.fetchByID(this.secondTeamID);
    }

    public Pitch getPitch() {
        Integer id = MatchGateway.fetchByID(this.matchID).getPitchID();
        return PitchGateway.fetchByID(id).toBO();
    }

    /**
     * Function that proceeds update and decides what to do next
     * @param match updated TeamMatch object
     * @param firstTeamName selected first team name
     * @param secondTeamName selected second team name
     * @param pitchName selected pitch name
     * @param date selected date
     * @return Integer based on which we decide later
     * @throws SQLException
     */
    public Integer proceedUpdate(TeamMatch match, String firstTeamName, String secondTeamName, String pitchName, String date) throws SQLException {
        if(firstTeamName == secondTeamName) {
            return -3;
        }

        Team firstTeam = null;
        Team secondTeam = null;
        Pitch pitch = null;
        // use lazy loading to load data

        ArrayList<Pitch> pitches = this.list.getPitchList();
        ArrayList<Team> teams = this.list.getTeamList();
        ArrayList<Match> matches = this.list.getMatchList();

        for(int i = 0; i < teams.size(); i++) {
            if(teams.get(i).getName() == firstTeamName) {
                firstTeam = teams.get(i);
            } else if (teams.get(i).getName() == secondTeamName) {
                secondTeam = teams.get(i);
            }

            if(firstTeam != null && secondTeam != null) {
                break;
            }
        }

        for(int i = 0; i < pitches.size(); i++) {
            if(pitches.get(i).getName() == pitchName) {
                pitch = pitches.get(i);
                break;
            }
        }


        for(int i = 0; i < matches.size(); i++) {
            if(matches.get(i).getMatchID() != match.getMatchID()) {

                if(matches.get(i).getDate().toString().equalsIgnoreCase(date)) {
                   TeamMatch tm = TeamMatchGateway.fetchByMatchID(matches.get(i).getMatchID()).toBO();

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

    /**
     * Function that checks if team is in selected match
     * @param teamMatch selected TeamMatch object
     * @param team compared Team object
     * @return boolean value if team is in selected match
     */
    private static boolean checkTeamCollisions(TeamMatch teamMatch, Team team) {
        if(teamMatch.getFirstTeamID() == team.getId() || teamMatch.getSecondTeamID() == team.getId()) {
            return  true;
        }

        return false;
    }

    /**
     * Function that calls update on Match gateway and TeamMatch gateway
     * @param match updated TeamMatch object
     * @param firstTeamName selected first team name
     * @param secondTeamName selected second team name
     * @param pitchName selected pitch name
     * @param date selected date
     * @throws SQLException
     */
    public static void update(TeamMatch match, String firstTeamName, String secondTeamName, String pitchName, String date) throws SQLException {
        Integer pitch = PitchGateway.fetchByName(pitchName).getPitchID();
        Integer firstTeam = TeamGateway.fetchByName(firstTeamName).getId();
        Integer secondTeam = TeamGateway.fetchByName(secondTeamName).getId();
        Match oldMatchData = MatchGateway.fetchByID(match.getMatchID()).toBO();

        TeamMatch teamMatch = new TeamMatch(match.getTeamMatchID(), match.getMatchID(), firstTeam, secondTeam, match.getFirstRefereeID(), match.getSecondRefereeID(), match.getFirstTeamGoals(), match.getSecondTeamGoals());
        Match matchNew = new Match(match.getMatchID(), oldMatchData.getPostponed(), date, pitch);
        TeamMatchGateway.update(teamMatch.toDTO());
        MatchGateway.update(matchNew.toDTO());
    }

    /**
     * Function that stores information about aborting match update
     * @throws IOException
     */
    public static void storeError() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("./logs/test.txt"));
        lines.add("Update of match aborted.");

        Files.write(Paths.get("./logs/test.txt"), lines,
                StandardCharsets.UTF_8);
    }

    public static ArrayList<TeamMatch> arrayListToBO(ArrayList<TeamMatchDTO> teamDTOS) {
        ArrayList<TeamMatch> t = new ArrayList<>();

        for(int i = 0; i < teamDTOS.size(); i++) {
            t.add(teamDTOS.get(i).toBO());
        }
        return t;
    }
}


