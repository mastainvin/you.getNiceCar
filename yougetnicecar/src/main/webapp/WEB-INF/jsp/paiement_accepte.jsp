<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat">

<html>
<head>
    <title>Paiement accepté</title>
</head>
<body style="font-family: 'Montserrat', sans-serif;">

<div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
    <jsp:include page="navbar.jsp"/>
    <main class="mdl-layout__content">
        <div style="margin: 50px 35% auto 35%; border-radius: 15px; background: rgb(80,80,80); width: 30%; height: 25%;" class="page-content mdl-layout__container">
			<div style="text-align: center; color: white; font-size: xx-large; margin-top: 50px;">Paiement Accepté</div>
			<hr style="color: white; width: 80%; margin-left: 10%; margin-right: 10%;"/>
			<div style="color: white; text-align: center;">Votre paiement a bien été accepté.</div>
			<br/><br/>
			<div style="text-align: center;">
				<a href="${pageContext.request.contextPath}/">Retourner à l'accueil</a>
			</div>
        </div>
	</main>
</div>

</body>
</html>
