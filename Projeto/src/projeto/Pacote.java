/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto;

import java.util.List;



/**
 *
 * @author José Pinto A81317
 * @author Luís Correia A81141
 * @author Pedro Barbosa A82068
 */
public class Pacote {
    private int id;
    private String categoria;
    private float preco;
    private String designacao;
    private List<Integer> componentesPacote;
    private List<Integer> pacotesIncompativeis;
    private List<Integer> componentesIncompativeis;
    private List<Integer> componentesNecessarios;

    public Pacote(int id, String categoria, float preco, String designacao, List<Integer> componentesPacote, List<Integer> pacotesIncompativeis, List<Integer> componentesIncompativeis, List<Integer> componentesNecessarios) {
        this.id = id;
        this.categoria = categoria;
        this.preco = preco;
        this.componentesPacote = componentesPacote;
        this.designacao = designacao;
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
    
    public float getPreco(){
                        System.out.println("Pacote " +preco);

        return this.preco;
    }

    public String getDesignacao() {
        return designacao;
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

    public boolean componenteIncompativel(int id){
        return this.componentesIncompativeis.contains(id);
    }
    
    @Override
    public boolean equals(Object o){
        if(o==this) return true;
        if(o==null || o.getClass() != this.getClass()) return false;

        Pacote l = (Pacote) o;
        return this.id == l.getId();
    }
}
