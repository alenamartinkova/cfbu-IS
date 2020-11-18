<%@ page import = "java.io.*,java.util.*, vis.tables.*, vis.entities.*" %>
<%@ page import="java.sql.SQLException" %>
<%@ include file="header.jsp" %>
<main>
    <center>
        <h2>Team UPDATE DONE</h2>
        <%
            String name = request.getParameter("newval");
            String id = request.getParameter("id");
            String rank = request.getParameter("rank");
            String league_id = request.getParameter("league");

            TeamTable tt = new TeamTable();
            tt.update(Integer.parseInt(id), Integer.parseInt(rank), name, Integer.parseInt(league_id));

            out.println("Team " + name + " was successfully updated.");
        %>

    </center>
</main>
<%@ include file="footer.jsp" %>