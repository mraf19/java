package programmer.zaman.now.collection;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class EntryMapApp {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();

        map.put("Rafli", "Rafli");
        map.put("Lia", "Lia");
        map.put("Eva", "Eva");
        map.put("Myesha", "Myesha");

        Set<Map.Entry<String, String>> entries = map.entrySet();

        System.out.println("{");
        for (var entry: entries){
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.println("}");
    }
}
