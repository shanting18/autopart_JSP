package servlets;

import DAO.OrdenPedidoDAO;
import java.io.IOException;
import java.io.PrintWriter;
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
import modelo.OrdenPedido;

@WebServlet("/OrdenPedidoServlet")
public class SvOrdenPedido extends HttpServlet {
    
    OrdenPedidoDAO ordenPedidoDAO = new OrdenPedidoDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String accion = request.getParameter("accion");

        if (accion == null) {
            listaOrdenPedido(request, response);
            return;
        }

        switch (accion) {
            case "listar":
                listaOrdenPedido(request, response);
                break;

            case "editar":
                String id = request.getParameter("id");
                OrdenPedido OrdenPedidoEditar = ordenPedidoDAO.obtenerPorId(id);

                request.setAttribute("OrdenPedido", OrdenPedidoEditar);
                RequestDispatcher rd = request.getRequestDispatcher("paginas/OrdenPedido/EditarOrdenPedido.jsp");
                rd.forward(request, response);
                break;

            case "eliminar":

                OrdenPedido ordenPedido = new OrdenPedido();
                try {
                    ordenPedido.setId(Integer.parseInt(request.getParameter("id")));
                    ordenPedidoDAO.eliminarOrden(ordenPedido);

                    listaOrdenPedido(request, response);

                } catch (SQLException ex) {
                    Logger.getLogger(SvOrdenPedido.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;

            default:
                listaOrdenPedido(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");

        switch (accion) {
            case "crear":

                OrdenPedido pedido = new OrdenPedido();
                
                LocalDate fechaOrden = LocalDate.now();
                pedido.setFecha(fechaOrden);
                pedido.setEstado(request.getParameter("estado"));
                pedido.setTotal(Double.parseDouble(request.getParameter("total")));
                

                ordenPedidoDAO.CrearOrden(pedido);
                listaOrdenPedido(request, response);

                break;

            case "actualizar":

                OrdenPedido oActualizar = new OrdenPedido();

                oActualizar.setId(Integer.parseInt(request.getParameter("id")));
                LocalDate fechaOrdenActualizar = LocalDate.now();
                oActualizar.setFecha(fechaOrdenActualizar);
                oActualizar.setEstado(request.getParameter("estado"));
                oActualizar.setTotal(Double.parseDouble(request.getParameter("total")));

                ordenPedidoDAO.actualizarOrden(oActualizar);
                response.sendRedirect("OrdenPedidoServlet");
                break;
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void listaOrdenPedido(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<OrdenPedido> listaOrdenPedido;
            listaOrdenPedido = ordenPedidoDAO.listaOrden();

            request.setAttribute("listaOrdenPedido", listaOrdenPedido);
            RequestDispatcher rd = request.getRequestDispatcher("paginas/OrdenPedido/ListaOrdenPedido.jsp");
            rd.forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(SvOrdenPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
