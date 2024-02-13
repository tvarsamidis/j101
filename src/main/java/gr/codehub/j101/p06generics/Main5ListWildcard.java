package gr.codehub.j101.p06generics;

import java.util.ArrayList;
import java.util.List;

public class Main5ListWildcard {

    public static void main(String[] args) {
        List<SomeUsefulBusinessClass2> ubc2 = new ArrayList<>();
        //List<SomeUsefulBusinessClass1> ubc1 = ubc2;   // compile-time error

        List<? extends Integer> intList = new ArrayList<>();
        List<? extends Number> numList = intList;  // OK

        List<? extends SomeUsefulBusinessClass2> ubc2List = new ArrayList<>();
        //List<? extends SomeUsefulBusinessClass1> ubc1List = ubc2List;  // OK
    }
}
