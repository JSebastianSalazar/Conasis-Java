<%-- 
    Document   : prograXcompe
    Created on : 10-jun-2016, 18:04:33
    Author     : Emerson
--%>

<%@page import="beans.Usuario"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="DAO.Instructor"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head><!--Gestión de programas y competencias-->
            <link rel="icon" type="image/png" href="imagenes/conasis2.png" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- importando materizalizecss CSS por medio de CDN -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.6/css/materialize.min.css">
         <!-- importando el CSS del datable por medio de CDN  DATATABLE-->
        <link href="http://cdn.datatables.net/1.10.12/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css"/>
        <!-- importando iconos materializecss por medio del CDN-->
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <title>Programas y competencias</title>
        <link href="css/styles.css" rel="stylesheet" type="text/css"/>
        <!-- importando el CSS de las alertas SweetAlert -->
        <link href="css/sweetalert.css" rel="stylesheet" type="text/css"/>
        <link href="css/registroGrupo.css" rel="stylesheet" type="text/css"/>

        <!--Menu -->
        <link href="css/normalize.css" rel="stylesheet" type="text/css"/>
        <link href="css/style2.css" rel="stylesheet" type="text/css"/>
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
                <a  class="brand-logo center">Gestión de programas y competencias</a>
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
                        <a href="#modalCDxI" id="competenciasAdministrador"  tabindex="-1">
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

    <div id="cntntMsj"></div>
    <div >
        <div class="row" style="margin-top: 2%; ">
            <div id="contenedorControles" style="border-left:#f2f2f2 170px solid;  border-right:#f2f2f2 170px solid;  padding-bottom: 2px; ">
                <div class="row z-depth-1" style="background-color: #ffffff; padding-left: 25px; padding-top: 15px;">
                    <div class="row" style="margin-bottom: 5px;"><!-- Seleccion programa o competencia -->
                        <div class="col s5" >
                            <p style="font-size: 20px; margin-top: 0px; margin-bottom: 0px ">Seleccione antes de realizar una acción</p>
                            <p>
                                <input name="grupo" type="radio" id="rdCompetencia" value="competencia"/>
                                <label for="rdCompetencia">Competencia</label>
                            </p>
                            <p>
                                <input name="grupo" type="radio" id="rdPrograma" value="programa"/>
                                <label for="rdPrograma">Programa</label>
                            </p>
                        </div>
                        <div class="col s7" style='padding-left:100px; padding-right:100px'>
                            <table >
                                <tr >
                                    <td style='padding: 2px; padding-bottom: 6px; width: 10px'> <center><button class="btn waves-effect light-blue accent-4 waves-light tooltipped" type="button" name="btnGuardarP" id="btnGuardarP" value="guardar" data-position="top" data-tooltip="Guardar programas" style='padding-left:35px; padding-right: 35px'>GUARDAR</button></center></td>
                                <td style='padding: 2px; padding-bottom: 6px; width: 10px'><center><button class="btn waves-effect light-blue accent-4 waves-light tooltipped" type="button" name="btnAgregarC" id="btnAgregarC" value="agregar" data-position="top" data-tooltip="Asociar competencias a programas">AGREGAR</button></center></td>
                                <td style='padding: 2px; padding-bottom: 6px; width: 10px'><center><button class="btn waves-effect light-blue accent-4 waves-light tooltipped" type="button" name="btnListarP" id="btnListarP" value="listar" data-position="top" data-tooltip="Mostrar todos los programas" style='padding-left:35px; padding-right: 35px'>LISTAR</button></center></td>
                                </tr>
                                <tr > 
                                    <td style='padding: 2px; width: 10px'><center><button class="btn waves-effect light-blue accent-4 waves-light tooltipped" type="button" name="btnModificar" id="btnModificar" value="modificar" data-tooltip="Modificar programas o competencias">MODIFICAR</button></center></td>
                                <td style='padding: 2px; width: 10px'><center><button class="btn waves-effect light-blue accent-4 waves-light tooltipped" type="button" name="btnEliminar" id="btnEliminar" value="eliminar"  data-tooltip="Eliminar programas o competencias">ELIMINAR</button></center></td><!--modal-trigger  data-target="modal1"-->
                                <td style='padding: 2px; width: 10px'><h1></h1></td>
                                </tr>
                            </table>
                        </div>
                    </div>
                    <!-- Modal Structure -->
                    <div id="modal1" class="modal bottom-sheet" >
                        <div class="modal-content">
                            <h4>¡Alerta!</h4>
                            <h5 class="msjInfoAntes" id="cabeceraModal"></h5><!-- mensaje de que puede estar asociado-->
                            <h5 class="msjInfoAntes">¿Desea eliminar el <span id="spanTipo"> </span> <span id="spanNombre"></span>?</h5>
                            <div id="msjInfoDespues"></div>
                        </div>
                        <div class="modal-footer">
                            <button class="btn waves-effect light-blue accent-4 waves-light " type="button" name="modalAceptar" id="modalAceptar" value="aceptar" >ACEPTAR</button>
                            <button href="#!" class=" modal-action modal-close waves-effect waves-green btn-flat" id=""><span id="btnCambianteModal">Cancelar</span></button>
                        </div>
                    </div>
                    <div class="row" style="margin-bottom: 5px">
                        <div class="input-field col s6"><!-- Area de texto -->
                            <textarea id="textarea" class="materialize-textarea" style="margin-bottom: 0px"  onkeypress="return soloLetras(event);"></textarea>
                            <label for="textarea">Area de texto</label>
                            <span id="vTxtarea" >El campo no puede estar vacio</span>
                        </div>
                        <div class="input-field col s5 grupoDiv"> <!--lista de competencias-->
                            <select name="competencias" id="competencias">
                                <option>Selecccione una competencia</option>
                            </select>
                            <label style="font-size: 14px">Competencias</label>
                        </div>
                        <div class="col s1" style="padding-left: 0px">
                            <button class="btn waves-effect light-blue accent-4 waves-light tooltipped" type="button" name="btnGuardarC" id="btnGuardarC" value="guardar" data-position="left" data-tooltip="Guardar una competencia" style='padding-left:15px; padding-right: 15px; margin-top: 20px'>+</button>
                        </div>
                    </div>
                </div>
            </div>    
            <div id="contenedorTablas">
                <div class="row z-depth-2" style="border-top: #2196f3 1px solid; padding-top: 10px; margin-top: 5px;">
                    <div class="col s12" id="imgPreloaderPrograComp">
                        <center>
                            <img src="imagenes/loader.gif" alt="" style="height: 100px; width: 100px"/><br>
                            <span style="font-size: 16px">Cargando...</span>
                        </center>
                    </div>

                    <div class="col s6" >
                        <table class='highlight bordered' id="tablaPrograma"> <!--tabla de programas -->
                            <thead>
                                <tr>
                                    <th data-field="id">Programas</th>
                                </tr>
                            </thead>
                            <tbody name="progra">
                            </tbody>
                        </table>
                    </div>
                    <div class="col s6">
                        <div class="col s6" id="imgPreloaderCompeT">
                            <br><br>
                            <center>
                                <img src="imagenes/loader.gif" alt="" style="height: 100px; width: 100px"/><br>
                                <span style="font-size: 16px">Cargando...</span>
                            </center>
                        </div>
                        <table class='highlight bordered' id="tablaCompetencia"><!-- tabla de competencias -->
                            <thead>
                                <tr>
                                    <th data-field="id">Competencias</th>
                                </tr>
                            </thead>
                            <tbody name="compe">
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>

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
    <%} else {
            session.invalidate();
            response.sendRedirect("index.html");
        }%>  

    <!--importación de Jquery por medio de CDN-->
    <script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
    <!--importacion de materializecss javaScript por medio de CDN
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.6/js/materialize.min.js"></script>-->
    <!--importacion de datatable javaScript por medio de CDN-->
            <script type="text/javascript" src="http://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>
    <script src="js/materialize.js" type="text/javascript"></script><!--importando las funciones javaScript de materializecss -->
    <!--importando en archivo .js ajax permite la comicacion entre servlets y la pagina jsp-->
    <script type="text/javascript" src="js/ajax.js"></script>
    <!--importacion del javaScript de las Alertas SweetAlert-->
    <script src="js/sweetalert.min.js" type="text/javascript"></script>
    <!--menu-->
    <script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js'></script>
    <script src="js/index2.js" type="text/javascript"></script>
     <!--importacion de materializecss javaScript por medio de CDN-->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.6/js/materialize.min.js"></script>
    <script>
                                $(document).ready(function () {
                                    $('.tooltipped').tooltip({delay: 50});
                                    $("#competenciasAdministrador").click(function (){
                                        $("#modalCDxI").openModal("#competenciasAdministrador");
                                        $(".sidebar-overlay").trigger("click");
                                    });
                                });

    </script>
</body>
</html>
