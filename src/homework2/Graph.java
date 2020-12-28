package homework2;

import java.util.*;

public class Graph<T> {

    // Abs. Function
    //	* A Graph is a representation of a directed graph
    //	* All the nodes in the graph are represented as entries in the set called nodes
    //  * An edge is rep as the existence of the child node in the hashset located under the parent key in the adjacencyList map

    // Rep. Invariant:
    // * adjacencyList and nodes are not null
    // * no node appears twice under nodes - guaranteed under the hashSet spec
    // * no node appear twice as a child of the same parent - guaranteed under the hashSet spec
    // * no node appear twice as a parent in the adjacencyList - guaranteed under the hashMap spec
    // * All parents and children in adjacencyList appear as nodes in nodes Set

    private HashMap<T, HashSet<T>> adjacencyList;
    private HashSet<T> nodes;


    private void checkRep(){
        assert(adjacencyList != null);
        assert(nodes != null);
        Iterator iterator = adjacencyList.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry entry = (Map.Entry) iterator.next();
            assert(nodes.contains(entry.getKey()));
            assert(nodes.containsAll((HashSet<T>)entry.getValue()));
        }
    };

    /**
     * @param node - node to check if in the graph
     * @return true if the node is in the graoh false otherwise
     */
    public boolean contains(T node){
        checkRep();
        if (node == null)
            return false;
        return nodes.contains(node);
    }

    /**
     * Constructs a new Graph that contains nodes of immutables of type T.
     * @effects Constructs a new empty Graph.
     **/
    public Graph(){
        this.adjacencyList = new HashMap<>();
        this.nodes = new HashSet<>();
        checkRep();
    }

    /**
     * @param node - node of immutable type T to be added
     * @requires node != NULL and is not in the graph already
     * @effects adds node to the graph without any edges connected to it
     */
    public void addNode(T node) throws IllegalArgumentException, NullPointerException{
        checkRep();
        if (node == null) {
            checkRep();
            throw new NullPointerException();
        }
        if (this.contains(node)){
            checkRep();
            throw new IllegalArgumentException("node already in the graph");
        }
        this.nodes.add(node);
        this.adjacencyList.put(node, new HashSet<T>());
        checkRep();
    }

    /**
     * @param parent - node from which the edge exits
     * @param child - node to which the edge enters
     * @requires parent != NULL and is in the graph already
     *           child != NULL and is in the graph already
     *           child is not already a child of parent
     * @effects adds a directed edge from parent to child
     */
    public void addEdge(T parent, T child){
        checkRep();
        if (parent == null) {
            checkRep();
            throw new IllegalArgumentException("node is null");
        }
        if (child == null) {
            checkRep();
            throw new IllegalArgumentException("child is null");
        }
        if (!this.contains(parent)){
            checkRep();
            throw new IllegalArgumentException("parent not in the graph");
        }
        if (!this.contains(child)){
            checkRep();
            throw new IllegalArgumentException("child not in the graph");
        }
        if (this.adjacencyList.get(parent).contains(child)){
            checkRep();
            throw new IllegalArgumentException("edge already exists");
        }
        this.adjacencyList.get(parent).add(child);
        checkRep();
    };

    /**
     * 
     * @return a set of all the nodes in the graph
     */
    public Set<T> listNodes(){
        checkRep();
        Set returnNodes = Collections.unmodifiableSet(nodes);
        return returnNodes;
    }

    /**
     *
     * @param parent the node whose children we want
     * @requires parent != NULL and is in the graph already
     * @return a set of all the childs of node parent in the graph
     */
    public Set<T> listChildren(T parent){
        checkRep();
        if (parent == null){
            checkRep();
            throw new IllegalArgumentException("node is null");
        }
        if (!this.contains(parent)){
            checkRep();
            throw new IllegalArgumentException("parent not in the graph");
        }
        Set returnChildren = Collections.unmodifiableSet(this.adjacencyList.get(parent));
        checkRep();
        return returnChildren;
    }

    /**
     * Compares the specified Object with this graph for equality.
     * @return true iff (o instanceof Graph) &&
     *         and all nodes and edges are the same.
     **/
    public boolean equals(Object o){
        checkRep();
        if (!(o instanceof Graph))
            return false;
        Graph<T> graph = (Graph<T>) o;
        checkRep();
        return this.adjacencyList.equals(graph.adjacencyList) && this.nodes.equals(graph.nodes);
    }

    /**
     * Returns a hash code for this.
     * @return a hash code for this.
     **/
    public int hashCode(){
        checkRep();
        return Objects.hash(this.adjacencyList, this.nodes);
    }
}
