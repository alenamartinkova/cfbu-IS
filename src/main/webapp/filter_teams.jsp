<%@ page import = "java.io.*,java.util.*, dais.tables.*, dais.entities.*" %>
<%@ page import="java.sql.SQLException" %>
<%@ include file="header.jsp" %>
<main>
    <center>
        <h2>Teams</h2>
        <form width = "100%" border = "1" align = "center" method="POST" action="detail.jsp">
            <input type="text" name="filter" style="margin-right: 10px;"><input type="submit" value="Filter" formaction="filter_teams.jsp">
            <ul class="list">
            <%

            String search = request.getParameter("filter");

            TeamTable table = new TeamTable();
            ArrayList<Team> teams  = table.searchByAttr(search);

            out.println("<p>Searching for: " + search + "</p>");
            for (Team t : teams) {
                out.println("<li class='list-item'><input class='input' type='checkbox' name='" + t.getId() + "'/>" + t.getId() + ". " + t.getName() + "</li><br>");
            }

            out.println("</ul>");
            out.println("<input class='btn' type='submit' value='Team detail'/>");
            out.println("<input class='btn' type='submit' formaction='delete.jsp' value='Delete team'/>");
            out.println("<input class='btn' type='submit' formaction='update.jsp' value='Update team'/>");
            out.println("<input class='btn' type='submit' formaction='add.jsp' value='Add team'/>");
            out.println("<input class='btn' type='submit' formaction='change_league.jsp' value='Add league'/>");
            out.println("<input class='btn' type='submit' formaction='transfer_team.jsp' value='Transfer team'/>");

            %>
        </form>
    </center>
</main>

<%@ include file="footer.jsp" %>