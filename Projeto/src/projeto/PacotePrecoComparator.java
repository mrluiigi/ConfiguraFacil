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
public class PacotePrecoComparator implements Comparator<Pacote>{
    
    
    public int compare(Pacote p1, Pacote p2) {
        return Float.compare(p1.getPreco(), p2.getPreco());
    }
    
}