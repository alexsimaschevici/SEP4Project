package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.ListSelectionModel;

import services.DataValidator;
import model.ResponseQA;
import model.SurveyResponsesCollection;
import controller.SystemController;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollBar;

import java.awt.Font;

public class ListWindow extends JFrame
{

   private JPanel contentPane;

   /**
    * Launch the application.
    */
   /*
    * public static void main(String[] args) { EventQueue.invokeLater(new
    * Runnable() { public void run() { try { ListWindow frame = new
    * ListWindow(); frame.setVisible(true); } catch (Exception e) {
    * e.printStackTrace(); } } }); }
    */

   /**
    * Create the frame.
    */
   public ListWindow()
   {
      getContentPane().setLayout(null);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setBounds(100, 100, 800, 486);
      contentPane = new JPanel();
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
      setContentPane(contentPane);
      contentPane.setLayout(null);

      JScrollPane scrollPane = new JScrollPane();
      scrollPane.setBounds(10, 25, 764, 377);
      JList<String> jList = new JList<String>();
      jList.setBounds(10, 25, 764, 377);
      scrollPane.setViewportView(jList);
      // ListSelectionModel listSelectionModel = jList.getSelectionModel();
      // listSelectionModel.addListSelectionListener(new
      // SharedListSelectionHandler());
      contentPane.add(scrollPane);

      String concatRow = "";
      DefaultListModel<String> dlm = new DefaultListModel<String>();

      SystemController contr = new SystemController();
      contr.readSurveys("");
      SurveyResponsesCollection coll = contr.getAllResponsesCollection();
      String surveyInstanceID = coll.getSurveys().get(0).getSurveyInstanceID();
      ArrayList<ResponseQA> qaList = (ArrayList<ResponseQA>) coll
            .getRow(surveyInstanceID);
      // ArrayList<String> struct = (ArrayList<String>)
      // contr.getStructForView();
      int indx = 0;
      // We display only the first respondent's Question+Answer+answertype
      for (ResponseQA elem : qaList)
      {
         DataValidator dv = new DataValidator();

         String questionStr = qaList.get(indx).getQuestion();
         questionStr = String.format("%-20s", questionStr);
         String answerDataStr = qaList.get(indx).getAnswers().toString();
         answerDataStr = String.format("%20s", answerDataStr);
         String answerTypeStr = dv.returnType(qaList.get(indx));
         answerTypeStr = String.format("%20s", answerTypeStr);
         //concatRow = questionStr + ":" + answerDataStr + ":" + answerTypeStr;

         if (questionStr.length() < 20 && answerDataStr.length() < 20)
         {
            int n = 20 - questionStr.length(); 
            for (int i=0; i<n; i++)
               questionStr += " "; 
            concatRow = questionStr + ":" + answerDataStr + ":"
                  + answerTypeStr;
         }
         else if (questionStr.length() < 20)
            concatRow = questionStr + answerDataStr.substring(0, 20) + ":"
                  + answerTypeStr;
         else if (answerDataStr.length() < 20)
            concatRow = questionStr.substring(0, 20) + answerDataStr + ":"
                  + answerTypeStr;
         else
            concatRow = questionStr.substring(0, 20) + ":"
                  + answerDataStr.substring(0, 20) + ":" + answerTypeStr;

         dlm.addElement(concatRow);
         indx++;
      }

      jList.setModel(dlm);

      JButton btnNewButton = new JButton("Confirm");
      btnNewButton.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent arg0)
         {
            String selected = "";

            int[] selectedIx = jList.getSelectedIndices();
            for (int i = 0; i < selectedIx.length; i++)
            {
               selected += jList.getModel().getElementAt(selectedIx[i]) + "\n";
            }
            /*
             * QPropertiesWindow propWin = new QPropertiesWindow();
             * propWin.setVisible(true);
             */
         }
      });
      btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
      btnNewButton.setBounds(640, 413, 134, 29);
      contentPane.add(btnNewButton);

   }

   public static String padRight(String s, int n)
   {
      return String.format(s + "");
   }
}
