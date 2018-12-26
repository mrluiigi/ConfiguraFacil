/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Utilizador
 */
public class ConfiguraFacil{
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Configuracoes configuracoes;
        Pacotes pacotes;
        Componentes componentes;
        Configuracao configuracao;
        
        
        
    }
    
    public void adicionaComponenteObrigatorio(int id, Configuracao configuracao, Componentes componentes){
        Obrigatorio obrigatorio;
        obrigatorio = componentes.getObrigatorio(id);
        configuracao.adicionaComponenteObrigatorio(id, obrigatorio.getPreco());
    }
    
    public void adicionaComponenteOpcional(int id, Componentes componentes, Configuracao configuracao){
        Opcional comp = componentes.getOpcional(id);
        List<Opcional> necessarios = new ArrayList<>();
        List<Opcional> incompativeis = new ArrayList<>();
        
        for(int i : comp.getListaNecessarios()){
            if(!(configuracao.containsOpcional(i))){
                Opcional nec = componentes.getOpcional(i);
                necessarios.add(nec);
            }
        }
        
        for(int i : comp.getListaIncompativeis()){
            if(configuracao.containsOpcional(i)){
                Opcional inc = componentes.getOpcional(i);
                incompativeis.add(inc);
            }
        }
    }
    
}
