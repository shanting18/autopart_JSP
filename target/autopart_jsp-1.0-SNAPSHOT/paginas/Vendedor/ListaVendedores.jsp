<%-- 
    Document   : ListaVendedor
    Created on : 16/11/2025, 6:15:24 p. m.
    Author     : santiago
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="modelo.Vendedor" %>

<!DOCTYPE html>
<html lang="es" data-bs-theme="dark">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista de Vendedores</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>

        <div class="py-3 bg-primary text-white text-center">
            <h1>Gestión de Vendedores</h1>
        </div>
        
        <div class="container text-center">
            <div class="row align-items-center my-3">
                <div class="col">
                    <h2 class="text-center mb-4">Lista de Vendedores</h2>
                    <table class="table table-dark table-striped table-responsive">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Nombre del negocion</th>
                                <th>descripcion</th>
                                <th>fecha de registro</th>
                                <th>REPUTACION</th>
                                <th>Editar</th>
                                <th>Eliminar</th>
                            </tr>
                        </thead>

                        <tbody>
                            <%
                                List<Vendedor> lista = (List<Vendedor>) request.getAttribute("listaVendedores");
                                if (lista != null) {
                                    for (Vendedor v : lista) {
                            %>

                            <tr>
                                <td><%= v.getId()%></td>
                                <td><%= v.getNombre_tienda()%></td>
                                <td><%= v.getDescripcion()%></td>
                                <td><%= v.getFecha_registro()%></td>
                                <td><%= v.getReputacion()%></td>


                                <td>
                                    <a class="btn btn-sm btn-warning"
                                       href="VendedorServlet?accion=editar&id=<%=v.getId()%>&nombre_tienda=<%=v.getNombre_tienda()%>&descripcion=<%=v.getDescripcion()%>&fecha_registro=<%=v.getFecha_registro()%>">
                                        Editar
                                    </a>
                                </td>

                                <td>
                                    <a class="btn btn-sm btn-danger"
                                       href="VendedorServlet?accion=eliminar&id=<%=v.getId()%>"
                                       onclick="return confirm('¿Seguro que deseas eliminar este vendedor?');">
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
