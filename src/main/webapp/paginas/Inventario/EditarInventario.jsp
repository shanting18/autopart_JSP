<%-- 
    Document   : EditarInventario
    Created on : 19/11/2025, 7:18:13 p. m.
    Author     : santiago
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="modelo.Inventario" %>

<!DOCTYPE html>
<html lang="es" data-bs-theme="dark">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edicion de Inventario</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>

        <div class="py-3 bg-primary text-white text-center">
            <h1>Gestión de inventario</h1>
        </div>

        <div class="container text-start">
            <div class="row align-items-center mx-auto w-50">
                <div class="col m-5">

                    <c:if test="${not empty Inventario}">
                        <h2 class="text-center mb-4">Editar inventario</h2>

                        <form action="${pageContext.request.contextPath}/InventarioServlet" method="post">
                            
                            <input type="hidden" name="accion" value="actualizar">
                            <input type="hidden" name="id" value="<%= request.getParameter("id")%>">
                            

                            <div class="mb-3">
                                <label class="form-label">Nombre del repuesto</label>
                                <input type="text" name="repuesto" class="form-control" value="${inventario.nombreRepuesto}">
                            </div>

                            <div class="mb-3">
                                <label class="form-label">Cantidad</label>
                                <input type="number" name="cantidad" class="form-control" value="${inventario.cantidad}">
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

