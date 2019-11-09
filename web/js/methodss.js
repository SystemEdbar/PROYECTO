
 function guardarFormulario(){
    $.ajax({
        url:"NewServlet",//servlet destino 
        dataType: "html", //tipo de respuesta
        data: "control=INSERTA&"+ $("#RRegistro").serialize(),//datos se envían al server
        method:"POST",
        success: function(respuesta){
            if(respuesta==="1"){ 
                mostrarMensajeExitoso();
                ocultarMensajeExitoso();
                limpiarFormulario();    
            }else{
                mostrarMensajeError();
                ocultarMensajeError();
            }
        },
        error: function(objAjax, estado, excepcion){
            alert("error en la comunicación");
        }
    });
 }

function mostrarMensajeExitoso(){

     $('#exit').fadeIn(2000);
     $('#exit').fadeOut(2000);
 }
 
 function ocultarMensajeExitoso(){
     setTimeout(function() {
        $("#exit").fadeOut(500);
       },1500);
 }
 
 function mostrarMensajeError(){
     $('#fall').fadeIn(2000);
     $('#fall').fadeOut(2000);
 }
 
 function ocultarMensajeError(){
     setTimeout(function() {
        $("#fall").fadeOut(500);
       },1500);
 }

function limpiarFormulario(){
        $("#FFECHA").val("");
        $("#NNOMBRE").val("");
        $("#DDPI").val("");
        $("#MMUNICIPIO").val("");
        $("#DDEPARTAMENTO").val("");
        $("#RRESIDENCIA").val("");
        $("#TTELEFONO").val("");
        $("#DDENUNCIA").val("");
}