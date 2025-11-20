/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import DAO.AyudaDAO;
import java.io.IOException;
import java.io.PrintWriter;
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
import modelo.Ayuda;

@WebServlet("/AyudaServlet")
public class SvAyuda extends HttpServlet {

    AyudaDAO ayudaDAO = new AyudaDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        if (accion == null) {
            listaAyuda(request, response);
            return;
        }

        switch (accion) {
            case "listar":
                listaAyuda(request, response);
                break;

            case "editar":
                String id = request.getParameter("id");
                Ayuda ayudaEditar = ayudaDAO.obtenerPorId(id);

                request.setAttribute("Ayuda", ayudaEditar);
                RequestDispatcher rd = request.getRequestDispatcher("paginas/Ayuda/EditarAyuda.jsp");
                rd.forward(request, response);
                break;

            case "eliminar":

                Ayuda ayuda = new Ayuda();
                try {
                    ayuda.setId(Integer.parseInt(request.getParameter("id")));
                    ayudaDAO.eliminarMensaje(ayuda);

                    listaAyuda(request, response);

                } catch (SQLException ex) {
                    Logger.getLogger(SvAyuda.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;

            default:
                listaAyuda(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");

        switch (accion) {
            case "crear":

                Ayuda ayuda = new Ayuda();
                ayuda.setPregunta(request.getParameter("pregunta"));

                ayudaDAO.crearMensaje(ayuda);
                listaAyuda(request, response);

                break;

            case "actualizar":

                Ayuda aActualizar = new Ayuda();

                aActualizar.setId(Integer.parseInt(request.getParameter("id")));
                aActualizar.setPregunta(request.getParameter("pregunta"));

                ayudaDAO.ActualizarPregunta(aActualizar);
                response.sendRedirect("AyudaServlet");
                break;
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void listaAyuda(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Ayuda> listaAyuda;
            listaAyuda = ayudaDAO.listaAyuda();

            request.setAttribute("listaAyuda", listaAyuda);
            RequestDispatcher rd = request.getRequestDispatcher("paginas/Ayuda/ListaAyuda.jsp");
            rd.forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(SvAyuda.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
