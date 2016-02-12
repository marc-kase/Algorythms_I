import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

public class RandomizeQueueTest {
    RandomizedQueue<String> rq;

    public static void pause(long pause) {
        try {
            Thread.sleep(pause);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Before
    public void init() {
        rq = new RandomizedQueue<>();

        System.out.println("============================");
        System.out.println("\nInit:");
        for (int i = 0; i < 4; i++) {
            System.out.println("enque: " + i);
            rq.enqueue(String.valueOf(i));
        }
        pause(300L);
    }

    @Test
    public void notEmpty() {
        System.out.println("\n2. Not empty:");
        for (int i = 0; i < 4; i++) {
            System.out.println("deque: " + rq.dequeue());
        }

        System.out.println("enque: 5");
        rq.enqueue("5");

        System.out.println("sample: " + rq.sample());

        Assert.assertEquals(true, rq.sample() != null);
        pause(300L);
    }

    @Test
    public void iteration() {
        System.out.println("\nIteration:");

        Iterator<String> iterator = rq.iterator();
        while (iterator.hasNext()) {
            System.out.println("next is: " + iterator.next());
        }

        Assert.assertEquals(true, rq.sample() != null);
    }

    @Test
    public void sampling() {
        System.out.println("\nSampling:");

        for (int i = 0; i < 8; i++) {
            System.out.println("sample: " + rq.sample());
        }
    }

}
