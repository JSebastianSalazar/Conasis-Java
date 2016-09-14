$(document).ready(function () {
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
    $('#Sgtns').click(function () {
        $.ajax({
            url: "RegistroSecretaria",
            method: "post",
            data: {
                tipo_Documento: $("#tipo_Documento").val(),
                numero_documento: $("#numero_documento").val(),
                correo1: $("#correo1").val(),
                telefono_fijo: $("#telefono_fijo").val(),
                apellido1: $("#apellido1").val(),
                nombre1: $("#nombre1").val(),
                fechaNacimiento: $("#fechaNacimiento").val()
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



});