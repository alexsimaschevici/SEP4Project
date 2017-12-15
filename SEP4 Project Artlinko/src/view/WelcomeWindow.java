package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Label;
import java.awt.Font;

import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextArea;

import java.awt.Color;

import javax.swing.UIManager;

import config.GlobalVar;
import controller.SystemController;

public class WelcomeWindow implements GlobalVar
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
            fc.setCurrentDirectory(new java.io.File(TESTPATH));
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

   public void drawSecondPage()
   {

      frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      frame.setBounds(100, 100, 949, 788);
      contentPane = new JPanel();
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
      frame.setContentPane(contentPane);
      contentPane.setLayout(null);

      JScrollPane scrollPane = new JScrollPane();
      scrollPane.setBounds(38, 38, 860, 640);
      contentPane.add(scrollPane);

      JTable table_1 = new JTable();
      // table_1.getModel().addTableModelListener();
      DataTable table = new DataTable(contr);
      table_1.setColumnSelectionAllowed(true);
      table_1.setRowSelectionAllowed(true);
      
      table_1 = new JTable(table.getData(), table.getColumns());
      table_1.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);;
      scrollPane.setViewportView(table_1);

      JButton btnSave = new JButton("Finish");
      btnSave.setFont(new Font("Tahoma", Font.BOLD, 12));
      btnSave.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent arg0)
         {
            contr.saveNewProperties(table.getTableData());
            int ret = JOptionPane
                  .showConfirmDialog(null,
                        "Transaction successful. Would you like to convert another survey file?");
            if (ret == JOptionPane.YES_OPTION)
            {
              
               frame.getContentPane().removeAll();
               frame.revalidate();
               frame.repaint();
               drawFirstPage();
            }
            else if (ret == JOptionPane.NO_OPTION)
               System.exit(0);
         }
      });
      btnSave.setBounds(633, 702, 265, 36);
      contentPane.add(btnSave);

      JButton btnBack = new JButton("Back");
      btnBack.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent arg0)
         {
            frame.getContentPane().removeAll();
            frame.revalidate();
            frame.repaint();
            drawFirstPage();
         }
      });
      btnBack.setFont(new Font("Tahoma", Font.BOLD, 12));
      btnBack.setBounds(38, 702, 265, 36);
      contentPane.add(btnBack);

      contentPane = new JPanel();
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

   }
   
   

}
