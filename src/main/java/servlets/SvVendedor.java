package servlets;

import DAO.VendedorDAO;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Vendedor;

@WebServlet("/VendedorServlet")
public class SvVendedor extends HttpServlet {

    VendedorDAO vendedorDAO = new VendedorDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");

        if (accion == null) {
            listaVendedores(request, response);
            return;
        }

        switch (accion) {
            case "listar":
                listaVendedores(request, response);
                break;

            case "editar":
                String id = request.getParameter("id");
                Vendedor vendedorEditar = vendedorDAO.obtenerPorId(id);

                request.setAttribute("vendedor", vendedorEditar);
                RequestDispatcher rd = request.getRequestDispatcher("paginas/Vendedor/EditarVendedor.jsp");
                rd.forward(request, response);
                break;

            case "eliminar":

                Vendedor vendedor = new Vendedor();
                try {
                    vendedor.setId(Integer.parseInt(request.getParameter("id")));
                    vendedorDAO.eliminarVendedor(vendedor);
                    listaVendedores(request, response);
                } catch (SQLException ex) {
                    Logger.getLogger(SvVendedor.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;

            default:
                listaVendedores(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        switch (accion) {
            case "crear":

                Vendedor vendedor = new Vendedor();

                vendedor.setNombre_tienda(request.getParameter("nombre_tienda"));
                vendedor.setDescripcion(request.getParameter("descripcion"));
                LocalDate fechaSQL = LocalDate.now();
                vendedor.setFecha_registro(fechaSQL);

                vendedorDAO.crearVendedor(vendedor);
                listaVendedores(request, response);

                break;

            case "actualizar":

                Vendedor vActualizar = new Vendedor();
                
                vActualizar.setId(Integer.parseInt(request.getParameter("id")));
                vActualizar.setNombre_tienda(request.getParameter("nombre_tienda"));
                vActualizar.setDescripcion(request.getParameter("descripcion"));

                LocalDate ActualizarDate = LocalDate.now();
                vActualizar.setFecha_registro(ActualizarDate);

                vendedorDAO.actualizarVendedor(vActualizar);
                response.sendRedirect("VendedorServlet");
                break;

        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void listaVendedores(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Vendedor> listaVendedores;
            listaVendedores = vendedorDAO.lista();

            request.setAttribute("listaVendedores", listaVendedores);
            RequestDispatcher rd = request.getRequestDispatcher("paginas/Vendedor/ListaVendedores.jsp");
            rd.forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(SvVendedor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
