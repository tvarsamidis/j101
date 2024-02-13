package gr.codehub.j101.p06generics;

public class Util {

    public static <K, V> boolean compare(Pair<K, V> p1, Pair<K, V> p2) {
        return p1.getKey().equals(p2.getKey())
                && p1.getValue().equals(p2.getValue());
    }

    public static <A, B> Pair<B, A> swap(Pair<A, B> p1) {
        A first = p1.getKey();
        B second = p1.getValue();
        return new OrderedPair<>(second, first);
    }
}
