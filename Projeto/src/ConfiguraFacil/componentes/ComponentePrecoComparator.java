
package ConfiguraFacil.componentes;

import java.util.Comparator;

/**
 *
 * @author José Pinto A81317
 * @author Luís Correia A81141
 * @author Pedro Barbosa A82068
 */

public class ComponentePrecoComparator implements Comparator<Componente>{

    @Override
    public int compare(Componente c1, Componente c2) {
        return Float.compare(c2.getPreco(), c1.getPreco());
    }
    
}
