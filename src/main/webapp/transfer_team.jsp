<%@ page import = "java.io.*,java.util.*, vis.tables.*, vis.entities.*" %>
<%@ page import="java.sql.SQLException" %>
<%@ include file="header.jsp" %>
<main>
    <center>
        <h2>Transfer team</h2>
        <form width = "100%" border = "1" align = "center" method="POST" action="transfer_team_done.jsp">
            <%
            String teamIdString = "";
            Enumeration paramNames = request.getParameterNames();

            while (paramNames.hasMoreElements()) {
                teamIdString = (String) paramNames.nextElement();
            }

            Integer teamId;
            try {
                teamId = Integer.parseInt(teamIdString);
            } catch (NumberFormatException e) {
                teamId = -1;
            }

            LeagueTable lt = new LeagueTable();
            TeamTable table = new TeamTable();

            if (teamId != -1) {
                ArrayList<Team> teams  = table.fetchByAttr("TEAM_ID", teamId);
                String l_name = "";
                // it will always be only one team (because id is unique) , but it returns arraylist so.
                for (Team t : teams) {
                    ArrayList<League> league = lt.fetchByAttr("LEAGUE_ID", t.getLeagueId());
                    // not pretty i know
                    for (League l : league) {
                        l_name = l.getName();
                    }
                    out.println("<p><strong>ID:</strong> " + t.getId() + "</p><br>");
                    out.println("<p><strong>Name:</strong> " + t.getName() + "</p><br>");
                    out.println("<p><strong>League:</strong> " + t.getLeagueId() + ". "+ l_name +"</p><br>");
                    out.println("<p><strong>Rank: </strong>" + t.getRank() + "</p><br>");
                }
            }
            out.println("<input type=\"hidden\" name=\"team\" value='"+teamId+"'>");
            ArrayList<League> leagues = lt.fetch(); %>

            <ul class="list"><br>
                <strong>Leagues:</strong>
                <%
                for (League l : leagues ) {
                    out.println("<li class='list-item'><input class='input' type='checkbox' name='" + l.getId() + "'>" + l.getName()+"</li>");
                }

                %>

            </ul>
            <input class="btn" type="submit" value="Transfer">
        </form>
    </center>
</main>

<%@ include file="footer.jsp" %>