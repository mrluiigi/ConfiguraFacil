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
import java.util.List;

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
            st.executeUpdate("INSERT INTO Configuração (NIF_Cliente, Pronta) VALUES ("+ c.getNif() + ", 0)"); 
            ResultSet rs = st.executeQuery("SELECT MAX(ID) AS LastID FROM Configuração;"); 
            int idConfig = Integer.parseInt(rs.getString("LastID"));
            for(int i : c.getComponentesObrigatorios()) {
                st.executeUpdate("INSERT INTO ConfiguraçãoObrigatórios (Configuração_ID, Obrigatório_ID) VALUES ("+ idConfig + "," + i + ")"); 
            }
            for(int i : c.getComponentesOpcionais()) {
                st.executeUpdate("INSERT INTO ConfiguraçãoOpcionais (Configuração_ID, Opcional_ID) VALUES ("+ idConfig + "," + i + ")"); 
            }
            for(int i : c.getPacotes()) {
                st.executeUpdate("INSERT INTO ConfiguraçãoPacotes (Configuração_ID, Pacote_ID) VALUES ("+ idConfig + "," + i + ")"); 
            }

        } catch (SQLException e) { 
                e.printStackTrace(System.out);
        } finally { 
            //close the connection 
            con.close(); 
        } 
    }
    
    /*
    public List<Configuracao> getConfiguracoesPorFabricar() {
        Statement st;
        try { 
            st = con.createStatement(); 
            ResultSet rs = st.executeQuery("Select ID, NIF_Cliente, Pronta FROM Configuração WHERE PRONTA = 0;"); 
            whi
            int idConfig = Integer.parseInt(rs.getString("LastID"));
            for(int i : c.getComponentesObrigatorios()) {
                st.executeUpdate("INSERT INTO ConfiguraçãoObrigatórios (Configuração_ID, Obrigatório_ID) VALUES ("+ idConfig + "," + i + ")"); 
            }
            for(int i : c.getComponentesOpcionais()) {
                st.executeUpdate("INSERT INTO ConfiguraçãoOpcionais (Configuração_ID, Opcional_ID) VALUES ("+ idConfig + "," + i + ")"); 
            }
            for(int i : c.getPacotes()) {
                st.executeUpdate("INSERT INTO ConfiguraçãoPacotes (Configuração_ID, Pacote_ID) VALUES ("+ idConfig + "," + i + ")"); 
            }

        } catch (SQLException e) { 
                e.printStackTrace(System.out);
        } finally { 
            //close the connection 
            con.close(); 
        } 
    }*/
    
    
}
