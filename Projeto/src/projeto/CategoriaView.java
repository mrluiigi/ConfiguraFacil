/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.sql.SQLException;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.event.ListSelectionListener;



/**
 *
 * @author Utilizador
 */
public class CategoriaView extends javax.swing.JDialog implements Observer{

    private ConfiguraFacil configuraFacil;
    private String categoria;
    private int pacote1;
    private int pacote2;
    /**
     * Creates new form CategoriaView
     */
    public CategoriaView(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    
    public void atualizaPreco() {
        preco.setText("" + configuraFacil.getPreco());
    }
    /*
    public void addComponentesListener(MouseAdapter ma) {
        list.addMouseListener(new ()
    }*/

    public int getPacote1() {
        return pacote1;
    }

    public int getPacote2() {
        return pacote2;
    }
    
    public boolean isPacote1Selected() {
        return checkBoxPacote1.isSelected();
    }
    
    public boolean isPacote2Selected() {
        return checkBoxPacote2.isSelected();
    }
    
    public void test() {
        System.out.println(teste.getSelectedValuesList().size());
    }
    
    public void componentesListener (MouseAdapter ma) {
        teste.addMouseListener(ma);
    }
    
    
    
    public void setComponentesPacotes(ConfiguraFacil config, String cat) {
        try{
        if(!cat.equals("anterior")) {
            this.categoria = cat;
        }
        
        CheckboxList cbl = new CheckboxList();
        List<Opcional> componentesJaPertencentes = configuraFacil.getComponentesCategoria(categoria);
        for(Opcional c : componentesJaPertencentes) {
            System.out.println(c.getId());
        }
        teste = cbl.showCheckBoxList(config.getOpcionais().stream().filter(c -> c.getCategoria().equals(categoria)).collect(Collectors.toList()),componentesJaPertencentes);
        
        List<Pacote> pacotes = config.getPacotes().stream().filter(c -> c.getCategoria().equals(categoria)).collect(Collectors.toList());
        List<Pacote> pacotesConfig = configuraFacil.getListaPacotesCategoriaNaConfiguracao(categoria);
        int size = pacotes.size();
        if(size == 0) {
            listaPacote2.setVisible(false);
            checkBoxPacote2.setVisible(false);
            listaPacote1.setVisible(false);
            checkBoxPacote1.setVisible(false);
        }
        else if(size == 1) {
            listaPacote1.setVisible(true);
            checkBoxPacote1.setVisible(true);
            listaPacote2.setVisible(false);
            checkBoxPacote2.setVisible(false);
            DefaultListModel<ListOb> mod1 = new DefaultListModel<>();
            Pacote p1 = pacotes.get(0);
            pacote1 = p1.getId();
            if(pacotesConfig.contains(p1)) {
                checkBoxPacote1.setSelected(true);
            }
            else {
                checkBoxPacote1.setSelected(false);
            }
            checkBoxPacote1.setText(p1.getDesignacao());
            
            for(Componente c : config.getComponentesPacote(p1.getId())) {
                System.out.println(c.getId());
                mod1.addElement(new ListOb(c.getId(), c.getDesignacao(), c.getPreco()));
            }
            listaPacote1.setModel(mod1);
        }
        else if(size == 2) {
            listaPacote1.setVisible(true);
            checkBoxPacote1.setVisible(true);
            listaPacote2.setVisible(true);
            checkBoxPacote2.setVisible(true);
            DefaultListModel<ListOb> mod1 = new DefaultListModel<ListOb>();
            Pacote p1 = pacotes.get(0);
            pacote1 = p1.getId();
            if(pacotesConfig.contains(p1)) {
                checkBoxPacote1.setSelected(true);
            }
            else {
                checkBoxPacote1.setSelected(false);
            }
            checkBoxPacote1.setText(p1.getDesignacao());
            for(Componente c : config.getComponentesPacote(p1.getId())) {
                mod1.addElement(new ListOb(c.getId(), c.getDesignacao(), c.getPreco()));
            }
            listaPacote1.setModel(mod1);
            
            DefaultListModel<ListOb> mod2 = new DefaultListModel<>();
            Pacote p2 = pacotes.get(1);
            pacote2 = p2.getId();
            if(pacotesConfig.contains(p2)) {
                checkBoxPacote2.setSelected(true);
            }
            else {
                checkBoxPacote2.setSelected(false);
            }
            checkBoxPacote2.setText(p2.getDesignacao());
            for(Componente c : config.getComponentesPacote(p2.getId())) {
                mod2.addElement(new ListOb(c.getId(), c.getDesignacao(), c.getPreco()));
            }
            listaPacote2.setModel(mod2);
        }
        
       jScrollPane1.setViewportView(teste);
        }  catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public CategoriaView(ConfiguraFacil config, String categoria){
        initComponents();
        

        setTitle("Categoria");
        this.configuraFacil = config;
        this.configuraFacil.addObserver(this);
        this.categoria = "Acabamentos interiores";
        atualizaPreco();
    }
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        exterior = new javax.swing.JButton();
        segur = new javax.swing.JButton();
        checkBoxPacote1 = new javax.swing.JCheckBox();
        checkBoxPacote2 = new javax.swing.JCheckBox();
        jLabel9 = new javax.swing.JLabel();
        preco = new javax.swing.JLabel();
        retroceder = new javax.swing.JButton();
        confirmar = new javax.swing.JButton();
        interior = new javax.swing.JButton();
        telem = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        teste = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        listaPacote1 = new javax.swing.JList<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        listaPacote2 = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        exterior.setText("Acabamentos exteriores ");
        exterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exteriorActionPerformed(evt);
            }
        });

        segur.setText("Segurança");
        segur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                segurActionPerformed(evt);
            }
        });

        checkBoxPacote1.setText("Pacote 1");

        checkBoxPacote2.setText("Pacote 2");

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel9.setText("Preço Total:");

        preco.setText("precoTotal");

        retroceder.setText("Retroceder");

        confirmar.setText("Confirmar");

        interior.setText("Acabamentos interiores");
        interior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                interiorActionPerformed(evt);
            }
        });

        telem.setText("Telemática");

        jScrollPane1.setViewportView(teste);

        jScrollPane2.setViewportView(listaPacote1);

        jScrollPane3.setViewportView(listaPacote2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(interior)
                        .addGap(6, 6, 6)
                        .addComponent(exterior)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(segur)
                        .addGap(18, 18, 18)
                        .addComponent(telem))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(jLabel9)
                        .addGap(12, 12, 12)
                        .addComponent(preco))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(retroceder)
                                .addGap(107, 107, 107)
                                .addComponent(confirmar)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(313, 313, 313)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(checkBoxPacote1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3)
                    .addComponent(checkBoxPacote2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(122, 122, 122))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(interior)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(exterior)
                                .addComponent(segur)
                                .addComponent(telem)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(checkBoxPacote2)
                            .addComponent(checkBoxPacote1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(54, 54, 54)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(preco)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(retroceder)
                    .addComponent(confirmar))
                .addContainerGap(134, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exteriorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_exteriorActionPerformed

    private void interiorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_interiorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_interiorActionPerformed

    private void segurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_segurActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_segurActionPerformed

    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CategoriaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CategoriaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CategoriaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CategoriaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                CategoriaView dialog = new CategoriaView(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    public void retrocederListener (ActionListener al) {
        retroceder.addActionListener(al);
    }
    
    public void confirmarListener (ActionListener al) {
        confirmar.addActionListener(al);
    }
    
    public void interiorListener (ActionListener al) {
        interior.addActionListener(al);
    }
    
    public void exteriorListener (ActionListener al) {
        exterior.addActionListener(al);
    }
    
    public void segurancaListener (ActionListener al) {
        segur.addActionListener(al);
    }
    
    public void telematicaListener (ActionListener al) {
        telem.addActionListener(al);
    }
    
    public void pacote1Listener (ActionListener al) {
        checkBoxPacote1.addActionListener(al);
    }
    
    public void pacote2Listener (ActionListener al) {
        checkBoxPacote2.addActionListener(al);
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox checkBoxPacote1;
    private javax.swing.JCheckBox checkBoxPacote2;
    private javax.swing.JButton confirmar;
    private javax.swing.JButton exterior;
    private javax.swing.JButton interior;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JList<ListOb> listaPacote1;
    private javax.swing.JList<ListOb> listaPacote2;
    private javax.swing.JLabel preco;
    private javax.swing.JButton retroceder;
    private javax.swing.JButton segur;
    private javax.swing.JButton telem;
    private javax.swing.JList<CheckboxListItem> teste;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("update");
        preco.setText(Float.toString(configuraFacil.getPreco()));
    }
}
