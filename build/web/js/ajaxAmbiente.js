/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

///*------------------------------------------------AMBIENTE-------------------------------------------------------------------*/
//Inicio del webSite------------------------------------
$(document).ready(function () {

    inicializacionAmbiente();
});

function inicializacionAmbiente() {
    $("#btnModificarAmbiente").hide();
    listarAmbientes();
}

//---- LISTANDO AMBIENTES
////funcion para listar Los ambientes EN UNA TABLA
function listarAmbientes() {
    $.ajax({
        beforeSend: function () {
        },
        method: "POST",
        url: "ServletAmbiente",
        data: {
            validacion: "listaAmbienteTbl"
        }, error: function (jqXHR, estado, error) {
            swal({title: "Error en el Servidor",
                text: "Lo sentimos... Intentalo Nuevamente",
                type: "error",
                timer: 4000,
                showConfirmButton: true});
        },
        complete: function (jqXHR, estado) {
        }
    })
            .done(function (msg) {
                $("#tblListaAmbiente").html(msg);
                //-- datatable ficha
                $('#tblAmbiente').dataTable({
                    'language': {
                        'url': '//cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/Spanish.json'
                    }
                });

            });
}

//validando que los campos del formulario esten llenos
function validarCampos() {
    if ($("#capacidad").val() !== '') {
        $("#lblcapacidad").css("color", "#00c853");
        if ($("#tipoAmbiente").val() !== '') {
            $("#lbltipoAmbiente").css("color", "#00c853");
            if ($("#suministros").val() !== '') {
                $("#lblsuministros").css("color", "#00c853");
                if ($("#observacion").val() !== '') {
                    $("#lblobservacion").css("color", "#00c853");
                    return true;
                } else {
                    swal({title: "Antención",
                        text: "El campo es obligatorio",
                        timer: 1800,
                        showConfirmButton: true});
                    $("#observacion").focus();
                    $("#lblobservacion").css("color", "#d50000");
                    return false;
                }
            }
            else {
                swal({title: "Antención",
                    text: "El campo es obligatorio",
                    timer: 1800,
                    showConfirmButton: true});
                $("#suministros").focus();
                $("#lblsuministros").css("color", "#d50000");
                return false;
            }
        } else {
            swal({title: "Antención",
                text: "El campo es obligatorio",
                timer: 1800,
                showConfirmButton: true});
            $("#tipoAmbiente").focus();
            $("#lbltipoAmbiente").css("color", "#d50000");
            return false;
        }
    } else {
        swal({title: "Antención",
            text: "El campo es obligatorio",
            timer: 1800,
            showConfirmButton: true});

        $("#capacidad").focus();
        $("#lblcapacidad").css("color", "#d50000");
        return false;
    }
}
//guardando ambientes
$("#btnGuardarAmbiente").click(function () {
    if (validarCampos() === true) {
       $.ajax({
           method: "POST",
            url: "ServletAmbiente",
        beforeSend: function (xhr) {              },
            data: {validacion:"guardarA",
                    capacidad: $("#capacidad").val(),
                    tipo: $("#tipoAmbiente").val() ,
                    suministros: $("#suministros").val(),
                    observacion: $("#observacion").val()
                },
            error: function (jqXHR, textStatus, errorThrown) {
                swal({title: "Error",
                                text: errorThrown,
                                type: "error",
                                timer: 2200,
                                showConfirmButton: true});
                },
                success: function (data, textStatus, jqXHR) {
                swal({title: "Perfecto",
                                text: data,
                                type: "success",
                                timer: 2200,
                                showConfirmButton: true});
                            $("#capacidad").val("");
                            $("#tipoAmbiente").val("");
                            $("#suministros").val("");
                            $("#observacion").val("");
                            listarAmbientes();
            },complete: function (jqXHR, textStatus) {
                
            }        });
    }
});//


///---- al pasar el mouse sobre la accón
function ubicacion(id) {//cuando el mouse no esta en cima
    switch ($(id).text()) {
        case "Eliminar":
            $($(id)).css("color", "#d50000");
            break;
        case "Modificar":
            $($(id)).css("color", "#d50000");
            break;
    }
}
function normal(id) {//cuando el mouse no esta encima
    switch ($(id).text()) {
        case "Eliminar":
            $($(id)).css("color", "#039be5");
            break;
        case "Modificar":
            $($(id)).css("color", "#039be5");
            break;
    }
}

//funcion de las acciones eliminar o editar
function accion(id) {//cuando se da clic 
    var ids = $(id).attr('id');
    switch ($(id).text()) {
        case "Eliminar":
            swal({title: "¿Estas seguro?", text: "Al eliminar el ambiente se eliminará información asociada como programaciones",
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "!Si, Eliminar!",
                cancelButtonText: "!No, Cancelar!",
                closeOnConfirm: false,
                closeOnCancel: false},
            function (isConfirm) {
                if (isConfirm) {
                    $.ajax({
                        beforeSend: function (xhr) {
                        },
                        method: "POST",
                        url: "ServletAmbiente",
                        data: {
                            validacion: "eliminarAmbiente",
                            idAmbiente: ids
                        },
                        error: function (jqXHR) {
                            swal({title: "Error",
                                text: jqXHR,
                                type: "error",
                                timer: 2200,
                                showConfirmButton: true});
                        },
                        complete: function (jqXHR, textStatus) {
                        },
                        success: function (data, textStatus, jqXHR) {
                            swal({title: "Perfecto",
                                text: data,
                                type: "success",
                                timer: 2200,
                                showConfirmButton: true});
                            listarAmbientes();
                        }
                    });
                } else {
                    swal("Cancelado", "La ficha no se eliminó", "error");
                }
            });
            break;
        case "Modificar":
            var datos = $(id).attr('value');
            var array = datos.split(",");
            $("#capacidad").val(array[0]);
            $("#tipoAmbiente").val(array[1]);
            $("#suministros").val(array[2]);
            $("#observacion").val(array[3]);
            $("#btnModificarAmbiente").val(array[4]);
            $("#suministros").focus();
            $("#observacion").focus();
            $("#tipoAmbiente").focus();
            $("#capacidad").focus();
            $("#btnGuardarAmbiente").hide();
            $("#btnModificarAmbiente").show();
            $("html, body").animate({scrollTop: 0}, 700);
            return false;
            break;
    }
}

//modificando ambientes
$("#btnModificarAmbiente").click(function () {
    if (validarCampos() === true) {
       $.ajax({
           method: "POST",
            url: "ServletAmbiente",
        beforeSend: function (xhr) {              },
            data: {validacion:"modificarA",
                    capacidad: $("#capacidad").val(),
                    tipo: $("#tipoAmbiente").val() ,
                    suministros: $("#suministros").val(),
                    observacion: $("#observacion").val(),
                    idAmbiente: $("#btnModificarAmbiente").attr('value')
                },
            error: function (jqXHR, textStatus, errorThrown) {
                swal({title: "Error",
                                text: errorThrown,
                                type: "error",
                                timer: 2200,
                                showConfirmButton: true});
                },
                success: function (data, textStatus, jqXHR) {
                swal({title: "Perfecto",
                                text: data,
                                type: "success",
                                timer: 2200,
                                showConfirmButton: true});
                            $("#capacidad").val("");
                            $("#tipoAmbiente").val("");
                            $("#suministros").val("");
                            $("#observacion").val("");
                            $("#btnGuardarAmbiente").show();
                            $("#btnModificarAmbiente").hide();
                            listarAmbientes();
            },complete: function (jqXHR, textStatus) {
                
            }        });
    }
});//