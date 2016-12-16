import Lab3Help.Path;

import java.util.*;

public class DijkstrasAlgorithm<E> implements Path<E> {

    private static final int NOPATH = 1000000;

    private Graph<E> graph;
    private List<E> path;
    private int pathLength;
    private Map<E, Integer> distance;
    private E endNode;

    public DijkstrasAlgorithm(Graph<E> graph){
        this.graph = graph;
    }

    @Override
    public void computePath(E from, E to) {
        endNode = to;

        List<Vertex<E>> vertexes = graph.getVertexes();
        Map<E, List<Edge<E>>> adjList = graph.getAdjList();

        //initialize
        distance = new HashMap<>();
        Map<E, E> previousNode = new HashMap<>();
        Set<E> visited = new HashSet<>();
        PriorityQueue<NodeCmpClass<E>> queue = new PriorityQueue<>();

        path = new ArrayList<>();

        for(Vertex<E> vertex : vertexes){
            distance.put(vertex.getName(), NOPATH); //Set the pathLength for each node to null (big value)
            previousNode.put(vertex.getName(), null); //Set previous node for all nodes to null
        }

        distance.put(from, 0);
        for(Vertex<E> v : vertexes){            // O(|V|)
            if(v.getName().equals(from)){
                queue.add(new NodeCmpClass<>(v)); //put the start node in the queue
            }
        }

        while(!queue.isEmpty()){ //will run for as long as the queue is empty
            NodeCmpClass<E> v = queue.remove(); //O(log v)
            if(!visited.contains(v.getName())){
                visited.add(v.getName());
                for(Edge<E> edge : adjList.get(v.getName())){ //O(|E|)

                    E destName = edge.getDestination().getName();
                    E vertexName = v.getName();

                    int newDistance = distance.get(vertexName) + edge.getWeight();

                    //if the next node is not visited and the distance to this node is greater than newDistance
                    if(!visited.contains(destName) && distance.get(destName) > newDistance){

                        previousNode.put(destName, vertexName);

                        NodeCmpClass<E> node = new NodeCmpClass<>(edge.getDestination());
                        node.setDistance(newDistance);
                        queue.add(node); //O(log v), we will add this node to the queue

                        distance.put(destName, newDistance);
                    }
                }
            }
        }
        pathLength = distance.get(to);
        E node = to;
        while(previousNode.get(node) != null){  //as long as our node has a previous
            path.add(node); //creates the path from 'from' to 'to'
            node = previousNode.get(node);
        }
        path.add(from); //manually add the starting node to the path since it has no previous node
        Collections.reverse(path); //reverse to get in the right order
    }

    @Override
    public Iterator<E> getPath() {
        if(path.size() == 0 || distance.get(endNode) == NOPATH){ //if size it 0 or if the distance to the end node is still too large
            return null;
        }
        return path.iterator();
    }

    @Override
    public int getPathLength() {
        return pathLength;
    }

    /**
     * Inner class for Dijkstra which holds a vertex and the distance to this node.
     * @param <E> any type
     */
    class NodeCmpClass<E> implements Comparable<NodeCmpClass<E>>{
        public Vertex<E> vertex;
        public int distance;

        public NodeCmpClass(Vertex<E> vertex){
            this.vertex = vertex;
        }

        public int getDistance(){
            return distance;
        }

        public void setDistance(int distance){
            this.distance = distance;
        }

        public E getName() {
            return vertex.getName();
        }

        @Override
        public int compareTo(NodeCmpClass<E> v) {
            if(v != null){
                if(this.distance < v.getDistance()){
                    return -1;
                } else if (this.distance > v.getDistance()){
                    return 1;
                }
            }
            return 0;
        }
    }
}