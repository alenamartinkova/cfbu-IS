<%@ page import = "java.io.*,java.util.*, vis.tables.*, vis.entities.*" %>
<%@ page import="java.sql.SQLException" %>
<%@ include file="header.jsp" %>
<main>
    <center>
        <h2>Team TRANSFER DONE</h2>
        <%
            Boolean ret;
            TeamTable tt = new TeamTable();
            LeagueTable lt = new LeagueTable();

            String team_id = request.getParameter("team");
            String league_id = "";

            Enumeration paramNames = request.getParameterNames();
            while (paramNames.hasMoreElements()) {
                league_id = (String)paramNames.nextElement();
            }

            Integer num_team_id = Integer.parseInt(team_id);
            Integer num_league_id = Integer.parseInt(league_id);

            ret = tt.teamTransfer(num_team_id, num_league_id);
            ArrayList<Team> team = tt.fetchByAttr("TEAM_ID", team_id);
            ArrayList<League> league = lt.fetchByAttr("LEAGUE_ID", league_id);

            String league_name = "";

            for (League l : league) {
                league_name = l.getName();
            }

            for (Team t : team) {
                if (ret) out.println("Team " + t.getName() + " was successfully transfered to league " + league_name);
                else out.println("Error");
            }
        %>

    </center>
</main>
<%@ include file="footer.jsp" %>