/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JDialog;

/**
 *
 * @author José Pinto A81317
 * @author Luís Correia A81141
 * @author Pedro Barbosa A82068
 */
public class Controller {
    
    private ConfiguraFacil model;
    private View view;
    private MenuFabricaView fabricaView;
    private AddStockView addStockView;
    private ProxConfigView proxConfigView;
    
    private CriarConfigView criarConfigView;
    private EscolhaView escolhaView;
    private AutomaticoView automaticoView;
    private CategoriaView categoriaView;
    private IncompView incompView;
    private ResumoView resumoView;
    
    //Indica se foi pelo menu automatico ou não;
    private boolean autom;
    
    
    public Controller(ConfiguraFacil m){
        this.model = m;
        this.view = new View();
        this.view.setVisible(true);
        this.view.setLocation(40, 40);
        
        this.view.fabricaListener(new FabricaListener());
        this.view.criarConfigListener(new CriarConfigListener());
    }
    
    private class FabricaListener implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            fabricaView = new MenuFabricaView();
            fabricaView.setVisible(true);
            fabricaView.setLocation(45, 45);
            
            fabricaView.addStockListener(new AddStockListener());
            fabricaView.proxConfigListener(new ProxConfigListener());
            fabricaView.retrocederListener(new RetrocederListener(fabricaView));
        }
    }
    
    private class AddStockListener implements ActionListener{
        
        public void actionPerformed(ActionEvent e) {
            addStockView = new AddStockView(model);
            addStockView.setVisible(true);
            addStockView.setLocation(45, 45);

            
            addStockView.okListener(new OkListener(addStockView));
            addStockView.adicionarListener(new AdicionarListener());
        }
    }
    
    private class ProxConfigListener implements ActionListener{
        
        public void actionPerformed(ActionEvent e) {  
            Configuracao c = model.obterProximaConfiguracao();
            if(c != null){
                List<Componente> comp = model.getListaComponentes(c.getId());
                proxConfigView = new ProxConfigView(c.getModelo(), comp);
                proxConfigView.setVisible(true);
                proxConfigView.setLocation(45, 45);
                
                proxConfigView.okProxListener(new OkListener(proxConfigView));
            }
            else{
                fabricaView.showError("Não existem configurações para serem feitas");
            }
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
            
            ComboItem componente;
            int quantidade = 0;
            
            try{
                componente = addStockView.getProduto();                //----------------------CORRIGIR-----------
                quantidade = addStockView.getQuantidade();
                
                model.adicionarStock(true, componente.getId(), quantidade);

            }catch (Exception ex){
                addStockView.showError("Bad Input");
            }
            
        }
    }
    
    private class CriarConfigListener implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            criarConfigView = new CriarConfigView();
            criarConfigView.setVisible(true);
            criarConfigView.setLocation(45, 45);
            
            criarConfigView.seguinteListener(new EscolhaListener());
            
            //ACABAR OS LISTENERS!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        }
    }
    
    
    private class EscolhaListener implements ActionListener{
        
        public void actionPerformed(ActionEvent e) {
            escolhaView = new EscolhaView();
            escolhaView.setVisible(true);
            escolhaView.setLocation(45, 45);

        
            escolhaView.retrocederListener(new RetrocederListener(escolhaView));
            escolhaView.automaticoListener(new AutomaticoListener());
            escolhaView.manualListener(new ManualListener());
            //LISTENERS
        }   
    }
    
    
    private class AutomaticoListener implements ActionListener{
        
        public void actionPerformed(ActionEvent e) {
            autom = true;
            automaticoView = new AutomaticoView();
            automaticoView.setVisible(true);
            automaticoView.setLocation(45, 45);

            
            automaticoView.confirmarListener(new ConfirmarListener());
            automaticoView.retrocederListener(new RetrocederListener(automaticoView));
        }
    }
    
    private class ConfirmarListener implements ActionListener{
        
        public void actionPerformed(ActionEvent e) {
            String orcamento = "";
            
            if( !(orcamento = automaticoView.getOrcamento()).equals("") ){
                try{
                    //FAZ METODO CONFIGURAÇAO OTIMA
                    resumoView = new ResumoView();  //PROVAVELMENTE RECEBE PARAMETROS
                    resumoView.setVisible(true);
                    resumoView.setLocation(45, 45);


                    resumoView.retrocederListener(new RetrocederListener(resumoView));
                    resumoView.confirmarListener(new VoltaInicioListener());
                }catch(Exception exc){
                    automaticoView.showError("Bad Input");
                }
            }
        }
    }
    
    private class ManualListener implements ActionListener{
        
        public void actionPerformed(ActionEvent e) {
            autom = false;
            categoriaView = new CategoriaView(model);
            categoriaView.setVisible(true);
            categoriaView.setLocation(45, 45);
            
            categoriaView.retrocederListener(new RetrocederListener(categoriaView));
            categoriaView.confirmarListener(new IncompListener());
            categoriaView.interiorListener(new InteriorListener());
            categoriaView.exteriorListener(new ExteriorListener());
            categoriaView.segurancaListener(new SegurancaListener());
            categoriaView.telematicaListener(new TelematicaListener());


            //CONTINUAR COM LISTENERS
        }
    }
    
    private class InteriorListener implements ActionListener{
        
        public void actionPerformed(ActionEvent e){
            //APRESENTA JDIALOG COM COMPONENTES DE INTERIOR
        }
    }

    private class ExteriorListener implements ActionListener{
        
        public void actionPerformed(ActionEvent e){
            //APRESENTA JDIALOG COM COMPONENTES DE EXTERIOR
        }
    }
    
    private class SegurancaListener implements ActionListener{
        
        public void actionPerformed(ActionEvent e){
            //APRESENTA JDIALOG COM COMPONENTES DE SEGURANÇA
        }
    }
    
    private class TelematicaListener implements ActionListener{
        
        public void actionPerformed(ActionEvent e){
            //APRESENTA JDIALOG COM COMPONENTES DE TELEMÁTICA
        }
    }
    
    private class IncompListener implements ActionListener{
        
        public void actionPerformed(ActionEvent e){
            incompView = new IncompView();
            incompView.setVisible(true);
            incompView.setLocation(45, 45);
            
            incompView.retrocederListener(new RetrocederListener(categoriaView));
            incompView.confirmarListener(new ResumoListener());
            //LISTENERS
        }
    }
    
    private class ResumoListener implements ActionListener{
        
        public void actionPerformed(ActionEvent e){
            resumoView = new ResumoView();  //PROVAVELMENTE RECEBE PARAMETROS
            resumoView.setVisible(true);
            resumoView.setLocation(45, 45);

            
            resumoView.retrocederListener(new RetrocederListener(resumoView));
            resumoView.confirmarListener(new VoltaInicioListener());
        }
    }
    
    private class VoltaInicioListener implements ActionListener {
       
        public void actionPerformed(ActionEvent e)throws NullPointerException{
            
            //MÉTODO PARA ADICIONAR A CONFIGURAÇÃO À LISTA

            resumoView.dispose();
            criarConfigView.dispose();
            escolhaView.dispose();
            
            //Caso vá pela configuração automática
            if(autom){
                automaticoView.dispose();
            }
            //Caso vá pela configuração manual
            else{
                categoriaView.dispose();
                incompView.dispose();
            }
        }
    }
}