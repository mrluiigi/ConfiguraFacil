/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

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
        String user = "root";
        
        
        
        
        
        
        
        
        
        
    String password = "";
        Connection con = DriverManager.getConnection(connection, user, password);
        ConfiguraFacil model = new ConfiguraFacil(con);
        
        
        
        
        
        
        
        
        
    Statement st;
        try { 
            st = con.createStatement(); 
            st.executeUpdate("INSERT INTO Configuração (NIF_Cliente, Pronta, Feita) VALUES ('111111111', 0, 0)"); 
            ResultSet rs = st.executeQuery("SELECT MAX(ID) AS LastID FROM Configuração;"); 
            while (rs.next()) { 
                System.out.println(rs.getString("LastID"));    
            } 
           
        } catch (SQLException e) { 
                e.printStackTrace(System.out);
        } finally { 
            //close the connection 
            con.close(); 
        }    
        
        
        
        
        
        
        
        
        
      
        View view = new View();
        
        Controller controller = new Controller(model);
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    }
    
}







































