
package ConfiguraFacil.componentes;

import java.util.Comparator;

/**
 *
 * @author José Pinto A81317
 * @author Luís Correia A81141
 * @author Pedro Barbosa A82068
 */
public class PacotePrecoComparator implements Comparator<Pacote>{
    
    
    @Override
    public int compare(Pacote p1, Pacote p2) {
        return Float.compare(p1.getPreco(), p2.getPreco());
    }
    
}
