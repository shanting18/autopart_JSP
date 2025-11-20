<%-- 
    Document   : crearVendedor
    Created on : 14/11/2025, 10:21:13 p. m.
    Author     : santiago
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="modelo.Vendedor" %>

<!DOCTYPE html>
<html lang="es" data-bs-theme="dark">
    <head>
        <meta charset="UTF-8">
        <title>Nuevo Vendedor</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>

        <div class="py-3 bg-primary text-white text-center">
            <h1>Gestión de Vendedores</h1>
        </div>

        <div class="container text-start">
            <div class="row align-items-center mx-auto w-50">
                <div class="col m-5">
                    <form action="${pageContext.request.contextPath}/VendedorServlet" method="post">
                        <input type="hidden" name="accion" value="crear">

                        <div class="mb-3">
                            <label class="form-label">Nombre del Negocio</label>
                            <input type="text" name="nombre_tienda" class="form-control" required>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Descripcion</label>
                            <input type="text" name="descripcion" class="form-control">
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

