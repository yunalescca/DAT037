import Lab3Help.Path;

import java.util.*;

public class DijkstrasAlgorithm<E> implements Path<E> {

    private List<Vertex<E>> vertexes;
    private Map<E, List<Edge<E>>> adjList;

    private Map<E, Integer> distance;
    private Map<E, E> previousNode;
    private Set<E> visited;
    private PriorityQueue<NodeCmpClass<E>> queue;

    private List<E> path;
    private int pathLength;

    public DijkstrasAlgorithm(Graph<E> graph){
        vertexes = graph.getVertexes();
        adjList = graph.getAdjList();

        distance = new HashMap<>();
        previousNode = new HashMap<>();
        visited = new HashSet<>();
        queue = new PriorityQueue<>();
        path = new ArrayList<>();

        for(Vertex<E> vertex : vertexes){
            distance.put(vertex.getName(), 100000); //Set the pathLength for each node to null
            previousNode.put(vertex.getName(), null); //Set previous node for all nodes to null
        }
    }

    @Override
    public void computePath(E from, E to) {
        distance.put(from, 0);
        for(Vertex<E> v : vertexes){            // O(|V|) //TODO fel här med Vertex?, ska det vara inre klass ist?
            if(v.getName().equals(from)){
                queue.add(new NodeCmpClass<>(v)); //bara v
            }
        }

        while(!queue.isEmpty()){
            NodeCmpClass<E> v = queue.remove();
            if(!visited.contains(v.getName())){
                visited.add(v.getName());
                for(Edge<E> edge : adjList.get(v.getName())){ //TODO uppdaterar v fel?

                    E destName = edge.getDestination().getName();
                    E vertexName = v.getName();


                    if(!visited.contains(destName) &&
                            distance.get(destName) > (distance.get(vertexName)) + edge.getWeight()){

                        distance.put(destName, (distance.get(vertexName)) + edge.getWeight());
                        previousNode.put(destName, vertexName);

                        NodeCmpClass<E> node = new NodeCmpClass<>(edge.getDestination()); //TODO behöver jag skapa en ny nodecmpclass?
                        node.setDistance(distance.get(vertexName) + edge.getWeight());
                        queue.add(node); //TODO vill ha den här istället, behöver ändra typ i PriorityQueue, och då förmodligen på rad 36-38

                        //edge.getDestination().setDistance((distance.get(v.getName())) + edge.getWeight());
                        //queue.add(edge.getDestination());
                    }
                }
            }
        }

        pathLength = distance.get(to);
        E node = to;
        while(previousNode.get(node) != null){ //creates the path from 'from' to 'to'
            path.add(node);
            node = previousNode.get(node);
        }
        Collections.reverse(path);



        /* FÖRELÄSNING
        ==============
        d: lagrar hittils kortaste vägen till alla noder, empty map from node indexes (by default infinity)
        p: lagrar föregående nod för hittills kortaste vägen, empty map from node indexes
        k: lagrar om kortaste vägen till noden är känd, empty set of node indexes
        q: new empty priority queue

        d[s] = 0;
        q.insert(s,0)
        while q is non-empty do
            v = q.delete-min()
            if v not in k then
                insert v into k
                for each direct successor v' of v do
                    if(v' not in k) and d[v'] > d[v] + c(v', v) then .... ( c = alternativa vägen via v)
                        d[v'] = d[v] + c(v', v)
                        p[v'] = v
                        q.insert(v', d[v'])
        return (d,p)*/
    }

    @Override
    public Iterator<E> getPath() {
        if(path.size() == 0){
            return null;
        }
        return path.iterator();
    }

    @Override
    public int getPathLength() {
        return pathLength;
    }

    class NodeCmpClass<E> implements Comparable<E>{
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

        @SuppressWarnings("unchecked") //TODO fix
        @Override
        public int compareTo(E v){
            if(v instanceof NodeCmpClass){ //<E>?
                NodeCmpClass<?> v1 = (NodeCmpClass<?>) v;
                if(this.distance < v1.getDistance()){
                    return 1;
                } else if (this.distance > v1.getDistance()){
                    return -1;
                }
            }
            return 0;
        }

        public E getName() {
            return vertex.getName();
        }
    }
}