/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author User
 */
import java.sql.*;
import Modelo.*;

public class UsuarioDAO {
    private Connection con;

    public UsuarioDAO(Connection con) {
        this.con = con;
    }

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

