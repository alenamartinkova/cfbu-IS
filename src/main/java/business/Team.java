package business;

import gateways.MatchGateway;
import gateways.TeamGateway;
import gateways.TeamMatchGateway;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.sql.Time;
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

    public static Team fetchByID(Integer tID) {
        return TeamGateway.fetchByID(tID);
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
     * @param quarantined_from new value of quarantined from
     * @param covid new value of covid
     * @return
     */
    public static Integer proceedUpdate(Team team, String quarantined_from, String covid) {
        try {
            Integer covidNumber = Integer.parseInt(covid);

            if(covidNumber == 1) {
                return -2;
            } else {
                Team t = new Team(team.getId(), team.getLeagueID(), team.getName(), team.getRank(), covidNumber, quarantined_from);
                TeamGateway.update(t);
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
        TeamGateway.update(t);
    }

    /**
     * Function that deletes all matches of team in the next two weeks and updates team info
     * @param team team to update
     * @param quarantinedFrom new value of quarantined from
     * @param covid new value of covid - at this point we know it is 1
     */
    public static void stopMatchesAndUpdate(Team team, String quarantinedFrom, String covid) {
        try {
            Timestamp date = new Timestamp(System.currentTimeMillis());
            date.setHours(0);
            date.setMinutes(0);
            date.setSeconds(0);
            date.setNanos(0);

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DAY_OF_YEAR, 14);

            Timestamp twoWeeksFromNow = new Timestamp(calendar.getTime().getTime());
            twoWeeksFromNow.setHours(0);
            twoWeeksFromNow.setMinutes(0);
            twoWeeksFromNow.setSeconds(0);
            twoWeeksFromNow.setNanos(0);
            Integer covidNumber = Integer.parseInt(covid);

            Team t = new Team(team.getId(), team.getLeagueID(), team.getName(), team.getRank(), covidNumber, quarantinedFrom);
            TeamGateway.update(t);

            ArrayList<TeamMatch> tm = TeamMatchGateway.fetchByTeamID(team.getId());

            for(int i = 0; i < tm.size(); i++) {
                Match m = MatchGateway.fetchByID(tm.get(i).getMatchID());

                if(m.getDate().after(date) && m.getDate().before(twoWeeksFromNow)) {
                    TeamMatchGateway.delete(tm.get(i).getTeamMatchID());
                    MatchGateway.delete(m.getMatchID());
                }
            }

        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
        }
    }
}
