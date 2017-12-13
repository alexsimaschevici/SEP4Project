package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Label;
import java.awt.Font;

import javax.swing.JFileChooser;
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

import javax.swing.UIManager;
import javax.swing.JProgressBar;

import controller.SystemController;

public class WelcomeWindow extends JFrame
{

   private JPanel contentPane;
   private JTextField textField;
   JTextArea textArea = new JTextArea();
   JLabel lblProgress = new JLabel("Progress:");
   JLabel progressStatusLabel = new JLabel("");
   SystemController contr;

//   /**
//    * Launch the application.
//    */
//   public static void main(String[] args)
//   {
//      EventQueue.invokeLater(new Runnable()
//      {
//         public void run()
//         {
//            try
//            {
//               WelcomeWindow frame = new WelcomeWindow();
//               frame.setVisible(true);
//            }
//            catch (Exception e)
//            {
//               e.printStackTrace();
//            }
//         }
//      });
//   }

   /**
    * Create the frame.
    */
   public WelcomeWindow(SystemController controller)
   {
	   
	  this.contr= controller; 

      setTitle("Welcome");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setBounds(100, 100, 576, 354);
      contentPane = new JPanel();
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
      setContentPane(contentPane);
      contentPane.setLayout(null);

      Label selectFilelbl = new Label("Select csv file:");
      selectFilelbl.setFont(new Font("Dialog", Font.BOLD, 13));
      selectFilelbl.setBounds(27, 28, 105, 29);
      contentPane.add(selectFilelbl);

      textField = new JTextField();
      textField.setBounds(28, 61, 396, 29);
      contentPane.add(textField);
      textField.setColumns(10);

      JButton browseBtn = new JButton("Browse...");
      browseBtn.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent arg0)
         {
            JFileChooser fc = new JFileChooser();
            fc.setCurrentDirectory(new java.io.File("C:\\SCHOOL\\SEP4\\Resources\\Surveys\\Original_data.csv"));
            fc.setDialogTitle("File Browser.");
            fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            if (fc.showOpenDialog(browseBtn) == JFileChooser.APPROVE_OPTION)
            {
               textField.setText(fc.getSelectedFile().getAbsolutePath());
            }
         }
      });
      browseBtn.setFont(new Font("Tahoma", Font.PLAIN, 12));
      browseBtn.setBounds(455, 61, 94, 29);
      contentPane.add(browseBtn);

      JButton fetchBtn = new JButton("Fetch file");
      ActionListener fetchFile = new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
            if (!textField.getText().isEmpty())
            {
               String path = textField.getText();
               path = path.replace("\\", "/");
               System.out.println(path);
               contr.readSurveys(path);
               
               ListWindow listWin = new ListWindow();
               listWin.setVisible(true);
            }
         }
      };
      
      ActionListener nextFrame = new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
           
         }
      };

      fetchBtn.addActionListener(fetchFile);
      fetchBtn.setFont(new Font("Tahoma", Font.PLAIN, 12));
      fetchBtn.setBounds(223, 110, 110, 29);
      contentPane.add(fetchBtn);

      lblProgress.setFont(new Font("Tahoma", Font.PLAIN, 13));
      lblProgress.setBounds(142, 153, 60, 29);
      contentPane.add(lblProgress);

      progressStatusLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
      progressStatusLabel.setBounds(200, 153, 192, 29);
      contentPane.add(progressStatusLabel);

      JButton restartBtn = new JButton("Restart");
      restartBtn.addActionListener(fetchFile);
      restartBtn.setFont(new Font("Tahoma", Font.PLAIN, 12));
      restartBtn.setBounds(356, 197, 94, 29);
      restartBtn.setEnabled(false); 
      contentPane.add(restartBtn);
      
      JButton nextBtn = new JButton("Next");
      nextBtn.addActionListener(nextFrame);
      nextBtn.setFont(new Font("Tahoma", Font.PLAIN, 12));
      nextBtn.setBounds(456, 197, 94, 29);
      contentPane.add(nextBtn);
      
      
      textArea.setForeground(new Color(255, 0, 0));
      textArea.setBackground(UIManager.getColor("CheckBox.background"));
      textArea.setLineWrap(true);

      textArea.setBounds(27, 200, 396, 92);
      contentPane.add(textArea);

      JProgressBar progressBar = new JProgressBar();
      progressBar.setStringPainted(true);
      progressBar.setBounds(138, 28, 285, 29);
      contentPane.add(progressBar);
      progressStatusLabel.setText("0%");
      this.setVisible(true);
   }
   
}
