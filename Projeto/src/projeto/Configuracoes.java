/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto;

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

    public Configuracoes(Map<Integer, Cliente> clientes, List<Configuracao> configuracoes) {
        this.clientes = clientes;
        this.configuracoes = configuracoes;
    }
    
}
