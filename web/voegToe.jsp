<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="nl">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width= device-width, initial-scale= 1.0">
    <link rel="stylesheet"  type="text/css" href="css/style.css">
    <title>huren - home</title>
</head>
<body>
<jsp:include page="header.jsp">
    <jsp:param name="actual" value="VoegToe"/>
</jsp:include>
<main>
    <h2>vul dit in om een reservatie te plaatsen</h2>
    <section class="error">
        <c:if test="${not empty errors}">
            <ul>
                <c:forEach items="${errors}" var="error">
                    <li>${error}</li>
                </c:forEach>
            </ul>
        </c:if>
    </section>
    <form method="POST" action="Servlet?command=voegToe" novalidate>
        <label for="naam">Je eigen naam: </label>
        <input type="text" name="naam" id="naam" value="${naamPreviousValue}" required placeholder="Naam">

        <label for="hoeveelMensen">Met hoeveel gaan jullie: </label>
        <input type="number" name="hoeveelMensen" id="hoeveelMensen" value="${hoeveelMensenPreviousValue}" required placeholder="hoeveel mensen">

        <label for="verblijfLengte">lengte van verblijf: </label>
        <input type="number" name="verblijfLengte" id="verblijfLengte" value="${verblijfLengtePreviousValue}" required placeholder="lengte van verblijf">

        <label for="startDatum">Startdatum: </label>
        <input type="date" name="startDatum" id="startDatum" required=""  value="${datumPreviousValue}" placeholder="datum van aankomst">

        <label for="knopform"></label>
        <input type="submit" value="Versturen" id="knopform">
    </form>
</main>
<jsp:include page="footer.jsp"/>
</body>
</html>
