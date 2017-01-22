package lmars218.taskmodel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.border.EtchedBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.hp.hpl.jena.ontology.Individual;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CreateSubtaskDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textUrl;
	private JTable tableParameter;
	private JList<AbstractTask> subtasklist;
	private TaskLib tasklib;
	private static CreateSubtaskDialog dialog;
	 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args,TaskLib ftasklib,JList listsubtask) {
		try {
			dialog = new CreateSubtaskDialog(ftasklib,listsubtask);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CreateSubtaskDialog(TaskLib ftasklib,JList<AbstractTask> listsubtask) {
		subtasklist = listsubtask;
		tasklib = ftasklib;
		setTitle("Create Subtask");
		setBounds(100, 100, 400, 362);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblTheAbstracttaskTo = new JLabel("The AbstractTask to add as the type of the Subtask:");
			lblTheAbstracttaskTo.setBounds(10, 10, 364, 15);
			contentPanel.add(lblTheAbstracttaskTo);
		}
		{
			JLabel lblUrl = new JLabel("Utl:");
			lblUrl.setBounds(20, 35, 29, 15);
			contentPanel.add(lblUrl);
		}
		{
			textUrl = new JTextField();
			textUrl.setEditable(false);
			textUrl.setBounds(45, 32, 254, 21);
			contentPanel.add(textUrl);
			textUrl.setColumns(10);
		}
		{
			JButton btnAdd = new JButton("Add");
			btnAdd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					// add subtask
					 ChooseTaskDialog choseAbstract = new ChooseTaskDialog(dialog,tasklib,textUrl);
					 choseAbstract.setVisible(true);
					
				}
			});
			btnAdd.setBounds(306, 31, 68, 23);
			contentPanel.add(btnAdd);
		}
		{
			JLabel lblParameters = new JLabel("Parameters:");
			lblParameters.setBounds(10, 74, 90, 15);
			contentPanel.add(lblParameters);
		}
		{
			JLabel lblDefineTheParameters = new JLabel("Define the parameters in a subtask as this follow\r\n tabel\r\n");
			lblDefineTheParameters.setBounds(20, 84, 354, 32);
			contentPanel.add(lblDefineTheParameters);
		}
		
		tableParameter = new JTable();
		tableParameter.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		tableParameter.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				" ", " "
			}
		) {
			Class[] columnTypes = new Class[] {
				Object.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tableParameter.setBounds(45, 126, 254, 126);
		contentPanel.add(tableParameter);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						//add subtask to the subtasklist
						AbstractTask currentTask = new AbstractTask(tasklib.getIndividualfromUrl(textUrl.getText()));
						DefaultListModel model = (DefaultListModel) subtasklist.getModel();
						model.addElement(currentTask);
						dispose();

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
}
