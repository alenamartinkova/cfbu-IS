<%@ page import = "java.util.*, vis.tables.*, vis.entities.*" %>
<%@ include file="header.jsp" %>
<main>
    <center>
        <h2>Change LEAGUE DONE</h2>
        <%
            TeamTable tt = new TeamTable();
            LeagueTable lt = new LeagueTable();

            String name = request.getParameter("name");
            String division = request.getParameter("division");
            String league_id = "";

            Enumeration paramNames = request.getParameterNames();
            while (paramNames.hasMoreElements()) {
                league_id = (String)paramNames.nextElement();
            }

            Integer num_league_id = Integer.parseInt(league_id);
            Integer num_division = Integer.parseInt(division);

            tt.changeLeague(name, num_division, num_league_id);
            ArrayList<League> league = lt.fetchByAttr("LEAGUE_ID", league_id);

            String league_name = "";

            for (League l : league) {
                league_name = l.getName();
            }

            out.println("League " + league_name + " was successfully replaced by league " + name + " and all teams were transfered.");

        %>

    </center>
</main>
<%@ include file="footer.jsp" %>