package programmer.zaman.now.collection;

import java.util.HashMap;
import java.util.Map;

public class HasMapApp {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();

        map.put("name.first", "Muhammad");
        map.put("nam.last", "Rafli");

        System.out.println(map.get("name.last"));
    }
}
