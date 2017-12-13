package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;

import controller.SystemController;

public class SecondFrame extends JFrame
{

   private JPanel contentPane;
   private JTable table;
   private SystemController controller;

   /**
    * Launch the application.
    */
  /* public static void main(String[] args)
   {
      EventQueue.invokeLater(new Runnable()
      {
         public void run()
         {
            try
            {
               SecondFrame frame = new SecondFrame();
               frame.setVisible(true);
            }
            catch (Exception e)
            {
               e.printStackTrace();
            }
         }
      });
   }*/

   /**
    * Create the frame.
    */
   public SecondFrame(SystemController controller)
   {
      this.controller = controller;
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setBounds(100, 100, 946, 581);
      contentPane = new JPanel();
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
      setContentPane(contentPane);
      contentPane.setLayout(null);
      DataTableModel tableModel = new DataTableModel();
      ArrayList<String> list = (ArrayList<String>) controller
            .getStructForView();
      
      System.out.println(list);

      table = new JTable(tableModel);
      table.setBounds(10, 24, 896, 408);
      contentPane.add(table);
   }
}
