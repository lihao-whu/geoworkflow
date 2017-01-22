package lmars218.taskmodel;

import com.hp.hpl.jena.ontology.Individual;

public class ComplexTask extends ImplemtTask {
    private AbstractTask[] subTask;

    public ComplexTask(Individual ind) {
        this.individual = ind;
    }

    public AbstractTask[] getSubTask() {
        return subTask;
    }

    public void setSubTask(AbstractTask[] subTask) {
        this.subTask = subTask;
    }

}
