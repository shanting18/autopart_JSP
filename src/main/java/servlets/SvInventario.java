package servlets;

import DAO.InventarioDAO;
import java.io.IOException;
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
import modelo.Inventario;

@WebServlet ("/InventarioServlet")
public class SvInventario extends HttpServlet {

    InventarioDAO inventarioDAO = new InventarioDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        if (accion == null) {
            listaInventario(request, response);
            return;
        }

        switch (accion) {
            case "listar":
                listaInventario(request, response);
                break;

            case "editar":
                String id = request.getParameter("id");
                Inventario inventarioEditar = inventarioDAO.obtenerPorId(id);

                request.setAttribute("inventario", inventarioEditar);
                RequestDispatcher rd = request.getRequestDispatcher("paginas/Inventario/EditarInventario.jsp");
                rd.forward(request, response);
                break;

            case "eliminar":

                Inventario inventario = new Inventario();
                try {
                    inventario.setId(Integer.parseInt(request.getParameter("id")));
                    inventarioDAO.eliminarInventario(inventario);

                    listaInventario(request, response);

                } catch (SQLException ex) {
                    Logger.getLogger(SvInventario.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;

            default:
                listaInventario(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");

        switch (accion) {
            case "crear":

                Inventario inventario = new Inventario();

                inventario.setNombreRepuesto(request.getParameter("repuesto"));
                inventario.setCantidad(Integer.parseInt(request.getParameter("cantidad")));
                LocalDate fechaRegistro = LocalDate.now();
                inventario.setUltimaActualizacion(fechaRegistro);

                inventarioDAO.crearInvenrtario(inventario);
                listaInventario(request, response);

                break;

            case "actualizar":

                Inventario iActualizar = new Inventario();

                iActualizar.setId(Integer.parseInt(request.getParameter("id")));
                iActualizar.setNombreRepuesto(request.getParameter("repuesto"));
                iActualizar.setCantidad(Integer.parseInt(request.getParameter("cantidad")));
                LocalDate ActualizarDate = LocalDate.now();
                iActualizar.setUltimaActualizacion(ActualizarDate);

                inventarioDAO.actualizarInventario(iActualizar);
                response.sendRedirect("InventarioServlet");
                break;
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void listaInventario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Inventario> listaInventario;
            listaInventario = inventarioDAO.listaInventario();

            request.setAttribute("listainventario", listaInventario);
            RequestDispatcher rd = request.getRequestDispatcher("paginas/Inventario/ListaInventario.jsp");
            rd.forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(SvInventario.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
