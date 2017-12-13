package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Label;
import java.awt.Font;

import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import services.CSVHelper;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextArea;

import java.awt.Color;
import java.awt.SystemColor;
import java.util.ArrayList;

import javax.swing.UIManager;
import javax.swing.JProgressBar;

import controller.SystemController;

public class WelcomeWindow
{

   private JPanel contentPane;
   private JTextField textField;
   JTextArea textArea = new JTextArea();
   JLabel lblProgress = new JLabel("Progress:");
   JLabel progressStatusLabel = new JLabel("");
   SystemController contr;
   JFrame frame;

   /**
    * Create the frame.
    */
   public WelcomeWindow(SystemController controller)
   {

      this.contr = controller;
      this.frame = new JFrame("");
      drawFirstPage();
   }

   public void drawFirstPage()
   {
      frame.setTitle("Welcome");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setBounds(100, 100, 576, 354);
      contentPane = new JPanel();
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
      frame.setContentPane(contentPane);
      contentPane.setLayout(null);

      Label selectFilelbl = new Label("Select csv file:");
      selectFilelbl.setFont(new Font("Dialog", Font.BOLD, 13));
      selectFilelbl.setBounds(27, 28, 105, 29);
      contentPane.add(selectFilelbl);

      textField = new JTextField();
      textField.setBounds(28, 61, 396, 29);
      contentPane.add(textField);
      textField.setColumns(10);

      ActionListener nextFrame = new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
            frame.getContentPane().removeAll();
            frame.revalidate();
            frame.repaint();
            drawSecondPage();
         }
      };

      JButton nextBtn = new JButton("Next");
      nextBtn.addActionListener(nextFrame);
      nextBtn.setFont(new Font("Tahoma", Font.PLAIN, 12));
      nextBtn.setBounds(456, 197, 94, 29);
      nextBtn.setEnabled(false);
      contentPane.add(nextBtn);

      JButton fetchBtn = new JButton("Fetch file");
      fetchBtn.setFont(new Font("Tahoma", Font.PLAIN, 12));
      fetchBtn.setBounds(223, 110, 110, 29);
      contentPane.add(fetchBtn);

      JButton restartBtn = new JButton("Restart");
      restartBtn.setFont(new Font("Tahoma", Font.PLAIN, 12));
      restartBtn.setBounds(356, 197, 94, 29);
      contentPane.add(restartBtn);

      
      JButton browseBtn = new JButton("Browse...");
      browseBtn.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent arg0)
         {
            JFileChooser fc = new JFileChooser();
            fc.setCurrentDirectory(new java.io.File(
                  "C:\\SCHOOL\\SEP4\\Resources\\Surveys\\Original_data.csv"));
            fc.setDialogTitle("File Browser.");
            fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            fetchBtn.setEnabled(true);
            restartBtn.setEnabled(false);
            textArea.setText("");
            if (fc.showOpenDialog(browseBtn) == JFileChooser.APPROVE_OPTION)
            {
               textField.setText(fc.getSelectedFile().getAbsolutePath());
            }
         }
      });
      browseBtn.setFont(new Font("Tahoma", Font.PLAIN, 12));
      browseBtn.setBounds(455, 61, 94, 29);
      contentPane.add(browseBtn);


      ActionListener fetchFile = new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
            if (!textField.getText().isEmpty())
            {
               String path = textField.getText();
               path = path.replace("\\", "/");
               try
               {
                  contr.readSurveys(path);
                  progressStatusLabel.setText("File fetched successfully!");
                  nextBtn.setEnabled(true);
                  
               }
               catch (Exception e2)
               {
                  setErrText();
                  restartBtn.setEnabled(true);
                  nextBtn.setEnabled(false);
                  fetchBtn.setEnabled(false);
               }

            }
         }
      };

      restartBtn.addActionListener(fetchFile);

      fetchBtn.addActionListener(fetchFile);

      lblProgress.setFont(new Font("Tahoma", Font.PLAIN, 13));
      lblProgress.setBounds(142, 153, 60, 29);
      contentPane.add(lblProgress);

      progressStatusLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
      progressStatusLabel.setBounds(200, 153, 192, 29);
      contentPane.add(progressStatusLabel);

      textArea.setForeground(new Color(255, 0, 0));
      textArea.setBackground(UIManager.getColor("CheckBox.background"));
      textArea.setLineWrap(true);

      textArea.setBounds(27, 200, 319, 92);
      contentPane.add(textArea);
      progressStatusLabel.setText("0%");
      frame.setVisible(true);
   }

   public void setErrText()
   {
      textArea.setText("Error Loading File. Check file path");
   }
   

   public void drawSecondPage(){
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setBounds(100, 100, 946, 581);
      contentPane = new JPanel();
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
      frame.setContentPane(contentPane);
      contentPane.setLayout(null);
      
      ArrayList<String> list = (ArrayList<String>) contr.getStructForView();
      
      System.out.println(list);
      DataTableModel model = new DataTableModel(); 
      JTable table = new JTable(model);
      table.setBounds(10, 24, 896, 408);
      contentPane.add(table);
      
      JButton btnNewButton = new JButton("Populate Table");
      btnNewButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            model.getAllStruct(contr);
            model.populateTable( model.getRowData());
            model.fireTableDataChanged();
         }
      });
      btnNewButton.setBounds(330, 477, 225, 23);
      contentPane.add(btnNewButton);
   }

}
