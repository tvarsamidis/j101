package gr.codehub.j101.p06generics;

public class Main4CountGreater {

    public static <T extends Comparable<T>> int countGreaterThan(T[] anArray, T elem) {
        int count = 0;
        for (T e : anArray) {
            if (e.compareTo(elem) > 0) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        String[] words = {"a", "b", "c", "d", "e"};
        System.out.println(countGreaterThan(words, "bee"));
    }

}
