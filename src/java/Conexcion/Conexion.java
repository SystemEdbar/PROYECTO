
package Conexcion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private String url="jdbc:oracle:thin:@localhost:1521:xe";//url de nuestro odbc
    private String usuario="system";
    private String clave="Marimar"; 
    private Connection conexion=null;
    
    
    public Connection conectar(){   
        try{
            Class.forName("oracle.jdbc.OracleDriver");
            conexion=DriverManager.getConnection( url, usuario,clave);
        }  catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {   
           ex.printStackTrace();
        }  
       
        return conexion;
        
    }
}
