function guardarAlumno(){
    $.ajax({
        url:"NewServlet",//servlet destino 
        dataType: "html", //tipo de respuesta
        data: "control=INSERT&"+ $("#registro").serialize(),//datos se envían al server
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
 function guardarFormulario(){
    $.ajax({
        url:"NewServlet",//servlet destino 
        dataType: "html", //tipo de respuesta
        data: "control=INSERTA&"+ $("#rregistro").serialize(),//datos se envían al server
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

     $('#exito').fadeIn(2000);
     $('#exito').fadeOut(2000);
 }
 
 function ocultarMensajeExitoso(){
     setTimeout(function() {
        $("#exito").fadeOut(500);
       },1500);
 }
 
 function mostrarMensajeError(){
     $('#fallo').fadeIn(2000);
     $('#fallo').fadeOut(2000);
 }
 
 function ocultarMensajeError(){
     setTimeout(function() {
        $("#fallo").fadeOut(500);
       },1500);
 }

function limpiarFormulario(){
        $("#FECHA").val("");
        $("#NOMBRE").val("");
        $("#DPI").val("");
        $("#MUNICIPIO").val("");
        $("#DEPARTAMENTO").val("");
        $("#RESIDENCIA").val("");
        $("#TELEFONO").val("");
        $("#DENUNCIA").val("");
}