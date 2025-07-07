/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Datos;

import Modelo.*;
import java.io.IOException;
import java.sql.*;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servidor destinado para el registro del usuario
 * @author Gabriela Solange Gonzalez Román
 * @author Leandro Rene Palacios Moriel
 * @version 1.0 
 * @since 2025‑06‑12
 */
@WebServlet(name = "RegistroServlet", urlPatterns = {"/RegistroServlet"})
public class RegistroServlet extends HttpServlet {

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
    request.setCharacterEncoding("UTF-8");
     
    //Declaracion de variables  para obtenerlas
    String apodo = request.getParameter("apodo");
    String nombre = request.getParameter("nombre");
    String correo = request.getParameter("correo");
    String telefono = request.getParameter("telefono");
    String nombreUsuario = request.getParameter("usuario");
    String clave = request.getParameter("contraseña");
    String confirmar = request.getParameter("confirmar");

    // Validaciones básicas
    if (apodo == null || nombre == null || correo == null || telefono == null ||
        nombreUsuario == null || clave == null || confirmar == null ||
        apodo.isEmpty() || nombre.isEmpty() || correo.isEmpty() || telefono.isEmpty() ||
        nombreUsuario.isEmpty() || clave.isEmpty() || confirmar.isEmpty()) {
        
        request.setAttribute("error", "Todos los campos son obligatorios.");
        request.getRequestDispatcher("registro.jsp").forward(request, response);
        return;
    }

    // Validar correo
    if (!correo.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$")) {
        request.setAttribute("error", "El correo no es válido.");
        request.getRequestDispatcher("registro.jsp").forward(request, response);
        return;
    }

    // Validar teléfono (solo 10 dígitos numéricos)
    if (!telefono.matches("\\d{10}")) {
        request.setAttribute("error", "El teléfono debe tener exactamente 10 dígitos.");
        request.getRequestDispatcher("registro.jsp").forward(request, response);
        return;
    }

    // Validar contraseña segura
    if (!clave.matches("^(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+=<>?{}\\[\\]~\\-.]).{8,}$")) {
        request.setAttribute("error", "La contraseña debe tener al menos una mayúscula, un número, un carácter especial y mínimo 8 caracteres.");
        request.getRequestDispatcher("registro.jsp").forward(request, response);
        return;
    }

    // Confirmar contraseña
    if (!clave.equals(confirmar)) {
        request.setAttribute("error", "Las contraseñas no coinciden.");
        request.getRequestDispatcher("registro.jsp").forward(request, response);
        return;
    }

    try (Connection con = ConexionPostgres.conectar()) {

        // Validar si apodo o correo ya existen
        PreparedStatement checkStmt = con.prepareStatement(
            "SELECT COUNT(*) FROM usuarios WHERE apodo = ? OR correo = ?");
        checkStmt.setString(1, apodo);
        checkStmt.setString(2, correo);
        ResultSet checkRs = checkStmt.executeQuery();
        if (checkRs.next() && checkRs.getInt(1) > 0) {
            request.setAttribute("error", "El apodo o correo ya está registrado.");
            request.getRequestDispatcher("registro.jsp").forward(request, response);
            return;
        }

        // Insertar usuario
        PreparedStatement ps = con.prepareStatement(
                "INSERT INTO usuarios (nombre_usuario, contraseña, apodo, nombre_completo, correo, telefono, fecha_registro) VALUES (?, ?, ?, ?, ?, ?, NOW())",
                Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, nombreUsuario);
        ps.setString(2, clave);
        ps.setString(3, apodo);
        ps.setString(4, nombre);
        ps.setString(5, correo);
        ps.setString(6, telefono);
        ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();
        int idUsuario = 0;
        if (rs.next()) {
            idUsuario = rs.getInt(1);
        }

        Usuario usuario = new Usuario(idUsuario, nombreUsuario, clave, apodo, nombre, correo, telefono, "");
        request.getSession().setAttribute("usuario", usuario);

        response.sendRedirect("ServletInicio");

    } catch (Exception e) {
        e.printStackTrace();
        request.setAttribute("error", "Error al registrar: " + e.getMessage());
        request.getRequestDispatcher("registro.jsp").forward(request, response);
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
