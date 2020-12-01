<%@ page import = "java.io.*,java.util.*, vis.tables.*, vis.entities.*" %>
<%@ page import="java.sql.SQLException" %>
<%@ include file="header.jsp" %>
<main>
    <center>
        <h2>Player UPDATE DONE</h2>
        <%
            String name = request.getParameter("newname");
            String sureName = request.getParameter("newsurename");
            String email = request.getParameter("newemail");
            String covid = request.getParameter("newcovid");
            String quaraFrom = request.getParameter("newquarantinedfrom");
            String stick = request.getParameter("stick");
            String dateBirth = request.getParameter("date");
            String team = request.getParameter("teamid");

            String id = request.getParameter("id");

            try {
                PlayerTable pt = new PlayerTable();
                Integer covidNumber = Integer.parseInt(covid);
                Integer teamNumber = Integer.parseInt(team);
                Integer idNumber = Integer.parseInt(id);
                Player p = new Player(idNumber, teamNumber, name, sureName, dateBirth, covidNumber, quaraFrom, email, stick);

                pt.update(p);

                out.println("Player with id " + id + " was successfully updated.");
            } catch (NumberFormatException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        %>

    </center>
</main>
<%@ include file="footer.jsp" %>