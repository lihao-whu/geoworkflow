package lmars218.taskmodel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class ChooseServiceDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JList listTask;
	private CreateSimpleTaskDialog parentDialog;
	private JTextField name;
	private JTextField url;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args, CreateSimpleTaskDialog parent,TaskLib ftasklib,JTextField serviceName,JTextField serviceUrl) {
		try {
			ChooseServiceDialog dialog = new ChooseServiceDialog(parent,ftasklib,serviceName,serviceUrl);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ChooseServiceDialog(CreateSimpleTaskDialog parent,TaskLib ftasklib,JTextField serviceName,JTextField serviceUrl) {
		 
		parentDialog = parent;
		name = serviceName;
		url = serviceUrl;
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		FlowLayout fl_contentPanel = new FlowLayout();
		fl_contentPanel.setAlignment(FlowLayout.LEFT);
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setLayout(fl_contentPanel);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			listTask = new JList();
			listTask.setSize(200,300);
			listTask.setVisibleRowCount(10);
			listTask.setModel(ftasklib.getServiceListModel());
			listTask.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			contentPanel.add(listTask);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						System.out.println("service");
						//add service name and url
						  if(!listTask.isSelectionEmpty())
						    {			    	 
						    	 Service targetTask = (Service) listTask.getSelectedValue();
						    	 if (targetTask == null) {
					                    return;
					                }
					                 
//						    	 System.out.println("Target: "+targetTask.getUrl());
					             String sname = String.valueOf(listTask.getSelectedValue().toString() );
					             String surl = targetTask.getUrl();
//								 textUrl.setToolTipText(url);	
								 url.setText(surl);
								 name.setText(sname);
								 dispose();
								 
						    }else{
						    	System.out.println("未选中list项");
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
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
