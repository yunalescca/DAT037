import java.util.*;

public class Graph<E> {

    private List<Edge<E>> edges;
    private List<Vertex<E>> vertexes;
    private Map<E, Set<Edge<E>>> adjList;

    public Graph(List<Edge<E>> edges, List<Vertex<E>> vertexes){
        this.edges = edges;
        this.vertexes = vertexes;
        adjList = new HashMap<>();
        generateVertices();
    }

    private void generateVertices(){
        for(Vertex<E> vertex : vertexes){
            System.out.println(vertex.getName());
            Set<Edge<E>> adjacent = new HashSet<>();
            for(Edge<E> edge : edges){
                if(edge.getSource().equals(vertex)){
                    if(!edge.getDestination().getName().equals(vertex.getName())){ //TODO innan lade den in Angered i Angereds grannlista, det ska den väl inte göra? Men jag vill väl inte heller att typ Gammelstadstorget ligger med två gånger?
                        //nu när den är ett set och jag har equals i edge bör den väl inte lägga in samma granne två gånger?
                        adjacent.add(edge);
                        System.out.print(edge.getDestination().getName() + ", ");
                    }
                }
            }
            System.out.println();
            System.out.println();
            adjList.put(vertex.getName(), adjacent);
        }
    }

    public List<Edge<E>> getEdges(){
        return edges;
    }

    public List<Vertex<E>> getVertexes(){
        return vertexes;
    }

    public Map<E, Set<Edge<E>>> getAdjList() { return adjList; }

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