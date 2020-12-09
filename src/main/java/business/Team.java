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

    public static ArrayList<Team> fetch() throws SQLException {
        return TeamGateway.fetch();
    }

    public static void storeInfo() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("./logs/test.txt"));
        lines.add("Team has covid.");

        Files.write(Paths.get("./logs/test.txt"), lines,
                StandardCharsets.UTF_8);
    }

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

    public static void update(Team t) throws SQLException {
        TeamGateway.update(t);
    }

    public static void stopMatchesAndUpdate(Team team) {
        try {
            Timestamp date = new Timestamp(System.currentTimeMillis());

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DAY_OF_YEAR, 14);

            Timestamp twoWeeksFromNow = new Timestamp(calendar.getTime().getTime());
            //System.out.println(date);
            //System.out.println(twoWeeksFromNow);
            //Integer covidNumber = Integer.parseInt(covid);

            //Team t = new Team(team.getId(), team.getLeagueID(), team.getName(), team.getRank(), covidNumber, quarantined_from);
            //TeamGateway.update(t);
            //System.out.println(team);
            ArrayList<TeamMatch> tm = TeamMatchGateway.fetchByTeamID(team.getId());

            for(int i = 0; i < tm.size(); i++) {
                Match m = MatchGateway.fetchByID(tm.get(i).getMatchID());

            }

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
