<%-- 
    Document   : ResultsPage
    Created on : Sep 19, 2016, 7:08:13 PM
    Author     : Spike
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Results Page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    </head>
    <body>
        <h1>List of Authors:</h1>

        <div>
            <table width="800" border="2" >
                <tr>
                    <th>ID</th>
                    <th>Author Name</th>
                    <th>Date Added</th>
                </tr>
                <c:forEach var="a" items="${authorList}">
                    <tr>
                        <td>${a.authorId}</td>
                        <td>${a.authorName}</td>
                        <td>${a.dateAdded}></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    </body>
</html>
