<%-- 
    Document   : ficha
    Created on : 6/09/2016, 06:50:40 AM
    Author     : Sena
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- importando materizalizecss CSS por medio de CDN -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.6/css/materialize.min.css">
        <!-- importando iconos materializecss por medio del CDN-->
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <title>Fichas</title>
        <link href="css/styles.css" rel="stylesheet" type="text/css"/>
        <!-- importando el CSS de las alertas SweetAlert -->
        <link href="css/sweetalert.css" rel="stylesheet" type="text/css"/>
        <link href="//cdn.datatables.net/1.10.12/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css"/>
    </head>
    <body style="background-color: #f2f2f2">

        <nav class="cabecera" id="nav">
            <center><h5 class="grupo">Fichas</h5></center>
            <!--<div id="mensajes" class="col s3"></div>-->
        </nav>
        <div class="linea"></div>
        <div id="cntntMsj"></div>
            <div class="row container" style="margin-top: 2%; ">
                <div id="contenedorControles" style="  padding-bottom: 2px; border-left: 0px; border-right: 0px;">
                    <div class="row z-depth-1" style="background-color: #ffffff; padding-top:15px;">
                        <!-------------------  FORMULARIO FICHA -----------------------------------> 
                        <form action="" class="col s12 m12" id="contenedorFormularioFicha">
                            <div class="row" style="margin-bottom: 0px;">
                                <br>
                                <div class="input-field col s4"  >
                                    <i class="material-icons prefix">done<label for="icon_prefix">example</label></i>
                                    <input id="numeroFicha" type="number" class="validate" >
                                    <label for="icon_prefix" id="lblFicha">Numero ficha</label>
                                </div>
                                <div class="input-field col s4">
                                    <i class="material-icons prefix">today</i>
                                    <input  id="fechaInicio" type="date"  >
                                    <label  class="active" for="first_name2" style="font-size: 15px" id="lblfechaInicio">Fecha inicio</label>
                                </div>
                                <div class="input-field col s4">    
                                    <i class="material-icons prefix">today</i>
                                    <input  id="fechaFinal" type="date"  >
                                    <label class="active" for="first_name2" style="font-size: 15px" id="lblfechaFinal">Fecha final</label>
                                </div>

                                <!---------------   SALTO DE LINEA ------------------------->
                                <div class="row s12 " >
                                    <div class="input-field col s4">
                                        <i class="material-icons prefix">done</i>
                                        <select id="jornada" class="" name="jornada">
                                        </select> 
                                        <label for="icon_prefix" style="font-size: 15px" id="lblJornada">Jornada</label>
                                    </div>
                                    <div class="input-field col s4">
                                        <i class="material-icons prefix">done</i>
                                        <select  class="" name="programas" id="programas">
                                            <option>Selecccione un programa</option>
                                        </select>
                                        <label id="lblPrograma" style="font-size: 15px">Programas</label>
                                    </div>  
                                    <div class="input-field col s4">
                                        <i class="material-icons prefix">done</i>
                                        <select id='instructores' name="instructores">
                                            <option value="">Seleccione</option>
                                        </select>
                                        <label for="instructores" style="font-size: 15px" id="lblGestor">Selecione un gestor</label>
                                    </div>
                                </div>
                            </div>
                            <div class="row" style="margin-bottom: 0px;">
                                <div class="col s2"><h1></h1></div>
                                <div class="col s3"><button class="btn waves-effect light-blue accent-4 waves-light" type="button" name="cancelar" id="btnCancelarFicha"  onClick="location.href = 'menuAdministrador.jsp'" >Cancelar</button></div>
                                <div class="col s2"><h1></h1></div>
                                <div class="col s3" style="padding-left: 10%"><button class="btn waves-effect light-blue accent-4 waves-light" type="button" name="guardar" id="btnGuardarFicha">Guardar</button></div>
                                <div class="col s3" style="padding-left: 10%"><button class="btn waves-effect light-blue accent-4 waves-light" type="button" name="modificar" id="btnModificarFicha" value="">Modificar</button></div>
                                <div class="col s2"><h1></h1></div>
                            </div>
                        </form> <!-- FIN DEL FORMULARIO DE FICHA -->
                    </div>    
                    <!------>
                    <div id="contenedorTablas" style="border-left: 0px; border-right: 0px; padding-left: 0px; padding-right: 0px; background-color: #ffffff"><!--Acá es la tabla de las fichas---->
                        <div class="row z-depth-2" style="border-top: #2196f3 1px solid; padding-top: 10px; margin-top: 5px;">
                            <div class="col s12" id="tblListaFicha">
                                <center>
                                    <img src="imagenes/loader.gif" alt="" style="height: 100px; width: 100px"/><br>
                                    <span style="font-size: 16px">Cargando...</span>
                                </center>
                            </div>

                        </div>
                    </div>
                </div>
            </div>

            <!--importación de Jquery por medio de CDN-->
            <script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
            <!--importacion de materializecss javaScript por medio de CDN-->
            <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.6/js/materialize.min.js"></script>
            <script src="js/materialize.js" type="text/javascript"></script><!--importando las funciones javaScript de materializecss -->
            <!--importacion de datatable javaScript por medio de CDN-->
            <script type="text/javascript" src="http://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>
            <!--Impòrtando el ajax-->
            <script src="js/ajaxFicha.js" type="text/javascript"></script>
            <!--importacion del javaScript de las Alertas SweetAlert-->
            <script src="js/sweetalert.min.js" type="text/javascript"></script>


    </body>
</html>
