/**
 * Simple generic interface for a
 * priority queue, with standard functions
 * @param <E>
 */
public interface MyPriorityQueue<E> {

    E findTop();

    void insert(E element);

    E deleteTop();

    void modifyKey(E data);

    boolean isEmpty();

    boolean contains (E data);

    int size();

    E get(int i);
}
