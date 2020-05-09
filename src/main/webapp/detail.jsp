<%@ page import = "java.io.*,java.util.*, dais.tables.*, dais.entities.*" %>
<%@ page import="java.sql.SQLException" %>
<%@ include file="header.jsp" %>

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

        for (Team t : teams) {
            out.println("<p>ID: " + t.getId() + "</p><br>");
            out.println("<p>Name: " + t.getName() + "</p><br>");
            out.println("<p>League ID: " + t.getLeagueId() + "</p><br>");
            out.println("<p> Rank: " + t.getRank() + "</p><br>");
        }
    }
    %>
</center>
<%@ include file="footer.jsp" %>