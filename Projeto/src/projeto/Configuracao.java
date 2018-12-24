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
public class Configuracao {
    //Id da configuração
    private int id;
    private String modelo;
    private float preco;
    //Ids dos componentes;
    private List<Integer> componentes;
    //Ids dos pacotes
    private List<Integer> pacotes;
    //Indica se a configuração está pronta para ser feita (se tem todos os componentes)
    private boolean pronta;
    
    
    public Configuracao(int id, String modelo, float preco, List<Integer> componentes, List<Integer> pacotes, boolean pronta) {
        this.id = id;
        this.modelo = modelo;
        this.preco = preco;
        this.componentes = componentes;
        this.pacotes = pacotes;
        this.pronta = pronta;
    }
}
