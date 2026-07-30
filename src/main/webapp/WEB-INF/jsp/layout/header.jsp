<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${titulo}</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css" rel="stylesheet">

</head>
<body class="bg-light">

<nav class="navbar navbar-expand-lg navbar-dark bg-primary shadow-sm">
    <div class="container-fluid">

        <a class="navbar-brand"
           href="${pageContext.request.contextPath}/home">
            <i class="bi bi-bank"></i> Sistema Corporativo
        </a>

        <div class="d-flex">

            <c:if test="${not empty usuario}">
                <span class="navbar-text text-white me-3">
                    <i class="bi bi-person-circle"></i>
                    ${usuario.username}
                </span>

                <a class="btn btn-outline-light btn-sm"
                   href="${pageContext.request.contextPath}/logout">
                    <i class="bi bi-box-arrow-right"></i> Salir
                </a>
            </c:if>

        </div>
    </div>
</nav>

<div class="container mt-4">
