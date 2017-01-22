package lmars218.taskmodel;

import com.hp.hpl.jena.ontology.Individual;

public class Service {

	

	protected Individual individual;


	public Service(Individual ind) {
		// TODO Auto-generated constructor stub
		this.individual = ind;
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
