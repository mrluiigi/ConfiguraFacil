/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import projeto.logica.Configuracao;

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
            st.executeUpdate("INSERT INTO Configuração (NIF_Cliente, Pronta, Preco, Modelo) VALUES (" + c.getNif() + ",0," + c.getPreco() + "," + "\"" + c.getModelo() + "\"" + ");"); 
            ResultSet rs = st.executeQuery("SELECT MAX(ID) AS LastID FROM Configuração;");
            rs.next();
            int idConfig = Integer.parseInt(rs.getString("LastID"));
            for(int i : c.getComponentesObrigatorios()) {
                st.executeUpdate("INSERT INTO ConfiguraçãoObrigatórios (Configuração_ID, Obrigatório_ID) VALUES ("+ idConfig + "," + i + ");"); 
            }
            for(int i : c.getComponentesOpcionais()) {
                st.executeUpdate("INSERT INTO ConfiguraçãoOpcionais (Configuração_ID, Opcional_ID) VALUES ("+ idConfig + "," + i + ");"); 
            }
            for(int i : c.getPacotes()) {
                st.executeUpdate("INSERT INTO ConfiguraçãoPacotes (Configuração_ID, Pacote_ID) VALUES ("+ idConfig + "," + i + ");"); 
            }

        } catch (SQLException e) { 
                e.printStackTrace(System.out);
        } finally { 
            //close the connection 
      
        } 
    }
    
    
    public List<Configuracao> getConfiguracoesPorFabricar() throws SQLException {
        Statement st;
        List<Configuracao> res = new ArrayList<>();
        try { 
            st = con.createStatement(); 
            ResultSet rs = st.executeQuery("Select ID, NIF_Cliente, Pronta, Modelo, Preco FROM Configuração WHERE PRONTA = 0;"); 
            while(rs.next()) {
                int id = Integer.parseInt(rs.getString("ID"));
                String nif = rs.getString("NIF_Cliente");
                String modelo = rs.getString("Modelo");
                float preco = Float.parseFloat(rs.getString("Preco"));
                
                Statement stob = con.createStatement();
                List <Integer> obgs =  new ArrayList<>();
                ResultSet rsObgs = stob.executeQuery("SELECT Obrigatório_ID FROM ConfiguraçãoObrigatórios WHERE Configuração_ID =" + id +";");
                while(rsObgs.next()) {
                    obgs.add(Integer.parseInt(rsObgs.getString("Obrigatório_ID")));
                }
                
                Statement stOp = con.createStatement();
                List <Integer> opts =  new ArrayList<>();
                ResultSet rsOpts = stOp.executeQuery("SELECT Opcional_ID FROM ConfiguraçãoOpcionais WHERE Configuração_ID =" + id +";");
                while(rsOpts.next()) {
                    opts.add(Integer.parseInt(rsOpts.getString("Opcional_ID")));
                }
                
                Statement stp = con.createStatement();
                List <Integer> pcts =  new ArrayList<>();
                ResultSet rsPcts = stp.executeQuery("SELECT Pacote_ID FROM ConfiguraçãoPacotes WHERE Configuração_ID =" + id +";");
                while(rsPcts.next()) {
                    pcts.add(Integer.parseInt(rsPcts.getString("Pacote_ID")));
                }
                res.add(new Configuracao(id, nif, modelo, preco, obgs, opts, pcts, false));
            }
        } catch (SQLException e) { 
                e.printStackTrace(System.out);
        } finally { 
            //close the connection 
            
        return res;
        } 
    }
    
    
    public void marcaComoPronta(int id) throws SQLException {
        Statement st;
        st = con.createStatement();
        try { 
            st.executeUpdate("UPDATE Configuração SET Pronta = 1 WHERE ID =" + id + ";");
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
    }
    
    
    public Configuracao getConfiguracaoPorID(int id) throws SQLException {
        Statement st;
        st = con.createStatement(); 
        Configuracao res = null;
        
        try {
            ResultSet rs = st.executeQuery("SELECT NIF_Cliente,Modelo,Preco FROM Configuração WHERE ID = " + id + ";");
            rs.next();
            String nif = rs.getString("NIF_Cliente");
            String modelo = rs.getString("Modelo");
            float preco = Float.parseFloat(rs.getString("Preco"));
                
            List <Integer> obgs =  new ArrayList<>();
            ResultSet rsObgs = st.executeQuery("SELECT Obrigatório_ID FROM ConfiguraçãoObrigatórios WHERE Configuração_ID =" + id +";");
            while(rsObgs.next()) {
                obgs.add(Integer.parseInt(rsObgs.getString("Obrigatório_ID")));
            }
                
            List <Integer> opts =  new ArrayList<>();
            ResultSet rsOpts = st.executeQuery("SELECT Opcional_ID FROM ConfiguraçãoOpcionais WHERE Configuração_ID =" + id +";");
            while(rsOpts.next()) {
                opts.add(Integer.parseInt(rsOpts.getString("Opcional_ID")));
            }
            
            List <Integer> pcts =  new ArrayList<>();
            ResultSet rsPcts = st.executeQuery("SELECT Pacote_ID FROM ConfiguraçãoPacotes WHERE Configuração_ID =" + id +";");
            while(rsPcts.next()) {
                pcts.add(Integer.parseInt(rsPcts.getString("Pacote_ID")));
            }
            
            res = (new Configuracao(id, nif, modelo, preco, obgs, opts, pcts, false)); //???
        } catch (SQLException e) { 
            e.printStackTrace(System.out);
        }
        
        return res;
    }
}
