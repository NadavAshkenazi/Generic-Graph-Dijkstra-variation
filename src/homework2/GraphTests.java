package homework2;


import org.junit.Test;

import java.util.Set;

/**
 * This class contains a set of test cases that can be used to test the graph
 * and shortest path finding algorithm implementations of homework assignment
 * #2.
 */
public class GraphTests extends ScriptFileTests {

	// black-box test are inherited from super
	public GraphTests(java.nio.file.Path testFile) {
		super(testFile);
	}


	@Test
	public void nullNode(){
		Graph g1 = new Graph();
		WeightedNode n1 = null;
		try{
			g1.addNode(n1);
		}
		catch (Exception e){
			assert (e instanceof NullPointerException);
		}
	}

	@Test
	public void duplicateNode(){
		Graph g1 = new Graph<WeightedNode>();
		WeightedNode n1 = new WeightedNode("n1", 1);
		g1.addNode(n1);
		try{
			g1.addNode(n1);
		}
		catch (Exception e){
			assert (e instanceof IllegalArgumentException);
			assert (e.getMessage() == "node already in the graph");
		}
	}
	@Test
	public void nullNodeForEdge(){
		Graph g1 = new Graph();
		WeightedNode n1 = new WeightedNode("n1", 1);
		WeightedNode n2 = null;
		g1.addNode(n1);
		try{
			g1.addEdge(n1, n2);
		}
		catch (Exception e){
			assert (e instanceof IllegalArgumentException);
			assert (e.getMessage() == "child is null");
		}
	}

	@Test
	public void nodeNotInTheGraph(){
		Graph g1 = new Graph();
		WeightedNode n1 = new WeightedNode("n1", 1);
		WeightedNode n2 = new WeightedNode("n2", 2);
		g1.addNode(n1);
		try{
			g1.addEdge(n1, n2);
		}
		catch (Exception e){
			assert (e instanceof IllegalArgumentException);
			assert (e.getMessage() == "child not in the graph");
		}
	}

	@Test
	public void edgeAlreadyExists(){
		Graph g1 = new Graph();
		WeightedNode n1 = new WeightedNode("n1", 1);
		WeightedNode n2 = new WeightedNode("n1", 2);
		g1.addNode(n1);
		g1.addNode(n2);
		g1.addEdge(n1, n2);
		try{
			g1.addEdge(n1, n2);
		}
		catch (Exception e){
			assert (e instanceof IllegalArgumentException);
			assert (e.getMessage() == "edge already exists");
		}
	}
	@Test
	public void listNotModified(){
		Graph g1 = new Graph();
		WeightedNode n1 = new WeightedNode("n1", 1);
		WeightedNode n2 = new WeightedNode("n1", 2);
		g1.addNode(n1);
		g1.addNode(n2);
		g1.addEdge(n1, n2);
		Set s1 = g1.listNodes();
		try{
			s1.add(n1);
		}
		catch (Exception e){
			assert (e instanceof UnsupportedOperationException);
		}
		Set s2 = g1.listChildren(n1);
		try{
			s2.add(n1);
		}
		catch (Exception e){
			assert (e instanceof UnsupportedOperationException);
		}
	}


}
