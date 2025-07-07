
<%--
  Archivo: acerca.jsp
  Descripción: Página informativa del sistema RecommendApp que explica su propósito, objetivo, equipo desarrollador y tecnologías usadas.
  Autor(es): Gabriela Solange Gonzalez Román, Leandro Rene Palacios Moriel
  Materia: Construcción de Software
  Semestre: 6to semestre de Ingeniería de Software
  Universidad: Facultad de Ciencias Matemáticas y Física
  Tecnologías utilizadas: JSP, HTML, CSS, JavaScript
  Fecha: Junio 2025
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="encabezado.jsp" %>

<style>
    .container {
        max-width: 90%;
        margin: 40px auto;
        padding: 20px;
        background: #ffffff;
        border-radius: 12px;
        box-shadow: 0 4px 12px rgba(0,0,0,0.1);
    }

    h1, p {
        text-align: center;
        color: #333;
    }

    .accordion {
        margin-top: 30px;
    }

    .accordion-item {
        border: 1px solid #ccc;
        border-radius: 8px;
        margin-bottom: 12px;
        background: #f2f5f7;
        overflow: hidden;
    }

    .accordion-header {
        background-color: #6C63FF;
        color: white;
        cursor: pointer;
        padding: 16px 24px;
        font-size: 18px;
        font-weight: bold;
        border: none;
        outline: none;
        transition: background-color 0.3s ease;
        display: flex;
        justify-content: space-between;
        align-items: center;
    }

    .accordion-header:hover {
        background-color: #5a53d4;
    }

    .accordion-header.active::after {
        content: "▲";
    }

    .accordion-header::after {
        content: "▼";
        font-size: 14px;
    }

    .container.accordion-content {
        max-height: 0;
        overflow: hidden;
        transition: max-height 0.4s ease;
        background: #f9f9f9;
        padding: 0 24px;
        font-size: 16px;
        color: #333;
    }

    .accordion-content.open {
        overflow: auto;
    }

    ul {
        padding-left: 20px;
        margin-top: 14px;
    }

    ul li {
        margin-bottom: 8px;
    }
</style>

<div class="container">
    <h1>Acerca de RecommendApp</h1>
    <p>Conoce más sobre esta aplicación.</p>

    <div class="accordion">

        <!-- ¿Qué es RecommendApp? -->
        <div class="accordion-item">
            <div class="accordion-header">¿Qué es RecommendApp?</div>
            <div class="accordion-content">
                <p>
                    Es una plataforma web donde los usuarios pueden compartir y descubrir recomendaciones de <strong>libros, series, películas</strong> y <strong>aplicaciones educativas</strong>. Fue desarrollada con fines académicos en el entorno de Desarrollo NetBeans.
                </p>
            </div>
        </div>

        <!-- Objetivo del proyecto -->
        <div class="accordion-item">
            <div class="accordion-header">Objetivo de RecommendApp</div>
            <div class="accordion-content">
                <p>Promover la colaboración y el intercambio de conocimientos entre personas a través de sugerencias útiles y confiables.</p>
            </div>
        </div>

        <!-- Créditos y tecnologías -->
        <div class="accordion-item">
            <div class="accordion-header">Créditos y Tecnologías</div>
            <div class="accordion-content">
                <ul>
                    <li><strong>Desarrolladores:</strong> Gabriela Solange Gonzalez Román y Leandro Rene Palacios Moriel</li>
                    <li><strong>Materia:</strong> Construcción de Software</li>
                    <li><strong>Semestre:</strong> 6to semestre de Ingeniería de Software</li>
                    <li><strong>Universidad:</strong> Facultad de Ciencias Matemáticas y Física</li>
                    <li><strong>Lenguaje:</strong> Java (Maven)</li>
                    <li><strong>Base de Datos:</strong> PostgreSQL version 17.5-1</li>
                    <li><strong>Frontend:</strong> HTML, CSS (inline), JSP</li>
                    <li><strong>Arquitectura:</strong> MVC</li>
                </ul>
            </div>
        </div>

    </div>
</div>
<%@ include file="footer.jsp" %>

<script>
    /**
    * Espera a que el DOM esté completamente cargado y luego
    * inicializa el comportamiento de un acordeón interactivo.
    * - Cada encabezado de acordeón (.accordion-header) responde al clic.
    * - Al hacer clic, se despliega su contenido y se cierran los demás.
    */
    document.addEventListener('DOMContentLoaded', () => {
        const headers = document.querySelectorAll('.accordion-header');

        headers.forEach(header => {
            header.addEventListener('click', () => {
                const content = header.nextElementSibling;

                // Cierra otros
                document.querySelectorAll('.accordion-content').forEach(c => {
                    if (c !== content) {
                        c.style.maxHeight = null;
                        c.classList.remove('open');
                        c.previousElementSibling.classList.remove('active');
                    }
                });

                // Toggle actual
                header.classList.toggle('active');
                if (content.style.maxHeight) {
                    content.style.maxHeight = null;
                    content.classList.remove('open');
                } else {
                    content.style.maxHeight = content.scrollHeight + 'px';
                    content.classList.add('open');
                }
            });
        });
    });
</script>






