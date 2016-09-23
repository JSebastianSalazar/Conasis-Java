<%-- 
    Document   : manual
    Created on : 14/09/2016, 04:23:09 PM
    Author     : sebas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="icon" type="image/png" href="imagenes/conasis2.png" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.7/css/materialize.min.css">
        <style>
            .ftft{
                position: fixed;
                background-color: #009688;
                bottom: 0;
                z-index: 100;/* Depende el valor segun las capas flotantes que tengas */
                right: 0%;
                width: 100%;

            }  

        </style>
    </head>

    <body>  <embed src="pdf/Manual de usuario CONASIS.pdf" width="100%" height="640px">
        <footer class="page-footer">
            <div class="ftft">
                <div class="footer-copyright">
                    <div class="container">
                        © 2016 Copyright
                        <a class="grey-text text-lighten-4 right" href="#!">CONASIS V.1.0 2016 </a>
                    </div>
                </div>
            </div>
        </footer>
    </body>
</html>
