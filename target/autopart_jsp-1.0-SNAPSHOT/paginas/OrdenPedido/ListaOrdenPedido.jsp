<%-- 
    Document   : ListaOrdenpedido
    Created on : 20/11/2025, 12:20:44 p. m.
    Author     : santiago
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="modelo.OrdenPedido" %>

<!DOCTYPE html>
<html lang="es" data-bs-theme="dark">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista de Oreden</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>

        <div class="py-3 bg-primary text-white text-center">
            <h1>Gestión de Pedidos</h1>
        </div>
        
        <div class="container text-center">
            <div class="row align-items-center my-3">
                <div class="col">
                    <h2 class="text-center mb-4">Lista de estado de pedido</h2>
                    <table class="table table-dark table-striped table-responsive">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>fecha de pedido</th>
                                <th>Estado de pedido</th>
                                <th>precio total a pagar</th>
                                <th>Editar</th>
                                <th>Eliminar</th>
                            </tr>
                        </thead>

                        <tbody>
                            <%
                                List<OrdenPedido> lista = (List<OrdenPedido>) request.getAttribute("listaOrdenPedido");
                                if (lista != null) {
                                    for (OrdenPedido o : lista) {
                            %>

                            <tr>
                                <td><%= o.getId()%></td>
                                <td><%= o.getFecha()%></td>
                                <td><%= o.getEstado()%></td>
                                <td><%= o.getTotal()%></td>


                                <td>
                                    <a class="btn btn-sm btn-warning"
                                       href="OrdenPedidoServlet?accion=editar&id=<%=o.getId()%>&fecha=<%=o.getFecha()%>&estado=<%=o.getEstado()%>&total=<%=o.getTotal()%>">
                                        Editar
                                    </a>
                                </td>

                                <td>
                                    <a class="btn btn-sm btn-danger"
                                       href="OrdenPedidoServlet?accion=eliminar&id=<%=o.getId()%>"
                                       onclick="return confirm('¿Seguro que deseas eliminar este pedido?');">
                                        Eliminar
                                    </a>
                                </td>

                            </tr>

                            <% }
                                }
                            %>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>

