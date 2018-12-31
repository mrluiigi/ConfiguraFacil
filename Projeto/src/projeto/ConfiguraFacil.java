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
    private Configuracao configuracao;
    
    public ConfiguraFacil(Connection con) {
        this.configuracoesDAO = new ConfiguraçõesDAO(con);
        this.componentesDAO = new ComponentesDAO(con);
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
    
    public List<Pacote> getPacotesConfiguracao() {
        List<Pacote> res = new ArrayList<>();
        try {
            for(int i : this.configuracao.getPacotes()) {
                res.add(componentesDAO.getPacote(i));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConfiguraFacil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
    

    public int getStockComponente(int id, boolean obrigatorio) throws SQLException{
        if(obrigatorio){
            List<Obrigatorio> comp = getObrigatorios();
            for(Obrigatorio c : comp){
                if(c.getId() == id) return c.getStock();
            }
        }
        else{
            List<Opcional> comp = getOpcionais();
            for(Opcional c : comp){
                if(c.getId() == id) return c.getStock();
            }
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
    
    public List<Pacote> alteracoesComponenteOpcionalPacotesIncompativeis(int id) throws SQLException{
        List<Pacote> pacotesIncompativeis = new ArrayList<>();
        
        for(int i : this.configuracao.getPacotes()){
            Pacote p = this.componentesDAO.getPacote(i);
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
    
    public List<Opcional> alteracoesPacoteCompNecessarios(int id) throws SQLException{
        Pacote p = this.componentesDAO.getPacote(id);
        List<Integer> nec = p.getListaComponentesNecessarios();
        List<Opcional> res = new ArrayList<>();
        
        for(int i : nec){
            if(!(this.configuracao.containsOpcional(i))){
                res.add(this.componentesDAO.getOpcional(i));
            }
        }
        
        return res;
    }
        
    public List<Opcional> alteracoesPacoteCompIncompativeis(int id) throws SQLException{
        Pacote p = this.componentesDAO.getPacote(id);
        List<Integer> inc = p.getListaComponentesIncompativeis();
        List<Opcional> res = new ArrayList<>();

        for(int i : inc){
            if(this.configuracao.containsOpcional(i)){
                res.add(this.componentesDAO.getOpcional(i));
            }
        }
        
        return res;
    }
    
    public List<Pacote> alteracoesPacotePacotesIncompativeis(int id) throws SQLException{
        Pacote p = this.componentesDAO.getPacote(id);
        List<Integer> pInc = p.getListaPacotesIncompativeis();
        List<Pacote> res = new ArrayList<>();

        for(int i : pInc){
            if(this.configuracao.containsPacote(i)){
                res.add(this.componentesDAO.getPacote(i));
            }
        }
        
        return res;
    }
    
    public void adicionaPacote(int id) throws SQLException{
        Pacote pacote = this.componentesDAO.getPacote(id);
        float preco = pacote.getPreco();

        
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
                preco = this.componentesDAO.getPacote(i).getPreco();
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
        else if(!obrigatorio){
            this.componentesDAO.adicionaStockOpcional(id, qtd);
        }
        this.setChanged();
        this.notifyObservers();
    }
    
    public boolean temStockPacote(int id) throws SQLException{
        Pacote p = this.componentesDAO.getPacote(id);
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
                    for(int j : this.componentesDAO.getPacote(i).getListaComponentesPacote()){
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
    public List<Componente> getListaComponentesOpcionaisConfiguracao() throws SQLException{
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
            for(int j : this.componentesDAO.getPacote(i).getListaComponentesPacote()){
                res.add(this.componentesDAO.getOpcional(j));
            }
        }
        
        return res;
    }

    public float escolhePacotesOtimos(float orcamento) throws SQLException{
        List<Pacote> ordenadoPreco = new ArrayList<>();
        float precoMaisBarato = this.componentesDAO.getPrecoPacoteMaisBarato();
        
        for(Pacote p : this.componentesDAO.getPacotes()){
            ordenadoPreco.add(p);
        }
        ordenadoPreco.sort(new PacotePrecoComparator());
        
        List<Opcional> listaComponentesNecessariosParaPacote;
        for(Pacote p : ordenadoPreco){
            if(orcamento < precoMaisBarato){
                break;
            }
            
            if((p.getPreco() <= orcamento) && (!(ocorremIncompatibilidadesPacote(p)))){
                listaComponentesNecessariosParaPacote = alteracoesPacoteCompNecessarios(p.getId());
                float valor = p.getPreco();
                for(Opcional i : listaComponentesNecessariosParaPacote){
                    valor += i.getPreco();
                }
                if((valor <= orcamento && !(this.configuracao.getPacotes().contains(p.getId())))){
                    this.configuracao.adicionaPacote(p.getId(), p.getPreco());
                    for(Opcional i : listaComponentesNecessariosParaPacote){
                        this.configuracao.adicionaComponenteOpcional(i.getId(), i.getPreco());
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
                float valor = o.getPreco();
                listaComponentesNecessarios = alteracoesComponenteOpcionalNecessarios(o.getId());
                for(Opcional n : listaComponentesNecessarios){
                    valor += n.getPreco();
                }
                if((valor <= orcamento) && !(this.configuracao.getComponentesOpcionais().contains(o.getId()))){
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
        float preco = escolhePacotesOtimos(orcamento);
        escolheComponentesOtimos(preco);
        
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
    
    public void escolheModelo(int idModelo, String modelo) throws SQLException{
        float preco = this.componentesDAO.getObrigatorio(idModelo).getPreco();
        this.configuracao.alteraModelo(idModelo, modelo, preco);
        
        
        this.setChanged();
        this.notifyObservers();
    }
    
    public List<Opcional> alteracoesRemoverComponenteOpcionalComponentes(int id) throws SQLException{
        List<Integer> componentesOpcionais = this.configuracao.getComponentesOpcionais();
        List<Opcional> aRemoverComponentes = new ArrayList<>();
        
        for(int i : componentesOpcionais){
            Opcional o = this.componentesDAO.getOpcional(i);
            boolean bool = o.getListaNecessarios().contains(id);
            if(bool){
                aRemoverComponentes.add(o);
            }
        }
        
        return aRemoverComponentes;
    }    
    
    public List<Pacote> alteracoesRemoverComponenteOpcionalPacotes(int id) throws SQLException{    
        List<Integer> pacotes = this.configuracao.getPacotes();
        List<Pacote> aRemoverPacotes = new ArrayList<>();
        
        for(int i : pacotes){
            Pacote pi = this.componentesDAO.getPacote(i);
            boolean bool = pi.getListaComponentesNecessarios().contains(id);
            if(bool){
                aRemoverPacotes.add(pi);
            }
        }
        
        return aRemoverPacotes;
    }
    
    public void removerComponenteOpcional(int id) throws SQLException{
        for(Opcional o : alteracoesRemoverComponenteOpcionalComponentes(id)){
            float preco = o.getPreco();
            this.configuracao.removeComponenteOpcional(o.getId(), preco);
        }
        
        for(Pacote i : alteracoesRemoverComponenteOpcionalPacotes(id)){
            float preco = i.getPreco();
            this.configuracao.removePacote(i.getId(), preco);
        }
        float preco = this.componentesDAO.getOpcional(id).getPreco();
        this.configuracao.removeComponenteOpcional(id, preco);
        
        this.setChanged();
        this.notifyObservers();
    }
    
    public float getPreco(){
        return this.configuracao.getPreco();
    }
    
    public void guardarConfiguracao() throws SQLException{
        this.configuracoesDAO.addConfiguracao(this.configuracao);
    }
    
    
    public List<Opcional> getComponentesPacote(int id) throws SQLException {
        return this.componentesDAO.getComponentesPacote(id);
    }
    
    public void removePacote(int id) throws SQLException {
        float preco = this.componentesDAO.getPacote(id).getPreco();
                System.out.println("preco" + preco);

        this.configuracao.removePacote(id, preco);
        this.setChanged();
        this.notifyObservers();
    }
    
    public List<Pacote> getListaPacotesCategoriaNaConfiguracao(String categoria) throws SQLException {
        List<Pacote> res = new ArrayList<>();
        List<Integer> pacotes = new ArrayList<>();

        pacotes = this.configuracao.getPacotes();
        for(int i : pacotes){
            if(componentesDAO.getPacote(i).getCategoria().equals(categoria)){
                res.add(componentesDAO.getPacote(i));
            }
        }

        return res;
    }
    
    public void resetConfiguracao() {
        this.configuracao = new Configuracao();
    }
}
