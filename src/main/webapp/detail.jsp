<%@ page import = "java.util.*, vis.tables.*, vis.entities.*" %>
<%@ page import="vis.interfaces.PlayerInterface" %>
<%@ page import="vis.interfaces.TeamInterface" %>
<%@ include file="header.jsp" %>
<main>
    <center>
        <%
        String playerIDString = "";
        Enumeration paramNames = request.getParameterNames();

        while (paramNames.hasMoreElements()) {
            playerIDString = (String) paramNames.nextElement();
        }

        Integer playerID;
        try {
            playerID = Integer.parseInt(playerIDString);
        } catch (NumberFormatException e) {
            playerID = -1;
        }

        final PlayerInterface pt = new PlayerTable();
        final TeamInterface tt = new TeamTable();

        if (playerID != -1) {
            Player p = pt.fetchByID(playerID);
            Team t = tt.fetchByID(p.getTeamID());
            ArrayList<Team> allTeams = tt.fetch();

            out.println("<form method='POST' action='update_done.jsp'>");
            out.println("<h2>Player no. "+ p.getId() +"</h2><div class='detail-wrapper'>");
            out.println("<div class='row'><p><strong>Name: </strong><input type='text' name='newname' value='" + p.getName() + "'></p>");
            out.println("<p><strong>Sure name: </strong><input type='text' name='newsurename' value='" + p.getSureName() + "'></p></div>");
            out.println("<div class='row'><p><strong>Email: </strong><input type='text' name='newemail' value='" + p.getEmail() + "'></p>");
            out.println("<p><strong>Covid: </strong><input type='text' name='newcovid' value='" + p.getCovid() + "'></p></div>");
            out.println("<div class='row'><p><strong>Date of birth: </strong>" + p.getDateOfBirth()+"</p>");
            out.println("<p><strong>Quarantined from: </strong><input type='text' name='newquarantinedfrom' value='" + p.getQuarantinedFrom() + "'></div>");
            out.println("<div class='row'><p><strong>Stick: </strong>" + p.getStick() + "</p>");
            out.println("<p><strong>Team: </strong>" + t.getName() + "</p></div></div>");

            out.println("<select>");
            for(Team team : allTeams) {
                out.println("<option>" + team.getName() +"</option>");
            }
            out.println("</select>");
            out.println("<button class='btn'>Submit</button><br>");
            out.println("<input type='hidden' name='id' value='" + p.getId() + "'>");
            out.println("<input type='hidden' name='teamid' value='" + p.getTeamID() + "'>");
            out.println("<input type='hidden' name='date' value='" + p.getDateOfBirth() + "'>");
            out.println("<input type='hidden' name='stick' value='" + p.getStick() + "'>");

        }
        %>
    </center>
</main>
<%@ include file="footer.jsp" %>