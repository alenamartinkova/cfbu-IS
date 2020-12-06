<%@ page import = "java.util.*, vis.business.*" %>
<%@ include file="header.jsp" %>
<main>
    <center>
        <%
        String playerIDString = "";
        Enumeration paramNames = request.getParameterNames();

        while (paramNames.hasMoreElements()) {
            playerIDString = (String) paramNames.nextElement();
        }

        Integer playerID;
        try {
            playerID = Integer.parseInt(playerIDString);
        } catch (NumberFormatException e) {
            playerID = -1;
        }

        /** PREROB DO BUSINESS */

        if (playerID != -1) {
            Player p = Player.fetchByID(playerID);
            Team t = Team.fetchByID(p.getTeamID());
            ArrayList<Team> allTeams = Team.fetch();
            Statistics s = Statistics.fetchByPlayerID(playerID);

            out.println("<form id='form' method='POST' action='update_done.jsp'>");
            out.println("<h2>Player no. "+ p.getId() +"</h2><div class='detail-wrapper'>");
            out.println("<div class='row'><p><strong>Name: </strong><input type='text' name='newname' value='" + p.getName() + "'></p>");
            out.println("<p><strong>Sure name: </strong><input type='text' name='newsurename' value='" + p.getSureName() + "'></p></div>");
            out.println("<div class='row'><p><strong>Email: </strong><input type='text' name='newemail' value='" + p.getEmail() + "'></p>");
            out.println("<p><strong>Covid: </strong><input type='number' name='newcovid' value='" + p.getCovid() + "'></p></div>");
            out.println("<div class='row'><p><strong>Date of birth: </strong>" + p.getDateOfBirth() +"</p>");
            out.println("<p><strong>Quarantined from: </strong><input type='text' name='newquarantinedfrom' value='" + p.getQuarantinedFrom() + "'></div>");
            out.println("<div class='row'><p><strong>Stick: </strong>" + p.getStick() + "</p>");
            out.println("<p><strong>Team: </strong>" + t.getName() + "</p></div>");
            out.println("<div class='row'><p><strong>Goals: </strong>" + s.getGoals() + "</p>");
            out.println("<p><strong>Assists: </strong>" + s.getAssists() + "</p></div></div>");

            out.println("<select id='select'>");
            for(Team team : allTeams) {
                if(t.getId() == team.getId()) {
                    out.println("<option selected data-teamid='"+ team.getId() +"' data-leagueid='"+team.getLeagueID()+"'>" + team.getName() +"</option>");
                } else {
                    out.println("<option data-teamid='"+ team.getId() +"' data-leagueid='"+team.getLeagueID()+"'>" + team.getName() +"</option>");
                }

            }
            out.println("</select>");

            out.println("<button type='button' id='button' class='btn'>Submit</button><br>");
            out.println("<input type='hidden' name='id' value='" + p.getId() + "'>");
            out.println("<input type='hidden' name='changeleague' value='0'>");
            out.println("<input type='hidden' name='teamid' value='" + p.getTeamID() + "'>");
            out.println("<input type='hidden' name='currentLeagueID' value='" + p.getTeamID() + "'>");
            out.println("<input type='hidden' name='date' value='" + p.getDateOfBirth() + "'>");
            out.println("<input type='hidden' name='stick' value='" + p.getStick() + "'>");
            out.println("<input type='hidden' name='declined' value='0'>");
        }
        %>
    </center>
</main>
<%@ include file="footer.jsp" %>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.7.1.js"></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.16/jquery-ui.js"></script>
<link rel="stylesheet" type="text/css" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.17/themes/base/jquery-ui.css">
<script>
    document.getElementById('button').addEventListener('click', function () {
        var qurantinedFromDateVal = document.getElementsByName('newquarantinedfrom')[0].value;

        //check date format
        if(isValidDate(qurantinedFromDateVal) || qurantinedFromDateVal == 'null') {
            document.getElementsByName('newquarantinedfrom')[0].style.border = '1px solid black';
            // check league
            var currentLeagueID = document.getElementsByName('currentLeagueID')[0].value;

            for (var option of document.getElementById('select')) {
                if(option.selected) {
                    var newLeagueID = option.getAttribute('data-leagueid');
                    var newTeamID = option.getAttribute('data-teamid');
                }
            }

            if(newLeagueID != currentLeagueID) {
                $(function() {
                    var dialog = $('<p>Team is in another league, do you want to continue?</p>').dialog({
                        title: "League change",
                        buttons: {
                            "Yes": function() {
                                document.getElementsByName('changeleague')[0].value = 1;
                                document.getElementsByName('teamid')[0].value = newTeamID;
                                document.getElementById('form').submit();
                            },
                            "No - change team":  function() {
                                alert('You chose to change team');
                            },
                            "Abort":  function() {
                                dialog.dialog('close');
                                document.getElementsByName('declined')[0].value = 1;
                                document.getElementById('form').submit();
                            }
                        }
                    });
                });
            } else {
                document.getElementsByName('teamid')[0].value = newTeamID;
                document.getElementById('form').submit();
            }
        } else {
            document.getElementsByName('newquarantinedfrom')[0].style.border = '1px solid red';
        }
    });

    // Validates that the input string is a valid date formatted as "YYYY-mm-dd"
    function isValidDate(dateString)
    {
        // First check for the pattern
        if(!/^\d{4}-\d{1,2}-\d{1,2}$/.test(dateString))
            return false;

        // Parse the date parts to integers
        var parts = dateString.split("-");
        var day = parseInt(parts[2], 10);
        var month = parseInt(parts[1], 10);
        var year = parseInt(parts[0], 10);

        // Check the ranges of month and year
        if(year < 1000 || year > 3000 || month == 0 || month > 12)
            return false;

        var monthLength = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 ];

        // Adjust for leap years
        if(year % 400 == 0 || (year % 100 != 0 && year % 4 == 0))
            monthLength[1] = 29;

        // Check the range of the day
        return day > 0 && day <= monthLength[month - 1];
    };

</script>