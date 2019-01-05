
package projeto;

import projeto.logica.ConfiguraFacil;
import projeto.view.View;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author José Pinto A81317
 * @author Luís Correia A81141
 * @author Pedro Barbosa A82068
 */
public class Main {
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        //Establish the connection 
        String connection = "jdbc:mysql://localhost:3306/dss?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String user = "user";
        String password = "pass";
        Connection con = DriverManager.getConnection(connection, user, password);
        ConfiguraFacil model = new ConfiguraFacil(con); 
        View view = new View();
        Controller controller = new Controller(model);   
        
        
        
        
    } 
    
    
    
}







































