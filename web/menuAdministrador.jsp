
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
                 <a  class="brand-logo center">Control de asistencia</a>
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
                <a href="manual.jsp">
                    <i class="sidebar-icon material-icons" >help outline</i>
                    Ayuda
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
                <a href="manual.jsp">
                    <i class="sidebar-icon material-icons" >help outline</i>
                    Ayuda
                </a>
            </li>
        </ul>
        <%
            }
        %>

    </aside>
   
    <%
            if (tipo.equals("Instructor")) {
        %>
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
     <%
        } 
        %>
    <!--Lo que se mostrará-->

    <div class="container">

        <div class="card small">
            <div class="card-image waves-effect waves-block waves-light activator">
                <center><img class="activator" src="imagenes/conasisLogo.png" style=" width:80%; height: 50%" alt="" /></center>
            </div>
            <div class="card-content">
                <span class="card-title activator grey-text text-darken-4">Acerca de ...<i class="material-icons right">more_vert</i></span>

            </div>
            <div class="card-reveal">
                <center>  <span class="card-title grey-text text-darken-4" style="font-size: 25px;">Control Asistencia<i class="material-icons right">close</i></span>
                    <p style="text-align: justify; width: 50%; height: 20%; font-size: 20px;">Es un sistema de información realizado para el SENA Complejo Norte CTGI Medellin,
                        con el que se quiere llevar un control de asistencia de los aprendices de una forma rapida donde la información registrada la podremos 
                        visualizar  en tiempo real, además podemos generar reportes y estadisticas de inasistencias de cada aprendiz en forma individual o
                        grupal.
                    </p>
                </center> 
            </div>
        </div> 
    </div> 






    <div class="container">
        <footer class="page-footer">
            <div class="container">
                <div class="row">
                    <div class="col l6 s12">
                        <h5 class="black-text" >Desarrolladores</h5>
                        <p class="grey-text text-lighten-3">
                            <a href="#eme" class="modal-trigger waves-effect waves-light img a">Emerson Javid Gonzalez</a>
                            <a href="#sebas" class="modal-trigger waves-effect waves-light img a">Johan Sebastian Salazar Muñoz</a>
                            <a href="#wil" class="modal-trigger waves-effect waves-light img a">Wilmar Alejandro Agudelo Yepes</a>
                        </p>
                    </div>
                    <div class="col l4 offset-l2 s12">
                        <h5 class="black-text"> Ayuda </h5>
                        <ul>
                            <li> <a href="manual.jsp" class="a">Manual usuario</a></li>
                            <li><a href="#propiedad" class="modal-trigger waves-effect waves-light img a">Propiedad intelectual</a></li>
                            <li><a href="#anexo" class="modal-trigger waves-effect waves-light img a">Anexos</a></li>
                        </ul>
                    </div>

                    <!-- Modal -->


                    <div id="anexo" class="modal">
                        <div class="modal-content">
                            <center> 
                                <p style="text-align: justify; width: 70%; height: 20%">Esperamos que tengas una esperincia satisfactoria con la utilizacion 
                                    de nuestro sistema de información y para ello estamos constantemente  realizando mejoras para que su funcionamiento sea 
                                    cada día mas acordes a sus actividades a desarrollar. 
                                    <br>
                                    <br>
                                    <b> CONASIS</b> es un sistema de información realizado en IDE NetBeans utilizando lenguajes de programación como 
                                    JavaEE, JavaScript y SQL con un gestor de base de datos en Mysql y otras tegnologias:
                                </p>
                            </center>
                            <div class="container">
                                <div class="row">
                                    <div class="col s4">
                                        <ul>	
                                            <li><b>framework </b></li>                        
                                            <li>Materializecss </li>
                                            <li> jquery</li>
                                        </ul>
                                        <ul><b>API </b></ul>
                                        <li> Google charts </li>
                                    </div>

                                    <div class="col s4">
                                        <ul><b>  librerias </b></ul>
                                        <li> Gson </li> 
                                        <li> Itext </li>
                                        <li> Smtp. </li>
                                    </div>
                                    <div class="col s4">
                                        <ul><b> plugins </b></ul>
                                        <li> datatable </li>
                                        <li> sweet alert.</li> 
                                        <ul><b> Ajax </b></ul>
                                    </div>
                                </div>
                            </div> 
                        </div>
                    </div>




                    <div id="propiedad" class="modal">
                        <div class="modal-content">
                            <center> 
                                <p style="text-align: justify; width: 70%; height: 20%">
                                <h4>Propiedad intelectual</h4>
                                </p>
                                <p style="text-align: justify; width: 70%; height: 20%">
                                <ul>Sistema de informacón realizado:
                                    <li>Emerson Javid Gonzalez Morales </li>
                                    <li>Johan Sebastian Salazar Muñoz</li>
                                    <li>Wilmar Alejandro Agudelo Yepes</li>
                                </ul>
                                </p>
                            </center>
                        </div>
                    </div>


                    <!-- Modal Structure -->
                    <div id="sebas" class="modal bottom-sheet">
                        <div class="modal-content">

                            <div class="row">
                                <div class="col s3"></div>
                                <div class="col s4"> 
                                    <img class="circle" src="imagenes/IMG_3702.JPG" style="width: 50%; height: 40%" alt=""/>
                                </div> 
                                <div class="col s5">
                                    <p><b>Johan Salazar</b> Tecnico en Sistemas y aprendiz de Analisis y Desarrollo de  Sistemas de Información Trabajó como desarrollador.</p>
                                    <img style="width: 5%; height: 5% " src="imagenes/linkedin.png" alt=""/>
                                    <img style="width: 5%; height: 5% " src="imagenes/google-plus.png" alt=""/>
                                </div>  
                            </div>
                        </div>
                    </div>
                    <div id="eme" class="modal bottom-sheet">
                        <div class="modal-content">
                            <div class="row">
                                <div class="col s3"> </div>
                                <div class="col s4">

                                    <img class="circle" src="imagenes/IMG_3699.JPG" style="width: 50%; height: 20%" alt=""/>
                                </div>
                                <div class="col s5">
                                    <p><b>Emerson Javid</b> Tecnico en Mantenimiento y Repacion de Equipos de Computo y aprendiz de Analisis y Desarrollo de  Sistemas de Información Trabajó como desarrollador.</p>
                                    <img style="width: 5%; height: 5% " src="imagenes/google-plus.png" alt=""/>
                                    <img style="width: 5%; height: 5% " src="imagenes/facebook.png" alt=""/>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div id="wil" class="modal bottom-sheet">
                        <div class="modal-content">

                            <div class="row">
                                <div class="col s3"></div>  
                                <div class="col s4 ">
                                    <img class="circle" src="imagenes/IMG_3698.JPG" style="width: 20%; height: 20%" alt=""/>
                                </div>  
                                <div class="col s5">   
                                    <p><b>Wilmar  Agudelo</b> Aprendiz de Analisis y Desarrollo de  Sistemas de Información Trabajó como desarrollador.</p>
                                    <a href=""><img style="width: 5%; height: 5% " src="imagenes/google-plus.png" alt=""/></a>
                                    <img style="width: 5%; height: 5% " src="imagenes/facebook.png" alt=""/>
                                    <img style="width: 5%; height: 5% " src="imagenes/google-plus.png" alt=""/>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <br>
            <br>

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
            <!--importacion de datatable javaScript por medio de CDN-->
            <script type="text/javascript" src="http://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>
            <script src="js/sweetalert.min.js" type="text/javascript"></script>
            <!-- <script src="js/main.js" type="text/javascript"></script>-->
             <script src="js/js.js" type="text/javascript"></script>
              <script src="js/materialize.js" type="text/javascript"></script><!--importando las funciones javaScript de materializecss -->
            <script src="js/ajax.js" type="text/javascript"></script>
            <script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js'></script>
            <script src="js/index.js"></script>
            <script>
                                $(document).ready(function () {
                                    $('select').material_select();
                                    $("#competenciasDictadasXintstructor").click(function (){
                                        $("#modalCDxI").openModal("#competenciasDictadasXintstructor");
                                        
                                        $(".sidebar-overlay").trigger("click");
                                    });
                                });

                                $('.modal-trigger').leanModal({
                                    dismissible: true, // Modal can be dismissed by clicking outside of the modal
                                    opacity: .5, // Opacity of modal background
                                    in_duration: 300, // Transition in duration
                                    out_duration: 200, // Transition out duration
                                    starting_top: '4%', // Starting top style attribute
                                    ending_top: '10%' // Ending top style attribute

                                }
                                );

            </script>

            </body>

            </html>