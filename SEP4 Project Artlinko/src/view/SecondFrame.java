package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;

import controller.SystemController;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SecondFrame extends JFrame
{

   private JPanel contentPane;
   private JTable table;
   private SystemController controller;

   

   /**
    * Create the frame.
    */
   public SecondFrame(SystemController controller)
   {
     
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setBounds(100, 100, 946, 581);
      contentPane = new JPanel();
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
      setContentPane(contentPane);
      contentPane.setLayout(null);
      
      ArrayList<String> list = (ArrayList<String>) controller
            .getStructForView();
      
      System.out.println(list);
      DataTableModel model = new DataTableModel(); 
      table = new JTable(model);
      table.setBounds(10, 24, 896, 408);
      contentPane.add(table);
      
      JButton btnNewButton = new JButton("Populate Table");
      btnNewButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            model.getAllStruct(controller);
            
         }
      });
      btnNewButton.setBounds(330, 477, 225, 23);
      contentPane.add(btnNewButton);
   }
}
