<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="nl">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width= device-width, initial-scale= 1.0">
    <link rel="stylesheet"  type="text/css" href="css/style.css">
    <title>Bekijk alle bezoekers</title>
</head>
<body>
<jsp:include page="header.jsp">
    <jsp:param name="actual" value="Overzicht"/>
</jsp:include>
<main>
    <c:choose>
        <c:when test="${not empty bezoekers}">
            <section>
                <h2>Overzicht van alle bezoekers</h2>
                <p>kies een sorteer optie:</p>
                <p><a href="Servlet?command=reservatieOverzicht">datum van reservatie</a></p>
                <p><a href="Servlet?command=chronologischOverzicht">chronologisch</a></p>
                <table>
                    <thead>
                    <tr>
                        <th> naam </th>
                        <th> hoeveelheid mensen </th>
                        <th> lengte van verblijf </th>
                        <th> datum van aankomst </th>
                        <th> verwijderen </th>
                        <th> updaten </th>

                    </thead>
                    <tbody>
                    <c:forEach var="bezoeker" items="${bezoekers}">
                        <tr>
                            <td>${bezoeker.naam}</td>
                            <td>${bezoeker.hoeveelMensen}</td>
                            <td>${bezoeker.verblijfLengte}</td>
                            <td>${bezoeker.startDatum}</td>
                            <td><a href="Servlet?command=ProbeerTeVerwijderen&naam=${bezoeker.naam}">verwijder</a> </td>
                            <td><a href="Servlet?command=ProbeerTeUpdaten&naam=${bezoeker.naam}">update</a> </td>

                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </section>
        </c:when>
        <c:otherwise>
            <p>Er zijn nog geen bezoekers geregistreerd. </p>
        </c:otherwise>
    </c:choose>
</main>
<jsp:include page="footer.jsp"/>
</body>
</html>
