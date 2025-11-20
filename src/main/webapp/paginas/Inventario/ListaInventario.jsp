<%-- 
    Document   : ListaInventario
    Created on : 19/11/2025, 6:36:05 p. m.
    Author     : santiago
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="modelo.Inventario" %>

<!DOCTYPE html>
<html lang="es" data-bs-theme="dark">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista de Inventario</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>

        <div class="py-3 bg-primary text-white text-center">
            <h1>Gestión de inventario</h1>
        </div>
        
        <div class="container text-center">
            <div class="row align-items-center my-3">
                <div class="col">
                    <h2 class="text-center mb-4">Lista de invetario</h2>
                    <table class="table table-dark table-striped table-responsive">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Nombre del repuesto</th>
                                <th>cantidad</th>
                                <th>fecha de agregacion</th>
                                <th>Editar</th>
                                <th>Eliminar</th>
                            </tr>
                        </thead>

                        <tbody>
                            <%
                                List<Inventario> lista = (List<Inventario>) request.getAttribute("listainventario");
                                if (lista != null) {
                                    for (Inventario i : lista) {
                            %>

                            <tr>
                                <td><%= i.getId()%></td>
                                <td><%= i.getNombreRepuesto()%></td>
                                <td><%= i.getCantidad()%></td>
                                <td><%= i.getUltimaActualizacion()%></td>


                                <td>
                                    <a class="btn btn-sm btn-warning"
                                       href="InventarioServlet?accion=editar&id=<%=i.getId()%>&repuesto=<%=i.getNombreRepuesto()%>&cantidad=<%=i.getCantidad()%>&fecha_registro=<%=i.getUltimaActualizacion()%>">
                                        Editar
                                    </a>
                                </td>

                                <td>
                                    <a class="btn btn-sm btn-danger"
                                       href="InventarioServlet?accion=eliminar&id=<%=i.getId()%>"
                                       onclick="return confirm('¿Seguro que deseas eliminar este inventario?');">
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
