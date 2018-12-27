/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto;

import java.util.Comparator;

/**
 *
 * @author Barbosa
 */
public class ComponentePrecoComparator implements Comparator<Componente>{

    public int compare(Componente c1, Componente c2) {
        return Float.compare(c1.getPreco(), c2.getPreco());
    }
    
}
