<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="nl">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width= device-width, initial-scale= 1.0">
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <title>verwijder bezoeker</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<main>
    <h2>Bevestiging</h2>
    <p>Ben je zeker dat de reservatie van <b> ${param.naam}</b> verwijderd mag worden?</p>
    <form action="Servlet?command=verwijderDefinitief&naam=${param.naam}" method="POST">
        <input type="submit" value="Ja"/>
    </form>
    <p><a href="Servlet?command=overzicht">Cancel</a> indien je de reservatie niet wil verwijderen</p>
</main>
<jsp:include page="footer.jsp"/>
</body>
</html>