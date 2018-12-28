/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    private float precoMaisBarato;


    public Componentes(){
        this.obrigatorios = new HashMap<>();
        this.opcionais = new HashMap<>();
        this.obrigatorios.put(1, new Obrigatorio(1, 2, "Ola", 20, "Pneus"));
    }

    
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
    
    public boolean temStock(int id, boolean obrigatorio) {
        if(obrigatorio) {
             return (this.obrigatorios.get(id).getStock() > 0);
        }
        else {
             return (this.opcionais.get(id).getStock() > 0);
        }
    }
    
    public void reduzStockObrigatorio(int id){
        this.obrigatorios.get(id).decrementaStock();
    }
    
    public void reduzStockOpcional(int id){
        this.opcionais.get(id).decrementaStock();
    }
    
    public float getPrecoComponenteOpcionalMaisBarato(){
        return this.precoMaisBarato;
    }
    
    public Map<Integer, Opcional> getComponentesOpcionais(){
        return this.opcionais;
    }
    
    public List<Componente> getComponentes(){
        List<Componente> res = new ArrayList<>();
        for(Obrigatorio ob : this.obrigatorios.values()){
            res.add(ob);
        }
        for(Opcional op : this.opcionais.values()){
            res.add(op);
        }
        return res;
    }
}
