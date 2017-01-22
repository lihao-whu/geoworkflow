package lmars218.taskmodel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.TextField;
import java.awt.Component;

import javax.swing.Box;

import java.awt.Dimension;

import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;
import javax.swing.JTextField;
import javax.swing.JList;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;

import org.apache.jena.atlas.io.IndentedLineBuffer;

import com.hp.hpl.jena.ontology.Individual;

import java.awt.Insets;

public class CreateComplexTaskDialog<E> extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textName;
	private JTextField textImplementUrl;
	private static CreateComplexTaskDialog dialog;
	private TaskLib tasklib;

	private JList<AbstractTask> listSubtask;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args,Frame parent,TaskLib ftasklib) {
		try {
			UIManager.setLookAndFeel(UIManager
                    .getSystemLookAndFeelClassName());
			dialog = new CreateComplexTaskDialog(parent,ftasklib);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CreateComplexTaskDialog(Frame parent,TaskLib ftasklib) {
		tasklib = ftasklib;
		setTitle("Create ComplexTask");
		setBounds(100, 100, 700, 498);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBounds(10, 10, 329, 407);
			contentPanel.add(panel);
			panel.setLayout(null);
			
			JPanel panel_1 = new JPanel();
			panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			panel_1.setBounds(0, 0, 329, 98);
			panel.add(panel_1);
			panel_1.setLayout(null);
			
			JLabel lblname = new JLabel("*Name: ");
			lblname.setBounds(7, 9, 42, 15);
			panel_1.add(lblname);
			
			textName = new JTextField();
			textName.setLocation(54, 7);
			textName.setSize(new Dimension(126, 20));
			textName.setPreferredSize(new Dimension(120, 20));
			panel_1.add(textName);
			textName.setColumns(20);
			
			JLabel lblDescription = new JLabel(" Description:");
			lblDescription.setBounds(7, 32, 78, 15);
			panel_1.add(lblDescription);
			
			JTextArea textDescription = new JTextArea();
			textDescription.setBounds(54, 52, 214, 40);
			textDescription.setColumns(30);
			textDescription.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			textDescription.setPreferredSize(new Dimension(160, 40));
			panel_1.add(textDescription);
			
			JPanel panel_2 = new JPanel();
			panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			panel_2.setBounds(0, 102, 329, 147);
			panel.add(panel_2);
			panel_2.setLayout(null);
			
			JLabel lblImplementOf = new JLabel("* Implement of AbstractTask:");
			lblImplementOf.setBounds(7, 7, 168, 15);
			panel_2.add(lblImplementOf);
			
			JLabel lblUrl = new JLabel("url:");
			lblUrl.setBounds(32, 31, 24, 15);
			panel_2.add(lblUrl);
			
			textImplementUrl = new JTextField();
			textImplementUrl.setBounds(61, 28, 204, 21);
//			textImplementUrl.setEditable(false);
			panel_2.add(textImplementUrl);
			textImplementUrl.setColumns(33);
			
			JButton btnAdd = new JButton("Add");
			btnAdd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					 ChooseTaskDialog choseAbstract = new ChooseTaskDialog(dialog,tasklib,textImplementUrl);
					 choseAbstract.setVisible(true);
					
					
				}
			});
			btnAdd.setBounds(270, 27, 51, 23);
			panel_2.add(btnAdd);
			
			JLabel lblParameters = new JLabel("parameters:");
			lblParameters.setBounds(32, 55, 66, 15);
			panel_2.add(lblParameters);
			
			JList listExParameter = new JList();
			listExParameter.setBounds(102, 75, 163, 60);
			listExParameter.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			listExParameter.setPreferredSize(new Dimension(163, 60));
			panel_2.add(listExParameter);
			
			JPanel panel_3 = new JPanel();
			panel_3.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			panel_3.setBounds(0, 254, 329, 153);
			panel.add(panel_3);
			panel_3.setLayout(null);
			
			JLabel lblsubtask = new JLabel("*SubTask");
			lblsubtask.setBounds(7, 7, 48, 15);
			panel_3.add(lblsubtask);
			
			listSubtask = new JList();
			listSubtask.setBounds(22, 27, 90, 120);
			listSubtask.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			listSubtask.setPreferredSize(new Dimension(90, 120));
			listSubtask.setModel(new DefaultListModel());
		
//			listSubtask.addAncestorListener(new Ac);
			
			
			panel_3.add(listSubtask);
			
			JPanel panel_4 = new JPanel();
			panel_4.setLocation(117, 27);
			panel_4.setPreferredSize(new Dimension(30, 120));
			panel_4.setSize(new Dimension(30, 120));
			panel_3.add(panel_4);
			panel_4.setLayout(new GridLayout(5, 1, 0, 0));
			
			JButton btnSubtaskAdd = new JButton("");
			btnSubtaskAdd.setToolTipText("Add Subtask");
			btnSubtaskAdd.setSize(new Dimension(14, 0));
			btnSubtaskAdd.setMargin(new Insets(2, 2, 2, 2));
			btnSubtaskAdd.setMinimumSize(new Dimension(0, 23));
			btnSubtaskAdd.setPreferredSize(new Dimension(17, 9));
			btnSubtaskAdd.setIcon(new ImageIcon(CreateComplexTaskDialog.class.getResource("/lmars218/taskmodel/resources/android-add.png")));
			btnSubtaskAdd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//add subtask
					CreateSubtaskDialog subtaskDialog = new CreateSubtaskDialog(tasklib, listSubtask);
					subtaskDialog.setVisible(true);
					
				}
			});
			panel_4.add(btnSubtaskAdd);
			
			JButton btnSubtaskDelete = new JButton("");
			btnSubtaskDelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					if(listSubtask.getSelectedValue()!= null)
					{
						
							DefaultListModel model = (DefaultListModel) listSubtask.getModel();
							int index =  listSubtask.getSelectedIndex();
							model.removeElementAt(index);
		 
					}
				}
			});
			btnSubtaskDelete.setToolTipText("Delete Subtask");
			btnSubtaskDelete.setIcon(new ImageIcon(CreateComplexTaskDialog.class.getResource("/lmars218/taskmodel/resources/android-remove.png")));
			btnSubtaskDelete.setSize(new Dimension(20, 55));
			panel_4.add(btnSubtaskDelete);
			
			JButton btnSubtaskUp = new JButton("");
			btnSubtaskUp.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					//move up one subtask
					if(listSubtask.getSelectedValue()!= null)
					{
						int index = listSubtask.getSelectedIndex();
						if(index!=0)
						{
							DefaultListModel model = (DefaultListModel) listSubtask.getModel();
							AbstractTask temp1 = (AbstractTask) model.getElementAt(index-1);
							AbstractTask temp2 = (AbstractTask) model.getElementAt(index);
							model.setElementAt(temp2, index-1);
							model.setElementAt(temp1, index);
							listSubtask.setSelectedIndex(index-1);
							
						}
					}
					
				}
			});
			btnSubtaskUp.setToolTipText("Move Up");
			btnSubtaskUp.setIcon(new ImageIcon(CreateComplexTaskDialog.class.getResource("/lmars218/taskmodel/resources/arrow-up-a.png")));
			panel_4.add(btnSubtaskUp);
			
			JButton btnSubtaskDown = new JButton("");
			btnSubtaskDown.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					//move down one subtask
					if(listSubtask.getSelectedValue()!= null)
					{
						int index = listSubtask.getSelectedIndex();
						DefaultListModel model = (DefaultListModel) listSubtask.getModel();
						int number = model.getSize();
						if(index<number-1)
						{
							
							AbstractTask temp1 = (AbstractTask) model.getElementAt(index+1);
							AbstractTask temp2 = (AbstractTask) model.getElementAt(index);
							model.setElementAt(temp2, index+1);
							model.setElementAt(temp1, index);
							listSubtask.setSelectedIndex(index+1);
						}
					}
					
				}
			});
			btnSubtaskDown.setToolTipText("Move Down");
			btnSubtaskDown.setIcon(new ImageIcon(CreateComplexTaskDialog.class.getResource("/lmars218/taskmodel/resources/arrow-down-a.png")));
			panel_4.add(btnSubtaskDown);
			
			JPanel panel_5 = new JPanel();
			panel_5.setBounds(167, 27, 150, 120);
			panel_5.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			FlowLayout flowLayout_3 = (FlowLayout) panel_5.getLayout();
			flowLayout_3.setAlignment(FlowLayout.LEFT);
			panel_5.setPreferredSize(new Dimension(150, 120));
			panel_3.add(panel_5);
			
			JLabel lblIntrinsicParameters = new JLabel("intrinsic parameters:");
			panel_5.add(lblIntrinsicParameters);
			
			JList listInParameter = new JList();
			listInParameter.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			panel_5.add(listInParameter);
			listInParameter.setPreferredSize(new Dimension(90, 90));
			
			JPanel panel_6 = new JPanel();
			panel_6.setPreferredSize(new Dimension(30, 90));
			panel_5.add(panel_6);
			panel_6.setLayout(new GridLayout(4, 1, 0, 0));
			
			JButton btnInParameterAdd = new JButton("");
			btnInParameterAdd.setToolTipText("Add Parameter");
			btnInParameterAdd.setIcon(new ImageIcon(CreateComplexTaskDialog.class.getResource("/lmars218/taskmodel/resources/android-add.png")));
			panel_6.add(btnInParameterAdd);
			
			JButton btnInParameterDelete = new JButton("");
			btnInParameterDelete.setToolTipText("Delete Parameter");
			btnInParameterDelete.setIcon(new ImageIcon(CreateComplexTaskDialog.class.getResource("/lmars218/taskmodel/resources/android-remove.png")));
			panel_6.add(btnInParameterDelete);
		}
		{
			JPanel panel = new JPanel();
			panel.setBounds(344, 10, 330, 407);
			contentPanel.add(panel);
			panel.setLayout(null);
			
			JPanel panel_1 = new JPanel();
			panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			panel_1.setBounds(0, 0, 330, 142);
			panel.add(panel_1);
			panel_1.setLayout(null);
			
			JLabel lblprecondition = new JLabel("*Precondition:");
			lblprecondition.setBounds(10, 10, 84, 15);
			panel_1.add(lblprecondition);
			
			JTextArea preQueryString = new JTextArea();
			preQueryString.setBounds(36, 35, 255, 86);
			panel_1.add(preQueryString);
			
			JPanel panel_2 = new JPanel();
			panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			panel_2.setBounds(0, 150, 330, 152);
			panel.add(panel_2);
			panel_2.setLayout(null);
			
			JLabel lbleffect = new JLabel("*Effect:");
			lbleffect.setBounds(7, 7, 48, 15);
			panel_2.add(lbleffect);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					@SuppressWarnings("null")
					public void actionPerformed(ActionEvent arg0) {
						// add complexTask to the owl file
						DefaultListModel<AbstractTask> model = (DefaultListModel) listSubtask.getModel();
						int number = model.getSize();
						Individual[] subtasks = new Individual[number];
						for(int i=0;i<number;i++)
						{
							AbstractTask currentTask = (AbstractTask) model.getElementAt(i);
							try{
								subtasks[i]=currentTask.individual;
							}catch(Exception e){
								System.out.println(e.getMessage());
							}
							
						}
						
						Individual abstractTaskIndividual = tasklib.getIndividualfromUrl(textImplementUrl.getText());
						tasklib.createComplexTask(textName.getText(),abstractTaskIndividual,subtasks, number);
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
				buttonPane.add(cancelButton);
			}
		}
	}
	
	private void createQueryString()
	{
		
	}
}
