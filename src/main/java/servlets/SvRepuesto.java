package servlets;

import DAO.RepuestoDAO;
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
import modelo.Repuesto;

@WebServlet("/RepuestoServlet")
public class SvRepuesto extends HttpServlet {

    RepuestoDAO repuestoDAO = new RepuestoDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        if (accion == null) {
            listaRepuestos(request, response);
            return;
        }

        switch (accion) {
            case "listar":
                listaRepuestos(request, response);
                break;

            case "editar":
                String id = request.getParameter("id");
                Repuesto repuestoEditar = repuestoDAO.obtenerPorId(id);

                request.setAttribute("repuesto", repuestoEditar);
                RequestDispatcher rd = request.getRequestDispatcher("paginas/Repuesto/EditarRepuesto.jsp");
                rd.forward(request, response);
                break;

            case "eliminar":

                Repuesto repuesto = new Repuesto();
                try {
                    repuesto.setId(Integer.parseInt(request.getParameter("id")));
                    repuestoDAO.eliminarRepuesto(repuesto);

                    listaRepuestos(request, response);

                } catch (SQLException ex) {
                    Logger.getLogger(SvRepuesto.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;

            default:
                listaRepuestos(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        switch (accion) {
            case "crear":

                Repuesto repuesto = new Repuesto();

                repuesto.setNombre(request.getParameter("nombre"));
                repuesto.setCategoria(request.getParameter("categoria"));
                repuesto.setPrecio(Double.parseDouble(request.getParameter("precio")));
                repuesto.setDescripcion(request.getParameter("descripcion"));
                repuesto.setCategoria(request.getParameter("categoria"));
                repuesto.setMarca(request.getParameter("modelo"));
                repuesto.setModelo(request.getParameter("marca"));
                

                repuestoDAO.crearRepuesto(repuesto);
                listaRepuestos(request, response);

                break;

            case "actualizar":

                Repuesto rActualizar = new Repuesto();

                rActualizar.setId(Integer.parseInt(request.getParameter("id")));
                rActualizar.setNombre(request.getParameter("nombre"));
                rActualizar.setCategoria(request.getParameter("categoria"));
                rActualizar.setPrecio(Double.parseDouble(request.getParameter("precio")));
                rActualizar.setDescripcion(request.getParameter("descripcion"));
                rActualizar.setCategoria(request.getParameter("categoria"));
                rActualizar.setMarca(request.getParameter("modelo"));
                rActualizar.setModelo(request.getParameter("marca"));

                repuestoDAO.actualizarRepuesto(rActualizar);
                response.sendRedirect("RepuestoServlet");
                break;
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void listaRepuestos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Repuesto> listaRepuestos;
            listaRepuestos = repuestoDAO.listaRepuesto();

            request.setAttribute("listaRepuestos", listaRepuestos);
            RequestDispatcher rd = request.getRequestDispatcher("paginas/Repuesto/ListaRepuestos.jsp");
            rd.forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(SvRepuesto.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
