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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Barbosa
 */
public class ComponentesDAO {
    private Connection con;

    public ComponentesDAO(Connection con) {
        this.con = con;
    }
    
    public Obrigatorio getObrigatorio(int id) throws SQLException {
        Statement st;
        Obrigatorio o = null;
        
        try { 
            st = con.createStatement(); 
            ResultSet rs = st.executeQuery("SELECT * FROM obrigatório WHERE ID = " + id + ";");
            rs.next();
            float preco = Float.parseFloat(rs.getString("Preco"));
            String designacao = rs.getString("Designacao");
            int stock = Integer.parseInt(rs.getString("Stock"));
            String categoria = rs.getString("Categoria");
            
            o = new Obrigatorio(id, preco, designacao, stock, categoria);
            
        } catch (SQLException e) { 
            e.printStackTrace(System.out);
        }
        
        return o;
    }
    
    
    public Opcional getOpcional(int id) throws SQLException {
        Statement st;
        Opcional o = null;
        
        try { 
            st = con.createStatement(); 
            ResultSet rs = st.executeQuery("SELECT Preco,Designacao,Stock,Categoria,Pacote_ID FROM opcional WHERE ID = " + id + ";");
            rs.next();
            
            float preco = Float.parseFloat(rs.getString("Preco"));
            String designacao = rs.getString("Designacao");
            int stock = Integer.parseInt(rs.getString("Stock"));
            String categoria = rs.getString("Categoria");
            String pp = rs.getString("Pacote_ID");
            int pertencePacote = 0;
            if (pp != null) {
                pertencePacote = Integer.parseInt(pp);
            }
             
            
            List<Integer> necessarios = new ArrayList();
            ResultSet rsNec = st.executeQuery("SELECT Necessitado FROM ComponenteNecessitaComponente WHERE Necessita = " + id +";");
                while(rsNec.next()) {
                    necessarios.add(Integer.parseInt(rsNec.getString("Necessitado")));
                }
                
            List<Integer> incompativeis = new ArrayList();
            ResultSet rsInc = st.executeQuery("SELECT Opcional_ID1 FROM ComponenteIncompatívelComponente WHERE Opcional_ID = " + id +";");
                while(rsInc.next()) {
                    incompativeis.add(Integer.parseInt(rsInc.getString("Opcional_ID1")));
                }
                
            
            
            o = new Opcional(necessarios, incompativeis, pertencePacote, id, preco, designacao, stock, categoria);
            
        } catch (SQLException e) { 
            e.printStackTrace(System.out);
        }
        
        return o;
    }
    
    
    public boolean temStock(int id, boolean obrigatorio) throws SQLException {
        Statement st;
        st = con.createStatement(); 

        try {
            if(obrigatorio){
                ResultSet rs = st.executeQuery("SELECT Stock FROM obrigatório WHERE ID = " + id +";");
                int stock = Integer.parseInt((rs.getString("Stock")));
                return (stock > 0);
            }
            else{
                ResultSet rs = st.executeQuery("SELECT Stock FROM opcional WHERE ID = " + id +";");
                int stock = Integer.parseInt((rs.getString("Stock")));
                return (stock > 0);
            }
        } catch (SQLException e) { 
            e.printStackTrace(System.out);
        }
        
        return false;
    }
    
    
    public void reduzStockObrigatorio(int id) throws SQLException {
        Statement st;
        st = con.createStatement(); 

        try {
            ResultSet rs = st.executeQuery("SELECT Stock FROM obrigatório WHERE ID = " + id + ";");
            int stock = Integer.parseInt(rs.getString("Stock"));
            stock--;
            
            st.executeUpdate("UPDATE obrigatório SET Stock = " + stock +"WHERE ID =" + id + ";");
        } catch (SQLException e) { 
            e.printStackTrace(System.out);
        }
    }
    
    
    public void reduzStockOpcional (int id) throws SQLException {
        Statement st;
        st = con.createStatement(); 

        try {
            ResultSet rs = st.executeQuery("SELECT Stock FROM opcional WHERE ID = " + id + ";");
            int stock = Integer.parseInt(rs.getString("Stock"));
            stock--;
            
            st.executeUpdate("UPDATE opcional SET Stock = " + stock +"WHERE ID =" + id + ";");
        } catch (SQLException e) { 
            e.printStackTrace(System.out);
        }
    }
    
    
    public float getPrecoComponenteOpcionalMaisBarato() throws SQLException {
        Statement st;
        st = con.createStatement(); 
        float preco = 0;
        
        try {
            ResultSet rs = st.executeQuery("SELECT MIN(preco) FROM opcional;");
            preco = Float.parseFloat(rs.getString("Preco"));
        } catch (SQLException e) { 
            e.printStackTrace(System.out);
        }
        
        return preco;
    }
    
    
    public List<Obrigatorio> getComponentesObrigatorios() throws SQLException {
        Statement st;
        st = con.createStatement();
        List<Obrigatorio> res = new ArrayList<>();
        
        try {
            ResultSet rs = st.executeQuery("Select * FROM obrigatório;");
            
            while(rs.next()) {
                int id = Integer.parseInt(rs.getString("ID"));
                float preco = Float.parseFloat(rs.getString("preco"));
                String designacao = rs.getString("Designacao");
                int stock = Integer.parseInt(rs.getString("Stock"));
                String categoria = rs.getString("Categoria");
                
                res.add(new Obrigatorio(id, preco, designacao, stock, categoria));
            }
        } catch (SQLException e) { 
            e.printStackTrace(System.out);
        }
        
        return res;
    }
    
    // verificar se vou buscar os necessitados certos ---------------------------------------------------------------------------------------------------------------------
    public List<Opcional> getComponentesOpcionais() throws SQLException {
        Statement st;
        st = con.createStatement();
        List<Opcional> res = new ArrayList<>();
        
        try {
            ResultSet rs = st.executeQuery("SELECT * FROM opcional WHERE Pacote_ID  IS NULL;");
            
            while(rs.next()) {
                int id = Integer.parseInt(rs.getString("ID"));              
                float preco = Float.parseFloat(rs.getString("preco"));
                String designacao = rs.getString("Designacao");
                int stock = Integer.parseInt(rs.getString("Stock"));
                String categoria = rs.getString("Categoria");
                String pertence = rs.getString("Pacote_ID");
                int pertencePacote;
                if (pertence != null) {
                    pertencePacote = Integer.parseInt(rs.getString("Pacote_ID"));
                }
                else pertencePacote = 0;

                Statement stn = con.createStatement();
                List<Integer> necessarios = new ArrayList();
                ResultSet rsNec = stn.executeQuery("SELECT Necessitado FROM ComponenteNecessitaComponente WHERE Necessita = " + id +";");
                while(rsNec.next()) {
                    necessarios.add(Integer.parseInt(rsNec.getString("Necessitado")));
                }
                Statement sti = con.createStatement();
                List<Integer> incompativeis = new ArrayList();
                ResultSet rsInc = sti.executeQuery("SELECT Opcional_ID1 FROM ComponenteIncompatívelComponente WHERE Opcional_ID = " + id +";");
                    while(rsInc.next()) {
                        incompativeis.add(Integer.parseInt(rsInc.getString("Opcional_ID1")));
                    }

                
                res.add(new Opcional(necessarios, incompativeis, pertencePacote, id, preco, designacao, stock, categoria));
            }
        } catch (SQLException e) { 
            e.printStackTrace(System.out);
        } 
        
        return res;
    }
    
    public List<Opcional> getComponentesPacote() throws SQLException {
        Statement st;
        st = con.createStatement();
        List<Opcional> res = new ArrayList<>();
        
        try {
            ResultSet rs = st.executeQuery("SELECT * FROM opcional WHERE Pacote_ID IS NOT NULL;");
            
            while(rs.next()) {
                int id = Integer.parseInt(rs.getString("ID"));              
                float preco = Float.parseFloat(rs.getString("preco"));
                String designacao = rs.getString("Designacao");
                int stock = Integer.parseInt(rs.getString("Stock"));
                String categoria = rs.getString("Categoria");
                String pertence = rs.getString("Pacote_ID");
                int pertencePacote;
                if (pertence != null) {
                    pertencePacote = Integer.parseInt(rs.getString("Pacote_ID"));
                }
                else pertencePacote = 0;

                Statement stn = con.createStatement();
                List<Integer> necessarios = new ArrayList();
                ResultSet rsNec = stn.executeQuery("SELECT Necessitado FROM ComponenteNecessitaComponente WHERE Necessita = " + id +";");
                while(rsNec.next()) {
                    necessarios.add(Integer.parseInt(rsNec.getString("Necessitado")));
                }
                Statement sti = con.createStatement();
                List<Integer> incompativeis = new ArrayList();
                ResultSet rsInc = sti.executeQuery("SELECT Opcional_ID1 FROM ComponenteIncompatívelComponente WHERE Opcional_ID = " + id +";");
                    while(rsInc.next()) {
                        incompativeis.add(Integer.parseInt(rsInc.getString("Opcional_ID1")));
                    }

                
                res.add(new Opcional(necessarios, incompativeis, pertencePacote, id, preco, designacao, stock, categoria));
            }
        } catch (SQLException e) { 
            e.printStackTrace(System.out);
        } 
        
        return res;
    }
    public List<Componente> getComponentes() throws SQLException {
        List<Componente> res = new ArrayList<>();
        
        for(Obrigatorio ob : getComponentesObrigatorios()){
            res.add(ob);
        }
        for(Opcional op : getComponentesOpcionais()){
            res.add(op);
        }
        for(Opcional op : getComponentesPacote()){
            res.add(op);
        }
        
        
        return res;
    }
    
    public List<Pacote> getPacotes() throws SQLException {
        Statement st;
        st = con.createStatement();
        List<Pacote> res = new ArrayList<>();
        
        try {
            ResultSet rs = st.executeQuery("Select * FROM Pacote;");
            
            while(rs.next()) {
                int id = Integer.parseInt(rs.getString("ID"));
                float preco = Float.parseFloat(rs.getString("preco"));
                String categoria = rs.getString("Categoria");
                String designacao = rs.getString("Designação");
                
                Statement stp = con.createStatement();
                List<Integer> componentesPacote = new ArrayList();
                ResultSet rsPac = stp.executeQuery("SELECT ID FROM Opcional WHERE Pacote_ID = " + id +";");
                while(rsPac.next()) {
                    componentesPacote.add(Integer.parseInt(rsPac.getString("ID")));
                }
                
                Statement stn = con.createStatement();
                List<Integer> componentesNecessarios = new ArrayList();
                ResultSet rsNec = stn.executeQuery("SELECT Opcional_ID FROM PacoteNecessitaComponente WHERE Pacote_ID = " + id +";");
                while(rsNec.next()) {
                    componentesNecessarios.add(Integer.parseInt(rsNec.getString("Opcional_ID")));
                }
                
                Statement sti = con.createStatement();
                List<Integer> componentesIncompativeis = new ArrayList();
                ResultSet rsInc = sti.executeQuery("SELECT Opcional_ID FROM PacoteIncompatívelComponente WHERE Pacote_ID = " + id +";");
                while(rsInc.next()) {
                    componentesIncompativeis.add(Integer.parseInt(rsInc.getString("Opcional_ID")));
                }
                Statement stpi = con.createStatement();
                List<Integer> pacotesIncompativeis = new ArrayList();
                ResultSet rsPctsInc = stpi.executeQuery("SELECT Pacote_ID FROM PacoteIncompatívelPacote WHERE Pacote_ID1 = " + id +";");
                while(rsPctsInc.next()) {
                    componentesIncompativeis.add(Integer.parseInt(rsPctsInc.getString("Pacote_ID")));
                }

                
                res.add(new Pacote(id, categoria, designacao, componentesPacote, pacotesIncompativeis, componentesIncompativeis, componentesNecessarios));
            }
        } catch (SQLException e) { 
            e.printStackTrace(System.out);
        }
        
        return res;
    }
    
    
    public float getPrecoPacoteMaisBarato() throws SQLException {
        Statement st;
        st = con.createStatement(); 
        float preco = 0;
        
        try {
            ResultSet rs = st.executeQuery("SELECT MIN(preco) FROM Pacote;");
            preco = Float.parseFloat(rs.getString("Preco"));
        } catch (SQLException e) { 
            e.printStackTrace(System.out);
        }
        
        return preco;
    }
}
