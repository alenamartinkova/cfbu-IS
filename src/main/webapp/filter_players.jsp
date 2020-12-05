<%@ page import = "java.util.*, vis.business.*" %>
<%@ include file="header.jsp" %>
<main>
    <center>
        <h2>Players</h2>
        <form width = "100%" border = "1" align = "center" method="POST" action="detail.jsp">
            <div class="detail-wrapper">
                <div class="row">
                    <div>List of Players</div>
                    <div><input type="text" name="filter" placeholder="Search" style="margin-right: 10px;"><input type="submit" value="Filter" formaction="filter_players.jsp"></div>
                </div>
            </div>
            <%

            String search = request.getParameter("filter");
            ArrayList<Player> players  = Player.searchByAttr(search);

            out.println("<p>Searching for: " + search + "</p>");
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
            %>
        </form>
    </center>
</main>

<%@ include file="footer.jsp" %>