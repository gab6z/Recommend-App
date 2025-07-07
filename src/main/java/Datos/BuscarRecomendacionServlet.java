/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Datos;


import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.*;
import DAO.*;
import Modelo.*;
import jakarta.servlet.http.HttpSession;
import java.util.List;


/**
 * Servidor para que se gestione la busqueda de recomendaciones
 * @author Gabriela Solange Gonzalez Román
 * @author Leandro Rene Palacios Moriel
 * @version 1.0 
 * @since 2025‑06‑12
 */
@WebServlet(name = "BuscarRecomendacionServlet", urlPatterns = {"/BuscarRecomendacionServlet"})
public class BuscarRecomendacionServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet BuscarRecomendacionServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BuscarRecomendacionServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
        String tituloBuscado = request.getParameter("titulo");

        try (Connection con = ConexionPostgres.conectar()) {
            RecomendacionDAO dao = new RecomendacionDAO(con);
            List<Recomendacion> resultados = dao.buscarPorTitulo(tituloBuscado);

            request.setAttribute("recomendaciones", resultados);

            // Para mantener al usuario logueado visible en la JSP:
            HttpSession sesion = request.getSession();
            Usuario usuario = (Usuario) sesion.getAttribute("usuario");
            request.setAttribute("usuario", usuario);

            request.getRequestDispatcher("inicio.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException("Error al buscar recomendaciones", e);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
