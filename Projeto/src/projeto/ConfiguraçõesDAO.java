/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

/**
 *
 * @author jose9
 */
public class ConfiguraçõesDAO {
    private Connection con;

    public ConfiguraçõesDAO(Connection con) {
        this.con = con;
    }
    
    public void addConfiguracao(Configuracao c) throws SQLException{
        Statement st;
        try { 
            st = con.createStatement(); 
           st.executeUpdate("INSERT INTO Configuração (NIF_Cliente, Pronta, Feita, Data) VALUES ('123456789', '0', " + LocalDate.now().toString() + ")"); 
        } catch (SQLException e) { 
            // handle exceptions 
        } finally { 
            //close the connection 
            con.close(); 
        }
    }
    
    
}
