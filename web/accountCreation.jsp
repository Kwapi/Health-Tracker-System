<%-- 
    Document   : accountCreation
    Created on : 11-Mar-2015, 14:54:32
    Author     : xmw13bzu
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import ="Controllers.*"%>
<%@page import ="Model.*"%>

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
                        <li><a href="index.jsp">Index</a></li>
                        <li><a href="accountCreation.jsp" class="thispage">Sign Up</a></li>                 
                        
                    </ul>
                </nav>
            </header>
            <article id="main">
                
                <%
                    String errorMessage = (String) request.getAttribute("errorMessage");
                    if (errorMessage != null) {
                %>
                        <p class="error"><%=errorMessage%></p>
                <%
                    }
                %>
                
                <h3>
                    Enter details 
                </h3>
                
                <form name="login" action="NewUserController" method="get">
                    <p>Username:<input type="text" name="username" class="textbox"/></p>
                    <p>Email:<input type="text" name="email" class="textbox"/></p>
                    <p>Password:<input type="password" name="password" id="textbox"/></p>
                    <p>Forename:<input type="text" name="forename" class="textbox"/></p>
                    <p>Surname:<input type="text" name="surname" class="textbox"/></p>
                    <p>Current Weight:<input type="text" name="weight" class="textbox"/></p>
                    <p><input type="radio" name="wUnit" value="metric" checked>Metric</input>
                        <input type="radio" name="wUnit" value="imperial">Imperial</input>
                    </p>
                    <p>Current Height:<input type="text" name="height" class="textbox"/></p>
                     <p><input type="radio" name="hUnit" value="metric" checked>Metric</input>
                        <input type="radio" name="hUnit" value="imperial">Imperial</input>
                    </p>
                    <p><input type="submit" value="Update"/>
                    <input type="reset" value="Reset"/></p>
                </form>
                <p><i><b>Weight:</b> if you choose Metric please enter the amount in kg.<br> If you choose Imperial please enter the amount in lbs.</i></p>
                <p><i><b>Height:</b> if you choose Metric please enter the amount in centimetres.<br> If you choose Imperial please enter the amount in feet and inches e.g. 6'1.</i></p>
            </article>
                
            <br><br>
            <footer>
                <p>&copy; Copyright 2015 
                </p>
            </footer>        </div>
    </body>
</html>

