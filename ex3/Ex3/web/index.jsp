<%-- 
    Document   : index
    Created on : Jan 8, 2014, 11:22:58 AM
    Author     : guest
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>My Book Search</title>
    </head>
    <body>
    <div class="container">
        
        <%@include file="myDetails.html" %>
        
        <h2>Search Books</h2>
        <form action="SearchServlet" method="GET">
                <p>
                    <label for="searchKeywords">Keywords: </label>
                    <input type="text" id="searchKeywords" name="searchKeywords" />
                </p>
                <p>
                    <label for="searchPrice">Max Price:</label>
                    <input type="text" id="searchPrice" name="searchPrice" />
                </p>
                <p>
                    <input type="submit" value="Search" class="button" />
                </p>
        </form>
          
    </div>        
    </body>
</html>
