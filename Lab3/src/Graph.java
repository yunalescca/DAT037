import java.util.*;

public class Graph<E> {

    private List<Edge<E>> edges;
    private List<Vertex<E>> vertexes;
    private Map<E, List<Edge<E>>> adjList;

    public Graph(List<Edge<E>> edges, List<Vertex<E>> vertexes){
        this.edges = edges;
        this.vertexes = vertexes;
        adjList = new HashMap<>();
        generateVertices();
    }

    /**
     * For each vertex, see if the edge has this as source. If yes, add the whole edge to the adjacency list.
     */
    private void generateVertices(){
        for(Vertex<E> vertex : vertexes){
            List<Edge<E>> adjacent = new ArrayList<>();
            for(Edge<E> edge : edges){
                if(edge.getSource().equals(vertex)){
                    if(!edge.getDestination().getName().equals(vertex.getName())
                            && !adjacent.contains(edge)){ //Only keep one of the same neighbour
                        adjacent.add(edge);
                    }
                }
            }
            adjList.put(vertex.getName(), adjacent);
        }
    }

    public List<Edge<E>> getEdges(){
        return edges;
    }

    public List<Vertex<E>> getVertexes(){
        return vertexes;
    }

    public Map<E, List<Edge<E>>> getAdjList() { return adjList; }
}