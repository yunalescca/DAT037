import Lab3Help.BLineStop;
import Lab3Help.BLineTable;
import Lab3Help.BStop;
import Lab3Help.Path;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class DijkstraStringPath implements Path<String>{

    private DijkstrasAlgorithm<String> dijsktra;

    public DijkstraStringPath(List<BStop> bStops, List<BLineTable> bLineTables){
        ArrayList<Vertex<String>> vertexes = new ArrayList<>();
        ArrayList<Edge<String>> edges = new ArrayList<>();

        //Add all bus stops to the vertex list
        for(BStop stop : bStops){
            vertexes.add(new Vertex<>(stop.getName()));
        }

        //Add to edges list, the start node, end node, and the distance between
        for (BLineTable bLineTable : bLineTables) {
            BLineStop[] stops = bLineTable.getStops();
            for (int j = 0; j < stops.length; j++) {

                Vertex<String> start, dest;
                int weight = stops[j].getTime();

                if (j > 0) {
                    start = new Vertex<>(stops[j - 1].getName());
                    dest =  new Vertex<>(stops[j].getName());

                    Edge<String> edge = new Edge<>(start, dest, weight);

                    for(int i = 0; i < edges.size(); i++){
                        Edge<String> temp = edges.get(i);

                        if(temp.equals(edge)){ //if there already exists an edge but with a higher weight, remove it
                            if(edge.getWeight() < temp.getWeight()){
                                edges.remove(i);
                            }
                        }
                    }
                    edges.add(edge);

                } else { //if j == 0 then we are at our start node
                    start = new Vertex<>(stops[j].getName());
                    edges.add(new Edge<>(start, start, weight));
                }
            }
        }
        Graph<String> graph = new Graph<>(edges, vertexes);
        dijsktra = new DijkstrasAlgorithm<>(graph);
    }

    @Override
    public void computePath(String from, String to){
        dijsktra.computePath(from, to);
    }

    @Override
    public Iterator<String> getPath(){
        return dijsktra.getPath();
    }

    @Override
    public int getPathLength(){
        return dijsktra.getPathLength();
    }
}