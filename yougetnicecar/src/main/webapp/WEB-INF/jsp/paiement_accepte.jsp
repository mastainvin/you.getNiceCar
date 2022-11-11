<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Paiement accepté</title>
</head>
<body>
<jsp:include page="navbar.jsp"/>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <h1>Paiement accepté</h1>
            <p>Votre paiement a bien été accepté.</p>
            <a href="${pageContext.request.contextPath}/">Retourner à l'accueil</a>
        </div>
    </div>
</body>
</html>
