import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Orderboken: hur?
 */

public class Lab2 {


    /**
     * Loops through a list and places each bid into
     * designated priority queue.
     * If a bid is possible to make, then both buyer
     * and seller will be removed from the queues.
     * @param bids, list of bids, both buyers and sellers
     */
    @SuppressWarnings("unchecked")
    public static void trade(List<Bid> bids) {
        SellerComparator sComp = new SellerComparator();
        BuyerComparator bComp = new BuyerComparator();

        MyPriorityQueue sellerQueue = new MyBinaryHeap<>(sComp);
        MyPriorityQueue buyerQueue = new MyBinaryHeap<>(bComp);

        for(Bid bid : bids){
            switch (bid.getStatus()) {

                case S:
                    sellerQueue.insert(bid);
                    break;
                case K:
                    buyerQueue.insert(bid);
                    break;
                case NS:
                    sellerQueue.modifyKey(bid);
                    break;
                case NK:
                    buyerQueue.modifyKey(bid);
                    break;
                default:
                    System.err.println("Invalid bid status!");
                    break;
            }

            if(!sellerQueue.isEmpty() && !buyerQueue.isEmpty()){
                Bid buyer = (Bid) buyerQueue.findTop();
                Bid seller = (Bid) sellerQueue.findTop();

                if(buyer.getValue() >= seller.getValue()){
                    buyerQueue.deleteTop();
                    sellerQueue.deleteTop();
                    System.out.println(buyer.getName() + " köper från " + seller.getName() + " for " + buyer.getValue());
                }
            }
        }


        System.out.print("\nOrderbok:\nSäljare: ");

        while(!sellerQueue.isEmpty()){
            System.out.println(sellerQueue.deleteTop());
        }

        System.out.print("\nKöpare: ");
        while(!buyerQueue.isEmpty()){
            System.out.println(buyerQueue.deleteTop());
        }

        System.out.println();
    }

    /**
     * Parses a bid.
     * @param s The string that should be parsed.
     * @throws MalformedBid If the bid cannot be parsed.
     */

    public static Bid parseBid(String s) throws MalformedBid {
        Matcher m = Pattern.compile(
                "\\s*(\\S+)\\s+" +
                        "(?:(K|S)\\s+(\\d+)|(NS|NK)\\s+(\\d+)\\s+(\\d+))" +
                        "\\s*").matcher(s);

        if (m.matches()) {
            if (m.group(2) == null) {
                String name = m.group(1); //The name of the buyer/seller
                String status = m.group(4); //NK or NS
                int newValue = Integer.parseInt(m.group(6)); //new value
                return new Bid(name, Bid.Status.valueOf(status), newValue);

            } else {
                String name = m.group(1); //The name of the buyer/seller
                String status = m.group(2); //K or S
                int value = Integer.parseInt(m.group(3)); //The value
                return new Bid(name, Bid.Status.valueOf(status), value);
            }
        } else {
            throw new MalformedBid(s);
        }
    }

    /**
     * Parses line-separated bids from the given Readable thing.
     * @param input The character stream that should be parsed.
     * @throws MalformedBid If some bid couldn't be parsed.
     */

    public static List<Bid> parseBids(Readable input) throws MalformedBid {
        ArrayList<Bid> bids = new ArrayList<>();
        Scanner s = new Scanner(input);

        while (s.hasNextLine()) {
            bids.add(parseBid(s.nextLine()));
        }

        return bids;
    }

    /**
     * Exception class for malformed bids.
     */

    public static class MalformedBid extends Exception {
        MalformedBid(String bid) {
            super("Malformed bid: " + bid + ".");
        }
    }

    /**
     * Prints usage info.
     */

    public static void usageInfo() {
        System.err.println("Usage: java Aktiehandel [<file>]");
        System.err.println("If no file is given, then input is " +
                "read from standard input.");
    }

    /**
     * ...
     */

    public static void main(String[] args) {

        if (args.length >= 2) {
            usageInfo();
        } else {
            try {
                BufferedReader r;
                if (args.length == 0) {
                    // Read from stdin.
                    r = new BufferedReader(new InputStreamReader(System.in));
                } else {
                    // Read from a named file.
                    r = new BufferedReader(new FileReader(args[0]));
                }

                try {
                    List<Bid> bids = parseBids(r);
                    trade(bids);
                } finally {
                    r.close();
                }
            } catch (MalformedBid e) {
                System.err.println(e.getMessage());
                usageInfo();
            } catch (FileNotFoundException e) {
                System.err.println("File not found: " + args[0] + ".");
                usageInfo();
            } catch (IOException e) {
                System.err.println(e.getMessage());
                usageInfo();
            }
        }
    }
}