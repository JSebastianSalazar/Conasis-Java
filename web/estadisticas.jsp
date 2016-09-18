<%-- 
    Document   : estadisticas
    Created on : 18-sep-2016, 11:34:56
    Author     : Emerson
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
        <!-- importando el CSS de las alertas SweetAlert -->
        <link href="css/sweetalert.css" rel="stylesheet" type="text/css"/>
        
        <title>Estadisticas</title>
        
    </head>
    <body>
       <div class="input-field col s6 "> <!--lista de fichas-->
                <select  class="" name="fichas" id="fichas">
                    <option  value="" disabled selected>Selecccione una ficha</option>
                </select>
                <label id="lblFicha" style="font-size: 14px">Fichas</label>
            </div>
        
        <div id="stdGenero" style="width: 900px; height: 300px;">
            
        </div>
        <!--importación de Jquery por medio de CDN-->
        <script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
        <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
        <!--importacion de materializecss javaScript por medio de CDN-->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.6/js/materialize.min.js"></script>
         <!--importacion del javaScript de las Alertas SweetAlert-->
        <script src="js/sweetalert.min.js" type="text/javascript"></script>
        <script src="js/estadisticas.js" type="text/javascript"></script>
        
    </body>
</html>
