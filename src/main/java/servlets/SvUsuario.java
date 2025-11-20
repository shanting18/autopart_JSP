package servlets;

import DAO.UsuarioDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Usuario;

@WebServlet("/UsuarioServlet")
public class SvUsuario extends HttpServlet {

    UsuarioDAO usuarioDAO = new UsuarioDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");

        if (accion == null) {
            listarUsuarios(request, response);
            return;
        }

        switch (accion) {
            case "listar":
                listarUsuarios(request, response);
                break;

            case "editar":
                String id = request.getParameter("id");
                Usuario usuarioEditar = usuarioDAO.obtenerPorId(id);

                request.setAttribute("usuario", usuarioEditar);
                RequestDispatcher rd = request.getRequestDispatcher("paginas/Usuario/EditarUsuario.jsp");
                rd.forward(request, response);
                break;

            case "eliminar":
                
                Usuario usuario = new Usuario();
                try {
                    usuario.setId(request.getParameter("id"));
                    usuarioDAO.eliminar(usuario);
                    listarUsuarios(request, response);
                } catch (SQLException ex) {
                    Logger.getLogger(SvUsuario.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;

            default:
                listarUsuarios(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        switch (accion) {
            case "crear":

                Usuario usuario = new Usuario();
                
                usuario.setNombre(request.getParameter("nombre"));
                usuario.setCorreo(request.getParameter("correo"));
                usuario.setContrasena(request.getParameter("contrasena"));
                usuario.setDireccion(request.getParameter("direccion"));
                usuario.setTelefono(request.getParameter("telefono"));

                usuarioDAO.crearUsuario(usuario);
                listarUsuarios(request, response);
                
                break;

            case "actualizar":
                
                Usuario uActualizar = new Usuario();
                
                uActualizar.setId(request.getParameter("id"));
                uActualizar.setNombre(request.getParameter("nombre"));
                uActualizar.setCorreo(request.getParameter("correo"));
                uActualizar.setContrasena(request.getParameter("contrasena"));
                uActualizar.setDireccion(request.getParameter("direccion"));
                uActualizar.setTelefono(request.getParameter("telefono"));

                usuarioDAO.actualizar(uActualizar);
                response.sendRedirect("UsuarioServlet");
                break;

        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void listarUsuarios(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Usuario> listaUsuarios;
            listaUsuarios = usuarioDAO.lista();
            
            request.setAttribute("listaUsuarios", listaUsuarios);
            RequestDispatcher rd = request.getRequestDispatcher("paginas/Usuario/ListaUsuario.jsp");
            rd.forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(SvUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
