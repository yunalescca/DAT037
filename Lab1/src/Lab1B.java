import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Main program as Lab1A
 */
public class Lab1B {

    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<Integer> list = new ArrayList<>();

        int element = Integer.valueOf(args[0]); //The element we're searching for

        String filename = args[1]; //The file we search in

        File file = new File(filename);
        Scanner readFile = new Scanner(file); //Scanner reads the file

        while(readFile.hasNextInt()){
            try {
                int i = readFile.nextInt();
                list.add(i); //Puts the content in the arraylist
            } catch(InputMismatchException e){
                System.out.println("Error in file: cannot interpret as integer");
            }
        }

        Integer[] array = new Integer[list.size()]; //creates an array with the same size as the arraylist

        for (int j = 0; j < list.size(); j++){
            array[j] = list.get(j); //puts the elements in the array
        }

        MySortedArray<Integer> mySortedArray = new MySortedArray<>(array);

        System.out.println(mySortedArray.member(element));
    }

}
