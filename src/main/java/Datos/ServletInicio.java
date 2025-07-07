package Datos;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.util.List;
import DAO.*;
import Modelo.*;

/**
 * Servlet destinado a cargar todas las recomendaciones de todos los usuarios,
 * es decir, las recomendaciones que se encuentran en la base de datos
 * @author Gabriela Solange Gonzalez Román
 * @author Leandro Rene Palacios Moriel
 * @version 1.0 
 * @since 2025‑06‑14
 */

@WebServlet(name = "ServletInicio", urlPatterns = {"/ServletInicio"})
public class ServletInicio extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        //Validar sesión activa
        if (request.getSession().getAttribute("usuario") == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        //Try-catch para establecer la conexion correctamente
        try (Connection con = ConexionPostgres.conectar()) {
            if (con == null) {
                request.setAttribute("error", "No se pudo conectar a la base de datos");
                request.getRequestDispatcher("error.jsp").forward(request, response);
                return;
            }

            // Obtener el usuario actual desde sesión
            Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
            request.setAttribute("usuario", usuario);

            // Obtener recomendaciones desde la base de datos
            RecomendacionDAO recomendacionDAO = new RecomendacionDAO(con);
            List<Recomendacion> recomendaciones = recomendacionDAO.obtenerTodas();
            request.setAttribute("recomendaciones", recomendaciones);

            RequestDispatcher rd = request.getRequestDispatcher("inicio.jsp");
            rd.forward(request, response);

        } catch (Exception e) {
            request.setAttribute("error", "Error cargando recomendaciones: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet de inicio que muestra recomendaciones";
    }
}
