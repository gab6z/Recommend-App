/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 * Permite realizar la autenticación de usuarios del sistema mediante el
 * método {@link #login(String, String)}, que verifica las credenciales contra
 * la tabla usuarios
 * @author Gabriela Solange Gonzalez Román
 * @author Leandro Rene Palacios Moriel
 * @version 1.0 
 * @since 2025‑06‑13
 */
import java.sql.*;
import Modelo.*;

/**
 * DAO (<em>Data Access Object</em>) para la entidad {@link Modelo.Usuario}.*/
public class UsuarioDAO {

    /** Conexión activa a la base de datos. */
    private Connection con;

    /**
     * Crea una instancia de {@code UsuarioDAO}.
     *
     * @param con conexión a la base de datos que se utilizará en las consultas
     */
    public UsuarioDAO(Connection con) {
        this.con = con;
    }

    /**
     * Autentica a un usuario comprobando su nombre de usuario y contraseña.
     *
     * @param nombreUsuario nombre de usuario ingresado
     * @param contraseña    contraseña ingresada
     * @return una instancia de {@link Modelo.Usuario} con los datos del usuario
     *         autenticado, o {@code null} si las credenciales no son válidas
     * @throws SQLException si ocurre un error durante la consulta
     */
    public Usuario login(String nombreUsuario, String contraseña) throws SQLException {
    String sql = "SELECT * FROM usuarios WHERE nombre_usuario = ? AND contraseña  = ?";
    try (PreparedStatement stmt = con.prepareStatement(sql)) {
        stmt.setString(1, nombreUsuario);
        stmt.setString(2, contraseña);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                Usuario u = new Usuario();
                u.setId(rs.getInt("id"));
                u.setNombreUsuario(rs.getString("nombre_usuario"));
                u.setContraseña(rs.getString("contraseña"));
                u.setApodo(rs.getString("apodo"));
                u.setNombreCompleto(rs.getString("nombre_completo"));
                u.setCorreo(rs.getString("correo"));
                u.setTelefono(rs.getString("telefono"));
                u.setFechaRegistro(rs.getString("fecha_registro"));
                return u;
            }
        }
    }
    return null;
}

}

