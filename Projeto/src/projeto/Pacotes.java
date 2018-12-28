/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto;

import java.util.Map;

/**
 *
 * @author José Pinto A81317
 * @author Luís Correia A81141
 * @author Pedro Barbosa A82068
 */
public class Pacotes {
    private Map<Integer, Pacote> pacotes;
    private float precoPacoteMaisBarato;
    
    public Pacote getPacote(int id){
        return this.pacotes.get(id);
    }

    public float getPrecoPacoteMaisBarato() {
        return precoPacoteMaisBarato;
    }

    public Map<Integer, Pacote> getPacotes() {
        return pacotes;
    }
    
    
    
    
}
