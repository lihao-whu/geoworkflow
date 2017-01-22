package lmars218.taskmodel;

import com.hp.hpl.jena.ontology.Individual;

public class Sequence {

	protected Individual individual;
	private TaskInstance first;//暂时定义 目前还没有用处
	private Sequence rest;//只有定义 还没有实际的用处
	
	public Sequence(Individual ind) {
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
