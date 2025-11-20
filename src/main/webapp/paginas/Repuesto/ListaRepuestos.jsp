<%-- 
    Document   : ListaRepuestos
    Created on : 18/11/2025, 1:57:44 p. m.
    Author     : santiago
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="modelo.Repuesto" %>

<!DOCTYPE html>
<html lang="es" data-bs-theme="dark">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista de Repuestos</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>

        <div class="py-3 bg-primary text-white text-center">
            <h1>Gestión de Repuestos</h1>
        </div>
        
        <div class="container text-center">
            <div class="row align-items-center my-3">
                <div class="col">
                    <h2 class="text-center mb-4">Lista de Repuestos</h2>
                    <table class="table table-dark table-striped table-responsive">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Nombre del repuesto</th>
                                <th>Descripcion</th>
                                <th>Precio</th>
                                <th>Marca</th>
                                <th>Modelo</th>
                                <th>Categoria</th>
                                <th>Editar</th>
                                <th>Eliminar</th>
                            </tr>
                        </thead>

                        <tbody>
                            <%
                                List<Repuesto> lista = (List<Repuesto>) request.getAttribute("listaRepuestos");
                                if (lista != null) {
                                    for (Repuesto r : lista) {
                            %>

                            <tr>
                                <td><%= r.getId()%></td>
                                <td><%= r.getNombre()%></td>
                                <td><%= r.getDescripcion()%></td>
                                <td><%= r.getPrecio()%></td>
                                <td><%= r.getMarca()%></td>
                                <td><%= r.getModelo()%></td>
                                <td><%= r.getCategoria()%></td>


                                <td>
                                    <a class="btn btn-sm btn-warning"
                                       href="RepuestoServlet?accion=editar&id=<%=r.getId()%>&nombre=<%=r.getNombre()%>&descripcion=<%=r.getDescripcion()%>&precio=<%=r.getPrecio()%>&marca=<%=r.getMarca()%>&modelo=<%=r.getModelo()%>&categoria=<%=r.getDescripcion()%>">
                                        Editar
                                    </a>
                                </td>

                                <td>
                                    <a class="btn btn-sm btn-danger"
                                       href="RepuestoServlet?accion=eliminar&id=<%=r.getId()%>"
                                       onclick="return confirm('¿Seguro que deseas eliminar este repuesto?');">
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
