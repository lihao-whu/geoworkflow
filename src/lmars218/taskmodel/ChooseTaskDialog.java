package lmars218.taskmodel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Color;

public class ChooseTaskDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	 private JList listTask;
	 private CreateSimpleTaskDialog parentDialog;
	 private JTextField textUrl;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args, JDialog parent,TaskLib ftasklib,JTextField ftextUrl) {
		try {
			ChooseTaskDialog dialog = new ChooseTaskDialog(parent,ftasklib,ftextUrl);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ChooseTaskDialog(JDialog parent,TaskLib ftasklib,JTextField ftextUrl) {
		parentDialog = (CreateSimpleTaskDialog) parent;
		textUrl=ftextUrl;
		setBounds(100, 100, 400, 300);
		getContentPane().setLayout(new BorderLayout());
		FlowLayout fl_contentPanel = new FlowLayout();
		fl_contentPanel.setAlignment(FlowLayout.LEFT);
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setLayout(fl_contentPanel);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			listTask= new JList();
			listTask.setSize(200,300);
			listTask.setVisibleRowCount(10);
			listTask.setModel(ftasklib.getAbstractTasksListModel());
			listTask.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			contentPanel.add(listTask);
		}
 
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addMouseListener(new  MouseListener() {
	
					@Override
					public void mouseClicked(MouseEvent arg0) {
						// TODO Auto-generated method stub
//					    System.out.println("+++++++++++++++++++++");
					    if(!listTask.isSelectionEmpty())
					    {			    	 
					    	 AbstractTask targetTask = (AbstractTask) listTask.getSelectedValue();
					    	 if (targetTask == null) {
				                    return;
				                }
				                 
//					    	 System.out.println("Target: "+targetTask.getUrl());
				             String name = String.valueOf(listTask.getSelectedValue().toString() );
				             String url = targetTask.getUrl();
//							 textUrl.setToolTipText(url);	
							 textUrl.setText(url);
							 dispose();
							 
					    }else{
					    	System.out.println("未选中list项");
					    }
					   
					}

					@Override
					public void mouseEntered(MouseEvent arg0) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mouseExited(MouseEvent arg0) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mousePressed(MouseEvent arg0) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mouseReleased(MouseEvent arg0) {
						// TODO Auto-generated method stub
						
					}
				});
 
				okButton.setHorizontalAlignment(SwingConstants.LEFT);
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
