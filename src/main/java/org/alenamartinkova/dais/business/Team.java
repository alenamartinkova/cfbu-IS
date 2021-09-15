package org.alenamartinkova.dais.business;

import org.alenamartinkova.dais.DTO.TeamDTO;
import org.alenamartinkova.dais.gateways.MatchGateway;
import org.alenamartinkova.dais.gateways.TeamGateway;
import org.alenamartinkova.dais.gateways.TeamMatchGateway;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Team {
    Integer teamid;
    Integer rank;
    String name;
    Integer leagueID;
    Integer covid;
    Timestamp quarantinedFrom;

    public Team(Integer id, Integer l,  String n, Integer r, Integer c, String q) {
        this.teamid = id;
        this.rank = r;
        this.name = n;
        this.leagueID = l;
        this.covid = c;
        try {
            this.quarantinedFrom = Timestamp.valueOf(q);
        } catch (IllegalArgumentException ex) {
            this.quarantinedFrom = null;
        }
    }

    public Team(){};

    public Integer getId() {
        return this.teamid;
    }

    public Integer getRank() {
        return this.rank;
    }

    public String getName() {
        return this.name;
    }

    public Integer getLeagueID() {
        return this.leagueID;
    }

    public Integer getCovid() {
        return this.covid;
    }

    public Timestamp getQuarantinedFrom() {
        return this.quarantinedFrom;
    }

    public TeamDTO toDTO() {
        TeamDTO teamDTO = new TeamDTO(this.teamid, this.leagueID, this.name, this.rank, this.covid, this.quarantinedFrom == null?null:this.quarantinedFrom.toString());
        return teamDTO;
    }

    public static Team fetchByID(Integer tID) {
        return TeamGateway.fetchByID(tID).toBO();
    }

    /**
     * Function that "sends" information about covid
     * @throws IOException
     */
    public static void storeInfo() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("./logs/test.txt"));
        lines.add("Team has covid.");

        Files.write(Paths.get("./logs/test.txt"), lines,
                StandardCharsets.UTF_8);
    }

    /**
     * Function that checks if team covid status has changed to 1, if no then update if yes then handle alternatives
     * @param team updated team
     * @param covid new value of covid
     * @param team_name new value of team name
     * @return
     */
    public static Integer proceedUpdate(Team team, String covid, String team_name) {
        try {
            Integer covidNumber = Integer.parseInt(covid);

            if(covidNumber == 1) {
                return -2;
            } else {
                Team t = new Team(team.getId(), team.getLeagueID(), team_name, team.getRank(), covidNumber, team.getQuarantinedFrom() == null ? null : team.getQuarantinedFrom().toString() );
                TeamGateway.update(t.toDTO());
                return 0;
            }
        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * Function that calls TeamGateway update
     * @param t updated team
     * @throws SQLException
     */
    public static void update(Team t) throws SQLException {
        TeamGateway.update(t.toDTO());
    }

    /**
     * Function that deletes all matches of team in the next two weeks and updates team info
     * @param team team to update
     * @param covid new value of covid - at this point we know it is 1
     * @param name new value of name
     */
    public static void stopMatchesAndUpdate(Team team, String covid, String name) {
        try {
            // Set time boundries
            Timestamp date = new Timestamp(System.currentTimeMillis());
            date = setTimeToZero(date);

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DAY_OF_YEAR, 14);

            Timestamp twoWeeksFromNow = new Timestamp(calendar.getTime().getTime());
            twoWeeksFromNow = setTimeToZero(twoWeeksFromNow);

            Integer covidNumber = Integer.parseInt(covid);

            Team t = new Team(team.getId(), team.getLeagueID(), name, team.getRank(), covidNumber,  team.getQuarantinedFrom() == null ? null : team.getQuarantinedFrom().toString() );
            TeamGateway.update(t.toDTO());

            ArrayList<TeamMatch> tm = TeamMatch.arrayListToBO(TeamMatchGateway.fetchByTeamID(team.getId()));

            for(int i = 0; i < tm.size(); i++) {
                Match m = MatchGateway.fetchByID(tm.get(i).getMatchID()).toBO();

                if(m.getDate().after(date) && m.getDate().before(twoWeeksFromNow)) {
                    TeamMatchGateway.delete(tm.get(i).getTeamMatchID());
                    MatchGateway.delete(m.getMatchID());
                }
            }

        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
        }
    }

    private static Timestamp setTimeToZero(Timestamp date) {
        date.setHours(0);
        date.setMinutes(0);
        date.setSeconds(0);
        date.setNanos(0);

        return date;
    }

    public static ArrayList<Team> arrayListToBO(ArrayList<TeamDTO> teamDTOS) {
        ArrayList<Team> t = new ArrayList<>();

        for(int i = 0; i < teamDTOS.size(); i++) {
            t.add(teamDTOS.get(i).toBO());
        }
        return t;
    }
}
