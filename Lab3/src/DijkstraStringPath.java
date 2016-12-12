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

        for(BStop stop : bStops){
            vertexes.add(new Vertex<>(stop.getName()));
        }

        for (BLineTable bLineTable : bLineTables) {
            BLineStop[] stops = bLineTable.getStops();
            for (int j = 0; j < stops.length; j++) {

                Vertex<String> start, dest;
                int weight = stops[j].getTime();

                if (weight > 0) {
                    start = new Vertex<>(stops[j - 1].getName());
                    dest =  new Vertex<>(stops[j].getName());
                    edges.add(new Edge<>(start, dest, weight));
                } else {
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