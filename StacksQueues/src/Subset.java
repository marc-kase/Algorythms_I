import edu.princeton.cs.algs4.*;

import java.util.Iterator;

public class Subset {
    public static void main(String[] args) {
        int limit = 0;
        if (args.length > 0) {
            limit = Integer.parseInt(args[0]);
        }

        RandomizedQueue<String> rq = new RandomizedQueue<>();
        int i = 0;
        while (i++ < limit) {
            String s = StdIn.readString();
            rq.enqueue(s);
        }

        Iterator<String> iterator = rq.iterator();
        while (iterator.hasNext()) {
            StdOut.println(iterator.next());
        }
    }
}
