
<%@page import="beans.Usuario"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="DAO.Instructor"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    String ficha = request.getParameter("ficha");
    String nomComp = request.getParameter("nomComp");
    int idProgramacion = Integer.parseInt(request.getParameter("idProgramacion"));
    String bandera = request.getParameter("bandera");
%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <link rel="icon" type="image/png" href="imagenes/conasis2.png" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- importando materizalizecss CSS por medio de CDN -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.6/css/materialize.min.css">
        <!--importacion de datatable javaScript por medio de CDN-->
        <script type="text/javascript" src="http://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>
        <link href="css/styles.css" rel="stylesheet" type="text/css"/>
        <!-- importando el CSS de las alertas SweetAlert -->
        <link href="css/sweetalert.css" rel="stylesheet" type="text/css"/>
        <!-- importando el CDN de los iconos de materializecss-->
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <!--Menu -->
        <link href="css/normalize.css" rel="stylesheet" type="text/css"/>
        <link href="css/style2.css" rel="stylesheet" type="text/css"/>
        <title>Entrada y Salida</title>
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
            String tipo = "" + session.getAttribute("tipo");
    %>

    <body style="background: #f2f2f2">
        <nav class="cabecera" >
            <div class="nav-wrapper" style="background-color: transparent">
                <img src="imagenes/conasisLogo.png" alt=""  class="sidebar-toggle" style="height: 80px; width: 130px; margin-left: 5%; cursor: pointer"/>
                <a  class="brand-logo center">Asistencia</a>
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
        <%
        } else {
        %>
        <!-- Sidebar navigation -->
        <ul class="nav sidebar-nav">
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
                    <li>
                        <a href="" tabindex="-1">
                            Ficha a cargo
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

    <div class="container z-depth-1" id="ContenedorAsistencial" style="margin-top: 2%; background-color: #ffffff" >
        <div class="row" style="margin-bottom:  0px;">
            <div class="row" id="infoFichaAsistencia" style="padding-top: 10px; padding-bottom: 5px; padding-left: 20px;padding-right: 20px"> 
                <!--<div class="col s6"><label class="grupolbl1">Programa:  </label><label class="grupolbl2" id="lblPrograma"></label></div> style="display:none;"-->
                <label class="grupolbl2" id="lblPrograma"  style="display:none;"><%= idProgramacion%></label><!-- Esto esta oculto-->
                <div class="col s12"><label class="grupolblA">Ficha:     </label><label class="grupolblAD" id="lblFicha"><%=ficha%></label></div>
                <div class="col s12"><label class="grupolblA">Competencia:     </label><label class="grupolblAD" id="lblCompetenciaAsis"><%=nomComp%></label></div>
            </div>
            <br>

            <div id="logicaInicial" style="padding-bottom: 20px">
                <center>
                    <button id="btne" class="btn waves-effect light-blue accent-4 waves-light " type="button">Comenzar con la entrada de aprendices</button>
                    <button id="btns" class="btn waves-effect light-blue accent-4 waves-light " type="button">Comenzar con la salida de aprendices</button>
                    <div id="otroA" >
                        <h5>La ficha ya registró asistencia</h5>
                        <h6>Clic en el boton si faltó un aprendiz por registrar la asistencia</h6>
                        <button id="btnmostrarCntA" class="btn waves-effect light-blue accent-4 waves-light "><i class="material-icons">add</i></button><!--muestra el formulario de asistencia-->
                    </div>
                </center>
            </div>
        </div>


        <form action="" id="contenedorTomaAsistencia" >                
            <div class="row" id="imgConasis" style="margin-top:  -15px; padding-left: 50px; padding-right: 50px;" >
                <div id="contentAsistencia2"><!-- Cuando faltó un aprendiz por marcar asistencia-->
                    <div class="input-field col s5 grupoDiv" id=""> <!--lista de los tipos asistencia-->
                        <select required class="grupoDiv" id="tipoAsistencia" name="tipoAsistencia" >
                            <option name="opciones" value="" selected>Seleccione</option>
                            <option name="opciones" value="0">Ingreso</option>
                            <option name="opciones" value="1">Salida</option>
                        </select>
                        <label style="font-size: 14px; ">Seleccione tipo de asistencia</label>
                        <span id="vTipoA" >Debe elegir una opción</span>
                    </div>
                    <div class="input-field col s5" >
                        <input id="buscarAprendiz" type="number" class="validate" required="" >
                        <label for="buscarAprendiz">Numero Documento del Aprendiz</label>
                        <span id="vNumeroDocA" >El campo no puede estar vacio</span>
                    </div>
                    <div class="boton col s2 grupoDiv" style="margin-top: 20px;">
                        <!--<input  class="waves-effect light-blue accent-4 btn" type="button" id="buscar" name="buscar" value="BUSCAR"/>-->
                        <button class="btn waves-effect light-blue accent-4 waves-light " type="button" name="buscarA" id="buscarA" >BUSCAR</button>
                    </div>
                </div><!-- Cuando faltó un aprendiz por marcar asistencia-->

                <div id="contentAsistencia01">
                    <div class="col s1"><h1></h1></div>
                    <div class="input-field col s6" >
                        <input id="buscarAprendiz2" type="number" class="validate" required="" >
                        <label for="buscarAprendiz2">Numero Documento del Aprendiz</label>
                        <span id="vNumeroDocA2" >El campo no puede estar vacio</span>
                    </div>
                    <div class="boton col s2 grupoDiv" style="margin-top: 20px;">
                        <!--<input  class="waves-effect light-blue accent-4 btn" type="button" id="buscar" name="buscar" value="BUSCAR"/>-->
                        <button class="btn waves-effect light-blue accent-4 waves-light " type="button" name="buscarA2" id="buscarA2" >BUSCAR</button>
                        <button class="btn waves-effect light-blue accent-4 waves-light " type="button" name="buscarA3" id="buscarA3" >BUSCAR</button>
                    </div>
                    <div class="col s1"><h1></h1></div>
                </div>
            </div>
            <div class="row" id="contenedorInfo">
                <div class="col s3" id="ContimgHuella"><img src="imagenes/huella.png" alt="" class="circle responsive-img tooltipped" data-delay="50" data-tooltip="Clic para ingresar con la huella" id="imgHuella"/></div>
                <div class="col s6" >
                    <fieldset id="InfoEntradaHuella">
                        <legend>Aprendiz</legend>
                        <div >
                            <ul class="listaInfo" style="margin-bottom: 2px; margin-top: 2px;">
                                <li><label class="grupolblA">Nombres:   </label><label class="grupolblAD" id="lblNombre"></label></li>
                                <li><label class="grupolblA">Apellidos: </label><label class="grupolblAD" id="lblApellido"></label></li>
                                <li><label class="grupolblA">Numero Documento: </label><label class="grupolblAD" id="lblDocumento"></label></li>
                                <!--<li><label class="grupolbl1">Numero Documento:  </label><label class="grupolbl2" id="lblNumeroDoc"></label>-->
                            </ul>
                        </div>
                    </fieldset>
                    <fieldset id="InfoEntradaHuella">
                        <legend>Asistencia</legend>
                        <div>
                            <ul class="listaInfo" style="margin-bottom: 2px; margin-top: 2px;">
                                <!--<li><label class="grupolbl1">Día:       </label><label class="grupolbl2"></label></li>-->
                                <li><label class="grupolblA">Hora:      </label><label class="grupolblAD" id="lblHora"></label></li>
                                <li><label class="grupolblA">Faltas:    </label><label class="grupolblAD" id="lblFalta"></label></li>
                                <li><label class="grupolblA">Tiempo en clase:    </label><label class="grupolblAD" id="lblTiempo"></label></li>
                            </ul>
                        </div>
                    </fieldset>
                </div>
                <div class="col s3" id="ContimgAprendiz"><img src="imagenes/aprendiz.png" alt="" class="circle responsive-img tooltipped" data-delay="50" data-tooltip="Foto aprendiz" id="imgAprendiz"/></div>
            </div>
        </form>
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
<!--importacion de materializecss javaScript por medio de CDN-->
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.6/js/materialize.min.js"></script>
<!--importacion de datatable javaScript por medio de CDN-->
<script type="text/javascript" src="http://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>
<script src="js/materialize.js" type="text/javascript"></script><!--importando las funciones javaScript de materializecss -->
<!--importando en archivo .js ajax permite visualizar las informacion de la base de datos en el sitio web-->
<script type="text/javascript" src="js/ajax.js"></script>
<!--importacion del javaScript de las Alertas SweetAlert-->
<script src="js/sweetalert.min.js" type="text/javascript"></script>
<script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js'></script>
<script src="js/index2.js" type="text/javascript"></script>
<script>
                                $(document).ready(function () {
                                    inicioAsistencia();
                                    $("#competenciasDictadasXintstructor").click(function () {
                                        $("#modalCDxI").openModal("#competenciasDictadasXintstructor");
                                        $(".sidebar-overlay").trigger("click");
                                    });
                                    $("#competenciasAdministrador").click(function () {
                                        $("#modalCDxI").openModal("#competenciasAdministrador");
                                        $(".sidebar-overlay").trigger("click");
                                    });
                                });
</script>
</body>
</html>
