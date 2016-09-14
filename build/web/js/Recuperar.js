
$(document).ready(function () {
    $('form').submit(function (evento) {
        evento.preventDefault();
    });
    $("#enviar1").click(function () {
        $.ajax({
            url: "RecuperarClave",
            method: "post",
            data: {
                correo: $("#correo").val()

            },
            success: function () {
                swal("Muy bien ", "Se envio correctamente", "success");
                $(location).attr("href", 'index.html');// redirecciona al inicio
            },
            error: function (respuesta, j, l) {
               
                if (l === "Not Found") {
                    swal({title: "Error", type: "error", text: "Problemas en el servidor de base de datos", timer: 2000, allowEscapeKey: false, showConfirmButton: false});
                } else if (l === "Bad Request") {

                    
                    swal({title: "Error", type: "error", text: "Problemas de conexion a la base de datos", timer: 2000, allowEscapeKey: false, showConfirmButton: false});
                } else {
                    swal({title: "Error", type: "error", text: l, timer: 2000, allowEscapeKey: false, showConfirmButton: false});

                }
            }
            
        });
        $("#correo").each(function () {
            $($(this)).val('');
        });
    });

});

