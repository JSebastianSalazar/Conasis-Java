<%-- 
    Document   : ErrorPage400
    Created on : 10/09/2016, 04:32:37 PM
    Author     : sebas
--%>

<%@page import="beans.Usuario"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="DAO.Instructor"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <title>Error</title>
     <!-- importando materizalizecss CSS por medio de CDN -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.6/css/materialize.min.css">
    <link href='https://fonts.googleapis.com/css?family=Varela+Round' rel='stylesheet' type='text/css'>
     <!-- importando iconos materializecss por medio del CDN-->
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link href="css/styles.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="css/reset.css">
    <link rel='stylesheet prefetch' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css'>
     <!-- importando materizalizecss CSS por medio de CDN -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.6/css/materialize.min.css">
    <link rel="stylesheet" href="css/style.css">
     <!--Menu -->
        <link href="css/normalize.css" rel="stylesheet" type="text/css"/>
        <link href="css/style2.css" rel="stylesheet" type="text/css"/>
    <style>
            .ftft{
                position: fixed;
                background-color: #009688;
                bottom: 0;
                z-index: 100;/* Depende el valor segun las capas flotantes que tengas */
                right: 0%;
                width: 100%;

            }  
            .a{font-size: 17px;}
        </style>
        
</head>
<% Instructor dao = new Instructor();
        List<Usuario> list = new ArrayList();
        if (session.getAttribute("usuario") != null) {
                String nombre = "" + session.getAttribute("usuario");
    %>

    <body style="background: #f2f2f2">
        <nav class="cabecera" >
            <div class="nav-wrapper" style="background-color: transparent">
                <img src="imagenes/conasisLogo.png" alt=""  class="sidebar-toggle" style="height: 80px; width: 130px; margin-left: 5%; cursor: pointer"/>
               <a  class="brand-logo center">Lo sentimos</a>
                </div>
            </div>
        </nav>
        <div class="linea col s12"></div>
        <div class="sidebar-overlay"></div>

<!-- Material sidebar -->
<aside id="sidebar" class="sidebar sidebar-default open" role="navigation">
         <!-- Sidebar header -->
    <div class="sidebar-header header-cover" style="margin-bottom: 0px; background-color: #0090A5;opacity: 0.9; filter: alpha(opacity=90);"><!--url(http://2.bp.blogspot.com/-2RewSLZUzRg/U-9o6SD4M6I/AAAAAAAADIE/voax99AbRx0/s1600/14%2B-%2B1%2B%281%29.jpg);             background-image:url(imagenes/conasisLogo.png); -->   
        <!-- Top bar <div class="top-bar" style="margin-top: -8px"></div>-->
        <center><a href="menuAdministrador.jsp"> <img src="imagenes/conasisLogo.png" alt="" style="width: 260px; height: 156px"/></a></center>
    </div>
    <div class="sidebar-header" style="height: 163px;">
        <!-- Top bar -->
        <div class="top-bar" ><center><p style="font-size: 16px"><%= session.getAttribute("tipo")%></p></center></div>
        <!-- Sidebar brand image -->
        <div class="sidebar-image">
            <img src="imagenes/foto1.jpg" alt="" />
        </div>
         <!-- Sidebar brand name -->
         <a data-toggle="dropdown" class="sidebar-brand" href="#settings-dropdown" style="position: relative;">
             <p style="font-size: 16px">  <%=nombre%></p>
            <b class="caret"></b>
        </a>
         </div>
    <!-- Sidebar navigation -->
    <ul class="nav sidebar-nav">
        <li class="dropdown">
            <ul id="settings-dropdown" class="dropdown-menu">
                <li><form action="SalidaAdmi">
            <a href="#" onclick="$(this).closest('form').submit()">
                <i class="sidebar-icon material-icons">power_settings_new</i>
                Salir
            </a></form>
        </li>
            </ul>
           
        </li>
    </ul>
    
        <!-- Sidebar navigation -->
    <ul class="nav sidebar-nav">
        <li class="divider"></li>
        <li class="dropdown">
            <a class="ripple-effect dropdown-toggle" href="#" data-toggle="dropdown">
                Administrar Usuarios
                <b class="caret"></b>
            </a>
            <ul class="dropdown-menu">
                <li>
                    <a href="registroInstructores.jsp" tabindex="-1">
                        Instructores
                        <span class="sidebar-badge"><i class="material-icons">send</i></span>
                    </a>
                </li>
                <li>
                    <a href="RegistroSecretaria.jsp" tabindex="-1">
                        Secretaria
                        <span class="sidebar-badge"><i class="material-icons">send</i></span>
                    </a>
                </li>
                <li>
                    <a href="RegistroAprendizIns.jsp" tabindex="-1">
                        Aprendices
                        <span class="sidebar-badge"><i class="material-icons">send</i></span>
                    </a>
                </li>
            </ul>
        </li>
        <li class="divider"></li>
        <li class="dropdown">
            <a class="ripple-effect dropdown-toggle" href="#" data-toggle="dropdown">
                Formación
                <b class="caret"></b>
            </a>
            <ul class="dropdown-menu">
                <li>
                    <a href="ambiente.jsp" tabindex="-1">
                        Ambientes
                        <span class="sidebar-badge"><i class="material-icons">send</i></span>
                    </a>
                </li>
                <li>
                    <a href="ficha.jsp" tabindex="-1">
                        Fichas
                        <span class="sidebar-badge"><i class="material-icons">send</i></span>
                    </a>
                </li>
                <li>
                    <a href="prograXcompe.jsp" tabindex="-1">
                        Programas y competencias
                        <span class="sidebar-badge"><i class="material-icons">send</i></span>
                    </a>
                </li>
                <li>
                    <a href="programacion.jsp" tabindex="-1">
                        Programación
                        <span class="sidebar-badge"><i class="material-icons">send</i></span>
                    </a>
                </li>
            </ul>
        </li>
        <li class="divider"></li>
        <li class="dropdown">
            <a class="ripple-effect dropdown-toggle" href="#" data-toggle="dropdown">
                Centro de Asistencia
                <b class="caret"></b>
            </a>
            <ul class="dropdown-menu">
                <li>
                    <a href="#modalCDxI" id="competenciasDictadasXintstructor"  tabindex="-1">
                        Ingreso y salida
                        <span class="sidebar-badge"><i class="material-icons">send</i></span>
                    </a>
                </li>
                <li>
                    <a href="inasistencia.jsp" tabindex="-1">
                        Justificar falta
                        <span class="sidebar-badge"><i class="material-icons">send</i></span>
                    </a>
                </li>
                <li>
                    <a href="infoAsistencia.jsp" tabindex="-1">
                        información de asistencia
                        <span class="sidebar-badge"><i class="material-icons">send</i></span>
                    </a>
                </li>
            </ul>
        </li>
        <li class="divider"></li>
        <li>
            <a href="#">
                <i class="sidebar-icon material-icons" >help outline</i>
                Acerca de
            </a>
        </li>
    </ul>
      
</aside>
            
            
        <div id="cntntMsj"></div>
    
    <div class="container">
        <div class="boo-wrapper">
            <div class="boo">
               
                <div class="face"></div>
            </div>
            <div class="shadow"></div>

            <h1>Oops!</h1>
            <p>
                Ha ocurrido un error inesperado
                <br />
                Nuestro equipo de trabajo técnico trabajará en esto.
                <br />
                Vuelve al inicio.
            </p>
        </div>
    </div>
        
                             <footer class="page-footer">
            <div class="ftft">
                <div class="footer-copyright">
                    <div class="container">
                        © 2014 Copyright 
                        <a class="grey-text text-lighten-4 right" href="#!">CONASIS V.1.0 2016 </a>
                    </div>
                </div>
            </div>
        </footer>
         <%} else {
                session.invalidate();
                response.sendRedirect("index.html");
            }%> 
              <!--importación de Jquery por medio de CDN-->
            <script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
            <!--importacion de materializecss javaScript por medio de CDN-->
            <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.6/js/materialize.min.js"></script>
            
            <!--menu-->
        <script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js'></script>
        <script src="js/index2.js" type="text/javascript"></script>

</body>

</html>
