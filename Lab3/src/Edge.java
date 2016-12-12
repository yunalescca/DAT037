

public class Edge<E>{
    private Vertex<E> source, destination;
    private int weight;

    public Edge(Vertex<E> source, Vertex<E> destination, int weight){
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    public Vertex<E> getSource() {
        return source;
    }

    public Vertex<E> getDestination() {
        return destination;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public boolean equals(Object e){
        if(e instanceof Edge<?>){
            Edge<?> e1 = (Edge<?>) e;
            if(this.getSource().getName().equals(e1.getSource().getName())
                    && this.getDestination().getName().equals(e1.getDestination().getName())){
                return true;
            }
        }
        return false;
    }
}