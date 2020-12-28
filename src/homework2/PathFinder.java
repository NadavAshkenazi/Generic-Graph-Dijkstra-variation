package homework2;

import java.util.*;

/**
 * A PathFinder is a class which allows you to find the minimal path predefine start to predefine end
 * @param <N> type of node that allows compression
 * @param <P> type of path with costs
 */
public class PathFinder<N extends Comparable<N>, P extends Path<N,P>>  {

    // Abs. Function
    //	* A PathFinder is a class which allows you to find the minimal path predefine start to predefine end using a variation of the Dijkstra algorithm
    //	* Graph represent the graph in which want to find the minimal path
    //  * paths represent the collection of the minimal cost sub paths in the graph
    //  * active represent the collection of minimal paths to all the nodes that have not been checked yet
    //  * finished represent all the nodes that have already been checked

    // Rep. Invariant:
    // * graph, paths, active and finished are not null
    // * all nodes in finished and are in the graph and all paths end in active are contained in the graph



    Graph<N> graph;
    Map<N, P> paths;
    PriorityQueue<P> active;
    Set<N> finished;

    private void checkRep(){
        assert (graph != null);
        assert (paths != null);
        assert (active != null);
        assert (finished != null);
        Iterator iterator = finished.iterator();
        while (iterator.hasNext()){
            N node = (N)iterator.next();
            assert (graph.contains(node));
        }
        iterator = active.iterator();
        while (iterator.hasNext()){
            N node = ((P)iterator.next()).getEnd();
            assert (graph.contains(node));
        }
        iterator = paths.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry entry = (Map.Entry) iterator.next();
            N node = ((P)entry.getValue()).getEnd();
            assert (graph.contains(node));
            node = (N)entry.getKey();
            assert (graph.contains(node));
        }
    }

    /**
     * Creates a pathFinder
     * @param graph - a graph of type N
     * @throws NullPointerException if graph is null
     */
    public PathFinder(Graph<N> graph) throws NullPointerException{
        this.graph = graph;
        if (graph == null)
            throw new NullPointerException();
        paths = new HashMap<N,P>();
        active = new PriorityQueue();
        finished = new HashSet<N>();
        checkRep();
    }

    private boolean checkIfActive(N node){
        checkRep();
        Iterator iterator = active.iterator();
        while(iterator.hasNext()){
            if (((P)iterator.next()).getEnd().equals(node)){
                checkRep();
                return true;
            }
        }
        checkRep();
        return false;
    }

    /**
     *returns the path with minimal cost that starts with one of the prefixes in starts and ends with one of the nodes
     *  in goals
     * @param starts - set of prefixes paths
     * @param goals - set of end goals nodes
     * @return the path with minimal cost from starts -> goals. if no path was found returns null
     */
    public Path findShortestPath(Set<P> starts, Set<N> goals){
        checkRep();
        Iterator startsIterator = starts.iterator();
        while (startsIterator.hasNext()){
            P path = (P)(startsIterator.next());
            N node = path.getEnd();
            if (!this.graph.contains(node)){
                throw new IllegalArgumentException("start node not in the graph");
            }
        }

        Iterator goalsIterator = goals.iterator();
        while (goalsIterator.hasNext()){
            N node = (N)(goalsIterator.next());
            if (!this.graph.contains(node)){
                throw new IllegalArgumentException("goal node not in the graph");
            }
        }

        startsIterator = starts.iterator();
        while (startsIterator.hasNext()){
            P path = (P)(startsIterator.next());
            paths.put(path.getEnd(), path);
            active.add(path);
        }
        while (!active.isEmpty()){
            P minNodePath = active.poll();
            N minNode = minNodePath.getEnd();
            if (goals.contains(minNode)) {
                checkRep();
                return minNodePath;
            }
            Set children = new HashSet(graph.listChildren(minNode));
            Iterator childrenIterator = children.iterator();
            while (childrenIterator.hasNext()){
                N child = (N)(childrenIterator.next());
                if(!finished.contains(child) && !checkIfActive(child))
                    active.add(minNodePath.extend(child));
            }
            finished.add(minNode);
        }
        checkRep();
        return null;
    }
}
