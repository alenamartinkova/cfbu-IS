<%@ page import = "java.io.*,java.util.*, dais.tables.*, dais.entities.*" %>
<%@ page import="java.sql.SQLException" %>
<%@ include file="header.jsp" %>
<main>
    <center>
        <h2>Team ADD DONE</h2>
        <%
            String name = request.getParameter("newval");
            TeamTable tt = new TeamTable();
            LeagueTable lt = new LeagueTable();
            String league = "";
            String league_name = "";

            Enumeration paramNames = request.getParameterNames();
            while (paramNames.hasMoreElements()) {
                league =  (String)paramNames.nextElement();
            }

            Integer league_id = Integer.parseInt(league);
            ArrayList<League> leagues = lt.fetchByAttr("LEAGUE_ID", league_id);

            for (League l : leagues) {
                league_name = l.getName();
            }
            Integer rank = tt.getMaxRank(league_id) + 1;

            tt.insert(rank, name, league_id);
            out.println("New team " + name + " was added to league " + league_name + " with rank " + rank +".");
        %>

    </center>
</main>
<%@ include file="footer.jsp" %>