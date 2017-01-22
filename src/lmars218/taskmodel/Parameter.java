package lmars218.taskmodel;

import com.hp.hpl.jena.ontology.Individual;

public class Parameter {

	protected Individual individual;
	public Parameter() {
		// TODO Auto-generated constructor stub
	}


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
