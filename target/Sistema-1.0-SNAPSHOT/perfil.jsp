<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*, Modelo.*" %>
<%@ page import="DAO.RecomendacionDAO" %>
<%@ page import="java.sql.*" %>

<%@ include file="encabezado.jsp" %>

<%
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    List<Recomendacion> misRecomendaciones = (List<Recomendacion>) request.getAttribute("misRecomendaciones");
%>

<html>
<head>
    <title>Perfil de <%= usuario != null ? usuario.getApodo() : "Usuario" %></title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            background: #f0f2f5;
            margin: 0;
            padding: 0;
        }

        .breadcrumb-container {
            display: flex;
            flex-direction: column;
            align-items: flex-start;
            margin-left: 30px;
            padding-top: 20px;     
        }

        .breadcrumb-container img {
            width: 80px;
            height: 80px;
            margin-bottom: 10px;
        }

        .breadcrumb {
            font-size: 14px;
            color: #666;
        }

        .breadcrumb a {
            color: #6C63FF;
            text-decoration: none;
            margin-right: 5px;
        }

        .perfil-container {
            display: flex;
            padding: 20px;
            gap: 20px;
            align-items: flex-start;
        }

        .perfil-izquierda {
            flex: 1;
            background: white;
            border-radius: 10px;
            padding: 20px;
            box-shadow: 0 0 8px rgba(0, 0, 0, 0.1);
        }

        .perfil-derecha {
            flex: 2;
            display: flex;
            flex-direction: column;
        }

        .publicaciones-titulo {
            display: flex;
            align-items: center;
            font-size: 24px;
            font-weight: bold;
            margin-bottom: 20px;
            color: #333;
        }

        .publicaciones-titulo i {
            margin-right: 10px;
            color: #6C63FF;
        }

        .recomendaciones-grid {
            display: grid;
            grid-template-columns: repeat(2, 1fr);
            gap: 15px;
        }

        .recomendacion-card {
            background: white;
            padding: 15px;
            border-radius: 10px;
            box-shadow: 0 2px 5px rgba(0,0,0,0.1);
        }

        .recomendacion-card h3 {
            margin: 0 0 10px 0;
            color: #333;
        }

        .recomendacion-card p {
            color: #555;
        }

        .acciones {
            margin-top: 10px;
        }

        .acciones form {
            display: inline;
        }

        .acciones button {
            margin-right: 10px;
            padding: 5px 10px;
            background: #6C63FF;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .acciones button:hover {
            background: #574fd6;
        }
        .breadcrumb-container img {
         width: 120px;
         height: 120px;
         margin-bottom: 10px;
        }

    </style>
</head>
<body>

    <div class="breadcrumb-container">
        <img src="imagenes/usuario.png" alt="Icono Perfil" />
        <div class="breadcrumb">
            <a href="ServletInicio">Inicio</a> / <a href="PerfilServlet">Perfil</a>
        </div>
    </div>

    <div class="perfil-container">
        <!-- Parte izquierda: datos del usuario -->
        <div class="perfil-izquierda">
            <h3>Detalles de Usuario</h3>
            <% if (usuario != null) { %>
                <p><strong>Apodo:</strong> <%= usuario.getApodo() != null ? usuario.getApodo() : "No disponible" %></p>
                <p><strong>Nombre completo:</strong> <%= usuario.getNombreCompleto() != null ? usuario.getNombreCompleto() : "No disponible" %></p>
                <p><strong>Correo:</strong> <%= usuario.getCorreo() != null ? usuario.getCorreo() : "No disponible" %></p>
                <p><strong>Teléfono:</strong> <%= usuario.getTelefono() != null ? usuario.getTelefono() : "No disponible" %></p>
                <p><strong>Usuario:</strong> <%= usuario.getNombreUsuario() != null ? usuario.getNombreUsuario() : "No disponible" %></p>
            <% } else { %>
                <p>No se encontró información del usuario.</p>
            <% } %>
        </div>

        <!-- Parte derecha: publicaciones -->
        <div class="perfil-derecha">
            <div class="publicaciones-titulo">
                <i class="fas fa-thumbtack"></i> Recomendaciones
            </div>

            <div class="recomendaciones-grid">
                <% if (misRecomendaciones != null && !misRecomendaciones.isEmpty()) {
                    for (Recomendacion rec : misRecomendaciones) { %>
                        <div class="recomendacion-card">
                            <h3><%= rec.getTitulo() %></h3>
                            <p><%= rec.getDescripcion() %></p>
                            <p><strong>Categoría:</strong> <%= rec.getCategoria().getNombre() %></p>
                            <p><strong>Fecha:</strong> <%= rec.getFecha() %></p>

                            <div class="acciones">
                                <form action="EditarRecomendacionServlet" method="get">
                                    <input type="hidden" name="id" value="<%= rec.getId() %>">
                                    <button type="submit">✏️ Editar</button>
                                </form>
                                <form action="EliminarRecomendacionServlet" method="post" onsubmit="return confirm('¿Seguro que deseas eliminar esta publicación?');">
                                    <input type="hidden" name="id" value="<%= rec.getId() %>">
                                    <button type="submit">🗑️ Eliminar</button>
                                </form>
                            </div>
                        </div>
                <%  } 
                } else { %>
                    <p>No hay recomendaciones aún.</p>
                <% } %>
            </div>
        </div>
    </div>

<%@ include file="footer.jsp" %>

</body>
</html>
