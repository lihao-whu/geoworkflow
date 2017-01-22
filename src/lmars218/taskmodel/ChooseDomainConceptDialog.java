package lmars218.taskmodel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.JTable;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ChooseDomainConceptDialog extends JDialog {

 
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private TaskLib tasklib;
	private JList<Concept> conceptList;
	private DefaultTableModel tablemodel;
	private DefaultListModel<Concept> conceptmodel;
	private int row=0;
	private int column=0;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args, TaskLib lib,DefaultListModel<Concept> cptmodel ) {
		try {
			ChooseDomainConceptDialog dialog = new ChooseDomainConceptDialog(lib, cptmodel);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true); 
	   
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ChooseDomainConceptDialog(TaskLib lib,DefaultListModel<Concept> cptmodel ) {// the tasklib to get all individuals of the concept od domain.owl
		                                                          // the cptlist is the  concepts list to add to the parameter;
		tasklib = lib;
		conceptmodel = cptmodel;
		setTitle("Choose Concept");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblChoseTheConcept = new JLabel("chose the concept of the selected parameter");
			lblChoseTheConcept.setBounds(28, 10, 258, 15);
			lblChoseTheConcept.setHorizontalAlignment(SwingConstants.LEFT);
			lblChoseTheConcept.setVerticalAlignment(SwingConstants.TOP);
			contentPanel.add(lblChoseTheConcept);
		}
		
		table = new JTable(); 
		table.setBounds(68, 50, 292, 155);

	
        tablemodel =new DefaultTableModel(null,new String[]{"name","class","comment"}); 
        table = new JTable(tablemodel);
     
//        JList list = new JList();
        DefaultListModel<Concept> listmodel = tasklib.getConcepts();
        conceptList = new JList<Concept>();
        conceptList.setModel(listmodel); 
        
         
        for(int i=0;i<listmodel.getSize();i++){
        	
        	Individual indual= (Individual)listmodel.getElementAt(i).individual;
        	String url = indual.getURI();
        	String comment = indual.getComment(null);
        	OntClass classname = indual.getOntClass();
        	Vector<String> value = new Vector<String>(3);
        	value.addElement(indual.getLocalName());
        	value.addElement(classname.getLocalName());
        	value.addElement(comment);
			tablemodel.addRow(value);
        }
        //tip设置
        table.addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseDragged(MouseEvent arg0) {
				// TODO Auto-generated method stub	
			}
 
			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				 Point point = e.getPoint();
	                int x = table.rowAtPoint(point);
	                int y = table.columnAtPoint(point);
	                if (x != row || y != column) {
	                    row = x;
	                    column = y;
	                }
	                Object tip = table.getValueAt(row, column);

	                if (tip != null) {

	                	table.setToolTipText(tip.toString());

	                }
			}

        });
        
        
        
        
        
        JScrollPane scrollPane = new JScrollPane(table);   //支持滚动
        getContentPane().add(scrollPane,BorderLayout.CENTER);
        //jdk1.6
        //排序:
        //table.setRowSorter(new TableRowSorter(tableModel));
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);  //单选
        scrollPane.setViewportView(table);
	 
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						//add concepts to parameter
						if(table.getSelectedRow()==-1 ){
							JOptionPane.showMessageDialog(null, "Please choose a concept!!");
						}else{
							
							conceptmodel.addElement(conceptList.getModel().getElementAt(table.getSelectedRow()));
							System.out.println(conceptmodel.getSize());
							Concept cpt = conceptmodel.getElementAt(0);
							System.out.println(cpt.getUrl());
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
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
