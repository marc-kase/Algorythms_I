import java.util.Iterator;

/**
 * Created by MM on 07.02.2016.
 */
public class DequeSimpleTest {

    public static void main(String[] args) {
        Deque<String> deque = new Deque<>();
        deque.addFirst("+1");
        deque.addFirst("+2");
//        deque.addFirst("+3");

        deque.addLast("-1");
        deque.addLast("-2");
        deque.addLast("-3");

        deque.addFirst("+3");
        deque.addLast("-4");

        printDeque(deque);

        for (int i = 0; i < 5; i++) {
            System.out.println("remove first = " + deque.removeFirst());
        }
        printDeque(deque);

        System.out.println("removed last = " + deque.removeLast());
        System.out.println("removed last = " + deque.removeLast());
        printDeque(deque);

        printNext(deque);

        deque.addFirst("+3");
        deque.addLast("-4");
        printDeque(deque);
    }

    private static void printDeque(Deque<String> deque) {
        System.out.println("========================");
        Iterator<String> it = deque.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
        System.out.println("========================");
    }

    private static void printNext(Deque<String> deque) {
        System.out.println("========================");
        Iterator<String> it = deque.iterator();
        System.out.println(it.next());
        System.out.println("========================");
    }
}
