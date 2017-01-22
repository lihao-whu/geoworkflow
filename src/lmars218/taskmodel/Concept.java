package lmars218.taskmodel;

import com.hp.hpl.jena.ontology.Individual;

public class Concept {

	protected Individual individual;
	public Concept(Individual individual) {
		// TODO Auto-generated constructor stub
		this.individual = individual;
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
