/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto;

import java.sql.Connection;
import java.sql.ResultSet;
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
            st.executeUpdate("INSERT INTO Configuração (NIF_Cliente, Pronta, Feita) VALUES ("+ c.getNif() + ", 0, 0)"); 
            ResultSet rs = st.executeQuery("SELECT MAX(ID) AS LastID FROM Configuração;"); 
            int idConfig = Integer.parseInt(rs.getString("LastID"));
            for(int i : c.getComponentesObrigatorios()) {
                st.executeUpdate("INSERT INTO ConfiguraçãoObrigatórios (Configuração_ID, Obrigatório_ID) VALUES ("+ idConfig + "," + i + ")"); 
            }

        } catch (SQLException e) { 
                e.printStackTrace(System.out);
        } finally { 
            //close the connection 
            con.close(); 
        } 
    }
    
    
}
