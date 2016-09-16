<%-- 
    Document   : AprendizRegistro
    Created on : 9/09/2016, 02:32:08 AM
    Author     : sebas
--%>

<%@page import="java.util.List"%>
<%@page import="beans.Usuario"%>
<%@page import="java.util.ArrayList"%>
<%@page import="DAO.Instructor"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head><meta name="robots" content="noindex, nofollow">
        <meta name="googlebot" content="noindex, nofollow">

        <title>Registro Aprendiz</title>
        <!--Import Google Icon Font-->
        <meta charset="utf-8">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.7/css/materialize.min.css">
        <link href="css/sweetalert.css" rel="stylesheet" type="text/css"/>
        <link href="css/feature-carousel.css" rel="stylesheet" type="text/css"/>
        <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link href="css/styles.css" rel="stylesheet" type="text/css"/>
        <link href="css/registroGrupo.css" rel="stylesheet" type="text/css"/>
        <!--Menu -->
        <link href="css/normalize.css" rel="stylesheet" type="text/css"/>
        <link href="css/style2.css" rel="stylesheet" type="text/css"/>

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
                <a  class="brand-logo center">Aprendices</a>
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
    
    <div class="contenedorTab ">
        <div class="row">
            <div class="col s12 ">
                <ul style="background: #f2f2f2" class="tabs">
                    <li class="tab col s6"><a href="#lista">Lista</a></li>
                    <li class="tab col s6"><a href="#registro">Registrar</a></li>
                </ul>
                <div class="container" id="lista" style="background-color:  #f2f2f2"> <!--abarca el 70% de la pantalla-->

                    <!--<div class="row">
                        <center>  <a id="but_prev" href="#"><i class="material-icons">video_library</i></a> | <a id="but_pause" href="#"><i class="material-icons">pause</i></a> | <a id="but_start" href="#"><i class="material-icons">play_arrow</i></a> | <a id="but_next" href="#"><i class="material-icons">skip_next</i></a> </center>
                        <center>   <div class="carousel-container">
                    <% list = dao.fotosInstructor();
                        if (list != null || !list.isEmpty() || list.size() != 0) {
                    %>
                    <div id="carousel">
                    <%for (int i = 0; i < list.size(); i++) {
                            Usuario u = new Usuario();
                            u = list.get(i);
                    %>
                    <div class="carousel-feature" id="holaModal" >

                        <a href="#modal78" class="modal-trigger" ><img   class="carousel-image" id="imgCarrusel" alt="Image Caption"  src="<%=u.getFoto()%>" ></a>
                        <div class="carousel-caption" >
                            <p   class="contenidoId" >
                    <%=u.getNombre()%>  <%=u.getApellido()%> <%=u.getId()%>
                </p>
            </div>

        </div> 
                    <%
                        }
                    %>
                </div>
                <div id="carousel-left"><img src="imagenes/arrow-left.png" /></div>
                <div id="carousel-right"><img src="imagenes/arrow-right.png" /></div>
            </div></center>
                    <%
                    } else {
                    %>
                <h5>No hay fotos de instructores</h5>
                    <%
                        }
                    %>
            </div>-->

                    <div class="row" style="margin-top: 2%; background-color: #f2f2f2">
                        <div id="contenedorControles" style="border-left: 0px; border-right: 0px;padding-bottom: 0px; ">
                            <div class="row" style="background-color: #f2f2f2; padding-left: 0px; padding-top: 0px; margin-bottom: 0px">
                                <div class="row z-depth-1" style="margin-right: 0px; margin-right: 0px; margin-bottom: 15px; padding-top: 5px;"><!-- Seleccion programa o competencia -->
                                    <h5 class="text-aling center">Fichas</h5>
                                    <div class="row col s12" id="contenedorFichasInstructor2">  
                                        <!--Se muestran las fichas que el instructor tiene asignada para impartir una formación-->
                                        <div class="col s12" id="">
                                            <center>
                                                <img src="imagenes/loader.gif" alt="" style="height: 100px; width: 100px"/><br>
                                                <span style="font-size: 16px">Cargando...</span>
                                            </center>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>    
                        <div  style="border-left:#f2f2f2 70px solid; border-right:#f2f2f2 70px solid; background-color: #ffffff;" id="contenedorGeneralAprendices">
                            <div class="row z-depth-2" style="border-top: #2196f3 1px solid; padding-top: 10px; margin-top: 5px;">
                                <div class="col s12" id="">
                                    <!--Se muestran las competencia que el instructor dictó clase (competencia y fecha)-->
                                    <div class="col s12" id="contentAprendices">
                                        <center>
                                            <img src="imagenes/loader.gif" alt="" style="height: 100px; width: 100px"/><br>
                                            <span style="font-size: 16px">Cargando...</span>
                                        </center>
                                    </div>
                                </div>
                            </div> 
                        </div>
                    </div>        
                </div>               
                <!--REGISTRO DE APRENDICES -->
                <div class="container" id="registro" style="background: #ffffff; padding-bottom: 0px;padding-left: 0px;padding-right: 0px; margin-top: 15px;" > <!--abarca el 70% de la pantalla-->
                    <div class="row" >
                        <form class="form col s12 z-depth-3"   style="padding-bottom:30px; padding-top: 20px">
                            <div  id="registro4">
                                <div class="input-field col s6 grupoDiv"> <!--lista de los tipos de documentos-->
                                    <select required class="grupoDiv" id="tipo_Documento" name="tipoDocumento">
                                        <option name="opciones" value="" disabled selected></option>
                                        <option name="opciones" value="1">Cédula de ciudadanía</option>
                                        <option name="opciones" value="2">Targeta de identidad</option>
                                        <option name="opciones" value="3">Cédula de extranjería</option>
                                        <option name="opciones" value="4">Documento nacional de identificación</option>
                                    </select>
                                    <label>Tipo documento</label>
                                </div>
                                <div class="input-field col s6 grupoDiv">
                                    <input id="numero_documento" type="text" name="numeroDocumento" class="validate"  maxlength="13"required onKeypress="if (event.keyCode < 45 || event.keyCode > 57)
                                                event.returnValue = false;"> 
                                    <label class="active" for="numero_documento">Número documento</label>
                                </div>
                                <div class="input-field col s6 grupoDiv"> 
                                    <i class="material-icons prefix">account_circle</i>
                                    <input id="nombre" type="text" name="nombre" class="validate" maxlength="50" required onkeypress="return soloLetras(event);">
                                    <label class="active" for="nombre">Nombres</label>
                                </div>
                                <div class="input-field col s6 grupoDiv">
                                    <i class="material-icons prefix">account_circle</i>
                                    <input id="apellido" type="text" name="apellido" class="validate" maxlength="50" required onkeypress="return soloLetras(event);">
                                    <label class="active" for="apellido">Apellidos</label>
                                </div>

                                <div class="boton col s6 grupoDiv" style="margin-left: 20%;" >
                                    <button class="btn waves-effect light-blue accent-4 waves-light " type="button" id="cancelar" onClick="location.href = 'menuAdministrador.jsp'" >Cancelar</button>
                                    <!-- <input  class="waves-effect light-blue accent-4 btn" type="button" id="cancelar" name="Guardar" value="Cancelar" onClick="location.href='menuAdministrador.jsp'"/>-->
                                </div>
                                <div class="boton col s3 grupoDiv" >
                                    <button class="btn waves-effect light-blue accent-4 waves-light " type="button" id="btns"  >Siguiente</button>
                                    <!-- <input  class="waves-effect light-blue accent-4 btn" type="button" id="btns" name="btnCancelar" value="Siguiente "/>-->
                                </div>
                            </div>

                            <div  id="registro3">
                                <div class="input-field col s6 grupoDiv"> <!--generos-->
                                    <i class="material-icons prefix">view_list_circle</i>
                                    <select required class="grupoDiv"  id="genero" name="genero">
                                        <option name="opciones" value="" disabled selected></option>
                                        <option name="opciones" value="0">Masculino</option>
                                        <option name="opciones" value="1">Femenino</option>
                                    </select>
                                    <label>Genero</label>
                                </div>
                                <div class="input-field col s6 grupoDiv">
                                    <i class="material-icons prefix">phone_circle</i>
                                    <input value="" id="telefono_fijo" type="text" maxlength="8" name="fijo" class="validate" required onKeypress="if (event.keyCode < 45 || event.keyCode > 57)
                                                event.returnValue = false;">
                                    <label class="active" for="telefono_fijo">Telefono fijo</label>
                                </div>
                                <div class="input-field col s6 grupoDiv"> 
                                    <i class="material-icons prefix">account_circle</i>
                                    <input value="" id="estrato" type="text"  maxlength="1" name="estrato" class="validate"onKeypress="if (event.keyCode < 45 || event.keyCode > 57)
                                                event.returnValue = false;" >
                                    <label class="active" for="estrato">estrato</label>
                                </div>

                                <div class="input-field col s6 grupoDiv"> 
                                    <i class="material-icons prefix">account_circle</i>
                                    <input value="" id="email" type="email"  pattern="^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$" name="email" class="validate">
                                    <label for="email" data-error="error" data-success="right">Email</label>
                                </div>

                                <br>
                                <br>
                                <div> 
                                    <div class="boton col s4 grupoDiv" style="padding-left: 10%;">
                                        <button class="btn waves-effect light-blue accent-4 waves-light " type="button" id="btnatr" >VOLVER</button>
                                        <!--<input  class="waves-effect light-blue accent-4 btn" type="button" id="btnatr" name="btnCancelar" value="VOLVER"/>-->
                                    </div>

                                    <div class="boton col s4 grupoDiv" style="     padding-left: 9%;" >
                                        <button class="btn waves-effect light-blue accent-4 waves-light " type="button" id="cancelar" onClick="location.href = 'menuAdministrador.jsp'" >Cancelar</button>
                                        <!-- <input  class="waves-effect light-blue accent-4 btn" type="button" id="cancelar" name="Guardar" value="Cancelar" onClick="location.href='menuAdministrador.jsp'"/>-->
                                    </div>
                                    <div class="boton col s4 grupoDiv" style=" padding-left: 8%;" >
                                        <button class="btn waves-effect light-blue accent-4 waves-light " type="button" id="Sgtns" >SIGUIENTE</button>
                                        <!-- <input  class="waves-effect light-blue accent-4 btn" type="button" id="Sgtns1" name="btnCancelar" value="SIGUIENTE"/>-->
                                    </div>
                                </div>
                            </div>
                            <div  id="registro5">

                                <div class="input-field col s6 grupoDiv"> <!--Barrios-->

                                    <select class="browser-default"  id="municipio" name="municipio">
                                        <option value="" disabled selected>Municipios</option>
                                        <option  value="1" > Medellin</option>
                                        <option value="2">Bello</option>
                                        <option  value="3" >Itagui</option>
                                        <option  value="4" > Caldas</option>
                                        <option value="5" > Estrella</option>
                                        <option value="6" >Sabaneta</option>
                                        <option value="7" >envigado</option>
                                        <option value="8" >copacabana</option>
                                        <option value="9" >Girardota</option>
                                        <option value="10" >Barbosa</option>
                                    </select>

                                </div>
                                <div class="input-field col s6" grupoDiv>

                                    <select class="browser-default" id="barrio1">


                                    </select>

                                </div>

                                <div class="input-field col s6 grupoDiv"> 
                                    <select required class="browser-default"  id="Ficha" name="Ficha">
                                        <option name="opciones" value="" disabled selected>Ficha</option>
                                        <option name="opciones" value="1">901528</option>
                                        <option name="opciones" value="0">981212</option>
                                    </select>

                                </div>
                                <div class="input-field col s6 grupoDiv">
                                    <i class="material-icons prefix" Tiny>perm_contact_calendar</i>

                                    <input type="date"  id="fechaNacimiento"  class="datepicker" name="fechaNacimiento1">
                                    <label class="active" for="Fecha_Nacimiento">Fecha Nacimiento</label>
                                </div>
                                <div> 
                                    <div class="boton col s4 grupoDiv" style="padding-left: 10%;">
                                        <button class="btn waves-effect light-blue accent-4 waves-light " type="button" id="ultimate" >VOLVER</button>
                                        <!--<input  class="waves-effect light-blue accent-4 btn" type="button" id="btnatr" name="btnCancelar" value="VOLVER"/>-->
                                    </div>

                                    <div class="boton col s4 grupoDiv" style="padding-left: 9%;" >
                                        <button class="btn waves-effect light-blue accent-4 waves-light " type="button" id="cancelar" onClick="location.href = 'menuAdministrador.jsp'" >Cancelar</button>
                                        <!-- <input  class="waves-effect light-blue accent-4 btn" type="button" id="cancelar" name="Guardar" value="Cancelar" onClick="location.href='menuAdministrador.jsp'"/>-->
                                    </div>
                                    <div class="boton col s4 grupoDiv" style="padding-left: 8%;" >
                                        <button class="btn waves-effect light-blue accent-4 waves-light " type="button" id="Sgtns22" >SIGUIENTE</button>
                                        <!-- <input  class="waves-effect light-blue accent-4 btn" type="button" id="Sgtns1" name="btnCancelar" value="SIGUIENTE"/>-->
                                    </div>
                                </div>
                            </div>
                            <div id="registro6">
                                <center>
                                    <div class="boton col s12 grupoDiv" style="padding-left: 110px">
                                        <video autoplay="true"></video>
                                        <canvas width="100" height="100"></canvas>
                                    </div>


                                    <div class="boton col s4 grupoDiv">
                                        <button class="btn waves-effect light-blue accent-4 waves-light " type="button" id="volver33">Volver</button>
                                        <!-- <input  class="waves-effect light-blue accent-4 btn" type="button" id="ultimate" name="Volver" value="Volver"/>-->
                                    </div>
                                    <div class="boton col s4 grupoDiv" >
                                        <button class="btn waves-effect light-blue accent-4 waves-light " type="button" id="cancelar" onClick="location.href = 'menuAdministrador.jsp'" >Cancelar</button>
                                        <!-- <input  class="waves-effect light-blue accent-4 btn" type="button" id="cancelar" name="Guardar" value="Cancelar" onClick="location.href='menuAdministrador.jsp'"/>-->
                                    </div>
                                    <div class="boton col s4 grupoDiv"  id="start1" >
                                        <!--<input  class="waves-effect light-blue accent-4 btn" type="button" id="start" name="btnCancelar"  value="Encender"/> -->
                                        <button class="btn waves-effect light-blue accent-4 waves-light " type="button" id="start" >Encender</button>
                                    </div>
                                    <div class="boton col s4 grupoDiv" id="gb">
                                        <button class="btn waves-effect light-blue accent-4 waves-light " type="button" id="Almacenar" >Guardar</button>
                                        <!--    <input  class="waves-effect light-blue accent-4 btn" type="button" id="Almacenar" name="Guardar" value="Guardar"/>-->
                                    </div>
                                    <div class="boton col s4 grupoDiv" id="Capturar1">
                                        <button class="btn waves-effect light-blue accent-4 waves-light " type="button" id="capture" >Capturar</button>
                                        <!--  <input  class="waves-effect light-blue accent-4 btn" type="button" id="capture" name="Capturar" value="Capturar"/>-->
                                    </div>

                                </center>
                            </div>


                        </form>
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
    <script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
    <!--importacion de materializecss javaScript por medio de CDN-->
    <!-- <script src="js/jquery-1.7.min.js" type="text/javascript"></script>
     <script src="js/jquery.featureCarousel.min.js" type="text/javascript"></script>-->
    
    <!--<script src="js/Carru.js" type="text/javascript"></script>
    <!--Import jQuery before materialize.js-->
   
   <script src="js/sweetalert.min.js" type="text/javascript"></script>
    <!--importando en archivo .js ajax permite visualizar las informacion de la base de datos en el sitio web-->
    <script type="text/javascript" src="js/ajax.js"></script>
    <!--Importacion del CDN de Google Chart -->
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <!--menu-->
    <script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js'></script>
    <script src="js/index2.js" type="text/javascript"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.6/js/materialize.min.js"></script>
    <script>
                                            $(document).ready(function () {
                                                inicioCentroAsistencia();
                                                var overlay = $('.sidebar-overlay');
                                                var sidebar = $('#sidebar');
                                                $("#competenciasDictadasXintstructor").click(function () {
                                                    $("#modalCDxI").openModal("#competenciasDictadasXintstructor");

                                                    $(".sidebar-overlay").trigger("click");
                                                });
                                            });
    </script>
     <script src="js/AprendizIns.js" type="text/javascript"></script>
</body>
</html>
