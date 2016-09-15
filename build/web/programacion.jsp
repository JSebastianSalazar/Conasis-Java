<%-- 
    Document   : programacion
    Created on : 19-jun-2016, 13:14:51
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
        <meta charset="UTF-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- importando materizalizecss CSS por medio de CDN -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.6/css/materialize.min.css">
        <!-- importando iconos materializecss por medio del CDN-->
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link href="css/styles.css" rel="stylesheet" type="text/css"/>
        <!-- importando el CSS del datable por medio de CDN -->
        <link href="http://cdn.datatables.net/1.10.12/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css"/>
        <!-- importando el CSS de las alertas SweetAlert -->
        <link href="css/sweetalert.css" rel="stylesheet" type="text/css"/>
        <title>Programación de fichas</title>
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
                <a  class="brand-logo center">Programación de fichas</a>
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
    <div id="contenedorFichasPrograma" style="margin-top:2%; ">
        <div class="container z-depth-1" style="background-color: #ffffff; padding-top: 15px; padding-left: 5px; padding-right: 5px">
            <div class="row col s12" >
                <div class="row col s12">
                    <div class="input-field col s6 "> <!--lista de fichas-->
                        <select  class="" name="programas" id="programas">
                            <option>Selecccione un programa</option>
                        </select>
                        <label id="lblPrograma" style="font-size: 15px">Programas</label>
                    </div>
                    <div class="col s6" style='padding-left:100px; padding-right:100px'>
                        <table>
                            <tr>
                                <td>
                                    <button class="btn waves-effect light-blue accent-4 waves-light tooltipped" type="button" name="cuadroFichasTodas" id="cuadroFichasTodas" value="cuadroFichasTodas" data-position="top" data-tooltip="Mostrar todas las fichas">TODAS</button>
                                </td>
                                <td>
                                    <button class="btn waves-effect light-blue accent-4 waves-light tooltipped" type="button" name="nuevaProgramacion" id="nuevaProgramacion" value="nuevaProgramacion" data-position="top" data-tooltip="Generar una programación">PROGRAMACIÓN</button>
                                </td>
                            </tr>
                        </table>
                    </div>
                </div>
                <div class="row col s12" id="contenedorFichas">
                    <div class="col s12" id="imgPreloaderProgramacionFichas">
                        <center>
                            <img src="imagenes/loader.gif" alt="" style="height: 100px; width: 100px"/><br>
                            <span style="font-size: 16px">Cargando...</span>
                        </center>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div>

                <div class="col s1"><h1></h1></div>
                <div  id="contenedorProgramacionFichas" class="col s10 z-depth-2" style="background-color: #ffffff;border-top: #2196f3 1px solid;">
                    <div class="col s12" id="imgPreloaderPrograCompProgramacion">
                        <center>
                            <img src="imagenes/loader.gif" alt="" style="height: 100px; width: 100px"/><br>
                            <span style="font-size: 16px">Cargando...</span>
                        </center>
                    </div>
                </div>
                <div class="col s1"><h1></h1></div>
            </div>
        </div>
    </div>
    <div class="container" id="contenedorNuevaProgramacion">
        <div class="row z-depth-2" id="contenedorProgamacionNueva"> <!--formulario -->
            <div class="input-field col s6 "> <!--lista de fichas-->
                <select  class="" name="fichas" id="fichas">
                    <option  value="" disabled selected>Selecccione una ficha</option>
                </select>
                <label id="lblFicha" style="font-size: 14px">Fichas</label>
            </div>

            <div class="input-field col s6 "> <!--lista de trimestre-->
                <select class="" id="trimestre" name="trimestre">
                    <option name="opciones" value="">seleccione</option>
                    <option name="opciones" value="1">1</option>
                    <option name="opciones" value="2">2</option>
                    <option name="opciones" value="3">3</option>
                    <option name="opciones" value="4">4</option>
                    <option name="opciones" value="5">5</option>
                    <option name="opciones" value="6">6</option>
                </select>
                <label id="lbltrimestre" style="font-size: 14px">Trimestre</label>
            </div>

            <div class="input-field col s6 "> <!--lista de competencias-->
                <select class="" name="competencias" id="competencias">
                    <option>Selecccione una competencia</option>
                </select>
                <label id="lblcompetencias" style="font-size: 14px">Competencias</label>
            </div>

            <div class="input-field col s6"> <!--lista de los dias de la semana-->
                <select class="" id="dia" name="dia">
                    <option name="opciones" value="">Seleccione</option>
                    <option name="opciones" value="Lunes">Lunes</option>
                    <option name="opciones" value="Martes">Martes</option>
                    <option name="opciones" value="Miércoles">Miércoles</option>
                    <option name="opciones" value="Jueves">Jueves</option>
                    <option name="opciones" value="Viernes">Viernes</option>
                    <option name="opciones" value="Sabado">Sabado</option>
                    <option name="opciones" value="Domingo">Domingo</option>
                </select>
                <label id="lbldia" style="font-size: 14px">Día de semana</label>
            </div>

            <div class="input-field col s6">
                <input type="date" class="datepicker" id="fechaInicio" name="">
                <label for="fechaInicio" style="margin-top: -30px;" id="lblfechaInicio">Fecha inicio</label>
            </div>
            <div class="input-field col s6">
                <input type="date" class="datepicker" id="fechaFinal" name="">
                <label for="fechaFinal" style="margin-top: -30px;" id="lblfechaFinal">Fecha final</label>
            </div>

            <div class="input-field col s6 ">
                <input type="time" class="" id="horaInicio" name="horaInicio">
                <label for="horaInicio" style="margin-top: -30px;" id="lblhoraInicio">Hora inicio en formato 12h</label>
            </div>
            <div class="input-field col s6 ">
                <input  class="" id="horaFin" name="horaFin" type="time" >
                <label for="horaFin" style="margin-top: -30px;" id="lblhoraFin">Hora fin en formato 12h</label>
            </div>
            <div class="input-field col s6"> <!--lista de los tipos de documentos-->
                <select class="" name="instructores" id="instructores">
                    <option name="opciones" value="">Seleccionar instructor</option>
                </select>
                <label id="lblinstructores" style="font-size: 14px">Instructores</label>
            </div>
            <div class="input-field modal-trigger z-depth-0 col s6 " data-target="modal1">
                <input type="text" class="validate" id="ambiente" name="ambiente">
                <label for="ambiente" id="lblambiente">Ambiente</label>
            </div>
            <br>
            <!-- Modal Structure -->
            <div id="modal1" class="modal modal-fixed-footer" >
                <div class="modal-content">
                    <h4>Ambientes</h4>
                    <div id="tblAmbientes">
                        <div class="col s12" id="">
                            <center>
                                <img src="imagenes/loader.gif" alt="" style="height: 100px; width: 100px"/><br>
                                <span style="font-size: 16px">Cargando...</span>
                            </center>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button href="#!" id='btnCancelarMdAmbiente' class=" modal-action modal-close waves-effect waves-green btn-flat" id=""><span id="btnCambianteAmbiente">Cancelar</span></button>
                </div>
            </div><!--fin del modal -->
            <div class="grupoDiv row" style="margin-bottom: 0px">
                <div class="grupoDiv  col s2"><h1></h1></div>
                <div class="grupoDiv col s2">
                    <button class="btn waves-effect light-blue accent-4 waves-light" type="button" name="btnCancelar" id="btnCancelar" value="cancelar">CANCELAR</button>
                </div>
                <div class="grupoDiv  col s4"><h1></h1></div>
                <div class="grupoDiv col s2">
                    <button class="btn waves-effect light-blue accent-4 waves-light" type="button" name="btnGuardar" id="btnGuardar" value="guardar">GUARDAR</button>
                    <button class="btn waves-effect light-blue accent-4 waves-light" type="button" name="btnModificar" id="btnModificarProgramacion" value="modificar">MODIFICAR</button>
                </div>
                <div class="grupoDiv  col s2"><h1></h1></div>     
            </div>
        </div>
        <!-- PRELOADER CON BEFORESEND--><div id="preloader"> <div> <span id="textoPreloader" style="margin-top: -500px">yufy</span></div><img src="imagenes/loader.gif" alt=""/> </div>
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
    <!--<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.6/js/materialize.min.js"></script>-->
    <script src="js/materialize.js" type="text/javascript"></script><!--importando las funciones javaScript de materializecss -->
    <!--importando en archivo .js ajax permite visualizar las informacion de la base de datos en el sitio web-->
    <script type="text/javascript" src="js/ajax.js"></script>
    <!--importacion de datatable javaScript por medio de CDN-->
    <script type="text/javascript" src="http://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>
    <!--importacion del javaScript de las Alertas SweetAlert-->
    <script src="js/sweetalert.min.js" type="text/javascript"></script>
    <!--menu-->
    <script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js'></script>
    <script src="js/index2.js" type="text/javascript"></script>
    <!--importacion de materializecss javaScript por medio de CDN-->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.6/js/materialize.min.js"></script>
    <script>
        $(document).ready(function (){
          
            $('#competencias').prop('disabled', true);
            if($('#fichas').val() !== ""){
                $('#competencias').prop('disabled', true);
            }else{
                 $('#competencias').prop('disabled', false);
            }
        });
       
    </script>
</body>
</html>