<%-- 
    Document   : ambiente
    Created on : 08-sep-2016, 13:04:44
    Author     : Emerson
--%>

<%@page import="beans.Usuario"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="DAO.Instructor"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- importando materizalizecss CSS por medio de CDN -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.6/css/materialize.min.css">
        <!-- importando iconos materializecss por medio del CDN-->
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <title>Ambientes</title>
        <link href="css/styles.css" rel="stylesheet" type="text/css"/>
        <!-- importando el CSS de las alertas SweetAlert -->
        <link href="css/sweetalert.css" rel="stylesheet" type="text/css"/>
        <link href="//cdn.datatables.net/1.10.12/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css"/>
 <!--Menu -->
        <link href="css/normalize.css" rel="stylesheet" type="text/css"/>
        <link href="css/style2.css" rel="stylesheet" type="text/css"/>
         <style>
            footer.page-footer{
                background-color: #f2f2f2;
            }
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
               <a  class="brand-logo center">Ambientes</a>
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
            <img src="<%= session.getAttribute("foto")%>" alt="" />
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
            <div class="row container" style="margin-top: 2%; ">
                <div id="contenedorControles" style="  padding-bottom: 2px; border-left: 0px; border-right: 0px;">
                    <div class="row z-depth-1" style="background-color: #ffffff; padding-top:15px; padding-right: 10px; padding-left: 10px;">
                        <!-------------------  FORMULARIO FICHA -----------------------------------> 
                        <form action="" class="col s12 m12" id="contenedorFormularioAmbiente">
                            <div class="row" style="margin-bottom: 0px;">

                            <div class="input-field col s6">
                                <i class="material-icons prefix">done</i>
                                <input id="capacidad" type="text" class="validate" onKeypress="if (event.keyCode < 45 || event.keyCode > 57) event.returnValue = false;">
                                <label for="icon_prefix" id="lblcapacidad">Capacidad</label>
                            </div>
                            <div class="input-field col s6">
                                <i class="material-icons prefix">done</i>
                                <input id="tipoAmbiente" type="text" class="validate" onkeypress="return soloLetras(event);">
                                <label for="icon_prefix" id="lbltipoAmbiente">Tipo ambiente</label>
                            </div>
                        

                            <div class="row s12 " style="margin-bottom: 0px;">
                            <div class="input-field col s6" >
                                <i class="material-icons prefix">done</i>
                                <input id="suministros" type="text" class="validate"   onkeypress="return soloLetras(event);">
                                <label for="icon_prefix" id="lblsuministros">Suministros</label >
                            </div>

                            <div class="input-field col s6" style="margin-top: 0px;">
                                <i class="material-icons prefix">done</i>
                                <textarea id="observacion" class="materialize-textarea"class="validate"  onkeypress="return soloLetras(event);"></textarea>
                                <label for="icon_prefix" id="lblobservacion">Observacion</label>
                            </div>
                            </div>
                            </div>
                            <div class="row" style="margin-bottom: 10px;">
                                <div class="col s2"><h1></h1></div>
                                <div class="col s3"><button class="btn waves-effect light-blue accent-4 waves-light" type="button" name="cancelar" id="btnCancelarAmbiente"  onClick="location.href = 'menuAdministrador.jsp'">Cancelar</button></div>
                                <div class="col s2"><h1></h1></div>
                                <div class="col s3" style="padding-left: 10%"><button class="btn waves-effect light-blue accent-4 waves-light" type="button" name="guardar" id="btnGuardarAmbiente">Guardar</button></div>
                                <div class="col s3" style="padding-left: 10%"><button class="btn waves-effect light-blue accent-4 waves-light" type="button" name="modificar" id="btnModificarAmbiente" value="">Modificar</button></div>
                                <div class="col s2"><h1></h1></div>
                            </div>
                            
                        </form> <!-- FIN DEL FORMULARIO DE FICHA -->
                    </div>    
                    <!------>
                    <div id="contenedorTablas" style="border-left: 0px; border-right: 0px;"><!--Acá es la tabla de las fichas---->
                        <div class="row z-depth-2" style="border-top: #2196f3 1px solid; padding-top: 10px; margin-top: 5px; border-left: 0px; border-right: 0px;">
                            <div class="col s12" id="tblListaAmbiente">
                                <center>
                                    <img src="imagenes/loader.gif" alt="" style="height: 100px; width: 100px"/><br>
                                    <span style="font-size: 16px">Cargando...</span>
                                </center>
                            </div>

                        </div>
                    </div>
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
            <script src="js/materialize.js" type="text/javascript"></script><!--importando las funciones javaScript de materializecss -->
            <!--importacion de datatable javaScript por medio de CDN-->
            <script type="text/javascript" src="http://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>
            <!--Impòrtando el ajax-->
            <script src="js/ajaxAmbiente.js" type="text/javascript"></script>
            <!--importacion del javaScript de las Alertas SweetAlert-->
            <script src="js/sweetalert.min.js" type="text/javascript"></script>
<!--menu-->
        <script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js'></script>
        <script src="js/index2.js" type="text/javascript"></script>

    </body>
</html>
