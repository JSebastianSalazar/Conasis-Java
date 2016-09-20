$(document).ready(function () {
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
               
               
               
            },success: function (data, textStatus, jqXHR) {
               
               
                window.location.assign("menuAdministrador.jsp");
            }
        });/*.done(function (respuesta) {
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
