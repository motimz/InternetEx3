<%-- 
    Document   : NoResultPage
    Created on : Jan 8, 2014, 11:27:41 AM
    Author     : guest
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>No Results</title>
    </head>
    <body>
        <div class="container">
            <%@include file="myDetails.html" %>

        <p class="errormsg">Sorry, no such book matches your search criteria</p>
        
        <%@include file="backBtn.html" %>
        </div>
    </body>
</html>
