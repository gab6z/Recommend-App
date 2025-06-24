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
import jakarta.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


/**
 *
 * @author User
 */
@WebServlet(name = "PublicarServlet", urlPatterns = {"/PublicarServlet"})
public class PublicarServlet extends HttpServlet {

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
            out.println("<title>Servlet PublicarServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PublicarServlet at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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
            // Validar sesión activa
    HttpSession session = request.getSession(false);
    if (session == null || session.getAttribute("usuario") == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    // Obtener datos del formulario
    String titulo = request.getParameter("titulo");
    String descripcion = request.getParameter("descripcion");
    String categoriaNombre = request.getParameter("categoria");

    if (titulo == null || descripcion == null || categoriaNombre == null) {
        request.setAttribute("error", "Todos los campos son obligatorios.");
        request.getRequestDispatcher("inicio.jsp").forward(request, response);
        return;
    }

    Usuario usuario = (Usuario) session.getAttribute("usuario");

    try (Connection con = ConexionPostgres.conectar()) {

        // Obtener ID de la categoría por nombre
        int categoriaId = -1;
        String catSQL = "SELECT id FROM categorias WHERE nombre = ?";
        try (PreparedStatement stmt = con.prepareStatement(catSQL)) {
            stmt.setString(1, categoriaNombre);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    categoriaId = rs.getInt("id");
                } else {
                    request.setAttribute("error", "Categoría inválida.");
                    request.getRequestDispatcher("inicio.jsp").forward(request, response);
                    return;
                }
            }
        }

        // Construir recomendación
        Recomendacion rec = new Recomendacion();
        rec.setTitulo(titulo);
        rec.setDescripcion(descripcion);
        rec.setCategoria(new Categoria(categoriaId, categoriaNombre));
        rec.setUsuario(usuario);
        rec.setFecha(new java.sql.Date(System.currentTimeMillis())); 

        // Insertar en la base
        RecomendacionDAO dao = new RecomendacionDAO(con);
        dao.insertar(rec);

        // Redirigir a inicio
        response.sendRedirect("ServletInicio");

    } catch (Exception e) {
        e.printStackTrace(); // idealmente usar logs
        request.setAttribute("error", "Error al publicar: " + e.getMessage());
        request.getRequestDispatcher("inicio.jsp").forward(request, response);
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
