<%-- 
    Document   : Repuesto
    Created on : 18/11/2025, 1:57:02 p. m.
    Author     : santiago
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="modelo.Repuesto" %>

<!DOCTYPE html>
<html lang="es" data-bs-theme="dark">
    <head>
        <meta charset="UTF-8">
        <title>Nuevo Repuesto</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>

        <div class="py-3 bg-primary text-white text-center">
            <h1>Gestión de Repuestos</h1>
        </div>

        <div class="container text-start">
            <div class="row align-items-center mx-auto w-50">
                <div class="col m-5">
                    <form action="${pageContext.request.contextPath}/RepuestoServlet" method="post">
                        <input type="hidden" name="accion" value="crear">

                        <div class="mb-3">
                            <label class="form-label">Nombre del repuesto</label>
                            <input type="text" name="nombre" class="form-control" required>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Marca</label>
                            <input type="text" name="marca" class="form-control">
                        </div>
                        
                        <div class="mb-3">
                            <label class="form-label">Modelo</label>
                            <input type="text" name="modelo" class="form-control">
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Precio</label>
                            <input type="number" name="precio" class="form-control">
                        </div>
                        
                        <div class="mb-3">
                            <label class="form-label">Descripcion</label>
                            <input type="text" name="descripcion" class="form-control">
                        </div>
                        
                        <div class="mb-3">
                            <label class="form-label">Categoria</label>
                            <input type="text" name="categoria" class="form-control">
                        </div>

                        <button type="submit" class="btn btn-primary w-50">
                            Registrar
                        </button>
                    </form>
                </div>
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
