package programmer.zaman.now.collection;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class LinkedHashMapApp {
    public static void main(String[] args) {
        Map<String, String> map = new LinkedHashMap<>();

        map.put("first", "Muhammad");
        map.put("middle", "Rafli");
        map.put("last", "Programmer");

        for (var key: map.keySet()){
            System.out.println(key);
        }
    }
}
