import javafx.util.Pair;

public class P2 {
    public static void main(String[] args) {
        Lst<Integer> lst = Lst.cons(0,
                Lst.cons(2,
                        Lst.cons(15,
                                Lst.cons(4,
                                        Lst.cons(3,
                                                Lst.nil())))));
        System.out.println(Lst.findCycle(lst));
    }
}

class Lst<T> {
    private T val;
    private Lst<T> next;

    public Lst(T val, Lst<T> next) {
        this.val = val;
        this.next = next;
    }

    public static <T> Lst<T> nil() {
        return null;
    }

    public static <T> Lst<T> cons(T head, Lst<T> tail) {
        return new Lst<>(head, tail);
    }

    public static <T> void print(Lst<T> lst) {
        Lst<T> p = lst;
        while (p != null) {
            System.out.print(p.val + " ");
            p = p.next;
        }
        System.out.println();
    }

    // сложность по времени - O(n)
    // сложность по памяти - O(n)
    public static <T> Pair<Integer,Integer> findCycle(Lst<T> lst) {
        Lst<T> fast = lst;
        Lst<T> slow = lst;
        boolean hasLoop = false;
        int lstLength = 0;
        int loopLength = 1;
        int lengthBeforeLoop = 1;

        // проверка на наличие цикла
        while (fast.next != null && fast.next.next != null && slow != null && !hasLoop) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                hasLoop = true;
            }
        }

        // количество элементов в цикле
        if (hasLoop) {
            while (slow.next != fast) {
                loopLength++;
                slow = slow.next;
            }
        }

        // количество элементов до цикла
        if (hasLoop) {
            slow = lst;
            while (fast != slow) {
                fast = fast.next;
                slow = slow.next;
                lengthBeforeLoop++;
            }
        }

        // длина списка
        if (!hasLoop) {
            slow = lst;
            while (slow != null) {
                lstLength++;
                slow = slow.next;
            }
        }

        if (hasLoop) {
            return new Pair<>(lengthBeforeLoop, loopLength);
        } else
            return new Pair<>(lstLength,0);
    }
}