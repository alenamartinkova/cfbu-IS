<%@ page import = "java.io.*,java.util.*, dais.tables.*, dais.entities.*" %>
<%@ page import="java.sql.SQLException" %>
<%@ include file="header.jsp" %>
<main>
    <center>
        <h2>Team UPDATE</h2>
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

            if (teamId != -1) {
                TeamTable tt = new TeamTable();
                ArrayList<Team> team = tt.fetchByAttr("TEAM_ID", teamId);

                // it will always be only one team (because id is unique) , but it returns arraylist so.
                out.println("<form method='POST' action='update_done.jsp'>");
                for (Team t : team) {
                    out.println("<h2>Current info:</h2>");
                    out.println("<p><strong>ID:</strong> " + t.getId() + "</p><br>");
                    out.println("<p><strong>Name:</strong> " + t.getName() + "</p><br>");
                    out.println("<p><strong>League ID:</strong> " + t.getLeagueId() + "</p><br>");
                    out.println("<p><strong>Rank: </strong>" + t.getRank() + "</p><br>");
                    out.println("<h2>Change info:</h2>");
                    out.println("<strong>Name: </strong><input type='text' name=\"newval\" value='" + t.getName() +"'><br>");
                    out.println("<input type='hidden' name=\"id\" value='" + t.getId() + "'>");
                    out.println("<input type='hidden' name=\"rank\" value='" + t.getRank() + "'>");
                    out.println("<input type='hidden' name=\"league\" value='" + t.getLeagueId() + "'>");
                }

                out.println("<input class='btn' type='submit' value='Update'></form>");
            }
        %>
    </center>
</main>
<%@ include file="footer.jsp" %>