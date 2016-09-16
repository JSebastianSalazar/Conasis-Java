

//Inicio del webSite------------------------------------
$(document).ready(function () {
    $("#cntntMsj").hide();
    $("#btnModificarFicha").hide();
    $("#btnModificarAmbiente").hide();
    listarProgramasCobobox();
    selectJSON();
    listarInstructores();
    listarFichas();
    listarTblFichas();
    inicializacionAmbiente();
});

///--------LISTANDO PROGRAMAS en lista desplegable----------------------///
//funcion para listar los programas
function listarProgramasCobobox() {
    $.ajax({
        beforeSend: function (xhr) {

        },
        method: "POST",
        url: "ServletPrograma",
        data: {
            validacion: "programasComobox"
        },
        error: function (jqXHR, textStatus, errorThrown) {
            swal({title: "Error en el Servidor",
                text: "Lo sentimos... Intentalo Nuevamente",
                type: "error",
                timer: 4000,
                showConfirmButton: true});
        },
        complete: function (jqXHR, textStatus) {

        }
    })
            .done(function (msg) {
                //$("#jsonphp").html(msg);
                //print(msg); funcion que convierte a pdf
                $('select[name=programas] option').remove();
                var myObject = eval('(' + msg + ')');
                $('select[name=programas]').append('<option name="opciones" value="" disabled selected>Seleccione</option>');
                for (var i = 0; i < myObject.length + 1; i++) {
                    $('select').material_select();//funcion materialize para actualizar el combobox
                    $('select[name=programas]').append('<option value=' + myObject[i].idPrograma + '>' + myObject[i].nomPrograma + '</option>');
                }
            });
}

//----- MOSTRANDO JORNADAS
function selectJSON() {
    var data = [
        {"ID": 0, "Name": "Mixta"},
        {"ID": 1, "Name": "Mañana"},
        {"ID": 2, "Name": "Tarde"},
        {"ID": 3, "Name": "Noche"}
    ];
    $('select[name=jornada] option').remove();
    $("#jornada").append('<option name="opciones" value="" disabled selected>Seleccione</option>');
    for (var i = 0; i < data.length; i++) {
        $("#jornada").append('<option value="' + data[i].Name + '">' + data[i].Name + '</option>');
    }
}

///--------LISTANDO INSTRUCTORES en lista desplegable----------------------///
//funcion para listar los instructores
function listarInstructores() {
    $.ajax({
        beforeSend: function (xhr) {

        },
        method: "POST",
        url: "ServletProgramacion",
        data: {
            validacion: "comboboxInstructores"
        },
        error: function (jqXHR, textStatus, errorThrown) {
            swal({title: "Error en el Servidor",
                text: "Lo sentimos... Intentalo Nuevamente",
                type: "error",
                timer: 4000,
                showConfirmButton: true});
        },
        complete: function (jqXHR, textStatus) {

        }
    })
            .done(function (msg) {
                //$("#jsonphp").html(msg);
                //print(msg); funcion que convierte a pdf
                $('select[name=instructores] option').remove();
                var myObject = eval('(' + msg + ')');
                $('select[name=instructores]').append('<option name="opciones" value="" disabled selected>Seleccione</option>');
                for (var i = 0; i < myObject.length + 1; i++) {
                    $('select').material_select();//funcion materialize para actualizar el combobox
                    $('select[name=instructores]').append('<option value=' + myObject[i].nombre + " " + myObject[i].apellido + '>' + myObject[i].nombre + " " + myObject[i].apellido + '</option>');
                }

            });
}

//---- LISTANDO FICHAS
////funcion para listar LAS FICHAS EN UNA TABLA
function listarFichas() {
    $.ajax({
        beforeSend: function () {
        },
        method: "POST",
        url: "ServletFicha",
        data: {
            validacion: "listarFichas"
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
                $("#tblListaFicha").html(msg);
                //-- datatable ficha
                $('#tblFichas').dataTable({
                    'language': {
                        'url': '//cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/Spanish.json'
                    }
                });

            });
}

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
            swal({title: "¿Estas seguro?", text: "Al eliminar la ficha se eliminara información asociada como programaciones",
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
                        url: "ServletFicha",
                        data: {
                            validacion: "eliminarFicha",
                            idFicha: ids
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
                            listarFichas();
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
            $("#numeroFicha").focus();
            $("#numeroFicha").val(array[1]);
            $("#fechaInicio").val(array[2]);
            $("#fechaFinal").val(array[3]);
            $("#btnGuardarFicha").hide();
            $("#btnModificarFicha").show();
            $("#btnModificarFicha").val($(id).attr('id'));
            $("html, body").animate({scrollTop: 0}, 700);
            return false;
            break;
    }
}

//---
//
//comparando fechas
function validandoFecha() {
    if ($("#fechaInicio").val() > $("#fechaFinal").val() || $("#fechaInicio").val() === $("#fechaFinal").val()) {

        swal({title: "Antención",
            text: "La fecha de inicio debe ser menor a la fecha final",
            timer: 1800,
            showConfirmButton: true});
        $("#fechaFinal").focus();
        $("#lblfechaFinal").css("color", "#d50000");
        $("#fechaInicio").focus();
        $("#lblfechaInicio").css("color", "#d50000");
        return false;
    } else {
        return true;
    }
}
//validando que los campos del formulario esten llenos
function validarCampos() {
    if ($("#numeroFicha").val() !== '') {
        $("#lblFicha").css("color", "#00c853");
        if ($("#fechaInicio").val() !== '') {
            $("#lblfechaInicio").css("color", "#00c853");
            if ($("#fechaFinal").val() !== '') {
                $("#lblfechaFinal").css("color", "#00c853");
                if ($("#jornada").val().trim() !== '') {
                    $("#lblJornada").css("color", "#00c853");
                    if ($("#programas").val().trim() !== '') {
                        $("#lblPrograma").css("color", "#00c853");
                        if ($("#instructores").val().trim() !== '') {
                            $("#lblGestor").css("color", "#00c853");
                            return true;
                        } else {
                            swal({title: "Antención",
                                text: "El campo es obligatorio",
                                timer: 1800,
                                showConfirmButton: true});
                            $("#instructores").focus();
                            $("#lblGestor").css("color", "#d50000");
                            return false;
                        }
                    } else {
                        swal({title: "Antención",
                            text: "El campo es obligatorio",
                            timer: 1800,
                            showConfirmButton: true});
                        $("#programas").focus();
                        $("#lblPrograma").css("color", "#d50000");
                        return false;
                    }
                } else {
                    swal({title: "Antención",
                        text: "El campo es obligatorio",
                        timer: 1800,
                        showConfirmButton: true});
                    $("#jornada").focus();
                    $("#lblJornada").css("color", "#d50000");
                    return false;
                }
            }
            else {
                swal({title: "Antención",
                    text: "El campo es obligatorio",
                    timer: 1800,
                    showConfirmButton: true});
                $("#fechaFinal").focus();
                $("#lblfechaFinal").css("color", "#d50000");
                return false;
            }
        } else {
            swal({title: "Antención",
                text: "El campo es obligatorio",
                timer: 1800,
                showConfirmButton: true});
            $("#fechaInicio").focus();
            $("#lblfechaInicio").css("color", "#d50000");
            return false;
        }
    } else {
        swal({title: "Antención",
            text: "El campo es obligatorio",
            timer: 1800,
            showConfirmButton: true});
        $("#numeroFicha").focus();
        $("#lblFicha").css("color", "#d50000");
        return false;
    }
}

//guardar la programación
//FILTRANDO las fichas por programa
var ProgramaCombobox = null;
$('select[name=programas]').on('change', function () {
    ProgramaCombobox = $('select[name=programas]').val();//saber value del combobox seleccionado
});
// 
$("#btnGuardarFicha").click(function () {
    if (validarCampos() === true) {
        if (validandoFecha() === true) {
            $.ajax({
                beforeSend: function (xhr) {
                },
                method: "POST",
                url: "ServletFicha",
                data: {
                    validacion: "guardarFicha",
                    numficha: $("#numeroFicha").val(),
                    fechaInicio: $("#fechaInicio").val(),
                    fechaFinal: $("#fechaFinal").val(),
                    jornada: $("#jornada").val(),
                    idPrograma: ProgramaCombobox,
                    gestor: $("#instructores option:selected").text()
                },
                error: function (jqXHR, j, l) {
                    if (l === "La ficha ya existe") {
                        $("#lblFicha").css("color", "#d50000");
                        $("#numeroFicha").val("");
                    } else {
                        $("#numeroFicha").val("");
                        $("#fechaInicio").val("");
                        $("#fechaFinal").val("");
                        selectJSON();
                        listarInstructores();
                        listarProgramasCobobox();
                    }
                    swal({title: "Error",
                        text: l,
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
                    $("#numeroFicha").val("");
                    $("#fechaInicio").val("");
                    $("#fechaFinal").val("");
                    listarInstructores();
                    selectJSON();
                    listarProgramasCobobox();
                    listarFichas();
                }
            });
        }
    }
});//

//---modificar la ficha
$("#btnModificarFicha").click(function () {
    if (validarCampos() === true) {
        if (validandoFecha() === true) {
            $.ajax({
                beforeSend: function (xhr) {
                },
                method: "POST",
                url: "ServletFicha",
                data: {
                    validacion: "modificarFicha",
                    numficha: $("#numeroFicha").val(),
                    fechaInicio: $("#fechaInicio").val(),
                    fechaFinal: $("#fechaFinal").val(),
                    jornada: $("#jornada").val(),
                    idPrograma: ProgramaCombobox,
                    gestor: $("#instructores option:selected").text(),
                    idF : $("#btnModificarFicha").attr('value')
                },
                error: function (jqXHR, j, l) {                    
                    swal({title: "Error",
                        text: l,
                        type: "error",
                        timer: 2200,
                        showConfirmButton: true});
                },
                complete: function (jqXHR, textStatus) {                },
                success: function (data, textStatus, jqXHR) {
                    swal({title: "Perfecto",
                        text: data,
                        type: "success",
                        timer: 2200,
                        showConfirmButton: true});
                    $("#numeroFicha").val("");
                    $("#fechaInicio").val("");
                    $("#fechaFinal").val("");
                    listarInstructores();
                    selectJSON();
                    listarProgramasCobobox();
                    listarFichas();
                    $("#btnGuardarFicha").show();
                    $("#btnModificarFicha").hide();
                }
            });
        }
    }
});

//competencias que las fichas veran en el día
$("#competenciasAdministrador").click(function () {
    $("#mdAceptarProgramacion").hide();
    $.ajax({
        beforeSend: function (xhr) {

        },
        method: "POST",
        url: "ServletProgramacion",
        data: {
            validacion: "asistenciaAdministrador"
        },
        error: function (jqXHR, textStatus, errorThrown) {
            swal({title: "Error en el Servidor",
                text: "Lo sentimos... Intentalo Nuevamente",
                type: "error",
                timer: 4000,
                showConfirmButton: true});
        },
        complete: function (jqXHR, textStatus) {

        }
    })
            .done(function (msg) {
                //alert(msg);
                $("#tblcompetenciasDictadasXintstructor").html(msg);
            });
});
