package lmars218.taskmodel;

import com.hp.hpl.jena.ontology.Individual;

public class TaskInstance {

	protected Individual individual;
	private Sequence firstParameter;//只有定义 还没有实际的用处
	private AbstractTask type;//只有定义 还没有实际的用处
	public TaskInstance(Individual ind) {
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
