/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto;

import java.util.List;

/**
 *
 * @author Utilizador
 */
public class Opcional extends Componente{
    /**Lista de componentes opcionais necessarios */
    private List<Opcional> necessarios;
    /** Lista de componentes opcionais incompativeis */
    private List<Opcional> incompativeis;
    
    public Opcional(int id, float preco, String designacao, int stock, String categoria) {
        super(id, preco, designacao, stock, categoria);
    }
    
}
