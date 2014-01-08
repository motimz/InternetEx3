<%-- 
    Document   : SingleResultPage
    Created on : Jan 8, 2014, 11:30:19 AM
    Author     : guest
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Result Found</title>
    </head>
    <body>
        <div class="container">
        <%@include file="myDetails.html" %>

        <h1>Single Result Found: </h1>
        
        <table border='1'>
            <tr class="descTr"><td> Id </td>
                <td> Author </td>
                <td> Title </td>
                <td> Description </td>
                <td> Genre </td>
                <td> Price </td>
                <td> Date </td>
            </tr>
            <tr><td> {$book.Id} </td>
                <td> {$book.Author} </td>
                <td> {$book.Title} </td>
                <td> {$book.Description} </td>
                <td> {$book.Genre} </td>
                <td> {$book.Price} </td>
                <td> {$book.Date} </td>
            </tr>            
            
        </table>
        </div>
        
        
        
    </body>
</html>
