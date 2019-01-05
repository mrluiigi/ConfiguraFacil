
package projeto.logica;

import java.util.Comparator;

public class ComponentePrecoComparator implements Comparator<Componente>{

    @Override
    public int compare(Componente c1, Componente c2) {
        return Float.compare(c2.getPreco(), c1.getPreco());
    }
    
}
