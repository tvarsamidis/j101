package gr.codehub.j101.p06generics;

public class Box<T> {
    // T stands for "Type"
    private T t;

    public void set(T t) {
        this.t = t;
    }

    public T get() {
        return t;
    }

    public <U extends Number> void inspect(U u) {
        System.out.println("T: " + t.getClass().getName());
        System.out.println("U: " + u.getClass().getName());
    }


    public static void main(String[] args) {
        Box<String> box = new Box();
        box.set("abc");
        box.inspect(Double.valueOf(3));

    }

}

