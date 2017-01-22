package lmars218.taskmodel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import org.apache.jena.atlas.iterator.Iter;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;

public class sparqlTest {

 
		// TODO Auto-generated constructor stub
		public static void main(String[] args) throws IOException {
		
			// Open the bloggers RDF graph from the filesystem
			InputStream in = new FileInputStream(new File("F:/0106/0115/task.owl"));
	  
			// Create an empty in-memory model and populate it from the graph
			Model model = ModelFactory.createMemModelMaker().createDefaultModel();
			model.read(in,null); // null base URI, since model URIs are absolute
			in.close();
			// Create a new query
			String queryString = 
				"PREFIX task: <http://lmars.whu.edu.cn/GeoService/Task.owl#>" +
				"SELECT ?A " +
				"WHERE {" +
		
				"      ?A task:implement ?B . " +
				"      ?A task:invoke ?C . " +
				"      }";
			Query query = QueryFactory.create(queryString);
			// Execute the query and obtain results
			QueryExecution qe = QueryExecutionFactory.create(query, model);
			ResultSet results = qe.execSelect();
			// Output query results	
			ResultSetFormatter.out(System.out, results, query);
			 	 
			List<QuerySolution> resultlist = ResultSetFormatter.toList(results);
			
			// Important - free up resources used running the query
			qe.close();
		}
		
		
	 

}
