import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private void generateVertices(){
        for(Vertex<E> vertex : vertexes){
            List<Edge<E>> adjacent = new ArrayList<>();
            for(Edge<E> edge : edges){
                if(edge.getSource().equals(vertex)){
                    adjacent.add(edge);
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

    //Temp method
    private String getEdge(Vertex<E> vertex) {
        String string = "";
        for(Edge<E> edge : edges){
            if(edge.getSource().equals(vertex) || edge.getDestination().equals(vertex)) {
                string += "Source : " + edge.getSource().getName() + ", dest: " + edge.getDestination().getName() +
                        ", weight: " + edge.getWeight() + "\n";
            }
        }
        return string;
    }

    //Temp method
    @Override
    public String toString(){
        System.out.println("VERTEXES");
        for(Vertex<E> vertex : vertexes){
            System.out.println(vertex.getName());
            System.out.println(getEdge(vertex));
        }
        return "";
    }
}