package programmer.zaman.now.collection;

import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;

public class IdentityHashMapApp {
    private String name;
    public static String getName(){
        return "Sambo";
    }
    public static void main(String[] args) {
        //Map<String, String> map = new IdentityHashMap<>();
        Map<String, String> map = new HashMap<>();

        String key1 = "name.first";
        String name = "name";
        String dot = ".";
        String first = "first";
        String key2 = name + dot + first;

        map.put(key1, "Rafli");
        map.put(key2, "Rafli");

        System.out.println(map.size());
        System.out.println(getName());

    }

}
