
package UI;

import java.awt.event.ActionListener;

/**
 *
 * @author José Pinto A81317
 * @author Luís Correia A81141
 * @author Pedro Barbosa A82068
 */
public class View extends javax.swing.JFrame {

    /**
     * Creates new form View
     */
    public View(){
        setTitle("ConfiguraFacil");
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fabrica = new javax.swing.JButton();
        criarConf = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        fabrica.setText("Fábrica");
        fabrica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fabricaActionPerformed(evt);
            }
        });

        criarConf.setText("Criar Configuração");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(fabrica)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 105, Short.MAX_VALUE)
                .addComponent(criarConf)
                .addGap(42, 42, 42))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fabrica)
                    .addComponent(criarConf))
                .addContainerGap(59, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fabricaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fabricaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fabricaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton criarConf;
    private javax.swing.JButton fabrica;
    // End of variables declaration//GEN-END:variables

    public void fabricaListener (ActionListener al) {
        fabrica.addActionListener(al);    
    }
    
    public void criarConfigListener(ActionListener al) {
        criarConf.addActionListener(al);    
    }
}