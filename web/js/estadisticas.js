/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function (){
    
    $('select').material_select();
    listarFichas();
    //llamando a google chart Solo se puede llamar una sola vez
    google.charts.load('current', {'packages': ['corechart']});
    //stdGenero();
});

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
$('select[name=fichas]').on('change', function () {
    var Nficha = $('select[name=competencias] option:selected').text(); //saber el texto entre los option del combobox seleccionado
    stdGenero(Nficha);
});

function stdGenero(ficha) {
        $.ajax({
            beforeSend: function () {
            },
            method: "POST",
            url: "ServletEstadistica", //nombre del servlet
            data: {
                //aca se optiene  los datos del formulario para enviarlos al servlet
                validacion: "stdGenero",
                numeroFicha: ficha
            }
            , error: function (jqXHR, estado, error) {
                swal({title: "Error en el Servidor",
                    text: error,
                    type: "warning",
                    timer: 4000,
                    showConfirmButton: true});
            },
            complete: function (jqXHR, estado) {
            }
        })
                .done(function (msg) {
                    google.charts.setOnLoadCallback(function () {
                        var array = msg.split(",");
                        var h;
                        var m;
                        for(var i = 0; i < array.length;i++){
                            alert(array[0]);
                            alert(array[1]);
                            h = array[0];
                            m = array[1];      
                        }
                        drawChart(h,m);
                    });
                });

        function drawChart(h,m) {
            var data = google.visualization.arrayToDataTable([
        ["Element", "Density", { role: "style" } ],
        ["Hombres",parseInt(h), "#b87333"],//)
        ["Mujeres",parseInt(m), "silver"]//
      ]);

            var options = {
                title: 'Cantidad generos por ficha',
                hAxis: { title: 'Sexo'}, //
                vAxis: {title: 'Cantidad', titleTextStyle: {color: '#333'}},
                animation: {
                    duration: 1000,
                    easing: 'out'
                }
            };

            var chart = new google.visualization.ColumnChart(document.getElementById('stdGenero'));
            chart.draw(data, options);
            //dando animación
            /*setTimeout(function () {
                for (var f = 0; f < fecha.length; f++) {
                    data.setValue(f, 1, dia[f]);
                }
                chart.draw(data, options);
            }, 1000);*/
        }
    
}