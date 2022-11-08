<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Boutique</title>
	<base href="${pageContext.request.contextPath}">
</head>
<body>

<div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
    <jsp:include page="navbar.jsp" />
    <main class="mdl-layout__content ">
        <div class="page-content mdl-layout__container">
        <div>
        	<div class="mdl-grid">
   			<c:forEach var ="produit" items="${produits}">
	  			<div class="mdl-card mdl-shadow--6dp mdl-cell mdl-cell--middle mdl-cell--6-col" style="background-image: url(${produit.imagePath}); background-repeat:no-repeat; background-size: cover; height: 50vh">
					<div class="mdl-card__title mdl-color-text--white" style="background: rgba(0,0,0,0.5)">
						<h2 class="mdl-card__title-text">${produit.modele} (${produit.annee}) ${produit.motorisation}</h2>
						<h2 style="position: absolute; right: 20px;" class="mdl-card__title-text">${produit.marque.nom}</h2>
					</div>
<%--
					<img src="<c:url value="${produit.imagePath}" />"  alt="image du produit">
--%>
					<div>
						<div style="position: absolute; bottom: 10px; left: 15px; width: 30%; text-align: left">
							<span>Stock : ${produit.stock}</span>
						</div>
						<div style="position: absolute; bottom: 0; left: 0; right: 0; margin-left: auto; margin-right: auto; width: 30%; text-align: center;">
							<button type="submit" class="mdl-button mdl-button--colored mdl-js-button">AJOUTER AU PANIER</button>
						</div>
						<div style="position: absolute; bottom: 10px; right: 15px; width: 30%; text-align: right;">
							<span>Prix : ${produit.prix}€</span>
						</div>
					</div>
				</div>
			</c:forEach>
			</div>
   		</div>
   		</div>
   	</main>
</div>
</body>
</html>
