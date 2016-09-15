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
                    alert(jqXHR + " - " + textStatus);
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
                estrato: $("#estrato").val(),
                email: $("#email").val(),
                barrio1: $("#barrio1").val(),
                Ficha: $("#Ficha").val(),
                fechaNacimiento: $("#fechaNacimiento").val()
            },
            success: function () {
                swal("Muy Bien", " ¡ Registro Satisfactorio ! ", "success");
                window.location.assign("menuInstructor.jsp");
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
