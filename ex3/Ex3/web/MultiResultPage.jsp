<%-- 
    Document   : MultiResultPage
    Created on : Jan 8, 2014, 11:50:23 AM
    Author     : guest
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Results Found</title>
    </head>
    <body>
        <div class="container">
            
        <%@include file="myDetails.html" %>

            
            <h1>Multiple Results Found: </h1>
        <table border='1'>
            <tr class="descTr"><td> More Information </td>
                <td> Author </td>
                <td> Title </td>
                <td> Price </td>
            </tr>
            <c:forEach items="${books.arrayVals}" var="book">
                <tr><td><a href="SearchServlet?id={$book.Id}">Link</a></td>
                <td> ${book.author} </td>
                <td> ${book.title} </td>
                <td> ${book.price} </td>
            </tr>            
            </c:forEach>

        </table>
                
        <%@include file="backBtn.html" %>
        </div>
        
        
        
    </body>
</html>


