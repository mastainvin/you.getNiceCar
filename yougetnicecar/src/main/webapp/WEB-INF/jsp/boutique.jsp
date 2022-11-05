<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Boutique</title>
</head>
<body>
<jsp:include page="navbar.jsp" />
<h1>Liste des produits</h1>
            <table>
                <thead>
                    <tr> 
                        <th>Modèle</th>
                        <th>Marque</th>
                        <th>Année</th>
                        <th>Stock</th>
                        <th>Motorisation</th>
                        <th>Prix</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var ="produit" items="${produits}">
                    	<tr>
                        <td><c:out value="${produit.modele}"/></td>
                        <td><c:out value="${produit.marque.nom}"/></td>
                        <td><c:out value="${produit.annee}"/></td>
                        <td><c:out value="${produit.stock}"/></td>
                        <td><c:out value="${produit.motorisation}"/></td>
                        <td><c:out value="${produit.prix}"/></td>
                        <td><button type="submit">Ajouter au panier</button></td>
                    	</tr> 
                    </c:forEach>      
                </tbody>
            </table>
       
</body>
</html>
