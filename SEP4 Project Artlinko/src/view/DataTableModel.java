package view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import config.GlobalVar;
import controller.SystemController;

public class DataTableModel implements TableModel, TableModelListener,
      GlobalVar
{

   private String[] colNames = { "ID", "Question", "Answer", "Type(s)",
         "Person", "Survey Instance", "Survey General", "Iwe Dimension",
         "C/L Dimension", "Other Dimension", "Layer" };
   private String[][] data = null;
   private List<String> colData;
   private List<String[]> rowData = new ArrayList<String[]>();
   private List<String> allData;

   public void getAllStruct(SystemController contr)
   {


      List<String> list = contr.getStructForView();

      for (int i = 0; i < list.size(); i++)
      {
         String[] singleRowData = list.get(i).split(":");
         rowData.add(singleRowData);
      }
      this.rowData = rowData;
   }
   
   public void populateTable(List<String[]> rowData){
      for(int i =0; i<rowData.size(); i++)
         for(int j =0; j<rowData.get(i).length; j++)
            setValueAt(rowData.get(i)[j], i, j);
   }
   
   public List<String[]> getRowData(){
      return rowData;
   }
   
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

      return rowData.size();
   }

   @Override
   public Object getValueAt(int i, int j)
   {
      return rowData.get(j)[i];
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
      rowData.get(i)[j] = (String) obj;
   }

   @Override
   public void tableChanged(TableModelEvent arg0)
   {
      // TODO Auto-generated method stub

   }

   
   ///TESTING AREA
  /* public static void main(String[] args)
   {
      DataTableModel model = new DataTableModel();
      SystemController contr = new SystemController(); 
      contr.readSurveys(TESTPATH);
      model.getAllStruct(contr);
      System.out.println(model.getValueAt(1, 0));

      
       String[] splitAllData = tmp.split(","); String tmp =
       allData.toString(); allData = contr.getStructForView(); String[]
       splitAllData = tmp.split(","); for (int i = 0; i< splitAllData.length;
      i++) { System.out.println(splitAllData[i]); }
      }
*/
}
