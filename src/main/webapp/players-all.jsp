<%@ page import = "java.io.*,java.util.*, vis.tables.*, vis.entities.*" %>
<%@ page import="java.sql.SQLException" %>
<%@ include file="header.jsp" %>
<main>
    <center>
        <h2>ALL PLAYERS</h2>
        <form width="100%" border="1" align="center" method="POST" action="detail.jsp">
            <ul class="checkbox-list">
            <%
              try {
                    PlayerTable pt = new PlayerTable();
                    ArrayList<Player> players  = pt.fetch();

                    for (Player p : players) {
                        out.println("<li><input class='input' type='checkbox' name='" + p.getId() + "'/>" + p.getId() + ". " + p.getName() + " " + p.getSureName() +"</li><br>");
                    }

                    out.println("</ul>");
                    out.println("<input class='btn' type='submit' value='Player detail'/>");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            %>
        </form>
    </center>
</main>

<%@ include file="footer.jsp" %>