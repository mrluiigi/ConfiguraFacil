/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Utilizador
 */
public class ConfiguraFacil{
    
    private Configuracoes configuracoes;
    private Pacotes pacotes;
    private Componentes componentes;
    private Configuracao configuracao;
    
    public void adicionaComponenteObrigatorio(int id){
        Obrigatorio obrigatorio;
        obrigatorio = this.componentes.getObrigatorio(id);
        this.configuracao.adicionaComponenteObrigatorio(id, obrigatorio.getPreco());
    }
    
    public List<Opcional> alteracoesComponenteOpcionalNecessarios(int id){
        Opcional comp = this.componentes.getOpcional(id);
        List<Opcional> necessarios = new ArrayList<>();
        
        for(int i : comp.getListaNecessarios()){
            if(!(this.configuracao.containsOpcional(i))){
                Opcional nec = this.componentes.getOpcional(i);
                necessarios.add(nec);
            }
        }
        
        return necessarios;
    }
    
    public List<Opcional> alteracoesComponenteOpcionalIncompativeis(int id){
        Opcional comp = this.componentes.getOpcional(id);
        List<Opcional> incompativeis = new ArrayList<>();
        
        for(int i : comp.getListaIncompativeis()){
            if(this.configuracao.containsOpcional(i)){
                Opcional inc = this.componentes.getOpcional(i);
                incompativeis.add(inc);
            }
        }
        
        return incompativeis;
    }
    
    public void adicionaComponenteOpcional(int id){
        Opcional comp = this.componentes.getOpcional(id);
        float preco;
        
        for(int i : comp.getListaNecessarios()){
            if(!(this.configuracao.containsOpcional(i))){
                preco = this.componentes.getOpcional(i).getPreco();
                this.configuracao.adicionaComponenteOpcional(i, preco);
            }
        }
        
        for(int i : comp.getListaIncompativeis()){
            if(this.configuracao.containsOpcional(i)){
                preco = this.componentes.getOpcional(i).getPreco();
                this.configuracao.removeComponenteOpcional(i, preco);
            }
        }
        
        this.configuracao.adicionaComponenteOpcional(id, comp.getPreco());
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
    
    public void adicionaPacote(int id){
        Pacote pacote = this.pacotes.getPacote(id);
        float preco;
        
        for(int i : pacote.getListaComponentesNecessarios()){
            if(!(this.configuracao.containsOpcional(i))){
                preco = this.componentes.getOpcional(i).getPreco();
                this.configuracao.adicionaComponenteOpcional(i, preco);
            }
        }
        
        for(int i : pacote.getListaComponentesIncompativeis()){
            if(this.configuracao.containsOpcional(i)){
                preco = this.componentes.getOpcional(i).getPreco();
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
    }
    
}
