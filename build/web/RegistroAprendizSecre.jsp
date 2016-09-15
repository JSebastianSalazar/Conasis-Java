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
        <link href="css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
        <link href="css/sweetalert.css" rel="stylesheet" type="text/css"/>
        <script src="js/sweetalert.min.js" type="text/javascript"></script>
        <script src="js/jquery-1.12.2.js" type="text/javascript"></script>
        <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <!--Import materialize.css-->
        <link type="text/css" rel="stylesheet" href="css/materialize.min.css"  media="screen,projection"/>
        <link href='https://fonts.googleapis.com/css?family=Roboto+Condensed:400,300' rel='stylesheet'   type='text/css'>


        <!--Let browser know website is optimized for mobile-->
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <!-- importacion del jquiery -->
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
        <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script>

        <link href="css/styles.css" rel="stylesheet" type="text/css"/>
        <link href="css/registroGrupo.css" rel="stylesheet" type="text/css"/>
        <script src="js/aprensecre.js" type="text/javascript"></script>


    </head>
    <% Instructor dao = new Instructor();
        List<Usuario> list = new ArrayList();

    %>

    <body style="background: #f2f2f2">
        <nav class="cabecera">
            <p class="grupo">Aprendiz</p>
        </nav>
        <div class="linea"></div>
        <div class="contenedorTab ">
            <div class="row">
                <div class="col s12 ">
                    <ul style="background: #f2f2f2" class="tabs">
                        <li class="tab col s6"><a href="#lista">Lista</a></li>
                        <li class="tab col s6"><a href="#registro">Registrar</a></li>
                    </ul>
                    <div class="container z-depth-3" id="lista" > <!--abarca el 70% de la pantalla-->
                        <div class="row" style="margin-bottom: 5px;">
                            <div class="col s1" id="espacio1"><h1></h1></div>
                            <div class="col s10" id="espacio2">
                                <p class="light titulo" id="espacioT">Lista de aprendices</p>
                                <p class="light parrafo" id="espacioP">Puedes ver todos los Aprendices registrados en el sistema o agregarlos fácilmente para que comiencen a disfrutar de la nueva forma de tomar asistencia y llevar estos registros.</p>
                            </div>
                            <div class="col s1" id="espacio3"><h1></h1></div>
                        </div>
                        <div id="modal1" class="modal">
                            <div class="modal-content">
                                <h4>Modal Header</h4>
                                <p>A bunch of text</p>
                            </div>
                            <div class="modal-footer">
                                <a href="#!" class=" modal-action modal-close waves-effect waves-green btn-flat">Agree</a>
                            </div>
                        </div>
                        <div class="row" style="margin-bottom: 5px;">
                            <div class="col s3"><h1></h1></div>
                            <div class="input-field col s6">
                                <center><i class="material-icons prefix">search</i>
                                    <input id="buscar" type="text" class="validate">
                                    <label for="buscar">Buscar aprendiz</label></center>
                            </div>
                            <div class="col s3"><h1></h1></div>
                        </div>
                        <div class="row">
                            <% list = dao.fotosAprendiz();
                                if (list != null || !list.isEmpty() || list.size() != 0) {
                            %>
                            <div class="carousel ">
                                <%for (int i = 0; i < list.size(); i++) {
                                        Usuario u = new Usuario();
                                        u = list.get(i);
                                %>
                                <a class="carousel-item" href="#"><img src="<%=u.getFoto()%>" alt="" class="circle  imgInstructor"  id="btnss"/> </a>
                                    <%
                                        }
                                    %>
                            </div>
                            <%
                            } else {
                            %>
                            <h5>No hay fotos de instructores</h5>
                            <%
                                }
                            %>

                        </div>
                    </div>
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
                                        <input id="numero_documento" type="text"  maxlength="13"name="numeroDocumento" class="validate" required onKeypress="if (event.keyCode < 45 || event.keyCode > 57)
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
                                        <button class="btn waves-effect light-blue accent-4 waves-light " type="button" id="cancelar" onClick="location.href = 'menuSecretaria.jsp'" >Cancelar</button>
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
                                        <input value="" id="estrato" type="text" name="estrato"  maxlength=" 1"class="validate" onKeypress="if (event.keyCode < 45 || event.keyCode > 57)
                                                    event.returnValue = false;">
                                        <label class="active" for="estrato">estrato</label>
                                    </div>

                                    <div class="input-field col s6 grupoDiv"> 
                                        <i class="material-icons prefix">account_circle</i>
                                        <input value="" id="email" type="email" name="email"   pattern="^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$" class="validate">
                                        <label for="email" data-error="Error" data-success="right">Email</label>
                                    </div>
                                    <br>
                                    <br>
                                    <br>
                                    <br>
                                    <br>
                                    <br>
                                    <br>
                                    <br>
                                    <div> 
                                        <div class="boton col s4 grupoDiv" style="padding-left: 10%;">
                                            <button class="btn waves-effect light-blue accent-4 waves-light " type="button" id="btnatr" >VOLVER</button>
                                            <!--<input  class="waves-effect light-blue accent-4 btn" type="button" id="btnatr" name="btnCancelar" value="VOLVER"/>-->
                                        </div>

                                        <div class="boton col s4 grupoDiv" style="     padding-left: 9%;" >
                                            <button class="btn waves-effect light-blue accent-4 waves-light " type="button" id="cancelar" onClick="location.href = 'menuSecretaria.jsp'" >Cancelar</button>
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
                                        <input type="date"  id="fechaNacimiento"  name="fechaNacimiento1">
                                        <label class="active" for="Fecha_Nacimiento">Fecha Nacimiento</label>
                                    </div>
                                    <div> 
                                        <div class="boton col s4 grupoDiv" style="padding-left: 10%;">
                                            <button class="btn waves-effect light-blue accent-4 waves-light " type="button" id="ultimate" >VOLVER</button>
                                            <!--<input  class="waves-effect light-blue accent-4 btn" type="button" id="btnatr" name="btnCancelar" value="VOLVER"/>-->
                                        </div>

                                        <div class="boton col s4 grupoDiv" style="padding-left: 9%;" >
                                            <button class="btn waves-effect light-blue accent-4 waves-light " type="button" id="cancelar" onClick="location.href = 'menuSecretaria.jsp'" >Cancelar</button>
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
                                            <button class="btn waves-effect light-blue accent-4 waves-light " type="button" id="cancelar" onClick="location.href = 'menuSecretaria.jsp'" >Cancelar</button>
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
        <!--Import jQuery before materialize.js-->
        <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
        <script type="text/javascript" src="js/materialize.min.js"></script>

        <script src="js/js.js" type="text/javascript"></script>
    </body>
</html>
