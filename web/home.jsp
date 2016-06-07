<%-- 
    Document   : home.jsp
    Created on : 04-Mar-2015, 14:51:04
    Author     : xmw13bzu
--%>

<%@page import="Goals.GoalLogger"%>
<%@page import="Goals.Goal"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.PhysicalHealth.PhysicalHealth"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import ="Controllers.*"%>
<%@page import ="Model.*"%>

<%
    Member member = (Member) session.getAttribute("member"); 
    final boolean loggedIn = (member != null);

    String updateMessage = (String) request.getAttribute("updateMessage");

    ExerciseLogger exLog = ExerciseLogger.find(member.getUserID());
    int avgActivityPerDay = exLog.findAverageDailyActivityTime();

    DietLogger dietLog = DietLogger.find(member.getUserID());
    int avgCalsPerDay = dietLog.findAverageDailyCalsConsumed();

    PhysicalHealth health = PhysicalHealth.find(member.getUserID());
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
                <h1>Hong Kong Fusion Health Tracker</h1>
                <nav id="mainnav">
                    <ul>
                        <li><a href="home.jsp" class="thispage">Home</a></li>
                        <li><a href="GoalsController">Goals</a></li>
                        <li><a href="PhysicalHealthLogController">Weight</a></li>
                        <li><a href="ExerciseLogController">Exercise</a></li>
                        <li><a href="DietLogController">Diet</a></li>
                        <li><a href="accountManagement.jsp">Account</a></li> 
                        <li><a href="LogoutController">Log Out</a></li>       
                    </ul>
                </nav>
            </header>
            
            
            <!--<article id="main">-->
            <div class="divTable">
              <div class="divRow">
                  <article class="contentArea" id="leftHalf">
                       <article class="basicArea">
                            <%
                                String errorMessage = (String) request.getAttribute("errorMessage");
                                if (errorMessage != null) {
                            %>
                                    <p class="error">Error: <%=errorMessage%></p>
                            <%
                                }
                            %>

                            <h3>
                                Your Info:
                            </h3>

                            <p><b>Username:</b> <%=member.getUsername()%></p>   
                            <p><b>Email:</b> <%=member.getEmail()%></p>
                            <p><b>Forename:</b> <%=member.getForename()%></p>
                            <p><b>Surname:</b> <%=member.getSurname()%></p>
                            <p><b>Height:</b> <%=health.getHeight()%> cm</p>
                            <p><b>Current weight:</b> <%=health.getMostRecentWeightProgress().getWeight().toNicelyDisplay()%></p>
                
                        </article>
                    </article>

                    <article id="centre"></article>
                    
                    <article class="contentArea" id="rightHalf">
                         <article class="basicArea">
                
                           

                            <h3>
                                Your Health Summary: 
                            </h3>
                                <p><b>Healthiness:</b> <%=member.calculateHealthiness()%>/10</p>
                                <p><b>Body Mass Index:</b> <%=String.format("%.2f",member.calculateBMI())%></p>
                                <p><b>Weight Status:</b> <%=member.getWeightStatus()%></p>
                            <p><b>AVG Activity per day:</b> <%=avgActivityPerDay%> mins</p>
                            <p><b>AVG Calories Consumed per day:</b> <%=avgCalsPerDay%> kCal</p>
                            <h7><b>Healthiness</b> is calculated based on comparing weight status, average activity per day and average calories consumed per day to the healthy standards.</h7>
                        </article>
                        
                    </article>
                </div>
           </div>
                            
            <div id="mainhome">
                <%
                    GoalLogger goalLog = GoalLogger.find(member.getUserID());
                    ArrayList<Goal> dueGoals = goalLog.findDueGoals();
                    
                %>
                
                <h3>
                    Notifications:
                </h3>
                <% if (!dueGoals.isEmpty()){
                    ArrayList<Integer> dueGoalsProgress = GoalLogger.checkProgress(dueGoals, member.getUserID());
                %>
                    
                    <p>
                        Goals due today
                    </p>

                    <table class="tablecenter" >
                        <tr>
                             <th>GOAL</th> 
                             <th>TARGET</th> 
                             <th>CURRENT PROGRESS</th>
                             <th>UPDATE/DELETE</th>
                        </tr>

                        <%
                            for (int i = 0; i < dueGoals.size(); ++i) {
                                Goal goal = dueGoals.get(i);
                                    String target = "";
                                      Goal.GoalType goalType = goal.getGoalType();
                                       if(goalType.equals(Goal.GoalType.ACTIVITY_TIME)){
                                            target = goal.getTarget() + " mins";
                                        }else 
                                        if(goalType.equals(Goal.GoalType.WEIGHT_HIGH) || goalType.equals(Goal.GoalType.WEIGHT_LOW)){
                                            target = goal.getTarget()/1000 + " kg";
                                        }else{
                                            target = goal.getTarget() + " kCal";
                                        }         
                                        String currentProgress = dueGoalsProgress.get(i) + "%";
                        %>
                                <tr>
                                    <td><%=goalType.toString()%></td>   
                                    <td><%=target%></td>
                                    <td><%=currentProgress%></td>
                                    <td>
                                        <form name="login" action="updateGoal.jsp" method="get">
                                           <input type="hidden" name="goalID" value="<%=dueGoals.get(i).getGoalID()%>"/>
                                           <input type="submit" name="submit" value="Update"/>
                                        </form>
                                        <form name="login" action="UpdateGoalController" method="get">
                                            <input type="hidden" name="goalID" value="<%=dueGoals.get(i).getGoalID()%>"/>
                                            <input type="submit" name="submit" value="Cancel"/>
                                        </form>
                                    </td> 
                                </tr>
                        <%                        
                            }
                        %>
                    </table>
                 <% } 
                    else if (updateMessage != null) { 
                 %>
                        <p class="error"><%=updateMessage%></p>
                <%
                    }
                     else {  
                %>
                        <p>No notifications</p>
                <% } %>
            </div>
                
            <br><br>
            <footer>
                <p>&copy; Copyright 2015 
                </p>
            </footer>        </div>
    </body>
</html>
