<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:useBean id="utilisateur" scope="request" type="com.jee.yougetnicecar.models.Utilisateur"/>

<html>
<head>
    <title>Admin Utilisateurs</title>
</head>
<body>
<div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
    <jsp:include page="navbar.jsp"/>
    <main class="mdl-layout__content ">
        <div class="page-content mdl-layout__container">
            <h2>Utilisateurs</h2>
            <hr/>
            <div class="mdl-grid center-items">
                <div class="mdl-cell mdl-cell--8-col">
                    <table class="mdl-data-table mdl-js-data-table mdl-shadow--2dp">
                        <thead>
                        <tr>
                            <th>Nom</th>
                            <th>Prénom</th>
                            <th>Login</th>
                            <th>Role</th>
                            <th>
                            </th>
                            <th>
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${utilisateurs}" var="bdd_utilisateur" varStatus="pStatus">

                            <c:if test="${!bdd_utilisateur.id.equals(utilisateur.id)}">
                                <tr>
                                        <%--@elvariable id="bdd_utilisateur" type="com.jee.yougetnicecar.models.Utilisateur"--%>
                                    <form:form action="/admin/users/update/${bdd_utilisateur.id}" method="post"
                                               modelAttribute="bdd_utilisateur" id="${bdd_utilisateur.id}">
                                        <td><form:input class="mdl-textfield__input" type="text" path="nom"
                                                        value="${bdd_utilisateur.nom}"/></td>
                                        <td><form:input class="mdl-textfield__input" type="text" path="prenom"
                                                        value="${bdd_utilisateur.prenom}"/></td>
                                        <td><form:input class="mdl-textfield__input" type="text" path="login"
                                                        value="${bdd_utilisateur.login}"/></td>
                                        <td>
                                            <form:select class="mdl-textfield__input" path="role" value="${bdd_utilisateur.role}">
                                                <c:forEach items="${roles}" var="role">
                                                    <option value="${role}"   <c:if
                                                            test="${role.equals(bdd_utilisateur.role)}"> selected </c:if>
                                                            <c:if test="${bdd_utilisateur.id.equals(utilisateur.id)}"> disabled </c:if>
                                                    >${role}
                                                    </option>
                                                </c:forEach>
                                            </form:select>
                                        </td>

                                        <td><input type="submit" value="Modifier"
                                                   class="mdl-button mdl-js-button mdl-button--raised mdl-button--colored"/>
                                        </td>

                                    </form:form>
                                        <%--                                <td>
                                                                            <form:form action="/produit/supprimer/${produit.id}" method="post"
                                                                                       modelAttribute="produit" id="${produit.id}">
                                                                                <input type="submit" value="Supprimer"
                                                                                       class="mdl-button mdl-js-button mdl-button--raised mdl-button--colored"/>
                                                                            </form:form>
                                                                        </td>--%>
                                </tr>
                            </c:if>

                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </main>
</div>
</body>
</html>
