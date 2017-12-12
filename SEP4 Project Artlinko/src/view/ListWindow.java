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

import services.DataValidator;
import model.ResponseQA;
import model.SurveyResponsesCollection;
import controller.SystemController;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.JScrollBar;

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
      JList<String> list = new JList<String>();
      list.setBounds(10, 25, 764, 377);

      scrollPane.setViewportView(list);
      contentPane.add(scrollPane);

      String concatRow = "";
      DefaultListModel<String> dlm = new DefaultListModel<String>();

      SystemController contr = new SystemController();
      contr.readSurveys();
      SurveyResponsesCollection coll = contr.getAllResponsesCollection();
      int indx = 0;
      for (ResponseQA elem : coll.getSurveys())
      {
         String questionStr = coll.getSurvey(indx).getQuestion();
         String answerDataStr = coll.getSurvey(indx).getAnswers().toString();
         DataValidator dv = new DataValidator();
         String answerTypenStr = dv.returnType(coll.getSurvey(indx));

         concatRow = questionStr + ":" + answerDataStr + ":" + answerTypenStr;
         dlm.addElement(concatRow);
         indx++;
      }

      list.setModel(dlm);

   }
}
