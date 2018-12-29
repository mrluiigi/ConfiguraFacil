/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto;

import java.awt.event.ActionListener;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.stream.Collectors;
import javax.swing.JCheckBox;



/**
 *
 * @author Utilizador
 */
public class CategoriaView extends javax.swing.JDialog implements Observer{

    private ConfiguraFacil configuraFacil;
    /**
     * Creates new form CategoriaView
     */
    public CategoriaView(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public CategoriaView(ConfiguraFacil config){
        initComponents();
        CheckboxList cbl = new CheckboxList();
        teste = cbl.showCheckBoxList(config.getOpcionais().stream().filter(c -> c.getCategoria().equals("Segurança")).collect(Collectors.toList()));
        List<Pacote> pacotes = config.getPacotes().stream().filter(c -> c.getCategoria().equals("segurança")).collect(Collectors.toList());
        int size = pacotes.size();
        if(size < 2) {
            listaPacote2.setVisible(false);
            checkBoxPacote2.setVisible(false);
            if (size < 1) {
                listaPacote1.setVisible(false);
                checkBoxPacote1.setVisible(false);
            }
        }
        
        jScrollPane1.setViewportView(teste);

        setTitle("Categoria");
        this.configuraFacil = config;
        this.configuraFacil.addObserver(this);
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

        listaPacote1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(listaPacote1);

        listaPacote2.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
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
                        .addComponent(exterior))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(jLabel9)
                        .addGap(12, 12, 12)
                        .addComponent(preco))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(retroceder)
                        .addGap(107, 107, 107)
                        .addComponent(confirmar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(segur)
                        .addGap(44, 44, 44)
                        .addComponent(telem)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(checkBoxPacote1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 178, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(122, 122, 122))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(checkBoxPacote2, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(97, 97, 97))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(interior)
                            .addComponent(exterior))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(segur)
                            .addComponent(telem))
                        .addGap(9, 9, 9)
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
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(preco)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(retroceder)
                    .addComponent(confirmar)))
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
    private javax.swing.JList<String> listaPacote1;
    private javax.swing.JList<String> listaPacote2;
    private javax.swing.JLabel preco;
    private javax.swing.JButton retroceder;
    private javax.swing.JButton segur;
    private javax.swing.JButton telem;
    private javax.swing.JList<CheckboxListItem> teste;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update(Observable o, Object arg) {
        preco.setText(Float.toString(configuraFacil.getPreco()));
    }
}
