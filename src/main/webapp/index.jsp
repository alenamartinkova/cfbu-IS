<%@ page import = "java.io.*,java.util.*, dais.tables.*, dais.entities.*" %>
<%@ page import="java.sql.SQLException" %>
<%@ include file="header.jsp" %>
<center>
    <h2>Teams</h2>
    <form width = "100%" border = "1" align = "center" method="POST" action="detail.jsp">
        <input class='btn' type='submit' formaction='add.jsp' value='Add team'/><br>
        <%
            TeamTable table = null;

            try {
                table = new TeamTable();
                ArrayList<Team> teams  = table.fetch();

                for (Team t : teams) {
                    out.println("<input class='input' type='checkbox' name='" + t.getId() + "'/>" + t.getId() + ". " + t.getName() + "<br>");
                }

                out.println("<input class='btn' type='submit' value='Team detail'/>");
                out.println("<input class='btn' type='submit' formaction='delete.jsp' value='Delete team'/>");
                out.println("<input class='btn' type='submit' formaction='update.jsp' value='Update team'/>");

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        %>
    </form>
</center>

<style>
    .btn {
        background-color: #000;
        border: none;
        color: #01baff;
        padding: 15px 25px;
        border-radius: 10px;
        text-align: center;
        text-decoration: none;
        display: inline-block;
        font-size: 16px;
        margin: 4px 2px;
        cursor: pointer;
    }

    .input {
        margin-top: 15px;
    }
</style>
<%@ include file="footer.jsp" %>