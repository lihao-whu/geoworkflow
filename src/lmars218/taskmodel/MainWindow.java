package lmars218.taskmodel;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JToolBar;

import java.awt.BorderLayout;

import javax.swing.AbstractButton;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JList;

import java.awt.Component;

import javax.swing.AbstractListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;

import java.awt.FlowLayout;

import javax.swing.border.LineBorder;

import java.awt.Color;

import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.Dimension;
import java.io.File;
import java.util.List;

import javax.swing.JTree;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

import com.hp.hpl.jena.ontology.OntModel;

import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainWindow {

    private JFrame frmGeotask;
    private JTree treeTaskDecomp;
    private JList listAbstractTask;
    private JList listTaskSeq;
    private JList listTaskImpl;
    private JPanel panelTaskDescription;
    private JPanel panelTaskSeq;
    private TaskLib tasklib;
    private JPanel panelTaskImpl;
    private JButton btnOpenTaskLib;
    private JButton btnChooseObjectTask;
    private JButton btnChooseTaskImplementation;
    private JButton btnExportServiceChain;
    private JLabel lblTaskImplChoose;
    private JTextPane txtpnDescription;
 private static MainWindow window;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager
                            .getSystemLookAndFeelClassName());
                    window = new MainWindow();
                    window.frmGeotask.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public MainWindow() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */ 
    private void initialize() {
        frmGeotask = new JFrame();
        frmGeotask.setMinimumSize(new Dimension(800, 600));
        BorderLayout borderLayout = (BorderLayout) frmGeotask.getContentPane()
                .getLayout();
        borderLayout.setVgap(2);
        borderLayout.setHgap(2);
        frmGeotask.setTitle("GeoTask");
        frmGeotask.setBounds(100, 100, 800, 600);
        frmGeotask.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();
        frmGeotask.setJMenuBar(menuBar);

        JMenu mnFile = new JMenu("File");
        menuBar.add(mnFile);

        JMenuItem mntmOpenTaskLib = new JMenuItem("Open Task Library");
        mnFile.add(mntmOpenTaskLib);

        JMenuItem mntmImportDomain = new JMenuItem("Import Domain");
        mntmImportDomain.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		//import model owl
        		  File ontoFile;
                  JFileChooser jfc = new JFileChooser("f:/0106/1223");
                  jfc.setDialogTitle("Import Domain");
                  jfc.setAcceptAllFileFilterUsed(false);
                  FileNameExtensionFilter owl = new FileNameExtensionFilter(
                          "owl File (.owl)", "owl");
                  FileNameExtensionFilter rdf = new FileNameExtensionFilter(
                          "rdf File (.rdf)", "rdf");
                  jfc.addChoosableFileFilter(owl);
                  jfc.addChoosableFileFilter(rdf);
                  jfc.setFileFilter(owl);
                  jfc.showOpenDialog(null);
                  ontoFile = jfc.getSelectedFile();
                  tasklib = new TaskLib();
                  tasklib.importDomain(ontoFile);	
        		
        	}
        });
        mnFile.add(mntmImportDomain);
        
        
        
        
        
        JMenuItem mntmImport = new JMenuItem("Import Model");
        mntmImport.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		//import model owl
        		  File ontoFile;
                  JFileChooser jfc = new JFileChooser("f:/0106/1223");
                  jfc.setDialogTitle("Import Model");
                  jfc.setAcceptAllFileFilterUsed(false);
                  FileNameExtensionFilter owl = new FileNameExtensionFilter(
                          "owl File (.owl)", "owl");
                  FileNameExtensionFilter rdf = new FileNameExtensionFilter(
                          "rdf File (.rdf)", "rdf");
                  jfc.addChoosableFileFilter(owl);
                  jfc.addChoosableFileFilter(rdf);
                  jfc.setFileFilter(owl);
                  jfc.showOpenDialog(null);
                  ontoFile = jfc.getSelectedFile();
//                  tasklib = new TaskLib();
                  tasklib.importTaskModel(ontoFile);	
        		
        	}
        });
        mnFile.add(mntmImport);
        
        JMenu mnCreateTask = new JMenu("Create Task");
        mnFile.add(mnCreateTask);
        
        JMenuItem mntmAbstracttask = new JMenuItem("AbstractTask");
        mntmAbstracttask.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		CreateAbstractTaskDialog abstractDialog=new CreateAbstractTaskDialog(window,tasklib);
        		abstractDialog.setVisible(true);
        		
        	}
        });
        mnCreateTask.add(mntmAbstracttask);
        
        JMenuItem mntmComplextask = new JMenuItem("ComplexTask");
        mntmComplextask.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				CreateComplexTaskDialog complexDialog = new CreateComplexTaskDialog(frmGeotask,tasklib);
				complexDialog.setVisible(true);
			}
		});
        
        mnCreateTask.add(mntmComplextask);
        
        JMenuItem mntmSimpletask = new JMenuItem("SimpleTask"); 
        mntmSimpletask.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				CreateSimpleTaskDialog simpleDialog = new CreateSimpleTaskDialog(frmGeotask,tasklib);
				simpleDialog.setVisible(true);
				
			}
		});
        mnCreateTask.add(mntmSimpletask);

        JMenuItem mntmExport = new JMenuItem("Save Service Chain");
        mnFile.add(mntmExport);

        JMenu mnSearch = new JMenu("Search");
        menuBar.add(mnSearch);

        JMenuItem mntmSearchAbstractTask = new JMenuItem("Search Abstract Task");
        mnSearch.add(mntmSearchAbstractTask);

        JMenuItem mntmSearchTaskImplementation = new JMenuItem(
                "Search Task Implementation");
        mnSearch.add(mntmSearchTaskImplementation);

        JMenu mnHelp = new JMenu("Help");
        menuBar.add(mnHelp);

        JMenuItem mntmAbout = new JMenuItem("About");
        mnHelp.add(mntmAbout);

        JToolBar toolBar = new JToolBar();
        frmGeotask.getContentPane().add(toolBar, BorderLayout.NORTH);

        btnOpenTaskLib = new JButton("");
        btnOpenTaskLib.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // File ontoFile = new File("d:/task.owl");
                File ontoFile;
                JFileChooser jfc = new JFileChooser("f:/0106/1223");
                jfc.setDialogTitle("Import tasks");
                jfc.setAcceptAllFileFilterUsed(false);
                FileNameExtensionFilter owl = new FileNameExtensionFilter(
                        "owl File (.owl)", "owl");
                FileNameExtensionFilter rdf = new FileNameExtensionFilter(
                        "rdf File (.rdf)", "rdf");
                jfc.addChoosableFileFilter(owl);
                jfc.addChoosableFileFilter(rdf);
                jfc.setFileFilter(owl);
                jfc.showOpenDialog(null);
                ontoFile = jfc.getSelectedFile();
                
                
                

//                tasklib = new TaskLib();
                tasklib.read(ontoFile);
                JList jlist = getListAbstractTask();
                jlist.setModel(tasklib.getAbstractTasksListModel());
                jlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            }
        });
        btnOpenTaskLib.setToolTipText("Open Task Library");
        btnOpenTaskLib.setIcon(new ImageIcon(MainWindow.class
                .getResource("/lmars218/taskmodel/resources/archive.png")));
        toolBar.add(btnOpenTaskLib);

        btnChooseObjectTask = new JButton("");
        btnChooseObjectTask.setEnabled(false);
        btnChooseObjectTask.setToolTipText("Choose Object Task");
        btnChooseObjectTask
                .setIcon(new ImageIcon(MainWindow.class.getResource("/lmars218/taskmodel/resources/android-locate.png")));
        btnChooseObjectTask.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	getPanelTaskSeq().setVisible(false);
            	listTaskSeq.setModel(new DefaultListModel());
                JList list = getListAbstractTask();
                AbstractTask targetTask = (AbstractTask) list
                        .getSelectedValue();
                if (targetTask == null) {
                    return;
                }
                // System.out.println("Target: "+targetTask.getIndividual());
                JTree tree = getTreeTaskDecomp();
                TaskDecomposeNode node = new TaskDecomposeNode(targetTask);
                DefaultMutableTreeNode root = new DefaultMutableTreeNode(node);
                DefaultTreeModel model = new DefaultTreeModel(root);
                tree.setCellRenderer(new TaskTreeCellRenderer());
                tree.setModel(model);
                tree.getSelectionModel().setSelectionMode(
                        TreeSelectionModel.SINGLE_TREE_SELECTION);
                // DefaultMutableTreeNode selectedNode =
                // (DefaultMutableTreeNode) tree
                // .getLastSelectedPathComponent();
                // if (selectedNode == null) {
                // return;
                // }
                // TaskDecomposeNode taskNode = (TaskDecomposeNode) selectedNode
                // .getUserObject();
                // taskNode.setImplementType(TaskDecomposeNode.ImplementType.COMPLEX);
                // ComplexTask complexTask = new ComplexTask("SERds");
                // complexTask.setAbstractTask(taskNode.getTask());
                // taskNode.setImplement(complexTask);
                // TaskDecomposeNode node1 = new TaskDecomposeNode(
                // new AbstractTask("Tase6"));
                // node1.setImplementType(TaskDecomposeNode.ImplementType.COMPLEX);
                // TaskDecomposeNode node2 = new TaskDecomposeNode(
                // new AbstractTask("sre25"));
                // node2.setImplementType(TaskDecomposeNode.ImplementType.NONE);
                // selectedNode.add(new DefaultMutableTreeNode(node1));
                // selectedNode.add(new DefaultMutableTreeNode(node2));
                // DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
                // TreePath path = new
                // TreePath(model.getPathToRoot(selectedNode));
                // tree.expandPath(path);
                // tree.scrollPathToVisible(path);
                // tree.updateUI();
            }
        });

        JLabel lblArrow1 = new JLabel("");
        lblArrow1
                .setIcon(new ImageIcon(
                        MainWindow.class
                                .getResource("/lmars218/taskmodel/resources/arrow-right-b32.png")));
        toolBar.add(lblArrow1);
        toolBar.add(btnChooseObjectTask);

        JLabel lblArrow2 = new JLabel("");
        lblArrow2
                .setIcon(new ImageIcon(
                        MainWindow.class
                                .getResource("/lmars218/taskmodel/resources/arrow-right-b32.png")));
        toolBar.add(lblArrow2);

        btnChooseTaskImplementation = new JButton("");
        btnChooseTaskImplementation.setEnabled(false);
        btnChooseTaskImplementation.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JTree tree = getTreeTaskDecomp();
                DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree
                        .getLastSelectedPathComponent();
                if (selectedNode == null) {
                    return;
                }
                TaskDecomposeNode taskNode = (TaskDecomposeNode) selectedNode
                        .getUserObject();
                JList jlist = getListTaskImpl();
                jlist.setModel(tasklib.getImplementationListModel(taskNode
                        .getTask().getIndividual()));
                jlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                // taskNode.setImplementType(TaskDecomposeNode.ImplementType.SIMPLE);
                // selectedNode.removeAllChildren();
                // DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
                // TreePath path = new
                // TreePath(model.getPathToRoot(selectedNode));
                // tree.scrollPathToVisible(path);
                // tree.updateUI();
            }
        });
        btnChooseTaskImplementation
                .setToolTipText("Choose Task Implementation");
        btnChooseTaskImplementation
                .setIcon(new ImageIcon(MainWindow.class.getResource("/lmars218/taskmodel/resources/android-search.png")));
        toolBar.add(btnChooseTaskImplementation);

        JLabel lblArrow3 = new JLabel("");
        lblArrow3
                .setIcon(new ImageIcon(
                        MainWindow.class
                                .getResource("/lmars218/taskmodel/resources/arrow-right-b32.png")));
        toolBar.add(lblArrow3);

        btnExportServiceChain = new JButton("");
        btnExportServiceChain.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               
                //serviceChain..shopping0421
                JTree tree=getTreeTaskDecomp();
            	JList list=getListTaskSeq();
             
            	list.setModel(new DefaultListModel());
            	TreeNode treenode = (TreeNode)tree.getModel().getRoot();
                
                int index=(int)treeTaskDecomp.getModel().getChildCount(treenode);
//                TaskDecomposeNode node=(TaskDecomposeNode) ((DefaultMutableTreeNode) treenode).getUserObject();
                exportServiceChain(treenode, index);
                
                getPanelTaskSeq().setVisible(true);
            }
            private void exportServiceChain(TreeNode node,int index)
            {
            	System.out.println("2222222222222222");
            	JTree tree = getTreeTaskDecomp();
             	JList list=getListTaskSeq();
            	if(index==0)
            	{
            		TaskDecomposeNode tempnode=(TaskDecomposeNode) ((DefaultMutableTreeNode) node).getUserObject();
            		String type=(String)tempnode.getImplementType().toString();
            		if(type.equals("SIMPLE"))
            		{
//            			((DefaultListModel) list.getModel()).addElement(tempnode.getTask().toString());
            			String toservice=tasklib.getService(tempnode.getImplement().individual);
            			((DefaultListModel) list.getModel()).addElement(tempnode.getImplement().individual.getLocalName()+"["+toservice+"]");
            		}
            	}
            	else
            	{
            		for(int i=0;i<index;i++)
            		{
            			 
            			TreeNode treenode=(TreeNode)tree.getModel().getChild(node, i);
            		 
//            			TaskDecomposeNode treenode=(TaskDecomposeNode)((DefaultMutableTreeNode) tempnode).getUserObject();
            			int childcount=(int)tree.getModel().getChildCount(treenode);
            		    exportServiceChain(treenode,childcount);
            		}
            	}
            }
        });
        
         
        btnExportServiceChain.setEnabled(false);
        btnExportServiceChain
                .setIcon(new ImageIcon(MainWindow.class.getResource("/lmars218/taskmodel/resources/android-share.png")));
        btnExportServiceChain.setToolTipText("Export Service Chain");
        toolBar.add(btnExportServiceChain);

        JPanel panelTaskSearch = new JPanel();
        panelTaskSearch.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null,
                null));
        frmGeotask.getContentPane().add(panelTaskSearch, BorderLayout.WEST);

        JLabel lblTaskSeachResult = new JLabel("Task Seach Result");
        lblTaskSeachResult.setIcon(new ImageIcon(MainWindow.class
                .getResource("/lmars218/taskmodel/resources/flag24.png")));
        panelTaskSearch.setLayout(new BorderLayout(2, 2));
        panelTaskSearch.add(lblTaskSeachResult, BorderLayout.NORTH);

        JScrollPane scrollPaneAbstractTask = new JScrollPane();
        scrollPaneAbstractTask.setPreferredSize(new Dimension(200, 300));
        scrollPaneAbstractTask.setMinimumSize(new Dimension(150, 200));
        scrollPaneAbstractTask.setBorder(null);
        panelTaskSearch.add(scrollPaneAbstractTask, BorderLayout.CENTER);

        listAbstractTask = new JList();
        listAbstractTask.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                JList list = getListAbstractTask();
                AbstractTask targetTask = (AbstractTask) list
                        .getSelectedValue();
                if (targetTask == null) {
                    getBtnChooseObjectTask().setEnabled(false);
                } else {
                    getBtnChooseObjectTask().setEnabled(true);
                }

            }
        });
        scrollPaneAbstractTask.setViewportView(listAbstractTask);
        lblTaskSeachResult.setLabelFor(listAbstractTask);

        JPanel panelMain = new JPanel();
        frmGeotask.getContentPane().add(panelMain, BorderLayout.CENTER);
        panelMain.setLayout(new BorderLayout(2, 2));

        JPanel panelTaskDecomp = new JPanel();
        panelTaskDecomp.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null,
                null));
        panelMain.add(panelTaskDecomp, BorderLayout.CENTER);
        panelTaskDecomp.setLayout(new BorderLayout(2, 2));

        JLabel lblTaskDecomp = new JLabel("Task Decomposition");
        lblTaskDecomp
                .setIcon(new ImageIcon(
                        MainWindow.class
                                .getResource("/lmars218/taskmodel/resources/code-download24.png")));
        panelTaskDecomp.add(lblTaskDecomp, BorderLayout.NORTH);

        JScrollPane scrollPaneTaskDecomp = new JScrollPane();
        scrollPaneTaskDecomp.setBorder(null);
        panelTaskDecomp.add(scrollPaneTaskDecomp, BorderLayout.CENTER);

        treeTaskDecomp = new JTree();
        treeTaskDecomp.addTreeSelectionListener(new TreeSelectionListener() {
            public void valueChanged(TreeSelectionEvent e) {
                JTree tree = getTreeTaskDecomp();
                DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree
                        .getLastSelectedPathComponent();
                if (selectedNode == null) {
                    getPanelTaskImpl().setVisible(false);
                    getBtnChooseTaskImplementation().setEnabled(false);
                    getBtnExportServiceChian().setEnabled(false);
                    return;
                }
                TaskDecomposeNode taskNode = (TaskDecomposeNode) selectedNode
                        .getUserObject();
                JList jlist = getListTaskImpl();
                jlist.setModel(tasklib.getImplementationListModel(taskNode
                        .getTask().getIndividual()));
                jlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                getLblTaskImplChoose().setText(
                        "Implementations of Abstract Task \""
                                + taskNode.getTask() + "\"");
                getPanelTaskImpl().setVisible(true);
                getBtnChooseTaskImplementation().setEnabled(true);
                getBtnExportServiceChian().setEnabled(true);
            }
        });
        scrollPaneTaskDecomp.setViewportView(treeTaskDecomp);
        treeTaskDecomp.setModel(null);

        panelTaskSeq = new JPanel();
        panelTaskSeq.setVisible(false);
        panelMain.add(panelTaskSeq, BorderLayout.EAST);
        panelTaskSeq.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null,
                null));
        panelTaskSeq.setLayout(new BorderLayout(2, 2));

        JLabel lblTaskSeq = new JLabel("Task Sequence");
        lblTaskSeq.setIcon(new ImageIcon(MainWindow.class
                .getResource("/lmars218/taskmodel/resources/clipboard24.png")));
        panelTaskSeq.add(lblTaskSeq, BorderLayout.NORTH);

        JScrollPane scrollPaneTaskSeq = new JScrollPane();
        scrollPaneTaskSeq.setBorder(null);
        panelTaskSeq.add(scrollPaneTaskSeq, BorderLayout.CENTER);

        listTaskSeq = new JList();
         
    	DefaultListModel listModel=new DefaultListModel();
    	listTaskSeq.setModel(listModel);
//        listTaskSeq.setModel(new AbstractListModel() {
//            String[] values = new String[] {"ZY3Image[WCS_ZY3]","ZY3ImageImage[WCS_ZY3]", 
//            		"RasterNDVI[WPS_NDVI]","RasterCalculate[WPS_RasterCalc]",
//            		"WaterLevelSenser[SOS_WaterLevel]","DEMbyWCS[WCS_DEM]",
//            		"WaterFill[WPS_3D_Simu]","PolygonOverlay[WPS_VectorOverlay]"};
//            public int getSize() {
//                return values.length;
//            }
//            public Object getElementAt(int index) {
//                return values[index];
//            }
//         });
        scrollPaneTaskSeq.setRowHeaderView(listTaskSeq);

        panelTaskImpl = new JPanel();
        panelTaskImpl.setVisible(false);
        panelTaskImpl.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null,
                null));
        panelTaskImpl.setMinimumSize(new Dimension(200, 50));
        panelMain.add(panelTaskImpl, BorderLayout.SOUTH);
        panelTaskImpl.setLayout(new BorderLayout(2, 2));

        panelTaskDescription = new JPanel();
        panelTaskDescription.setBackground(SystemColor.window);
        panelTaskDescription.setVisible(false);
        panelTaskImpl.add(panelTaskDescription, BorderLayout.EAST);
        panelTaskDescription.setLayout(new BorderLayout(0, 0));

        txtpnDescription = new JTextPane();
        txtpnDescription.setPreferredSize(new Dimension(300, 50));
        txtpnDescription.setOpaque(false);
        txtpnDescription.setText("Description of {}");
        panelTaskDescription.add(txtpnDescription, BorderLayout.CENTER);

        JPanel panelTaskImplChoose = new JPanel();
        panelTaskDescription.add(panelTaskImplChoose, BorderLayout.SOUTH);
        panelTaskImplChoose.setLayout(new BoxLayout(panelTaskImplChoose,
                BoxLayout.Y_AXIS));

        final JButton btnTaskImplChoose = new JButton("Choose This Implement");
        btnTaskImplChoose.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseEntered(MouseEvent e) {
        		btnTaskImplChoose.setContentAreaFilled(true);
        	}
        	@Override
        	public void mouseExited(MouseEvent e) {
        		btnTaskImplChoose.setContentAreaFilled(false);
        	}
        });
        
        btnTaskImplChoose.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnTaskImplChoose.setIcon(new ImageIcon(MainWindow.class.getResource("/lmars218/taskmodel/resources/plus-circled-small.png")));
        btnTaskImplChoose.setContentAreaFilled(false);
        btnTaskImplChoose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JList list = getListTaskImpl();
                JTree tree = getTreeTaskDecomp();
                ImplemtTask implTask = (ImplemtTask) list.getSelectedValue();
                DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree
                        .getLastSelectedPathComponent();
                if (selectedNode == null) {
                    getPanelTaskImpl().setVisible(false);
                    return;
                }
                TaskDecomposeNode taskNode = (TaskDecomposeNode) selectedNode
                        .getUserObject();
                if (implTask instanceof ComplexTask) {
                    taskNode.setImplementType(TaskDecomposeNode.ImplementType.COMPLEX);
                    taskNode.setImplement(implTask);
                    selectedNode.removeAllChildren();
                    //get subTask of the implement ComplexTask
                    List<AbstractTask> subTaskList = tasklib
                            .getSubTasks(implTask.getIndividual());
                    for (AbstractTask atask : subTaskList) {
                        TaskDecomposeNode subNode = new TaskDecomposeNode(atask);
                        selectedNode.add(new DefaultMutableTreeNode(subNode));
                      
                    }
                    TreePath path = new TreePath(((DefaultTreeModel) tree
                            .getModel()).getPathToRoot(selectedNode));//��ݽ��õ�·��
                    tree.expandPath(path);//չ�����
                    tree.scrollPathToVisible(path);//ȷ��·�������е�·�������չ�������һ��·��������⣩���������Ա���ʾ��·����ʶ�Ľڵ㡣 
                    tree.updateUI();
                    // TaskDecomposeNode node1 = new TaskDecomposeNode(
                    // new AbstractTask("Tase6"));
                    // node1.setImplementType(TaskDecomposeNode.ImplementType.COMPLEX);
                    // TaskDecomposeNode node2 = new TaskDecomposeNode(
                    // new AbstractTask("sre25"));

                }
                if (implTask instanceof SimpleTask) {
                    taskNode.setImplementType(TaskDecomposeNode.ImplementType.SIMPLE);
                    taskNode.setImplement(implTask);

                    selectedNode.removeAllChildren();
                    DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
                    TreePath path = new TreePath(model
                            .getPathToRoot(selectedNode));
                    tree.scrollPathToVisible(path);
                    tree.updateUI();
                }
            }
            
            
            
        });
        panelTaskImplChoose.add(btnTaskImplChoose);

        lblTaskImplChoose = new JLabel("Task Implementation of {}");
        lblTaskImplChoose.setIcon(new ImageIcon(MainWindow.class
                .getResource("/lmars218/taskmodel/resources/gear-a24.png")));
        panelTaskImpl.add(lblTaskImplChoose, BorderLayout.NORTH);

        JScrollPane scrollPaneTaskImpl = new JScrollPane();
        scrollPaneTaskImpl.setBorder(null);
        panelTaskImpl.add(scrollPaneTaskImpl, BorderLayout.CENTER);

        listTaskImpl = new JList();
  
        listTaskImpl.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                JList list = getListTaskImpl();
                ImplemtTask implTask = (ImplemtTask) list.getSelectedValue();
                if (implTask == null) {
                    getPanelTaskDescription().setVisible(false);
                    return;
                }
                if (implTask instanceof ComplexTask) {
                	//description ...shopping0421
                	 List<AbstractTask> subTaskList = tasklib
                             .getSubTasks(implTask.getIndividual());
                    getTxtpnDescription().setText(
                            "Description of Complex Task \"" + implTask + "\"\n" + "Subtasks:\n" +subTaskList);
                    
                }
                if (implTask instanceof SimpleTask) {
                    getTxtpnDescription().setText(
                            "Description of Simple Task \"" + implTask + "\"" + "Service:\n");
                }
                getPanelTaskDescription().setVisible(true);
            }
        });
        scrollPaneTaskImpl.setViewportView(listTaskImpl);
    }

    public JTree getTreeTaskDecomp() {
        return treeTaskDecomp;
    }

    public JList getListAbstractTask() {
        return listAbstractTask;
    }

    public JList getListTaskSeq() {
        return listTaskSeq;
    }

    public JList getListTaskImpl() {
        return listTaskImpl;
    }

    protected JPanel getPanelTaskDescription() {
        return panelTaskDescription;
    }

    protected JPanel getPanelTaskSeq() {
        return panelTaskSeq;
    }

    public JPanel getPanelTaskImpl() {
        return panelTaskImpl;
    }

    public JButton getBtnOpenTaskLib() {
        return btnOpenTaskLib;
    }

    public JButton getBtnChooseObjectTask() {
        return btnChooseObjectTask;
    }

    public JButton getBtnChooseTaskImplementation() {
        return btnChooseTaskImplementation;
    }

    public JButton getBtnExportServiceChian() {
        return btnExportServiceChain;
    }

    public JLabel getLblTaskImplChoose() {
        return lblTaskImplChoose;
    }

    public JTextPane getTxtpnDescription() {
        return txtpnDescription;
    }
    
    public void refreshAbstract(){
    	 JList jlist = getListAbstractTask();
         jlist.setModel(tasklib.getAbstractTasksListModel());
         jlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
}
