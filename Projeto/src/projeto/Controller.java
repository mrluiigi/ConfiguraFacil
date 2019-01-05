/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JList;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

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

        @Override
        public void actionPerformed(ActionEvent e) {
            view.setVisible(false);
            fabricaView = new MenuFabricaView();
            fabricaView.setVisible(true);
            fabricaView.setLocation(45, 45);
            fabricaView.setDefaultCloseOperation(EXIT_ON_CLOSE);

            
            fabricaView.addStockListener(new AddStockListener());
            fabricaView.proxConfigListener(new ProxConfigListener());
            fabricaView.retrocederListener(new RetrocederListener(fabricaView, view));
        }
    }
    
    private class AddStockListener implements ActionListener{
        
        @Override
        public void actionPerformed(ActionEvent e) {
            fabricaView.setVisible(false);
            addStockView = new AddStockView(model);
            addStockView.setVisible(true);
            addStockView.setLocation(45, 45);
            addStockView.setDefaultCloseOperation(EXIT_ON_CLOSE);


            
            addStockView.okListener(new RetrocederListener(addStockView, fabricaView));
            addStockView.adicionarListener(new AdicionarListener());
            addStockView.comboBoxListener(new ComboBoxListener());
        }
    }
    
    private class ComboBoxListener implements ActionListener{
        
         @Override
         public void actionPerformed(ActionEvent e) {
            int comp = addStockView.getComponente().getId();
            boolean obrigatorio = addStockView.getComponente().isObrigatorio();
             try {
                 addStockView.setStock(model.getStockComponente(comp, obrigatorio));
             } catch (SQLException ex) {
                 Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
             }
         }
    }
    
    
    
    
    private class ProxConfigListener implements ActionListener{
        
        @Override
        public void actionPerformed(ActionEvent e) {  
            Configuracao c;
            try {
                c = model.obterProximaConfiguracao();
                if(c != null){
                    fabricaView.setVisible(false);
                    List<Componente> comp = model.getListaComponentes(c.getId());
                    proxConfigView = new ProxConfigView(c.getModelo(), comp);
                    proxConfigView.setVisible(true);
                    proxConfigView.setLocation(45, 45);
                    proxConfigView.setDefaultCloseOperation(EXIT_ON_CLOSE);


                    proxConfigView.okProxListener(new RetrocederListener(proxConfigView, fabricaView));
                }
                else{
                    fabricaView.showError("Não existem configurações para serem feitas");
                }
            } catch (SQLException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    
    /** Listener genérico */
    private class RetrocederListener implements ActionListener{
        JFrame view;
        JFrame displayView; 
        
        public RetrocederListener(JFrame view, JFrame d){
            this.view = view;
            this.displayView = d;
        }
        
        
        @Override
        public void actionPerformed(ActionEvent e) {
            view.dispose();
            displayView.setVisible(true);
        }
    }
    
    private class AdicionarListener implements ActionListener{
        
        @Override
        public void actionPerformed(ActionEvent e) {
            
            ComboItem componente;
            int quantidade = 0;
            
            try{
                componente = addStockView.getComponente();                
                quantidade = addStockView.getQuantidade();
                
                model.adicionarStock(addStockView.isObrigatorio(), componente.getId(), quantidade);

            }catch (Exception ex){
                addStockView.showError("Bad Input");
            }
            
        }
    }
    
    private class CriarConfigListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            view.setVisible(false);
            criarConfigView = new CriarConfigView(model);
            criarConfigView.setVisible(true);
            criarConfigView.setLocation(45, 45);
            criarConfigView.setDefaultCloseOperation(EXIT_ON_CLOSE);

            
            criarConfigView.seguinteListener(new EscolhaListener());
            criarConfigView.retrocederListener(new RetrocederListener(criarConfigView, view));
            criarConfigView.modeloListener(new listaListener());
            criarConfigView.motorListener(new listaListener());
            criarConfigView.pinturaListener(new listaListener());
            criarConfigView.estofosListener(new listaListener());
            criarConfigView.jantesListener(new listaListener());
        }
    }
    
    private class listaListener implements ListSelectionListener{

        @Override
        public void valueChanged(ListSelectionEvent e) {
            criarConfigView.atualizaPreco();
        }
    
    }
    
    private class EscolhaListener implements ActionListener{
        
        @Override
        public void actionPerformed(ActionEvent e) {
            if(criarConfigView.areAllSelected()){
                try {
                model.resetConfiguracao();
                model.escolheModelo(criarConfigView.getModeloId(), criarConfigView.getModeloDesignacao());
                model.adicionaComponenteObrigatorio(criarConfigView.getMotor());
                model.adicionaComponenteObrigatorio(criarConfigView.getPintura());
                model.adicionaComponenteObrigatorio(criarConfigView.getEstofos());
                model.adicionaComponenteObrigatorio(criarConfigView.getJantes());
                } catch (SQLException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
                

                criarConfigView.setVisible(false);
                escolhaView = new EscolhaView();
                escolhaView.setVisible(true);
                escolhaView.setLocation(45, 45);                            
                escolhaView.setDefaultCloseOperation(EXIT_ON_CLOSE);

                escolhaView.retrocederListener(new RetrocederEscolhaListener(escolhaView, criarConfigView));
                escolhaView.automaticoListener(new AutomaticoListener());
                escolhaView.manualListener(new ManualListener());
            }
        }   
    }
    
    private class RetrocederEscolhaListener implements ActionListener{
        JFrame view;
        JFrame displayView;
        
        public RetrocederEscolhaListener(JFrame view, JFrame v){
            this.view = view;
            this.displayView = v;
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            model.removeComponenteObrigatorio();
            view.dispose();
            displayView.setVisible(true);
        }
        
    }
    
    
    private class AutomaticoListener implements ActionListener{
        
        @Override
        public void actionPerformed(ActionEvent e) {
            autom = true;
            escolhaView.setVisible(false);
            automaticoView = new AutomaticoView();
            automaticoView.setVisible(true);
            automaticoView.setLocation(45, 45);
            automaticoView.setDefaultCloseOperation(EXIT_ON_CLOSE);


            
            automaticoView.confirmarListener(new ConfirmarListener());
            automaticoView.retrocederListener(new RetrocederListener(automaticoView, escolhaView));
        }
    }
    
    private class ConfirmarListener implements ActionListener{
        
        @Override
        public void actionPerformed(ActionEvent e) {
            String orcamento = "";
            orcamento = automaticoView.getOrcamento();
            if( !(orcamento.equals(""))){
                try{
                    model.configuracaoOptima(Float.parseFloat(orcamento));   
                    automaticoView.setVisible(false);
                    resumoView = new ResumoView(model);
                    resumoView.setDefaultCloseOperation(EXIT_ON_CLOSE);
                    resumoView.setVisible(true);
                    resumoView.setLocation(45, 45);

                    resumoView.retrocederListener(new RetrocederListener(resumoView, automaticoView));
                    resumoView.confirmarListener(new VoltaInicioListener());
                }catch(Exception exc){
                    exc.printStackTrace();
                    automaticoView.showError("Bad Input");
                }
            }
        }
    }
    
    private class ManualListener implements ActionListener{
        
        @Override
        public void actionPerformed(ActionEvent e) {
            autom = false;
            escolhaView.setVisible(false);
            categoriaView = new CategoriaView(model, "Acabamentos interiores");
            categoriaView.setDefaultCloseOperation(EXIT_ON_CLOSE);
            categoriaView.setVisible(true);
            categoriaView.setLocation(45, 45);
            
            categoriaView.retrocederListener(new RetrocederListener(categoriaView, escolhaView));
            categoriaView.interiorListener(new InteriorListener());
            categoriaView.exteriorListener(new ExteriorListener());
            categoriaView.segurancaListener(new SegurancaListener());
            categoriaView.telematicaListener(new TelematicaListener());
            categoriaView.confirmarListener(new ConfirmarEscolhaManualListener());
            categoriaView.pacote1Listener(new Pacote1Listener());
            categoriaView.pacote2Listener(new Pacote2Listener());
        }
    }
    
    private class Pacote1Listener implements ActionListener{
        
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int id = categoriaView.getPacote1();
                if(categoriaView.isPacote1Selected()) {
                    List<Opcional> inc = model.alteracoesPacoteCompIncompativeis(id);
                    List<Opcional> nec = model.alteracoesPacoteCompNecessarios(id);
                    List<Pacote> pinc = model.alteracoesPacotePacotesIncompativeis(id);
                    if(inc.size() > 0 || nec.size() > 0 || pinc.size() > 0) {
                        incompView = new IncompView(id, true, false, inc, nec, pinc);
                        incompView.setVisible(true);
                        incompView.setLocation(45, 45);
                        incompView.addConfirmarAlteracoesListener(new ConfirmarAlteracoesListener());
                        incompView.addRejeitarAlteracoesListener(new RejeitarAlteracoesListener());
                        incompView.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
                     }
                    else {
                        model.adicionaPacote(id);     
                    }
                }
                else {
                    System.out.println("remove" + id);
                    model.removePacote(id);
                }
            } catch (SQLException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
    }
    
    private class Pacote2Listener implements ActionListener{
        
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int id = categoriaView.getPacote2();
                if(categoriaView.isPacote2Selected()) {
                    List<Opcional> inc = model.alteracoesPacoteCompIncompativeis(id);
                    List<Opcional> nec = model.alteracoesPacoteCompNecessarios(id);
                    List<Pacote> pinc = model.alteracoesPacotePacotesIncompativeis(id);
                    if(inc.size() > 0 || nec.size() > 0 || pinc.size() > 0) {
                        incompView = new IncompView(id, true, false, inc, nec, pinc);
                        incompView.setVisible(true);
                        incompView.setLocation(45, 45);
                        incompView.addConfirmarAlteracoesListener(new ConfirmarAlteracoesListener());
                        incompView.addRejeitarAlteracoesListener(new RejeitarAlteracoesListener());
                        incompView.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

                     }
                    else {
                        model.adicionaPacote(id);     
                    }
                }
                else {
                    System.out.println("remove" + id);
                    model.removePacote(id);
                }
            } catch (SQLException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
    }
    
    private class ConfirmarEscolhaManualListener implements ActionListener{
        
        @Override
        public void actionPerformed(ActionEvent e) {  
                categoriaView.setVisible(false);
                resumoView = new ResumoView(model); 
                resumoView.setVisible(true);
                resumoView.setDefaultCloseOperation(EXIT_ON_CLOSE);
                resumoView.setLocation(45, 45);

                resumoView.retrocederListener(new RetrocederListener(resumoView, categoriaView));
                resumoView.confirmarListener(new VoltaInicioListener());
        }
    }
    
    private class opcionaisListener extends MouseAdapter{
        @Override
        public void mouseClicked(MouseEvent event) {
            try {
            @SuppressWarnings("unchecked")
            JList<CheckboxListItem> list =
               (JList<CheckboxListItem>) event.getSource();
 
            // Get index of item clicked
 
            int index = list.locationToIndex(event.getPoint());
            CheckboxListItem item = (CheckboxListItem) list.getModel().getElementAt(index);
            if(item.isSelected()){
                int id = item.getId();
                List<Opcional> inc = model.alteracoesComponenteOpcionalIncompativeis(item.getId());
                List<Opcional> nec = model.alteracoesComponenteOpcionalNecessarios(id);
                List<Pacote> pinc = model.alteracoesComponenteOpcionalPacotesIncompativeis(id);
                System.out.println("inc: " + inc.size());
                System.out.println("ID: " + id);
                if(inc.size() > 0 || nec.size() > 0 || pinc.size() > 0) {
                    incompView = new IncompView(id, true, true, inc, nec, pinc);
                    incompView.setVisible(true);
                    incompView.setLocation(45, 45);
                    incompView.addConfirmarAlteracoesListener(new ConfirmarAlteracoesListener());
                    incompView.addRejeitarAlteracoesListener(new RejeitarAlteracoesListener());
                    incompView.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
                }
                else {
                    model.adicionaComponenteOpcional(id);     
                }
            }
            else{
                int id = item.getId();
                List<Opcional> inc = model.alteracoesRemoverComponenteOpcionalComponentes(id);
                List<Opcional> nec = new ArrayList<>();
                List<Pacote> pinc = model.alteracoesRemoverComponenteOpcionalPacotes(id);
                if(inc.size() > 0 || nec.size() > 0 || pinc.size() > 0) {
                    System.out.println("teste");
                    incompView = new IncompView(id, false, true, inc, nec, pinc);
                    incompView.setVisible(true);
                    incompView.setLocation(45, 45);
                    incompView.addConfirmarAlteracoesListener(new ConfirmarAlteracoesListener());
                    incompView.addRejeitarAlteracoesListener(new RejeitarAlteracoesListener());
                    incompView.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
                }
                else {
                    model.removerComponenteOpcional(id);
                }
            }
                } catch (SQLException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
        
    
    }
    
    private class ConfirmarAlteracoesListener implements ActionListener{
        
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if(incompView.getAdiciona()){
                    if(incompView.isComponente()) {
                        model.adicionaComponenteOpcional(incompView.getId());
                    }
                    else {
                        model.adicionaPacote(incompView.getId());
                    }
                }
                else{
                    System.out.println("bool" +incompView.isComponente());
                    if(incompView.isComponente()) {
                        System.out.println("remove componente" + incompView.getId());
                        model.removerComponenteOpcional(incompView.getId());
                        
                    }
                    else {
                          System.out.println("remove pacote" + incompView.getId());
                        model.removePacote(incompView.getId());
                    }                   
                }
                categoriaView.setComponentesPacotes(model, "anterior");
                categoriaView.componentesListener(new opcionaisListener());
                incompView.dispose();

            } catch (SQLException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
     private class RejeitarAlteracoesListener implements ActionListener{
        
        @Override
        public void actionPerformed(ActionEvent e) {
            categoriaView.setComponentesPacotes(model, "anterior");
            categoriaView.componentesListener(new opcionaisListener());
            incompView.dispose();
        }
    }
    
    private class InteriorListener implements ActionListener{
        
        @Override
        public void actionPerformed(ActionEvent e) {
            autom = false;
            categoriaView.setComponentesPacotes(model, "Acabamentos interiores");
            categoriaView.componentesListener(new opcionaisListener());
            categoriaView.setVisible(true);
            categoriaView.setLocation(45, 45);
        }
    }

    private class ExteriorListener implements ActionListener{
        
        @Override
        public void actionPerformed(ActionEvent e) {
            autom = false;
            categoriaView.setComponentesPacotes(model, "Acabamentos exteriores");
            categoriaView.componentesListener(new opcionaisListener());
            categoriaView.setVisible(true);
            categoriaView.setLocation(45, 45);
        }
    }
 
    private class SegurancaListener implements ActionListener{
        
        @Override
        public void actionPerformed(ActionEvent e) {
            autom = false;
            categoriaView.setComponentesPacotes(model, "Segurança");
            categoriaView.componentesListener(new opcionaisListener());
            categoriaView.setVisible(true);
            categoriaView.setLocation(45, 45);
        }
    }
    
    private class TelematicaListener implements ActionListener{
        
        @Override
        public void actionPerformed(ActionEvent e) {
            autom = false;
            categoriaView.setComponentesPacotes(model, "Telemática");
            categoriaView.componentesListener(new opcionaisListener());
            categoriaView.setVisible(true);
            categoriaView.setLocation(45, 45);
        }
    }
    
    private class VoltaInicioListener implements ActionListener {
       
        @Override
        public void actionPerformed(ActionEvent e)throws NullPointerException{
            
            //MÉTODO PARA ADICIONAR A CONFIGURAÇÃO À LISTA
            view.setVisible(true);
            resumoView.guardarConfiguracao();
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
                //incompView.dispose();
            }
        }
    }
}