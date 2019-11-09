
package BaseDatos;

import Conexcion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BD_Form {
    private Conexion conn =new Conexion();
    private Connection cn;
    private PreparedStatement prstmt = null;
    private ResultSet result = null;
                StringBuffer respuesta =  new StringBuffer();

    public BD_Form(){
        cn=conn.conectar();
    }
  
    public String insert(String FECHA, String NOMBRE, String DPI, String MUNICIPIO, String DEPARTAMENTO, String RESIDENCIA, String TELEFONO, String DENUNCIA){
        String sql = "INSERT INTO REGISTER_REGISTRO(FECHA, NOMBRE, DPI, MUNICIPIO, DEPARTAMENTO, RESIDENCIA, TELEFONO, DENUNCIA) ";
             sql += " VALUES( ?,?,?,?,?,?,?,?)"; 
        try{
            prstmt = cn.prepareStatement(sql);
            System.out.println(NOMBRE);
            prstmt.setString(1, FECHA);
            prstmt.setString(2, NOMBRE);
            prstmt.setString(3, DPI);
            prstmt.setString(4, MUNICIPIO);
            prstmt.setString(5, DEPARTAMENTO);
            prstmt.setString(6, RESIDENCIA);
            prstmt.setString(7, TELEFONO);
            prstmt.setString(8, DENUNCIA);
            System.out.println(DENUNCIA);
             int resultado = prstmt.executeUpdate(); 
                if(resultado > 0){
                   return "1";
                }else{
                     return "0";
                }
        }catch(SQLException e){
            String error = e.getMessage();  
            if(error.indexOf("ORA-00001") != -1){
                //salida.append("ORA-00001");
                  return "ORA-00001";
            }else{
                //salida.append(error);
                  return "error al guardar";
            }
        }
    
    }
        public String insertUser(String DPI, String NOMBRE, String APELLIDO, String SEXO, String USUARIO, String CONTRASENA){
        String sql = "INSERT INTO USER_USUARIO( DPI, NOMBRE, APELLIDO, SEXO, USUARIO, CONTRASENA) ";
             sql += " VALUES( ?,?,?,?,?,?)"; 
        try{
            prstmt = cn.prepareStatement(sql);
            System.out.println(NOMBRE);
            prstmt.setString(1, DPI);
            prstmt.setString(2, NOMBRE);
            prstmt.setString(3, APELLIDO);
            prstmt.setString(4, SEXO);
            prstmt.setString(5, USUARIO);
            prstmt.setString(6, CONTRASENA);
             int resultado = prstmt.executeUpdate(); 
                if(resultado > 0){
                   return "1";
                }else{
                     return "0";
                }
        }catch(SQLException e){
            String error = e.getMessage();  
            if(error.indexOf("ORA-00001") != -1){
                //salida.append("ORA-00001");
                  return "ORA-00001";
            }else{
                //salida.append(error);
                  return "error al guardar";
            }
        }
    
    }
    
   public StringBuffer consultarRegistros(){   
        String sql="select * from REGISTER_REGISTRO";
        try{
        prstmt = cn.prepareStatement(sql);                        
        result = prstmt.executeQuery();            

            
                while (result.next()){
                respuesta.append("<tr>");
                respuesta.append("<td >").append(result.getString("FECHA")).append("</td>");
                respuesta.append("<td >").append(result.getString("NOMBRE")).append("</td>");
                respuesta.append("<td >").append(result.getString("DPI")).append("</td>");
                respuesta.append("<td >").append(result.getString("MUNICIPIO")).append("</td>");
                respuesta.append("<td >").append(result.getString("DEPARTAMENTO")).append("</td>");
                respuesta.append("<td >").append(result.getString("RESIDENCIA")).append("</td>");
                respuesta.append("<td >").append(result.getString("TELEFONO")).append("</td>");
                respuesta.append("<td >").append(result.getString("DENUNCIA")).append("</td>");
                respuesta.append("</tr>");
                }
           
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        System.out.println("Respuesta");
        System.out.println(respuesta);
        return respuesta;
    }
   
   public StringBuffer consultarRegistrosUsuario(String dpi){   
        String sql="select * from REGISTER_REGISTRO where DPI ='"+ dpi +"'";
        try{
            System.out.println(sql);
        prstmt = cn.prepareStatement(sql);                        
        result = prstmt.executeQuery();           
            System.out.println("si entro");
            
                while (result.next()){
                System.out.println("ENTRO");
                respuesta.append("<tr>");
                respuesta.append("<td>").append(result.getString("FECHA")).append("</td>");
                respuesta.append("<td>").append(result.getString("NOMBRE")).append("</td>");
                respuesta.append("<td>").append(result.getString("DPI")).append("</td>");
                respuesta.append("<td>").append(result.getString("MUNICIPIO")).append("</td>");
                respuesta.append("<td>").append(result.getString("DEPARTAMENTO")).append("</td>");
                respuesta.append("<td>").append(result.getString("RESIDENCIA")).append("</td>");
                respuesta.append("<td>").append(result.getString("TELEFONO")).append("</td>");
                respuesta.append("<td>").append(result.getString("DENUNCIA")).append("</td>");
                respuesta.append("</tr>");
                }
           
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        System.out.println("Respuesta");
        System.out.println(respuesta);
        return respuesta;
    }
   
      public StringBuffer consultarRegistrosVisit(){   
        String sql="select * from REGISTER_REGISTRO";
        try{
        prstmt = cn.prepareStatement(sql);                        
        result = prstmt.executeQuery();            

            
                while (result.next()){
                respuesta.append("<tr> ");
                respuesta.append("<td>").append(result.getString("FECHA")).append("</td>");
                respuesta.append("<td>").append(result.getString("DENUNCIA")).append("</td>");
                respuesta.append("</tr>");
                }
           
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        System.out.println("Respuesta del modelo");
        System.out.println(respuesta);
        return respuesta;
    }
   

   public boolean consultarUsuario(String usuario, String contra){
        boolean Validar=false;
        String sql="select * from USER_USUARIO where USUARIO='"+usuario+"' and CONTRASENA='"+contra+"' ";
        try{
        prstmt = cn.prepareStatement(sql);                        
        result = prstmt.executeQuery();            
           
                while (result.next()){
                    if(usuario.equals(result.getString("USUARIO")) && contra.equals(result.getString("CONTRASENA")))
                    {
                        Validar=true;
                    }
                }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        System.out.println(Validar);
        System.out.println("Aquí es");
        return Validar;
        
    }


   public StringBuffer obtenerUsuario(String usuario, String contra){
        StringBuffer datos= new StringBuffer();
        String sql="select * from USER_USUARIO where USUARIO='"+usuario+"' and CONTRASENA='"+contra+"' ";
        try{
        prstmt = cn.prepareStatement(sql);                        
        result = prstmt.executeQuery();            
           
                while (result.next()){
                    if(usuario.equals(result.getString("USUARIO")) && contra.equals(result.getString("CONTRASENA")))
                    {
                        datos.append("[{\"Nombre\":\""+result.getString("NOMBRE")+"\", \"Apellido\":\""+result.getString("APELLIDO")+"\", \"DPI\":\""+result.getString("DPI")+"\", \"Usuario\":\""+result.getString("Usuario")+"\", \"Sexo\":\""+result.getString("SEXO")+"\"}]");
                    }
                }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        System.out.println(datos);
        System.out.println("Aquí es");
        return datos;
        
    }
}


/*

--INICIO--
    CREATE TABLE USER_USUARIO (
        DPI             VARCHAR2(15 BYTE) NOT NULL,
        NOMBRE          VARCHAR2(30 BYTE) NOT NULL,
        APELLIDO        VARCHAR2(30 BYTE) NOT NULL,
        SEXO            VARCHAR2(10 BYTE) NOT NULL,
        USUARIO         VARCHAR2(25 BYTE) NOT NULL,
        CONTRASENA      VARCHAR2(16 BYTE) NOT NULL
);
ALTER TABLE USER_USUARIO ADD CONSTRAINT USER_PK PRIMARY KEY (DPI, USUARIO);
  

--REGISTRO--
  
  CREATE TABLE REGISTER_REGISTRO (
    FECHA          VARCHAR2(30 BYTE) NOT NULL,
    NOMBRE         VARCHAR2(60 BYTE) NOT NULL,
    DPI            VARCHAR2(15 BYTE) NOT NULL,
    MUNICIPIO      VARCHAR2(25 BYTE) NOT NULL,
    DEPARTAMENTO   VARCHAR2(30 BYTE) NOT NULL,
    RESIDENCIA     VARCHAR2(30 BYTE) NOT NULL,
    TELEFONO       VARCHAR2(10 BYTE) NOT NULL,
    DENUNCIA       VARCHAR2(500 BYTE) NOT NULL
);


--INSERTAR--
INSERT INTO "USER_USUARIO" (DPI, NOMBRE, APELLIDO, SEXO, USUARIO, CONTRASENA) VALUES ('3390-38814-1801','Gladys Noemí','Lemus Mejía','Femenino','NoemiJR', 'Root');
INSERT INTO "USER_USUARIO" (DPI, NOMBRE, APELLIDO, SEXO, USUARIO, CONTRASENA) VALUES ('3310-38161-1801','Edwin Enrique','Barrera Castillo','Masculino','EdwinJR', 'Root');
INSERT INTO REGISTER_REGISTRO(FECHA, NOMBRE, DPI, MUNICIPIO, DEPARTAMENTO, RESIDENCIA, TELEFONO, DENUNCIA) VALUES ('07/11/2019','Edwin Enrique Barrera Castillo','3310-38161-1801','Puerto Barrios','Izabal','Mi Casita','54691801','PRUEBA 1');


--ELIMINAR
Drop Table REGISTER_REGISTRO;
Drop Table USER_USUARIO;

*/