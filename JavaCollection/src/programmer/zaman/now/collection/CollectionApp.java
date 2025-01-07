package programmer.zaman.now.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class CollectionApp {
    public static void main(String[] args) {
        Collection<String> collection = new ArrayList<>();

        collection.add("Muhammad");
        collection.add("Rafli");
        collection.addAll(List.of("Electrindo", "Inti", "Dinamika"));

        for (var value: collection){
            System.out.println(value);
        }

        collection.remove("Muhammad");
        collection.removeAll(Arrays.asList("Inti", "Dinamika"));

        System.out.println(collection.contains("Rafli"));
        System.out.println(collection.containsAll(List.of("Rafli", "Lia")));
        System.out.println(collection.containsAll(List.of("Rafli", "Electrindo")));

        for (var value: collection){
            System.out.println(value);
        }
    }
}
