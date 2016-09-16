<%-- 
    Document   : IntructorRegistro
    Created on : 11/09/2016, 08:19:30 AM
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
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.7/css/materialize.min.css">
        <link href="css/feature-carousel.css" rel="stylesheet" type="text/css"/>
        <title>Registro Instructores</title>

        <!--Import Google Icon Font-->
        <meta charset="utf-8">
        <link href="css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
        <link href="css/sweetalert.css" rel="stylesheet" type="text/css"/>

        <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <!-- importando el CSS del datable por medio de CDN  DATATABLE-->
        <link href="http://cdn.datatables.net/1.10.12/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css"/>
        <link href='https://fonts.googleapis.com/css?family=Roboto+Condensed:400,300' rel='stylesheet'   type='text/css'>

        <!--Let browser know website is optimized for mobile-->
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <!-- importacion del jquiery -->
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
    %>

    <body style="background: #f2f2f2">
        <nav class="cabecera" >
            <div class="nav-wrapper" style="background-color: transparent">
                <img src="imagenes/conasisLogo.png" alt=""  class="sidebar-toggle" style="height: 80px; width: 130px; margin-left: 5%; cursor: pointer"/>
                <a  class="brand-logo center">Instructores</a>
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
    <!--Lo que aparecerá-->
    <div class="contenedorTab ">
        <div class="row">
            <div class="col s12 ">
                <ul style="background: #f2f2f2" class="tabs">
                    <li class="tab col s6"><a href="#lista">Lista</a></li>
                    <li class="tab col s6"><a href="#registro" >Registrar</a></li>
                </ul>
                <div class="container z-depth-3" id="lista" > <!--abarca el 70% de la pantalla-->
                    <div class="row" style="margin-bottom: 5px;">
                        <div class="col s1" id="espacio1"><h1></h1></div>
                        <div class="col s10" id="espacio2">
                            <p class="light titulo" id="espacioT">Lista de instructores</p>
                            <p class="light parrafo" id="espacioP">Puedes ver todos los instructores registrados en el sistema o agregarlos fácilmente para que comiencen a disfrutar de la nueva forma de tomar asistencia y llevar estos registros.</p>
                        </div>
                        <div class="col s1" id="espacio3"><h1></h1></div>
                    </div>

                    <div class="row" style="margin-bottom: 5px;">
                        <div class="col s3"><h1></h1></div>
                        <div class="input-field col s6">
                            <center><i class="material-icons prefix">search</i>
                                <input id="buscar" type="text" class="validate" >
                                <label for="buscar">Buscar instructor</label></center>
                            <!-- Modal Trigger -->

                        </div>
                        <div class="col s3"><a class="waves-effect waves-light btn modal-trigger"style="margin-top: 22px;"  id="admi"href="#modal78">Administrar</a></div>
                    </div>
                    <div class="row" style="margin-bottom: 0px;">
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
                                                <%=u.getNombre()%>  <%=u.getApellido()%>
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



                    </div>
                </div>
                <div id="modal78" class="modal modal-fixed-footer">
                    <div class="modal-content">
                        <center><h4 >Administrar</h4></center>
                        <br>
                        <br>
                        <center>      <a class="waves-effect waves-light btn" id="actualizarlll" >Actualizar</a>
                            <a class="waves-effect waves-light btn" id="eliminarlll" >Eliminar</a></center>

                        <div class="row" id="contenedorActualiza">
                            <form class="form col s12 "   style="padding-bottom:30px; padding-top: 20px">

                                <div  id="registro4">
                                    <div class="input-field col s6 grupoDiv"> <!--lista de los tipos de documentos-->
                                        <select required class="grupoDiv" id="tipo_Documento">
                                            <option name="opciones" value="" disabled selected></option>
                                            <option name="opciones" value="Cédula de ciudadanía">Cédula de ciudadanía</option>
                                            <option name="opciones" value="Targeta de identidad">Targeta de identidad</option>
                                            <option name="opciones" value="Cédula de extranjería">Cédula de extranjería</option>
                                            <option name="opciones" value="Documento nacional de identificación">Documento nacional de identificación</option>
                                        </select>
                                        <label>Tipo documento</label>
                                    </div>
                                    <div class="input-field col s6 grupoDiv">
                                        <i class="material-icons prefix">view_list</i>
                                        <input id="numero_documento" type="text" name="numeroDocumento" class="validate" required onKeypress="if (event.keyCode < 45 || event.keyCode > 57)
                                                    event.returnValue = false;" maxlength="13">
                                        <label class="active" for="numero_documento">Número documento</label>
                                    </div>
                                    <div class="input-field col s6 grupoDiv">
                                        <i class="material-icons prefix">account_circle</i>
                                        <input id="nombre1" type="text" name="nombre" class="validate" maxlength="50" required required onkeypress="return soloLetras(event);">
                                        <label class="active" for="nombre">Nombres</label>
                                    </div>
                                    <div class="input-field col s6 grupoDiv">
                                        <i class="material-icons prefix">account_circle</i>
                                        <input id="apellido1" type="text" name="apellido" class="validate" maxlength="50" required required onkeypress="return soloLetras(event);">
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
                                    <div class="input-field col s6 grupoDiv">
                                        <i class="material-icons prefix" Tiny>perm_contact_calendar</i>
                                        <input type="date"  id="fechaNacimiento" name="fechaNacimiento1">
                                        <label class="active" for="Fecha_Nacimiento">Fecha Nacimiento</label>
                                    </div>
                                    <div class="input-field col s6 grupoDiv">
                                        <i class="material-icons prefix">phone</i>
                                        <input value="" id="telefono_fijo" type="text" name="fijo" class="validate" onKeypress="if (event.keyCode < 45 || event.keyCode > 57)
                                                    event.returnValue = false;" maxlength="13">
                                        <label class="active" for="telefono_fijo">Telefono fijo</label>
                                    </div>
                                    <div class="input-field col s6 grupoDiv"> 
                                        <i class="material-icons prefix">email</i>
                                        <input value="" id="correo1" type="email" name="correo1" class="validate">
                                        <label for="email" data-error="requiere @" data-success="Muy bien">Email</label>
                                    </div>
                                    <div class="input-field col s6 grupoDiv" style="margin-left: 20%;"> 
                                        <h1></h1>
                                        <h1></h1>
                                    </div>
                                    <br>
                                    <br>
                                    <div> 
                                        <div class="boton col s4 grupoDiv" style="padding-left: 10%;">
                                            <button class="btn waves-effect light-blue accent-4 waves-light " type="button" id="btnatr" >VOLVER</button>
                                            <!--<input  class="waves-effect light-blue accent-4 btn" type="button" id="btnatr" name="btnCancelar" value="VOLVER"/>-->
                                        </div>

                                        <div class="boton col s4 grupoDiv" style="padding-left: 8%;" >
                                            <button class="btn waves-effect light-blue accent-4 waves-light " type="button" id="cancelar" onClick="location.href = 'menuAdministrador.jsp'" >Cancelar</button>
                                            <!-- <input  class="waves-effect light-blue accent-4 btn" type="button" id="cancelar" name="Guardar" value="Cancelar" onClick="location.href='menuAdministrador.jsp'"/>-->
                                        </div>
                                        <div class="boton col s4 grupoDiv" style="padding-left: 9%;" >
                                            <button class="btn waves-effect light-blue accent-4 waves-light " type="button" id="Sgtns1" >SIGUIENTE</button>
                                            <!-- <input  class="waves-effect light-blue accent-4 btn" type="button" id="Sgtns1" name="btnCancelar" value="SIGUIENTE"/>-->
                                        </div>
                                    </div>

                                </div>
                                <div  id="registro5" >
                                    <center>
                                        <div class="boton col s12 grupoDiv">
                                            <video autoplay="true" style="width: 400px; height: 300px;" ></video>
                                            <canvas width="110" height="90"></canvas>
                                        </div>
                                        <div class="boton col s4 grupoDiv">
                                            <button class="btn waves-effect light-blue accent-4 waves-light " type="button" id="ultimate">Volver</button>
                                            <!-- <input  class="waves-effect light-blue accent-4 btn" type="button" id="ultimate" name="Volver" value="Volver"/>-->
                                        </div>
                                        <div class="boton col s4 grupoDiv" >
                                            <button class="btn waves-effect light-blue accent-4 waves-light " type="button" id="cancelar" onClick="location.href = 'menuAdministrador.jsp'" >Cancelar</button>
                                            <!-- <input  class="waves-effect light-blue accent-4 btn" type="button" id="cancelar" name="Guardar" value="Cancelar" onClick="location.href='menuAdministrador.jsp'"/>-->
                                        </div>

                                        <div class="boton col s4 grupoDiv" id="Capturar1">
                                            <button class="btn waves-effect light-blue accent-4 waves-light " type="button" id="capture" >Capturar</button>
                                            <!--  <input  class="waves-effect light-blue accent-4 btn" type="button" id="capture" name="Capturar" value="Capturar"/>-->
                                        </div>
                                        <div class="boton col s4 grupoDiv"  id="start1" >
                                            <!--<input  class="waves-effect light-blue accent-4 btn" type="button" id="start" name="btnCancelar"  value="Encender"/> -->
                                            <button class="btn waves-effect light-blue accent-4 waves-light " type="button" id="start" >Encender</button>
                                        </div>
                                        <div class="boton col s4 grupoDiv" id="gb">
                                            <button class="btn waves-effect light-blue accent-4 waves-light " type="button" id="Actualizar" >Modificar</button>
                                        </div>
                                    </center>
                                </div>

                            </form>
                        </div>


                        <div>



                        </div>


                    </div>
                    <div class="modal-footer">
                        <a href="#!" class="modal-action modal-close waves-effect waves-green btn-flat " id="cancelll">Cancelar</a>
                    </div>
                </div>
                <div class="container" id="registro" style="background: #ffffff; padding-bottom: 0px;padding-left: 0px;padding-right: 0px; margin-top: 15px;" > <!--abarca el 70% de la pantalla-->
                    <div class="row" >
                        <form class="form col s12 z-depth-3"   style="padding-bottom:15px; padding-top: 20px">

                            <div  id="registro4G">
                                <div class="input-field col s6 grupoDiv"> <!--lista de los tipos de documentos-->
                                    <select required class="grupoDiv" id="tipo_DocumentoG">
                                        <option name="opciones" value="" disabled selected></option>
                                        <option name="opciones" value="Cédula de ciudadanía">Cédula de ciudadanía</option>
                                        <option name="opciones" value="Targeta de identidad">Targeta de identidad</option>
                                        <option name="opciones" value="Cédula de extranjería">Cédula de extranjería</option>
                                        <option name="opciones" value="Documento nacional de identificación">Documento nacional de identificación</option>
                                    </select>
                                    <label>Tipo documento</label>
                                </div>
                                <div class="input-field col s6 grupoDiv">
                                    <i class="material-icons prefix">view_list</i>
                                    <input id="numero_documentoG" type="text" name="numeroDocumento" class="validate" required onKeypress="if (event.keyCode < 45 || event.keyCode > 57)
                                                event.returnValue = false;" maxlength="13">
                                    <label class="active" for="numero_documento">Número documento</label>
                                </div>
                                <div class="input-field col s6 grupoDiv">
                                    <i class="material-icons prefix">account_circle</i>
                                    <input id="nombre1G" type="text" name="nombre" class="validate" maxlength="50" required required onkeypress="return soloLetras(event);">
                                    <label class="active" for="nombre">Nombres</label>
                                </div>
                                <div class="input-field col s6 grupoDiv">
                                    <i class="material-icons prefix">account_circle</i>
                                    <input id="apellido1G" type="text" name="apellido" class="validate" maxlength="50" required required onkeypress="return soloLetras(event);">
                                    <label class="active" for="apellido">Apellidos</label>
                                </div>
                                <div class="boton col s6 grupoDiv" style="margin-left: 20%;" >
                                    <button class="btn waves-effect light-blue accent-4 waves-light " type="button" id="cancelarG" onClick="location.href = 'menuAdministrador.jsp'" >Cancelar</button>
                                    <!-- <input  class="waves-effect light-blue accent-4 btn" type="button" id="cancelar" name="Guardar" value="Cancelar" onClick="location.href='menuAdministrador.jsp'"/>-->
                                </div>
                                <div class="boton col s3 grupoDiv" >
                                    <button class="btn waves-effect light-blue accent-4 waves-light " type="button" id="btnsG"  >Siguiente</button>
                                    <!-- <input  class="waves-effect light-blue accent-4 btn" type="button" id="btns" name="btnCancelar" value="Siguiente "/>-->
                                </div>
                            </div>


                            <div  id="registro3G">
                                <div class="input-field col s6 grupoDiv">
                                    <i class="material-icons prefix" Tiny>perm_contact_calendar</i>
                                    <input type="date"  id="fechaNacimientoG" name="fechaNacimiento1">
                                    <label class="active" for="Fecha_Nacimiento">Fecha Nacimiento</label>
                                </div>
                                <div class="input-field col s6 grupoDiv">
                                    <i class="material-icons prefix">phone</i>
                                    <input value="" id="telefono_fijoG" type="text" name="fijo" class="validate" onKeypress="if (event.keyCode < 45 || event.keyCode > 57)
                                                event.returnValue = false;" maxlength="13">
                                    <label class="active" for="telefono_fijo">Telefono fijo</label>
                                </div>
                                <div class="input-field col s6 grupoDiv"> 
                                    <i class="material-icons prefix">email</i>
                                    <input value="" id="correo1G" type="email" name="correo1" class="validate">
                                    <label for="email" data-error="Requiere '@'" data-success="Muy bien ">Email</label>
                                </div>
                                <div class="input-field col s6 grupoDiv" style="margin-left: 20%;"> 
                                    <h1></h1>
                                    <h1></h1>
                                </div>
                                <br>
                                <br>
                                <div> 
                                    <div class="boton col s4 grupoDiv" style="padding-left: 10%;">
                                        <button class="btn waves-effect light-blue accent-4 waves-light " type="button" id="btnatrG" >VOLVER</button>
                                        <!--<input  class="waves-effect light-blue accent-4 btn" type="button" id="btnatr" name="btnCancelar" value="VOLVER"/>-->
                                    </div>

                                    <div class="boton col s4 grupoDiv" style="padding-left: 8%;" >
                                        <button class="btn waves-effect light-blue accent-4 waves-light " type="button" id="cancelarG" onClick="location.href = 'menuAdministrador.jsp'" >Cancelar</button>
                                        <!-- <input  class="waves-effect light-blue accent-4 btn" type="button" id="cancelar" name="Guardar" value="Cancelar" onClick="location.href='menuAdministrador.jsp'"/>-->
                                    </div>
                                    <div class="boton col s4 grupoDiv" style="padding-left: 9%;" >
                                        <button class="btn waves-effect light-blue accent-4 waves-light " type="button" id="Sgtns1G" >SIGUIENTE</button>
                                        <!-- <input  class="waves-effect light-blue accent-4 btn" type="button" id="Sgtns1" name="btnCancelar" value="SIGUIENTE"/>-->
                                    </div>
                                </div>

                            </div>
                            <div  id="registro5G" >
                                <center>
                                    <div class="boton col s12 grupoDiv" style="padding-left: 110px">
                                        <video autoplay="true" id="videoG" style="width:600px; height:400px;"></video>
                                        <canvas width="130" height="100" id="canvasG"></canvas>
                                    </div>
                                    <div class="boton col s4 grupoDiv">
                                        <button class="btn waves-effect light-blue accent-4 waves-light " type="button" id="ultimateG">Volver</button>
                                        <!-- <input  class="waves-effect light-blue accent-4 btn" type="button" id="ultimate" name="Volver" value="Volver"/>-->
                                    </div>
                                    <div class="boton col s4 grupoDiv" >
                                        <button class="btn waves-effect light-blue accent-4 waves-light " type="button" id="cancelarG" onClick="location.href = 'menuAdministrador.jsp'" >Cancelar</button>
                                        <!-- <input  class="waves-effect light-blue accent-4 btn" type="button" id="cancelar" name="Guardar" value="Cancelar" onClick="location.href='menuAdministrador.jsp'"/>-->
                                    </div>

                                    <div class="boton col s4 grupoDiv" id="CaptureG">
                                        <button class="btn waves-effect light-blue accent-4 waves-light " type="button" id="captureG" >Capturar</button>
                                        <!--  <input  class="waves-effect light-blue accent-4 btn" type="button" id="capture" name="Capturar" value="Capturar"/>-->
                                    </div>
                                    <div class="boton col s4 grupoDiv"  id="startG" >
                                        <!--<input  class="waves-effect light-blue accent-4 btn" type="button" id="start" name="btnCancelar"  value="Encender"/> -->
                                        <button class="btn waves-effect light-blue accent-4 waves-light " type="button" id="startG" >Encender</button>
                                    </div>
                                    <div class="boton col s4 grupoDiv" id="gbG">
                                        <button class="btn waves-effect light-blue accent-4 waves-light " type="button" id="SgtnsG" >Guardar</button>

                                        <!--      <input  class="waves-effect light-blue accent-4 btn" type="button" id="SgtnsG" name="Guardar" value="Guardar"/> -->
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
    <!--Import jQuery before materialize.js-->
    <!--importación de Jquery por medio de CDN-->
    <script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
      <!--menu-->
    <script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js'></script>
    <script src="js/index2.js" type="text/javascript"></script>
    <script src="js/jquery-1.7.min.js" type="text/javascript"></script>
    <script src="js/jquery.featureCarousel.min.js" type="text/javascript"></script>
    <script src="js/usuario.js" type="text/javascript"></script>
    <!--importacion de materializecss javaScript por medio de CDN-->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.6/js/materialize.min.js"></script>
     <!--importacion de datatable javaScript por medio de CDN-->
            <script type="text/javascript" src="http://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>
    <script src="js/sweetalert.min.js" type="text/javascript"></script>
    <script src="js/js.js" type="text/javascript"></script>
    <script src="js/Carru.js" type="text/javascript"></script>
  <script>
            $(document).ready(function () {
            $("#competenciasAdministrador").click(function (){
                                        $("#modalCDxI").openModal("#competenciasAdministrador");
                                        $(".sidebar-overlay").trigger("click");
                                    });
                                  });
        </script>
</body>
</html>
