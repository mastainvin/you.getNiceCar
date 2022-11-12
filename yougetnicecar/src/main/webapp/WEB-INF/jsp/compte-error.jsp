<jsp:useBean id="erreur" scope="request" type="java.lang.String"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:useBean id="utilisateur" scope="request" type="com.jee.yougetnicecar.models.Utilisateur"/>
<jsp:useBean id="commandes" scope="request" type="java.util.List<com.jee.yougetnicecar.dtos.CommandeDto>"/>

<html>
<head>
    <title>Compte</title>
</head>
<body>
<jsp:include page="navbar.jsp"/>


<h2>Compte</h2>

<div style="color: red;">
    ${erreur}
</div>
<form action="/compte/modifier/${utilisateur.id}" method="post">
    <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
        <label for="login" class="mdl-textfield__label">Nom d'utilisateur </label>
        <input id="login" type="text" name="login" class="mdl-textfield__input" value="${utilisateur.login}"/>
    </div>
    <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
        <label for="nom" class="mdl-textfield__label">Nom </label>
        <input id="nom" type="text" name="nom" class="mdl-textfield__input" value="${utilisateur.nom}" />
    </div>
    <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
        <label for="prenom" class="mdl-textfield__label">Prénom </label>
        <input id="prenom" type="text" name="prenom" class="mdl-textfield__input" value="${utilisateur.prenom}" />
    </div>
    <button style="background-color: rgb(50,50,50); color: white; border-radius: 15px;" type="submit"
            class="mdl-button mdl-button--colored mdl-js-button">MODIFIER
    </button>
</form>

<h2>Anciennes commandes</h2>

<c:forEach items="${commandes}" var="commande">
    <p>Commande du ${commande.date}</p>
    <c:forEach items="${commande.produits}" var="ligne">
        <p>${ligne.key.modele} : ${ligne.key.prix} X ${ligne.value} = ${ligne.key.prix * ligne.value}</p>
    </c:forEach>

    <p>Total : ${commande.total}</p>
</c:forEach>
</body>
</html>
