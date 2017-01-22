package lmars218.taskmodel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JEditorPane;

import java.awt.Component;

import javax.swing.Box;

import java.awt.Frame;
import java.awt.TextField;
import java.awt.Rectangle;
import java.awt.Dimension;

import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JTextField;

import java.awt.TextArea;

import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntModel;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Window.Type;

public class CreateAbstractTaskDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextArea textDescription;
	private JTextField textName;
	private JList listConcept ;
	private JList<Parameter> listParameter;
	private static CreateAbstractTaskDialog dialog;
	private TaskLib tasklib;
	private MainWindow parentWindow;
    private DefaultListModel<Parameter> listParameterModel;
	private String[][] propertyName;
	private JPanel panel_2;
	private JLabel lblname;
	private JLabel lblDescription;
	private JPanel buttonPane;
	private JButton okButton;
	private JButton cancelButton;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args,MainWindow parent,TaskLib ftasklib) {
		try {
//			String lookAndFeel = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
//			UIManager.setLookAndFeel(lookAndFeel);
			UIManager.setLookAndFeel(UIManager
                    .getSystemLookAndFeelClassName());
			dialog = new CreateAbstractTaskDialog(parent,ftasklib);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CreateAbstractTaskDialog(MainWindow parent,TaskLib ftasklib) {
		tasklib=ftasklib;
		parentWindow = parent;
		setSize(new Dimension(360, 401));
		setMaximumSize(new Dimension(360, 420));
		setMinimumSize(new Dimension(360, 420));
		setPreferredSize(new Dimension(360, 420));
		setTitle("Create AbstractTask");
		setBounds(100, 100, 360, 516);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setSize(new Dimension(360, 401));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			panel_2 = new JPanel();
			panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			panel_2.setBounds(6, 6, 328, 106);
			contentPanel.add(panel_2);
			panel_2.setLayout(null);
			{
				lblname = new JLabel("*Name: ");
				lblname.setBounds(7, 10, 42, 15);
				lblname.setHorizontalAlignment(SwingConstants.LEFT);
				panel_2.add(lblname);
			}
			
			textName = new JTextField("",30);
			textName.setBounds(54, 7, 186, 21);
			panel_2.add(textName);
			{
				lblDescription = new JLabel(" Description: ");
				lblDescription.setBounds(7, 33, 84, 15);
				panel_2.add(lblDescription);
			}
			
			textDescription = new JTextArea(2,30);
			textDescription.setBounds(54, 53, 214, 44);
			textDescription.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			panel_2.add(textDescription);
		}
//		{
//			JPanel panel = new JPanel();
//			panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
//			panel.setBounds(6, 117, 328, 148);
//			contentPanel.add(panel);
//			panel.setLayout(null);
//			{
//				JLabel lblconcepts = new JLabel("*Concepts: ");
//				lblconcepts.setBounds(2, 2, 66, 15);
//				panel.add(lblconcepts);
//			}
//			
//			listConcept = new JList();
//			listConcept.setBounds(86, 17, 100, 120);
//			listConcept.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
//			listConcept.setPreferredSize(new Dimension(100, 120));
//			listConcept.setModel(new AbstractListModel() {
//				String[] values = new String[] {};
//				public int getSize() {
//					return values.length;
//				}
//				public Object getElementAt(int index) {
//					return values[index];
//				}
//			});
//			panel.add(listConcept);
			
//			JPanel panel_1 = new JPanel();
//			panel_1.setBounds(226, 22, 30, 115);
//			panel.add(panel_1);
//			panel_1.setLayout(new GridLayout(5, 1, 0, 0));
//			
//			JButton btnAddConcept = new JButton("");
//			btnAddConcept.setToolTipText("Add domain concepts");
//			btnAddConcept.setIcon(new ImageIcon(CreateAbstractTaskDialog.class.getResource("/lmars218/taskmodel/resources/android-add.png")));
//			panel_1.add(btnAddConcept);
//			
//			JButton btnDeletConcept = new JButton("");
//			btnDeletConcept.setToolTipText("Delete Domain Concepts");
//			btnDeletConcept.setIcon(new ImageIcon(CreateAbstractTaskDialog.class.getResource("/lmars218/taskmodel/resources/android-remove.png")));
//			panel_1.add(btnDeletConcept);
//		}
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(6, 122, 328, 162);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblparameters = new JLabel("*Parameters: ");
		lblparameters.setBounds(7, 7, 78, 15);
		panel.add(lblparameters);
		
		listParameter = new JList();
		listParameter.setBounds(87, 34, 100, 120);
		listParameter.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		listParameter.setPreferredSize(new Dimension(100, 120));
		listParameterModel = new DefaultListModel<Parameter>();
		listParameter.setModel(listParameterModel);
		panel.add(listParameter);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(224, 34, 29, 115);
		panel.add(panel_1);
		panel_1.setLayout(new GridLayout(5, 1, 0, 0));
		
		JButton btnParameterAdd = new JButton("");
		btnParameterAdd.setToolTipText("Add Parameters");
		btnParameterAdd.setIcon(new ImageIcon(CreateAbstractTaskDialog.class.getResource("/lmars218/taskmodel/resources/android-add.png")));
		btnParameterAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { 
//				String name = null;
//				String description = null;
//				Parameter para = new Parameter(); 
				CreateParameterDialog cparaDialog=new CreateParameterDialog(tasklib,listParameterModel);//create paramer and add parameter to de parameterlist of the abstractTask
				cparaDialog.setVisible(true);
			 
 
			}
		});
		panel_1.add(btnParameterAdd);
		
		JButton btnParameterDelete = new JButton("");
		btnParameterDelete.setToolTipText("Delete Parameters");
		btnParameterDelete.setIcon(new ImageIcon(CreateAbstractTaskDialog.class.getResource("/lmars218/taskmodel/resources/android-remove.png")));
		panel_1.add(btnParameterDelete);
		
		JButton btnParameterUp = new JButton("");
		btnParameterUp.setToolTipText("Move Up");
		btnParameterUp.setIcon(new ImageIcon(CreateAbstractTaskDialog.class.getResource("/lmars218/taskmodel/resources/arrow-up-a.png")));
		panel_1.add(btnParameterUp);
		
		JButton btnParameterDown = new JButton("");
		btnParameterDown.setToolTipText("Move Down");
		btnParameterDown.setIcon(new ImageIcon(CreateAbstractTaskDialog.class.getResource("/lmars218/taskmodel/resources/arrow-down-a.png")));
		panel_1.add(btnParameterDown);
		{
			buttonPane = new JPanel();
			buttonPane.setBounds(0, 297, 344, 33);
			contentPanel.add(buttonPane);
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			{
				okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//OK..	
						 System.out.println("---------------------");
						 tasklib.createAbstractTask(textDescription.getText(), textName.getText(), listParameterModel);
						 tasklib.refresh();
						 parentWindow.refreshAbstract();
						 dispose();
 
					}
				});
				okButton.setHorizontalAlignment(SwingConstants.LEFT);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
                          dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{getContentPane(), contentPanel, panel_2, lblname, textName, lblDescription, textDescription, panel, lblparameters, listParameter, panel_1, btnParameterAdd, btnParameterDelete, btnParameterUp, btnParameterDown, buttonPane, okButton, cancelButton}));
	}

 
   
   public JList getParameterlist()
   {
	   return listParameter;
   }

}
