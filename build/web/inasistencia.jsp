<%-- 
    Document   : asistencia2
    Created on : 22-ago-2016, 13:49:20
    Author     : Emerson
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%--
    String ficha= request.getParameter("ficha");
    String nomComp = request.getParameter("nomComp");
    int idProgramacion = Integer.parseInt(request.getParameter("idProgramacion"));
--%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- importando materizalizecss CSS por medio de CDN -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.6/css/materialize.min.css">
        <!-- importando iconos materializecss por medio del CDN-->
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link href="css/styles.css" rel="stylesheet" type="text/css"/>
        <!-- importando el CSS de las alertas SweetAlert -->
        <link href="css/sweetalert.css" rel="stylesheet" type="text/css"/>
        <!-- importando el CSS del datable por medio de CDN -->
        <link href="http://cdn.datatables.net/1.10.12/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css"/>
        <title>Entrada y Salida</title>
    </head>
    <body style="background-color: #f2f2f2">
        <nav class="cabecera">
            <center><h5 class="grupo">Inasistencias</h5></center>
            <!--<div id="mensajes" class="col s3"></div>-->
            </nav>
        <div class="linea"></div>
        <div id="cntntMsj"></div>
       <div >
        <div class="row" style="margin-top: 2%;">
            <!--<input type="button" id="ggggg"/>-->
        <div id="contenedorControles" style="border-left:#f2f2f2 170px solid;  border-right:#f2f2f2 170px solid;  padding-bottom: 2px; ">
                       <div class="row z-depth-1" style="background-color: #ffffff; padding-left: 25px; padding-top: 15px;">
        <div class="row" style="margin-bottom: 5px;"><!-- Seleccion programa o competencia -->
            <h5 class="text-aling center">Fichas</h5>
                <div class="row col s12" id="contenedorFichasInstructor">  
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
            <div  style="border-left:#f2f2f2 70px solid; border-right:#f2f2f2 70px solid; background-color: #ffffff;" id="contenedorGeneralTablaIna">
                <div class="row z-depth-2" style="border-top: #2196f3 1px solid; padding-top: 10px; margin-top: 5px;">
            <div class="col s12" id="contenedorTablasInasistenciaFicha">
                <!--Se muestran las competencia que el instructor dictó clase (competencia y fecha)-->
                <div class="col s12" id="">
                        <center>
                            <img src="imagenes/loader.gif" alt="" style="height: 100px; width: 100px"/><br>
                            <span style="font-size: 16px">Cargando...</span>
                        </center>
                    </div>
            </div>
        </div> 
        </div>
            
            
        <div  style="border-left:#f2f2f2 70px solid; border-right:#f2f2f2 70px solid; background-color: #ffffff;" id="contenedorTablaFaltasAprendices">
                <div class="row z-depth-2" style="border-top: #2196f3 1px solid; padding-top: 10px; margin-top: 5px;">
            <div class="col s12" id="TablaFaltasAprendices">
                <!--Se muestran los aprendices que faltaron ese día a formaion-->
                <div class="col s12" id="">
                        <center>
                            <img src="imagenes/loader.gif" alt="" style="height: 100px; width: 100px"/><br>
                            <span style="font-size: 16px">Cargando...</span>
                        </center>
                    </div>
                   <!-- Modal Structure -->
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
        <!--importación de Jquery por medio de CDN-->
        <script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
        <!--importacion de materializecss javaScript por medio de CDN-->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.6/js/materialize.min.js"></script>
        <script src="js/materialize.js" type="text/javascript"></script><!--importando las funciones javaScript de materializecss -->
        <!--importando en archivo .js ajax permite visualizar las informacion de la base de datos en el sitio web-->
        <script type="text/javascript" src="js/ajax.js"></script>
        <!--importacion del javaScript de las Alertas SweetAlert-->
        <script src="js/sweetalert.min.js" type="text/javascript"></script>
        <!--importacion de datatable javaScript por medio de CDN-->
        <script type="text/javascript" src="http://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>
    </body>
</html>
