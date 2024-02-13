package gr.codehub.j101.p06generics;

public class Main3BoxInspectNaturalNum {

    public static void main(String[] args) {
        Box<Integer> integerBox = new Box<Integer>();
        integerBox.set(Integer.valueOf(10));
        integerBox.inspect(11);
        //integerBox.inspect("some text"); // error: this is still String!
        
        NaturalNumber nn = new NaturalNumber(5);
        System.out.println("isEven=" + nn.isEven());
    }

}
