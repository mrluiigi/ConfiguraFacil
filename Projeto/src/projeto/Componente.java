/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto;

/**
 *
 * @author Utilizador
 */
public class Componente {
    private int id;
    private float preco;
    private String designacao;
    private int stock;
    private String categoria;

    public Componente(int id, float preco, String designacao, int stock, String categoria) {
        this.id = id;
        this.preco = preco;
        this.designacao = designacao;
        this.stock = stock;
        this.categoria = categoria;
    }

    public float getPreco() {
        return preco;
    }
    
    public void addStock(int quantidade){
        this.stock += quantidade;
    }

    public int getId() {
        return id;
    }

    public String getDesignacao() {
        return designacao;
    }

    public int getStock() {
        return stock;
    }

    public String getCategoria() {
        return categoria;
    }
    
    public void decrementaStock(){
        this.stock--;
    }
    
    
}
