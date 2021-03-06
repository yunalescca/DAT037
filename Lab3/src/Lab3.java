import Lab3Help.*;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class Lab3 {

    public static void main(String[] args){
        Lab3File lab3File = new Lab3File();

        try {
            List<BStop> bStops = lab3File.readStops(args[0]);
            List<BLineTable> bLineTables = lab3File.readLines(args[1]);
            String start = args[2];
            String end   = args[3];

            /*List<BStop> bStops = lab3File.readStops("stops-gbg.txt");
            List<BLineTable> bLineTables = lab3File.readLines("lines-gbg.txt");
            String start = "Angered";
            String end   = "AxelDahlströmsTorg";*/

            Path<String> path = new DijkstraStringPath(bStops, bLineTables);
            new GUI(bStops, bLineTables, path);

            path.computePath(start, end);

            if(path.getPath() == null){
                System.out.println("Det finns ingen väg mellan " + start + " och " + end);
            } else if(start.equals(end)){
                System.out.println(path.getPathLength() + "\n" + start);
            } else{
                System.out.println(path.getPathLength());
                Iterator<String> pathIterator = path.getPath();
                while(pathIterator.hasNext()){
                    System.out.println(pathIterator.next());
                }
            }

        } catch (MalformedData malformedData) {
            malformedData.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
