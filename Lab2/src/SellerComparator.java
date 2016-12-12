import java.util.Comparator;

public class SellerComparator implements Comparator<Bid> {

    @Override
    public int compare(Bid b1, Bid b2) {
        if(b1.getValue() < b2.getValue()){ //If b1 is selling for less money than b2 (higher priority)
            return 1;
        } else if(b1.getValue() > b2.getValue()){ //else if b1 is selling for more (lower priority);
            return -1;
        }

        return 0; //or if they are selling for the same amount of money (equal priority)
    }
}
