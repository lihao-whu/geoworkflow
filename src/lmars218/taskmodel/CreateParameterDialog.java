package lmars218.taskmodel;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Component;

import javax.swing.Box;

import java.awt.TextField;
import java.awt.Dimension;

import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextField;
import javax.swing.JList;

import com.hp.hpl.jena.ontology.Individual;

public class CreateParameterDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextArea textDescription;
	private JTextField textName;
//	private String parameterName;
//	private String parameterDescription;
	private DefaultListModel<Parameter> parameterlistodel;
	private TaskLib tasklib;
	private JList<Concept> conceptlist;
    private DefaultListModel<Concept> conceptmodel;
	private static CreateParameterDialog dialog;
		/**
	 
	 * Launch the application.
	 */
	public static void main(String[] args,TaskLib lib,DefaultListModel<Parameter> listParaModel) {
		try {
			UIManager.setLookAndFeel(UIManager
                    .getSystemLookAndFeelClassName());
			dialog = new CreateParameterDialog(lib,listParaModel);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
			dialog.setVisible(true);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CreateParameterDialog(TaskLib lib,DefaultListModel<Parameter> listParaModel) {
		parameterlistodel = listParaModel;
		tasklib = lib;
		 
		setTitle("Create Parameter");
		setBounds(100, 100, 400, 380);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBounds(7, 7, 46, 25);
			contentPanel.add(panel);
			{
				JLabel lblName = new JLabel("*Name:");
				panel.add(lblName);
			}
		}
		{
			textName = new JTextField();
			textName.setBounds(58, 9, 250, 21);
			contentPanel.add(textName);
			textName.setColumns(20);
		}
		{
			JLabel lblDescription = new JLabel("  Description:");
			lblDescription.setBounds(7, 42, 84, 15);
			contentPanel.add(lblDescription);
		}
		{
			textDescription = new JTextArea();
			textDescription.setBounds(57, 62, 251, 60);
			textDescription.setColumns(20);
			textDescription.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			textDescription.setPreferredSize(new Dimension(140, 60));
			contentPanel.add(textDescription);
		}
		
		JButton btnAddConcept = new JButton("Add Concept");
		
		btnAddConcept.setBounds(10, 147, 108, 23);
		contentPanel.add(btnAddConcept);
		
		final JList<Concept> list = new JList();
		list.setBounds(52, 180, 256, 100);
		contentPanel.add(list);
		conceptmodel = new DefaultListModel<Concept>();
		btnAddConcept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("add concpt");
//				conceptlist = new JList();
				
				list.setModel(conceptmodel);
				ChooseDomainConceptDialog choseDomain = new ChooseDomainConceptDialog(tasklib,conceptmodel);
				choseDomain.setVisible(true);
				
			}
		});
		
		
		
		
 
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					@SuppressWarnings("static-access")
					public void actionPerformed(ActionEvent arg0) {
						if(textName.getText()!=null && list.getModel().getSize()!=-1)
						{
//							parameterName=textName.getText();
//							parameterDescription=textDescription.getText();
							//create parameter individual
							
							Individual indual = tasklib.createParameter(textName.getText(), textDescription.getText(), conceptmodel);
							//add parameter to the abstract Task
							Parameter newparameter = new Parameter();
							newparameter.setIndividual(indual);
							parameterlistodel.addElement(newparameter);
							
							
							dispose();
						}
						
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
	}

	public void getlistmodel()
	{
	
	}
}
