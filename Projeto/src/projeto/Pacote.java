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
public class Pacote {
    private int id;
    private String categoria;
    private float preco;
    private List<Integer> componentesPacote;
    private List<Integer> pacotesIncompativeis;
    private List<Integer> componentesIncompativeis;
    private List<Integer> componentesNecessarios;

    public Pacote(int id, String categoria, List<Integer> componentesPacote, List<Integer> pacotesIncompativeis, List<Integer> componentesIncompativeis, List<Integer> componentesNecessarios) {
        this.id = id;
        this.categoria = categoria;
        this.componentesPacote = componentesPacote;
        this.pacotesIncompativeis = pacotesIncompativeis;
        this.componentesIncompativeis = componentesIncompativeis;
        this.componentesNecessarios = componentesNecessarios;
    }

    public int getId() {
        return id;
    }

    public String getCategoria() {
        return categoria;
    }
    
    public List<Integer> getListaComponentesPacote(){
        return this.componentesPacote;
    }
    
    public List<Integer> getListaComponentesNecessarios(){
        return this.componentesNecessarios;
    }
    
    public List<Integer> getListaComponentesIncompativeis(){
        return this.componentesIncompativeis;
    }
    
    public List<Integer> getListaPacotesIncompativeis(){
        return this.pacotesIncompativeis;
    }    
}
