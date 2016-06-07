<%-- 
    Document   : accountManagement
    Created on : 19-Apr-2015, 16:05:01
    Author     : xmw13bzu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import ="Controllers.*"%>
<%@page import ="Model.*"%>

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
                        <li><a href="home.jsp">Home</a></li>
                        <li><a href="GoalsController">Goals</a></li>
                        <li><a href="PhysicalHealthLogController">Weight</a></li>
                        <li><a href="ExerciseLogController">Exercise</a></li>
                        <li><a href="DietLogController">Diet</a></li>
                        <li><a href="accountManagement.jsp" class="thispage">Account</a></li> 
                        <li><a href="LogoutController">Log Out</a></li>       
                    </ul>
                </nav>
            </header>
            <div class="divTable">
                <div class="divRow">
                    <article class="contentArea" id="leftHalf">
                         <article class="basicArea">
                
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
                            <form name="update" action="UpdateUserController" method="get">
                                <p>Email:<input type="text" name="email" class="textbox"/></p>
                                <p>Password:<input type="password" name="password" id="textbox"/></p>
                                <p>Forename:<input type="text" name="forename" class="textbox"/></p>
                                <p>Surname:<input type="text" name="surname" class="textbox"/></p>
                                <p>Height:<input type="text" name="height" class="textbox"/></p>
                                <p><input type="radio" name="hUnit" value="metric" checked>Metric</input>
                                <input type="radio" name="hUnit" value="imperial">Imperial</input>
                                </p>
                                <p><input type="submit" name="submit" value="Update"/>
                                <input type="reset" name="submit" value="Reset"/></p>
                            </form>
                            <h7><b>Height:</b> if you choose Metric please enter the amount in centimetres.<br> If you choose Imperial please enter the amount in feet and inches e.g. 6'1.</h7>

                        </article>
                    </article>

                    <article id="centre"></article>

                    <article class="contentArea" id="rightHalf">
                         <article class="basicArea">
                            <h3>Delete Account</h3>

                            <form name="accountDelete" action="UpdateUserController" method="get">
                                <input type="submit" name="submit" value="Delete Account">
                            </form>
                        </article>
                    </article>
                </div>
           </div>
                
            <br><br>
            <footer>
                <p>&copy; Copyright 2015 
                </p>
            </footer>        </div>
    </body>
</html>
