<%-- 
    Document   : ayuda
    Created on : 23 jun 2025, 09:00:11
    Author     : User
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

    .faq-question {
        margin: 14px 0;
        font-weight: bold;
        cursor: pointer;
        position: relative;
        padding: 12px 16px;
        border-radius: 6px;
        background: #e2e6f7;
        color: #333;
    }

    .faq-answer {
        display: none;
        padding: 10px 20px;
        background: #ffffff;
        margin-bottom: 10px;
        border-left: 4px solid #6C63FF;
        border-radius: 0 0 8px 8px;
    }

    .faq-question.active + .faq-answer {
        display: block;
    }
</style>

<div class="container">
    <h1>Ayuda</h1>
    <p>¿Tienes dudas? Aquí tienes información útil.</p>

    <div class="accordion">

        <!-- Acordeón 1 -->
        <div class="accordion-item">
            <div class="accordion-header">Preguntas Frecuentes</div>
            <div class="accordion-content">

                <div class="faq-question">¿Cómo me registro?</div>
                <div class="faq-answer">Haz clic en "Registrarse" desde la página de inicio y completa tus datos.</div>

                <div class="faq-question">¿Cómo inicio sesión?</div>
                <div class="faq-answer">Usa tu correo y contraseña en el formulario de inicio.</div>

                <div class="faq-question">¿Cómo publico una recomendación?</div>
                <div class="faq-answer">Haz clic en el botón "+ Nueva Recomendación" y completa el formulario.</div>

                <div class="faq-question">¿Dónde puedo ver mis recomendaciones?</div>
                <div class="faq-answer">Accede a tu <a href="PerfilServlet">perfil</a>.</div>

                <div class="faq-question">¿Cómo cierro sesión?</div>
                <div class="faq-answer">Desde el menú (⋮), selecciona "Cerrar sesión".</div>
            </div>
        </div>

        <!-- Acordeón 2 -->
        <div class="accordion-item">
            <div class="accordion-header">¿Necesitas más ayuda?</div>
            <div class="accordion-content">
                <p>Puedes contactarnos al correo: <strong>soporte@recommendapp.com</strong></p>
            </div>
        </div>

    </div>
</div>
<%@ include file="footer.jsp" %>

<script>
    document.addEventListener('DOMContentLoaded', () => {
        // Acordeones principales
        const headers = document.querySelectorAll('.accordion-header');

        headers.forEach(header => {
            header.addEventListener('click', () => {
                const content = header.nextElementSibling;

                // Cierra todos los demás
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

                    // Si el contenido es largo y se sale de pantalla
                    const rect = content.getBoundingClientRect();
                    const isOffScreen = rect.bottom > window.innerHeight;
                    if (isOffScreen) {
                        content.scrollIntoView({ behavior: "smooth", block: "start" });
                    }
                }
            });
        });

        // Subacordeones de FAQ
        const faqQuestions = document.querySelectorAll('.faq-question');
        faqQuestions.forEach(question => {
            question.addEventListener('click', () => {
                // Alternar solo el actual
                question.classList.toggle('active');

                // Cerrar los demás si quieres:
                faqQuestions.forEach(q => {
                    if (q !== question) q.classList.remove('active');
                });
            });
        });
    });
</script>


