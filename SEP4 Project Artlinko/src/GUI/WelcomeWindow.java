package GUI;

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

import services.CSVHelper;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.UIManager;
import javax.swing.JProgressBar;

public class WelcomeWindow extends JFrame
{

   private JPanel contentPane;
   private JTextField textField;
   JTextArea textArea = new JTextArea();
   JLabel lblProgress = new JLabel("Progress:");
   JLabel progressStatusLabel = new JLabel("");

   /**
    * Launch the application.
    */
   public static void main(String[] args)
   {
      EventQueue.invokeLater(new Runnable()
      {
         public void run()
         {
            try
            {
               WelcomeWindow frame = new WelcomeWindow();
               frame.setVisible(true);
            }
            catch (Exception e)
            {
               e.printStackTrace();
            }
         }
      });
   }

   /**
    * Create the frame.
    */
   public WelcomeWindow()
   {
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
            fc.setCurrentDirectory(new java.io.File("C:/Users"));
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
      fetchBtn.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
            String path = textField.getText();
            path = path.replace("\\", "\\\\");
            CSVHelper csvH = new CSVHelper();
            double percentage = 0;
            try
            {
               csvH.readData(path);
              // for (int i = 0; i < csvH.getProgressStatus(path).size() - 1; i++)
               {
                // Something is bbroken either here or in the getProgressStatus() method from CSVHelper class
                //  progressStatusLabel.setText(csvH.getProgressStatus(path).get(
                //        i).toString());
                  
                //  System.out.println(csvH.getProgressStatus(path).get(i));
               }
            }
            catch (Exception e1)
            {
               String errorM = e1.getMessage();
               textArea.setText(errorM);
            }
         }
      });
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
      restartBtn.setFont(new Font("Tahoma", Font.PLAIN, 12));
      restartBtn.setBounds(456, 197, 94, 29);
      contentPane.add(restartBtn);
      textArea.setForeground(new Color(255, 0, 0));
      textArea.setBackground(UIManager.getColor("CheckBox.background"));
      textArea.setLineWrap(true);

      textArea.setBounds(27, 200, 396, 92);
      contentPane.add(textArea);
      
      JProgressBar progressBar = new JProgressBar();
      progressBar.setStringPainted(true);
      progressBar.setBounds(142, 28, 282, 26);
      contentPane.add(progressBar);
   }
}
