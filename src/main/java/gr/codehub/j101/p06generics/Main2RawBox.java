package gr.codehub.j101.p06generics;

public class Main2RawBox {

    public static void main(String[] args) {
        Box rawBox = new Box();               // rawBox is a raw type of Box<T>
        Box<Integer> intBox = rawBox; // warning: unchecked conversion

        Box<String> stringBox = new Box<>();
        Box rawBox2 = stringBox;
        rawBox2.set("abc");
        rawBox2.set(8);  // warning: unchecked invocation to set(T)

    }

}
