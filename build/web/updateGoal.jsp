<%-- 
    Document   : updateGoal
    Created on : 19-Apr-2015, 18:44:57
    Author     : xmw13bzu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import ="Controllers.*"%>
<%@page import ="Model.*"%>
<%@page import ="Goals.*"%>

<%
    
    Member member = (Member) session.getAttribute("member"); 
    int memberID = member.getUserID();
    if (member == null) {
        //something or other 
    }
    
    Goal goal = null;
    String goalIDstr = request.getParameter("goalID");

    if (goalIDstr != null)
        goal = Goal.find(Integer.parseInt(goalIDstr));
%>
                        
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>HONG KONG FUSIOOOOON</title>
        <link href="styles/main.css" rel="stylesheet" type="text/css">

    </head>

    <body>
        <div id="wrapper">
            
            <footer>
                <article id="disclaimer">
                    <span>Disclaimer:</span> This application is not a commercial application and does not provide
                    insurance. This is a study project that is part of a Computing Science module taught at the
                    University of East Anglia, Norwich, UK. If you have any questions, please contact the
                    module coordinator, Joost Noppen, at j.noppen@uea.ac.uk
                </article>
            </footer>
            
            <header id="top">
                <h1>HONG KONG FUSIOOOOON</h1>
                <nav id="mainnav">
                    <ul>
                        <li><a href="home.jsp">Home</a></li>
                        <li><a href="GoalsController" class="thispage">Goals</a></li>
                        <li><a href="PhysicalHealthLogController">Weight</a></li>
                        <li><a href="ExerciseLogController">Exercise</a></li>
                        <li><a href="DietLogController">Diet</a></li>
                        <li><a href="accountManagement.jsp">Account</a></li> 
                        <li><a href="LogoutController">Log Out</a></li>       
                    </ul>
                </nav>
            </header>
            <br>
            
            <article id="mainbook">
                
                <%
                    String errorMessage = (String) request.getAttribute("errorMessage");
                    if (errorMessage != null) {
                %>
                        <p class="error"><%=errorMessage%></p>
                <%
                    }
                %>
                
                <h3>
                    Enter details in fields you wish to change
                </h3>
               
                <% 
                    int target = goal.getTarget();
                    if (goal.getGoalType() == Goal.GoalType.WEIGHT_HIGH || goal.getGoalType() == Goal.GoalType.WEIGHT_LOW) { 
                        target = (int) (target/1000.0);
                    }
                %>
                
                <form name="update" action="UpdateGoalController" method="get">
                    <input type="hidden" name="goalID" value="<%=goal.getGoalID()%>"/>
                    <p>End Date:<input type="date" name="goalDeadline" value="<%=goal.getEndDate()%>" class="textbox"/></p>     
                    <p>Target:<input type="number" name="target" value="<%=target%>" class="textbox"/></p>
                    <% if (goal.getGoalType() == Goal.GoalType.WEIGHT_HIGH || goal.getGoalType() == Goal.GoalType.WEIGHT_LOW) { %>
                        <p>
                            <input type="radio" name="wUnit" value="metric" checked >Metric</input>
                            <input type="radio" name="wUnit" value="imperial">Imperial</input>
                        </p>
                    <% } %>
                    <p><input type="submit" name="submit" value="Update"/></p>
                </form>

                <h7>
                    <b>Weight Loss and Weight Gain:</b> if you choose Metric please enter the amount in kg (just number).<br> If you choose Imperial please enter the amount in lbs (just number).<br>
                    <b>Exercise Time: </b> please enter the target amount in minutes (just number - Metric and Imperial do not apply here)<br>
                    <b>Calorie Burn, Min and Max Calorie Consumption:</b> please enter the target amount in kCal (just number - Metric and Imperial do not apply here)
                </h7>
                    
            </article>
                
            <br><br>
            <footer>
                <p>&copy; Copyright 2015 
                </p>
            </footer>        
        </div>
    </body>
</html>
