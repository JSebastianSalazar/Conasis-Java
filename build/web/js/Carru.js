/* 
 
 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    
  
    var carousel = $("#carousel").featureCarousel({
        // include options like this:
        // (use quotes only for string values, and no trailing comma after last option)
        // option: value,
        // option: value
    });

    $("#but_prev").click(function () {
        carousel.prev();
    });
    $("#but_pause").click(function () {
        carousel.pause();
    });
    $("#but_start").click(function () {
        carousel.start();
    });
    $("#but_next").click(function () {
        carousel.next();
    });
    
   //registro
$("#actualizarlll").click(function (){
    ("#tabsRe").trigger( "click" );
});

$("#contenedorActualiza").hide();


});
$("#actualizarlll").click(function (){
    $("#contenedorActualiza").show();
    $("#eliminarlll").hide();
    $("#actualizarlll").hide();
});
$("#cancelll").click(function (){
    $("#contenedorActualiza").hide();
     $("#eliminarlll").show();
    $("#actualizarlll").show();
    
    });
    $("#admi").hide();
    $("#buscar").keyup(function() {
          $("#admi").show();
          if( $("#buscar").val().length>1){
              
          }else{
              $("#admi").hide(); 
          }
        
    });
    
    
    
  $("#eliminarlll").click(function (){
      $.ajax({
            url: "RegistroInstructor",
            method: "post",
            data: {
                numero_documento: $("#buscar").val(),
                validar: "eliminar"
            },
            success: function (msg) {
                swal("Bien", msg, "success");
            },
            error: function (respuesta, j , k) {
                swal("Error", k, "error");
            }, complete: function (jqXHR, textStatus) {

            }

        });
        
        
  });
