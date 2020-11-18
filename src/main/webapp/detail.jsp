<%@ page import = "java.io.*,java.util.*, vis.tables.*, vis.entities.*" %>
<%@ page import="java.sql.SQLException" %>
<%@ include file="header.jsp" %>
<main>
    <center>
        <h2>Team Detail</h2>
        <%
        String teamIdString = "";
        Enumeration paramNames = request.getParameterNames();

        while (paramNames.hasMoreElements()) {
            teamIdString = (String) paramNames.nextElement();
        }

        Integer teamId;
        try {
            teamId = Integer.parseInt(teamIdString);
        } catch (NumberFormatException e) {
            teamId = -1;
        }

        LeagueTable lt = new LeagueTable();
        TeamTable table = new TeamTable();

        if (teamId != -1) {
            ArrayList<Team> teams  = table.fetchByAttr("TEAM_ID", teamId);

            String l_name = "";
            // it will always be only one team (because id is unique) , but it returns arraylist so.
            for (Team t : teams) {
                ArrayList<League> league = lt.fetchByAttr("LEAGUE_ID", t.getLeagueId());
                // not pretty i know
                for (League l : league) {
                    l_name = l.getName();
                }
                out.println("<p><strong>ID:</strong> " + t.getId() + "</p><br>");
                out.println("<p><strong>Name:</strong> " + t.getName() + "</p><br>");
                out.println("<p><strong>League:</strong> " + t.getLeagueId() + ". "+ l_name +"</p><br>");
                out.println("<p><strong>Rank: </strong>" + t.getRank() + "</p><br>");
            }
        }
        %>
    </center>
</main>
<%@ include file="footer.jsp" %>