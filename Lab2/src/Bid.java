import java.util.Objects;

/**
 * Bid keeps track of every bidder and their name,
 * status (enum) and value.
 * Overrides equals and hashCode so mapping in hash table
 * only takes the name of the bidder into consideration
 */
public class Bid {

    public enum Status {K, S, NK, NS}

    private String name;
    private int value;
    private Status status;

    public Bid(String name, Status status, int value){
        this.name  = name;
        this.status = status;
        this.value = value;
    }

    public Status getStatus(){
        return status;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object b){
        if(b.getClass() == Bid.class){
            Bid bid = (Bid) b;
            return (Objects.equals(name, bid.name)); //name == bid.name returnerar false
        }
        return false;
    }

    @Override
    public int hashCode(){
        int result = 17;
        result = 31 * (result + name.hashCode());
        return result;
    }

    @Override
    public String toString(){
        return (name + " " + value);
    }
}
