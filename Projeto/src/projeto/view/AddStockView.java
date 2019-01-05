
package projeto.view;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import projeto.logica.Componente;
import projeto.logica.ConfiguraFacil;
import projeto.Controller;
import projeto.logica.Obrigatorio;
import projeto.logica.Opcional;

/**
 *
 * @author José Pinto A81317
 * @author Luís Correia A81141
 * @author Pedro Barbosa A82068
 */
public class AddStockView extends javax.swing.JFrame implements Observer{

    private ConfiguraFacil configuraFacil;
    
    public AddStockView(ConfiguraFacil config) {
        setTitle("Adiciona Stock");
        initComponents();
        this.configuraFacil = config;

        this.configuraFacil.addObserver(this);

        List<Obrigatorio> obrigatorio = this.configuraFacil.getObrigatorios();
        List<Opcional> opcionais = this.configuraFacil.getOpcionais();
        DefaultComboBoxModel<ComboItemComponente> d = new DefaultComboBoxModel<ComboItemComponente>();

        for(Componente c : obrigatorio){
            d.addElement(new ComboItemComponente(c.getId(), c.getDesignacao(), true));
        }

        for(Componente c : opcionais){
            d.addElement(new ComboItemComponente(c.getId(), c.getDesignacao(), false));
        }

        lista.setModel(d);
        stock.setText(Integer.toString(obrigatorio.get(0).getStock()));
    }

    private AddStockView(JFrame jFrame, boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lista = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        quantidade = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        adicionar = new javax.swing.JButton();
        ok = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        stock = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lista.setModel(new javax.swing.DefaultComboBoxModel<>());
        lista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listaActionPerformed(evt);
            }
        });

        jLabel1.setText("Produto");

        quantidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quantidadeActionPerformed(evt);
            }
        });

        jLabel2.setText("Quantidade");

        adicionar.setText("Adicionar");
        adicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adicionarActionPerformed(evt);
            }
        });

        ok.setText("Ok");
        ok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okActionPerformed(evt);
            }
        });

        jLabel3.setText("Stock");

        stock.setText("jLabel4");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(lista, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(stock)
                            .addComponent(jLabel3))
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(quantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(28, 28, 28)
                        .addComponent(adicionar))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ok)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(quantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(adicionar)
                    .addComponent(stock))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addComponent(ok)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void listaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_listaActionPerformed

    private void adicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adicionarActionPerformed
        //TODO add your handling code here:
    }//GEN-LAST:event_adicionarActionPerformed

    private void okActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_okActionPerformed

    private void quantidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quantidadeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_quantidadeActionPerformed

    
    public ComboItemComponente getComponente(){
        return (ComboItemComponente) lista.getSelectedItem();        //PRECISA CAST POIS RETORNA OBJECT
    }
    
    public void setStock(int stock){
        this.stock.setText(Integer.toString(stock));
    }
    
    public int getQuantidade(){
        return Integer.parseInt(this.quantidade.getText());
    }
    
    public boolean isObrigatorio(){
        return ((ComboItemComponente) lista.getSelectedItem()).isObrigatorio();
    }
    
    public void okListener(ActionListener al) {
        ok.addActionListener(al);    
    }
    
    public void adicionarListener(ActionListener al){
        adicionar.addActionListener(al);  
    }
    
    public void comboBoxListener(ActionListener al){
        lista.addActionListener(al);
    }
     
     
    public void showError(String errMessage) {
        JOptionPane.showMessageDialog(this, errMessage);
    }
    
    
    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton adicionar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JComboBox<ComboItemComponente> lista;
    private javax.swing.JButton ok;
    private javax.swing.JTextField quantidade;
    private javax.swing.JLabel stock;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update(Observable o, Object arg) {
        try {
            boolean obrigatorio = ((ComboItemComponente) lista.getSelectedItem()).isObrigatorio();
            int id = ((ComboItemComponente) lista.getSelectedItem()).getId();
            int st = configuraFacil.getStockComponente(id, obrigatorio);
            stock.setText(Integer.toString(st));
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}