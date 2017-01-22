package lmars218.taskmodel;

import com.hp.hpl.jena.ontology.Individual;

public abstract class Task {
    protected Individual individual;


    public Individual getIndividual() {
        return individual;
    }


    public void setIndividual(Individual individual) {
        this.individual = individual;
    }


    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return individual.getLocalName();
    }

    public String getUrl(){
    	
    	return individual.getURI().toString();
    }
}
