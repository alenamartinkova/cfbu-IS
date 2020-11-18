<%@ page import = "java.io.*,java.util.*, vis.tables.*, vis.entities.*" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.sql.Array" %>
<%@ include file="header.jsp" %>
<main>
    <center>
        <h2>Team ADD</h2>
        <form method="post" action="add_done.jsp">
            <strong>Name: </strong><input type="text" name="newval">
            <%
            LeagueTable lt = new LeagueTable();
            ArrayList<League> leagues = lt.fetch(); %>

            <ul class="list"><br>
                <strong>Leagues:</strong>
            <%
            for (League l : leagues ) {
                out.println("<li class='list-item'><input class='input' type='checkbox' name='" + l.getId() + "'>" + l.getName()+"</li>");
            } %>
                <input class="btn" type="submit" value="Add">
            </ul>
        </form>
    </center>
</main>
<%@ include file="footer.jsp" %>