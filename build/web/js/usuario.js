/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {
 iniciarActualizar();
  inicializaCamaraModificar();
  iniciarGuardar();
tomaFotoGuardar();

});

function soloLetras(e) {
    key = e.keyCode || e.which;
    tecla = String.fromCharCode(key).toString();
    letras = " áéíóúabcdefghijklmnñopqrstuvwxyzÁÉÍÓÚABCDEFGHIJKLMNÑOPQRSTUVWXYZ";//Se define todo el abecedario que se quiere que se muestre.
    especiales = [8, 37, 39, 46, 6]; //Es la validación del KeyCodes, que teclas recibe el campo de texto.

    tecla_especial = false
    for(var i in especiales) {
        if(key == especiales[i]) {
            tecla_especial = true;
            break;
        }
    }

    if(letras.indexOf(tecla) == -1 && !tecla_especial)
        return false;
}

//inicializacion modificar
function iniciarActualizar(){
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

}

function inicializaCamaraModificar(){
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
}


//inicializar guardar
function iniciarGuardar(){
      $("#registro3G").hide();
    $("#registro5G").hide();

    $("#btnsG").click(function () {
        $("#registro3G").show();
        $("#registro4G").hide();

    });

    $("#btnsG").click(function () {
        $("#registro3G").show();
        $("#registro4G").hide();

    });


    $("#btnatrG").click(function () {
        $("#registro3G").hide();
        $("#registro4G").show();
    });
    $("#Sgtns1G").click(function () {

        $("#registro3G").hide();
        $("#registro5G").show();

    });
    $("#CaptureG").hide();
    $("#gbG").hide();

    $("#startG").click(function () {
        $("#startG").hide();
        $("#CaptureG").show();
    });
    $("#captureG").click(function () {
        $("#startG").hide();
        $("#CaptureG").hide();
        $("#gbG").show();
    });
    $("#ultimateG").click(function () {
        $("#registro3G").show();
        $("#registro5G").hide();
    });

}

//toma foto guardar
function tomaFotoGuardar(){
       //Tomando foto
    navigator.getUserMedia = navigator.getUserMedia || navigator.webkitGetUserMedia || navigator.mozGetUserMedia || navigator.msGetUserMedia;
    window.URL = window.URL || window.webkitURL || window.mozURL || window.msURL;
    var start = document.querySelector('#startG'),
            capture = document.querySelector('#captureG'),
            save = document.querySelector('#SgtnsG'),
            canvas = document.querySelector('#canvasG'),
            ctx = canvas.getContext('2d'),
            video = document.querySelector('#videoG');

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
    
    ///GUARDA
        $('#SgtnsG').click(function () {
        $.ajax({
            url: "RegistroInstructor",
            method: "post",
            data: {
                tipo_Documento: $("#tipo_DocumentoG").val(),
                numero_documento: $("#numero_documentoG").val(),
                correo1: $("#correo1G").val(),
                telefono_fijo: $("#telefono_fijoG").val(),
                apellido1: $("#apellido1G").val(),
                nombre1: $("#nombre1G").val(),
                fechaNacimiento: $("#fechaNacimientoG").val(),
                validar: "inserta"
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
                xmlhttp.open("POST", "Camara", true);
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


}