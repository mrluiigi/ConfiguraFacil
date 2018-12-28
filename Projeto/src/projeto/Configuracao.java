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
 * @author José Pinto A81317
 * @author Luís Correia A81141
 * @author Pedro Barbosa A82068
 */
public class Configuracao {
    /**Id da configuração*/
    private int id;
    private String nif;
    /**Modelo da configuração */
    private String modelo;
    private float preco;
    //Ids dos componentes obrigatórios;
    private List<Integer> componentesObrigatorios;
    //Ids dos componentes opcionais;
    private List<Integer> componentesOpcionais;
    //Ids dos pacotes
    private List<Integer> pacotes;
    //Indica se a configuração está pronta para ser feita (se tem todos os componentes)
    private boolean pronta;

    public Configuracao(){
        this.id = 0;
        this.modelo = "";
        this.preco = 0;
        this.componentesObrigatorios = new ArrayList<>();
        this.componentesOpcionais = new ArrayList<>();
        this.pacotes = new ArrayList<>();
        this.pronta = false;
    }

    
    public Configuracao(int id, String modelo, float preco, List<Integer> componentesObrigatorios, List<Integer> componentesOpcionais, List<Integer> pacotes, boolean pronta) {
        this.id = id;
        this.modelo = modelo;
        this.preco = preco;
        this.componentesObrigatorios = componentesObrigatorios;
        this.componentesOpcionais = componentesOpcionais;
        this.pacotes = pacotes;
        this.pronta = pronta;
    }

    public int getId() {
        return id;
    }

    public String getNif() {
        return nif;
    }
    
    
    public String getModelo(){
        return this.modelo;
    }

    public float getPreco() {
        return preco;
    }
    
    
    public void adicionaComponenteObrigatorio(int id, float preco){
        this.preco += preco;
        this.componentesObrigatorios.add(id);
    }
    
    public void adicionaComponenteOpcional(int id, float preco){
        this.preco += preco;
        this.componentesOpcionais.add(id);
    }
    
    public void removeComponenteOpcional(int id, float preco){
        this.preco -= preco;
        this.componentesOpcionais.remove(id);
    }
    
    public void adicionaPacote(int id, float preco){
        this.preco += preco;
        this.pacotes.remove(id);
    }
    
    public void removePacote(int id, float preco){
        this.preco -= preco;
        this.pacotes.remove(id);
    }
    
    public boolean containsOpcional(int id){
        return this.componentesOpcionais.contains(id);
    }
    
    public boolean containsPacote(int id){
        return this.pacotes.contains(id);
    }

    public List<Integer> getComponentesObrigatorios() {
        return componentesObrigatorios;
    }

    public List<Integer> getComponentesOpcionais() {
        return componentesOpcionais;
    }

    public List<Integer> getPacotes() {
        return pacotes;
    }
    
    public void alteraModelo(String modelo){
        this.modelo = modelo;
        this.preco = 0;
        this.componentesObrigatorios = new ArrayList<>();
        this.componentesOpcionais = new ArrayList<>();
        this.pacotes = new ArrayList<>();
        this.pronta = false;
    }
    
    public void removeComponentesObrigatorios(){
        this.componentesObrigatorios = new ArrayList<>();
    }
    
   /* public List<Integer> getTodosComponentes(){
        List<Integer> res = new ArrayList<>();
        for(int i : this.componentesObrigatorios){
            res.add(i);
        }
        for(int i : this.componentesOpcionais){
            res.add(i);
        }
        
        return res;
    }*/
    
    
}
