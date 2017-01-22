package lmars218.taskmodel;

import com.hp.hpl.jena.ontology.Individual;

public class AbstractTask extends Task {
    private String parameter;

    public AbstractTask(Individual ind) {
        this.individual = ind;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }
     

}
