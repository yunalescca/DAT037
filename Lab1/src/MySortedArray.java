/**
 * Class to check whether or not an element exists in an array. Uses generic types
 * so it is possible to compare more than just ints.
 */
public class MySortedArray<E extends Comparable<? super E>> implements MySet<E> {

    E[] arr;

    public MySortedArray(E[] arr){
        this.arr = arr;
    }

    /**
     * This method does the same thing as the one in MySortedIntArray,
     * the simple difference is that we're using an array of Es now, instead of ints.
     * @param element is a generic type, but must implement the Comparable interface
     * @return true or false based on if the element exists in the array
     */
    @Override
    public boolean member(E element) {

        int low = 0;
        int high = arr.length-1;

        while(low <= high){
            int mid = low + ((high-low) / 2);

            if(arr[mid].compareTo(element) < 0){
                low = mid+1;
            } else if(arr[mid].compareTo(element) > 0){
                high = mid - 1;
            } else {
                return true;
            }
        }

        return false;
    }
}
