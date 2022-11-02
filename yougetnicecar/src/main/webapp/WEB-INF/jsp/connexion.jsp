<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: cytech
  Date: 02/11/2022
  Time: 10:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Connexion</title>
</head>
<body>
<jsp:include page="navbar.jsp" />

<%--@elvariable id="utilisateurConnexionDto" type="com.jee.yougetnicecar.dtos.UtilisateurConnexionDto"--%>
<form:form action="/connexion" method="post" modelAttribute="utilisateurConnexionDto">
    <form:label path="username">Nom d'utilisateur: </form:label> <form:input type="text" path="username"/>
    <form:label path="password">Mot de passe: </form:label> <form:input type="text" path="password"/>

    <input type="submit" value="submit"/>
</form:form>
</body>
</html>
