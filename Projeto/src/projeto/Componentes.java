/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto;

import java.util.Map;

/**
 *
 * @author Utilizador
 */
public class Componentes {
    /** Todos os componentes obrigatorios */
    private Map<Integer, Obrigatorio> obrigatorios;
    /** Todos os componentes opcionais */
    private Map<Integer, Opcional> opcionais;

    public Componentes(Map<Integer, Obrigatorio> obrigatorios, Map<Integer, Opcional> opcionais) {
        this.obrigatorios = obrigatorios;
        this.opcionais = opcionais;
    }

    public Obrigatorio getObrigatorio(int id) {
        return obrigatorios.get(id);
    }

    public Opcional getOpcional(int id) {
        return opcionais.get(id);
    }
    
    public boolean temStock(Componente id, boolean obrigatorio) {
        if(obrigatorio) {
             return (this.obrigatorios.get(id).getStock() > 0);
        }
        else {
             return (this.opcionais.get(id).getStock() > 0);
        }
    }
    
}
