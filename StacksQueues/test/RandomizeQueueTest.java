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

        System.out.println("1. Init\n");
        for (int i = 0; i < 4; i++) {
            System.out.println("enque: " + i);
            rq.enqueue(String.valueOf(i));
        }
        pause(3000L);
    }

    @Test
    public void notEmpty() {
        System.out.println("2. Not empty\n");
        for (int i = 0; i < 4; i++) {
            System.out.println("deque: " + rq.dequeue());
        }

        System.out.println("enque: 5");
        rq.enqueue("5");

        System.out.println("sample: " + rq.sample());

        Assert.assertEquals(true, rq.sample() != null);
        pause(3000L);
    }

    @Test
    public void iteration() {
        System.out.println("Iteration:\n");

        Iterator<String> iterator = rq.iterator();
        while (iterator.hasNext()) {
            System.out.println("next is: " + iterator.next());
        }

        Assert.assertEquals(true, rq.sample() != null);
    }

}
