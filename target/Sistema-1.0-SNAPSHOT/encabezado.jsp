<%--
  Archivo: encabezado.jsp
  Descripción: Header de todas las paginas 
  Autor(es): Gabriela Solange Gonzalez Román, Leandro Rene Palacios Moriel
  Materia: Construcción de Software
  Semestre: 6to semestre de Ingeniería de Software
  Universidad: Facultad de Ciencias Matemáticas y Física
  Tecnologías utilizadas: JSP, HTML, CSS, JavaScript
  Fecha: Junio 2025
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- Estilos y encabezado común -->
<head>
    <meta charset="UTF-8">
    <title>RecomendApp</title>
    <link href="https://fonts.googleapis.com/css2?family=Segoe+UI:wght@400;700&display=swap" rel="stylesheet">
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            background: #f2f5f7;
            margin: 0;
        }
        header {
            background: #6C63FF;
            padding: 20px 20px;
            margin-right: 20px;
            color: white;
            display: flex;
            justify-content: space-between;
            align-items: center;
            position: relative;
        }
        .header-left {
            font-weight: bold;
            font-size: 36px;
            margin: 10px;
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
        .btn {
            padding: 8px 14px;
            border: none;
            border-radius: 6px;
            font-size: 14px;
            cursor: pointer;
        }
        .btn-editar {
            background-color: #007bff;
            color: white;
        }
        .btn-eliminar {
            background-color: #dc3545;
            color: white;
        }
        .btn:hover {
            opacity: 0.9;
        }
    </style>
    <script>
        function toggleMenu() {
            var menu = document.getElementById("menu");
            menu.style.display = menu.style.display === "block" ? "none" : "block";
        }
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
    <div class="header-left">RecommendApp</div>
    <div>
        <button class="menu-button" onclick="toggleMenu()" id="menuButton">⋮</button>
        <div class="menu-dropdown" id="menu">
            <a href="PerfilServlet">Perfil</a>
            <a href="ayuda.jsp">Ayuda</a>
            <a href="acerca.jsp">Acerca de</a>
            <a href="LogoutServlet">Cerrar sesión</a>
        </div>
    </div>
</header>

