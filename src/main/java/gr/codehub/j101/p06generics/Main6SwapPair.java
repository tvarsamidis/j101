package gr.codehub.j101.p06generics;

public class Main6SwapPair {

    public static void main(String[] args) {
        Pair<Number, String> a = new OrderedPair<>(3, "Hello");
        Pair<String, Number> b = Util.swap(a);
        System.out.println("swapped key=" + b.getKey());
        System.out.println("swapped value=" + b.getValue());
    }
}
