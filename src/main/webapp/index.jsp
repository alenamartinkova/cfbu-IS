<%@ page import = "java.io.*,java.util.*, dais.tables.*, dais.entities.*" %>
<%@ page import="java.sql.SQLException" %>
<%@ include file="header.jsp" %>
<center>
    <h2>Teams</h2>
    <form width = "100%" border = "1" align = "center" method="POST" action="detail.jsp">
        <%
            TeamTable table = null;

            try {
                table = new TeamTable();
                ArrayList<Team> teams  = table.fetch();

                for (Team t : teams) {
                    out.println("<input type='checkbox' name='" + t.getId() + "'/>" + t.getId() + ". " + t.getName() + "<br>");
                }

                out.println("<input type='submit' value='Team detail'/>");

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        %>
    </form>
</center>
<%@ include file="footer.jsp" %>