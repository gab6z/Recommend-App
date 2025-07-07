/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 * Se encarga de gestionar las operaciones de acceso a datos relacionadas con las
 * categorías en la tabla categorias.
 * @author Gabriela Solange Gonzalez Román
 * @author Leandro Rene Palacios Moriel
 * @since 15/06/2025
 * @version 1.0
 */
import java.sql.*;
import java.util.*;
import Modelo.*;
/*DAO (Data Access Object) para la entidad {@link Modelo.Categoria}*/
public class CategoriaDAO {
    /** Conexión activa a la base de datos. */
    private Connection con;

     /**
     * Crea una instancia de {@code CategoriaDAO}.
     *
     * @param con conexión que se utilizará para ejecutar las sentencias SQL
     */
    public CategoriaDAO(Connection con) {
        this.con = con;
    }

     /**
     * Recupera todas las categorías almacenadas en la base de datos.
     *
     * @return lista con todas las categorías encontradas
     * @throws SQLException si ocurre un error al ejecutar la consulta
     */
    public List<Categoria> obtenerTodas() throws SQLException {
        List<Categoria> lista = new ArrayList<>();
        String sql = "SELECT * FROM categorias";
        try (PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                //instancia de categoria
                Categoria c = new Categoria();
                c.setId(rs.getInt("id"));
                c.setNombre(rs.getString("nombre"));
                lista.add(c);
            }
        }
        return lista;
    }
}
