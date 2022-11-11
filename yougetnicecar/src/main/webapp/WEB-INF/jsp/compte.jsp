<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Compte</title>
</head>
<body>
<jsp:include page="navbar.jsp"/>


<h2>Anciennes commandes</h2>

<jsp:useBean id="commandes" scope="request" type="java.util.List<com.jee.yougetnicecar.dtos.CommandeDto>"/>
<c:forEach items="${commandes}" var="commande">
    <p>Commande du ${commande.date}</p>
    <c:forEach items="${commande.produits}" var="ligne">
        <p>${ligne.key.modele} : ${ligne.key.prix} X ${ligne.value} = ${ligne.key.prix * ligne.value}</p>
    </c:forEach>

    <p>Total : ${commande.total}</p>
</c:forEach>
</body>
</html>
