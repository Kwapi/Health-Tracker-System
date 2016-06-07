<%-- 
    Document   : goalProgress
    Created on : 16-Apr-2015, 18:28:41
    Author     : xmw13bzu
--%>

<%@page import="Goals.Goal.GoalType"%>
<%@page import="java.util.ArrayList"%>
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
%>

                                                
<%
    GoalLogger goalLog = (GoalLogger) session.getAttribute("goalLog"); 

    ArrayList<Goal> finishedGoals = goalLog.findFinishedGoals();
    ArrayList<Goal> dueGoals = goalLog.findDueGoals();
    ArrayList<Goal> upcomingGoals = goalLog.findUpcomingGoals();
    ArrayList<Goal> allGoals = goalLog.getGoalList();
    ArrayList<Integer> finishedGoalsProgress = GoalLogger.checkProgress(finishedGoals, memberID);
    ArrayList<Integer> dueGoalsProgress = GoalLogger.checkProgress(dueGoals, memberID);
    ArrayList<Integer> upcomingGoalsProgress = GoalLogger.checkProgress(upcomingGoals, memberID);
%>


                 <script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <script type="text/javascript">

      // Load the Visualization API and the piechart package.
      google.load('visualization', '1.0', {'packages':['corechart']});

      // Set a callback to run when the Google Visualization API is loaded.
      google.setOnLoadCallback(startChart);
      

                    
                    function prepareDataSoon() {
                      
                      data = new google.visualization.DataTable();
                      data.addColumn('string', 'Goal');
                      data.addColumn('number', 'Percentage completed')
                      data.addColumn({type: 'string', role: 'tooltip', p: {'html': true}});
                                                                  
                      
                      <%
                              for(int i = 0; i< upcomingGoals.size(); i++){
                                  Goal goal = upcomingGoals.get(i);
                                  String goalName = goal.getGoalType().toString();
                                  int percentageCompleted = upcomingGoalsProgress.get(i);
                                  String startDate = goal.getStartDate().toString();
                                  String endDate = goal.getEndDate().toString();
                                  String target = "";
                                  GoalType goalType = goal.getGoalType();
                                   if(goalType.equals(GoalType.ACTIVITY_TIME)){
                                        target = goal.getTarget() + " mins";
                                    }else 
                                    if(goalType.equals(GoalType.WEIGHT_HIGH) || goalType.equals(GoalType.WEIGHT_LOW)){
                                        target = goal.getTarget()/1000 + " kg";
                                    }else{
                                        target = goal.getTarget() + " kCal";
                                    }               
                                  %>

                                  data.addRow(['<%=goalName%>', <%=percentageCompleted%>,'<b>Start Date</b>: <%=startDate%><br><b>End Date:</b> <%=endDate%><br><b>Target:</b><%=target%><br><b>Completed: </b><%=percentageCompleted%>%']);
                         
                       <%}%>  

                    }
                    function prepareChartSoon(){
                         options = {
                             title: 'Goals Due Soon',                                              
                            width: 900,
                            height: 500,
                            vAxis:{
                                minValue: 0,
                                maxValue: 100,
                                format: '#\'%\'',
                                gridlines: {
                                    count: -1
                                }
                            },
                            tooltip: { isHtml: true }
                            
                                                      
                            
                           
                            
                          };

                          chart = new google.visualization.ColumnChart(document.getElementById('goalsDueSoon'));
                    }                                       
                    function drawChartSoon(){
                      chart.draw(data, options);
                    }
                    
                    function prepareDataToday() {
                      
                      data = new google.visualization.DataTable();
                      data.addColumn('string', 'Goal');
                      data.addColumn('number', 'Percentage completed')
                      data.addColumn({type: 'string', role: 'tooltip', p: {'html': true}});
                                                                  
                      
                      <%
                              for(int i = 0; i< dueGoals.size(); i++){
                                  Goal goal = dueGoals.get(i);
                                  String goalName = goal.getGoalType().toString();
                                  int percentageCompleted = dueGoalsProgress.get(i);                                  
                                  String target = "";
                                  GoalType goalType = goal.getGoalType();
                                   if(goalType.equals(GoalType.ACTIVITY_TIME)){
                                        target = goal.getTarget() + " mins";
                                    }else 
                                    if(goalType.equals(GoalType.WEIGHT_HIGH) || goalType.equals(GoalType.WEIGHT_LOW)){
                                        target = goal.getTarget()/1000 + " kg";
                                    }else{
                                        target = goal.getTarget() + " kCal";
                                    }         
                                  
                                  %>

                                  data.addRow(['<%=goalName%>', <%=percentageCompleted%>,'<b>Target:</b><%=target%><br><b>Completed: </b><%=percentageCompleted%>%']);
                         
                       <%}%>  

                    }
                    function prepareChartToday(){
                         options = {
                             title: 'Goals Due Today',                                              
                            width: 900,
                            height: 500,
                            vAxis:{
                                minValue: 0,
                                maxValue: 100,
                                format: '#\'%\'',
                                gridlines: {
                                    count: -1
                                }
                            },
                            tooltip: { isHtml: true }
                            
                                                      
                            
                           
                            
                          };

                          chart = new google.visualization.ColumnChart(document.getElementById('goalsDueToday'));
                    }                                       
                    function drawChartToday(){
                      chart.draw(data, options);
                    }
                    function startChart(){
                      prepareDataSoon();
                      prepareChartSoon();
                      drawChartSoon();
                       prepareDataToday();
                      prepareChartToday();
                      drawChartToday();
                      
                    }
                                                           
                </script>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Hong Kong Fusion Health Tracker</title>
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
                <h1>HONG KONG FUSION HEALTH TRACKER</h1>
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
            <article id="meh">
               
                <%
                    String updateMessage = (String) request.getAttribute("updateMessage");
                    if (updateMessage != null) {
                %>
                        <p class="error"><%=updateMessage%></p>
                <%
                    }
                %>        
                        
                <h5>
                    GOALS DUE TODAY
                </h5>
                <% if (!dueGoals.isEmpty()){%>
                  <div id="goalsDueToday"></div>

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
                                  GoalType goalType = goal.getGoalType();
                                   if(goalType.equals(GoalType.ACTIVITY_TIME)){
                                        target = goal.getTarget() + " mins";
                                    }else 
                                    if(goalType.equals(GoalType.WEIGHT_HIGH) || goalType.equals(GoalType.WEIGHT_LOW)){
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
                <%}else{%>
                    <p><i>YOU HAVE NO GOALS THAT ARE DUE TODAY</i></p>
                <%}%>
                <h5>
                    GOALS DUE SOON
                </h5>
                <%if (!upcomingGoals.isEmpty()){%>
                    
                
                  <div id="goalsDueSoon"></div>
                
                <!-- for each exercise progress -->
                    <!-- print date : exercisename, cals burned --> 
                <table class="tablecenter">
                    <tr>
                         <th>GOAL</th> 
                         <th>TARGET</th> 
                         <th>DATE DUE</th>
                         <th>CURRENT PROGRESS</th>
                         <th>UPDATE/DELETE</th>
                    </tr>
                   
                    <%
                        for (int i = 0; i < upcomingGoals.size(); ++i) {
                            Goal goal = upcomingGoals.get(i);
                            String target = "";
                                  GoalType goalType = goal.getGoalType();
                                   if(goalType.equals(GoalType.ACTIVITY_TIME)){
                                        target = goal.getTarget() + " mins";
                                    }else 
                                    if(goalType.equals(GoalType.WEIGHT_HIGH) || goalType.equals(GoalType.WEIGHT_LOW)){
                                        target = goal.getTarget()/1000 + " kg";
                                    }else{
                                        target = goal.getTarget() + " kCal";
                                    }  
                                   String currentProgress = upcomingGoalsProgress.get(i) + "%";
                    %>
                            <tr>
                                <td><%=upcomingGoals.get(i).getGoalType().toString()%></td>   
                                <td><%=target%></td>
                                <td><%=upcomingGoals.get(i).getEndDate()%></td>
                                <td><%=currentProgress%></td>
                                <td>
                                    <form name="login" action="updateGoal.jsp" method="get">
                                       <input type="hidden" name="goalID" value="<%=upcomingGoals.get(i).getGoalID()%>"/>
                                       <input type="submit" name="submit" value="Update"/>
                                    </form>
                                    <form name="login" action="UpdateGoalController" method="get">
                                        <input type="hidden" name="goalID" value="<%=upcomingGoals.get(i).getGoalID()%>"/>
                                        <input type="submit" name="submit" value="Cancel"/>
                                    </form>
                                </td> 
                            </tr>
                    <%                        
                        }
                    %>
                </table>
                <%}else{%>
                     <p><i>YOU HAVE NO GOALS THAT ARE DUE SOON</i></p>
                <%}%>
                <h5>
                    ALL YOUR GOALS
                </h5>

                <!-- for each exercise progress -->
                    <!-- print date : exercisename, cals burned --> 
                    
                    <% if(!allGoals.isEmpty()){
                        
                        //check if there are any goals in progress
                        boolean goalsInProgressDisplay = false;
                        for(Goal goal : allGoals){
                            if(goal.getEndDate().compareTo(new HKFDate()) >= 0){
                                goalsInProgressDisplay = true;
                            }
                        }
                        
                        if(goalsInProgressDisplay){
                        %>
                    <p class="tableTitle"> IN PROGRESS</p>
                <table class="tablecenter">
                    <tr>
                         <th>GOAL</th> 
                         <th>TARGET</th> 
                         <th>START DATE</th>
                         <th>END DATE</th>
                         <th>UPDATE/DELETE</th>
                    </tr>
                   
                <%
                    for (Goal goal : allGoals) {
                %>
                        <tr>
                            <%
                                if (goal.getEndDate().compareTo(new HKFDate()) >= 0) {
                                    
                            String target = "";
                                  GoalType goalType = goal.getGoalType();
                                   if(goalType.equals(GoalType.ACTIVITY_TIME)){
                                        target = goal.getTarget() + " mins";
                                    }else 
                                    if(goalType.equals(GoalType.WEIGHT_HIGH) || goalType.equals(GoalType.WEIGHT_LOW)){
                                        target = goal.getTarget()/1000 + " kg";
                                    }else{
                                        target = goal.getTarget() + " kCal";
                                    }         
                                    
                            %>
                            <td><%=goalType.toString()%></td>   
                            <td><%=target%></td>
                            <td><%=goal.getStartDate()%></td>
                            <td><%=goal.getEndDate()%></td>
                            <td>
                                       <form name="login" action="updateGoal.jsp" method="get">
                                       <input type="hidden" name="goalID" value="<%=goal.getGoalID()%>"/>
                                       <input type="submit" name="submit" value="Update"/>
                                    </form>
                                    <form name="login" action="UpdateGoalController" method="get">
                                        <input type="hidden" name="goalID" value="<%=goal.getGoalID()%>"/>
                                        <input type="submit" name="submit" value="Cancel"/>
                                    </form>
                            <% 
                                }
                            %>
                            </td>
                        </tr>
                <%                        
                    }
                        }
                %>
                </table>  
                <% if (!finishedGoals.isEmpty()){
                    %>
                
                <p class="tableTitle"> FINISHED </p>
                <table class="tablecenter">
                    <tr>
                         <th>GOAL</th> 
                         <th>TARGET</th> 
                         <th>START DATE</th>
                         <th>END DATE</th>
                         <th>COMPLETION</th>
                    </tr>
                 <%
                    for (int i = 0; i<finishedGoals.size(); i++) {
                        Goal goal = finishedGoals.get(i);
                        String percentage = (int)finishedGoalsProgress.get(i) + "%";
                        GoalType goalType = goal.getGoalType();
                        String target = "";
                        if(goalType.equals(GoalType.ACTIVITY_TIME)){
                            target = goal.getTarget() + " mins";
                        }else 
                        if(goalType.equals(GoalType.WEIGHT_HIGH) || goalType.equals(GoalType.WEIGHT_LOW)){
                            target = goal.getTarget()/1000 + " kg";
                        }else{
                            target = goal.getTarget() + " kCal";
                        }                      
                        
                        
                        
                        
                        
                            %>
                        <tr>
                           
                            <td><%=goal.getGoalType().toString()%></td>   
                            <td><%=target%></td>
                            <td><%=goal.getStartDate()%></td>
                            <td><%=goal.getEndDate()%></td>
                            <td><%=percentage%></td>
                        </tr>
                <%                        
                    }
                %>
                
                </table>
                 <%}
                }else{%>
                <p><i>YOU HAVE NO RECORDED GOALS</i></p>
                        
                    <%}%>    
                
              
                <h5>
                    SET NEW GOAL
                </h5>
                
                <form name="login" action="CreateGoalController" method="get">
                    <p>Goal Type: 
                        <select name="goalType" class ="textbox">
                            <%
                                for (Goal.GoalType gt : Goal.GoalType.values()) {
                            %>
                                    <option value="<%=gt.ordinal()%>"><%=gt.toString()%></option>
                            <%                        
                                }
                            %>
                        </select>
                    </p>

                    <p>Start Date:<input type="date" name="startDate" value="<%=new HKFDate().toString()%>" class="textbox"/></p>     
                    <p>End Date:<input type="date" name="endDate" value="<%=new HKFDate().toString()%>" class="textbox"/></p>     
                    <p>Target:<input type="text" name="target" value="0" class="textbox"/></p>
                    <p>
                        <input type="radio" name="wUnit" value="metric" checked >Metric</input>
                        <input type="radio" name="wUnit" value="imperial">Imperial</input>
                    </p>
                    
                    <p><input type="submit" value="Create Goal" class=textbox"/>
                    <input type="reset" value="Reset" class="textbox"/></p>
                </form>
                    <h7><b>Weight Loss and Weight Gain:</b> if you choose Metric please enter the amount in kg (just number).<br> If you choose Imperial please enter the amount in lbs (just number).<br>
                        <b>Exercise Time: </b> please enter the target amount in minutes (just number - Metric and Imperial do not apply here)<br>
                        <b>Calorie Burn, Min and Max Calorie Consumption:</b> please enter the target amount in kCal (just number - Metric and Imperial do not apply here)</h7>
                    
                     <%
                    String errorMessage = (String) request.getAttribute("errorMessage");
                    if (errorMessage != null) {
                %>
                        <p class="error">Error: <%=errorMessage%></p>
                <%
                    }
                %>
                
            </article>
                
            <br><br>
            <footer>
                <p>&copy; Copyright 2015 
                </p>
            </footer>       
        </div>
    </body>
</html>
