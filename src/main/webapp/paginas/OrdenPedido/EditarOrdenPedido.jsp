<%-- 
    Document   : EditarOrdenPedido
    Created on : 20/11/2025, 12:42:24 p. m.
    Author     : santiago
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="modelo.OrdenPedido" %>

<!DOCTYPE html>
<html lang="es" data-bs-theme="dark">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edicion de Ppedido</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>

        <div class="py-3 bg-primary text-white text-center">
            <h1>Gestión de pedido</h1>
        </div>

        <div class="container text-start">
            <div class="row align-items-center mx-auto w-50">
                <div class="col m-5">

                    <c:if test="${not empty OrdenPedido}">
                        <h2 class="text-center mb-4">Editar Pedido</h2>

                        <form action="${pageContext.request.contextPath}/OrdenPedidoServlet" method="post">
                            
                            <input type="hidden" name="accion" value="actualizar">
                            <input type="hidden" name="id" value="<%= request.getParameter("id")%>">
                            

                            <div class="mb-3">
                                <label class="form-label">Estado del pedido</label>
                                <input type="text" name="estado" class="form-control" value="${ordenPedido.estado}">
                            </div>

                            <div class="mb-3">
                                <label class="form-label">total</label>
                                <input type="number" name="total" class="form-control" value="${ordenPedido.total}">
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

