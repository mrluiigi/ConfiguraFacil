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
public class Opcional extends Componente{
    /**Lista de componentes opcionais necessarios */
    private List<Integer> necessarios;
    /** Lista de componentes opcionais incompativeis */
    private List<Integer> incompativeis;
    // id do pacote a que pertence, 0 não pertence a nenhum
    private int pertencePacote;

    public Opcional(List<Integer> necessarios, List<Integer> incompativeis, int pertencePacote, int id, float preco, String designacao, int stock, String categoria) {
        super(id, preco, designacao, stock, categoria);
        this.necessarios = necessarios;
        this.incompativeis = incompativeis;
        this.pertencePacote = pertencePacote;
    }
    
    public List<Integer> getListaNecessarios(){
        return this.necessarios;
    }
    
    public List<Integer> getListaIncompativeis(){
        return this.incompativeis;
    }

    public int getPertencePacote() {
        return pertencePacote;
    }
    
}
