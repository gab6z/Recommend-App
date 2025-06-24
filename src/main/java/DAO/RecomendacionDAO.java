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
import java.util.*;
import Modelo.*;

public class RecomendacionDAO {
    private Connection con;
    public Usuario usuario;

    public RecomendacionDAO(Connection con) {
        this.con = con;
    }

    public List<Recomendacion> obtenerTodas() throws SQLException {
        List<Recomendacion> lista = new ArrayList<>();
        String sql = "SELECT r.*, c.nombre AS nombre_categoria, u.apodo AS nombre_usuario " +
                     "FROM recomendaciones r " +
                     "LEFT JOIN categorias c ON r.categoria_id = c.id " +
                     "LEFT JOIN usuarios u ON r.usuario_id = u.id";
        try (PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Recomendacion rec = new Recomendacion();
                rec.setId(rs.getInt("id"));
                rec.setTitulo(rs.getString("titulo"));
                rec.setDescripcion(rs.getString("descripcion"));
                rec.setFecha(rs.getDate("fecha"));

                Categoria cat = new Categoria(rs.getInt("categoria_id"), rs.getString("nombre_categoria"));
                Usuario usr = new Usuario();
                usr.setId(rs.getInt("usuario_id"));
                usr.setApodo(rs.getString("nombre_usuario"));

                rec.setCategoria(cat);
                rec.setUsuario(usr);
                lista.add(rec);
            }
        }
        return lista;
    }

    public void insertar(Recomendacion rec) throws SQLException {
        String sql = "INSERT INTO recomendaciones (titulo, descripcion, categoria_id, usuario_id, fecha) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, rec.getTitulo());
            stmt.setString(2, rec.getDescripcion());
            stmt.setInt(3, rec.getCategoria().getId());
            stmt.setInt(4, rec.getUsuario().getId());
            stmt.setDate(5, new java.sql.Date(rec.getFecha().getTime()));
            stmt.executeUpdate();
        }
    }
    public List<Recomendacion> buscarPorTitulo(String titulo) throws SQLException {
    List<Recomendacion> lista = new ArrayList<>();
    String sql = "SELECT r.*, c.nombre AS nombre_categoria, u.apodo AS nombre_usuario " +
                 "FROM recomendaciones r " +
                 "LEFT JOIN categorias c ON r.categoria_id = c.id " +
                 "LEFT JOIN usuarios u ON r.usuario_id = u.id " +
                 "WHERE LOWER(r.titulo) LIKE ?";
    try (PreparedStatement stmt = con.prepareStatement(sql)) {
        stmt.setString(1, "%" + titulo.toLowerCase() + "%");
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Recomendacion rec = new Recomendacion();
                rec.setId(rs.getInt("id"));
                rec.setTitulo(rs.getString("titulo"));
                rec.setDescripcion(rs.getString("descripcion"));
                rec.setFecha(rs.getDate("fecha"));

                Categoria cat = new Categoria(rs.getInt("categoria_id"), rs.getString("nombre_categoria"));
                Usuario usr = new Usuario();
                usr.setId(rs.getInt("usuario_id"));
                usr.setApodo(rs.getString("nombre_usuario"));

                rec.setCategoria(cat);
                rec.setUsuario(usr);
                lista.add(rec);
            }
        }
    }
    return lista;
}
public List<Recomendacion> buscarPorTituloOCategoria(String texto) throws SQLException {
    List<Recomendacion> lista = new ArrayList<>();
    String sql = "SELECT r.*, c.nombre AS nombre_categoria, u.apodo AS nombre_usuario " +
                 "FROM recomendaciones r " +
                 "LEFT JOIN categorias c ON r.categoria_id = c.id " +
                 "LEFT JOIN usuarios u ON r.usuario_id = u.id " +
                 "WHERE LOWER(r.titulo) LIKE ? OR LOWER(c.nombre) LIKE ?";
    try (PreparedStatement stmt = con.prepareStatement(sql)) {
        String param = "%" + texto.toLowerCase() + "%";
        stmt.setString(1, param);
        stmt.setString(2, param);

        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Recomendacion rec = new Recomendacion();
                rec.setId(rs.getInt("id"));
                rec.setTitulo(rs.getString("titulo"));
                rec.setDescripcion(rs.getString("descripcion"));
                rec.setFecha(rs.getDate("fecha"));

                Categoria cat = new Categoria(rs.getInt("categoria_id"), rs.getString("nombre_categoria"));
                Usuario usr = new Usuario();
                usr.setId(rs.getInt("usuario_id"));
                usr.setApodo(rs.getString("nombre_usuario"));

                rec.setCategoria(cat);
                rec.setUsuario(usr);
                lista.add(rec);
            }
        }
    }
    return lista;
}
public List<Recomendacion> listarPorUsuario(int usuarioId) throws SQLException {
    List<Recomendacion> lista = new ArrayList<>();
    String sql = "SELECT r.*, c.nombre AS nombre_categoria, u.apodo AS nombre_usuario " +
                 "FROM recomendaciones r " +
                 "LEFT JOIN categorias c ON r.categoria_id = c.id " +
                 "LEFT JOIN usuarios u ON r.usuario_id = u.id " +
                 "WHERE r.usuario_id = ?";
    try (PreparedStatement stmt = con.prepareStatement(sql)) {
        stmt.setInt(1, usuarioId);
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Recomendacion rec = new Recomendacion();
                rec.setId(rs.getInt("id"));
                rec.setTitulo(rs.getString("titulo"));
                rec.setDescripcion(rs.getString("descripcion"));
                rec.setFecha(rs.getDate("fecha"));

                Categoria cat = new Categoria(
                    rs.getInt("categoria_id"),
                    rs.getString("nombre_categoria")
                );

                Usuario usr = new Usuario();
                usr.setId(rs.getInt("usuario_id"));
                usr.setApodo(rs.getString("nombre_usuario"));

                rec.setCategoria(cat);
                rec.setUsuario(usr);
                lista.add(rec);
            }
        }
    }
    return lista;
}

public Recomendacion obtenerPorId(int id) throws SQLException {
    String sql = "SELECT r.*, c.nombre AS nombre_categoria FROM recomendaciones r " +
                 "LEFT JOIN categorias c ON r.categoria_id = c.id WHERE r.id = ?";
    try (PreparedStatement stmt = con.prepareStatement(sql)) {
        stmt.setInt(1, id);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                Recomendacion rec = new Recomendacion();
                rec.setId(rs.getInt("id"));
                rec.setTitulo(rs.getString("titulo"));
                rec.setDescripcion(rs.getString("descripcion"));
                rec.setFecha(rs.getDate("fecha"));

                Categoria cat = new Categoria(rs.getInt("categoria_id"), rs.getString("nombre_categoria"));
                rec.setCategoria(cat);
                return rec;
            }
        }
    }
    return null;
}

public void actualizar(Recomendacion rec) throws SQLException {
    String sql = "UPDATE recomendaciones SET titulo = ?, descripcion = ?, categoria_id = ? WHERE id = ?";
    try (PreparedStatement stmt = con.prepareStatement(sql)) {
        stmt.setString(1, rec.getTitulo());
        stmt.setString(2, rec.getDescripcion());
        stmt.setInt(3, rec.getCategoria().getId());
        stmt.setInt(4, rec.getId());
        stmt.executeUpdate();
    }
}

public void eliminar(int id) throws SQLException {
    String sql = "DELETE FROM recomendaciones WHERE id = ?";
    try (PreparedStatement stmt = con.prepareStatement(sql)) {
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }
}
public Usuario getUsuario() {
    return usuario;
}

public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
}

}

