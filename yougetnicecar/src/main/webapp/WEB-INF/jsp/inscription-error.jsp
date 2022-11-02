<%--
  Created by IntelliJ IDEA.
  User: cytech
  Date: 02/11/2022
  Time: 10:42
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Inscription</title>
</head>
<body>
<jsp:include page="navbar.jsp" />

    <%--@elvariable id="utilisateurInscriptionDto" type="com.jee.yougetnicecar.dtos.UtilisateurInscriptionDto"--%>
    <form:form action="/inscription" method="post" modelAttribute="utilisateurInscriptionDto">
        <form:label path="username">Nom d'utilisateur: </form:label> <form:input type="text" path="username"/>
        <form:label path="password">Mot de passe: </form:label> <form:input type="text" path="password"/>
        <form:label path="repeatPassword">Répétez le mot de passe: </form:label> <form:input path="repeatPassword"/>
        <form:label path="nom">Nom: </form:label> <form:input type="text" path="nom"/>
        <form:label path="prenom">Prenom: </form:label> <form:input type="text" path="prenom"/>

        <jsp:useBean id="erreur" scope="request" type="java.lang.String"/>
        <c:if test="${erreur != null}" >
            <span>${erreur}</span>
        </c:if>
        <input type="submit" value="submit"/>
    </form:form>
</body>
</html>
