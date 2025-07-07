<%--
  Archivo: editar_recomendacion.jsp
  Descripción: Página con formulario en donde se ingresan los nuevos datos de la recomendacion.
  Autor(es): Gabriela Solange Gonzalez Román, Leandro Rene Palacios Moriel
  Materia: Construcción de Software
  Semestre: 6to semestre de Ingeniería de Software
  Universidad: Facultad de Ciencias Matemáticas y Física
  Tecnologías utilizadas: JSP, HTML, CSS, JavaScript
  Fecha: Junio 2025
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="Modelo.*, java.util.List" %>
<%
    Recomendacion rec = (Recomendacion) request.getAttribute("recomendacion");
    List<Categoria> categorias = (List<Categoria>) request.getAttribute("categorias");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Editar Recomendación</title>
    <link href="https://fonts.googleapis.com/css2?family=Segoe+UI:wght@400;700&display=swap" rel="stylesheet">
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            background: #f2f5f7;
            margin: 0;
        }

        header {
            background: #6C63FF;
            padding: 15px 20px;
            color: white;
            display: flex;
            justify-content: space-between;
            align-items: center;
            position: relative;
        }

        .header-left {
            font-weight: bold;
            font-size: 50px;
            margin: 20px;
            padding-left: 20px;
        }

        .menu-button {
            background: none;
            border: none;
            color: white;
            font-size: 30px;
            cursor: pointer;
        }

        .menu-dropdown {
            display: none;
            position: absolute;
            top: 60px;
            right: 20px;
            background: white;
            color: black;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
            z-index: 100;
            width: 160px;
        }

        .menu-dropdown a {
            display: block;
            padding: 12px;
            text-decoration: none;
            color: black;
        }

        .menu-dropdown a:hover {
            background: #f1f1f1;
        }

        .container {
            max-width: 800px;
            margin: 40px auto;
            background: white;
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0 0 15px rgba(0,0,0,0.1);
        }

        h2 {
            text-align: center;
            color: #333;
            margin-bottom: 30px;
        }

        form p {
            margin: 15px 0;
        }

        input[type="text"], textarea, select {
            width: 100%;
            padding: 10px;
            border-radius: 6px;
            border: 1px solid #ccc;
            font-size: 16px;
            box-sizing: border-box;
        }

        button {
            background: #6C63FF;
            color: white;
            padding: 12px 20px;
            border: none;
            border-radius: 8px;
            cursor: pointer;
            font-size: 16px;
            margin-top: 20px;
        }

        button:hover {
            background: #574fd6;
        }
    </style>
    <script>
        /**
        * Muestra u oculta el menú hamburguesa.
        * ─ Si #menu está visible (display: block) lo oculta.  
        * ─ Si está oculto (display ≠ block) lo hace visible.
        */
        function toggleMenu() {
            var menu = document.getElementById("menu");
            menu.style.display = menu.style.display === "block" ? "none" : "block";
        }
        /**
         * Cierra el menú cuando el usuario hace clic en cualquier parte
         * que NO sea el propio menú (#menu) ni el botón que lo abre (#menuButton).
         *
         * @param {MouseEvent} e Evento de clic lanzado por el documento.
         */
        document.addEventListener('click', function(e) {
            var menu = document.getElementById("menu");
            var btn = document.getElementById("menuButton");
            if (menu && btn && !menu.contains(e.target) && !btn.contains(e.target)) {
                menu.style.display = "none";
            }
        });
    </script>
</head>
<body>

<header>
    <div class="header-left">RecomendApp</div>
    <div>
        <button class="menu-button" onclick="toggleMenu()" id="menuButton">⋮</button>
        <div class="menu-dropdown" id="menu">
            <a href="inicio.jsp">Inicio</a>
            <a href="perfil.jsp">Perfil</a>
            <a href="ayuda.jsp">Ayuda</a>
            <a href="LoginServlet">Cerrar Sesión</a>
        </div>
    </div>
</header>

<div class="container">
    <h2>Editar Recomendación</h2>
    <form action="EditarRecomendacionServlet" method="post">
        <input type="hidden" name="id" value="<%= rec.getId() %>">

        <p><label>Título:</label>
            <input type="text" name="titulo" value="<%= rec.getTitulo() %>" required>
        </p>

        <p><label>Descripción:</label>
            <textarea name="descripcion" required rows="5"><%= rec.getDescripcion() %></textarea>
        </p>

        <p><label>Categoría:</label>
            <select name="categoria" required>
                <% for (Categoria cat : categorias) { %>
                    <option value="<%= cat.getId() %>" <%= (rec.getCategoria().getId() == cat.getId()) ? "selected" : "" %>>
                        <%= cat.getNombre() %>
                    </option>
                <% } %>
            </select>
        </p>

        <button type="submit">Guardar Cambios</button>
    </form>
</div>

</body>
</html>


