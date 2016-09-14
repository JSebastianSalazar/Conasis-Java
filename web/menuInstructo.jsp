<%-- 
    Document   : menuInstructor
    Created on : 5/09/2016, 09:07:18 PM
    Author     : sebas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <title>Menú Instructor</title>
        <meta charset="utf-8">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.7/css/materialize.min.css">
        <link href="css/Configuracion.css" rel="stylesheet" type="text/css"/>
        <!--Import Google Icon Font-->

        <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link href="css/styles.css" rel="stylesheet" type="text/css"/>
        <link href="css/registroGrupo.css" rel="stylesheet" type="text/css"/>
        <link href="css/slider.css" rel="stylesheet" type="text/css"/>
     <!-- importando el CSS del datable por medio de CDN -->
        <link href="http://cdn.datatables.net/1.10.12/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css"/>
        <!--Let browser know website is optimized for mobile-->
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        

    </head>

    <body>

        <% if (session.getAttribute("usuario") != null) {
                String nombre = "" + session.getAttribute("usuario");
        %>
        <!--Import jQuery before materialize.js-->

        <!--importando hojas de estilos-->


        <nav class="cabecera">
            <div class="nav-wrapper container " style="background-color: transparent">
                <a><%=nombre%></a>
                <div class="right contenedorImgInstructor" style="background-color: transparent">
                    <img src="<%= session.getAttribute("foto")%>" alt="" class="circle responsive-img imgInstructor " data-delay="50" id="imglol"  style="height: 82px; width: 82px;"/>
                    <form action="SalidaAdmi">

                        <div class="dropdown" id="dropdown">  
                            <a class="mainmenu toggle-login"><%= session.getAttribute("tipo")%></a>  
                            <div class="submenu">  
                                <center>     <ul class="menuitems" id="main">  
                                        <li><a href="configuracion.jsp"style="width:  135px;" >Configurar</a></li>  
                                        <li><a href="#" style="width:  135px;">  Ayuda  </a></li>
                                        <li><a href="#" style="width:  135px;"> Diseño</a></li>
                                        <li><a href="#" onclick="$(this).closest('form').submit()" style="width:  135px;"> Salir </a></li>  
                                    </ul> </center> 
                            </div>  
                        </div>  

                    </form>
                </div>
                <!-- clases para movil  -->
                <ul id="nav-mobile" class="side-nav">
                    <li><a href="#">Navbar Link</a></li>
                </ul>
                <a href="#" data-activates="nav-mobile" class="button-collapse" ><i class="material-icons " style="background-color: transparent">menu</i></a>
            </div>
        </nav>
        <div class="linea col s12"></div>
        <div class="container">
            <div class="row" style="margin: 0px" id="UsuariosEstadicticas">
                <div class="card col s5" style="height: 485px">
                    <div class="card-image waves-effect waves-block waves-light">
                        <img class="activator imgGrupo responsive-img" id="imgCarta" src="imagenes/grupo.png" alt="">
                    </div>

                    <div class="card-content cartaInformacion" >
                        <span class="card-title activator grey-text text-darken-4">Usuarios<i class="material-icons right">more_vert</i></span>

                        <p><a href="RegistroAprendizIns.jsp">Aprendices</a></p>
                    </div>
                    <div class="card-reveal grupoDiv">
                        <span class="card-title grey-text text-darken-4">Usuarios<i class="material-icons right">close</i></span><br>
                        <p>Puedes registrar a los usuarios en el sistema para que comiencen a disfrutar de la nueva forma de tomar asistencia,  consultar sus registros,  modificar sus datos y eliminarlos.</p>
                    </div>
                </div>

                <div class="col s2"><h1></h1></div>

                <div class="card col s5" style="height: 485px; margin-top: -10px;">
                    <div class="card-image waves-effect waves-block waves-light">
                        <img class="activator imgGrupo responsive-img" id="imgCarta1" src="imagenes/asistencia.png" alt="">
                    </div>

                    <div class="card-content cartaInformacion" >
                        <span class="card-title activator grey-text text-darken-4">Asistencia<i class="material-icons right">more_vert</i></span>
                        <p><a class="modal-trigger" href="#modalCDxI" id="competenciasDictadasXintstructor" >Ingreso y salida</a></p><!-- asistencia.jsp -->
                        <p><a href="inasistencia.jsp">Justificar falta</a></p>
                        <p><a href="infoAsistencia.jsp">Información de asistencia</a></p>
                    </div>
                    <div class="card-reveal grupoDiv">
                        <span class="card-title grey-text text-darken-4">Asistencia<i class="material-icons right">close</i></span><br>
                        <p>Apartado donde se puede se puede habilitar el registro de asistencia (entrada y salida de clases), el cuál es util para tomar el tiempo preciso de la llegada y salida de los aprendices.</p>
                    </div>
                </div>
            </div>


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
        </div>
        <%} else {
                session.invalidate();
                response.sendRedirect("index.html");
            }%>
        <script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
        <!--importacion de materializecss javaScript por medio de CDN-->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.6/js/materialize.min.js"></script>
        <script src="js/main.js" type="text/javascript"></script>
        <script src="js/js.js" type="text/javascript"></script>
        <script src="js/ajax.js" type="text/javascript"></script>
           <!--importacion de datatable javaScript por medio de CDN-->
        <script type="text/javascript" src="http://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>

    </body>

</html>