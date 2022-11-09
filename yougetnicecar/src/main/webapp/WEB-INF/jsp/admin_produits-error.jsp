<jsp:useBean id="erreur" scope="request" type="java.lang.String"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Admin Produits</title>
</head>
<style>
.page-content {
	padding: 20px;
}
</style>

<body>

<div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
    <jsp:include page="navbar.jsp" />
    <main class="mdl-layout__content ">
        <div class="page-content mdl-layout__container">

            <h2>Produits</h2>
            <hr/>
            <div style="color: red;">
                ${erreur}
            </div>
            <div class="mdl-grid center-items">
                <div class="mdl-cell mdl-cell--8-col">
                    <table class="mdl-data-table mdl-js-data-table mdl-shadow--2dp">
                        <thead>
                        <tr>
                            <th>Modèle</th>
                            <th>Prix</th>
                            <th>Année</th>
                            <th>Image</th>
                            <th>Stock</th>
                            <th>Motorisation</th>
                            <th>Marque</th>
                            <th>
                            </th>
                            <th>
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${produits}" var="produit" varStatus="pStatus">
                            <tr>
                                    <%--@elvariable id="produit" type="com.jee.yougetnicecar.models.Produit"--%>
                                <form:form action="/produit/modifier/${produit.id}" method="post" modelAttribute="produit" id="${produit.id}">
                                    <td><form:input class="mdl-textfield__input" type="text" path="modele" value="${produit.modele}"/></td>
                                    <td><form:input class="mdl-textfield__input" type="number" path="prix" value="${produit.prix}"/></td>
                                    <td><form:input class="mdl-textfield__input" type="number" path="annee" value="${produit.annee}"/></td>
                                    <td><form:input class="mdl-textfield__input" type="text" path="imagePath" value="${produit.imagePath}"/></td>
                                    <td><form:input class="mdl-textfield__input" type="number" path="stock" value="${produit.stock}"/></td>
                                    <td>
                                        <form:select class="mdl-textfield__input" path="motorisation" value="${produit.motorisation}">
                                            <c:forEach items="${motorisations}" var="motorisation">
                                                <option value="${motorisation}"   <c:if test="${motorisation.equals(produit.motorisation)}"> selected </c:if>>${motorisation}</option>
                                            </c:forEach>
                                        </form:select>
                                    </td>
                                    <td>
                                        <form:select class="mdl-textfield__input" path="marque" value="${produit.marque}" >
                                            <c:forEach items="${marques}" var="marque">
                                                <option value="${marque.id}"  <c:if test="${marque.id.equals(produit.marque.id)}"> selected </c:if>>${marque.nom}</option>
                                            </c:forEach>
                                        </form:select>
                                    </td>
                                    <td><input type="submit" value="Modifier" class="mdl-button mdl-js-button mdl-button--raised mdl-button--colored"/></td>

                                </form:form>
                                <td>
                                    <form:form action="/produit/supprimer/${produit.id}" method="post" modelAttribute="produit" id="${produit.id}">
                                        <input type="submit" value="Supprimer" class="mdl-button mdl-js-button mdl-button--raised mdl-button--colored"/>
                                    </form:form>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="mdl-cell mdl-cell--4-col ">
                    <div class="demo-card-wide mdl-card mdl-shadow--2dp mdl-grid center-items">
                    <%--@elvariable id="produitDto" type="com.jee.yougetnicecar.dtos.ProduitDto"--%>
                    <form:form action="/produit/ajouter" method="post" modelAttribute="produitDto">
                            <div class="mdl-textfield mdl-js-textfield">
                                <form:label path="modele" class="mdl-textfield__label" >Modèle</form:label> <form:input class="mdl-textfield__input" type="text" path="modele"/>
                            </div>
                            <div class="mdl-textfield mdl-js-textfield">
                                <form:label path="prix" class="mdl-textfield__label" >Prix</form:label> <form:input class="mdl-textfield__input" type="number" path="prix"/>
                            </div>
                        <div class="mdl-textfield mdl-js-textfield">
                            <form:label path="annee" class="mdl-textfield__label">Année</form:label> <form:input  class="mdl-textfield__input" type="number" path="annee"/>
                        </div>
                        <div class="mdl-textfield mdl-js-textfield">
                            <form:label path="imagePath" class="mdl-textfield__label">Image</form:label> <form:input  class="mdl-textfield__input" type="text" path="imagePath"/>

                        </div>
                        <div class="mdl-textfield mdl-js-textfield">
                            <form:label path="stock" class="mdl-textfield__label">Stock</form:label> <form:input  class="mdl-textfield__input" type="number" path="stock"/>

                        </div>
                        <div class="mdl-textfield mdl-js-textfield">
                            <form:select path="motorisation"  class="mdl-textfield__input">
                                <c:forEach items="${motorisations}" var="motorisation">
                                    <option value="${motorisation}">${motorisation}</option>
                                </c:forEach>
                            </form:select>
                        </div>
                        <div class="mdl-textfield mdl-js-textfield">
                            <form:select path="marque"  class="mdl-textfield__input">
                                <c:forEach items="${marques}" var="marque">
                                    <option value="${marque.id}">${marque.nom}</option>
                                </c:forEach>
                            </form:select>
                        </div>
                        <div class="mdl-textfield mdl-js-textfield">
                        </div>
                        <input type="submit" value="Ajouter" class="mdl-button mdl-js-button mdl-button--raised mdl-button--colored"/>
                    </form:form>
                    </div>
                </div>
            </div>

            <h2>Marques</h2>
            <hr/>
            <div class="mdl-grid center-items">
                <div class="mdl-cell mdl-cell--8-col">
                    <table class="mdl-data-table mdl-js-data-table mdl-shadow--2dp">
                        <thead>
                        <tr>
                            <th>Nom</th>
                            <th>
                            </th>
                            <th>
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${marques}" var="marque" varStatus="mStatus">
                            <tr>
                                    <%--@elvariable id="marque" type="com.jee.yougetnicecar.models.Marque"--%>
                                <form:form action="/marque/modifier/${marque.id}" method="post" modelAttribute="marque" id="${marque.id}">
                                    <td><form:input class="mdl-textfield__input" type="text" path="nom" value="${marque.nom}"/></td>
                                    <td><input type="submit" value="Modifier" class="mdl-button mdl-js-button mdl-button--raised mdl-button--colored"/></td>

                                </form:form>
                                <td>
                                    <form:form action="/produit/supprimer/${marque.id}" method="post" modelAttribute="produit" id="${marque.id}">
                                        <input type="submit" value="Supprimer" class="mdl-button mdl-js-button mdl-button--raised mdl-button--colored"/>
                                    </form:form>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="mdl-cell mdl-cell--4-col ">
                    <div class="demo-card-wide mdl-card mdl-shadow--2dp mdl-grid center-items">
                        <%--@elvariable id="marqueDto" type="com.jee.yougetnicecar.dtos.MarqueDto"--%>
                        <form:form action="/marque/ajouter" method="post" modelAttribute="marqueDto">
                            <div class="mdl-textfield mdl-js-textfield">
                                <form:label path="nom" class="mdl-textfield__label" >Nom</form:label> <form:input class="mdl-textfield__input" type="text" path="nom"/>
                            </div>
                            <input type="submit" value="Ajouter" class="mdl-button mdl-js-button mdl-button--raised mdl-button--colored"/>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </main>
</div>

</body>
</html>
