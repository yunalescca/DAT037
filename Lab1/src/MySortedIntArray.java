/**
 * Class to check whether or not an element exists in a given array
 */
public class MySortedIntArray implements MyIntSet {

    private int[] arr;

    public MySortedIntArray(int[] arr){
        this.arr = arr;
    }

    /**
     * Method that checks if a given element exists in an array of ints.
     * @param element is always an int
     * @return true or false whether or not the element was found in the array
     */
    @Override
    public boolean member(int element) {

        int low = 0;
        int high = arr.length-1; //high is given the index of the last element in the array

        while (low <= high){
            int mid = low + ((high-low) / 2); //To not cause arithmetic overflow where we would add two great numbers together and then try
                                             //to divide that number by two, we subtract in order to not a too great number by two.

            if(arr[mid] < element) {
                low = mid + 1; //If the desired element is higher than the middle one, low will increase by one as to narrow down the search field
            } else if (arr[mid] > element) {
                high = mid - 1; //If -||- lower than the mid one, high will decrease by one, -||-. This will result in
                                //low growing bigger as high gets smaller, and eventually low > high, if the desired element doesn't exist.
            } else {
                return true;
            }
        }

        return false;
    }
}
