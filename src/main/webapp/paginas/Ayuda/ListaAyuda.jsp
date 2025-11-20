<%-- 
    Document   : ListaAyuda
    Created on : 20/11/2025, 1:19:09 p. m.
    Author     : santiago
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="modelo.Ayuda" %>

<!DOCTYPE html>
<html lang="es" data-bs-theme="dark">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista de Mensajes</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>

        <div class="py-3 bg-primary text-white text-center">
            <h1>Gestión de Mensajes</h1>
        </div>
        
        <div class="container text-center">
            <div class="row align-items-center my-3">
                <div class="col">
                    <h2 class="text-center mb-4">Lista de invetario</h2>
                    <table class="table table-dark table-striped table-responsive">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>pregunta</th>
                                <th>respuesta</th>
                                <th>Editar</th>
                                <th>Eliminar</th>
                            </tr>
                        </thead>

                        <tbody>
                            <%
                                List<Ayuda> lista = (List<Ayuda>) request.getAttribute("listaAyuda");
                                if (lista != null) {
                                    for (Ayuda a : lista) {
                            %>

                            <tr>
                                <td><%= a.getId()%></td>
                                <td><%= a.getPregunta()%></td>
                                <td><%= a.getRespuesta()%></td>

                                <td>
                                    <a class="btn btn-sm btn-warning"
                                       href="AyudaServlet?accion=editar&id=<%=a.getId()%>&pregunta=<%=a.getPregunta()%>">
                                        Editar
                                    </a>
                                </td>

                                <td>
                                    <a class="btn btn-sm btn-danger"
                                       href="AyudaServlet?accion=eliminar&id=<%=a.getId()%>"
                                       onclick="return confirm('¿Seguro que deseas eliminar este el mensaje?');">
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

