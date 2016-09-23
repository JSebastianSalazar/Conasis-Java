$("#usuario").keyup(function (event) {
    if (event.keyCode == 13) {
        $("#enviar").click();
    }
});
$("#password").keyup(function (event) {
    if (event.keyCode == 13) {
        $("#enviar").click();
    }
});
$("#body").keyup(function (event) {
    if (event.keyCode == 13) {
        $("#enviar").click();
    }
});

$(document).ready(function () {
    if (annyang) {
      //  annyang.setLenguage("es");
// Let's define our first command. First the text we expect, and then the function it should call
        var commands = {
            'hello': function () {
                $('#enviar').click();//  animate({bottom: '-100px'});
            }
        };
    }

    // Add our commands to annyang
    annyang.addCommands(commands);

    // Start listening. You can call this here, or attach this call to an event, button, etc.
    annyang.start();
    

    $("#enviar").click(function () {
        $.ajax({
            method: "Post",
            url: "LogInServlet",
            data: {
                usuario: $("#usuario").val(),
                password: $("#password").val()
            }, error: function (respuesta, j, l) {


                if (l === "Not Found") {
                    swal({title: "Error", type: "error", text: "Problemas en el servidor de base de datos", timer: 2000, allowEscapeKey: false, showConfirmButton: false});
                } else if (l === "Bad Request") {

                    swal({title: "Error", type: "error", text: "Problemas de conexion a la base de datos", timer: 2000, allowEscapeKey: false, showConfirmButton: false});
                } else {
                    swal({title: "Error", type: "error", text: l, timer: 2000, allowEscapeKey: false, showConfirmButton: false});
                }
            },
            complete: function (jqXHR, textStatus) {



            }, success: function (data, textStatus, jqXHR) {


                window.location.assign("menuAdministrador.jsp");
            }
        }); /*.done(function (respuesta) {
         /*  if (respuesta === "Admin") {
         window.location.assign("menuAdministrador.jsp");
         }
         if (respuesta === "Instructor") {
         window.location.assign("menuInstructor.jsp");
         }
         if (respuesta === "secretaria") {
         window.location.assign("menuSecretaria.jsp");
         }
         
         });*/


    });
});
