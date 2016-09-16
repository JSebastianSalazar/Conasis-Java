/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var fichaAsistencia = null;

$(document).ready(function () {
    listarProgramas();
    listarCompetencias();
    listarInstructores();
    listarFichas();
    listarProgramasCobobox();
    cuadrosFichasTodas();
    visualizacionInicio();
    fichasInstructor();
    inicioInasistencia();
    inicio();
    //tablasConDatatable();
});
function inicio() {
    $("#vNumeroDocA").hide();
    $("#vTipoA").hide();
    $("#preloader").hide();
    $("#imgPreloaderPrograComp").hide();
    $("#imgPreloaderCompeT").hide();
    configuracionBotones();
    $("#contenedorProgramacionFichas").hide();
    //$("#imgPreloaderProgramacionFichas").hide();
    $("#cntntMsj").hide();
}
//funcion para listar los programas en un combobox
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
                $('select[name=programas]').append('<option name="opciones" value="" >Seleccione</option>');
                for (var i = 0; i < myObject.length + 1; i++) {
                    $('select').material_select();//funcion materialize para actualizar el combobox
                    $('select[name=programas]').append('<option value=' + myObject[i].idPrograma + '>' + myObject[i].nomPrograma + '</option>');

                }

            });
}

////funcion para listar los programas
function listarProgramas() {

    $.ajax({
        beforeSend: function () {
            $("#imgPreloaderPrograComp").show();
        },
        method: "POST",
        url: "ServletPrograma",
        data: {
            validacion: "vListar"
        }, error: function (jqXHR, estado, error) {
            swal({title: "Error en el Servidor",
                text: "Lo sentimos... Intentalo Nuevamente",
                type: "error",
                timer: 4000,
                showConfirmButton: true});
        },
        complete: function (jqXHR, estado) {
            $("#imgPreloaderPrograComp").hide();
        }
    })
            .done(function (msg) {
                $("#tablaPrograma").html(msg);
            });
}
//funcion para listar competencias en combobox
function listarCompetencias() {
    $.ajax({
        beforeSend: function (xhr) {

        },
        method: "POST",
        url: "ServletCompetencia",
        type: "get",
        datatype: "json",
        data: {
            validacion: "combobox"
        },
        error: function (jqXHR, textStatus, errorThrown) {

        },
        complete: function (jqXHR, textStatus) {
            swal({title: "Error en el Servidor",
                text: "Lo sentimos... Intentalo Nuevamente",
                type: "error",
                timer: 4000,
                showConfirmButton: true});
        }
    })
            .done(function (msg) {
                //print(msg); funcion que convierte a pdf
                $('select[name=competencias] option').remove();
                var myObject = eval('(' + msg + ')');
                $('select[name=competencias]').append('<option name="opciones" value="">Seleccione</option>');
                for (var i = 0; i < myObject.length + 1; i++) {
                    $('select').material_select();//funcion materialize para actualizar el combobox
                    $('select[name=competencias]').append('<option value=' + myObject[i].idCompetencia + '>' + myObject[i].nomCompetencia + '</option>');
                }
            });
}
//funcion que obtiene el id del programa seleccionado cuando se da clic en uno de ellos
var idPrograma = null;
var nomPrograma = null;
var count = 0;
var aux = 0;
function idProgramaF(id) {
    count = count + 1;
    var idP = $(id).attr('id');
    idPrograma = idP;
    listarCompeDependiente(idP);
    nomPrograma = $(id).text(); //nombre de la celda de la tabla programa
    if (count % 2 === 1) {
        $("#" + idP).css("background-color", "#bbdefb");
        $("#" + aux).css("background-color", "white");
        aux = idP;
    } else {
        $("#" + aux).css("background-color", "white");
        $("#" + idP).css("background-color", "#bbdefb");
        aux = idP;
    }
}

//funcion que obtiene el id y el nombre de la competencia cuando se da clic en una de ellas en la tabla
var count2 = null;
var aux2 = null;
var idCompeIntermedia = null;
var NomCompeIntermedia = null;
function idCompetenciaIntermedia(id) {
    var idC_I = $(id).attr('id');
    idCompeIntermedia = idC_I;
    nomCompeIntermedia = $(id).text();
    if (count2 % 2 === 1) {
        $(".tdCompe" + idC_I).css("background-color", "white");
        $(".tdCompe" + aux2).css("background-color", "#bbdefb");
        aux2 = idC_I;
    } else {
        $(".tdCompe" + aux2).css("background-color", "#bbdefb");
        $(".tdCompe" + idC_I).css("background-color", "white");
        aux2 = idC_I;
    }
}
//funcion para listar las competencias dependientes de los programas
function listarCompeDependiente(id) {
    $.ajax({
        beforeSend: function (xhr) {
            $("#imgPreloaderCompeT").show();
        },
        method: "POST",
        url: "ServletCompetencia",
        data: {
            idPrograma: id,
            validacion: "dependiente"
        },
        error: function (jqXHR, textStatus, errorThrown) {
            swal({title: "Error en el Servidor",
                text: "Lo sentimos... Intentalo Nuevamente",
                type: "error",
                timer: 4000,
                showConfirmButton: true});
        },
        complete: function (jqXHR, textStatus) {
            $("#imgPreloaderCompeT").hide();
        }
    })
            .done(function (msg) {
                $("#tablaCompetencia").html(msg);
            });
}
//funcion para guardar o almacenar un programa 
$("#btnGuardarC").click(function () {
    if (validarTexArea() === true) {
        $.ajax({
            beforeSend: function (xhr) {

            },
            method: "POST",
            url: "ServletCompetencia",
            data: {
                validacion: "vGuardar",
                competencia: $("#textarea").val(),
                boton: $("#btnGuardarC").val()
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
                    $("#cntntMsj").html(msg);//se mostrará un alert pero en realidad esta escribiendo en el div (contenedor)
                    $("#textarea").val('');
                    listarCompetencias();//metodo para actualizar combobox de competencias
                });
    }
});
//funcion para guardar o almacenar un programa
$("#btnGuardarP").click(function () {
    if (validarTexArea() === true) {
        if (validarRB() === "programa") {
            $.ajax({
                beforeSend: function (xhr) {

                },
                method: "POST",
                url: "ServletPrograma",
                data: {
                    validacion: "vGuardar",
                    programa: $("#textarea").val(),
                    boton: $("#btnGuardarP").val()
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
                        $("#cntntMsj").html(msg);
                        $("#textarea").val('');
                        listarProgramas();//metodo para actualizar la tabla programas
                    });
        }
    }
});
//funcion que valida que se ha seleccionado un programa
function vdrSeleccionPrograma() {
    if (idPrograma !== null) {
        return true;
    } else {
        swal({title: "Antención",
            text: "No seleccionó un programa de la tabla",
            timer: 1800,
            showConfirmButton: true});
        $("#tablaPrograma").focus();
    }
}
//funcion que agrega una competencia a un programa
$("#btnAgregarC").click(function () {
    if (vdrSeleccionPrograma() === true) {
        if (idCompetencia !== null) {
            $.ajax({
                beforeSend: function (xhr) {

                },
                method: "POST",
                url: "ServletProgramaXcompetencia",
                data: {
                    validacion: "vAgrgar",
                    idPrograma: idPrograma,
                    idCompetencia: idCompetencia,
                    boton: $("#btnAgregarC").val()
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
                        $("#cntntMsj").html(msg);
                        //alert(msg);
                        //$("#mensajes").html(msg);
                        listarCompeDependiente(idPrograma);
                        idPrograma = null;
                        idCompetencia = null;
                    });
        } else {
            swal({title: "Antención",
                text: "No seleccionó una competencia de la tabla",
                timer: 1800,
                showConfirmButton: true});
        }

    }
});
//validando radio buttons
function validarRB() {
    if ($('input[name=grupo]').is(':checked')) {
        return $('input[name=grupo]:checked').val();
    } else {
        swal({title: "Antención",
            text: "Debe selecionar una opcion de los radio booton",
            timer: 1800,
            showConfirmButton: true});
    }
}
//configuracion de botones
function configuracionBotones() {
    $('input[name=grupo]').click(function h() {
        var valorRadioButton = $('input[name=grupo]:checked').val();
        switch (valorRadioButton) {
            case "programa":
                $("#btnAgregarC").prop('disabled', true);
                $("#btnGuardarC").prop('disabled', true);
                $("#btnGuardarP").prop('disabled', false);
                $("#btnListarP").prop('disabled', false);
                $("#btnAgregarC").addClass("disabled");
                $("#btnGuardarC").addClass("disabled");
                $("#btnGuardarP").removeClass("disabled");
                $("#btnListarP").removeClass("disabled");
                break;
            case "competencia":
                $("#btnAgregarC").prop('disabled', false);
                $("#btnGuardarC").prop('disabled', false);
                $("#btnGuardarP").prop('disabled', true);
                $("#btnListarP").prop('disabled', true);
                $("#btnGuardarP").addClass("disabled");
                $("#btnListarP").addClass("disabled");
                $("#btnAgregarC").removeClass("disabled");
                $("#btnGuardarC").removeClass("disabled");
                break;
        }
    });
}
//saber el id de la competencia seleccionada en el combobox
var idCompetencia = null;
var nomCompetencia = null;
$('select[name=competencias]').on('change', function () {
    idCompetencia = $('select[name=competencias]').val();//saber value del combobox seleccionado
    nomCompetencia = $('select[name=competencias] option:selected').text(); //saber el texto entre los option del combobox seleccionado
});
//funcion que modifica un programa y una competencia
$("#btnModificar").click(function () {
    if (validarTexArea() === true) {
        switch (validarRB()) {
            case "programa":
                if (vdrSeleccionPrograma() === true) {
                    $.ajax({
                        beforeSend: function (xhr) {

                        },
                        method: "POST",
                        url: "ServletPrograma",
                        data: {
                            validacion: "vModificar",
                            idPrograma: idPrograma,
                            programa: $("#textarea").val()
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
                                $("#loaderE").hide();
                                $("#cntntMsj").html(msg);
                                $("#textarea").val('');
                                idPrograma = null;
                                listarProgramas();//metodo para actualizar la tabla programas
                            });
                }
                break;
            case "competencia":
                if (idCompetencia !== null) {
                    $.ajax({
                        beforeSend: function (xhr) {

                        },
                        method: "POST",
                        url: "ServletCompetencia",
                        data: {
                            validacion: "vModificar",
                            idCompetencia: idCompetencia,
                            competencia: $("#textarea").val()
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
                                $("#cntntMsj").html(msg);
                                $("#textarea").val('');
                                idCompetencia = null;
                                listarCompetencias();//metodo para actualizar combobox de competencias
                                listarProgramas();//metodo para actualizar la tabla programas
                            });
                } else {
                    swal({title: "Antención",
                        text: "No seleccionó una competencia de la lista desplegable",
                        timer: 1800,
                        showConfirmButton: true});
                }
                break;
        }
    }

});
//funcion que elimina un programa una competencia y una competencia dependiente
$("#btnEliminar").click(function () {
    switch (validarRB()) {
        case "programa":
            if (vdrSeleccionPrograma() === true) {
                $("#modal1").openModal("#btnEliminar");
                msjModalAntes("p");//mensajes de información a eliminar antes de eliminar
                $("#modalAceptar").click(function () {
                    $.ajax({
                        method: "POST",
                        url: "ServletPrograma",
                        data: {
                            validacion: "vEliminar",
                            idPrograma: idPrograma
                                    // programa: nomPrograma
                        },
                        beforeSend: function () {
                            //funcion antes de enviar
                            //si el proceso se demora mucho puede haber un preloader
                        },
                        error: function (jqXHR, textStatus, errorThrown) {
                            swal({title: "Error en el Servidor",
                                text: "Lo sentimos... Intentalo Nuevamente",
                                type: "error",
                                timer: 4000,
                                showConfirmButton: true});
                        },
                        complete: function (jqXHR, textStatus) {

                        },
                        success: function (msj) {
                            msjModalDespues(msj);
                            idPrograma = null;
                            listarProgramas();//metodo para actualizar la tabla programas
                            $("#tablaCompetencia").html("");
                        }
                    });

                });
            }
            break;
        case "competencia":
            if (idCompetencia !== null) {
                $("#modal1").openModal("#btnEliminar");
                msjModalAntes("c");//mensajes de información a eliminar antes de eliminar
                $("#modalAceptar").click(function () {
                    $.ajax({
                        beforeSend: function () {
                        },
                        method: "POST",
                        url: "ServletCompetencia",
                        data: {
                            validacion: "vEliminarCompe",
                            idCompetencia: idCompetencia
                        }, error: function (jqXHR, estado, error) {
                            swal({title: "Error en el Servidor",
                                text: "Lo sentimos... Intentalo Nuevamente",
                                type: "error",
                                timer: 4000,
                                showConfirmButton: true});
                        },
                        complete: function (jqXHR, estado) {
                            //esto se ejecuta despues del done o error
                            //aca se oculta la imagen de cargando que se mostró en el beforeSend
                            //alert("Terminó");
                        }
                    })
                            .done(function (msg) {
                                msjModalDespues(msg);
                                idCompetencia = null;
                                listarCompetencias();//metodo para actualizar combobox de competencias
                            });
                });
            } else {
                if (idCompeIntermedia !== null && idPrograma !== null) {//eliminado en tabla programaXcompetencia
                    $("#modal1").openModal("#btnEliminar");
                    msjModalAntes("pc");//mensajes de información a eliminar antes de eliminar
                    $("#modalAceptar").click(function () {
                        $.ajax({
                            beforeSend: function () {
                                //aca pueden poner una imagen gif como un preloader
                                //¿para qué?
                                //este metodo se ejecuta antes de llegar al servlet
                                //por ejemplo al guardar algo, parecera la imagen cargando mientras se completa el registro
                            },
                            method: "POST",
                            url: "ServletProgramaXcompetencia",
                            data: {
                                validacion: "vEliminar",
                                idCompetencia: idCompeIntermedia,
                                idPrograma: idPrograma
                            },
                            error: function (jqXHR, estado, error) {
                                swal({title: "Error en el Servidor",
                                    text: "Lo sentimos... Intentalo Nuevamente",
                                    type: "error",
                                    timer: 4000,
                                    showConfirmButton: true});
                            },
                            complete: function (jqXHR, estado) {
                                //esto se ejecuta despues del done o error
                                //aca se oculta la imagen de cargando que se mostró en el beforeSend
                            }
                        })
                                .done(function (msg) {
                                    msjModalDespues(msg);
                                    listarCompeDependiente(idPrograma);
                                    idPrograma = null;
                                    idCompeIntermedia = null;
                                });
                    });
                } else {
                    swal({title: "Antención",
                        text: "Primero debe seleccionar un programa y después la competencia a eliminar",
                        timer: 1800,
                        showConfirmButton: true});
                }
            }
            break;
    }
});
//funcion que verifica que el textArea este lleno
function validarTexArea() {
    if ($("#textarea").val().length > 1) {
        $("#vTxtarea").hide();//mensaje de validacion
        return true;
    } else {//el campo esta vacio        
        $("#textarea").focus();
        $("#vTxtarea").show();
        $("#vTxtarea").css("color", "#d50000");
    }
}
//funcion que asigna un mensaje despues de haber hecho una accion del crud al modal 
function msjModalDespues(msj) {
    $(".msjInfoAntes").hide();
    $("#msjInfoDespues").show();
    $("#msjInfoDespues").html(msj);
    $("#modalAceptar").hide();
    $("#btnCambianteModal").text("Cerrar");
}
//funcion que asigna ativa mensajes del modal antes de hacer una accion del crud
function msjModalAntes(p) {
    if (p === "p") {
        $("#cabeceraModal").text("El programa puede tener competencias asociadas");
        $("#spanTipo").text("programa: ");
        $("#spanNombre").text(nomPrograma);
        nomPrograma = null;
    } else if (p === "c") {
        $("#cabeceraModal").text("La competencia puede estar asociada a un programa");
        $("#spanTipo").text("competencia: ");
        $("#spanNombre").text(nomCompetencia);
        nomCompetencia = null;
    } else if (p === "pc") {
        $("#cabeceraModal").text("La competencia esta asociada a un programa");
        $("#spanTipo").text("competencia: ");
        $("#spanNombre").text(nomCompeIntermedia);
        nomCompeIntermedia = null;
    }
    $("#loaderE").hide();
    $(".msjInfoAntes").show();
    $("#modalAceptar").show();
    $("#msjInfoDespues").hide();
    $("#btnCambianteModal").text("Cancelar");
}
///* JSP Consulta autocomplete*/
$("#textarea").keyup(function () {
    if ($('input[name=grupo]').is(':checked')) {
        var rb = $('input[name=grupo]:checked').val();
        switch (rb) {
            case "programa":
                $.ajax({
                    method: "POST",
                    url: "ServletPrograma",
                    data: {
                        validacion: "vConsultaAutocomplete",
                        programa: $("#textarea").val()
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
                            $("#tablaCompetencia").html("");
                            $("#tablaPrograma").html(msg);
                        });
                break;
        }
    }
});
//funcion que al dar clic al boton listar   
$("#btnListarP").click(function () {
    listarProgramas();
});



//-----------------------------------------------------PROGRAMACIÓN---------PROGRAMACIÓN-------PROGRAMACIÓN-----------------------------------------------
//funcion para listar instructores en combobox
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
                $('select[name=instructores]').append('<option name="opciones" value="">Seleccione</option>');
                for (var i = 0; i < myObject.length + 1; i++) {
                    $('select').material_select();//funcion materialize para actualizar el combobox
                    $('select[name=instructores]').append('<option value=' + myObject[i].id + '>' + myObject[i].nombre + " " + myObject[i].apellido + '</option>');
                }

            });
}
//funcion para listar fichas en combobox
function listarFichas() {
    $.ajax({
        beforeSend: function (xhr) {

        },
        method: "POST",
        url: "ServletProgramacion",
        data: {
            validacion: "comboboxFicha"
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
                $('select[name=fichas] option').remove();
                var myObject = eval('(' + msg + ')');
                $('select[name=fichas]').append('<option name="opciones" value="" disabled selected>Seleccione</option>');
                for (var i = 0; i < myObject.length + 1; i++) {
                    $('select').material_select();//funcion materialize para actualizar el combobox
                    $('select[name=fichas]').append('<option value=' + myObject[i].id + ' id="' + myObject[i].idPrograma + '">' + myObject[i].numeroFicha + '</option>');
                }

            });
}

//configuracion inicio
$("#nuevaProgramacion").click(function () {
    $("#contenedorFichasPrograma").hide();
    $("#contenedorNuevaProgramacion").show();
    $("#btnGuardar").show();
});
$("#btnCancelar").click(function () {
    visualizacionInicio();
});
function visualizacionInicio() {
    $("#contenedorFichasPrograma").show();
    $("#contenedorNuevaProgramacion").hide();
    $("#btnModificarProgramacion").hide();
}

///cuadro fichas                                                            
//todas las fichas al abrir el sitio web
function cuadrosFichasTodas() {
    $.ajax({
        beforeSend: function (xhr) {
            //$("#imgPreloaderPrograCompProgramacion").show();
        },
        method: "POST",
        url: "ServletProgramacion",
        data: {
            validacion: "cuadrosFichasTodos"
        },
        error: function (jqXHR, textStatus, errorThrown) {
            swal({title: "Error en el Servidor",
                text: "Lo sentimos... Intentalo Nuevamente",
                type: "error",
                timer: 4000,
                showConfirmButton: true});
        },
        complete: function (jqXHR, textStatus) {
            //$("#imgPreloaderPrograCompProgramacion").hide();
        }
    })
            .done(function (msg) {
                $("#contenedorFichas").html(msg);
            });
}
$("#cuadroFichasTodas").click(function () {
    cuadrosFichasTodas();
    $("#contenedorProgramacionFichas").text('');
});
//FILTRANDO las fichas por programa
var ProgramaCombobox = null;
$('select[name=programas]').on('change', function () {
    var idPrograma = $('select[name=programas]').val();//saber value del combobox seleccionado
    ProgramaCombobox = $('select[name=programas] option:selected').text();
    cuadrosFichas(idPrograma);
});
//
function cuadrosFichas(id) {
    $.ajax({
        beforeSend: function (xhr) {
            $("#imgPreloaderProgramacionFichas").show();
        },
        method: "POST",
        url: "ServletPrograma",
        data: {
            validacion: "cuadrosFichas",
            idPrograma: id
        },
        error: function (jqXHR, textStatus, errorThrown) {
            swal({title: "Error en el Servidor",
                text: "Lo sentimos... Intentalo Nuevamente",
                type: "error",
                timer: 4000,
                showConfirmButton: true});
        },
        complete: function (jqXHR, textStatus) {
            $("#imgPreloaderProgramacionFichas").hide();
        }
    })
            .done(function (msg) {
                $("#contenedorFichas").html(msg);
            });
}
//fin cuadro fichas programacionFicha
//seleccionar cuadro de ficha
var idCuadroFichaV;
var fichaCuadro;
function idCuadroFicha(id) {
    idCuadroFichaV = $(id).attr('id');
    fichaCuadro = $(id).text();
    seleccionarCuadroFicha(idCuadroFichaV, fichaCuadro, ProgramaCombobox);
    ProgramaCombobox = null;
}
function seleccionarCuadroFicha(idF, ficha, programa) {
    $.ajax({
        beforeSend: function (xhr) {
            $("#contenedorProgramacionFichas").show();
        },
        method: "POST",
        url: "ServletProgramacion",
        data: {
            validacion: "programacionFicha",
            idFicha: idF,
            nombreFicha: ficha,
            nombrePrograma: programa
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
                $("#contenedorProgramacionFichas").html(msg);
            });
}
//funcion para listar los ambientes
$("#ambiente").click(function listarAmbientestbl() {
    $.ajax({
        beforeSend: function (xhr) {

        },
        method: "POST",
        url: "ServletProgramacion",
        data: {
            validacion: "tblAmbientes"
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
                $("#tblAmbientes").html(msg);
            });
});
//seleccionarAmbiente
function escogerAmbiente(id) {
    var idA = $(id).attr("id");
    $("#ambiente").focus();
    $("#ambiente").val(idA);
    $("#modal1").closeModal("#" + idA);
}

//funcion del boton aceptar despues de seleccionar un ambiete mdAceptarAmbiente boton
$("#btnCancelarMdAmbiente").click(function () {
    $("#ambiente").focusout();
    $("#ambiente").val("");
});


//validando los campos del formulario
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
//comparando horas 
function validandoHora() {
    if ($("#horaInicio").val() > $("#horaFin").val() || $("#horaInicio").val() === $("#horaFin").val()) {
        swal({title: "Antención",
            text: "La hora de inicio debe ser menor a la hora final",
            timer: 1800,
            showConfirmButton: true});
        $("#horaFin").focus();
        $("#lblhoraFin").css("color", "#d50000");
        $("#horaInicio").focus();
        $("#lblhoraInicio").css("color", "#d50000");
        return false;
    } else {
        return true;
    }
}
//validando que los campos del formulario esten llenos
function validarCampos() {
    if ($("#fichas").val().trim() !== '') {
        $("#lblFicha").css("color", "#00c853");
        if ($("#trimestre").val().trim() !== '') {
            $("#lbltrimestre").css("color", "#00c853");
            if ($("#competencias").val().trim() !== '') {
                $("#lblcompetencias").css("color", "#00c853");
                if ($("#dia").val().trim() !== '') {
                    $("#lbldia").css("color", "#00c853");
                    if ($("#fechaInicio").val() !== '') {
                        $("#lblfechaInicio").css("color", "#00c853");
                        if ($("#fechaFinal").val() !== '') {
                            $("#lblfechaFinal").css("color", "#00c853");
                            if ($("#horaInicio").val() !== '') {
                                $("#lblhoraInicio").css("color", "#00c853");
                                if ($("#horaFin").val() !== '') {
                                    $("#lblhoraFin").css("color", "#00c853");
                                    if ($("#instructores").val().trim() !== '') {
                                        $("#lblinstructores").css("color", "#00c853");
                                        if ($("#ambiente").val().trim() !== '') {
                                            $("#lblambiente").css("color", "#00c853");
                                            return true;
                                        } else {
                                            swal({title: "Antención",
                                                text: "El campo es obligatorio",
                                                timer: 1800,
                                                showConfirmButton: true});
                                            $("#ambiente").focus();
                                            $("#lblambiente").css("color", "#d50000");
                                            return false;
                                        }
                                    } else {
                                        swal({title: "Antención",
                                            text: "El campo es obligatorio",
                                            timer: 1800,
                                            showConfirmButton: true});
                                        $("#instructores").focus();
                                        $("#lblinstructores").css("color", "#d50000");
                                        return false;
                                    }
                                } else {
                                    swal({title: "Antención",
                                        text: "El campo es obligatorio",
                                        timer: 1800,
                                        showConfirmButton: true});
                                    $("#horaFin").focus();
                                    $("#lblhoraFin").css("color", "#d50000");
                                    return false;
                                }
                            } else {
                                swal({title: "Antención",
                                    text: "El campo es obligatorio",
                                    timer: 1800,
                                    showConfirmButton: true});
                                $("#horaInicio").focus();
                                $("#lblhoraInicio").css("color", "#d50000");
                                return false;
                            }
                        } else {
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
                    $("#dia").focus();
                    $("#lbldia").css("color", "#d50000");
                    return false;
                }
            } else {
                swal({title: "Antención",
                    text: "El campo es obligatorio",
                    timer: 1800,
                    showConfirmButton: true});
                $("#competencias").focus();
                $("#lblcompetencias").css("color", "#d50000");
                return false;
            }
        } else {
            swal({title: "Antención",
                text: "El campo es obligatorio",
                timer: 1800,
                showConfirmButton: true});
            $("#trimestre").focus();
            $("#lbltrimestre").css("color", "#d50000");
            return false;
        }
    } else {
        swal({title: "Antención",
            text: "El campo es obligatorio",
            timer: 1800,
            showConfirmButton: true});
        $("#fichas").focus();
        $("#lblFicha").css("color", "#d50000");
        return false;
    }
}

//obteniendo valores de los combobox
//obteniendo el id de ficha
var idFicha = null;
$('select[name=competencias]').disabled = true;
$('select[name=fichas]').on('change', function () {
            $('select[name=competencias] ').material_select('destroy');
             $('select[name=competencias]').append('<option name="opciones" value="" disabled selected>Seleccione</option>');
    var idProgramaProgramaion = null;
    if ($('select[name=fichas]').children(":selected").attr("id") !== "") {
        idFicha = $('select[name=fichas]').val();//saber value del combobox seleccionado
        idProgramaProgramaion = $(this).children(":selected").attr("id");
        $.ajax({
            beforeSend: function (xhr) {

            },
            method: "POST",
            url: "ServletCompetencia",
            type: "get",
            datatype: "json",
            data: {
                validacion: "comboboxProgramacion",
                idPrograma: idProgramaProgramaion
            },
            error: function (jqXHR, textStatus, errorThrown) {

            },
            complete: function (jqXHR, textStatus) {
                swal({title: "Error en el Servidor",
                    text: "Lo sentimos... Intentalo Nuevamente",
                    type: "error",
                    timer: 4000,
                    showConfirmButton: true});
                idProgramaProgramaion = null;
            }
        })
                .done(function (msg) {
                    $('#competencias').prop('disabled', false);
                    $('select[name=competencias] option').remove();
                    var myObject = eval('(' + msg + ')');
                    $('select[name=competencias]').append('<option name="opciones" value="" disabled selected>Seleccione</option>');
                    for (var i = 0; i < myObject.length + 1; i++) {
                        $('select').material_select();//funcion materialize para actualizar el combobox
                        $('select[name=competencias]').append('<option value=' + myObject[i].idCompetencia + '>' + myObject[i].nomCompetencia + '</option>');
                    }
                    if ($('select[name=fichas]').children(":selected").attr("id") == "Undefined") {
                        $('#competencias').prop('disabled', true);
                    } else {
                        $('#competencias').prop('disabled', false);
                    }

                });

    }



});
//obteniendo el id de la competencia
var idCompetenciaProgramacion = null;
$('select[name=competencias]').on('change', function () {
    idCompetenciaProgramacion = $('select[name=competencias]').val();//saber value del combobbox seleccionado
});
//obteniendo el id del instructor
//obteniendo el id de ficha
var idInstructor = null;
$('select[name=instructores]').on('change', function () {
    idInstructor = $('select[name=instructores]').val();//saber value del combobox seleccionado
});

//almacenando una programacion                    
$("#btnGuardar").click(function () {
    if (validarCampos() === true) {
        if (validandoFecha() === true) {
            if (validandoHora() === true) {
                $.ajax({
                    beforeSend: function () {
                        $("#preloader").show();
                        $("#textoPreloader").text("paso 1");
                    },
                    method: "POST",
                    url: "ServletProgramacion",
                    data: {
                        validacion: "insertarProgramacion",
                        idFicha: idFicha,
                        idAmbiente: $("#ambiente").val(),
                        trimestre: $('select[name=trimestre]').val(),
                        dia: $('select[name=dia]').val(),
                        fechaInicio: $("#fechaInicio").val(),
                        fechaFin: $("#fechaFinal").val(),
                        horaInicio: $("#horaInicio").val(),
                        horaFinal: $("#horaFin").val(),
                        idInstructor: idInstructor,
                        idCompetencia: idCompetenciaProgramacion
                    }//,beforeSend: function(){$("#preloader").show();$("#textoPreloader").text("paso 1");}
                    , error: function (jqXHR, estado, error) {
                        swal({title: "Error en el Servidor",
                            text: "Lo sentimos... Intentalo Nuevamente",
                            type: "error",
                            timer: 4000,
                            showConfirmButton: true});
                    },
                    complete: function (jqXHR, estado) {
                        $("#preloader").hide();
                    }
                })
                        .done(function (msg) {
                            $("#cntntMsj").html(msg);
                            //alert(msg);
                            limpiarCampos();//limpia ls campos luego de guardar
                            //alert(msg);
                            /*  var idProgramacion = msg;
                             $.ajax({//enviando datos a la entidad intermedia usuarioXprogramacion
                             method: "POST",
                             url: "../ServletUsuarioXprogramacion",
                             data: {
                             validacion: "insertarProgramacion",
                             idProgramacion: idProgramacion,
                             idInstructor: idInstructor
                             }//,beforeSend: function(){ $("#textoPreloader").text("paso 2");}
                             })
                             .done(function (msg) {
                             //alert(msg);
                             $.ajax({//enviando datos a la entidad comp_progra
                             method: "POST",
                             url: "../ServletComp_Progra",
                             data: {
                             validacion: "insertarProgramacion",
                             idProgramacion: idProgramacion,
                             idCompetencia: idCompetenciaProgramacion
                             }//,beforeSend: function(){$("#textoPreloader").text("paso 3");}
                             })
                             .done(function (msg) {
                             alert(msg);
                             limpiarCampos();//limpia ls campos luego de guardar
                             });
                             });*/
                        });

            }
        }
    }
});
//otras funciones del crud eliminar y modificar
var idProgramacionM = null;
function accion(id) {//cuando se da clic 
    var ids = $(id).attr('id');
    switch ($(id).text()) {
        case "Eliminar":
            $.ajax({
                beforeSend: function (xhr) {

                },
                method: "POST",
                url: "ServletProgramacion",
                data: {
                    validacion: "eliminarProgramacion",
                    idProgramacion: ids
                }, /*,beforeSend: function(){$("#textoPreloader").text("paso 3");}*/
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
                        $("#cntntMsj").html(msg);
                        seleccionarCuadroFicha(idCuadroFichaV, fichaCuadro, '');
                    });
            break;
        case "Modificar":
            $("#contenedorFichasPrograma").hide();
            $("#contenedorNuevaProgramacion").show();
            $("#btnModificarProgramacion").show();
            $("#btnGuardar").hide();
            swal({title: "Antención",
                text: "Favor llenar todos los campos",
                timer: 1800,
                showConfirmButton: true});
            idProgramacionM = ids;
            break;
    }
}
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
//modificar programacion
$("#btnModificarProgramacion").click(function () {
    if (validarCampos() === true) {
        if (validandoFecha() === true) {
            if (validandoHora() === true) {
                $.ajax({
                    beforeSend: function (xhr) {

                    },
                    method: "POST",
                    url: "ServletProgramacion",
                    data: {
                        validacion: "ProgramacionModificar",
                        idProgramacion: idProgramacionM,
                        idFicha: idFicha,
                        idAmbiente: $("#ambiente").val(),
                        trimestre: $('select[name=trimestre]').val(),
                        dia: $('select[name=dia]').val(),
                        fechaInicio: $("#fechaInicio").val(),
                        fechaFin: $("#fechaFinal").val(),
                        horaInicio: $("#horaInicio").val(),
                        horaFinal: $("#horaFin").val(),
                        idUsuario: idInstructor,
                        idCompetencia: idCompetenciaProgramacion
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
                            $("#cntntMsj").html(msg);
                            seleccionarCuadroFicha(idCuadroFichaV, '', '');
                            limpiarCampos();
                        });
            }
        }
    }
});
//limpiar campos del formulario de programacion
function limpiarCampos() {
    listarFichas();
    $('#trimestre').html('');
    actualizarListTrimestre();
    listarCompetencias();
    $('select[name=dia]').html('');
    actualizarListDiasSemana();
    $("#fechaInicio").val("");
    $("#fechaFinal").val("");
    $("#horaInicio").val("");
    $("#horaFin").val("");
    listarInstructores();
    $("#ambiente").val("");
}
function actualizarListTrimestre() {
    $('select[name=trimestre]').append('<option name="opciones" value="">Seleccione</option>');
    $('select[name=trimestre]').append('<option name="opciones" value="1">1</option>');
    $('select[name=trimestre]').append('<option name="opciones" value="2">2</option>');
    $('select[name=trimestre]').append('<option name="opciones" value="3">3</option>');
    $('select[name=trimestre]').append('<option name="opciones" value="4">4</option>');
    $('select[name=trimestre]').append('<option name="opciones" value="5">5</option>');
    $('select[name=trimestre]').append('<option name="opciones" value="6">6</option>');
}
function actualizarListDiasSemana() {
    $('select[name=dia]').append('<option name="opciones" value="">Seleccione</option>');
    $('select[name=dia]').append('<option name="opciones" value="Lunes">Lunes</option>');
    $('select[name=dia]').append('<option name="opciones" value="Martes">Martes</option>');
    $('select[name=dia]').append('<option name="opciones" value="Miércoles">Miércoles</option>');
    $('select[name=dia]').append('<option name="opciones" value="Jueves">Jueves</option>');
    $('select[name=dia]').append('<option name="opciones" value="Viernes">Viernes</option>');
    $('select[name=dia]').append('<option name="opciones" value="Sabado">Sabado</option>');
    $('select[name=dia]').append('<option name="opciones" alue="Domingo">Domingo</option>');
}


//-------------------------ASISTENCIA-------------------------*************
//competencias que el instructor va a dictar 
$("#competenciasDictadasXintstructor").click(function () {
    $("#mdAceptarProgramacion").hide();
    $.ajax({
        beforeSend: function (xhr) {

        },
        method: "POST",
        url: "ServletProgramacion",
        data: {
            validacion: "competenciasDictadasXintstructor",
            idInstructor: 2 //Esto es el id del usuaio logueado
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


//funciona cuando ya la ficha marcó la entrada y salida
// es decir, cuando se cerró la entrada y salida  y faltan aprendices
$("#buscarA").click(function () {
    if ($('select[name=tipoAsistencia] option:selected').text() === "Seleccione") {
        //alert("Debe seleccionar la acción a realizar en: Seleccione tipo de asistencia");
        $('select[name=tipoAsistencia] option:selected').focus();
        $("#vTipoA").show();
        $("#vTipoA").css("color", "#d50000");
    } else {
        $("#vTipoA").hide();
        if ($("#buscarAprendiz").val().length < 1) {
            //alert("Debe ingresar un numero de documento de identidad");
            $("#buscarAprendiz").focus();
            $("#vNumeroDocA").show();
            $("#vNumeroDocA").css("color", "#d50000");
        } else {
            $("#vNumeroDocA").hide();
            var nDi = $("#buscarAprendiz").val();//nuero del documento del aprendiz
            $.ajax({
                beforeSend: function (xhr) {
                },
                method: "POST",
                url: "ServletAsistencia",
                data: {
                    validacion: "tomaAsistencia",
                    idProgramacion: $("#lblPrograma").text(),
                    documento: nDi,
                    tipo: $('select[name=tipoAsistencia]').val(),
                    ficha: $("#lblFicha").text()
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
                        $("#cntntMsj").html(msg);
                        var myObject = eval('(' + msg + ')');
                        $("#buscarAprendiz").val("");
                        $("#lblNombre").text(myObject[0].nombre);
                        $("#lblApellido").text(myObject[0].apellido);
                        $("#lblHora").text(myObject[0].hora);
                        $("#lblDocumento").text(nDi);
                        $("#lblFalta").text(myObject[0].faltas);
                        $("#lblTiempo").text(myObject[0].tiempo);
                        $("#imgAprendiz").attr("href", "'" + myObject[0].foto + "'");
                        swal({title: "Perfecto",
                            text: myObject[0].mensaje,
                            type: "success",
                            timer: 2000,
                            showConfirmButton: true});
                    });
        }
    }
    $("#buscarAprendiz").val("");
    $("#buscarAprendiz").focusout();
});


//configuración de inicio de la pagina al cargar
function inicioAsistencia() {
    $("#btne").hide();
    $("#btns").hide();
    $("#otroA").hide();
    $("#contentAsistencia01").hide();
    $("#contentAsistencia2").hide();
    $("#vNumeroDocA2").hide();
    $("#vNumeroDocA").hide();
    $("#buscarA2").hide();
    $("#buscarA3").hide();
    $.ajax({
        beforeSend: function (xhr) {

        },
        method: "POST",
        url: "ServletAsistencia",
        data: {
            validacion: "verificacionFicha",
            idProgramacion: $("#lblPrograma").text()
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
                var i = parseInt(msg);
                if (i == "0") {
                    $("#btne").show();
                    $("#btns").hide();
                    $("#otroA").hide();
                    $("#buscarA2").show();
                    $("#buscarA3").hide();
                } else if (i == "1") {
                    $("#btne").hide();
                    $("#btns").show();
                    $("#otroA").hide();
                    $("#buscarA2").hide();
                    $("#buscarA3").show();
                } else if (i == "2") {
                    $("#btne").hide();
                    $("#btns").hide();
                    $("#otroA").show();
                } else {
                    $("#cntntMsj").html(msg);
                }
            });

    $("#logicaInicial").show();
    $("#contenedorTomaAsistencia").hide();
    mostrar2();
    mostrar1();
    mostrar0();
}
function mostrar2() {//se ejectuta cuando faltó un aprendiz por marcar la asistencia
    $("#btnmostrarCntA").click(function () {
        $("#otroA").hide();
        $("#contenedorTomaAsistencia").show();
        $("#contentAsistencia01").hide();
        $("#contentAsistencia2").show();
    });
}
function mostrar1() {//se ejectuta cuando ya la ficha a marcado la entrada
    $("#btns").click(function () {
        $("#contenedorTomaAsistencia").show();
        $("#contentAsistencia01").show();
        $("#contentAsistencia2").hide();
        $("#btns").hide();
    });
}
function mostrar0() {//se ejectuta cuando ya la ficha No ha marcado la entrada
    $("#btne").click(function () {
        $.ajax({
            beforeSend: function (xhr) {
            },
            method: "POST",
            url: "ServletAsistencia",
            data: {
                validacion: "vertimientoFicha",
                idProgramacion: $("#lblPrograma").text(),
                ficha: $("#lblFicha").text()
            },
            error: function (jqXHR, textStatus, errorThrown) {
                swal({title: "Error en el Servidor",
                    text: errorThrown,
                    type: "error",
                    timer: 4000,
                    showConfirmButton: true});
            },
            complete: function (jqXHR, textStatus) {
            }
        })
                .done(function (msg) {
                    $("#cntntMsj").html(msg);
                });
        $("#btne").hide();
        $("#contenedorTomaAsistencia").show();
        $("#contentAsistencia01").show();
        $("#contentAsistencia2").hide();
    });
}

//este se ejecuta cuando es la primera vez del dia con ese instructor que se toma asistencia
$("#buscarA2").click(function () {
    if ($("#buscarAprendiz2").val().length < 1) {
        $("#buscarAprendiz2").focus();
        $("#vNumeroDocA2").show();
        $("#vNumeroDocA2").css("color", "#d50000");
    } else {
        $("#vNumeroDocA2").hide();
        var nDi = $("#buscarAprendiz2").val();//nuero del documento del aprendiz
        $.ajax({
            beforeSend: function (xhr) {
            },
            method: "POST",
            url: "ServletAsistencia",
            data: {
                validacion: "tomaAsistencia2",
                idProgramacion: $("#lblPrograma").text(),
                documento: nDi,
                tipo: 0,
                ficha: $("#lblFicha").text()
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
                    $("#cntntMsj").html(msg);
                    //alert(msg);
                    var myObject = eval('(' + msg + ')');
                    //alert(myObject.toString());
                    $("#buscarAprendiz").val("");
                    $("#lblNombre").text(myObject[0].nombre);
                    $("#lblApellido").text(myObject[0].apellido);
                    $("#lblHora").text(myObject[0].hora);
                    $("#lblDocumento").text(nDi);
                    $("#lblFalta").text(myObject[0].faltas);
                    $("#imgAprendiz").attr("href", "'" + myObject[0].foto + "'");
                    swal({title: "Perfecto",
                        text: myObject[0].mensaje,
                        type: "success",
                        timer: 1900,
                        showConfirmButton: true});
                });
    }
    $("#buscarAprendiz2").val("");
    $("#buscarAprendiz2").focusout();
});

//este se ejecuta cuando se va a registrar la salida de los aprendices
$("#buscarA3").click(function () {
    if ($("#buscarAprendiz2").val().length < 1) {
        $("#buscarAprendiz2").focus();
        $("#vNumeroDocA2").show();
        $("#vNumeroDocA2").css("color", "#d50000");
    } else {
        $("#vNumeroDocA2").hide();
        var nDi = $("#buscarAprendiz2").val();//nuero del documento del aprendiz
        $.ajax({
            beforeSend: function (xhr) {
            },
            method: "POST",
            url: "ServletAsistencia",
            data: {
                validacion: "tomaAsistenciaSalida",
                idProgramacion: $("#lblPrograma").text(),
                documento: nDi,
                tipo: 1,
                ficha: $("#lblFicha").text()
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
                    $("#cntntMsj").html(msg);
                    //alert(msg);
                    var myObject = eval('(' + msg + ')');
                    //alert(myObject.toString());
                    $("#buscarAprendiz").val("");
                    $("#lblNombre").text(myObject[0].nombre);
                    $("#lblApellido").text(myObject[0].apellido);
                    $("#lblHora").text(myObject[0].hora);
                    $("#lblDocumento").text(nDi);
                    $("#lblFalta").text(myObject[0].faltas);
                    $("#lblTiempo").text(myObject[0].tiempo);
                    $("#imgAprendiz").attr("href", "'" + myObject[0].foto + "'");
                    swal({title: "Perfecto",
                        text: myObject[0].mensaje,
                        type: "success",
                        timer: 1900,
                        showConfirmButton: true});
                });
    }
    $("#buscarAprendiz2").val("");
    $("#buscarAprendiz2").focusout();
});



//---------------------------***********INASISTENCIA**********---------------------
function fichasInstructor() {
    $.ajax({
        beforeSend: function () {
            //aca pueden poner una imagen gif como un preloader
            //¿para qué?
            //este metodo se ejecuta antes de llegar al servlet
            //por ejemplo al guardar algo, parecera la imagen cargando mientras se completa el registro
        },
        method: "POST",
        url: "ServletAsistencia", //nombre del servlet
        data: {
            //aca se optiene  los datos del formulario para enviarlos al servlet
            validacion: "fichasInstructor",
            idInstructor: 2 //Esto es el id del usuaio logueado//CAMBIAR
        }
        , error: function (jqXHR, estado, error) {
            swal({title: "Error en el Servidor",
                text: "Lo sentimos... Intentalo Nuevamente",
                type: "error",
                timer: 4000,
                showConfirmButton: true});
        },
        complete: function (jqXHR, estado) {
            //esto se ejecuta despues del done o error
            //aca se oculta la imagen de cargando que se mostró en el beforeSend
        }
    })
            .done(function (msg) {
                //acá se muestra lo que se imprime en el servlert
                //alertas de si guardo o no o si ocurrio un error
                $("#contenedorFichasInstructor").html(msg);

            });
}
//inasistenciaFicha
//tabla de las fechas con inasistencia de la ficha

function inicioInasistencia() {
    $("#contenedorGeneralTablaIna").hide();
    $("#contenedorTablaFaltasAprendices").hide();
}
var idCFIna = null;
function tablaInasistenciaFicha(val) {
    $("#contenedorGeneralTablaIna").show();
    $("#contenedorTablaFaltasAprendices").hide();
    var numeroFicha = $(val).attr('value');
    idCFIna = $(val).attr('id');
    $.ajax({
        beforeSend: function () {
            //aca pueden poner una imagen gif como un preloader
            //¿para qué?
            //este metodo se ejecuta antes de llegar al servlet
            //por ejemplo al guardar algo, parecera la imagen cargando mientras se completa el registro
        },
        method: "POST",
        url: "ServletAsistencia", //nombre del servlet
        data: {
            //aca se optiene  los datos del formulario para enviarlos al servlet
            validacion: "inasistenciaFicha",
            ficha: numeroFicha,
            idInstructor: 2 //Esto es el id del usuaio logueado//CAMBIAR
        }
        , error: function (jqXHR, estado, error) {
            swal({title: "Error en el Servidor",
                text: "Lo sentimos... Intentalo Nuevamente",
                type: "error",
                timer: 4000,
                showConfirmButton: true});
        },
        complete: function (jqXHR, estado) {
            //esto se ejecuta despues del done o error
            //aca se oculta la imagen de cargando que se mostró en el beforeSend
            // alert("completado");
        }
    })
            .done(function (msg) {
                //acá se muestra lo que se imprime en el servlert
                //alertas de si guardo o no o si ocurrio un error
                $("#contenedorTablasInasistenciaFicha").html(msg);

            });
}
//Mostrar aprendices que faltaron
function FaltasAprendices(val) {
    var ficha;
    var idProgramacion;
    var fecha;
    var object = eval($(val).attr('id'));
    //alert (object);
    for (var i = 0; object.length; i++) {
        fecha = $(val).attr('value');
        ficha = object[i].ficha;
        idProgramacion = object[i].idProgramacion;
        $("#contenedorTablaFaltasAprendices").show();
        $("#contenedorGeneralTablaIna").hide();
        $.ajax({
            beforeSend: function () {
                //aca pueden poner una imagen gif como un preloader
                //¿para qué?
                //este metodo se ejecuta antes de llegar al servlet
                //por ejemplo al guardar algo, parecera la imagen cargando mientras se completa el registro
            },
            method: "POST",
            url: "ServletUsuario", //nombre del servlet
            data: {
                //aca se optiene  los datos del formulario para enviarlos al servlet
                validacion: "aprendicesQueNoAsistieron",
                fecha: fecha,
                ficha: ficha,
                idProgramacion: idProgramacion //Esto es el id del usuaio logueado//CAMBIAR
            }
            , error: function (jqXHR, estado, error) {
                swal({title: "Error en el Servidor",
                    text: "Lo sentimos... Intentalo Nuevamente",
                    type: "error",
                    timer: 4000,
                    showConfirmButton: true});
            },
            complete: function (jqXHR, estado) {
                //esto se ejecuta despues del done o error
                //aca se oculta la imagen de cargando que se mostró en el beforeSend
                // alert("completado");
            }
        })
                .done(function (msg) {
                    //acá se muestra lo que se imprime en el servlert
                    //alertas de si guardo o no o si ocurrio un error
                    $("#TablaFaltasAprendices").html(msg);

                });
    }

}

//con esto se cierra el modal donde esta el textArea para copiar la novedad
function modal(id) {
    $("#modal6").openModal("#" + $(id).attr('id'));
    var object = eval($(id).attr('value'));
    for (var i = 0; object.length; i++) {
        novedad(object[i].idAprendiz, object[i].idProgramacion, $(id).attr('id'));
    }
}
var idAprendiz = null;
var idProgramacionN = null;
var fechaN = null;
function novedad(idA, idP, fecha) {
    idAprendiz = idA;
    idProgramacionN = idP;
    fechaN = fecha;
}
//GUARDA LA novedad del aprendiz que faltó a la clase
function guardarNovedad() {

    if ($("#txtaNovedad").val().length < 1) {
        $('#txtaNovedad').focus();
        $('#validarTxtaN').show();
        $('#validarTxtaN').css('color', '#d50000');
    } else {
        $('#validarTxtaN').hide();
        $.ajax({
            beforeSend: function () {
            },
            method: 'POST',
            url: 'ServletAsistencia',
            data: {
                validacion: 'insertarNovedad',
                novedad: $('#txtaNovedad').val(),
                documento: idAprendiz,
                idProgramacion: idProgramacionN,
                fecha: fechaN
            }
            , error: function (jqXHR, estado, error) {
                swal({title: "Error en el Servidor",
                    text: "Lo sentimos... Intentalo Nuevamente",
                    type: "error",
                    timer: 4000,
                    showConfirmButton: true});
            },
            complete: function (jqXHR, estado) {
                //$('#modal6').closeModal('#modalAceptarNovedad');
                //$('#ggggg').trigger('click');
                $('#cerrarModalNovedad').trigger('click');
                $('#' + idCFIna + '').trigger('click'); //simila dar un clic 
                idAprendiz = null;
                idProgramacionN = null;
                idCFIna = null;
            }
        })
                .done(function (msg) {
                    $("#cntntMsj").html(msg);
                });
    }
}


//-------------CENTRO DE ASISTENCIA------------------------------------------------------------------------------------
//mostrara las fichas que el instructor tiene clase
function inicioCentroAsistencia() {
    //llamando a google chart Solo se puede llamar una sola vez
    google.charts.load('current', {'packages': ['corechart']});
    $("#contenedorGeneralAprendices").hide();
    $.ajax({
        beforeSend: function () {
            //aca pueden poner una imagen gif como un preloader
            //¿para qué?
            //este metodo se ejecuta antes de llegar al servlet
            //por ejemplo al guardar algo, parecera la imagen cargando mientras se completa el registro
        },
        method: "POST",
        url: "ServletAsistencia", //nombre del servlet
        data: {
            //aca se optiene  los datos del formulario para enviarlos al servlet
            validacion: "fichasInstructor2",
            idInstructor: 2 //Esto es el id del usuaio logueado//CAMBIAR
        }
        , error: function (jqXHR, estado, error) {
            swal({title: "Error en el Servidor",
                text: "Lo sentimos... Intentalo Nuevamente",
                type: "error",
                timer: 4000,
                showConfirmButton: true});
        },
        complete: function (jqXHR, estado) {
            //esto se ejecuta despues del done o error
            //aca se oculta la imagen de cargando que se mostró en el beforeSend
        }
    })
            .done(function (msg) {
                //acá se muestra lo que se imprime en el servlert
                //alertas de si guardo o no o si ocurrio un error
                $("#contenedorFichasInstructor2").html(msg);

            });
}


//las fichas en que el instructor dicta clase
function tablaCompetencias(val) {
    var numeroFicha = $(val).attr('value');
    $("#contenedorGeneralAprendices").show();
    $.ajax({
        beforeSend: function () {
            //aca pueden poner una imagen gif como un preloader
            //¿para qué?
            //este metodo se ejecuta antes de llegar al servlet
            //por ejemplo al guardar algo, parecera la imagen cargando mientras se completa el registro
        },
        method: "POST",
        url: "ServletAsistencia", //nombre del servlet
        data: {
            //aca se optiene  los datos del formulario para enviarlos al servlet
            validacion: "competenciasInss",
            ficha: numeroFicha,
            idInstructor: 2 //Esto es el id del usuaio logueado//CAMBIAR
        }
        , error: function (jqXHR, estado, error) {
            swal({title: "Error en el Servidor",
                text: "Lo sentimos... Intentalo Nuevamente",
                type: "error",
                timer: 4000,
                showConfirmButton: true});
        },
        complete: function (jqXHR, estado) {
            //esto se ejecuta despues del done o error
            //aca se oculta la imagen de cargando que se mostró en el beforeSend
            // alert("completado");
        }
    })
            .done(function (msg) {
                //acá se muestra lo que se imprime en el servlert
                //alertas de si guardo o no o si ocurrio un error
                $("#contentAprendices").html(msg);

            });
}


//las fichas en que el instructor dicta clase
function tablaCompetenciasComoGestor(val) {
    var gestor = $(val).attr('value');
    var numeroFicha = $(val).text();
    $("#contenedorGeneralAprendices").show();
    $.ajax({
        beforeSend: function () {
            //aca pueden poner una imagen gif como un preloader
            //¿para qué?
            //este metodo se ejecuta antes de llegar al servlet
            //por ejemplo al guardar algo, parecera la imagen cargando mientras se completa el registro
        },
        method: "POST",
        url: "ServletAsistencia", //nombre del servlet
        data: {
            //aca se optiene  los datos del formulario para enviarlos al servlet
            validacion: "competenciasInssGestor",
            ficha: numeroFicha,
            idInstructor: 2 //Esto es el id del usuaio logueado//CAMBIAR
        }
        , error: function (jqXHR, estado, error) {
            swal({title: "Error en el Servidor",
                text: "Lo sentimos... Intentalo Nuevamente",
                type: "error",
                timer: 4000,
                showConfirmButton: true});
        },
        complete: function (jqXHR, estado) {
            //esto se ejecuta despues del done o error
            //aca se oculta la imagen de cargando que se mostró en el beforeSend
            // alert("completado");
        }
    })
            .done(function (msg) {
                //acá se muestra lo que se imprime en el servlert
                //alertas de si guardo o no o si ocurrio un error
                $("#contentAprendices").html(msg);

            });
}



//muestra los aprendices que estan en la ficha
function tablaAprendices(val) {
    $("#contenedorGeneralAprendices").show();
    var numeroFicha = $(val).attr('id');
    var idProgramacion = $(val).attr('value');
    $.ajax({
        beforeSend: function () {
            //aca pueden poner una imagen gif como un preloader
            //¿para qué?
            //este metodo se ejecuta antes de llegar al servlet
            //por ejemplo al guardar algo, parecera la imagen cargando mientras se completa el registro
        },
        method: "POST",
        url: "ServletUsuario", //nombre del servlet
        data: {
            //aca se optiene  los datos del formulario para enviarlos al servlet
            validacion: "aprendicesDeFichas",
            ficha: numeroFicha,
            idInstructor: 2 //Esto es el id del usuaio logueado//CAMBIAR
        }
        , error: function (jqXHR, estado, error) {
            swal({title: "Error en el Servidor",
                text: "Lo sentimos... Intentalo Nuevamente",
                type: "error",
                timer: 4000,
                showConfirmButton: true});
        },
        complete: function (jqXHR, estado) {
            //esto se ejecuta despues del done o error
            //aca se oculta la imagen de cargando que se mostró en el beforeSend
            // alert("completado");
        }
    })
            .done(function (msg) {
                //acá se muestra lo que se imprime en el servlert
                //alertas de si guardo o no o si ocurrio un error
                $("#contentAprendices").html(msg);

            });
}

//modal de faltas
function mdCntFaltas(id) {
    $("#modal7").openModal("#" + $(id).attr('id'));
    $("#mdTiempoApre").hide();
    $("#mdFaltasApre").show();
    $("#contentEstadisticaApre").hide();
    $("#contentEstadisticaFaltasApre").hide();
    var object = eval($(id).attr('value'));
    for (var i = 0; i < object.length; i++) {
        var idProgramacion = object[i].idProgramacion;
        var idAprendiz = object[i].idAprendiz;

        $.ajax({
            beforeSend: function () {
            },
            method: "POST",
            url: "ServletAsistencia", //nombre del servlet
            data: {
                //aca se optiene  los datos del formulario para enviarlos al servlet
                validacion: "faltas",
                documento: idAprendiz,
                idProgramacion: idProgramacion,
                idInstructor: 2 //Esto es el id del usuaio logueado//CAMBIAR
            }
            , error: function (jqXHR, estado, error) {
                swal({title: "Error en el Servidor",
                    text: "Lo sentimos... Intentalo Nuevamente",
                    type: "error",
                    timer: 4000,
                    showConfirmButton: true});
            },
            complete: function (jqXHR, estado) {
                //esto se ejecuta despues del done o error
                //aca se oculta la imagen de cargando que se mostró en el beforeSend
                // alert("completado");
            }
        })
                .done(function (msg) {
                    //acá se muestra lo que se imprime en el servlert
                    //alertas de si guardo o no o si ocurrio un error
                    $("#mdFaltasApre").html(msg);
                });
    }
}
//El tiempo en que el aprendiz ha estado en clase
function mdCntTiempo(id) {
    $("#modal7").openModal("#" + $(id).attr('id'));
    $("#mdTiempoApre").show();
    $("#mdFaltasApre").hide();
    $("#contentEstadisticaApre").hide();
    $("#contentEstadisticaFaltasApre").hide();

    var object = eval($(id).attr('value'));
    for (var i = 0; i < object.length; i++) {
        var idProgramacion = object[i].idProgramacion;
        var idAprendiz = object[i].idAprendiz;
        $.ajax({
            beforeSend: function () {
            },
            method: "POST",
            url: "ServletAsistencia", //nombre del servlet
            data: {
                //aca se optiene  los datos del formulario para enviarlos al servlet
                validacion: "tiempoEnClase",
                documento: idAprendiz,
                idProgramacion: idProgramacion,
                idInstructor: 2 //Esto es el id del usuaio logueado//CAMBIAR
            }
            , error: function (jqXHR, estado, error) {
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
                    $("#mdTiempoApre").html(msg);

                });
    }
}

//GRAFICA unfinished
//fechas en que han faltado
//estadistica Aprendiz falta
function mdCntGenral(id) {
    $("#modal7").openModal("#" + $(id).attr('id'));
    $("#contentEstadisticaApre").show();
    $("#mdFaltasApre").hide();
    $("#mdTiempoApre").hide();
    $("#contentEstadisticaFaltasApre").hide();
    var object = eval($(id).attr('value'));
    for (var i = 0; i < object.length; i++) {
        var idProgramacion = object[i].idProgramacion;
        var idAprendiz = object[i].idAprendiz;

        $.ajax({
            beforeSend: function () {
            },
            method: "POST",
            url: "ServletAsistencia", //nombre del servlet
            data: {
                //aca se optiene  los datos del formulario para enviarlos al servlet
                validacion: "diasAsistidos",
                documento: idAprendiz,
                idProgramacion: idProgramacion,
                idInstructor: 2 //Esto es el id del usuaio logueado//CAMBIAR
            }
            , error: function (jqXHR, estado, error) {
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
                    $("#cntntMsj").html(msg);
                    ///falta
                    google.charts.setOnLoadCallback(function () {
                        drawChart(msg);
                    });
                });

        function drawChart(msg) {
            var object = eval(msg);
            var data = new google.visualization.DataTable();
            //agrego las columnas
            data.addColumn('date', 'Fecha');
            data.addColumn('number', 'Asistencia');
            var fecha = [];
            var dia = [];
            for (var j = 0; j < object.length; j++) {
                fecha[j] = object[j].fecha;
                dia[j] = object[j].entradaSalida;
            }
            alert(fecha);
            alert(dia);
            for (var f = 0; f < fecha.length; f++) {
                data.addRows([[new Date(fecha[f]), f]]);//filas de la grafica
            }

            var options = {
                title: 'Asistencia en la competencia.     Asistió = 0 - No asistio = 3',
                hAxis: {format: ['yyyy-MM-dd'], title: 'Fecha'}, //
                vAxis: {title: 'Asistó = 0  Falto = 3', titleTextStyle: {color: '#333'}, minValue: 0, maxValue: 3},
                animation: {
                    duration: 1000,
                    easing: 'out'
                }
            };

            var chart = new google.visualization.AreaChart(document.getElementById('contentEstadisticaApre'));
            chart.draw(data, options);
            //dando animación
            setTimeout(function () {
                for (var f = 0; f < fecha.length; f++) {
                    data.setValue(f, 1, dia[f]);
                }
                chart.draw(data, options);
            }, 1000);
        }
    }
}

//GRAFICA -ok
//dias faltados por grupo
function mdCntFaltasGrupal(id) {
    $("#modal7").openModal("#" + $(id).attr('id'));
    $("#contentEstadisticaFaltasApre").show();
    $("#contentEstadisticaApre").hide();
    $("#mdFaltasApre").hide();
    $("#mdTiempoApre").hide();
    var idProgramacion = $(id).attr('value');
    $.ajax({
        beforeSend: function () {
        },
        method: "POST",
        url: "ServletUsuario", //nombre del servlet
        data: {
            //aca se optiene  los datos del formulario para enviarlos al servlet
            validacion: "faltasGrupal",
            idProgramacion: idProgramacion,
            idInstructor: 2 //Esto es el id del usuaio logueado//CAMBIAR
        }
        , error: function (jqXHR, estado, error) {
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
                $("#cntntMsj").html(msg);

                //  google.charts.load('current', {'packages':['corechart']});
                google.charts.setOnLoadCallback(function () {
                    drawChart(msg);
                });
            });
    function drawChart(msg) {
        var object = eval(msg);
        var data = new google.visualization.DataTable();
        //agrego las columnas
        data.addColumn('string', 'Nombres');
        data.addColumn('number', 'Días');
        var nombre = [];
        var dia = [];
        for (var j = 0; j < object.length; j++) {
            nombre[j] = object[j].nombre + " " + object[j].apellido;
            dia[j] = object[j].dia;
        }
        for (var f = 0; f < nombre.length; f++) {
            data.addRows([[nombre[f], f]]);//filas de la grafica
        }
        //parte visual de la grafica colores, etc..
        var view = new google.visualization.DataView(data);
        view.setColumns([0, 1,
            {calc: "stringify",
                sourceColumn: 0,
                type: "string",
                role: "annotation"}]);
        var options = {
            title: 'Días faltados por aprendiz en la competencia',
            subtitle: 'Días',
            vAxis: {title: 'Nombres', titleTextStyle: {color: '#333'}},
            hAxis: {title: 'Días', titleTextStyle: {color: '#333'}, format: 'short'},
            bar: {groupWidth: "10%"},
            legend: {position: "none"},
            animation: {
                duration: 1000,
                easing: 'out'
            }
        };
//dibuja la grafica
        var chart = new google.visualization.BarChart(document.getElementById('contentEstadisticaFaltasApre'));
        chart.draw(view, options);
        //le da la animacion
        setTimeout(function () {
            for (var f = 0; f < nombre.length; f++) {
                data.setValue(f, 1, dia[f]);
            }
            chart.draw(view, options);
        }, 1000);
    }

}

/*
 * DIAS QUE HAY EN DOS FECHAS
 * @param {type} id
 * @returns {undefined}
 * var restaFechas = function(f1,f2)
 {
 var aFecha1 = f1.split('/'); 
 var aFecha2 = f2.split('/'); 
 var fFecha1 = Date.UTC(aFecha1[2],aFecha1[1]-1,aFecha1[0]); 
 var fFecha2 = Date.UTC(aFecha2[2],aFecha2[1]-1,aFecha2[0]); 
 var dif = fFecha2 - fFecha1;
 var dias = Math.floor(dif / (1000 * 60 * 60 * 24)); 
 return dias;
 }
 
 var f1 = '10/09/2014';
 var f2='15/10/2014';
 alert(restaFechas(f1,f2));
 */
// solo letras js
function soloLetras(e) {
    key = e.keyCode || e.which;
    tecla = String.fromCharCode(key).toString();
    letras = " áéíóúabcdefghijklmnñopqrstuvwxyzÁÉÍÓÚABCDEFGHIJKLMNÑOPQRSTUVWXYZ";//Se define todo el abecedario que se quiere que se muestre.
    especiales = [8, 37, 39, 46, 6]; //Es la validación del KeyCodes, que teclas recibe el campo de texto.

    tecla_especial = false
    for (var i in especiales) {
        if (key == especiales[i]) {
            tecla_especial = true;
            break;
        }
    }

    if (letras.indexOf(tecla) == -1 && !tecla_especial)
        return false;
}
