/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Utilizador
 */
public class Configuracoes {
    /** Map de todos os clientes */
    private Map<Integer, Cliente> clientes;
    /** Lista de todas as configurações*/ 
    private List<Configuracao> configuracoes;

    public Configuracoes(){
        this.clientes = new HashMap<>();
        this.configuracoes = new ArrayList<>();
        List<Integer> aux = new ArrayList<>();
        aux.add(1);
        this.configuracoes.add(new Configuracao(1, "camaro", 50, aux, new ArrayList<>(), new ArrayList<>(), true));
    }
    
    public Configuracoes(Map<Integer, Cliente> clientes, List<Configuracao> configuracoes) {
        this.clientes = clientes;
        this.configuracoes = configuracoes;
    }

    public List<Configuracao> getConfiguracoes() {
        return configuracoes;
    }
    
    public void addConfiguracao(Configuracao c){
        this.configuracoes.add(c);
    }
    
    public Configuracao getConfiguracao(int id){
        for(Configuracao c : configuracoes){
            if(c.getId() == id) return c;
        }
        return null;
    }
}
