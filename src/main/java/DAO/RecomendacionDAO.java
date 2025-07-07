/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 * Representa a un usuario del sistema RecommendApp.
 * Contiene la información de autenticación y los datos personales que
 * se muestran en las recomendaciones y comentarios.
 * @author Gabriela Solange Gonzalez Román
 * @author Leandro Rene Palacios Moriel
 * @version 1.0 
 * @since 2025‑06‑14
 */
import java.sql.*;
import java.util.*;
import Modelo.*;

/**
 * DAO (<em>Data Access Object</em>) para la entidad {@link Modelo.Recomendacion}.
 */
public class RecomendacionDAO {

    /** Conexión activa a la base de datos. */
    private Connection con;
    /** Usuario asociado al DAO (opcional, para filtrar consultas). */
    public Usuario usuario;

    /**
     * Crea una instancia de {@code RecomendacionDAO}.
     *
     * @param con conexión a la base de datos que se utilizará para ejecutar las
     *            sentencias SQL
     */
    public RecomendacionDAO(Connection con) {
        this.con = con;
    }

    /**
     * Obtiene todas las recomendaciones registradas en la base.
     *
     * @return lista con todas las recomendaciones
     * @throws SQLException si ocurre un error al ejecutar la consulta
     */
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

    /**
     * Inserta una nueva recomendación en la base de datos.
     *
     * @param rec recomendación a persistir
     * @throws SQLException si ocurre un error durante la inserción
     */
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

    /**
     * Busca recomendaciones cuyo título contenga la cadena indicada (ignorando
     * mayúsculas/minúsculas).
     *
     * @param titulo texto parcial a buscar en el campo <em>titulo</em>
     * @return lista con las recomendaciones coincidentes
     * @throws SQLException si ocurre un error en la consulta
     */
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

    /**
     * Busca recomendaciones cuyo título o categoría contenga la cadena indicada.
     *
     * @param texto texto parcial a buscar en <em>titulo</em> o <em>nombre</em> de la categoría
     * @return lista con las recomendaciones coincidentes
     * @throws SQLException si ocurre un error en la consulta
     */
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

    /**
     * Lista todas las recomendaciones realizadas por un usuario concreto.
     *
     * @param usuarioId identificador del usuario
     * @return lista de recomendaciones del usuario
     * @throws SQLException si ocurre un error durante la consulta
     */
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

    /**
     * Obtiene una recomendación por su identificador.
     *
     * @param id identificador de la recomendación
     * @return la recomendación encontrada o {@code null} si no existe
     * @throws SQLException si ocurre un error en la consulta
     */
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

    /**
     * Actualiza los campos título, descripción y categoría de una recomendación existente.
     *
     * @param rec recomendación con los nuevos datos
     * @throws SQLException si ocurre un error durante la actualización
     */
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

    /**
     * Elimina una recomendación por su identificador.
     *
     * @param id identificador de la recomendación a eliminar
     * @throws SQLException si ocurre un error durante la eliminación
     */
public void eliminar(int id) throws SQLException {
    String sql = "DELETE FROM recomendaciones WHERE id = ?";
    try (PreparedStatement stmt = con.prepareStatement(sql)) {
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }
}

    /**
     * Devuelve el usuario asociado a este DAO.
     *
     * @return el usuario actual
     */
public Usuario getUsuario() {
    return usuario;
}


    /**
     * Asigna un usuario a este DAO; útil para filtrar consultas.
     *
     * @param usuario usuario que se asociará
     */
public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
}

}

