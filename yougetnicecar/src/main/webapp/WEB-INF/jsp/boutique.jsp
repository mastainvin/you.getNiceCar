<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat">
<html>
<head>
    <title>Boutique</title>
    <base href="${pageContext.request.contextPath}">
</head>
<body style="font-family: 'Montserrat', sans-serif; background: rgb(40,40,40);">

<div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
    <jsp:include page="navbar.jsp"/>
    <main class="mdl-layout__content ">
        <div class="page-content mdl-layout__container">
            <div>
                <div class="mdl-grid mdl-grid--no-spacing">
                    <c:forEach var="produit" items="${produits}">
                        <div class="mdl-card mdl-cell mdl-cell--middle mdl-cell--6-col"
                             style="background-image: url(${produit.imagePath}); background-repeat:no-repeat; background-size: cover; height: 50vh">
                            <div class="mdl-card__title mdl-color-text--white" style="background: rgba(0,0,0,0.5)">
                                <h2 class="mdl-card__title-text">${produit.modele}
                                    (${produit.annee}) ${produit.motorisation}</h2>
                                <h2 style="position: absolute; right: 20px;"
                                    class="mdl-card__title-text">${produit.marque.nom}</h2>
                            </div>
                            <div class="mdl-card__supporting-text mdl-color-text--white" style="background: rgba(0,0,0,0.5); position: absolute; bottom: 0; width: 97%; height: 7.5px;">
                                <div style="position: absolute; bottom: 10px; left: 15px; width: 30%; text-align: left">
                                    <c:if test="${produit.stock >= 1}">
                                            <div>En stock (${produit.stock})</div>
                                    </c:if>
                                    <c:if test="${produit.stock < 1}">
                                        <div>Rupture de stock</div>
                                    </c:if>
                                </div>
                                <div style="position: absolute; bottom: 0; left: 0; right: 0; margin-left: auto; margin-right: auto; text-align: center;">
                                    <c:if test="${produit.stock >= 1}">
                                        <form:form method="get" action="/ajouter/${produit.id}">
                                            <button style="color: white; position: absolute; top: 50%; left: 50%; transform: translate(-50%, -85%); border-radius: 15px;" name="${produit.modele}" value="${produit.id}"
                                                    class="mdl-button mdl-js-button">AJOUTER AU PANIER
                                            </button>
                                        </form:form>
                                    </c:if>
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
