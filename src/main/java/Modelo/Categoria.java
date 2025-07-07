/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 * Representa una categoría dentro del sistema RecommendApp.
 * Cada categoría sirve para clasificar las recomendaciones creadas por los usuarios.
 * @author Gabriela Solange Gonzalez Román
 * @author Leandro Rene Palacios Moriel
 * @version 1.0 
 * @since 2025‑06‑12
 */
public class Categoria {
    
    /** Identificador único de la categoría. */
    private int id;

    /** Nombre de la categoría. */
    private String nombre;

    /** Constructor por defecto. */
    public Categoria() {}

    /**
     * Constructor con todos los atributos.
     *
     * @param id     identificador único de la categoría
     * @param nombre nombre o título de la categoría
     */
    public Categoria(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
}
