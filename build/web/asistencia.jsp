<%-- 
    Document   : asistencia
    Created on : 08-jul-2016, 17:52:14
    Author     : Emerson
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    String ficha= request.getParameter("ficha");
    String nomComp = request.getParameter("nomComp");
    int idProgramacion = Integer.parseInt(request.getParameter("idProgramacion"));
    String bandera = request.getParameter("bandera");
%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- importando materizalizecss CSS por medio de CDN -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.6/css/materialize.min.css">
        <link href="css/styles.css" rel="stylesheet" type="text/css"/>
        <!-- importando el CSS de las alertas SweetAlert -->
        <link href="css/sweetalert.css" rel="stylesheet" type="text/css"/>
        <!-- importando el CDN de los iconos de materializecss-->
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <title>Entrada y Salida</title>
    </head>
    <body style="background-color: #f2f2f2">
        <nav class="cabecera">
            <center><h5 class="grupo">Ingreso y salida de Aprendices</h5></center>
            <!--<div id="mensajes" class="col s3"></div>-->
            </nav>
        <div class="linea"></div>
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
        
        <!--importación de Jquery por medio de CDN-->
        <script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
        <!--importacion de materializecss javaScript por medio de CDN-->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.6/js/materialize.min.js"></script>
        <script src="js/materialize.js" type="text/javascript"></script><!--importando las funciones javaScript de materializecss -->
        <!--importando en archivo .js ajax permite visualizar las informacion de la base de datos en el sitio web-->
        <script type="text/javascript" src="js/ajax.js"></script>
        <!--importacion del javaScript de las Alertas SweetAlert-->
        <script src="js/sweetalert.min.js" type="text/javascript"></script>
        <script>
            $(document).ready(function (){
            inicioAsistencia();
            });
        </script>
    </body>
</html>
