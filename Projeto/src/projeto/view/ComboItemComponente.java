/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto.view;

/**
 *
 * @author jose9
 */
public class ComboItemComponente{
    private int id;
    private String designacao;
    private boolean obrigatorio;

    public ComboItemComponente(int id, String designacao, boolean ob){
        this.id = id;
        this.designacao = designacao;
        this.obrigatorio = ob;
    }
    
    @Override
    public String toString(){
        return this.designacao;
    }

    public int getId() {
        return this.id;
    }

    public String getDesignacao() {
        return this.designacao;
    }
    
    public boolean isObrigatorio(){
        return this.obrigatorio;
    }
}