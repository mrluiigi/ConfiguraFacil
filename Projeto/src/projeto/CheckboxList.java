package projeto;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
 
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
 
public class CheckboxList {
 
   public JList<CheckboxListItem> showCheckBoxList (List<Opcional> componentes) {
     // JFrame frame = new JFrame();
     // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
      // Create a list containing CheckboxListItem's
      CheckboxListItem[] cbl = new CheckboxListItem[componentes.size()];
      int i = 0;
      for(Componente c : componentes) {            
             cbl[i] = new CheckboxListItem(c.getId(), c.getDesignacao()); 
             i++;
        }
      
      JList<CheckboxListItem> list = new JList<CheckboxListItem>(
            cbl);
 
      // Use a CheckboxListRenderer (see below)
      // to renderer list cells
 
      list.setCellRenderer(new CheckboxListRenderer());
      list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
 
      // Add a mouse listener to handle changing selection
 
      list.addMouseListener(new MouseAdapter() {
         public void mouseClicked(MouseEvent event) {
            JList<CheckboxListItem> list =
               (JList<CheckboxListItem>) event.getSource();
 
            // Get index of item clicked
 
            int index = list.locationToIndex(event.getPoint());
            CheckboxListItem item = (CheckboxListItem) list.getModel()
                  .getElementAt(index);
 
            // Toggle selected state
 
            item.setSelected(!item.isSelected());
 
            // Repaint cell
 
            list.repaint(list.getCellBounds(index, index));
         }
      });
 
     // frame.getContentPane().add(new JScrollPane(list));
    //  frame.pack();
    //  frame.setVisible(true);
    return list;
   }
   
}
 
// Represents items in the list that can be selected
 
class CheckboxListItem {
   private String label;
   private int id;
   private boolean isSelected = false;
 
   public CheckboxListItem(int id, String label) {
        this.id = id;
        this.label = label;
   }
 
   public boolean isSelected() {
      return isSelected;
   }
 
   public void setSelected(boolean isSelected) {
      this.isSelected = isSelected;
   }
 
   public String toString() {
      return label;
   }
}
 
// Handles rendering cells in the list using a check box
 
class CheckboxListRenderer extends JCheckBox implements
      ListCellRenderer<CheckboxListItem> {
 
   @Override
   public Component getListCellRendererComponent(
         JList<? extends CheckboxListItem> list, CheckboxListItem value,
         int index, boolean isSelected, boolean cellHasFocus) {
      setEnabled(list.isEnabled());
      setSelected(value.isSelected());
      setFont(list.getFont());
      setBackground(list.getBackground());
      setForeground(list.getForeground());
      setText(value.toString());
      return this;
   }
}