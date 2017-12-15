package view;

import java.awt.Container;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import config.GlobalVar;
import controller.SystemController;

public class DataTable extends AbstractTableModel implements TableModelListener
{

   private SystemController contr;
   private String[] colList = { "questioID", "Question", "Answer", "PERSON",
         "QUESTION", "SURVEY", "GENERAL", "INSTANCE", "SGD", "OTHER",
         "NEW COLUMN NAME" };
   private String[][] allData;

   public DataTable(SystemController contr)
   {

      this.contr = contr;
   }

   public String[] getColumns()
   {

      return colList;
   }

   public int getColumnCount()
   {
      return colList.length;
   }

   public int getRowCount()
   {
      return allData.length;
   }

   public String getColumnName(int col)
   {
      return colList[col];
   }

   public String getValueAt(int row, int col)
   {
      return allData[row][col];
   }

   public String getRow(int row)
   {
      String str = "";
      for (int col = 0; col < getColumnCount(); col++)
      {
         str += getValueAt(row, col) + ":";

      }
      if (str.substring(str.length() - 5, str.length() - 1) == "null")
         str = str.substring(0, str.length() - 6);
      str = str.substring(0, str.length() - 1);

      return str;
   }

   public Class getColumnClass(int c)
   {
      return getValueAt(0, c).getClass();
   }

   public boolean isCellEditable(int row, int col)
   {
      if (col < 2)
      {
         return false;
      }
      else
      {
         return true;
      }
   }

   public void setValueAt(String value, int row, int col)
   {
      allData[row][col] = value;
      fireTableCellUpdated(row, col);
      System.out.println("new value of data: " + allData[row][col]);
   }

   /**
    * Returns all data in a String[][]
    * 
    * @return
    */
   public String[][] getData()
   {
      List<String> rawList = contr.getStructForView();

      String[] row = new String[colList.length];
      List<String[]> rowList = new ArrayList<String[]>();
      allData = new String[rawList.size()][colList.length];

      for (int i = 0; i < rawList.size(); i++)
      {
         String tmp = rawList.get(i);
         row = tmp.split(" : ");
         rowList.add(row);

         for (int j = 0; j < row.length; j++)
         {
            allData[i][j] = row[j];
         }
      }

      return allData;
   };

  /* // //TESTING AREA
   public static void main(String[] args)
   {
      SystemController contr = new SystemController();
      DataTable dt = new DataTable(contr);
      contr.readSurveys("C:\\Users\\Cristi\\Documents\\Course Material\\SEM4\\SEP4\\SEP4D\\Original_data.csv");
      String[][] allData = dt.getData();
      System.out.println(contr.getStructForView());
      System.out.println(dt.getRow(0));
      System.out.println(dt.getTableData());
   }*/

   /**Returns a list of strings with all the data from the JTable
    * @return
    */
   public List<String> getTableData()
   {
      List<String> ans = new ArrayList<String>();
      for (int row = 0; row < getRowCount(); row++)
         ans.add(getRow(row));
      return ans;
   }

   @Override
   public void tableChanged(TableModelEvent e)
   {
      // TODO Auto-generated method stub

   }

}
