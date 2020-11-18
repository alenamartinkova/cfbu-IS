<%@ page import = "java.util.*, vis.tables.*, vis.entities.*" %>
<%@ include file="header.jsp" %>
<main>
    <center>
        <h2>Change entire league</h2>
        <form width = "100%" border = "1" align = "center" method="POST" action="change_league_done.jsp">
            <strong>Name: </strong><input type="text" name="name">
            <strong>Divison: </strong><input type="text" name="division">

            <ul class="list"><br>
                <strong>Which league do you want to replace? </strong>
                <%
                    LeagueTable lt = new LeagueTable();
                    ArrayList<League> leagues = lt.fetch();
                    for (League l : leagues ) {
                        out.println("<li class='list-item'><input class='input' type='checkbox' name='" + l.getId() + "'>"+ l.getId() + "." + l.getName()+"</li>");
                    }
                %>

            </ul>
            <input class="btn" type="submit" value="Change league">
        </form>
    </center>
</main>

<%@ include file="footer.jsp" %>