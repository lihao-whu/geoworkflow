package lmars218.taskmodel;

import com.hp.hpl.jena.ontology.Individual;

public class SimpleTask extends ImplemtTask {
    private String service;

    public SimpleTask(Individual ind) {
        this.individual = ind;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

}
