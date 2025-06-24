<%-- 
    Document   : registro
    Created on : 16 jun 2025, 09:15:58
    Author     : Gabriela Gonzalez
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registro de Usuario</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        html, body {
            height: 100%;
            font-family: 'Segoe UI', sans-serif;
            background: #f0f2f5;
        }

        body {
            display: flex;
            flex-direction: column;
        }

        header, footer {
            width: 100%;
        }

        main {
            flex: 1;
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 40px 20px;
        }

        .container {
            display: flex;
            flex-direction: row;
            background: white;
            border-radius: 12px;
            box-shadow: 0 0 20px rgba(0,0,0,0.1);
            overflow: hidden;
            max-width: 900px;
            width: 100%;
        }

        .left-panel {
            background: #e9f0fa;
            padding: 40px;
            display: flex;
            justify-content: center;
            align-items: center;
            width: 40%;
        }

        .left-panel img {
            max-width: 100%;
            max-height: 300px;
        }

        .form-panel {
            padding: 50px;
            width: 60%;
        }

        .form-panel h2 {
            font-size: 28px;
            margin-bottom: 20px;
            text-align: center;
        }

        input, button {
            width: 100%;
            padding: 14px;
            margin: 12px 0;
            font-size: 15px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        button {
            background: #007bff;
            color: white;
            font-weight: bold;
            border: none;
            transition: background 0.3s ease;
        }

        button:hover {
            background: #0056b3;
        }

        p.error {
            color: red;
            text-align: center;
            margin-top: -5px;
        }

        @media (max-width: 768px) {
            .container {
                flex-direction: column;
            }

            .left-panel, .form-panel {
                width: 100%;
                padding: 30px;
            }

            .left-panel {
                background: white;
            }
        }
    </style>
</head>
<body>

    <!-- Encabezado -->
    <header>
        <%@ include file="encabezado.jsp" %>
    </header>

    <!-- Contenido principal -->
    <main>
        <div class="container">
            <!-- Panel izquierdo con logo -->
            <div class="left-panel">
                <img src="imagenes/icono.png" alt="Logo">
            </div>

            <!-- Panel derecho con formulario -->
            <div class="form-panel">
                <h2>Crear Cuenta</h2>

                <% if(request.getAttribute("error") != null){ %>
                <p class="error"><%= request.getAttribute("error") %></p> 
                <% } %>

                <form action="RegistroServlet" method="post">
                    <input type="text" name="apodo" placeholder="Apodo" required>
                    <input type="text" name="nombre" placeholder="Nombre completo" required>
                    <input type="email" name="correo" placeholder="Correo electrónico" required>
                    <input type="text" name="telefono" placeholder="Teléfono" required>
                    <input type="text" name="usuario" placeholder="Nombre de usuario" required>
                    <input type="password" name="contraseña" placeholder="Contraseña" required>
                    <input type="password" name="confirmar" placeholder="Confirmar contraseña" required>
                    <button type="submit">Registrarse</button>
                </form>
            </div>
        </div>
    </main>

    <!-- Footer -->
    <footer>
        <%@ include file="footer.jsp" %>
    </footer>

</body>
</html>
