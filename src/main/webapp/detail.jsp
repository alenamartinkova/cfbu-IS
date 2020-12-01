<%@ page import = "java.io.*,java.util.*, vis.tables.*, vis.entities.*" %>
<%@ page import="java.sql.SQLException" %>
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

        PlayerTable pt = new PlayerTable();

        if (playerID != -1) {
            ArrayList<Player> player  = pt.fetchByAttr("memberID", playerID);

            out.println("<form method='POST' action='update_done.jsp'>");
            // it will always be only one team (because id is unique) , but it returns arraylist so.
            for (Player p : player) {
                out.println("<h2>Player no. "+ p.getId() +"</h2>");
                out.println("<div class='row'><strong>Name:</strong><input type='text' name='newname' value='" + p.getName() + "'>");
                out.println("<strong>Sure name: </strong><input type='text' name='newsurename' value='" + p.getSureName() + "'></div>");
                out.println("<div class='row'><strong>Email: </strong><input type='text' name='newemail' value='" + p.getEmail() + "'>");
                out.println("<strong>Covid: </strong><input type='text' name='newcovid' value='" + p.getCovid() + "'></div>");
                out.println("<div class='row'><strong>Date of birth: </strong>" + p.getDateOfBirth() + "</p>");
                out.println("<p><strong>Quarantined from: </strong><input type='text' name='newquarantinedfrom' value='" + p.getQuarantinedFrom() + "'></div>");
                out.println("<div class='row'><p><strong>Stick: </strong>" + p.getStick() + "</p>");
                out.println("<p><strong>Team: </strong>" + p.getTeamID() + "</p></div>");
                out.println("<button class='btn'>Submit</button><br>");
                out.println("<input type='hidden' name='id' value='" + p.getId() + "'>");
            }
        }
        %>
    </center>
</main>
<%@ include file="footer.jsp" %>