package UI;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
 
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import ConfiguraFacil.componentes.Opcional;

/**
 *
 * @author José Pinto A81317
 * @author Luís Correia A81141
 * @author Pedro Barbosa A82068
 */

public class CheckboxList {
 
   public JList<CheckboxListItem> showCheckBoxList (List<Opcional> componentes, List<Opcional> componentesJaPertencentes ) {

      CheckboxListItem[] cbl = new CheckboxListItem[componentes.size()];
      int i = 0;
      for(Opcional c : componentes) {            
             cbl[i] = new CheckboxListItem(c.getId(), c.getDesignacao(), c.getPreco());
             if (componentesJaPertencentes.contains(c)) {
                 cbl[i].setSelected(true);
             }
             i++;
        }
      
      JList<CheckboxListItem> list = new JList<>(
            cbl);

      list.setCellRenderer(new CheckboxListRenderer());
      list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

 
      list.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent event) {
            @SuppressWarnings("unchecked")
            JList<CheckboxListItem> list =
               (JList<CheckboxListItem>) event.getSource();
 
            int index = list.locationToIndex(event.getPoint());
            CheckboxListItem item = (CheckboxListItem) list.getModel()
                  .getElementAt(index);
 
            item.setSelected(!item.isSelected());
 
            list.repaint(list.getCellBounds(index, index));
         }
      });
    return list;
   }
   
}
 
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