<%@ page import = "java.io.*,java.util.*, vis.tables.*, vis.entities.*" %>
<%@ page import="java.sql.SQLException" %>
<%@ include file="header.jsp" %>
<main>
    <center>
        <h2>Player UPDATE DONE</h2>
        <%
            String name = request.getParameter("newname");
            String id = request.getParameter("id");

            out.println("Player " + name + " was successfully updated.");
        %>

    </center>
</main>
<%@ include file="footer.jsp" %>