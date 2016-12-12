import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class MyBinaryHeap<E> implements MyPriorityQueue<E> {

    private ArrayList<E> binHeapList;
    private Comparator<? super E> comp;
    private Map<E, Integer> map;

    /**
     * Creates a new heap with a specified comparator
     * @param comp, the comparator
     */
    public MyBinaryHeap(Comparator<? super E> comp){
        this.comp = comp;
        binHeapList = new ArrayList<>();
        map = new HashMap<>();
    }

    /**
     * @return the first element in the array/the root in the binary heap
     */
    @Override
    public E findTop() {
        if(isEmpty())
        {
            throw new NullPointerException();
        }
        return binHeapList.get(0);
    }

    /**
     * Insert an element at the end of the list, bubble up if necessary
     * EXAMPLE: If list size is 1, we only have one element and we don't need to bubble up.
     * If list size is greater than 1, compare to parent.
     * If Joe is selling for less than Bob, then Joe should be before Joe in the list.
     * That is, if the compare method between Joe and Bob returns 1, we know that Joe is selling for LESS.
     * OR if compare between buyer Claire and Liz returns 1, we know that Claire is buying for MORE than Liz
     * @param element, the element we want to insert
     */
    @Override
    public void insert(E element) {

        binHeapList.add(element); //Place the element in the arraylist and check the index of it;
        int i = binHeapList.size() - 1;
        map.put(element, i);

        if(binHeapList.size() > 1
                && comp.compare(element, binHeapList.get((i-1)/2)) > 0){ //if we have more than 1 element, and if child has greater value than parent
                bubbleUp(element, i);
        }
    }

    /**
     * Delete old top, replace it with the bottom child, bubble down and return old top
     * @return old top (top before replaced)
     */
    @Override
    public E deleteTop() {
        if(isEmpty()){
            throw new NullPointerException();
        }

        E oldTop = binHeapList.get(0); //save the old top
        int indexBottom = binHeapList.size() - 1;

        E bottom = binHeapList.get(indexBottom); //index of bottom leaf/child
        binHeapList.set(0, bottom); //Place it on top
        int newIndex = binHeapList.indexOf(bottom);

        map.put(bottom, newIndex);
        map.remove(oldTop);

        binHeapList.remove(indexBottom);

        bubbleDown(bottom, newIndex); //and bubble down until child is in correct spot

        return oldTop;
    }

    /**
     * If data is in the hashmap, bubble up or down based on how
     * we changed its priority
     * @param data, when we modify its value/change priority
     */
    @Override
    public void modifyKey(E data) {
        if(contains(data)){
            int i = map.get(data);
            binHeapList.set(i, data); //replace the element in the list with the new value
            if(binHeapList.size() > 1){ //decide whether to bubble up, down, or do nothing
                if(comp.compare(data, binHeapList.get((i-1)/2)) > 0) {
                    bubbleUp(data, i);
                } else {
                    bubbleDown(data, i);
                }
            }
        } else {
            System.err.println("Data not in map");
        }
    }

    /**
     * With the help of contains, the search for the data will only take O(1) time
     * @param data, what we wearch for
     * @return true if data is in hash map
     */
    @Override
    public boolean contains(E data){
        return map.containsKey(data);
    }

    /**
     * Size of array/binary heap
     * @return
     */
    @Override
    public int size(){
        return binHeapList.size();
    }

    /**
     * Get the element on index i
     * @param i, index
     * @return the element on index i
     */
    @Override
    public E get(int i){
        return binHeapList.get(i);
    }

    /**
     * When bubble up, compare to parent. If child is more prioritized than parent,
     * swap places. Do this until child is in correct spot.
     * @param element
     * @param i
     */
    private void bubbleUp(E element, int i) {
        while(comp.compare(element, binHeapList.get((i - 1) / 2)) > 0){
            E parent = binHeapList.get((i - 1) / 2);

            swap(parent, element);
            i = (i-1)/2;
        }
    }

    /**
     * Bubbles down an element until they are all in the desired order
     * Sellers: when the one selling for the smallest amount of money is on top
     * Buyers: When the one buying for the most amount of money is on top
     */
    private void bubbleDown(E element, int i) {
        boolean doneSwapping = false;

        while(!doneSwapping){
            if(hasLeftChild(i)){
                E leftChild = binHeapList.get(i*2+1);

                if(hasRightChild(i)){
                    E rightChild = binHeapList.get(i*2+2);

                    if((comp.compare(rightChild, element) > 0) || comp.compare(leftChild, element) > 0){
                        if(comp.compare(rightChild, leftChild) > 0){ //Choose right child if it's more prioritized (if selling price is lower/buying price is higher than the right child)
                            swap(binHeapList.get(i), rightChild);
                            i = i*2+2;

                        } else { //choose left child
                            swap(binHeapList.get(i), leftChild);
                            i = i*2+1;
                        }
                    } else {
                        doneSwapping = true;
                    }
                }

                else if (comp.compare(leftChild, binHeapList.get(i)) > 0) { //if no right child exists, and if left child is more prioritized than parent
                    swap(binHeapList.get(i), leftChild);
                } else {
                    doneSwapping = true;
                }
            } else {
                doneSwapping = true;
            }
        }
    }

    /**
     * Swap places on e1 and e2
     * @param e1 element to be bubbled down
     * @param e2 element to switch place with e1
     */
    private void swap(E e1, E e2){
        int e1index = map.get(e1);
        int e2index = map.get(e2);

        binHeapList.set(e1index, e2);
        map.put(e2, e1index);

        binHeapList.set(e2index, e1);
        map.put(e1, e2index);
    }

    /**
     * @param i the parent
     * @return true if left child exists
     */
    private boolean hasLeftChild(int i){
        if((2*i+1) < binHeapList.size()) {
            if(binHeapList.get(2*i+1) != null){
                return true;
            }
        }
        return false;
    }

    /**
     * @param i the parent
     * @return true if right child exists
     */
    private boolean hasRightChild(int i){
        if((2*i+2) < binHeapList.size()) {
            if(binHeapList.get(2*i+2) != null){
                return true;
            }
        }
        return false;
    }

    /**
     * @return true if heap is not empty
     */
    @Override
    public boolean isEmpty() {
        return (binHeapList.size() == 0);
    }
}