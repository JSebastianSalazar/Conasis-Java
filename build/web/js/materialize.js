/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function() {
    $('select').material_select(); 
    $('.modal-trigger').leanModal({
      dismissible: false // Modal can be dismissed by clicking outside of the modal
    }
  );
  $('.tooltipped').tooltip({delay: 50});
    ocultar();
  });
        
function ocultar(){
    $("#vTxtarea").hide();
}

