<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="nl">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width= device-width, initial-scale= 1.0">
    <link rel="stylesheet"  type="text/css" href="css/style.css">
    <title>Tracker - Home</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<main>
    <c:choose>
        <c:when test="${gevondenBezoekers == null}">
            <h2>Helaas!</h2>
            <p>We hebben geen reservatie op naam van: <b> "${param.bezoekersNaam}"</b> kunnen terugvinden.
        </c:when>
        <c:otherwise>
            <h2>Gevonden!</h2>
            <p>Het de reservatie dat u zocht is van : ${param.bezoekersNaam} </p>
            <p>Hier wat info over deze reservatie</p>
            <table>
                <thead>
                <tr>
                    <th> naam </th>
                    <th> hoeveelheid mensen </th>
                    <th> lengte van verblijf </th>
                    <th> datum van aankomst </th>
                </tr>
                </thead>
                <tbody>
                    <c:forEach var="bezoeker" items="${gevondenBezoekers}">
                        <tr>
                            <td>${bezoeker.naam}</td>
                            <td>${bezoeker.hoeveelMensen}</td>
                            <td>${bezoeker.verblijfLengte}</td>
                            <td>${bezoeker.startDatum}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:otherwise>
    </c:choose>
</main>
<jsp:include page="footer.jsp"/>
</body>
</html>