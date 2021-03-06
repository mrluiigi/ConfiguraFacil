package UI;

/**
 *
 * @author José Pinto A81317
 * @author Luís Correia A81141
 * @author Pedro Barbosa A82068
 */

public class CheckboxListItem {
   private String label;
   private int id;
   private boolean isSelected = false;
   private float preco;
 
   public CheckboxListItem(int id, String label, float preco) {
        this.id = id;
        this.label = label;
        this.preco = preco;
   }

    public int getId() {
        return id;
    }
   
   public boolean isSelected() {
      return isSelected;
   }
 
   public void setSelected(boolean isSelected) {
      this.isSelected = isSelected;
   }
 
   @Override
   public String toString() {
      return label + "  " + preco;
   }
}