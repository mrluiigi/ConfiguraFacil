
package UI;
/**
 *
 * @author José Pinto A81317
 * @author Luís Correia A81141
 * @author Pedro Barbosa A82068
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