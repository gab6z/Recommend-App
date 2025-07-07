/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.Date;

/**
 * Representa una recomendación registrada en el sistema RecommendApp.
 * Cada recomendación está vinculada a una categoría y un usuario, e incluye un
 * título, descripción y la fecha de publicación.
 * @author Gabriela Solange Gonzalez Román
 * @author Leandro Rene Palacios Moriel
 * @version 1.0 
 * @since 2025‑06‑12
 */
public class Recomendacion {
    
    /** Identificador único de la recomendación. */
    private int id;

    /** Título descriptivo de la recomendación. */
    private String titulo;

    /** Detalle o contenido de la recomendación. */
    private String descripcion;

    /** Categoría asociada a la recomendación. */
    private Categoria categoria;

    /** Usuario que publicó la recomendación. */
    private Usuario usuario;

    /** Fecha en que se publicó la recomendación. */
    private Date fecha;

    /** Constructor por defecto. */

    public Recomendacion() {}


    /**
     * Constructor con todos los atributos.
     *
     * @param id          identificador único
     * @param titulo      título de la recomendación
     * @param descripcion contenido o detalle
     * @param categoria   categoría a la que pertenece
     * @param usuario     usuario autor
     * @param fecha       fecha de publicación
     */
    public Recomendacion(int id, String titulo, String descripcion, Categoria categoria, Usuario usuario, Date fecha) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.usuario = usuario;
        this.fecha = fecha;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }
    
}
