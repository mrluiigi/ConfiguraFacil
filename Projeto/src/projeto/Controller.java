/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Utilizador
 */
public class Controller {
    
    private ConfiguraFacil model;
    
    private View view;
    
    private MenuFabricaView fabricaView;
    
    private AddStockView addStockView;
    
    private ProxConfigView proxConfigView;
    
    
    
    public Controller(ConfiguraFacil m){
        this.model = m;
        this.view = new View();
        this.view.setVisible(true);
        this.view.setLocation(20, 20);
        
        this.view.fabricaListener(new FabricaListener());
        this.view.criarConfigListener(new CriarConfigListener());
    }
    
    private class FabricaListener implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            fabricaView = new MenuFabricaView();
            fabricaView.setVisible(true);
            
            fabricaView.addStockListener(new AddStockListener());
            fabricaView.proxConfigListener(new ProxConfigListener());
            fabricaView.retrocederListener(new RetrocederListener());
        }
    }
    
    private class CriarConfigListener implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            //Fazer view para config
            
            //ADICIONAR LISTENERS

        }
    }
    
    private class AddStockListener implements ActionListener{
        
        public void actionPerformed(ActionEvent e) {
            addStockView = new AddStockView();
            addStockView.setVisible(true);
            
            addStockView.okListener(new OkListener());
            addStockView.adicionarListener(new AdicionarListener());
            //ADICIONAR LISTENERS
        }
    }
    
    private class ProxConfigListener implements ActionListener{
        
        public void actionPerformed(ActionEvent e) {                //-------------------------------------------------------------------
            proxConfigView = new ProxConfigView();                  //PENSO QUE É PRECISO ADICIONAR COMO PARAMETRO A PROXIMA CONFIGURAÇÃO
            proxConfigView.setVisible(true);
            
            proxConfigView.okProxListener(new OkProxListener());
            //ADICIONAR LISTENERS
        }
    }
    
    private class RetrocederListener implements ActionListener{
        
        public void actionPerformed(ActionEvent e) {
            fabricaView.dispose();
        }
        
    }
    
    private class OkListener implements ActionListener{
        
        public void actionPerformed(ActionEvent e) {
            addStockView.dispose();
        }
    }
    
    private class AdicionarListener implements ActionListener{
        
        public void actionPerformed(ActionEvent e) {
            
            String produto = "";
            String quantidade = "";
            
            try{
                produto = addStockView.getProduto();                //---------------------------------
                quantidade = addStockView.getQuantidade();                
                
                // CHAMAR FUNÇÃO PARA ADICIONAR STOCK!

            }catch (Exception ex){
                addStockView.showError("Bad Input");
            }
            
        }
    }
    
    private class OkProxListener implements ActionListener{
        
        public void actionPerformed(ActionEvent e) {
            proxConfigView.dispose();
        }
        
    }
    
}




