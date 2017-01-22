package lmars218.taskmodel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;

import java.awt.Insets;
import java.awt.Component;

import javax.swing.Box;
import javax.swing.JList;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

import javax.swing.border.LineBorder;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntModel;

import java.awt.SystemColor;

public class CreateSimpleTaskDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textName;
	private JTextArea textDescription;
	private JTextField textUrl;
	private JTextField textServiceName;
	private JTextField textServiceUrl;
	private static CreateSimpleTaskDialog dialog;
	private TaskLib tasklib;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args,Frame parent,TaskLib ftasklib) {
		try {
			UIManager.setLookAndFeel(UIManager
                    .getSystemLookAndFeelClassName());
			dialog = new CreateSimpleTaskDialog(parent,ftasklib);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CreateSimpleTaskDialog(Frame parent,TaskLib ftasklib) {
		tasklib = ftasklib;
		setTitle("Create SimpleTask");
		setBounds(100, 100, 700, 500);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			panel.setBounds(10, 10, 310, 105);
			contentPanel.add(panel);
			panel.setLayout(null);
			{
				JLabel lblNewLabel = new JLabel("*Name: ");
				lblNewLabel.setBounds(10, 10, 54, 15);
				panel.add(lblNewLabel);
			}
			{
				textName = new JTextField();
				textName.setBounds(52, 7, 134, 21);
				panel.add(textName);
				textName.setColumns(10);
			}
			{
				JLabel lblDescription = new JLabel("Description:");
				lblDescription.setBounds(16, 35, 82, 15);
				panel.add(lblDescription);
			}
			{
				textDescription =new JTextArea();
				textDescription.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				textDescription.setBounds(52, 60, 196, 43);
				panel.add(textDescription);
			}
		}
		{
			JPanel panel = new JPanel();
			panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			panel.setBounds(10, 117, 310, 169);
			contentPanel.add(panel);
			panel.setLayout(null);
			{
				JLabel lblNewLabel_1 = new JLabel("*Implement of  AbstractTask:");
				lblNewLabel_1.setBounds(10, 10, 209, 15);
				panel.add(lblNewLabel_1);
			}
			{
				JLabel lblUrl = new JLabel("Url:");
				lblUrl.setBounds(20, 35, 24, 15);
				panel.add(lblUrl);
			}
			{
				textUrl = new JTextField();
//			    textUrl.setBorder(new LineBorder(SystemColor.activeCaptionBorder));
				textUrl.setBounds(50, 32, 195, 21);
				panel.add(textUrl);
		 
			}
			{
				JButton btnAdd = new JButton("Add");
				btnAdd.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						//add the abstractUrl to implement
						 ChooseTaskDialog choseAbstract = new ChooseTaskDialog(dialog,tasklib,textUrl);
						 choseAbstract.setVisible(true);
						
	
						
						
					}
				});
				btnAdd.setMargin(new Insets(2, 2, 2, 2));
				btnAdd.setBounds(252, 31, 48, 23);
				panel.add(btnAdd);
			}
			{
				JLabel lblParameters = new JLabel("parameters:");
				lblParameters.setBounds(20, 60, 118, 15);
				panel.add(lblParameters);
			}
			{
				JList listParameter = new JList();
				listParameter.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				listParameter.setBounds(80, 85, 165, 74);
				panel.add(listParameter);
			}
		}
		{
			JPanel panel = new JPanel();
			panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			panel.setBounds(10, 287, 310, 132);
			contentPanel.add(panel);
			panel.setLayout(null);
			{
				JLabel lblNewLabel_2 = new JLabel("*invoke Service:");
				lblNewLabel_2.setBounds(7, 7, 166, 15);
				panel.add(lblNewLabel_2);
			}
			{
				JLabel lblName = new JLabel("Name:");
				lblName.setBounds(17, 32, 36, 15);
				panel.add(lblName);
			}
			{
				JLabel lblUrl_1 = new JLabel("Url:");
				lblUrl_1.setBounds(17, 35, 36, 65);
				panel.add(lblUrl_1);
			}
			{
				textServiceName = new JTextField();
				textServiceName.setBounds(52, 29, 121, 21);
				panel.add(textServiceName);
				textServiceName.setColumns(10);
			}
			{
				textServiceUrl = new JTextField();
				textServiceUrl.setEditable(true);
				textServiceUrl.setBounds(52, 57, 192, 21);
				panel.add(textServiceUrl);
				textServiceUrl.setColumns(10);
			}
			{
				JButton btnAdd_1 = new JButton("Add");
				btnAdd_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						ChooseServiceDialog choseService = new ChooseServiceDialog(dialog, tasklib, textServiceName, textServiceUrl);
						choseService.setVisible(true);
					}
				});
				btnAdd_1.setMargin(new Insets(2, 2, 2, 2));
				btnAdd_1.setBounds(248, 56, 41, 23);
				panel.add(btnAdd_1);
			}
		}
		{
			JPanel panel = new JPanel();
			panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			panel.setBounds(330, 10, 344, 138);
			contentPanel.add(panel);
			panel.setLayout(null);
			{
				JLabel lblprecondition = new JLabel("*Precondition\uFF1A");
				lblprecondition.setBounds(10, 10, 94, 15);
				panel.add(lblprecondition);
			}
		}
		{
			JPanel panel = new JPanel();
			panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			panel.setBounds(330, 153, 344, 132);
			contentPanel.add(panel);
			panel.setLayout(null);
			{
				JLabel lbleffect = new JLabel("*Effect\uFF1A");
				lbleffect.setBounds(10, 10, 54, 15);
				panel.add(lbleffect);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						Individual abstractTaskINdividual = tasklib.getIndividualfromUrl(textUrl.getText());
						Individual serviceIndividual = tasklib.getIndividualfromUrl(textServiceUrl.getText());
						tasklib.createSimpleTask(textName.getText(),textDescription.getText(), abstractTaskINdividual,serviceIndividual);
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			
			Component horizontalStrut = Box.createHorizontalStrut(40);
			buttonPane.add(horizontalStrut);
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						dispose();
					}
				});
				buttonPane.add(cancelButton);
			}
		}
	}
	
	
 
}
