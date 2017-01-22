package lmars218.taskmodel;

import java.awt.Component;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;


public class TaskTreeCellRenderer extends DefaultTreeCellRenderer {
    Icon noImpl;
    Icon simplImpl;
    Icon complexImpl;
    
    public TaskTreeCellRenderer() {
        super();
        noImpl = new ImageIcon(MainWindow.class.getResource("/lmars218/taskmodel/resources/help-circled16.png")); 
        simplImpl = new ImageIcon(MainWindow.class.getResource("/lmars218/taskmodel/resources/checkmark-circled16.png")); 
        complexImpl = new ImageIcon(MainWindow.class.getResource("/lmars218/taskmodel/resources/android-storage16.png")); 
    }

    /**
     * 
     */
    private static final long serialVersionUID = 6184417799631049664L;

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value,
            boolean sel, boolean expanded, boolean leaf, int row,
            boolean hasFocus) {
        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf,
                row, hasFocus);
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
        TaskDecomposeNode taskNode = (TaskDecomposeNode) node.getUserObject();
        switch (taskNode.getImplementType()) {
        case SIMPLE:
            setIcon(simplImpl);
            break;
        case COMPLEX:
            setIcon(complexImpl);
            break;
        case NONE:
            setIcon(noImpl);
            break;
        default:
            break;
        }
        return this;
    }

    
}
