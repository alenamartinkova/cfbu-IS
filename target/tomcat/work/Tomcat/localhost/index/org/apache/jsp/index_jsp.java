/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2020-12-07 17:07:00 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(2);
    _jspx_dependants.put("/footer.jsp", Long.valueOf(1607351164620L));
    _jspx_dependants.put("/header.jsp", Long.valueOf(1607351164632L));
  }

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("    <meta charset=\"utf-8\">\r\n");
      out.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("<header>\r\n");
      out.write("    <div class=\"header\">\r\n");
      out.write("        <div>\r\n");
      out.write("            <h1><a href=\"index.jsp\">Project VIS</a></h1>\r\n");
      out.write("        </div>\r\n");
      out.write("\r\n");
      out.write("        <div>\r\n");
      out.write("            <nav>\r\n");
      out.write("                <ul class=\"list\">\r\n");
      out.write("                    <li class=\"list-item\">\r\n");
      out.write("                        <a href=\"#\">TEAMS</a>\r\n");
      out.write("                    </li>\r\n");
      out.write("                    <li class=\"list-item\">\r\n");
      out.write("                        <a href=\"players_all.jsp\">PLAYERS</a>\r\n");
      out.write("                    </li>\r\n");
      out.write("                </ul>\r\n");
      out.write("            </nav>\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("</header>\r\n");
      out.write("\r\n");
      out.write("<style>\r\n");
      out.write("    * {\r\n");
      out.write("        font-family: Open-Sans, sans-serif;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    main {\r\n");
      out.write("        min-height: 620px;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    body {\r\n");
      out.write("        margin: 0;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    header {\r\n");
      out.write("        width: 100%;\r\n");
      out.write("        border-bottom: 1px solid darkred;\r\n");
      out.write("        position: fixed;\r\n");
      out.write("        top: 0;\r\n");
      out.write("        background: white;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    .header {\r\n");
      out.write("        display: flex;\r\n");
      out.write("        justify-content: space-between;\r\n");
      out.write("        max-width: 1400px;\r\n");
      out.write("        margin: 0 auto;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    .header h1 {\r\n");
      out.write("        margin: 0;\r\n");
      out.write("        padding: 20px;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    .btn {\r\n");
      out.write("        background-color: #000;\r\n");
      out.write("        border: none;\r\n");
      out.write("        color: white;\r\n");
      out.write("        padding: 15px 25px;\r\n");
      out.write("        border-radius: 10px;\r\n");
      out.write("        text-align: center;\r\n");
      out.write("        text-decoration: none;\r\n");
      out.write("        display: inline-block;\r\n");
      out.write("        font-size: 16px;\r\n");
      out.write("        margin: 10px 0 30px 0;\r\n");
      out.write("        cursor: pointer;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    .list {\r\n");
      out.write("        width: 100%;\r\n");
      out.write("        margin: 20px 0 0 0;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    .checkbox-list {\r\n");
      out.write("        padding-left: 0;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    .list-item {\r\n");
      out.write("        list-style: none;\r\n");
      out.write("        position: relative;\r\n");
      out.write("        display: inline-block;\r\n");
      out.write("        padding: 10px;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    main {\r\n");
      out.write("        margin-top: 150px;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    .main-index h1 {\r\n");
      out.write("        color: darkred;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    .main-index a {\r\n");
      out.write("        font-size: 24px;\r\n");
      out.write("        padding: 20px;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    a {\r\n");
      out.write("        color: black;\r\n");
      out.write("        text-decoration: none;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    a:hover {\r\n");
      out.write("        color: darkred;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    li {\r\n");
      out.write("        list-style: none;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    .row p {\r\n");
      out.write("        display: inline-block;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    table, th, td {\r\n");
      out.write("        margin: 0 auto;\r\n");
      out.write("        border: 1px solid darkred;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    th, td {\r\n");
      out.write("        padding: 3px 10px;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    .gray-bg {\r\n");
      out.write("        background: #e6e6e6;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    .detail-wrapper {\r\n");
      out.write("        max-width: 800px;\r\n");
      out.write("        margin: 0 auto;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    .row {\r\n");
      out.write("        display: flex;\r\n");
      out.write("        justify-content: space-between;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    select {\r\n");
      out.write("        margin-right: 20px;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("</style>");
      out.write("\r\n");
      out.write("<main class=\"main-index\">\r\n");
      out.write("    <center>\r\n");
      out.write("        <h1>PLEASE CHOOSE WHAT ENTITY DO YOU WANT TO EDIT</h1>\r\n");
      out.write("        <div>\r\n");
      out.write("            <a href=\"#\">TEAMS</a>\r\n");
      out.write("            <a href=\"players_all.jsp\">PLAYERS</a>\r\n");
      out.write("        </div>\r\n");
      out.write("    </center>\r\n");
      out.write("</main>\r\n");
      out.write("\r\n");
      out.write("        <footer class=\"footer\">\r\n");
      out.write("            <p>Alena Martinkova, MAR0702 | &copy 2020</p>\r\n");
      out.write("        </footer>\r\n");
      out.write("    </body>\r\n");
      out.write("</html>\r\n");
      out.write("\r\n");
      out.write("<style>\r\n");
      out.write("    .footer {\r\n");
      out.write("        border-top: 1px solid darkred;\r\n");
      out.write("        width: 100%;\r\n");
      out.write("        margin-top: 20px;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    .footer p {\r\n");
      out.write("        margin: 0;\r\n");
      out.write("        padding: 20px;\r\n");
      out.write("        font-size: 16px;\r\n");
      out.write("    }\r\n");
      out.write("</style>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
