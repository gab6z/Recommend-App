/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Datos;

import java.sql.*;

/**
 * Establece la conexión a la base de datos PostgreSQL utilizada por el sistema RecommendApp.
 * Proporciona un método estático para conectar a la base de datos.
 * @author Gabriela Solange Gonzalez Román
 * @author Leandro Rene Palacios Moriel
 * @version 1.0
 * @since 2025-06-12
 */
public class ConexionPostgres {
    
    /**
     * Establece y devuelve una conexión activa a la base de datos PostgreSQL.
     *
     * @return un objeto {@link Connection} si la conexión fue exitosa, o <code>null</code> si falló.
     */
 public static Connection conectar() {
        Connection con = null; 
        try {

            // Cargar el driver de PostgreSQL
            Class.forName("org.postgresql.Driver");

            // Establecer la conexión con la base de datos
            con = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/muro_recomendaciones", //url de la base de datos
                    "postgres", //usuario
                    "0321"); //contrasena
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error al conectar: " + e.getMessage());
        }
        return con;
    }
}
