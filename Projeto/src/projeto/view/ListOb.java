
package projeto.view;

/**
 *
 * @author jose9
 */
public class ListOb{
    private int id;
    private String designacao;
    private float preco;
    
    public ListOb(int id, String d, float preco){
        this.id = id;
        this.designacao = d;
        this.preco = preco;
    }
    
    @Override
    public String toString(){
        return this.designacao + "        " + this.preco + "€";
    }
    
    public int getId(){
        return this.id;
    }

    public float getPreco() {
        return preco;
    }

    public String getDesignacao() {
        return designacao;
    }
    
}