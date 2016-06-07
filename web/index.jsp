<%-- 
    Document   : index
    Created on : 04-Mar-2015, 13:20:55
    Author     : xmw13bzu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import ="Controllers.*"%>
<%@page import ="Model.*"%>

<%
    Member member = (Member) session.getAttribute("user"); 
    final boolean loggedIn = (member != null);
%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>HONG KONG FUSIOOOOON</title>
        <link href="styles/main.css" rel="stylesheet" type="text/css">

    </head>

    <body>
        
        <% 
            if (!loggedIn) {
        %>
        
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
            </header>
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
                                String deleteMessage = (String) request.getAttribute("deleteMessage");
                                if (deleteMessage != null) {
                            %>
                                    <p class="error"><%=deleteMessage%></p>
                            <%
                                }
                            %>

                            <h3>Log In</h3>
                            <form name="login" action="LoginController" method="get">
                                <p>Email:<input type="text" name="email" class="textbox"/></p>
                                <p>Password:<input type="password" name="password" id="textbox"/></p>
                                <p><input type="submit" value="Login"/>
                                <input type="reset" value="Reset"/></p>
                            </form>
                        </article>
                    </article>

                    <article id="centre"></article>
                    
                    <article class="contentArea" id="rightHalf">
                         <article class="basicArea">
                            <h3>Sign Up</h3>
                            <form name="signup" action="accountCreation.jsp" method="get">
                                <input type="submit" value="Sign up"/>
                            </form>
                        </article>
                    </article>
                </div>
           </div>
      

            <br><br>
            <footer>
                <p>&copy; Copyright 2015 
                </p>
            </footer>        

            <% 
                }
                else {
            %>

            <p>Why are you here, you're logged in silly</p>
            <h3>LOG OUT</h3>
            <form name="logout" action="LogoutController" method="get">
                    <p><input type="submit" value="Logout"/>
            </form>

            <%
                }
            %>
        </div>
    </body>
</html>

