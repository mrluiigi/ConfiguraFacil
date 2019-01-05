/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;

/**
 *
 * @author José Pinto A81317
 * @author Luís Correia A81141
 * @author Pedro Barbosa A82068
 */
public class ResumoView extends javax.swing.JFrame {
    private ConfiguraFacil configuraFacil;
    private Configuracao configuracao;
   
    
    public ResumoView(ConfiguraFacil con){
        try {
            setTitle("Resumo");
            initComponents();
            this.configuraFacil = con;
            this.configuracao = configuraFacil.getConfiguracao();
            modelo.setText(this.configuracao.getModelo());

            DefaultListModel<String> ob = new DefaultListModel<>();
            DefaultListModel<String> in = new DefaultListModel<>();
            DefaultListModel<String> ex = new DefaultListModel<>();
            DefaultListModel<String> sg = new DefaultListModel<>();
            DefaultListModel<String> tl = new DefaultListModel<>();
            DefaultListModel<String> p = new DefaultListModel<>();

            List<Componente> obgs = this.configuraFacil.getListaComponentesObrigatórios();
            
            for(Componente c : obgs) {
                if(!(c.getCategoria().equals("Modelo"))){
                    ob.addElement(c.getDesignacao());
                }
            }
            
            List<Componente> opcs = this.configuraFacil.getListaComponentesOpcionaisConfiguracao();
            
            for(Componente c : opcs){
                if(c.getCategoria().equals("Acabamentos interiores")){
                        in.addElement(c.getDesignacao());
                }
                else if(c.getCategoria().equals("Acabamentos exteriores")){
                        ex.addElement(c.getDesignacao());
                }
                else if(c.getCategoria().equals("Segurança")){
                        sg.addElement(c.getDesignacao());
                }
                else if(c.getCategoria().equals("Telemática")){
                        tl.addElement(c.getDesignacao());
                }
            }
            
            List<Pacote> pac = this.configuraFacil.getPacotesConfiguracao();
            
            for(Pacote pa : pac){
                p.addElement(pa.getDesignacao());
            }
            obrigatorios.setModel(ob);
            interiores.setModel(in);
            exteriores.setModel(ex);
            seguranca.setModel(sg);
            telematica.setModel(tl);
            pacotes.setModel(p);
        }  catch (SQLException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        retroceder = new javax.swing.JButton();
        confirmar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        obrigatorios = new javax.swing.JList<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        interiores = new javax.swing.JList<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        exteriores = new javax.swing.JList<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        seguranca = new javax.swing.JList<>();
        jScrollPane5 = new javax.swing.JScrollPane();
        pacotes = new javax.swing.JList<>();
        modelo = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        telematica = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setText("Modelo");

        jLabel2.setText("Componentes Obrigatórios");

        retroceder.setText("Retroceder");

        confirmar.setText("Confirmar");

        jScrollPane1.setViewportView(obrigatorios);

        jLabel3.setText("Acabementos Interiores");

        jLabel4.setText("Acabamentos Exteriores");

        jLabel5.setText("Segurança");

        jLabel6.setText("Pacotes");

        jScrollPane2.setViewportView(interiores);

        jScrollPane3.setViewportView(exteriores);

        jScrollPane4.setViewportView(seguranca);

        jScrollPane5.setViewportView(pacotes);

        modelo.setText("jLabel7");

        jLabel7.setText("Telemática");

        jScrollPane6.setViewportView(telematica);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(31, 31, 31)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGap(64, 64, 64)
                                                .addComponent(jLabel3)
                                                .addGap(40, 40, 40))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(270, 270, 270)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                                .addGap(0, 0, Short.MAX_VALUE))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(88, 88, 88)
                                                .addComponent(jLabel1)
                                                .addGap(28, 28, 28)
                                                .addComponent(modelo))
                                            .addComponent(retroceder))
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(40, 40, 40)
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel7)
                                        .addGap(82, 82, 82)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(32, 32, 32)
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(44, 44, 44))
                                    .addComponent(confirmar, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addGap(70, 70, 70)))))
                        .addGap(26, 26, 26))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(modelo))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jLabel3)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(confirmar)
                    .addComponent(retroceder))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    public void retrocederListener (ActionListener al) {
        retroceder.addActionListener(al);
    }
    
    public void confirmarListener (ActionListener al) {
        confirmar.addActionListener(al);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton confirmar;
    private javax.swing.JList<String> exteriores;
    private javax.swing.JList<String> interiores;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JLabel modelo;
    private javax.swing.JList<String> obrigatorios;
    private javax.swing.JList<String> pacotes;
    private javax.swing.JButton retroceder;
    private javax.swing.JList<String> seguranca;
    private javax.swing.JList<String> telematica;
    // End of variables declaration//GEN-END:variables

    void guardarConfiguracao() {
        try {
            configuraFacil.guardarConfiguracao();
        } catch (SQLException ex) {
            Logger.getLogger(ResumoView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
