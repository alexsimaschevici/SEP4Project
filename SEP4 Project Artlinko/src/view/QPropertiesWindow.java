package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.JButton;

public class QPropertiesWindow extends JFrame
{

   private JPanel contentPane;
   private JTextField textField;

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
               QPropertiesWindow frame = new QPropertiesWindow();
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
   public QPropertiesWindow()
   {
      setTitle("Edit Properties");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setBounds(100, 100, 622, 508);
      contentPane = new JPanel();
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
      setContentPane(contentPane);
      contentPane.setLayout(null);

      JLabel lblNewLabel = new JLabel(
            "Edit the properties of all selected questions and answers");
      lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
      lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
      lblNewLabel.setBounds(98, 21, 404, 27);
      contentPane.add(lblNewLabel);

      JRadioButton rdbtnPerson = new JRadioButton(
            "Personal Feature (ex. age, income)");
      rdbtnPerson.setBounds(25, 55, 247, 23);
      contentPane.add(rdbtnPerson);

      JRadioButton rdbtnSurvey = new JRadioButton("Survey");
      rdbtnSurvey.setBounds(25, 81, 193, 23);
      contentPane.add(rdbtnSurvey);

      JRadioButton rdbtnQuestion = new JRadioButton("Question Type");
      rdbtnQuestion.setBounds(25, 159, 193, 23);
      contentPane.add(rdbtnQuestion);

      ButtonGroup group1 = new ButtonGroup();

      group1.add(rdbtnPerson);
      group1.add(rdbtnQuestion);
      group1.add(rdbtnSurvey);

      JRadioButton rdbtnGeneral = new JRadioButton("General");
      rdbtnGeneral.setBounds(46, 107, 109, 23);
      contentPane.add(rdbtnGeneral);

      JRadioButton rdbtnInstance = new JRadioButton("Instance");
      rdbtnInstance.setBounds(46, 133, 109, 23);
      contentPane.add(rdbtnInstance);

      ButtonGroup group2 = new ButtonGroup();
      group2.add(rdbtnGeneral);
      group2.add(rdbtnInstance);

      JRadioButton rdbtnDimension = new JRadioButton("Dimension");
      rdbtnDimension.setBounds(46, 187, 109, 23);
      contentPane.add(rdbtnDimension);

      JRadioButton rdbtnIwe = new JRadioButton("I/We");
      rdbtnIwe.setBounds(66, 213, 109, 23);
      contentPane.add(rdbtnIwe);

      JRadioButton rdbtnAnchoredmutable = new JRadioButton("Anchored/Mutable");
      rdbtnAnchoredmutable.setBounds(66, 240, 152, 23);
      contentPane.add(rdbtnAnchoredmutable);

      JRadioButton rdbtnLayer = new JRadioButton("Layer");
      rdbtnLayer.setBounds(46, 317, 109, 23);
      contentPane.add(rdbtnLayer);

      JRadioButton rdbtnOther = new JRadioButton("Other:");
      rdbtnOther.setBounds(66, 266, 109, 23);
      contentPane.add(rdbtnOther);

      ButtonGroup group3 = new ButtonGroup();
      group3.add(rdbtnDimension);
      group3.add(rdbtnLayer);

      ButtonGroup group4 = new ButtonGroup();
      group4.add(rdbtnIwe);
      group4.add(rdbtnAnchoredmutable);
      group4.add(rdbtnOther);

      JRadioButton[] rdbtnList = { rdbtnPerson, rdbtnSurvey, rdbtnGeneral,
            rdbtnInstance, rdbtnQuestion, rdbtnDimension, rdbtnIwe,
            rdbtnAnchoredmutable, rdbtnOther, rdbtnLayer };

      JRadioButton[] rdbtnList1 = { rdbtnPerson, rdbtnSurvey, rdbtnQuestion };
      JRadioButton[] rdbtnList2 = { rdbtnGeneral, rdbtnInstance };
      JRadioButton[] rdbtnList3 = { rdbtnDimension, rdbtnLayer };
      JRadioButton[] rdbtnList4 = { rdbtnIwe, rdbtnAnchoredmutable, rdbtnOther };

      textField = new JTextField();
      textField.setBounds(76, 289, 142, 27);
      contentPane.add(textField);
      textField.setColumns(10);

      JButton btnCancel = new JButton("Cancel");
      btnCancel.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
            group1.clearSelection();
            group2.clearSelection();
            group3.clearSelection();
            group4.clearSelection();
            for (int i = 0; i < rdbtnList.length; i++)
            {
               rdbtnList[i].setEnabled(true);

            }

            textField.setText("");
         }
      });
      btnCancel.setBounds(25, 347, 89, 23);
      contentPane.add(btnCancel);

      JButton btnConfirm = new JButton("Confirm");
      btnConfirm.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
         }
      });
      btnConfirm.setBounds(129, 347, 89, 23);
      contentPane.add(btnConfirm);

      ActionListener listener1 = new ActionListener()
      {
         @Override
         public void actionPerformed(ActionEvent e)

         {

            rdbtnSurvey.setEnabled(false);
            rdbtnGeneral.setEnabled(false);
            rdbtnInstance.setEnabled(false);

            rdbtnQuestion.setEnabled(false);
            ;
            rdbtnDimension.setEnabled(false);
            rdbtnIwe.setEnabled(false);
            rdbtnAnchoredmutable.setEnabled(false);
            rdbtnOther.setEnabled(false);
            textField.setEnabled(false);

            rdbtnLayer.setEnabled(false);
         }
      };

      ActionListener listener2 = new ActionListener()
      {
         @Override
         public void actionPerformed(ActionEvent e)

         {
            rdbtnPerson.setEnabled(false);
            rdbtnSurvey.setEnabled(false);
            rdbtnGeneral.setEnabled(false);
            rdbtnInstance.setEnabled(false);

         }
      };

      ActionListener listener3 = new ActionListener()
      {
         @Override
         public void actionPerformed(ActionEvent e)

         {
            rdbtnPerson.setEnabled(false);

            rdbtnQuestion.setEnabled(false);
            ;
            rdbtnDimension.setEnabled(false);
            rdbtnIwe.setEnabled(false);
            rdbtnAnchoredmutable.setEnabled(false);
            rdbtnOther.setEnabled(false);
            textField.setEnabled(false);

            rdbtnLayer.setEnabled(false);
         }
      };

      ActionListener listener4 = new ActionListener()
      {
         @Override
         public void actionPerformed(ActionEvent e)

         {
            rdbtnPerson.setEnabled(false);

            rdbtnQuestion.setEnabled(false);

            rdbtnDimension.setEnabled(false);
            rdbtnIwe.setEnabled(false);
            rdbtnAnchoredmutable.setEnabled(false);
            rdbtnOther.setEnabled(false);
            textField.setEnabled(false);

            rdbtnLayer.setEnabled(false);
         }
      };

      ActionListener listener5 = new ActionListener()
      {
         @Override
         public void actionPerformed(ActionEvent e)

         {
            rdbtnPerson.setEnabled(false);
            rdbtnSurvey.setEnabled(false);
            rdbtnGeneral.setEnabled(false);
            rdbtnInstance.setEnabled(false);

            rdbtnDimension.setEnabled(true);
            rdbtnIwe.setEnabled(true);
            rdbtnAnchoredmutable.setEnabled(true);
            rdbtnOther.setEnabled(true);
            textField.setEnabled(true);

         }
      };
      ActionListener listener6 = new ActionListener()
      {
         @Override
         public void actionPerformed(ActionEvent e)

         {
            rdbtnPerson.setEnabled(false);

            rdbtnInstance.setEnabled(false);
            rdbtnSurvey.setEnabled(false);
            rdbtnGeneral.setEnabled(false);

            rdbtnQuestion.setEnabled(false);

            group4.clearSelection();

            rdbtnIwe.setEnabled(false);
            rdbtnAnchoredmutable.setEnabled(false);
            rdbtnOther.setEnabled(false);
            textField.setEnabled(false);

         }
      };

      ActionListener listener7 = new ActionListener()
      {
         @Override
         public void actionPerformed(ActionEvent e)

         {
            rdbtnPerson.setEnabled(false);
            rdbtnSurvey.setEnabled(false);
            rdbtnGeneral.setEnabled(false);
            rdbtnInstance.setEnabled(false);

         }
      };

      rdbtnPerson.addActionListener(listener1);
      rdbtnQuestion.addActionListener(listener2);
      rdbtnSurvey.addActionListener(listener3);

      rdbtnSurvey.addActionListener(listener4);
      rdbtnGeneral.addActionListener(listener4);

      rdbtnDimension.addActionListener(listener5);
      rdbtnLayer.addActionListener(listener6);

      rdbtnIwe.addActionListener(listener7);
      rdbtnAnchoredmutable.addActionListener(listener7);
      rdbtnOther.addActionListener(listener7);

   }

}
