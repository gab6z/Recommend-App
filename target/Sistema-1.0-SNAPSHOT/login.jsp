<%-- 
    Document   : login
    Created on : 16 jun 2025, 09:28:21
    Author     : Gabriela Gonzalez Roman 
--%>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Iniciar Sesión</title>
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            background: #e8f0fe;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            margin: 0;
        }

        .logo {
            width: 100px;
            height: 100px;
            margin-bottom: 10px;
        }

        .app-title {
            text-align: center;
            font-size: 20px;
            color: black;
            margin-bottom: 20px;
            font-weight: bold;
        }

        .form-container {
            background: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 0 15px rgba(0,0,0,0.1);
            width: 350px;
        }

        input, button {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
        }

        button {
            background: #007bff;
            color: white;
            border: none;
            cursor: pointer;
        }

        button:hover {
            background: #0056b3;
        }

        .register-link {
            text-align: center;
            margin-top: 10px;
        }

        footer {
            margin-top: 30px;
            text-align: center;
            font-size: 13px;
            color: #555;
            padding: 10px;
            max-width: 600px;
        }
    </style>
</head>
<body>

    <img src="imagenes/icono.png" alt="Icono de RecomendApp" class="logo">

    <!-- Título de bienvenida -->
    <div class="app-title">Bienvenido/a a tu lugar favorito</div>

    <!-- Formulario de inicio de sesión -->
    <div class="form-container">
        <h2>Iniciar Sesión</h2>
        <% if (request.getAttribute("error") != null) { %>
        <p style=" color: red; text-align:center;"><%= request.getAttribute("error") %></p>
        <% }%>
        <form action="LoginServlet" method="post">
            <input type="text" name="usuario" placeholder="Usuario" required>
            <input type="password" name="contraseña" placeholder="Contraseña" required>
            <button type="submit">Ingresar</button>
        </form>
        <div class="register-link">
            ¿No tienes cuenta? <a href="registro.jsp">Regístrate</a>
        </div>
    </div>

    <footer>
        © 2025 Elaborado por: Gabriela Gonzalez Román y Leandro Palacios Moriel
        Estudiantes de la Universidad de Guayaquil
        Facultad de Ciencias Matemáticas y Físicas.  
        Proyecto de Construcción de Software.
    </footer>
</body>
</html>

