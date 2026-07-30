<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Home</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<!-- NAVBAR -->
<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
    <div class="container-fluid">
        <span class="navbar-brand">Sistema Usuarios</span>

        <div class="d-flex">
            <span class="navbar-text text-white me-3">
                Bienvenido ${usuario.username}
            </span>

            <a class="btn btn-outline-light btn-sm"
               href="${pageContext.request.contextPath}/logout">
                Cerrar sesión
            </a>
        </div>
    </div>
</nav>

<!-- CONTENIDO -->
<div class="container mt-5">

    <div class="card shadow">
        <div class="card-body text-center">

            <h4 class="mb-4">Panel Principal</h4>

            <p>Login exitoso.</p>

            <a class="btn btn-success"
               href="${pageContext.request.contextPath}/crearUsuario">
                Crear Nuevo Usuario
            </a>

        </div>
    </div>

</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
