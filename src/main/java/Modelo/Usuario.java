/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 * Representa a un usuario del sistema RecommendApp.
 * Contiene la información de autenticación y los datos personales que
 * se muestran en las recomendaciones y comentarios.
 * @author Gabriela Solange Gonzalez Román
 * @author Leandro Rene Palacios Moriel
 * @version 1.0 
 * @since 2025‑06‑12
 */
public class Usuario {
    
    /** Identificador único del usuario. */
    private int id;

    /** Nombre de inicio de sesión. */
    private String nombreUsuario;

    /** Contraseña (almacenada preferentemente con hash en la base de datos). */
    private String contraseña;

    /** Apodo o alias que se muestra públicamente. */
    private String apodo;

    /** Nombre completo del usuario. */
    private String nombreCompleto;

    /** Correo electrónico de contacto. */
    private String correo;

    /** Número de teléfono. */
    private String telefono;

    /** Fecha de registro en formato ISO 8601 (AAAA‑MM‑DD). */
    private String fechaRegistro;

    /** Constructor por defecto. */
    public Usuario() {}

    /**
     * Constructor con todos los campos.
     *
     * @param id             identificador del usuario
     * @param nombreUsuario  nombre de inicio de sesión
     * @param contraseña     contraseña del usuario
     * @param apodo          alias visible
     * @param nombreCompleto nombre completo
     * @param correo         correo electrónico
     * @param telefono       número de teléfono
     * @param fechaRegistro  fecha de alta
     */
    public Usuario(int id, String nombreUsuario, String contraseña, String apodo, String nombreCompleto, String correo, String telefono, String fechaRegistro) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.contraseña = contraseña;
        this.apodo = apodo;
        this.nombreCompleto = nombreCompleto;
        this.correo = correo;
        this.telefono = telefono;
        this.fechaRegistro = fechaRegistro;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombreUsuario() { return nombreUsuario; }
    public void setNombreUsuario(String nombreUsuario) { this.nombreUsuario = nombreUsuario; }

    public String getContraseña() { return contraseña; }
    public void setContraseña(String contraseña) { this.contraseña = contraseña; }

    public String getApodo() { return apodo; }
    public void setApodo(String apodo) { this.apodo = apodo; }

    public String getNombreCompleto() { return nombreCompleto; }
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(String fechaRegistro) { this.fechaRegistro = fechaRegistro; }
      
}
