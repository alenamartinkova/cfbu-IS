<%@ page import = "java.io.*,java.util.*, dais.tables.*, dais.entities.*" %>
<%@ page import="java.sql.SQLException" %>
<%@ include file="header.jsp" %>
<main>
    <center>
        <h2>Team Detail</h2>
        <%
        String teamIdString = "";
        Enumeration paramNames = request.getParameterNames();

        if (paramNames.hasMoreElements()) {
            teamIdString = (String) paramNames.nextElement();
        }

        Integer teamId;
        try {
            teamId = Integer.parseInt(teamIdString);
        } catch (NumberFormatException e) {
            teamId = -1;
        }

        TeamTable table = null;

        table = new TeamTable();

        if (teamId != -1) {
            ArrayList<Team> teams  = table.fetchByAttr("TEAM_ID", teamId);

            // it will always be only one team (because id is unique) , but it returns arraylist so.
            for (Team t : teams) {
                out.println("<p><strong>ID:</strong> " + t.getId() + "</p><br>");
                out.println("<p><strong>Name:</strong> " + t.getName() + "</p><br>");
                out.println("<p><strong>League ID:</strong> " + t.getLeagueId() + "</p><br>");
                out.println("<p><strong>Rank: </strong>" + t.getRank() + "</p><br>");
            }
        }
        %>
    </center>
</main>
<%@ include file="footer.jsp" %>