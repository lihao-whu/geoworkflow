package lmars218.taskmodel;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntDocumentManager;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.ontology.Ontology;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.RDFWriter;

public class tasktest {

	public static void main(String[] args) throws IOException {
		String BASE = "http://lmars.whu.edu.cn/taskrepo";
		OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);

		OntDocumentManager docManager = model.getDocumentManager();
		docManager.addAltEntry("http://lmars.whu.edu.cn/GeoService/Task.owl",
				"file:///F:/0106/1223/task.owl");

		model.setDynamicImports(true);
		model.setNsPrefix("", BASE + "#");
		model.setNsPrefix("task", "http://lmars.whu.edu.cn/GeoService/Task.owl#");

		Ontology ont = model.createOntology(BASE);
		ont.addImport(model
				.createResource("http://lmars.whu.edu.cn/GeoService/Task.owl"));

		OntClass abstractTask = model
				.getOntClass("http://lmars.whu.edu.cn/GeoService/Task.owl#AbstractTask");
		model.createIndividual(BASE + "#HumanActivity", abstractTask); 

		// m.write(outWriter, "RDF/XML-ABBREV", BASE);
		RDFWriter xmlAbbrWriter = model.getWriter("RDF/XML-ABBREV");
		xmlAbbrWriter.setProperty("xmlbase", BASE);
		xmlAbbrWriter.setProperty("relativeURIs", "same-document");
		xmlAbbrWriter.setProperty("showXmlDeclaration", true);

		FileOutputStream outFile = new FileOutputStream("F:\\tastRepo.owl");
		OutputStreamWriter outWriter = new OutputStreamWriter(outFile, "UTF-8");
		xmlAbbrWriter.write(model.getBaseModel(), outWriter, BASE);
	}

}