<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<jsp:include page="layout/header.jsp">
    <jsp:param name="titulo" value="Crear Usuario"/>
</jsp:include>

<div class="row justify-content-center">
    <div class="col-md-8">

        <div class="card shadow-sm">
            <div class="card-body">

                <h4 class="mb-4">
                    <i class="bi bi-person-plus"></i> Registro de Usuario
                </h4>

                <!-- ✅ ALERTA -->
                <c:if test="${not empty success}">
                    <div class="alert alert-success alert-dismissible fade show">
                        <i class="bi bi-check-circle"></i> ${success}
                        <button type="button" class="btn-close"
                                data-bs-dismiss="alert"></button>
                    </div>
                </c:if>

                <form action="${pageContext.request.contextPath}/guardarUsuario"
                      method="post"
                      class="needs-validation"
                      novalidate>

                    <!-- NUMERO DOCUMENTO -->
                    <div class="mb-3">
                        <label class="form-label">Número de Documento</label>
                        <input type="text"
                               name="numeroDocumento"
                               class="form-control"
                               required>
                    </div>

                    <!-- NOMBRE -->
                    <div class="mb-3">
                        <label class="form-label">Nombre</label>
                        <input type="text"
                               name="nombre"
                               class="form-control"
                               required>
                    </div>

                    <!-- APELLIDO -->
                    <div class="mb-3">
                        <label class="form-label">Apellido</label>
                        <input type="text"
                               name="apellido"
                               class="form-control"
                               required>
                    </div>

                    <!-- NOTA INFORMATIVA -->
                    <div class="alert alert-info">
                        El usuario se generará automáticamente como:
                        <strong>nombre.apellido</strong><br>
                        La contraseña inicial será el número de documento.
                    </div>


                    <!-- EMAIL -->
                    <div class="mb-3">
                        <label class="form-label">Email</label>
                        <input type="email" name="email"
                               class="form-control" required>
                    </div>

                    <!-- TELÉFONO -->
                    <div class="mb-3">
                        <label class="form-label">Teléfono</label>
                        <input type="text" name="telefono"
                               class="form-control">
                    </div>

                    <!-- GENERO -->
                    <div class="mb-3">
                        <label class="form-label">Género</label><br>
                        <input type="radio" name="genero"
                               value="M" required> Masculino
                        <input type="radio" name="genero"
                               value="F" class="ms-3" required> Femenino
                    </div>

                    <!-- ROL -->
                    <div class="mb-3">
                        <label class="form-label">Rol</label>
                        <select name="rol" class="form-select" required>
                            <option value="USER">Usuario</option>
                            <option value="ADMIN">Administrador</option>
                        </select>
                    </div>

                    <!-- ACTIVO -->
                    <div class="form-check mb-3">
                        <input class="form-check-input"
                               type="checkbox"
                               name="activo"
                               checked>
                        <label class="form-check-label">
                            Usuario Activo
                        </label>
                    </div>

                    <!-- DEPARTAMENTO -->
                    <div class="mb-3">
                        <label class="form-label">Departamento</label>
                        <select name="departamentoId"
                                id="departamento"
                                class="form-select"
                                onchange="cargarCiudades()"
                                required>

                            <c:forEach var="dep"
                                       items="${listaDepartamentos}">
                                <option value="${dep.id}">
                                    ${dep.nombre}
                                </option>
                            </c:forEach>

                        </select>
                    </div>

                    <!-- CIUDAD -->
                    <div class="mb-3">
                        <label class="form-label">Ciudad</label>
                        <select name="ciudadId"
                                id="ciudad"
                                class="form-select"
                                required>
                            <option value="">Seleccione una ciudad</option>
                        </select>
                    </div>

                    <!-- BOTÓN -->
                    <div class="d-grid">
                        <button class="btn btn-primary">
                            <i class="bi bi-save"></i> Guardar Usuario
                        </button>
                    </div>

                </form>

                <div class="text-center mt-3">
                    <a href="${pageContext.request.contextPath}/home">
                        Volver al Home
                    </a>
                </div>

            </div>
        </div>

    </div>
</div>

<!-- ✅ SCRIPT CIUDADES (NO SE BORRA FUNCIONALIDAD) -->
<script>
function cargarCiudades() {

    var departamentoId = document.getElementById("departamento").value;

    fetch("${pageContext.request.contextPath}/ciudadesPorDepartamento?departamentoId=" + departamentoId)
        .then(response => response.json())
        .then(data => {

            var ciudadSelect = document.getElementById("ciudad");
            ciudadSelect.innerHTML = "";

            if (data.length === 0) {
                var option = document.createElement("option");
                option.text = "No hay ciudades";
                ciudadSelect.add(option);
                return;
            }

            data.forEach(function(ciudad) {

                var option = document.createElement("option");
                option.value = ciudad.id;
                option.text = ciudad.nombre;

                ciudadSelect.appendChild(option);
            });
        });
}

window.onload = function() {
    cargarCiudades();
};
</script>

<!-- ✅ VALIDACIÓN VISUAL -->
<script>
(() => {
  'use strict'
  const forms = document.querySelectorAll('.needs-validation')

  Array.from(forms).forEach(form => {
    form.addEventListener('submit', event => {
      if (!form.checkValidity()) {
        event.preventDefault()
        event.stopPropagation()
      }
      form.classList.add('was-validated')
    }, false)
  })
})();
</script>

<jsp:include page="layout/footer.jsp"/>
