$(document).ready(function () {

    listarFichas();
    $('select').material_select();
    //llamando a google chart Solo se puede llamar una sola vez
    google.charts.load('current', {'packages': ['corechart']});

});

function stadistica3(id) {
    var valor = $(id).attr('id');

    window.location.assign("EstadisticaMunicipio.jsp");
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
    stdMUnicipiio(Nficha);

});
function stdMUnicipiio(ficha) {

    $.ajax({
        beforeSend: function () {
        },
        method: "POST",
        url: "ServletEstadistica", //nombre del servlet
        data: {
            //aca se optiene  los datos del formulario para enviarlos al servlet
            validacion: "stMunicipio",
            numeroFicha2: ficha
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
                    var Medellin;
                    var Bello;
                    var Itagüi;
                    var Caldas;
                    var Estrella;
                    var Sabaneta;
                    var Envigado;
                    var Copacabana;
                    var Girardota;
                    var Barbosa;
                    for (var i = 0; i < array.length; i++) {


                        Medellin = array[0];
                        Bello = array[1];
                        Itagüi = array[2];
                        Caldas = array[3];
                        Estrella = array[4];
                        Sabaneta = array[5];
                        Envigado = array[6];
                        Copacabana = array[7];
                        Girardota = array[8];
                        Barbosa = array[9];
                    }
                    drawChart(Medellin, Bello, Itagüi, Caldas, Estrella, Sabaneta, Envigado, Copacabana, Girardota, Barbosa);
                });
            });

    function drawChart(Medellin, Bello, Itagüi, Caldas, Estrella, Sabaneta, Envigado, Copacabana, Girardota, Barbosa) {
        var data = google.visualization.arrayToDataTable([
            ["Element", "Cantidad", {role: "style"}],
            ["Medellin", parseInt(Medellin),"#e53935"], //)
            ["Bello", parseInt(Bello),"silver"],
            ["Itagüi", parseInt(Itagüi), "#6a1b9a"],
            ["Caldas", parseInt(Caldas),"#90caf9"],
            ["Estrella", parseInt(Estrella),"#f0f4c3"],
            ["Sabaneta", parseInt(Sabaneta),"#006064"],
            ["Envigado", parseInt(Envigado),"#26a69a"],
            ["Copacabana", parseInt(Copacabana),"#0097a7"],
            ["Girardota", parseInt(Girardota),"#82b1ff"],
            ["Barbosa", parseInt(Barbosa),"#303f9f "]
        ]);

        var options = {
            title: 'Cantidad  Municipio  por ficha',
            hAxis: {title: 'Cantidad '}, //
            vAxis: {title: 'Municipio', titleTextStyle: {color: '#333'}},
            animation: {
                duration: 1000,
                easing: 'out'
            }
        };

        var chart = new google.visualization.PieChart(document.getElementById('stdMunicipio'));
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

