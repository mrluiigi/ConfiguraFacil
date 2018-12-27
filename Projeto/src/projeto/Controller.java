/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;

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
    
    private CriarConfigView criarConfigView;
    private EscolhaView escolhaView;
    
    
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
            fabricaView.retrocederListener(new RetrocederListener(fabricaView));
        }
    }
    
    private class CriarConfigListener implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            criarConfigView = new CriarConfigView();
            criarConfigView.setVisible(true);
            
            criarConfigView.seguinteListener(new SeguinteListener());
            
        }
    }
    
    private class AddStockListener implements ActionListener{
        
        public void actionPerformed(ActionEvent e) {
            addStockView = new AddStockView();
            addStockView.setVisible(true);
            
            addStockView.okListener(new OkListener(addStockView));
            addStockView.adicionarListener(new AdicionarListener());
            //ADICIONAR LISTENERS
        }
    }
    
    private class ProxConfigListener implements ActionListener{
        
        public void actionPerformed(ActionEvent e) {                //-------------------------------------------------------------------
            proxConfigView = new ProxConfigView();                  //PENSO QUE É PRECISO ADICIONAR COMO PARAMETRO A PROXIMA CONFIGURAÇÃO
            proxConfigView.setVisible(true);
            
            proxConfigView.okProxListener(new OkListener(proxConfigView));
            //ADICIONAR LISTENERS
        }
    }
    
    private class RetrocederListener implements ActionListener{
        JDialog view;
        
        public RetrocederListener(JDialog view){
            this.view = view;
        }
        
        public void actionPerformed(ActionEvent e) {
            view.dispose();
        }
        
    }
    
    private class OkListener implements ActionListener{
        JDialog view;
        
        public OkListener(JDialog view){
            this.view = view;
        }
        
        public void actionPerformed(ActionEvent e) {
            view.dispose();
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
    
    
    private class SeguinteListener implements ActionListener{
        
        public void actionPerformed(ActionEvent e) {
            escolhaView = new EscolhaView();
            escolhaView.setVisible(true);
        
            escolhaView.retrocederListener(new RetrocederListener(escolhaView));
            //LISTENERS
        }   
    }
    
}




