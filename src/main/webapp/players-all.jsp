<%@ page import = "java.io.*,java.util.*, vis.tables.*, vis.entities.*" %>
<%@ page import="java.sql.SQLException" %>
<%@ include file="header.jsp" %>
<main>
    <center>
        <div class="detail-wrapper">
            <div class="row">
                <div>List of Players</div>
                <div><input type="text" placeholder="Search"></div>
            </div>
        </div>

        <form width="100%" border="1" align="center" method="POST" action="detail.jsp">
            <ul class="checkbox-list">
            <%
              try {
                    PlayerTable pt = new PlayerTable();
                    ArrayList<Player> players  = pt.fetch();

                    out.println("<input class='btn' type='submit' value='Player detail'/>");
                    out.println("<div class='table-wrapper'><table><tbody>");
                    out.println("<tr class='gray-bg'><th>Player</th><th>Name</th><th>Sure Name</th><th>Team</th><th></th>");
                    for (Player p : players) {
                        out.println("<tr><th>"+p.getId()+"</th>");
                        out.println("<td>" + p.getName() + "</td>");
                        out.println("<td>" + p.getSureName() +"</td>");
                        out.println("<td>" + p.getTeamID() + "</td>");
                        out.println("<td><input type='checkbox' class='input' name='"+p.getId()+"'></td></tr>");
                    }

                    out.println("</tbody></table></div>");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            %>
        </form>
    </center>
</main>

<%@ include file="footer.jsp" %>