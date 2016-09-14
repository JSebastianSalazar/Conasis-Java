<%-- 
    Document   : MensajeRecuperar
    Created on : 25/08/2016, 06:49:09 PM
    Author     : sebas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.7/css/materialize.min.css">
        <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet"
              <!--Import materialize.css-->
              <link href="css/styles.css" rel="stylesheet" type="text/css"/>
        <link href="css/registroGrupo.css" rel="stylesheet" type="text/css"/>
        <link href="css/sweetalert.css" rel="stylesheet" type="text/css"/>
        <title>CONASIS MENSAJE DE RECUPERACION</title>
    </head>
    <body>
        <nav class="cabecera">
            <p class="grupo">CONASIS</p>
        </nav>
        <div class="linea"></div>

        <!--Manejo de tabs-->
        <div class="contenedorTab ">
            <div class="row">
                <div class="col s12 ">

                    <div class="container " id="manual" style="margin-top: 60px;"> <!--abarca el 70% de la pantalla-->
                        <div class="container" style="background-color: transparent">
                            <div class="container z-depth-3" style="background-color: #ffffff; padding-bottom: 10px;">
                                <div class="container">
                                    <form>
                                        <br>
                                        <div class="row s12">
                                            <center> <h5>Recuperación de contraseña</h5> </center>
                                            <div class="linea"></div>
                                        </div>
                                        <br>
                                        <p style="text-align:center ">Digite la nueva contraseña que se facil de recordar por usted</p>

                                        <div class="input-field">
                                            <i class="material-icons prefix">account_circle</i>
                                            <input id="clave" name="clave" type="password"  required maxlength="100">
                                            <label for="usuario">Contraseña</label>
                                        </div>
                                        <div class="input-field">
                                            <i class="material-icons prefix">account_circle</i>
                                            <input id="clave2" name="clave" type="password"  required maxlength="100">
                                            <label for="usuario"> Confirmar contraseña</label>
                                        </div>
                                        <br>
                                        <br>
                                        <div class="row">
                                            <div class="row col s12">
                                                <div>
                                                    <center><button class="btn waves-effect light-blue accent-4 waves-light" type="button" id="enviar1" name="action" style="padding-left: 45px; padding-right: 45px;">Enviar
                                                        </button></center>
                                                </div>
                                            </div>

                                        </diV>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!--importación de Jquery por medio de CDN-->
            <script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
            <!--importacion de materializecss javaScript por medio de CDN-->
            <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.6/js/materialize.min.js"></script>
            <script src="js/Link.js" type="text/javascript"></script>
            <script src="js/sweetalert.min.js" type="text/javascript"></script>
    </body>

</html>
