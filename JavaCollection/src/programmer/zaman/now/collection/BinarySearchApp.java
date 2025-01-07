package programmer.zaman.now.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BinarySearchApp {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();

        for (int i = 1; i < 1000; i++) {
            numbers.add(i);
        }
        int index = Collections.binarySearch(numbers, 332);
        System.out.println(index);

        Comparator<Integer> reverse = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        };

        Integer index2 = Collections.binarySearch(numbers, 332, reverse);
        System.out.println(index2);
    }


}
