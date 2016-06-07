<%-- 
    Document   : weightProgress.jsp
    Created on : 08-April-2015, 14:51:04
    Author     : xmw13bzu
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import ="Controllers.*"%>
<%@page import ="Model.*"%>
<%@page import ="Model.PhysicalHealth.*"%>

<%
    Member member = (Member) session.getAttribute("member"); 
    if (member == null) {
        //something or other 
    }
%>

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
                        <li><a href="GoalsController">Goals</a></li>
                        <li><a href="PhysicalHealthLogController" class="thispage">Weight</a></li>
                        <li><a href="ExerciseLogController">Exercise</a></li>
                        <li><a href="DietLogController">Diet</a></li>
                        <li><a href="accountManagement.jsp">Account</a></li> 
                        <li><a href="LogoutController">Log Out</a></li>       
                    </ul>
                </nav>
            </header>
            
            <article id="meh">
                
               
                
                <%
                    PhysicalHealth physHealth = (PhysicalHealth) session.getAttribute("physHealth"); 
                %>
                
                <h5>
                    YOUR WEIGHT LOG
                </h5>
                
                <!-- for each weight progress -->
                    <!-- print date : weight --> 
                                  
                           
              <!--Load the AJAX API-->
    <script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <script type="text/javascript">

      // Load the Visualization API and the piechart package.
      google.load('visualization', '1.0', {'packages':['corechart']});

      // Set a callback to run when the Google Visualization API is loaded.
      google.setOnLoadCallback(startChart);

                    
                    
                    function prepareData() {
                      
                      data = new google.visualization.DataTable();
                      data.addColumn('date', 'Date');
                      data.addColumn('number', 'Your Weight')
                      
                      
                      <%for (WeightProgress weightProg : physHealth.sortDate()){
                          HKFDate date = weightProg.getDate();
                          Weight weight = weightProg.getWeight();
                          
                      %>                      
                        data.addRow([new Date(<%=date.getYear()%>,<%=date.getMonthForGraph()%>,<%=date.getDay()%>), <%=weight.toString()%>]);
                       <%}%>  

                    }
                    
                    function prepareChart(){
                         options = {
                             title: 'Your Weight Over Time [kg]',                                              
                            width: 900,
                            height: 500,
                            pointSize: 20,
                            legend: 'none',
                             hAxis: {title: 'Day'},
                             vAxis: {title: 'Weight [kg]'},
                             crosshair: { 
                                          trigger: 'both',
                                          orientation: 'horizontal',
                                          color: 'red'                                                                                   
                                        }
                           
                            
                          };

                          var formatter = new google.visualization.NumberFormat(
                          {suffix: ' kg', fractionDigits: 1});
                          formatter.format(data, 1); // Apply formatter to second column
                          
                       
                          chart = new google.visualization.LineChart(document.getElementById('linechart'));
                    }
                    var button = document.getElementById('b1');
                    
                    function drawChart(){
                      chart.draw(data, options);
                    }
                    
                    function startChart(){
                      prepareData();
                      prepareChart();
                      drawChart();
                    }
                    
                    drawChart();
                    
                
                                       
                </script>
  
                <div id="linechart"></div>
            </article>
                       
            <article id="meh">
                <h5>
                    LOG NEW WEIGHT
                </h5>
            </article>
            <article id="meh2">
                 <form name="login" action="LogWeightController" method="get">
                    <p>Date:<input type="date" name="date" value="<%=new HKFDate().toString()%>" class="textbox"/></p>
                    <p>Weight:<input type="text" name="weight" value="<%=physHealth.getMostRecentWeightProgress().getWeight().getKilos()%>" class="textbox"/></p>
                    <p><input type="radio" name="wUnit" value="metric" checked>Metric</input>
                        <input type="radio" name="wUnit" value="imperial">Imperial</input>
                    </p>
                    <h7><b>Weight:</b> if you choose Metric please enter the amount in kg.<br> If you choose Imperial please enter the amount in lbs.</i></h7>
                    <p><input type="submit" value="Enter"/>
                    <input type="reset" value="Reset"/></p>
                </form>
                
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
            </footer>        </div>
    </body>
</html>
