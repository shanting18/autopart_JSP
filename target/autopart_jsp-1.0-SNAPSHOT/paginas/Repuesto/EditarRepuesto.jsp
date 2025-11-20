<%-- 
    Document   : EditarRepuesto
    Created on : 18/11/2025, 1:58:01 p. m.
    Author     : santiago
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="modelo.Repuesto" %>

<!DOCTYPE html>
<html lang="es" data-bs-theme="dark">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edicion de Repuesto</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>

        <div class="py-3 bg-primary text-white text-center">
            <h1>Gestión de Repuestos</h1>
        </div>

        <div class="container text-start">
            <div class="row align-items-center mx-auto w-50">
                <div class="col m-5">

                    <c:if test="${not empty Repuesto}">
                        <h2 class="text-center mb-4">Editar Repuesto</h2>

                        <form action="${pageContext.request.contextPath}/RepuestoServlet" method="post">
                            <input type="hidden" name="accion" value="actualizar">
                            <input type="hidden" name="id" value="<%= request.getParameter("id")%>">
                            

                            <div class="mb-3">
                                <label class="form-label">Nombre del repuesto</label>
                                <input type="text" name="nombre" class="form-control" value="${repuesto.nombre}">
                            </div>

                            <div class="mb-3">
                                <label class="form-label">Marca</label>
                                <input type="text" name="marca" class="form-control" value="${repuesto.marca}">
                            </div>

                            <div class="mb-3">
                                <label class="form-label">Modelo</label>
                                <input type="text" name="modelo" class="form-control" value="${repuesto.modelo}">
                            </div>
                            
                            <div class="mb-3">
                                <label class="form-label">Precio</label>
                                <input type="number" name="precio" class="form-control" value="${repuesto.precio}">
                            </div>
                            
                            <div class="mb-3">
                                <label class="form-label">Descripcion</label>
                                <input type="text" name="descripcion" class="form-control" value="${repuesto.descripcion}">
                            </div>
                            
                            <div class="mb-3">
                                <label class="form-label">Categoria</label>
                                <input type="text" name="categoria" class="form-control" value="${repuesto.categoria}">
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
