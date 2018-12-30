/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Utilizador
 */
public class ConfiguraFacil extends Observable{
    private Connection con;
    private ComponentesDAO componentesDAO;
    private ConfiguraçõesDAO configuracoesDAO;
    private Pacotes pacotes;
    private Configuracao configuracao;
    
    public ConfiguraFacil(Connection con) {
        this.configuracoesDAO = new ConfiguraçõesDAO(con);
        this.componentesDAO = new ComponentesDAO(con);
        this.pacotes = new Pacotes();
        this.configuracao = new Configuracao();
        
        this.setChanged();
        this.notifyObservers();
    }
    
    public Configuracao getConfiguracao() {
        return configuracao;
    }
    
    public List<Opcional> getComponentesCategoria(String categoria) throws SQLException{
        List<Opcional> res = new ArrayList<>();
        for(Integer i : this.configuracao.getComponentesOpcionais()){
            Opcional o = this.componentesDAO.getOpcional(i);
            if(o.getCategoria().equals(categoria)){
                res.add(o);
            }
        }
        return res;
    }
    
    public List<Obrigatorio> getObrigatorios() {
        try {
            return this.componentesDAO.getComponentesObrigatorios();
            // return this.componentes.getObrigatorios();
        } catch (SQLException ex) {
            Logger.getLogger(ConfiguraFacil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public List<Opcional> getOpcionais() {
        try {
            return this.componentesDAO.getComponentesOpcionais();
        } catch (SQLException ex) {
            Logger.getLogger(ConfiguraFacil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public List<Componente> getComponentes() throws SQLException {
        return componentesDAO.getComponentes();
    }
    
    public List<Pacote> getPacotes() {
        try {
            return componentesDAO.getPacotes();
        } catch (SQLException ex) {
            Logger.getLogger(ConfiguraFacil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public int getStockComponente(int id) throws SQLException{
        List<Componente> comp = getComponentes();
        for(Componente c : comp){
            if(c.getId() == id) return c.getStock();
        }
        return -1;
    }
    
    
    public void adicionaComponenteObrigatorio(int id) throws SQLException{
        Obrigatorio obrigatorio;
        obrigatorio = this.componentesDAO.getObrigatorio(id);
        this.configuracao.adicionaComponenteObrigatorio(id, obrigatorio.getPreco());
        
        this.setChanged();
        this.notifyObservers();
    }
    
    public void removeComponenteObrigatorio(){
        this.configuracao.removeComponentesObrigatorios();
        
        this.setChanged();
        this.notifyObservers();
    }
    
    public List<Opcional> alteracoesComponenteOpcionalNecessarios(int id) throws SQLException{
        Opcional comp = this.componentesDAO.getOpcional(id);
        List<Opcional> necessarios = new ArrayList<>();
        
        for(int i : comp.getListaNecessarios()){
            if(!(this.configuracao.containsOpcional(i))){
                Opcional nec = this.componentesDAO.getOpcional(i);
                necessarios.add(nec);
            }
        }
        
        return necessarios;
    }
    
    public List<Opcional> alteracoesComponenteOpcionalIncompativeis(int id) throws SQLException{
        Opcional comp = this.componentesDAO.getOpcional(id);
        List<Opcional> incompativeis = new ArrayList<>();
        
        for(int i : comp.getListaIncompativeis()){
            if(this.configuracao.containsOpcional(i)){
                Opcional inc = this.componentesDAO.getOpcional(i);
                incompativeis.add(inc);
            }
        }
        
        return incompativeis;
    }
    
    public List<Pacote> alteracoesComponenteOpcionalPacotesIncompativeis(int id){
        List<Pacote> pacotesIncompativeis = new ArrayList<>();
        
        for(int i : this.configuracao.getPacotes()){
            Pacote p = this.pacotes.getPacote(i);
            if(p.componenteIncompativel(id)){
                pacotesIncompativeis.add(p);
            }
        }
        
        return pacotesIncompativeis;
    }
    
    // percorrer lista dos pacotes da configuracao, para cada pacote tenho que verificar se o
    // componente que eu quero adicionar esta la (na lista dos incompativeis)
    public void adicionaComponenteOpcional(int id) throws SQLException{
        Opcional comp = this.componentesDAO.getOpcional(id);
        float preco;
        
        for(int i : comp.getListaNecessarios()){
            if(!(this.configuracao.containsOpcional(i))){
                preco = this.componentesDAO.getOpcional(i).getPreco();
                this.configuracao.adicionaComponenteOpcional(i, preco);
            }
        }
        
        for(int i : comp.getListaIncompativeis()){
            if(this.configuracao.containsOpcional(i)){
                preco = this.componentesDAO.getOpcional(i).getPreco();
                this.configuracao.removeComponenteOpcional(i, preco);
            }
        }
        
        List<Pacote> pacotesIncompativeis = alteracoesComponenteOpcionalPacotesIncompativeis(id);
        for(Pacote p : pacotesIncompativeis){
            this.configuracao.removePacote(p.getId(), p.getPreco());
        }
        
        this.configuracao.adicionaComponenteOpcional(id, comp.getPreco());
        
        this.setChanged();
        this.notifyObservers();
    }
    
    public List<Integer> alteracoesPacoteCompNecessarios(int id){
        Pacote p = this.pacotes.getPacote(id);
        List<Integer> nec = p.getListaComponentesNecessarios();
        List<Integer> res = new ArrayList<>();
        
        for(int i : nec){
            if(!(this.configuracao.containsOpcional(i))){
                res.add(i);
            }
        }
        
        return res;
    }
        
    public List<Integer> alteracoesPacoteCompIncompativeis(int id){
        Pacote p = this.pacotes.getPacote(id);
        List<Integer> inc = p.getListaComponentesIncompativeis();
        List<Integer> res = new ArrayList<>();

        for(int i : inc){
            if(this.configuracao.containsOpcional(i)){
                res.add(i);
            }
        }
        
        return res;
    }
    
    public List<Integer> alteracoesPacotePacotesIncompativeis(int id){
        Pacote p = this.pacotes.getPacote(id);
        List<Integer> pInc = p.getListaPacotesIncompativeis();
        List<Integer> res = new ArrayList<>();

        for(int i : pInc){
            if(this.configuracao.containsPacote(i)){
                res.add(i);
            }
        }
        
        return res;
    }
    
    public void adicionaPacote(int id) throws SQLException{
        Pacote pacote = this.pacotes.getPacote(id);
        float preco;
        
        for(int i : pacote.getListaComponentesNecessarios()){
            if(!(this.configuracao.containsOpcional(i))){
                preco = this.componentesDAO.getOpcional(i).getPreco();
                this.configuracao.adicionaComponenteOpcional(i, preco);
            }
        }
        
        for(int i : pacote.getListaComponentesIncompativeis()){
            if(this.configuracao.containsOpcional(i)){
                preco = this.componentesDAO.getOpcional(i).getPreco();
                this.configuracao.removeComponenteOpcional(i, preco);
            }
        }
            
        for(int i : pacote.getListaPacotesIncompativeis()){
            if(this.configuracao.containsPacote(i)){
                preco = this.pacotes.getPacote(i).getPreco();
                this.configuracao.removePacote(i, preco);
            }
        }
        
        this.configuracao.adicionaPacote(id, pacote.getPreco());
        this.setChanged();
        this.notifyObservers();
    }
    
    public void adicionarStock(boolean obrigatorio, int id, int qtd) throws SQLException{
        if(obrigatorio){    
            this.componentesDAO.adicionaStockObrigatorio(id, qtd);                 
        }
        else{
            this.componentesDAO.adicionaStockOpcional(id, qtd);
        }
        this.setChanged();
        this.notifyObservers();
    }
    
    public boolean temStockPacote(int id) throws SQLException{
        Pacote p = this.pacotes.getPacote(id);
        boolean res = false;
        
        for(int i : p.getListaComponentesPacote()){
                res = this.componentesDAO.temStock(i, false);
                if(!res){
                    return res;
                }
        }
        
        return res;
    }
    
    public Configuracao obterProximaConfiguracao() throws SQLException{
        List<Configuracao> configs = this.configuracoesDAO.getConfiguracoesPorFabricar();
        boolean bool = false;
        
        for(Configuracao c : configs){
            for(int i : c.getComponentesObrigatorios()){
                bool = this.componentesDAO.temStock(i, true);
                if(!bool){
                    break;
                }
            }
            
            if(bool){
                for(int i : c.getComponentesOpcionais()){
                    bool = this.componentesDAO.temStock(i, false);
                    if(!bool){
                        break;
                    }
                }
            }
            
            if(bool){
                for(int p : c.getPacotes()){
                    bool = temStockPacote(p);
                    if(!bool){
                        break;
                    }
                }
            }
            
            if(bool){
                for(int i : c.getComponentesObrigatorios()){
                    this.componentesDAO.reduzStockObrigatorio(i);
                }
                for(int i : c.getComponentesOpcionais()){
                    this.componentesDAO.reduzStockOpcional(i);
                }
                for(int i : c.getPacotes()){
                    for(int j : this.pacotes.getPacote(i).getListaComponentesPacote()){
                        this.componentesDAO.reduzStockOpcional(j);
                    }
                }
                this.configuracoesDAO.marcaComoPronta(c.getId());
                return c;
            }
        }
        
        return null;        
    }
    
    
    public List<Componente> getListaComponentesObrigatórios() throws SQLException{
        List<Componente> res = new ArrayList<>(); 
        for(int i : configuracao.getComponentesObrigatorios()){
            res.add(this.componentesDAO.getObrigatorio(i));
        }
      /*  for(int i : configuracao.getPacotes()){
            for(int j : this.pacotes.getPacote(i).getListaComponentesPacote()){
                res.add(this.componentesDAO.getOpcional(j));
            }
        }*/
        
        return res;
    }
    public List<Componente> getListaComponentesOpcionais() throws SQLException{
        List<Componente> res = new ArrayList<>(); 
        for(int i : configuracao.getComponentesOpcionais()){
            res.add(this.componentesDAO.getOpcional(i));
        }        
        return res;
    }
    
    public List<Componente> getListaComponentes(int id) throws SQLException{
        List<Componente> res = new ArrayList<>();
        Configuracao c = this.configuracoesDAO.getConfiguracaoPorID(id);
        
        for(int i : c.getComponentesObrigatorios()){
            res.add(this.componentesDAO.getObrigatorio(i));
        }
        for(int i : c.getComponentesOpcionais()){
            res.add(this.componentesDAO.getOpcional(i));
        }
        for(int i : c.getPacotes()){
            for(int j : this.pacotes.getPacote(i).getListaComponentesPacote()){
                res.add(this.componentesDAO.getOpcional(j));
            }
        }
        
        return res;
    }

    public float escolhePacotesOtimos(float orcamento) throws SQLException{
        List<Pacote> ordenadoPreco = new ArrayList<>();
        float precoMaisBarato = this.pacotes.getPrecoPacoteMaisBarato();
        
        for(Pacote p : this.pacotes.getPacotes()){
            ordenadoPreco.add(p);
        }
        ordenadoPreco.sort(new PacotePrecoComparator());
        
        List<Integer> listaComponentesNecessariosParaPacote;
        for(Pacote p : ordenadoPreco){
            if(orcamento < precoMaisBarato){
                break;
            }
            
            if((p.getPreco() <= orcamento) && (!(ocorremIncompatibilidadesPacote(p)))){
                listaComponentesNecessariosParaPacote = alteracoesPacoteCompNecessarios(p.getId());
                float valor = p.getPreco();
                for(int i : listaComponentesNecessariosParaPacote){
                    valor += this.componentesDAO.getOpcional(i).getPreco();
                }
                if((valor <= orcamento)){
                    this.configuracao.adicionaPacote(p.getId(), p.getPreco());
                    for(int i : listaComponentesNecessariosParaPacote){
                        this.configuracao.adicionaComponenteOpcional(this.componentesDAO.getOpcional(i).getId(), this.componentesDAO.getOpcional(i).getPreco());
                    }
                    orcamento -= valor;
                }
            }
        }
        
        return orcamento;
    }
    
    public float escolheComponentesOtimos(float orcamento) throws SQLException{
        List<Opcional> ordenadoPreco = new ArrayList<>();
        float precoMaisBarato = this.componentesDAO.getPrecoComponenteOpcionalMaisBarato();
        
        for(Opcional o : this.componentesDAO.getComponentesOpcionais()){
            ordenadoPreco.add(o);
        }
        ordenadoPreco.sort(new ComponentePrecoComparator());
        
        List<Opcional> listaComponentesNecessarios;
        for(Opcional o : ordenadoPreco){
            if(orcamento < precoMaisBarato){
                break;
            }
            
            if((o.getPreco() <= orcamento) && (!(ocorremIncompatibilidadesComponentes(o)))){
                listaComponentesNecessarios = alteracoesComponenteOpcionalNecessarios(o.getId());
                float valor = o.getPreco();
                for(Opcional n : listaComponentesNecessarios){
                    valor += n.getPreco();
                }
                if((valor <= orcamento)){
                    this.configuracao.adicionaComponenteOpcional(o.getId(), o.getPreco());
                    for(Opcional n : listaComponentesNecessarios){
                        this.configuracao.adicionaComponenteOpcional(n.getId(), n.getPreco());
                    }
                    orcamento -= valor;
                }
            }
        }
        
        return orcamento;
    }
    
    public Configuracao configuracaoOptima(float orcamento) throws SQLException{
        escolhePacotesOtimos(orcamento);
        escolheComponentesOtimos(orcamento);
        
        return this.configuracao;
    }
    
    public boolean ocorremIncompatibilidadesPacote(Pacote p){
        for(int i : p.getListaComponentesIncompativeis()){
            if(this.configuracao.getComponentesOpcionais().contains(i)){
                return true;
            }
        }
        for(int i : p.getListaPacotesIncompativeis()){
            if(this.configuracao.getPacotes().contains(i)){
                return true;
            }
        }
        return false;
    }
    
    public boolean ocorremIncompatibilidadesComponentes(Opcional o){
        for(int i : o.getListaIncompativeis()){
            if(this.configuracao.getComponentesOpcionais().contains(i)){
                return true;
            }
        }
        for(int i : o.getListaIncompativeis()){
            if(this.configuracao.getPacotes().contains(i)){
                return true;
            }
        }
        return false;
    }
    
    public void escolheModelo(String modelo){
        this.configuracao.alteraModelo(modelo);
        
        this.setChanged();
        this.notifyObservers();
    }
    
    public List<Integer> alteracoesRemoverComponenteOpcionalComponentes(int id) throws SQLException{
        List<Integer> componentesOpcionais = this.configuracao.getComponentesOpcionais();
        List<Integer> aRemoverComponentes = new ArrayList<>();
        
        for(int i : componentesOpcionais){
            boolean bool = this.componentesDAO.getOpcional(i).getListaNecessarios().contains(id);
            if(bool){
                aRemoverComponentes.add(i);
            }
        }
        
        return aRemoverComponentes;
    }    
    
    public List<Integer> alteracoesRemoverComponenteOpcionalPacotes(int id){    
        List<Integer> pacotes = this.configuracao.getPacotes();
        List<Integer> aRemoverPacotes = new ArrayList<>();
        
        for(int i : pacotes){
            boolean bool = this.pacotes.getPacote(i).getListaComponentesNecessarios().contains(id);
            if(bool){
                aRemoverPacotes.add(i);
            }
        }
        
        return aRemoverPacotes;
    }
    
    public void removerComponenteOpcional(int id) throws SQLException{
        for(int i : alteracoesRemoverComponenteOpcionalComponentes(id)){
            float preco = this.componentesDAO.getOpcional(i).getPreco();
            this.configuracao.removeComponenteOpcional(i, preco);
        }
        
        for(int i : alteracoesRemoverComponenteOpcionalPacotes(id)){
            float preco = this.pacotes.getPacote(i).getPreco();
            this.configuracao.removePacote(i, preco);
        }
        
        this.setChanged();
        this.notifyObservers();
    }
    
    public float getPreco(){
        return this.configuracao.getPreco();
    }
    
    public void guardarConfiguracao() throws SQLException{
        this.configuracoesDAO.addConfiguracao(this.configuracao);
    }
}
