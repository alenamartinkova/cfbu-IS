<%@ page import = "java.io.*,java.util.*, dais.tables.*, dais.entities.*" %>
<%@ page import="java.sql.SQLException" %>
<%@ include file="header.jsp" %>
<main>
    <center>
        <h2>Team ADD DONE</h2>
        <%
            String name = request.getParameter("newval");
            String league_name = request.getParameter("league_name");

            String league = "";
            Enumeration paramNames = request.getParameterNames();
            while (paramNames.hasMoreElements()) {
                league =  (String)paramNames.nextElement();
            }

            Integer league_id = Integer.parseInt(league);
            TeamTable tt = new TeamTable();
            Integer rank = tt.getMaxRank(league_id) + 1;

            out.println("New team " + name + " was added to league " + league_name + " with rank " + rank +".");
        %>

    </center>
</main>
<%@ include file="footer.jsp" %>