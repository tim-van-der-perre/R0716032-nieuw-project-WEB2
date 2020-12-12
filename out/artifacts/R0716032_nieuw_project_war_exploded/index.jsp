<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="nl">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width= device-width, initial-scale= 1.0">
    <link rel="stylesheet"  type="text/css" href="css/style.css">
    <title>Home</title>
  </head>
  <body>
  <jsp:include page="header.jsp">
    <jsp:param name="actual" value="Home"/>
  </jsp:include>
  <main>
    <section>
      <h2>Appartementje reserveren</h2>
      <p>
        Op deze website kan je een appartementje reserveren.
        Kijk eens wie er al heeft gereserveerd en of je eventueel zelf wilt reserveren.
      </p>
      <p>De eerstvolgende dat gaat is: ${volgendeBezoeker.naam}</p>
    </section>
  </main>
  <jsp:include page="footer.jsp"/>
  </body>
</html>
