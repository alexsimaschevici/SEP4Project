package view;



import java.util.ArrayList;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class SharedListSelectionHandler implements ListSelectionListener {
   public void valueChanged(ListSelectionEvent e) {
      ListSelectionModel lsm = (ListSelectionModel)e.getSource();
      ArrayList<String> output = new ArrayList<>();
      boolean isAdjusting = e.getValueIsAdjusting();
      
      int firstIndex = e.getFirstIndex();
      int lastIndex = e.getLastIndex();
      
      if (lsm.isSelectionEmpty()) {
          output.add(" <none>");
      } else {
          // Find out which indexes are selected.
          int minIndex = lsm.getMinSelectionIndex();
          int maxIndex = lsm.getMaxSelectionIndex();
          for (int i = minIndex; i <= maxIndex; i++) {
              if (lsm.isSelectedIndex(i)) {
                  output.add(isAdjusting + "" + i);
              }
          }
      }
      
      System.out.println(output);
  }
}