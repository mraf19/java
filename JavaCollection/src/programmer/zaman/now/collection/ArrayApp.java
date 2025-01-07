package programmer.zaman.now.collection;

import java.util.Arrays;
import java.util.List;

public class ArrayApp {
    public static void main(String[] args) {
        List<String> names = List.of("Muhammad", "Rafli");

        Object[] objects = names.toArray();
        String[] strings = names.toArray(new String[]{});

        System.out.println(objects);
        System.out.println(Arrays.toString(objects));
        System.out.println(strings);
        System.out.println(Arrays.toString(strings));
    }


}


