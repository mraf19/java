package programmer.zaman.now.collection;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListApp {
    public static void main(String[] args) {
        List<String> strings = new ArrayList<>();
//        List<String> strings = new LinkedList<>();

        strings.add("Muhammad");
        strings.add("Rafli");

        strings.set(0, "Programmer");
        System.out.println(strings.get(0));
    }
}
