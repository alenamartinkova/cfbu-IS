<%@ page import = "java.io.*,java.util.*, vis.tables.*, vis.entities.*" %>
<%@ page import="java.sql.SQLException" %>
<%@ include file="header.jsp" %>
<main>
    <center>
        <h2>Player Detail</h2>
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

            // it will always be only one team (because id is unique) , but it returns arraylist so.
            for (Player p : player) {
                out.println("<p><strong>ID:</strong> " + p.getId() + "</p><br>");
                out.println("<p><strong>Name:</strong> " + p.getName() + "</p><br>");
                out.println("<p><strong>Sure Name:</strong> " + p.getSureName() +"</p><br>");
                out.println("<p><strong>Email: </strong>" + p.getEmail() + "</p><br>");
                out.println("<p><strong>Covid: </strong>" + p.getCovid() + "</p><br>");
                out.println("<p><strong>Date of birth: </strong>" + p.getDateOfBirth() + "</p><br>");
                out.println("<p><strong>Quarantined from: </strong>" + p.getQuarantinedFrom() + "</p><br>");
                out.println("<p><strong>Stick: </strong>" + p.getStick() + "</p><br>");
                out.println("<p><strong>Team: </strong>" + p.getTeamID() + "</p><br>");
                out.println("<button class='btn'>Submit</button><br>");
            }
        }
        %>
    </center>
</main>
<%@ include file="footer.jsp" %>