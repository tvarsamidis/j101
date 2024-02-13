package gr.codehub.j101.p06generics;

import java.util.Arrays;
import java.util.List;

public class Main7ListReplace {

    public static <T> void replace(List<? super T> dest, List<? extends T> src) {
        for (int i = 0; i < src.size(); i++) {
            dest.set(i, src.get(i));
        }
    }

    public static void main(String[] args) {
        List<Integer> ints = Arrays.asList(10, 20, 30); 
        List<Number> nums = Arrays.asList(1, 2, 3);
        replace(nums, ints);
        nums.forEach(System.out::println);
        //replace(ints, nums); // error - incompatible bounds
    }
}
