package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.SystemController;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import view.DataTable;
import java.awt.Font;
public class SecondFrame extends JFrame
{

   private JPanel contentPane;
   private JTable table_1;
   private JTable table_2;
   

   /**
    * Launch the application.
    */
   /*
    * public static void main(String[] args) { EventQueue.invokeLater(new
    * Runnable() { public void run() { try { SecondFrame frame = new
    * SecondFrame(); frame.setVisible(true); } catch (Exception e) {
    * e.printStackTrace(); } } }); }
    */

   /**
    * Create the frame.
    */
   public SecondFrame(SystemController contr)
   {

      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setBounds(100, 100, 949, 788);
      contentPane = new JPanel();
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
      setContentPane(contentPane);
      contentPane.setLayout(null);

      JScrollPane scrollPane = new JScrollPane();
      scrollPane.setBounds(38, 38, 860, 640);
      contentPane.add(scrollPane);
      
      table_2 = new JTable();
      table_2.setColumnSelectionAllowed(true);
      scrollPane.setViewportView(table_2);
      
      
      JButton btnSave = new JButton("Save Changes");
      btnSave.setFont(new Font("Tahoma", Font.BOLD, 12));
      btnSave.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent arg0)
         {
            DataTable table = new DataTable(contr);
            table_1 = new JTable(table.getData(), table.getColumns());
            scrollPane.setViewportView(table_1);
            
         }
      });
      btnSave.setBounds(633, 702, 265, 36);
      contentPane.add(btnSave);
      
      JButton btnBack = new JButton("Back");
      btnBack.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent arg0) {
         }
      });
      btnBack.setFont(new Font("Tahoma", Font.BOLD, 12));
      btnBack.setBounds(38, 702, 265, 36);
      contentPane.add(btnBack);

      contentPane = new JPanel();
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
      setVisible(true);
   }
}
