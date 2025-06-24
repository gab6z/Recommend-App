/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Datos;

import DAO.*;
import Modelo.*;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.List;

/**
 *
 * @author User
 */
@WebServlet(name = "EditarRecomendacionServlet", urlPatterns = {"/EditarRecomendacionServlet"})
public class EditarRecomendacionServlet extends HttpServlet {

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
         int id = Integer.parseInt(request.getParameter("id"));

        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/muro_recomendaciones", "postgres", "0321");

            RecomendacionDAO dao = new RecomendacionDAO(con);
            Recomendacion rec = dao.obtenerPorId(id); 

            List<Categoria> categorias = new CategoriaDAO(con).obtenerTodas(); 

            request.setAttribute("recomendacion", rec);
            request.setAttribute("categorias", categorias);
            request.getRequestDispatcher("editar_recomendacion.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error cargando recomendación: " + e.getMessage());
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String titulo = request.getParameter("titulo");
        String descripcion = request.getParameter("descripcion");
        int categoriaId = Integer.parseInt(request.getParameter("categoria"));

        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/muro_recomendaciones", "postgres", "0321");

            RecomendacionDAO dao = new RecomendacionDAO(con);

            Recomendacion rec = new Recomendacion();
            rec.setId(id);
            rec.setTitulo(titulo);
            rec.setDescripcion(descripcion);
            rec.setCategoria(new Categoria(categoriaId, ""));

            dao.actualizar(rec); 
            response.sendRedirect("perfil.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error actualizando recomendación: " + e.getMessage());
        }
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
