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
<div class="mdl-layout mdl-js-layout mdl-color--grey-100">
	<div class="mdl-card mdl-shadow--6dp mdl-cell mdl-cell--middle mdl-cell--4-col">
		<div class="mdl-card__title mdl-color--primary mdl-color-text--white">
			<h2 class="mdl-card__title-text">Inscription</h2>
		</div>
	  	<div class="mdl-card__supporting-text mdl-cell mdl-cell--middle mdl-cell--8-col">
				<form:form action="/inscription" method="post" modelAttribute="utilisateurInscriptionDto">
					<jsp:useBean id="erreur" scope="request" type="java.lang.String"/>
			        <c:if test="${erreur != null}" >
			            <span style="color: red;">${erreur}</span>
			        </c:if>
				    <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
						<form:label path="username" class="mdl-textfield__label">Nom d'utilisateur </form:label>
						<form:input type="text" path="username" class="mdl-textfield__input"/>
					</div>
					<div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
						<form:label path="password" class="mdl-textfield__label">Mot de passe </form:label>
						<form:input type="password" path="password" class="mdl-textfield__input"/>
					</div>
					<div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
						<form:label path="repeatPassword" class="mdl-textfield__label">Répetez le mot de passe </form:label>
						<form:input type="password" path="repeatPassword" class="mdl-textfield__input"/>
					</div>
					<div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
						<form:label path="nom" class="mdl-textfield__label">Nom </form:label>
						<form:input type="text" path="nom" class="mdl-textfield__input"/>
					</div>
					<div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
						<form:label path="prenom" class="mdl-textfield__label">Prénom</form:label>
						<form:input type="text" path="prenom" class="mdl-textfield__input"/>
					</div>
						<button type="submit" class="mdl-button mdl-button--colored mdl-js-button">SUBMIT</button>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</div>
</body>
</html>
