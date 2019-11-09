/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import BaseDatos.BD_Form;
import Conexcion.Conexion;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author edwin
 */
@WebServlet(urlPatterns = {"/NewServlet"})
public class NewServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
        String user_recu, contra_recu;
        Gson gson = new Gson(); 
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
            Writer ajaxSalida =  response.getWriter(); 
        Conexion conexion = new Conexion();  //PARA REALIZAR LA CONEXCION
        conexion.conectar();
        BD_Form bd_form = new BD_Form();
        StringBuffer respuestaB =  new StringBuffer(); //PARA RESPUESTA DE INSERTAR Y MOSTRAR
        
        //PARA GUARDAR LOS DATOS
     
        String json;
        StringBuffer datos = new StringBuffer();
        
        //datos para validar el usuario
        String user= request.getParameter("username"); 
        String contra= request.getParameter("password");
        String DPI= request.getParameter("DPII");
        String resultado="";
        Boolean valor=true;
        Boolean validar;
        try{
            String control= request.getParameter("control");
            switch(control){
            case "INSERT":
                    
                     String dato1 = request.getParameter("FECHA");
                     String dato2 =  request.getParameter("NOMBRE");
                     String dato3 =  request.getParameter("DPI");
                     String dato4 = request.getParameter("MUNICIPIO");
                     String dato5 =  request.getParameter("DEPARTAMENTO");
                     String dato6 =  request.getParameter("RESIDENCIA");
                     String dato7 = request.getParameter("TELEFONO");
                     String dato8 =  request.getParameter("DENUNCIA");
                     System.out.println("Del controlador"+dato2);
                String respuesta =(bd_form.insert(dato1,dato2,dato3,dato4,dato5,dato6,dato7,dato8));
                response.getWriter().print(respuesta);
            break;
            case "INSERTA":
                    
                     String datoo1 = request.getParameter("FFECHA");
                     String datoo2 =  request.getParameter("NNOMBRE");
                     String datoo3 =  request.getParameter("DDPI");
                     String datoo4 = request.getParameter("MMUNICIPIO");
                     String datoo5 =  request.getParameter("DDEPARTAMENTO");
                     String datoo6 =  request.getParameter("RRESIDENCIA");
                     String datoo7 = request.getParameter("TTELEFONO");
                     String datoo8 =  request.getParameter("DDENUNCIA");
                     System.out.println("Del controlador"+datoo2);
                String respuestta =(bd_form.insert(datoo1,datoo2,datoo3,datoo4,datoo5,datoo6,datoo7,datoo8));
                response.getWriter().print(respuestta);
            break;
            case "INSERTUSER":
                    
                     String dato11 = request.getParameter("DPI");
                     String dato22 =  request.getParameter("NOMBRE");
                     String dato33 =  request.getParameter("APELLIDO");
                     String dato44 = request.getParameter("SEXO");
                     String dato55 =  request.getParameter("USUARIO");
                     String dato66 =  request.getParameter("CONTRASENA");
                     System.out.println("Del controlador"+dato22);
                String respuestaa =(bd_form.insertUser(dato11,dato22,dato33,dato44,dato55,dato66));
                response.getWriter().print(respuestaa);
            break;
            case "MOSTRAR":
                
                 respuestaB.append(bd_form.consultarRegistros());
                 System.out.println("Respuesta del servlet");
                 System.out.println(respuestaB);
                   ajaxSalida.write(respuestaB.toString());
                   ajaxSalida.flush();
                   ajaxSalida.close();
            break;
            case "MOSTRARDEN":
                System.out.println(DPI);
                 respuestaB.append(bd_form.consultarRegistrosUsuario(DPI));
                 System.out.println(respuestaB);
                   ajaxSalida.write(respuestaB.toString());
                   ajaxSalida.flush();
                   ajaxSalida.close();
            break;
            case "MOSTRARVISIT":
                
                 respuestaB.append(bd_form.consultarRegistrosVisit());
                 System.out.println("Respuesta del servlet");
                 System.out.println(respuestaB);
                   ajaxSalida.write(respuestaB.toString());
                   ajaxSalida.flush();
                   ajaxSalida.close();
            break;
            case "VALIDAR":
                validar = bd_form.consultarUsuario(user , contra);
                
                if(valor.equals(validar)){
                    resultado="1";
                    user_recu=user; contra_recu=contra;
                }
                else
                {
                    resultado="0";
                }
                response.getWriter().print(resultado);
            break;
            case "RECUPERAR":
                validar = bd_form.consultarUsuario(user_recu , contra_recu);
                if(valor.equals(validar)){
                    datos.append(bd_form.obtenerUsuario(user_recu , contra_recu));
                    json = gson.toJson(datos.toString());
                    ajaxSalida.write(json);
                    ajaxSalida.flush();
                    ajaxSalida.close();
                }
                else
                {
                    resultado="0";
                    response.getWriter().print(resultado);
                }
                
            break;
        }}catch(Exception e){
                e.getMessage();
        }}

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
