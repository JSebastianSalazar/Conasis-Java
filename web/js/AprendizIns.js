// combobox
$(document).ready(function () {//se accede al documento
    $('select').material_select();

    $('#municipio').change(function () {
        SeleccionandoCombo(this, 'barrio1');
    });
    function SeleccionandoCombo(combo1, combo2) {
        combo2 = document.getElementById("barrio1");//se obtiene combo2 por id
        LimpiarCombo(combo2);

        if (combo1.options[combo1.selectedIndex].value !== "") {
            combo2.disabled = true;

            $.ajax({//permite acceder al servidor por medio de un objeto json xml o html asincrono dejar en segundo plano el proceso y no alterar la pagina 
                type: 'get',
                url: 'ejemplo',
                dataType: 'json',
                data: {valor1: combo1.options[combo1.selectedIndex].value},
                success: function (resultado) {
                    var MyObjeto = eval(resultado);//convertir un String de tipo Json a un objeto
                    llenarCombo(MyObjeto, combo2);
                    combo1.disabled = false;
                    combo2.disabled = false;

                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert(textStatus + " - " + errorThrown + " - " + jqXHR);
                },
                complete: function (jqXHR, textStatus) {
                    //alert(jqXHR + " - " + textStatus);
                }

            });

        }
    }
    //metodo para limpiar combo
    function LimpiarCombo(combo) {
        while (combo.length > 0) {// desde su tamaño hasta 0
            combo.remove(combo.length - 1);// remueve uno por uno 

        }

    }
    function llenarCombo(json, combo) {
        combo.options[0] = new Option('Barrrios', '');
        for (var i = 0; i < json.length; i++) {
            combo.options[combo.length] = new Option(json[i].municipio, json[i].id);
        }
    }

});


$(document).ready(function () {

    //Tomando foto
    navigator.getUserMedia = navigator.getUserMedia || navigator.webkitGetUserMedia || navigator.mozGetUserMedia || navigator.msGetUserMedia;
    window.URL = window.URL || window.webkitURL || window.mozURL || window.msURL;
    var start = document.querySelector('#start'),
            capture = document.querySelector('#capture'),
            //  save = document.querySelector('#Sgtns'),
            canvas = document.querySelector('canvas'),
            ctx = canvas.getContext('2d'),
            video = document.querySelector('video');

    start.addEventListener('click', function () {

        navigator.getUserMedia({
            video: true
        }, function (stream) {
            var src = window.URL.createObjectURL(stream);
            video.src = src;
        }, function (e) {
            console.log(e);
        });

        capture.addEventListener('click', function () {
            ctx.drawImage(video, 0, 0, canvas.width, canvas.height);
        }, false);

    }, false);
    $("#Almacenar").click(function () {
        $.ajax({
            url: "RegistroAprendiz",
            method: "post",
            data: {
                tipo_Documento: $("#tipo_Documento").val(),
                numero_documento: $("#numero_documento").val(),
                apellido: $("#apellido").val(),
                nombre: $("#nombre").val(),
                genero: $("#genero").val(),
                telefono_fijo: $("#telefono_fijo").val(),
                estrato: $("#estrato1").val(),
                email: $("#email").val(),
                barrio1: $("#barrio1").val(),
                Ficha: $("#Ficha").val(),
                fechaNacimiento: $("#fechaNacimiento").val()
            },
            success: function () {
                swal("Perfecto", " ¡ Registro Satisfactorio ! ", "success");
                window.location.assign("menuAdministrador.jsp");
            },
            error: function (respuesta) {
                swal("Error", respuesta.responseText, "error");
            }, complete: function (jqXHR, textStatus) {
                //save.addEventListener('click', function () {
                var imageData = canvas.toDataURL();
                var xmlhttp = new XMLHttpRequest();
                xmlhttp.open("POST", "Camara", true);
                xmlhttp.send(imageData);
            }

        }).done(function (respuesta) {
            $("#tipo_Documento").val(""),
                    $("#numero_documento").val(""),
                    $("#correo1").val(""),
                    $("#telefono_fijo").val(""),
                    $("#apellido1").val(""),
                    $("#nombre1").val("");

        });

    });
    //botones
    $("#registro3").hide();
    $("#registro5").hide();
    $("#registro6").hide();

    $("#btns").click(function () {
        $("#registro3").show();
        $("#registro4").hide();

    });

    $("#btns").click(function () {
        $("#registro3").show();
        $("#registro4").hide();

    });


    $("#btnatr").click(function () {
        $("#registro3").hide();
        $("#registro4").show();
    });
    $("#Sgtns").click(function () {
        $("#registro3").hide();
        $("#registro5").show();
    });
    $("#ultimate").click(function () {
        $("#registro3").show();
        $("#registro5").hide();
    });

    $("#Sgtns22").click(function () {
        $("#registro5").hide();
        $("#registro6").show();

    });
    $("#Capturar1").hide();
    $("#gb").hide();

    $("#start").click(function () {
        $("#start1").hide();
        $("#Capturar1").show();
    });
    $("#capture").click(function () {
        $("#start1").hide();
        $("#Capturar1").hide();
        $("#gb").show();
    });
    $("#volver33").click(function () {

        $("#registro5").show();
        $("#registro6").hide();


    });

});


//Se utiliza para que el campo de texto solo acepte letras
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

function SoloNumeros(evt) {
    if (window.event) {//asignamos el valor de la tecla a keynum
        keynum = evt.keyCode; //IE
    }
    else {
        keynum = evt.which; //FF
    }
    //comprobamos si se encuentra en el rango numérico y que teclas no recibirá.
    if ((keynum > 47 && keynum < 58) || keynum == 8 || keynum == 13 || keynum == 6) {
        return true;
    }
    else {
        return false;
    }
}


//***********************************************ACA ACA ACA ACA ACA ACA ***********************************************

//ESTO ES SOBRE LO Que va a trabajar
//la funcion fichasInstructor puede dejaerlo asi
$(document).ready(function (){
    fichasInstructor();
});
//la funcion fichasInstructor puede dejaerlo asi NO LA MODIFIQUE
function fichasInstructor() {
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
            validacion: "fichasInstructor3"
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

//muestra los aprendices que estan en la ficha
//cuando se da clic sobre la ficha mostrará los aprendices
function tablaAprendicesFicha(val) {
    $("#contenedorGeneralAprendices").show();
    var idProgramacion = $(val).attr('id');
    var  numeroFicha = $(val).attr('value');
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
            validacion: "tblAprendicesFicha",
            ficha: numeroFicha
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
                $('#sebas').dataTable({
                    'language': {
                        'url': '//cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/Spanish.json'
                    }
                });

            });
}


///ACA si modifica 
//acá en el case eliminar hace el ajax para eliminar el aprendiz
//en el case de modificar hace el ajax para modiciar
function accion(id) {//cuando se da clic 
    var ids = $(id).attr('id');// ESTE ES EL ID DEL APRENDIZ
    switch ($(id).text()) {
        case "Eliminar":
            $.ajax({
                beforeSend: function (xhr) {

                },
                method: "POST",
                url: "",
                data: {
                    idAprendiz: ids// ESTE ES EL ID DEL APRENDIZ
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
                    });
            break;
        case "Modificar":
            //HACE EL MODIFICAR AJAX
            
            break;
    }
}


//estas dos funciones no las modifique 
//No las toque 
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


//combobox de fichas en el registro de aprendices
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
                $('select[name=fichas]').append('<option name="opciones" value="" disabled selected>Ficha</option>');
                for (var i = 0; i < myObject.length + 1; i++) {
                    $('select').material_select();//funcion materialize para actualizar el combobox
                    $('select[name=fichas]').append('<option value=' + myObject[i].numeroFicha+ ' id="' + myObject[i].idPrograma + '">' + myObject[i].numeroFicha + '</option>');
                }

            });
}