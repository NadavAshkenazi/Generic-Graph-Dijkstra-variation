package homework2;


import org.junit.Rule;
import org.junit.Test;
import org.junit.Before;
import org.junit.rules.TestName;

import java.util.HashSet;
import java.util.Set;

/**
 * This class contains a set of test cases that can be used to test the graph
 * and shortest path finding algorithm implementations of homework assignment
 * #2.
 */

public class GraphTests extends ScriptFileTests {
	Graph<WeightedNode> testGraph;
	WeightedNode n1;
	WeightedNode n2;
	WeightedNode n3;
	WeightedNode n4;
	WeightedNode n5;

	@Rule
	public TestName testName= new TestName();

	@Before
	public void setup(){
		String name = testName.getMethodName();
		System.out.println("Running " + name);
		testGraph = new Graph<>();
		this.n1 = new WeightedNode("n1", 1);
		testGraph.addNode(n1);
		this.n2 = new WeightedNode("n2", 1);
		testGraph.addNode(n2);
		this.n3 = new WeightedNode("n3", 1);
		testGraph.addNode(n3);
		this.n4 = new WeightedNode("n4", 1);
		testGraph.addNode(n4);
		this.n5 = new WeightedNode("n5", 1);
		testGraph.addNode(n5);
		testGraph.addEdge(n1,n4);
		testGraph.addEdge(n2,n5);
		testGraph.addEdge(n3,n1);
		testGraph.addEdge(n3,n2);
		testGraph.addEdge(n4,n5);
	}

	// black-box test are inherited from super
	public GraphTests(java.nio.file.Path testFile) {
		super(testFile);
	}

	// White-box tests:

	/**************************/
	/****** path tests ********/
	/**************************/
	@Test
	public void graph_nullNode(){
		Graph g1 = new Graph();
		n1 = null;
		try{
			g1.addNode(n1);
		}
		catch (Exception e){
			assert (e instanceof NullPointerException);
		}
	}

	@Test
	public void graph_duplicateNode(){
		WeightedNode testNode = new WeightedNode("testNode", 1);
		testGraph.addNode(testNode);
		try{
			testGraph.addNode(testNode);
		}
		catch (Exception e){
			assert (e instanceof IllegalArgumentException);
			assert (e.getMessage() == "node already in the graph");
		}
	}
	@Test
	public void graph_nullNodeForEdge(){
		WeightedNode testNode = new WeightedNode("testNode", 1);
		WeightedNode nullNode = null;
		testGraph.addNode(testNode);
		try{
			testGraph.addEdge(testNode, nullNode);
		}
		catch (Exception e){
			assert (e instanceof IllegalArgumentException);
			assert (e.getMessage() == "child is null");
		}
	}

	@Test
	public void graph_nodeNotInTheGraph(){
		WeightedNode testNode1 = new WeightedNode("testNode1", 1);
		WeightedNode testNode2 = new WeightedNode("testNode2", 2);
		testGraph.addNode(testNode1);
		try{
			testGraph.addEdge(testNode1, testNode2);
		}
		catch (Exception e){
			assert (e instanceof IllegalArgumentException);
			assert (e.getMessage() == "child not in the graph");
		}
	}

	@Test
	public void graph_edgeAlreadyExists(){
		WeightedNode testNode1 = new WeightedNode("testNode1", 1);
		WeightedNode testNode2 = new WeightedNode("testNode2", 2);
		testGraph.addNode(testNode1);
		testGraph.addNode(testNode2);
		testGraph.addEdge(testNode1, testNode2);
		try{
			testGraph.addEdge(testNode1, testNode2);
		}
		catch (Exception e){
			assert (e instanceof IllegalArgumentException);
			assert (e.getMessage() == "edge already exists");
		}
	}
	@Test
	public void graph_listNotModified(){
		WeightedNode testNode1 = new WeightedNode("testNode1", 1);
		WeightedNode testNode2 = new WeightedNode("testNode2", 2);
		testGraph.addNode(testNode1);
		testGraph.addNode(testNode2);
		testGraph.addEdge(testNode1, testNode2);
		Set s1 = testGraph.listNodes();
		try{
			s1.add(testNode1);
		}
		catch (Exception e){
			assert (e instanceof UnsupportedOperationException);
		}
		Set s2 = testGraph.listChildren(testNode1);
		try{
			s2.add(testNode1);
		}
		catch (Exception e){
			assert (e instanceof UnsupportedOperationException);
		}
	}

	@Test
	public void graph_graphEquals(){
		Graph testGraphDuplicate = new Graph<>();
		WeightedNode n1Duplicate = new WeightedNode("n1", 1);
		testGraphDuplicate.addNode(n1Duplicate);
		WeightedNode n2Duplicate = new WeightedNode("n2", 1);
		testGraphDuplicate.addNode(n2Duplicate);
		WeightedNode n3Duplicate = new WeightedNode("n3", 1);
		testGraphDuplicate.addNode(n3Duplicate);
		WeightedNode n4Duplicate = new WeightedNode("n4", 1);
		testGraphDuplicate.addNode(n4Duplicate);
		WeightedNode n5Duplicate = new WeightedNode("n5", 1);
		testGraphDuplicate.addNode(n5Duplicate);
		testGraphDuplicate.addEdge(n1Duplicate,n4Duplicate);
		testGraphDuplicate.addEdge(n2Duplicate,n5Duplicate);
		testGraphDuplicate.addEdge(n3Duplicate,n1Duplicate);
		testGraphDuplicate.addEdge(n3Duplicate,n2Duplicate);
		testGraphDuplicate.addEdge(n4Duplicate,n5Duplicate);
		assert (testGraph.equals(testGraphDuplicate));
	}

	@Test
	public void graph_inverseNotEqual(){
		Graph testGraphDuplicate = new Graph<>();
		WeightedNode n1Duplicate = new WeightedNode("n1", 1);
		testGraphDuplicate.addNode(n1Duplicate);
		WeightedNode n2Duplicate = new WeightedNode("n2", 1);
		testGraphDuplicate.addNode(n2Duplicate);
		WeightedNode n3Duplicate = new WeightedNode("n3", 1);
		testGraphDuplicate.addNode(n3Duplicate);
		WeightedNode n4Duplicate = new WeightedNode("n4", 1);
		testGraphDuplicate.addNode(n4Duplicate);
		WeightedNode n5Duplicate = new WeightedNode("n5", 1);
		testGraphDuplicate.addNode(n5Duplicate);
		testGraphDuplicate.addEdge(n4Duplicate, n1Duplicate);
		testGraphDuplicate.addEdge(n5Duplicate, n2Duplicate);
		testGraphDuplicate.addEdge(n1Duplicate, n3Duplicate);
		testGraphDuplicate.addEdge(n2Duplicate, n3Duplicate);
		testGraphDuplicate.addEdge(n5Duplicate, n4Duplicate);
		assert (!testGraph.equals(testGraphDuplicate));
	}
	/**************************/
	/****** path tests ********/
	/**************************/

	@Test
	public void path_nullGraphForPath(){
		testGraph = null;
		try{
			PathFinder<WeightedNode, WeightedNodePath> pf1 = new PathFinder<>(testGraph);
		}
		catch (Exception e){
			assert (e instanceof NullPointerException);
		}
	}

	@Test
	public void path_startNotInGraph(){
		// node not in the graph
		WeightedNode NodeNotInGraph = new WeightedNode("NodeNotInGraph", 1);

		//path finder setup
		Set<WeightedNodePath> starts = new HashSet<>();
		starts.add(new WeightedNodePath(n1));
		starts.add(new WeightedNodePath(NodeNotInGraph)); // a start node no in the graph
		Set<WeightedNode> goals = new HashSet<>();
		goals.add(n2);
		PathFinder<WeightedNode, WeightedNodePath> pf1 = new PathFinder<>(testGraph);

		try{
			pf1.findShortestPath(starts, goals);
		}
		catch (Exception e){
			assert (e instanceof IllegalArgumentException);
			assert (e.getMessage() == "start node not in the graph");
		}
	}

	@Test
	public void path_goalNotInGraph(){

		// node not in the graph
		WeightedNode NodeNotInGraph = new WeightedNode("NodeNotInGraph", 1);

		//path finder setup
		Set<WeightedNodePath> starts = new HashSet<>();
		starts.add(new WeightedNodePath(n1));
		Set<WeightedNode> goals = new HashSet<>();
		goals.add(n2);
		goals.add(NodeNotInGraph); // a start node no in the graph
		PathFinder<WeightedNode, WeightedNodePath> pf1 = new PathFinder<>(testGraph);

		try{
			pf1.findShortestPath(starts, goals);
		}
		catch (Exception e){
			assert (e instanceof IllegalArgumentException);
			assert (e.getMessage() == "goal node not in the graph");
		}
	}

	@Test
	public void path_testCodeCoverage(){
		n1 = new WeightedNode("n1", 5);
		n3 = new WeightedNode("n3", 1);
		Graph A = new Graph<WeightedNode>();
		A.addNode(n1);
		n2 = new WeightedNode("n2", 10);
		A.addNode(n2);
		Graph B = new Graph<WeightedNode>();
		Set bNodes = B.listNodes();
		assert (bNodes.equals(new HashSet()));

		A.addNode(n3);
		A.addEdge(n3,n1);
		B.addNode(n1);
		B.addNode(n2);
		B.addEdge(n2, n1);
		A.addEdge(n1, n3);
		A.addEdge(n1, n2);
		Set aNodes = A.listNodes();
		Set testSet = new HashSet();
		testSet.add(n1);
		testSet.add(n2);
		testSet.add(n3);
		assert (aNodes.equals(testSet));

		Set aN1Children = A.listChildren(n1);
		testSet = new HashSet();
		testSet.add(n2);
		testSet.add(n3);
		assert (aN1Children.equals(testSet));

		A.addEdge(n3,n3);
		Set aN3Children = A.listChildren(n3);
		testSet = new HashSet();
		testSet.add(n1);
		testSet.add(n3);
		assert (aN3Children.equals(testSet));

		PathFinder<WeightedNode,WeightedNodePath> pathFinder = new PathFinder<>(A);
		Set<WeightedNodePath> starts = new HashSet<>();
		starts.add(new WeightedNodePath(n3));
		Set<WeightedNode> goals = new HashSet<>();
		goals.add(n2);
		Path shortestPath = pathFinder.findShortestPath(starts, goals);
		Path<WeightedNode,WeightedNodePath> testPath = new WeightedNodePath(n3);
		testPath = testPath.extend(n1);
		testPath = testPath.extend(n2);
		assert (shortestPath.equals(testPath));
	}
}
