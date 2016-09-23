<%-- 
    Document   : RecuperarClave
    Created on : 10/09/2016, 02:41:25 PM
    Author     : sebas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="icon" type="image/png" href="imagenes/conasis2.png" />
        <link href="css/sweetalert.css" rel="stylesheet" type="text/css"/>
        <!-- importando iconos materializecss por medio del CDN-->
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <!-- Compiled and minified CSS -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.7/css/materialize.min.css">

        <!--importando hojas de estilos-->
        <link href="css/styles.css" rel="stylesheet" type="text/css"/>
        <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <!--Import materialize.css-->
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/registroGrupo.css" rel="stylesheet" type="text/css"/>

        <title>RECUPERAR CONTRASEÑA</title>

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

                    <div class="container " id="manual" style="margin-top: 80px;"> <!--abarca el 70% de la pantalla-->
                        <div class="container" style="background-color: transparent">
                            <div class="container z-depth-3" style="background-color: #ffffff; padding-bottom: 20px;">
                                <div class="container">
                                    <form>
                                        <br>

                                        <center>   <h5>Recuperar contraseña</h5></center>
                                        <div class="linea"></div>
                                        <br>
                                        <p>Digite su correo con el cual hizo su registro en CONASIS</p>

                                        <div class="input-field">
                                            <i class="material-icons prefix">account_circle</i>
                                            <input id="correo" name="usuario" type="email" class="validate" required="@">
                                            <label for="email" data-error="Error" data-success="right">Correo</label>
                                        </div>
                                        <br>
                                        <div class="linea"></div>
                                        <p style="text-align: center;">Clikea enviar y revisa tu correo</p>
                                        <div class="row">
                                            <br>
                                            <div class="row col s6">
                                                <div >
                                                    <button class="btn waves-effect light-blue accent-4 waves-light" type="button" id="cancelar" onClick="location.href = 'index.html'" >Cancelar

                                                    </button>

                                                </div>
                                            </div>
                                            <div class="row col s6">
                                                <div style=" margin-left:  20px;">
                                                    <button class="btn waves-effect light-blue accent-4 waves-light" type="button" id="enviar1" style="width: 140px" >Enviar

                                                    </button>
                                                </div>
                                            </div>
                                        </div>


                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
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
        <!--importación de Jquery por medio de CDN-->
        <script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
        <!--importacion de materializecss javaScript por medio de CDN-->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.6/js/materialize.min.js"></script>

        <script src="js/Recuperar.js" type="text/javascript"></script>
        <script src="js/sweetalert.min.js" type="text/javascript"></script>
    </body>
</html>
