package lmars218.taskmodel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.swing.DefaultListModel;

import com.hp.hpl.jena.assembler.ImportManager;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntDocumentManager;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.ontology.Ontology;
import com.hp.hpl.jena.ontology.impl.IndividualImpl;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.NodeIterator;
import com.hp.hpl.jena.rdf.model.RDFWriter;
import com.hp.hpl.jena.rdf.model.ResIterator;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.SimpleSelector;
import com.hp.hpl.jena.util.FileManager;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;

public class TaskLib {
    // private File file;
    private OntModel ontModel;
    private OntClass abstractTask;
    private OntClass complexTask;
    private OntClass simpleTask;
    private OntClass service;
    private OntClass sequence;
    private OntClass taskInstance;
    private OntClass parameter;
    private OntClass concept;
    private OntClass datatype;
    private OntProperty implement;
    private OntProperty hasParameters;
    private OntProperty type;
    private OntProperty first;
    private OntProperty rest;
    private OntProperty invoke;
    private OntProperty hasSubTask;
    private OntProperty hasConcept;
    private Individual nil;
    private File myfile;
//    private File modelFile;//用来存放
 
    private String mTask ;
    private String taskpath;
    private String domainpath;
    
    

    // private OntProperty isImplementOf;

    public void refresh()
    {
    	read(myfile);
    	
    }
    
    public void importDomain(File file){
    	domainpath = file.getPath();
    	
    	
    }
    
    public void importTaskModel(File file){
	
    	taskpath = file.getPath();//获取task.owl的路径
 
    }
    
    public void read(File file) {
    	
    	
    	String BASE = "http://lmars.whu.edu.cn/GeoService/taskRepo";
    	ontModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);

		OntDocumentManager docManager = ontModel.getDocumentManager();
		docManager.addAltEntry("http://lmars.whu.edu.cn/GeoService/Task.owl",taskpath);
		docManager.addAltEntry("http://lmars.whu.edu.cn/GeoService/Domain.owl", domainpath);
		
		ontModel.setDynamicImports(true);
		
		ontModel.setNsPrefix("", BASE + "#");
		ontModel.setNsPrefix("task", "http://lmars.whu.edu.cn/GeoService/Task.owl#");
		ontModel.setNsPrefix("domain", "http://lmars.whu.edu.cn/GeoService/Domain.owl#");
		
		Ontology ont = ontModel.createOntology(BASE);
		ont.addImport(ontModel.createResource(taskpath));
		ont.addImport(ontModel.createResource(domainpath));
 
		
		  try {
			  ontModel.read(new FileInputStream(file), "");
		  }catch (FileNotFoundException e) {
	            e.printStackTrace();
	        }


    	myfile=file;
 
        abstractTask = ontModel
		        .getOntClass("http://lmars.whu.edu.cn/GeoService/Task.owl#AbstractTask");
		complexTask = ontModel
		        .getOntClass("http://lmars.whu.edu.cn/GeoService/Task.owl#ComplexTask");
		simpleTask = ontModel
		        .getOntClass("http://lmars.whu.edu.cn/GeoService/Task.owl#SimpleTask");
		service = ontModel
				.getOntClass("http://lmars.whu.edu.cn/GeoService/Task.owl#Service");
		sequence = ontModel
				.getOntClass("http://lmars.whu.edu.cn/GeoService/Task.owl#Sequence");
		taskInstance = ontModel
				.getOntClass("http://lmars.whu.edu.cn/GeoService/Task.owl#TaskInstance");
		implement = ontModel
		        .getOntProperty("http://lmars.whu.edu.cn/GeoService/Task.owl#implement");
		hasParameters = ontModel
		        .getOntProperty("http://lmars.whu.edu.cn/GeoService/Task.owl#hasParameters");
		hasConcept = ontModel
		        .getOntProperty("http://lmars.whu.edu.cn/GeoService/Task.owl#hasConcept");
		type = ontModel
		        .getOntProperty("http://lmars.whu.edu.cn/GeoService/Task.owl#type");
		first = ontModel
		        .getOntProperty("http://lmars.whu.edu.cn/GeoService/Task.owl#first");
		rest = ontModel
		        .getOntProperty("http://lmars.whu.edu.cn/GeoService/Task.owl#rest");
		invoke = ontModel
		        .getOntProperty("http://lmars.whu.edu.cn/GeoService/Task.owl#invoke");
		hasSubTask = ontModel
		        .getOntProperty("http://lmars.whu.edu.cn/GeoService/Task.owl#hasSubTask");
		nil = ontModel
		        .getIndividual("http://lmars.whu.edu.cn/GeoService/Task.owl#nil");
		parameter= ontModel
				.getOntClass("http://lmars.whu.edu.cn/GeoService/Task.owl#Parameter");
		concept = ontModel
				.getOntClass("http://lmars.whu.edu.cn/GeoService/Domain.owl#Concept");
		datatype = ontModel
				.getOntClass("http://lmars.whu.edu.cn/GeoService/Domain.owl#DataType");
		 
    }

    public DefaultListModel getAbstractTasksListModel() {
        DefaultListModel list = new DefaultListModel();
        if(ontModel.listIndividuals(abstractTask).hasNext())
        for (ExtendedIterator<Individual> itor = ontModel
                .listIndividuals(abstractTask); itor.hasNext();) {
            Individual atask = itor.next();
            System.out.println(atask.getURI());
            System.out.println(atask.getLocalName());
            list.addElement(new AbstractTask(atask));
        }
        return list;
    }
    
    public DefaultListModel getServiceListModel() {
        DefaultListModel list = new DefaultListModel();
        for (ExtendedIterator<Individual> itor = ontModel
                .listIndividuals(service); itor.hasNext();) {
            Individual atask = itor.next();
//            System.out.println(atask.getURI());
//            System.out.println(atask.getLocalName());
            list.addElement(new Service(atask));
        }
        return list;
    }
    
    
    

    public DefaultListModel getImplementationListModel(Individual atask) {
        DefaultListModel list = new DefaultListModel();
        // for (NodeIterator itor =
        // atask.listPropertyValues(isImplementOf);itor.hasNext();){
        // Individual impl =
        // ontModel.getIndividual(itor.next().asResource().getURI());
        // OntClass taskClass = impl.getOntClass();
        // if (taskClass.equals(complexTask)) {
        // list.addElement(new ComplexTask(impl));
        // }
        // if (taskClass.equals(simpleTask)) {
        // list.addElement(new SimpleTask(impl));
        // }
        // }
        Model result = ontModel
                .query(new SimpleSelector(null, implement, atask));
        for (ResIterator itor = result.listSubjects(); itor.hasNext();) {
            Individual impl = ontModel.getIndividual(itor.next().getURI());
            OntClass taskClass = impl.getOntClass();
            if (taskClass.equals(complexTask)) {
                list.addElement(new ComplexTask(impl));
            }
            if (taskClass.equals(simpleTask)) {
                list.addElement(new SimpleTask(impl));
            }
        }
        return list;
    }
    //shopping ..
    
    
    public String getOWLUrl()
    {
    	String s="http://lmars.whu.edu.cn/GeoService/Task.owl#";
    	return s;
    }
    
    
    
    
    public String getService(Individual stask){
    	
    	String result = stask.getProperty(invoke).asTriple().getMatchObject().getLocalName().toString();
    	return result;
    }
    
    public List<String> getParameters(Individual atask){
    	List<String> list=new ArrayList<String>();
    	Individual para=ontModel.getIndividual(atask.getPropertyResourceValue(type).getURI());
    	
    	while(!para.equals(nil)&&para!=null)
    	{
    		//
    	}
    	
    	return list;
    }
    
    public boolean hasExactlySuperClass=false;
    public boolean hasExactlySuperClass(OntClass superclass,OntClass subclass){
    	  if(hasExactlySuperClass==true)
    	  {
    		  return true;
    	  }else{
    		  for (ExtendedIterator<OntClass> supClassitor = subclass.listSuperClasses(); supClassitor.hasNext();) {
    		      OntClass currentClass =  supClassitor.next() ;
    		      if(currentClass.equals(superclass))
    		      {
    		    	  hasExactlySuperClass=true;
    		    	  return true;
    		    	  
    		      }else{
    		    	  if(currentClass.hasSuperClass())
    		    	  {
    		    		  if(hasExactlySuperClass(superclass,currentClass))
    		    			  {
    		    			     return true;
    		    			  }else
    		    			  {
    		    				  return false;
    		    			  }
    		    			 
    		    	  }else{
    		    		  return false;
    		    	  }
    		    	 
    		      }
    		  } 
    		  return false;
    	  }
	
		  
		   
 
    }
    
    /*
     * get individuals of Class:Concept in domain.owl 
     */
    @SuppressWarnings("unchecked")
	public DefaultListModel<Concept> getConcepts(){
    	DefaultListModel<Concept> list = new DefaultListModel<Concept>();
    	for (ExtendedIterator<Individual> itor = ontModel
                .listIndividuals(); itor.hasNext();) {
            
    		Individual cpt = itor.next();
    		OntClass ontclass = cpt.getOntClass();
    		hasExactlySuperClass=false;
    		//get the concept in Domain
    		if(hasExactlySuperClass(concept, ontclass)){
    			 System.out.println(cpt.getURI());
                 System.out.println(cpt.getLocalName());
                ((DefaultListModel<Concept>) list).addElement(new Concept(cpt));	
    		}else
    		//get the dataype in Domain
    		if(hasExactlySuperClass(datatype, ontclass)){
   			 System.out.println(cpt.getURI());
                System.out.println(cpt.getLocalName());
               ((DefaultListModel<Concept>) list).addElement(new Concept(cpt));	
   		}
    		
    	}

    	return (DefaultListModel<Concept>) list;
    }
    
    

    public List<AbstractTask> getSubTasks(Individual ctask) {
        List<AbstractTask> list = new ArrayList<AbstractTask>();
        if(ctask.getOntClass().equals(complexTask)){
            Individual seq = ontModel.getIndividual(ctask.getPropertyResourceValue(hasSubTask).getURI());
            while(!nil.equals(seq)&&seq!=null){
                Individual taskImpl = ontModel.getIndividual(seq.getPropertyResourceValue(first).getURI());
                Individual atask = ontModel.getIndividual(taskImpl.getPropertyResourceValue(type).getURI());
                list.add(new AbstractTask(atask));
                seq = ontModel.getIndividual(seq.getPropertyResourceValue(rest).getURI());
            }
//            NodeIterator itor = ctask.listPropertyValues(hasSubTask);
//            if(itor.hasNext()){
//                Individual seq = ontModel.getIndividual(itor.next().asResource().getURI());
//                while(!seq.equals(nil)){
//                    NodeIterator itor1 = ctask.listPropertyValues(first);
//                    NodeIterator itor2 = ctask.listPropertyValues(rest);
//                    if(itor1.hasNext() && itor2.hasNext()){
//                        Individual subtask = ontModel.getIndividual(itor1.next().asResource().getURI());
//                        subtask.getPropertyResourceValue(first)
//                        seq = ontModel.getIndividual(itor2.next().asResource().getURI());
//                    }else{
//                        seq = nil;
//                    }
//                }
//            }
        }
        return list;
    }

    public Individual createParameter(String paraName,String paraDescription,DefaultListModel<Concept> conceptlistModel){
    	//createParameter
    	Individual newParameter = ontModel.createIndividual("http://lmars.whu.edu.cn/GeoService/taskrepo.owl#"+paraName,parameter);
    	newParameter.addComment(paraDescription, null);
    	
    	int number = conceptlistModel.getSize();
    	if(number==0)
    	{
    		writeToFile();
    	}else{
    		for(int i=0;i<number;i++)
    		{
    			Concept newconcept = conceptlistModel.getElementAt(i);
    			newParameter.addProperty(hasConcept, newconcept.getIndividual());
    		}
    		 
    		writeToFile();
    	}
    	
    	return newParameter;
        
    }
    
    
    public void createAbstractTask(String description,String taskName,DefaultListModel<Parameter> parameterlistmodel){
    	//create a new individual of  abstractTask.....
    	
//    	Individual newAbstractTask = abstractTask.createIndividual(getOWLUrl()+taskName);
    	
    	
    	Individual newAbstractTask = ontModel.createIndividual("http://lmars.whu.edu.cn/GeoService/taskrepo.owl#"+taskName,abstractTask);
    	newAbstractTask.addComment(description, null);
    	//add Parameters
      
        if(parameterlistmodel.getSize()!=0)
        {
        	  int number = parameterlistmodel.getSize();
        	  UUID taskInstanceName = UUID.randomUUID();
        	  Individual taskIns = ontModel
        			  .createIndividual("http://lmars.whu.edu.cn/GeoService/taskrepo.owl#"+taskInstanceName, taskInstance);
        	  taskIns.addProperty(type, newAbstractTask);
        	  UUID sequenceName = UUID.randomUUID();
        	  
        	  Individual firstSequence = ontModel
        			  .createIndividual("http://lmars.whu.edu.cn/GeoService/taskrepo.owl#"+sequenceName, sequence);
        	  
        	  taskIns.addProperty(hasParameters, firstSequence);
        	  
        	  Individual temp = firstSequence;
        	  for(int i=0;i<number;i++)
        	  {
        		  Parameter para = parameterlistmodel.getElementAt(i);
        		  Individual paraIndual = para.individual;
        		  temp.addProperty(first, paraIndual);
        		  
        		  UUID nextSeqName = UUID.randomUUID();
        		  Individual nextSeq =  ontModel
            			  .createIndividual("http://lmars.whu.edu.cn/GeoService/taskrepo.owl#"+nextSeqName, sequence);
        		  
        		  temp.addProperty(rest, nextSeq);
        		  temp = nextSeq;
        		  
        	  }temp.addProperty(rest, nil);
        	  
        }
    	

    	//write the individual
    	writeToFile();
    }
    	
   

    public void createSimpleTask(String taskName,String description,Individual abstractTaskIndividual,Individual serviceIndividual){
    	Individual newSimpleTask = simpleTask.createIndividual(getOWLUrl()+taskName);
    	
    	
    	newSimpleTask.addProperty(implement, abstractTaskIndividual);
    	newSimpleTask.addProperty(invoke, serviceIndividual);
    	writeToFile();
    }
    
    public void createComplexTask(String taskName,Individual impleTask, Individual[] subtasks,int number){
    	Individual newComplexTask = complexTask.createIndividual(getOWLUrl()+taskName);
    	
    	if(number==0)
    		return;
    	else{
    	
    		UUID uuid1 = UUID.randomUUID();
    		UUID uuid2 = UUID.randomUUID();
    		Individual newtaskins = taskInstance.createIndividual(getOWLUrl()+uuid1);
    		newtaskins.addProperty(type, subtasks[0]);
    		
    		Individual firstTask = sequence.createIndividual(getOWLUrl()+uuid2);
    		firstTask.addProperty(first, newtaskins);
    		
    		newComplexTask.addProperty(implement, impleTask);
    		if(number==1)
    		{
    			firstTask.addProperty(rest, nil);
        		newComplexTask.addProperty(hasSubTask, firstTask);
        		
    		}else{
    			UUID uuid3= UUID.randomUUID();
	    		Individual restSeq= sequence.createIndividual(getOWLUrl()+uuid3);
	    		firstTask.addProperty(rest, restSeq);
	    		newComplexTask.addProperty(hasSubTask, firstTask);
	    		
	    		Individual tempSeq = restSeq;
    			for(int i=1;i<number;i++)
    			{
    				UUID uuidtemp1 = UUID.randomUUID();
    	    		Individual taslInstemp = taskInstance.createIndividual(getOWLUrl()+uuidtemp1);
    	    		taslInstemp.addProperty(type, subtasks[i]);
    	    		
    	    		tempSeq.addProperty(first, taslInstemp);
    	    		
    	    		UUID uuidtemp2= UUID.randomUUID();
    	    		Individual restSeqtemp= sequence.createIndividual(getOWLUrl()+uuidtemp2);
    	    		if(i==(number-1))
    	    		{
    	    			tempSeq.addProperty(rest, nil);
    	    		}else{
    	    			tempSeq.addProperty(rest, restSeqtemp);
        	    		tempSeq = restSeqtemp;
    	    		}
    			}
    	    }
    	}
    	writeToFile();
    	
    }
    
    
    /*
     * after change the owl,write to the file
     */
    public void writeToFile(){
    	try {
            FileOutputStream output = new FileOutputStream(myfile);
            ontModel.write(output);   
            output.close();
       } catch (FileNotFoundException e) {
          // TODO Auto-generated catch block
             e.printStackTrace();
       } catch (IOException e) {
             // TODO Auto-generated catch block
               e.printStackTrace();
       }
    }
    
    public Individual getIndividualfromUrl(String url){
    	
    	Individual ind = ontModel.getIndividual(url);
    	
    	return ind;
    }
}
