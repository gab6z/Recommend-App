<%--
  Archivo: inicio.jsp
  Descripción: Muestra todas las recomendaciones y el inicio del sistema
  Autor(es): Gabriela Solange Gonzalez Román, Leandro Rene Palacios Moriel
  Materia: Construcción de Software
  Semestre: 6to semestre de Ingeniería de Software
  Universidad: Facultad de Ciencias Matemáticas y Física
  Tecnologías utilizadas: JSP, HTML, CSS, JavaScript
  Fecha: Junio 2025
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix= "c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix= "fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="encabezado.jsp" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Muro de Recomendaciones</title>
    <link href="https://fonts.googleapis.com/css2?family=Segoe+UI:wght@400;700&display=swap" rel="stylesheet">
    <style>
         body {
            font-family: 'Segoe UI', sans-serif;
            background: #f2f5f7;
            margin: 0;
        }
        .container {
            max-width: 1200px;
            margin: 20px auto;
            padding: 10px 20px;
        }
        .filtros {
            margin-bottom: 20px;
        }
        .filtros button {
            margin: 5px;
            padding: 8px 12px;
            border-radius: 20px;
            border: none;
            cursor: pointer;
            background: #ddd;
        }
        .filtros .activo {
            background: #6C63FF;
            color: white;
        }
        .recomendaciones {
         display: grid;
         grid-template-columns: repeat(4, 1fr); 
         gap: 20px;
        }

        .recomendacion {
         background: white;
         border-radius: 12px;
         box-shadow: 0 0 10px rgba(0,0,0,0.05);
         padding: 20px;
         width: 100%;
         box-sizing: border-box;
         }

        .recomendacion small {
            display: block;
            color: #666;
            margin-bottom: 8px;
        }
        .nueva-btn {
            background: #6C63FF;
            color: white;
            padding: 12px 20px;
            border: none;
            border-radius: 8px;
            cursor: pointer;
            font-size: 14px;
            margin-bottom: 30px;
        }
        .modal {
            display: none;
            position: fixed;
            top: 0; left: 0; right: 0; bottom: 0;
            background: rgba(0,0,0,0.4);
            justify-content: center;
            align-items: center;
        }
        .modal-content {
            background: white;
            padding: 30px;
            border-radius: 10px;
            width: 400px;
        }
        .modal-content input, .modal-content textarea, .modal-content select {
            width: 100%;
            margin: 10px 0;
            padding: 8px;
        }
        .modal-content button {
            background: #28a745;
            color: white;
            border: none;
            padding: 10px;
            cursor: pointer;
        }
        .categoria-badge {
            display: inline-block;
            padding: 4px 8px;
            border-radius: 12px;
            font-size: 12px;
            color: white;
            margin-right: 8px;
        }
        .Libros { background-color: #3498db; }
        .Series { background-color: #9b59b6; }
        .Películas { background-color: #e67e22; }
        .AppsEducativas { background-color: gray; }
    </style>
    <script>
     /**
     * Alterna la visibilidad del menú hamburguesa.
     * ‑ Si el elemento #menu está visible (display:block) lo oculta.
     * ‑ Si está oculto, lo muestra.
     */
        function toggleMenu() {
            var menu = document.getElementById("menu");
            menu.style.display = menu.style.display === "block" ? "none" : "block";
        }
     /**
     * Abre el modal de “Nueva Recomendación”.
     * Cambia el display del contenedor #modal a flex
     * para centrarlo (el CSS usa display:flex).
     */

        function showModal() {
            document.getElementById("modal").style.display = "flex";
        }
        
    /**
     * Cierra el modal restaurando display:none.
     */
        function closeModal() {
            document.getElementById("modal").style.display = "none";
        }
    /**
     * Cierra el menú desplegable si el usuario hace clic
     * fuera del menú (#menu) y del botón que lo abre (#menuButton).
     *
     * @param {MouseEvent} e - Evento de clic recibido desde el document.
     */
        document.addEventListener('click', function(e) {
            var menu = document.getElementById("menu");
            var btn = document.getElementById("menuButton");
            if (menu && btn && !menu.contains(e.target) && !btn.contains(e.target)) {
                menu.style.display = "none";
            }
        });
    /**
    * Registra los listeners de filtro al cargar la ventana y
    * muestra/oculta tarjetas según la categoría seleccionada.
    */
        window.onload = function() {
            document.querySelectorAll('.filtro-btn').forEach(btn => {
                btn.addEventListener('click', () => {
                    document.querySelectorAll('.filtro-btn').forEach(b => b.classList.remove('activo'));
                    btn.classList.add('activo');
                    const categoria = btn.getAttribute('data-categoria');
                    document.querySelectorAll('.recomendacion').forEach(card => {
                        const cat = card.getAttribute('data-categoria');
                        if (categoria === 'todas' || categoria === cat) {
                            card.style.display = 'block';
                        } else {
                            card.style.display = 'none';
                        }
                    });
                });
            });
        };
    </script>
</head>
<body>

<div class="container">
    <h1 style="text-align:center; color:#333; margin-bottom: 10px;">Muro de Recomendaciones</h1>
    <p style="text-align:center; font-size: 18px;">Bienvenida/o, <strong>${usuario.apodo}</strong></p>
    <form action="BuscarRecomendacionServlet" method="get" style="text-align:center; margin: 20px 0;">
        <input type="text" name="titulo" placeholder="Buscar recomendación por título..." style="padding:8px; width: 300px;">
        <button type="submit" style="padding:8px 16px; background:#6C63FF; color:white; border:none; border-radius:4px;">Buscar</button>
    </form>

    <!-- Botones de filtros para la app -->
    <div class="filtros">
        <form action="ServletInicio" method="get" style="display:inline;">
        <button class="filtro-btn activo" type="submit">Todas</button>
        </form>

        <button class="filtro-btn" data-categoria="Libros">Libros</button>
        <button class="filtro-btn" data-categoria="Series">Series</button>
        <button class="filtro-btn" data-categoria="Películas">Películas</button>
        <button class="filtro-btn" data-categoria="Apps Educativas">Apps Educativas</button>
    </div>
    <button class="nueva-btn" onclick="showModal()">+ Nueva Recomendación</button>

    <div class="recomendaciones">
       
   <c:choose>
       <c:when test="${not empty recomendaciones}">
           <c:forEach var="rec" items="${recomendaciones}">
               <c:set var="cat" value="${rec.categoria.nombre}" />
               <c:set var="catClase" value="${fn:replace(cat, ' ', '')}" />
               <c:set var="icono">
                   <c:choose>
                       <c:when test="${cat eq 'Libros'}"></c:when>
                       <c:when test="${cat eq 'Series'}"></c:when>
                       <c:when test="${cat eq 'Películas'}"></c:when>
                       <c:when test="${cat eq 'Apps Educativas'}"></c:when>
                       <c:otherwise>⭐</c:otherwise>
                   </c:choose>
               </c:set>
              <!-- Tarjeta de recomendación, con atributo personalizado para filtrado JS -->
               <div class="recomendacion" data-categoria="${cat}">
                   <strong>${rec.titulo}</strong>
                   <br></br>
                   <small>
                       <span class="categoria-badge ${catClase}">
                           ${icono} ${cat}
                       </span>
                       | <fmt:formatDate value="${rec.fecha}" pattern="dd/MM/yyyy" /> | 
                       <c:choose>
                           <c:when test="${not empty rec.usuario}">
                               ${rec.usuario.apodo}
                           </c:when>
                           <c:otherwise>
                               Anónimo
                           </c:otherwise>
                       </c:choose>
                   </small>
                   <p>${rec.descripcion}</p>
               </div>
           </c:forEach>
       </c:when>
       <c:otherwise>
           <div>No hay recomendaciones para mostrar.</div>
       </c:otherwise>
   </c:choose>
    </div>
</div>

    <!-- Formulario para publicar nueva recomendación. -->
<div class="modal" id="modal">
    <div class="modal-content">
        <form action="PublicarServlet" method="post">
            <h3>Crear Recomendación</h3>
            <input type="text" name="titulo" placeholder="Título" required>
            <textarea name="descripcion" placeholder="Descripción" required></textarea>
            <select name="categoria" required>
                <option value="">Seleccione categoría</option>
                <option value="Libros">Libros</option>
                <option value="Series">Series</option>
                <option value="Películas">Películas</option>
                <option value="Apps Educativas">Apps Educativas</option>
            </select>
            <button type="submit">Publicar</button>
            <button type="button" onclick="closeModal()">Cancelar</button>
        </form>
    </div>
</div>
<%@ include file="footer.jsp" %>

</body>
</html>
