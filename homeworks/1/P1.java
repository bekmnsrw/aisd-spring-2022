public class P1 {
    public static void main(String[] args) {
        Lst<Integer> lst = Lst.cons(0, Lst.cons(2, Lst.cons(15, Lst.cons(4, Lst.cons(3, Lst.cons(2, Lst.nil()))))));
        Lst<Integer> l2 = Lst.change12(lst);
        Lst.print(l2);
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

    public static <T> Lst<T> change12(Lst<T> lst) {
        Lst<T> tempLst = new Lst<>(null, null);
        tempLst.next = lst;
        Lst<T> currLst = tempLst;
        while (currLst.next != null && currLst.next.next != null) {
            Lst<T> fstEl = currLst.next;
            Lst<T> sndEl = currLst.next.next;
            fstEl.next = sndEl.next;
            currLst.next = sndEl;
            currLst.next.next = fstEl;
            currLst = currLst.next.next;
        }
        return tempLst.next;
    }
}