/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto;

import java.util.List;

/**
 *
 * @author Utilizador
 */
public class Cliente {
    private int id;
    private String nome;
    private List<Configuracao> configuracoes;

    public Cliente(int id, String nome, List<Configuracao> configuracoes) {
        this.id = id;
        this.nome = nome;
        this.configuracoes = configuracoes;
    }
    
   
}