<%-- 
    Document   : OrdenPedido
    Created on : 20/11/2025, 12:03:49 p. m.
    Author     : santiago
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="modelo.OrdenPedido" %>

<!DOCTYPE html>
<html lang="es" data-bs-theme="dark">
    <head>
        <meta charset="UTF-8">
        <title>Nueva Orden</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>

        <div class="py-3 bg-primary text-white text-center">
            <h1>Gestión de Pedidos</h1>
        </div>

        <div class="container text-start">
            <div class="row align-items-center mx-auto w-50">
                <div class="col m-5">
                    <form action="${pageContext.request.contextPath}/OrdenPedidoServlet" method="post">
                        <input type="hidden" name="accion" value="crear">

                        <div class="mb-3">
                            <label class="form-label">Estado del pedido</label>
                            <input type="text" name="estado" class="form-control" required>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Total</label>
                            <input type="number" name="total" class="form-control">
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
