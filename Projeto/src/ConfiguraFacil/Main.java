
package ConfiguraFacil;

import UI.View;
import java.sql.SQLException;

/**
 *
 * @author José Pinto A81317
 * @author Luís Correia A81141
 * @author Pedro Barbosa A82068
 */
public class Main {
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException {       
        ConfiguraFacil model = new ConfiguraFacil(); 
        View view = new View();
        Controller controller = new Controller(model);   
        
        
        
        
    } 
    
    
    
}







































