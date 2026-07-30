<%@ page contentType="text/html; charset=UTF-8" %>
<jsp:include page="layout/header.jsp">
    <jsp:param name="titulo" value="Error"/>
</jsp:include>

<div class="row justify-content-center">
    <div class="col-md-6">

        <div class="card shadow-sm border-danger">
            <div class="card-body text-center">

                <h4 class="text-danger">
                    <i class="bi bi-exclamation-triangle"></i>
                    Error del Sistema
                </h4>

                <p class="mt-3">
                    ${error}
                </p>

                <a href="${pageContext.request.contextPath}/home"
                   class="btn btn-primary">
                    Volver al inicio
                </a>

            </div>
        </div>

    </div>
</div>

<jsp:include page="layout/footer.jsp"/>
