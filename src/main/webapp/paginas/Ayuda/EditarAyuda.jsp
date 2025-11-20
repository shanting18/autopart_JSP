<%-- 
    Document   : EditarAyuda
    Created on : 20/11/2025, 1:19:24 p. m.
    Author     : santiago
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="modelo.Ayuda" %>

<!DOCTYPE html>
<html lang="es" data-bs-theme="dark">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edicion de mensaje</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>

        <div class="py-3 bg-primary text-white text-center">
            <h1>Gestión de mensaje</h1>
        </div>

        <div class="container text-start">
            <div class="row align-items-center mx-auto w-50">
                <div class="col m-5">

                    <c:if test="${not empty Ayuda}">
                        <h2 class="text-center mb-4">Editar mensaje</h2>

                        <form action="${pageContext.request.contextPath}/AyudaServlet" method="post">
                            
                            <input type="hidden" name="accion" value="actualizar">
                            <input type="hidden" name="id" value="<%= request.getParameter("id")%>">
                            

                            <div class="mb-3">
                                <label class="form-label">Editar mensaje</label>
                                <input type="text" name="pregunta" class="form-control" value="${ayuda.pregunta}">
                            </div>

                            <button type="submit" class="btn btn-primary w-50">Actualizar</button>
                        </form>
                    </c:if>

                </div>
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>


