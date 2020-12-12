<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="nl">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width= device-width, initial-scale= 1.0">
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <title>update bezoeker</title>
</head>
<body>
<jsp:include page="header.jsp"/>
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
    <h2>Update ${param.naam}</h2>
    <p>Wat wil je aan de reservatie van <b> ${param.naam}</b> updaten?</p>
    <form action="Servlet?command=updateDefinitief&naam=${param.naam}" method="POST" novalidate>
        <label for="hoeveelMensen">Met hoeveel gaan jullie: </label>
        <input type="number" name="hoeveelMensen" id="hoeveelMensen" value="${hoeveelMensenPreviousValue}" required placeholder="hoeveel mensen">
        <label for="verblijfLengte">nieuwe lengte van verblijf: </label>
        <input type="number" name="verblijfLengte" id="verblijfLengte" value="${verblijfLengtePreviousValue}" required placeholder="lengte van verblijf">
        <label for="startDatum">nieuwe datum: </label>
        <input type="date" name="startDatum" id="startDatum" required="" value="${datumPreviousValue}" placeholder="">

        <input type="submit" value="Update"  id="knopform"/>
    </form>
    <a href="Servlet?command=overzicht">Ik wil toch niet updaten</a>
</main>
<jsp:include page="footer.jsp"/>
</body>
</html>