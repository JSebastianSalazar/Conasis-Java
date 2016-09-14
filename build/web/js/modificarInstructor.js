/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {

    $("#registro3").hide();
    $("#registro5").hide();

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
    $("#Sgtns1").click(function () {

        $("#registro3").hide();
        $("#registro5").show();

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
    $("#ultimate").click(function () {
        $("#registro3").show();
        $("#registro5").hide();
    });

    //Tomando foto
    navigator.getUserMedia = navigator.getUserMedia || navigator.webkitGetUserMedia || navigator.mozGetUserMedia || navigator.msGetUserMedia;
    window.URL = window.URL || window.webkitURL || window.mozURL || window.msURL;
    var start = document.querySelector('#start'),
            capture = document.querySelector('#capture'),
            save = document.querySelector('#Sgtns'),
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



////////////////////
//ACTUALIZA
    $('#Actualizar').click(function () {
        $.ajax({
            url: "RegistroInstructor",
            method: "post",
            data: {
                numero_documento2: $("#buscar").val(),
                tipo_Documento: $("#tipo_Documento").val(),
                numero_documento: $("#numero_documento").val(),
                correo1: $("#correo1").val(),
                telefono_fijo: $("#telefono_fijo").val(),
                apellido1: $("#apellido1").val(),
                nombre1: $("#nombre1").val(),
                fechaNacimiento: $("#fechaNacimiento").val(),
                validar: "modificar"
            },
            success: function () {
                swal("Bien", "Usuario registrado!", "success");
                window.location.assign("menuAdministrador.jsp");
            },
            error: function (respuesta) {
                swal("Error", respuesta.responseText, "error");
            }, complete: function (jqXHR, textStatus) {


                //save.addEventListener('click', function () {
                var imageData = canvas.toDataURL();
                var xmlhttp = new XMLHttpRequest();
                xmlhttp.open("POST", "CamaraActu", true);
                xmlhttp.send(imageData);
                //limpiando campos
                $("#tipo_Documento").val(""),
                        $("#numero_documento").val(""),
                        $("#correo1").val(""),
                        $("#telefono_fijo").val(""),
                        $("#apellido1").val(""),
                        $("#nombre1").val("");
            }

        });

    });


});
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




