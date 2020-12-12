<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="nl">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width= device-width, initial-scale= 1.0">
    <link rel="stylesheet" type="text/css" href="css/reset.css">
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <title>Tracker - Home</title>
</head>
<body>
<jsp:include page="header.jsp">
    <jsp:param name="actual" value="Zoek"/>
</jsp:include>
<main>
    <section class="error">
        <c:if test="${not empty errors}">
            <ul>
                <c:forEach items="${errors}" var="error">
                    <li>${error}</li>
                </c:forEach>
            </ul>
        </c:if>
    </section>
    <section>
        <h2>bezoeker opzoeken</h2>
        <p>Hier kan je een bezoeker opzoeken en er wat info over terugvinden.</p>
    </section>

    <form method="post" action="Servlet?command=zoek" novalidate>
        <label for="bezoekersNaam">reservatie op naam van: </label>
        <input type="text" name="bezoekersNaam" id="bezoekersNaam" value="" required placeholder="Naam">

        <label for="knopform"></label>
        <input type="submit" value="zoek" id="knopform">
    </form>
    <section>
        <c:if test="${not empty logboek}">
            <h3>Voorgaande zoekopdrachten</h3>
            <ul>
                <c:forEach items="${logboek}" var="log">
                    <li>${log}</li>
                </c:forEach>
            </ul>
        </c:if>
    </section>
</main>
<jsp:include page="footer.jsp"/>
</body>
</html>
