import java.util.Comparator;

public class BuyerComparator implements Comparator<Bid> {

    @Override
    public int compare(Bid b1, Bid b2) {
        if(b1.getValue() > b2.getValue()){ //If b1 is willing to pay more money than b2 (higher priority)
            return 1;
        } else if(b1.getValue() < b2.getValue()){ //If b1 is buying for less (lower priority)
            return -1;
        }

        return 0; //If they are buying for an equal amount of money, then order does not matter (equal priority)
    }
}
