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
        
        //id, preco, designacao, stock, categoria
        this.obrigatorios.put(1, new Obrigatorio(1, 20000, "Motor 270kW", 23, "Motor"));
        this.obrigatorios.put(2, new Obrigatorio(2, 22500, "Motor 300kW", 17, "Motor"));
        this.obrigatorios.put(3, new Obrigatorio(3, 27000, "Motor 320kW", 12, "Motor"));
        this.obrigatorios.put(4, new Obrigatorio(4, 35000, "Motor 430kW", 5, "Motor"));
        
        this.obrigatorios.put(5, new Obrigatorio(5, 5000, "Branco Polar", 100, "Pintura"));
        this.obrigatorios.put(6, new Obrigatorio(6, 5000, "Azul Brilliant", 73, "Pintura"));
        this.obrigatorios.put(7, new Obrigatorio(7, 5000, "Cinzento Graphite", 50, "Pintura"));
        this.obrigatorios.put(8, new Obrigatorio(8, 5000, "Cinzento Selenite", 83, "Pintura"));
        this.obrigatorios.put(9, new Obrigatorio(9, 5000, "Vermelho Jupiter", 55, "Pintura"));
        this.obrigatorios.put(10, new Obrigatorio(10, 5000, "Preto Obsidian", 68, "Pintura"));
        this.obrigatorios.put(11, new Obrigatorio(11, 7500, "Prata Eridium", 31, "Pintura"));
        
        this.obrigatorios.put(12, new Obrigatorio(12, 1500, "Pele Preto", 120, "Estofos"));
        this.obrigatorios.put(13, new Obrigatorio(13, 1500, "Pele cinzento e preto", 103, "Estofos"));
        this.obrigatorios.put(14, new Obrigatorio(14, 2000, "Pele exclusive preto", 107, "Estofos"));
        this.obrigatorios.put(15, new Obrigatorio(15, 2000, "Pele exclusive castanho", 93, "Estofos"));
        this.obrigatorios.put(16, new Obrigatorio(16, 2500, "Pele dinamica vermelha", 88, "Estofos"));
        this.obrigatorios.put(17, new Obrigatorio(17, 2500, "Pele dinamica preto", 113, "Estofos"));
        
        this.obrigatorios.put(18, new Obrigatorio(18, 650, "Liga Leve AMG multiraios de 20\"", 83, "Jantes"));
        this.obrigatorios.put(19, new Obrigatorio(19, 650, "5 raios AMG de 20\"", 60, "Jantes"));
        this.obrigatorios.put(20, new Obrigatorio(20, 650, "AMG com raios cruzados de 21\"", 55, "Jantes"));
        this.obrigatorios.put(21, new Obrigatorio(21, 550, "AMG multiraios de 21\"", 73, "Jantes"));
        this.obrigatorios.put(22, new Obrigatorio(22, 750, "Liga leve de 5 raios duplos de 20\"", 38, "Jantes"));
        this.obrigatorios.put(23, new Obrigatorio(23, 1250, "Forjadas de raios cruzados de 21\"", 20, "Jantes"));
        
        // necessarios, incompativeis, int pertencePacote, int id, float preco, String designacao, int stock, String categoria
        this.opcionais.put(1, new Opcional(new ArrayList<>(), new ArrayList<Integer>() {{add(2); add(3); add(4); add(5);}}, 0, 1, 750, "Piano preto lacado", 50, "Acabamentos interiores"));
        this.opcionais.put(2, new Opcional(new ArrayList<>(), new ArrayList<Integer>() {{add(1); add(3); add(4); add(5);}}, 0, 2, 3650, "AMG carbono mate", 13, "Acabamentos interiores"));
        this.opcionais.put(3, new Opcional(new ArrayList<>(), new ArrayList<Integer>() {{add(1); add(2); add(4); add(5);}}, 0, 3, 3650, "AMG fibra de carbono", 17, "Acabamentos interiores"));
        this.opcionais.put(4, new Opcional(new ArrayList<>(), new ArrayList<Integer>() {{add(1); add(2); add(3); add(5);}}, 0, 4, 500, "Madeira de freixo", 25, "Acabamentos interiores"));
        this.opcionais.put(5, new Opcional(new ArrayList<>(), new ArrayList<Integer>() {{add(1); add(2); add(3); add(4);}}, 0, 5, 10, "Madeira de freixo cinzento", 20, "Acabamentos interiores"));
        
        this.opcionais.put(6, new Opcional(new ArrayList<>(), new ArrayList<>(), 1, 6, 500, "Vidros laterais escurecidos", 48, "Acabamentos exteriores"));
        this.opcionais.put(7, new Opcional(new ArrayList<Integer>() {{add(9);}}, new ArrayList<>(), 1, 7, 450, "Teto escurecido", 30, "Acabamentos exteriores"));
        this.opcionais.put(8, new Opcional(new ArrayList<>(), new ArrayList<>(), 1, 8, 1200, "Cobertura do motor em carbono", 20, "Acabamentos exteriores"));
        this.opcionais.put(9, new Opcional(new ArrayList<>(), new ArrayList<>(), 1, 9, 750, "Teto panorâmico", 13, "Acabamentos exteriores"));
        this.opcionais.put(10, new Opcional(new ArrayList<>(), new ArrayList<>(), 1, 10, 500, "Pinça de travões colorida", 89, "Acabamentos exteriores"));
        this.opcionais.put(11, new Opcional(new ArrayList<>(), new ArrayList<>(), 1, 11, 3000, "Spoiler traseiro", 7, "Acabamentos exteriores"));
        this.opcionais.put(12, new Opcional(new ArrayList<>(), new ArrayList<>(), 0, 12, 100, "Gancho para reboque", 50, "Acabamentos exteriores"));
        
        this.opcionais.put(13, new Opcional(new ArrayList<>(), new ArrayList<>(), 0, 13, 500, "Câmara de marcha atrás", 20, "Segurança"));
        this.opcionais.put(14, new Opcional(new ArrayList<>(), new ArrayList<>(), 1, 14, 500, "Assistente de ângulo morto", 17, "Segurança"));
        this.opcionais.put(15, new Opcional(new ArrayList<>(), new ArrayList<>(), 1, 15, 750, "Prevenção de colisão", 12, "Segurança"));
        this.opcionais.put(16, new Opcional(new ArrayList<>(), new ArrayList<>(), 1, 16, 1000, "Assistente de direção", 8, "Segurança"));
        this.opcionais.put(17, new Opcional(new ArrayList<>(), new ArrayList<>(), 1, 17, 500, "Sidebags traseiros", 40, "Segurança"));
        this.opcionais.put(18, new Opcional(new ArrayList<>(), new ArrayList<>(), 1, 18, 150, "Extintor", 13, "Segurança"));
        this.opcionais.put(19, new Opcional(new ArrayList<>(), new ArrayList<>(), 1, 19, 1000, "Câmara 360º", 15, "Segurança"));
        this.opcionais.put(20, new Opcional(new ArrayList<>(), new ArrayList<>(), 1, 20, 350, "Assistente de faixa de rodagem", 23, "Segurança"));
        this.opcionais.put(21, new Opcional(new ArrayList<>(), new ArrayList<>(), 1, 21, 250, "Assistente automático do controlo de velocidade", 30, "Segurança"));
        
        this.opcionais.put(22, new Opcional(new ArrayList<>(), new ArrayList<>(), 0, 22, 1250, "Sintonizador de TV", 3, "Telemática"));
        this.opcionais.put(23, new Opcional(new ArrayList<>(), new ArrayList<>(), 1, 23, 150, "Chave digital no smartphone", 30, "Telemática"));
        this.opcionais.put(24, new Opcional(new ArrayList<>(), new ArrayList<>(), 1, 24, 350, "Carregamento wireless para smartphone", 13, "Telemática"));
        this.opcionais.put(25, new Opcional(new ArrayList<>(), new ArrayList<>(), 0, 25, 350, "Carregamento wireless para smartphone (zona traseira)", 25, "Telemática"));
        this.opcionais.put(26, new Opcional(new ArrayList<>(), new ArrayList<>(), 0, 26, 450, "Sirius Satellite Radio", 60, "Telemática"));
        this.opcionais.put(27, new Opcional(new ArrayList<>(), new ArrayList<>(), 0, 27, 1500, "Sistema de Som Burmester", 30, "Telemática"));
        this.opcionais.put(28, new Opcional(new ArrayList<>(), new ArrayList<>(), 0, 28, 5000, "Sistema de Som Burmester 3D High End", 10, "Telemática"));
        
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
