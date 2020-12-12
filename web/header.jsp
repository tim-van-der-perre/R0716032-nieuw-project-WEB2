<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>
    <h1>Appartementje</h1>
    <nav>
        <ul>
            <li ${param.actual eq 'Home'?'id="actual"':""}>
                <a href="Servlet?command=home">Home</a></li>
            <li ${param.actual eq "VoegToe"?"id='actual'":""}>
                <a href="voegToe.jsp">Voeg Toe</a></li>
            <li ${param.actual eq "Overzicht"?"id='actual'":""}>
                <a href="Servlet?command=overzicht">Overzicht</a></li>
            <li ${param.actual eq "Zoek"?"id='actual'":""}>
                <a href="zoek.jsp">Zoek</a></li>
        </ul>
    </nav>
</header>