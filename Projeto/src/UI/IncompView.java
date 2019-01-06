
package UI;

import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import ConfiguraFacil.componentes.Componente;
import ConfiguraFacil.componentes.Opcional;
import ConfiguraFacil.componentes.Pacote;

/**
 *
 * @author José Pinto A81317
 * @author Luís Correia A81141
 * @author Pedro Barbosa A82068
 */
public class IncompView extends javax.swing.JFrame {
    int id;
    //1 adicionar
    //0 remover
    boolean adiciona;
    //1 componente
    //0 pacote
    boolean componente;
    

    public IncompView(int id, boolean adiciona, boolean isComponente, List<Opcional> inc, List<Opcional> nec, List<Pacote> pac){
        this.id = id;
        this.adiciona = adiciona;
        this.componente = isComponente;
        setTitle("Incompatibilidades e Necessidades");
        initComponents();
        
        float alteracaoValor = 0;
        
        DefaultListModel<ListOb> mod = new DefaultListModel<>();
        for(Componente c : inc){
            mod.addElement(new ListOb(c.getId(), c.getDesignacao(), c.getPreco()));
            alteracaoValor -= c.getPreco();
        }
        removidos.setModel(mod);
        
        DefaultListModel<ListOb> mod2 = new DefaultListModel<>();
        for(Componente c : nec){
            mod2.addElement(new ListOb(c.getId(), c.getDesignacao(), c.getPreco()));
            alteracaoValor += c.getPreco();
        }
        necessarios.setModel(mod2);
        
        DefaultListModel<ListOb> mod3 = new DefaultListModel<>();
        for(Pacote p : pac){
            mod3.addElement(new ListOb(p.getId(), p.getDesignacao(), p.getPreco()));
            alteracaoValor -= p.getPreco();
        }
        pacotes.setModel(mod3);
        preco.setText(Float.toString(alteracaoValor));
        
        
    }

    private IncompView(JFrame jFrame, boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public boolean getAdiciona() {
        return adiciona;
    }

    public boolean isComponente() {
        return componente;
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
        incompativeis = new javax.swing.JScrollPane();
        removidos = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        necessarios = new javax.swing.JList<>();
        jLabel3 = new javax.swing.JLabel();
        preco = new javax.swing.JLabel();
        confirmar = new javax.swing.JButton();
        retroceder = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        pacotes = new javax.swing.JList<>();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setText("Produtos que são removidos");

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setText("Produtos que são adicionados");

        incompativeis.setViewportView(removidos);

        jScrollPane2.setViewportView(necessarios);

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setText("Balanço");

        preco.setText("preco");

        confirmar.setText("Confirmar");

        retroceder.setText("Retroceder");

        jScrollPane1.setViewportView(pacotes);

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel5.setText("Pacotes que são removidos");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(preco))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(incompativeis, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))))
                .addGap(53, 53, 53)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(retroceder)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(confirmar)
                        .addGap(99, 99, 99))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(incompativeis, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE))
                .addGap(209, 209, 209)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(preco))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(confirmar)
                    .addComponent(retroceder))
                .addGap(17, 17, 17))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void addRejeitarAlteracoesListener (ActionListener al) {
        retroceder.addActionListener(al);
    }
    
    public void addConfirmarAlteracoesListener (ActionListener al) {
        confirmar.addActionListener(al);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton confirmar;
    private javax.swing.JScrollPane incompativeis;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList<ListOb> necessarios;
    private javax.swing.JList<ListOb> pacotes;
    private javax.swing.JLabel preco;
    private javax.swing.JList<ListOb> removidos;
    private javax.swing.JButton retroceder;
    // End of variables declaration//GEN-END:variables
}