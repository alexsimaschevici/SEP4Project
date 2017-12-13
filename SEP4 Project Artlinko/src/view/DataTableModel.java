package view;

import java.util.List;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class DataTableModel implements TableModel, TableModelListener
{

   private String[] colNames = { "ID", "Question", "Answer", "Type(s)", "Person", "Survey Instance", "Survey General", "Iwe Dimension", "C/L Dimension" , "Other Dimension", "Layer" };
   private String[][] data = null;
   private List<String> colData;

   @Override
   public void addTableModelListener(TableModelListener arg0)
   {
      // TODO Auto-generated method stub

   }

   @Override
   public Class<?> getColumnClass(int c)
   {
      return getValueAt(0, c).getClass();

   }

   @Override
   public int getColumnCount()
   {
      return colNames.length;
   }

   @Override
   public String getColumnName(int i)
   {

      return colNames[i];
   }

   @Override
   public int getRowCount()
   {
      
         return data[0].length;
   }

   @Override
   public Object getValueAt(int i, int j)
   {
      return data[i][j];
   }

   @Override
   public boolean isCellEditable(int i, int j)
   {
      return true;
   }

   @Override
   public void removeTableModelListener(TableModelListener arg0)
   {
      // TODO Auto-generated method stub

   }

   @Override
   public void setValueAt(Object obj, int i, int j)
   {
      data[i][j] = (String) obj;
   }

   @Override
   public void tableChanged(TableModelEvent arg0)
   {
      // TODO Auto-generated method stub

   }

}
