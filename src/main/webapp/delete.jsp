<%@ page import = "java.io.*,java.util.*, dais.tables.*, dais.entities.*" %>
<%@ page import="java.sql.SQLException" %>
<%@ include file="header.jsp" %>
<main>
    <center>
        <h2>Team DELETE</h2>
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
                tt.delete(teamId);

                // it will always be only one team (because id is unique) , but it returns arraylist so.
                for (Team t : team) {
                    out.println("Team " + t.getName() + " was successfully deleted.");
                }
            }
        %>
    </center>
</main>
<%@ include file="footer.jsp" %>