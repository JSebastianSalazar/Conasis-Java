/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(inicio);
    function inicio(){
        $("#fichasAmbientes").hide();
        $("#AsistenciaCompetencia").hide();
        $("#UsuariosEstadicticas").show();
        $("#usuarios").click(OfichasMusuarios);
        $("#grupos").click(OusuariosMfichas);
        $("#asistencia").click(OusuarioYfichasMasistencia);
    }
    function OfichasMusuarios(){
        $("#fichasAmbientes").hide();
        $("#AsistenciaCompetencia").hide();
        $("#UsuariosEstadicticas").slideDown("slow");
    }
    function OusuariosMfichas(){
        $("#fichasAmbientes").slideDown("slow");
        $("#UsuariosEstadicticas").slideUp("slow");
        $("#AsistenciaCompetencia").hide();
    }
    function OusuarioYfichasMasistencia(){
        $("#fichasAmbientes").slideUp("slow");
        $("#UsuariosEstadicticas").slideUp("slow");
        $("#AsistenciaCompetencia").show();
    }
    
    $(document).ready(function () {
    // Materialize Calendar init
    $('.datepicker').pickadate({//calendario
        selectMonths: true, // Creates a dropdown to control month
        selectYears: true// Creates a dropdown of 15 years to control year
    });
    // Materialize campo seleccion tipo documento
    $('select').material_select();//campo de opciones
    $('.fixed-action-btn').openFAB();//botones emergentes
    $('.modal-trigger').leanModal();//pantalla emergente spam
    $('.tooltipped').tooltip({delay: 50}); //informacion adicional emergente
    $('.fixed-action-btn').closeFAB();
    $('.carousel').carousel();

});
