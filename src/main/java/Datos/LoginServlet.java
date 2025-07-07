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
import jakarta.servlet.http.HttpSession;
import java.sql.*;
import DAO.*;
import Modelo.*;
import java.util.List;
/**
 * Servlet que maneja el inicio de sesion 
 * @author Gabriela Solange Gonzalez Román
 * @author Leandro Rene Palacios Moriel
 * @version 1.0 
 * @since 2025‑06‑14
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

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
String usuario = request.getParameter("usuario");
    String contraseña = request.getParameter("contraseña");

    try (Connection con = ConexionPostgres.conectar()) {

        PreparedStatement stmt = con.prepareStatement(
                "SELECT * FROM usuarios WHERE nombre_usuario = ? AND contraseña = ?");
        stmt.setString(1, usuario);
        stmt.setString(2, contraseña);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            // Credenciales válidas
            Usuario u = new Usuario(
                    rs.getInt("id"),
                    rs.getString("nombre_usuario"),
                    rs.getString("contraseña"),
                    rs.getString("apodo"),
                    rs.getString("nombre_completo"),
                    rs.getString("correo"),
                    rs.getString("telefono"),
                    rs.getString("fecha_registro")
            );

            request.getSession().setAttribute("usuario", u);
            response.sendRedirect("ServletInicio");
        } else {
            // Credenciales incorrectas
            request.setAttribute("error", "Usuario o contraseña incorrectos. Por favor registrese o verifique sus datos.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }

    } catch (Exception e) {
        e.printStackTrace();
        request.setAttribute("error", "Error al iniciar sesión: " + e.getMessage());
        request.getRequestDispatcher("login.jsp").forward(request, response);
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
