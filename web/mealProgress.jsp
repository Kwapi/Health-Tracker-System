<%-- 
    Document   : mealProgress
    Created on : 14-Apr-2015, 12:10:25
    Author     : xmw13bzu
--%>

<%@page import="java.util.TreeSet"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.TreeMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import ="Controllers.*"%>
<%@page import ="Model.*"%>
<%@page import ="Model.Meal.*"%>

<%
    
    Member member = (Member) session.getAttribute("member");
    String errorMessage = (String) request.getAttribute("errorMessage");
    //no one is logged in
    if (member == null) {
        //something or other 
    }
    //get diet log
                    DietLogger dietLog = (DietLogger) session.getAttribute("dietLog"); 
                    //get list of all available meals
                    ArrayList<Meal> meals = Meal.findAll();
                    //get list of all exercises from the chosen date to be displayed in advanced graphs (if there is one)
                    ArrayList<MealProgress> specificProgresses = (ArrayList<MealProgress>) session.getAttribute("specificMeals");
                    //get list of calories eaten per day
                    TreeMap<HKFDate, Integer> calEatenPerDay = dietLog.findCalsConsumedPerDay();
%>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <script type="text/javascript">

      // Load the Visualization API and the piechart package.
      google.load('visualization', '1.0', {'packages':['corechart']});

      // Set a callback to run when the Google Visualization API is loaded.
      google.setOnLoadCallback(startChart);
      var loadMoreGraphs = <%=!specificProgresses.isEmpty()%>;


                    
                    function drawCalBreakDown() {
                      
                      var data = new google.visualization.DataTable();
                      data.addColumn('string', 'Date');
                     // data.addColumn('string', 'Meal name');
                      
                      
                      // index in array corresponds to (meal.Id)
                      // i.e. Pizza.id = 2, 
                      <%for (Meal meal: meals){%>
                        data.addColumn('number', '<%=meal.getMealName()%>');
                         
                      <%}%>
                      
                      
                      
                      <%
                      //******************JSP***************//
                        
                        ArrayList<Object> row = new ArrayList<Object>();
                      
                      
                        //create a temporary row containg 
                        //date and calories per all possible meals initialised to 0                        
                        row.add(new HKFDate());
                        int mealSize = 0;
                        for (Meal meal : meals){
                              mealSize++;
                              row.add(Integer.valueOf(0));
                          }
                        
                        
                          int counter = 0;
                          
                      for (MealProgress mealProg : specificProgresses){
                          
                         
                          
                          HKFDate date = mealProg.getDate();                                                                             
                          row.set(0, date);
                          int mealIndex = mealProg.getMeal().getID();
                          
                          
                          
                                                     
                              //add to whatever is already there (might be 0, might be a value)                                                           
                              Integer valueToStore = Integer.valueOf(mealProg.calcCalories());
                              Integer valueStored = (Integer) row.get(mealIndex);
                                      
                              row.set(mealProg.getMeal().getID(), valueStored + valueToStore);
                          
                          if (counter == specificProgresses.size() - 1){
                             
                             //output row to GOOGLE GRAPHS
                             %>   
                                 data.addRow([new Date(<%=date.getYear()%>,<%=date.getMonthForGraph()%>,<%=date.getDay()%>).toDateString()
                                     <%
                                     String mealCell;
                                     for(int i = 1; i<=mealSize; i++){
                                         mealCell = ", " + ((Integer)row.get(i)).intValue();
                                         %>
                                                     <%=mealCell%>
                                                         
                                     <%
                                     }
                                     %>                               
                                ]);     
                             <% 
                              //reload row (clean it up)
                              for (int j = 1; j<=mealSize; j++){
                                   row.set(j,Integer.valueOf(0));
                               }
                              //start a new one
                             row.set(mealIndex, Integer.valueOf(mealProg.calcCalories()));
                             
                             
                          }                         
                         counter++;
                        }%>  
                                
                        
                                                  
                          
                                     

                      var options = {
                        title: 'Calories consumed per meal',                                              
                        width: 900,
                        height: 500,
                        legend: 'none',
                        isStacked: 'true'
                        
                       
                      };
                       var formatter = new google.visualization.NumberFormat(
                          {pattern: '# kCal'});
                  
                                 <% for (int i = 1; i < meals.size(); i++){%>
                          formatter.format(data, <%=i%>); // Apply formatter to all number columns
                                                  
                        <%}%>
                       var chart = new google.visualization.BarChart(document.getElementById('calBreakDown'));
                       
                       //getting the total amount of calories consumed per date
                       
                      chart.draw(data, options);
                    }
                    
                   
                    
                    
                    function prepareData() {
                      
                      data = new google.visualization.DataTable();
                      data.addColumn('date', 'Date');
                      data.addColumn('number', 'Calories')
                      
                      
                      <%
                          
                          
                          for (Map.Entry<HKFDate,Integer> entry : calEatenPerDay.entrySet()){
                                HKFDate date = entry.getKey();
                                int calEaten = (int) entry.getValue();
                          
                      %>                      
                        data.addRow([new Date(<%=date.getYear()%>,<%=date.getMonthForGraph()%>,<%=date.getDay()%>), <%=calEaten%>]);
                       <%}%>  

                    }
                    
                    function prepareChart(){
                         options = {
                             title: 'Calories consumed per day',                                              
                            width: 900,
                            height: 500,
                            pointSize: 20,
                            legend: 'none',
                             hAxis: {title: 'Day'},
                             vAxis: {title: 'Calories [kCal]'},
                             crosshair: { 
                                          trigger: 'both',
                                          orientation: 'horizontal',
                                          color: 'red'                                                                                   
                                        }
                           
                            
                          };

                          var formatter = new google.visualization.NumberFormat(
                          {pattern: '# kCal'});
                          formatter.format(data, 1); // Apply formatter to second column
                          
                       
                          chart = new google.visualization.LineChart(document.getElementById('linechart'));
                    }
                    
                    
                    function drawChart(){
                      chart.draw(data, options);
                    }
                    
                    function startChart(){
                      if(loadMoreGraphs){
                          drawCalBreakDown();
                      }
                      prepareData();
                      prepareChart();
                      drawChart();
                      
                    }
                                                           
                </script>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>HONG KONG FUSION HEALTH TRACKER</title>
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
                        <li><a href="PhysicalHealthLogController">Weight</a></li>
                        <li><a href="ExerciseLogController">Exercise</a></li>
                        <li><a href="DietLogController" class="thispage">Diet</a></li>
                        <li><a href="accountManagement.jsp">Account</a></li> 
                        <li><a href="LogoutController">Log Out</a></li>       
                    </ul>
                </nav>
            </header>
            <article id="meh">
                
              
                
                <%
                    
                    if (specificProgresses.isEmpty()){
                        //do nothing
                    }else{
                        HKFDate today = new HKFDate();
                        HKFDate obtainedDate = specificProgresses.get(0).getDate();
                        String finalDateStr = "ON " + obtainedDate.toString();
                        if(today.compareToWithoutTime(obtainedDate)==0){
                             finalDateStr = "TODAY";
                        }
                            
                        
                    //display the graphs%>
                    
                    <h5>
                    CALORIES CONSUMED <%=finalDateStr%>
                    </h5>
                        <div id="calBreakDown"></div>
                                           
                    <%}%>
                
                
                <h5>
                YOUR MEAL LOG  
                </h5>
                <%  if (!calEatenPerDay.isEmpty()){%>
                 <div id="linechart"></div>
                 <h5>
                    GET A MORE DETAILED VIEW
               </h5>                            
                <%
                        TreeSet<String> dateSet = new TreeSet<String>();
                        for (MealProgress mealProg : dietLog.sortDate()) {
                            dateSet.add(mealProg.getDate().toString());
                        }
                %>
                <form name="chooseDate" action="DietLogController" method="get">
                    <p>
                        <select name="specificDate">
                            <%
                                for (String date : dateSet) {
                            %>
                            <option value="<%=date%>"><%=date%></option>
                            <%
                                }
                            %>
                        </select>
                        <input type="submit" value="View"/>
                    </p>
                </form>
                 
                 
                <%}else{%>
                <p><i>YOU HAVE NO RECORDED MEALS</i></p>  
                <%}%> 
                
                             
                <h5>
                    LOG NEW MEAL
                </h5>
                              
                 <form name="login" action="LogMealController" method="get">
                    <p>Date:<input type="date" name="date" value="<%=new HKFDate().toString()%>" class="textbox"/></p>
                    <!--<p>Exercise:<input type="text" name="exercise" class="textbox"/></p>-->
                    <p>Meal: 
                        <select name="meal" class ="textbox">
                            <%
                                for (Meal meal : meals) {
                                    if (meal.getMealName().startsWith("t_")) 
                                        continue;
                            %>
                                    <option value="<%=meal.getID()%>"><%=meal.getMealName()%> (<%=meal.getCalsPerUnit()%> kCal)</option>
                            <%                        
                                }
                            %>
                        </select>
                    </p>
                                        
                    <p>Meal Type:
                        <select name="mealType" class ="textbox">
                            <%
                                for (MealProgress.MealTime type : MealProgress.MealTime.values()) {
                            %>
                                    <option value="<%=type.getValue()%>"><%=type.toString()%></option>
                            <%                        
                                }
                            %>
                        </select>
                    </p>
                            
                    <p>Amount:<input type="number" name="amount" value="1" class="textbox"/></p>
                    <p><input type="submit" value="Enter"/>
                    <input type="reset" value="Reset"/></p>
                </form>
                        
                          <%                    
                    //display error message if there is one
                    if (errorMessage != null) {
                %>
                        <p class="error"><%=errorMessage%></p>
                <% }%>
                
            </article>
                
            <br><br>
            <footer>
                <p>&copy; Copyright 2015 
                </p>
            </footer>        </div>
    </body>
</html>
