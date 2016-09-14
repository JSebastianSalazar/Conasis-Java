
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <title>Menú</title>
        <meta charset="utf-8">

        <!-- <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
         
         <link href="css/Configuracion.css" rel="stylesheet" type="text/css"/>
         <link href="css/sweetalert.css" rel="stylesheet" type="text/css"/>
         
         
         <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <link href="css/registroGrupo.css" rel="stylesheet" type="text/css"/>
         <link href="css/slider.css" rel="stylesheet" type="text/css"/>-->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.7/css/materialize.min.css">
        <!-- importando el CSS del datable por medio de CDN -->
        <link href="http://cdn.datatables.net/1.10.12/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css"/>
        <link href="css/styles.css" rel="stylesheet" type="text/css"/>
        <!-- importando iconos materializecss por medio del CDN-->
        <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link href="css/normalize.css" rel="stylesheet" type="text/css"/>
        <link href="css/style2.css" rel="stylesheet" type="text/css"/>


    </head>

    <body>
        <% if (session.getAttribute("usuario") != null) {
                String nombre = "" + session.getAttribute("usuario");
                String tipo = "" + session.getAttribute("tipo");
        %>
        <!--Import jQuery before materialize.js
        <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
        <script type="text/javascript" src="js/materialize.min.js"></script>

        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
        <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script>
        <script src="js/js.js" type="text/javascript"></script>
        <link href="css/styles.css" rel="stylesheet" type="text/css"/>
        <link href="css/normalize.css" rel="stylesheet" type="text/css"/>
        <link href="css/style2.css" rel="stylesheet" type="text/css"/>-->
        <nav class="cabecera" >
            <div class="nav-wrapper" style="background-color: transparent">
                <img src="imagenes/conasisLogo.png" alt=""  class="sidebar-toggle" style="height: 80px; width: 130px; margin-left: 5%; cursor: pointer"/><!--<a><%= nombre%></a>-->
                <!-- <div class="right contenedorImgInstructor" style="background-color: transparent">
                     <img src="<%= session.getAttribute("foto")%>" alt="" class="circle responsive-img imgInstructor " data-delay="50" id="imglol"  style="height: 82px; width: 82px;" />
                     <form action="SalidaAdmi">
 
                         <div class="dropdown" id="dropdown">  
                             <a class="mainmenu toggle-login"><%= session.getAttribute("tipo")%></a>  
                             <div class="submenu">  
                                 <center>     <ul class="menuitems" id="main">  
                                     <li><a href="configuracion.jsp"style="width:  135px;" >Configurar</a></li>  
                                     <li><a href="#" style="width:  135px;">  Ayuda  </a></li>
                                     <li><a href="#" style="width:  135px;"> Diseño</a></li>
                                     <li><a href="#" onclick="$(this).closest('form').submit()" style="width:  135px;"> Salir </a></li>  
                                     </ul> </center> 
                             </div>  
                         </div>  
 
                     </form>-->
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
        <%
            if (tipo.equals("administrador")) {
        %>
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
        <%
        } else {
        %>
        <!-- Sidebar navigation -->
        <ul class="nav sidebar-nav">
            <li class="divider"></li>
            <li class="dropdown">
                <a class="ripple-effect dropdown-toggle" href="#" data-toggle="dropdown">
                    Aprendices
                    <b class="caret"></b>
                </a>
                <ul class="dropdown-menu">
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
                    Centro de Asistencia
                    <b class="caret"></b>
                </a>
                <ul class="dropdown-menu">
                    <li>
                        <a class="modal-trigger" href="#modalCDxI" id="competenciasDictadasXintstructor"  tabindex="-1">
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
        <%
            }
        %>

    </aside>
    <!-- Modal Structure -->
    <div id="modalCDxI" class="modal modal-fixed-footer" >
        <div class="modal-content">
            <h4>Asistencia</h4>
            <div id="tblcompetenciasDictadasXintstructor">

            </div>
        </div>
        <div class="modal-footer">
            <a href="asistencia.jsp" class=" modal-action modal-close waves-effect waves-green btn-flat" id="mdAceptarProgramacion">ACEPTAR</a>
            <button href="#!" class=" modal-action modal-close waves-effect waves-green btn-flat" id=""><span id="btnCambianteAmbiente">Cancelar</span></button>
        </div>
    </div><!--fin del modal -->

    <!--Lo que se mostrará-->

    <div class="">
        <div class="container">
            <center>
                <div style="margin-left: 10%">
                    <h4>Control de asistencia</h4>
                    <img src="imagenes/conasis2.png" alt=""/>
                    <br>
                    <p style="font-size: 20px">Sistema de información que registra y controla la asistencia de los aprendices, al igual que proporciona información estadística al respecto y facilita el proceso de asignación de horarios de formación.</p>
                </div>
            </center>
        </div>

    </div>
    <!--<div class="container">
        <div class="row" style="margin: 0px ;" id="UsuariosEstadicticas">
            <div class="card col s5" style="height: 485px">
                <div class="card-image waves-effect waves-block waves-light">
                    <img class="activator imgGrupo responsive-img" id="imgCarta" src="imagenes/grupo.png" alt="">
                </div>

                <div class="card-content cartaInformacion" >
                    <span class="card-title activator grey-text text-darken-4">Usuarios<i class="material-icons right">more_vert</i></span>
                    <p><a href="registroInstructores.jsp">Instructores</a></p>
                    <p><a href="RegistroSecretaria.jsp">Secretarías</a></p>
                    <p><a href="RegistroAprendiz.jsp">Aprendices</a></p>
                </div>
                <div class="card-reveal grupoDiv">
                    <span class="card-title grey-text text-darken-4">Usuarios<i class="material-icons right">close</i></span><br>
                    <p>Puedes registrar a los usuarios en el sistema para que comiencen a disfrutar de la nueva forma de tomar asistencia,  consultar sus registros,  modificar sus datos y eliminarlos.</p>
                </div>
            </div>

            <div class="col s2"><h1></h1></div>

            <div class="card large col s5" style="height: 485px; margin-top: -10px;">
                <div class="card-image waves-effect waves-block waves-light">
                    <img class="activator responsive-img" id="imgCarta2" src="imagenes/estadisticas.gif" alt="">
                </div>

                <div class="card-content cartaInformacion">
                    <span class="card-title activator grey-text text-darken-4">Visualizar datos estadísticos<i class="material-icons right">more_vert</i></span>
                    <p><a href="#">Datos estadísticos</a></p>
                </div>
                <div class="card-reveal grupoDiv">
                    <span class="card-title grey-text text-darken-4">Datos estadísticos<i class="material-icons right">close</i></span><br>
                    <p>Aquí puedes ver graficas  de estadísticas con datos recopilados y resumidos de:</p>
                    <ul>
                        <li>La asistencia por Aprendiz</li>
                        <li>La asistencia de los apredices por Ficha</li>
                        <li>La asistencia de los aprendices por Programa</li>
                    </ul>
                    <p>Además de estadisticas gráficas por género, municipio, barrio.</p>
                </div>
            </div>
        </div>

    <!---Ambientes y fichas

    <div class="row" style="margin: 0px" id="fichasAmbientes" >
        <div class="card col s5" style="height: 485px ;" >
            <div class="card-image waves-effect waves-block waves-light">
                <img class="activator imgGrupo responsive-img" id="imgCarta" src="imagenes/fichas.png" alt="">
            </div>

            <div class="card-content cartaInformacion" >
                <span class="card-title activator grey-text text-darken-4">Fichas<i class="material-icons right">more_vert</i></span>
                <p><a href="ficha.jsp">Fichas</a></p>
                 <p><a href="programacion.jsp">Programación</a></p>
            </div>
            <div class="card-reveal grupoDiv">
                <span class="card-title grey-text text-darken-4">Fichas<i class="material-icons right">close</i></span><br>
                <p>En esta sección se registran las fichas del centro para posteriormente asignarles un gestor, los ambientes de clases, sus competencias entre otras caracteristicas.</p>
            </div>
        </div>

        <div class="col s2" style="background-color: #f2f2f2;"><h1></h1></div>

        <div class="card large col s5" style="height: 485px; margin-top:-10px;">
            <div class="card-image waves-effect waves-block waves-light">
                <img class="activator responsive-img" id="imgCarta" src="imagenes/ambiente.jpg" alt="">
            </div>

            <div class="card-content cartaInformacion">
                <span class="card-title activator grey-text text-darken-4">Ambientes<i class="material-icons right">more_vert</i></span>
                <p><a href="ambiente.jsp">Ambientes</a></p>
            </div>
            <div class="card-reveal grupoDiv">
                <span class="card-title grey-text text-darken-4">Ambientes de clases<i class="material-icons right">close</i></span><br>
                <p>Aquí se registrán los ambientes de clases con sus caracteriticas(adecuaciones, sumistros, capacidad, etc.) para ser asignados a las distintas fichas.</p>
            </div>
        </div>
    </div>

    <!--Competencias y asistencia

    <div class="row" style="margin: 0px" id="AsistenciaCompetencia">
        <div class="card col s5" style="height: 485px;">
            <div class="card-image waves-effect waves-block waves-light">
                <img class="activator imgGrupo responsive-img" id="imgCarta1" src="imagenes/asistencia.png" alt="">
            </div>

            <div class="card-content cartaInformacion" >
                <span class="card-title activator grey-text text-darken-4">Asistencia<i class="material-icons right">more_vert</i></span>
                <p><a class="modal-trigger" href="#modalCDxI" id="competenciasDictadasXintstructor" >Ingreso y salida</a></p><!-- asistencia.jsp 
                <p><a href="inasistencia.jsp">Justificar falta</a></p>
                <p><a href="infoAsistencia.jsp">Información de asistencia</a></p>
            </div>
            <div class="card-reveal grupoDiv">
                <span class="card-title grey-text text-darken-4">Asistencia<i class="material-icons right">close</i></span><br>
                <p>Apartado donde se puede se puede habilitar el registro de asistencia (entrada y salida de clases), el cuál es util para tomar el tiempo preciso de la llegada y salida de los aprendices.</p>
            </div>
        </div>
    <!-- Modal Structure 
   <div id="modalCDxI" class="modal modal-fixed-footer" >
       <div class="modal-content">
           <h4>Asistencia</h4>
           <div id="tblcompetenciasDictadasXintstructor">
               
           </div>
       </div>
       <div class="modal-footer">
           <a href="asistencia.jsp" class=" modal-action modal-close waves-effect waves-green btn-flat" id="mdAceptarProgramacion">ACEPTAR</a>
           <button href="#!" class=" modal-action modal-close waves-effect waves-green btn-flat" id=""><span id="btnCambianteAmbiente">Cancelar</span></button>
       </div>
   </div><!--fin del modal 
           <div class="col s2"><h1></h1></div>

           <div class="card large col s5" style="height: 485px; margin-top: -10px;">
               <div class="card-image waves-effect waves-block waves-light">
                   <center> <img class="activator responsive-img" id="imgCarta3" src="imagenes/competencias.png" alt=""></center>
               </div>

               <div class="card-content cartaInformacion">
                   <span class="card-title activator grey-text text-darken-4">Programas y Competencias<i class="material-icons right">more_vert</i></span>
                   <p><a href="prograXcompe.jsp">Programas y competencias</a></p>
               </div>
               <div class="card-reveal grupoDiv">
                   <span class="card-title grey-text text-darken-4">Programas y Competencias<i class="material-icons right">close</i></span><br>
                   <p>Puedes agregar las competencias pertinenetes de cada programa para posteriormente utilizarlas en la programación de las fichas, además, de buscarlas para hacer modificaciones o eliminarlas según su necesidad.</p>
               </div>
           </div>
       </div>


       <div class="row" id="listaMenu">
           <ul>
               <li><a class="btn-floating btn-large waves-effect waves-light tooltipped" data-position="top" data-delay="50" data-tooltip="Usuarios y estadisticas" id="usuarios"><i class="material-icons" >add</i></a></li>
               <li><a class="btn-floating btn-large waves-effect waves-light tooltipped" data-position="top" data-delay="50" data-tooltip="Fichas y ambientes" id="grupos"><i class="material-icons" >add</i></a></li>
               <li><a class="btn-floating btn-large waves-effect waves-light tooltipped" data-position="top" data-delay="50" data-tooltip="Asistencia y competencias" id="asistencia"><i class="material-icons" >add</i></a></li>
           </ul>
       </div>

   </div>-->
    <footer class="page-footer">

        <div class="footer-copyright">
            <div class="container">
                © 2014 Copyright Text
                <a class="grey-text text-lighten-4 right" href="#!">More Links</a>
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
    <!--importacion de datatable javaScript por medio de CDN-->
    <script type="text/javascript" src="http://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>
    <script src="js/sweetalert.min.js" type="text/javascript"></script>
    <!-- <script src="js/main.js" type="text/javascript"></script>
     <script src="js/js.js" type="text/javascript"></script>
      <script src="js/materialize.js" type="text/javascript"></script><!--importando las funciones javaScript de materializecss -->
    <script src="js/ajax.js" type="text/javascript"></script>
    <script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js'></script>
    <script src="js/index.js"></script>
</body>

</html>