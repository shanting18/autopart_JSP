<%-- 
    Document   : ListaUsuario
    Created on : 16/11/2025, 6:15:24 p. m.
    Author     : santiago
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="modelo.Usuario" %>

<!DOCTYPE html>
<html lang="es" data-bs-theme="dark">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista de usuarios</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>

        <div class="py-3 bg-primary text-white text-center">
            <h1>Gestión de Usuarios</h1>
        </div>
        
        <div class="container text-center">
            <div class="row align-items-center my-3">
                <div class="col">
                    <h2 class="text-center mb-4">Lista de usuarios</h2>
                    <table class="table table-dark table-striped table-responsive">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Nombre</th>
                                <th>Correo</th>
                                <th>Direccion</th>
                                <th>Telefono</th>
                                <th>Editar</th>
                                <th>Eliminar</th>
                            </tr>
                        </thead>

                        <tbody>
                            <%
                                List<Usuario> lista = (List<Usuario>) request.getAttribute("listaUsuarios");
                                if (lista != null) {
                                    for (Usuario u : lista) {
                            %>

                            <tr>
                                <td><%= u.getId()%></td>
                                <td><%= u.getNombre()%></td>
                                <td><%= u.getCorreo()%></td>
                                <td><%= u.getDireccion()%></td>
                                <td><%= u.getTelefono()%></td>

                                <td>
                                    <a class="btn btn-sm btn-warning"
                                       href="UsuarioServlet?accion=editar&id=<%=u.getId()%>&nombre=<%=u.getNombre()%>&correo=<%=u.getCorreo()%>&direccion=<%=u.getDireccion()%>&telefono=<%=u.getTelefono()%>">
                                        Editar
                                    </a>
                                </td>

                                <td>
                                    <a class="btn btn-sm btn-danger"
                                       href="UsuarioServlet?accion=eliminar&id=<%=u.getId()%>"
                                       onclick="return confirm('¿Seguro que deseas eliminar este usuario?');">
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
