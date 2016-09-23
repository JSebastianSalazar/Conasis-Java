$(document).ready(function () {

    listarFichas();
    $('select').material_select();
    //llamando a google chart Solo se puede llamar una sola vez
    google.charts.load('current', {'packages': ['corechart']});

});

function stadistica2(id) {
    var valor = $(id).attr('id');
  
    window.location.assign("EstadisticaEstrato.jsp");
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
$('select[name=fichas]').on('change', function () {
    var Nficha = $('select[name=fichas] option:selected').text(); //saber el texto entre los option del combobox seleccionado
    stdEstrato(Nficha);

});
function stdEstrato(ficha) {
   
    $.ajax({
        beforeSend: function () {
        },
        method: "POST",
        url: "ServletEstadistica", //nombre del servlet
        data: {
            //aca se optiene  los datos del formulario para enviarlos al servlet
            validacion: "stdEstrato67",
            numeroFicha1: ficha
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
                    var uno;
                    var dos;
                    var tres;
                    var cuatro;
                    var cinco;
                    var seis;
                    for (var i = 0; i < array.length; i++) {


                        uno = array[0];
                        dos = array[1];
                        tres = array[2];
                        cuatro = array[3];
                        cinco = array[4];
                        seis = array[5];
                    }
                    drawChart(uno, dos, tres, cuatro, cinco, seis);
                });
            });

    function drawChart(uno, dos, tres, cuatro, cinco, seis) {
        var data = google.visualization.arrayToDataTable([
            ["Element", "Cantidad", {role: "style"}],
            ["Estrato 1", parseInt(uno), "#b87333"], //)
            ["Estrato 2", parseInt(dos), "silver"],
            ["Estrato 3", parseInt(tres), "#e6ee9c"],
            ["Estrato 4", parseInt(cuatro), "#b2ff59"],
            ["Estrato 5", parseInt(cinco), "#d84315"],
            ["Estrato 6", parseInt(seis), "#0097a7"]
        ]);

        var options = {
            title: 'Cantidad estrato por ficha',
            hAxis: {title: 'Estrato'}, //
            vAxis: {title: 'Cantidad', titleTextStyle: {color: '#333'}},
            animation: {
                duration: 1000,
                easing: 'out'
            }
        };

        var chart = new google.visualization.AreaChart(document.getElementById('stdEstrato'));
        chart.draw(data, options);
        //dando animación
//        setTimeout(function () {
//         for (var f = 0; f < fecha.length; f++) {
//         data.setValue(f, 1, dia[f]);
//         }
//         chart.draw(data, options);
//         }, 1000);
    }
    
}

