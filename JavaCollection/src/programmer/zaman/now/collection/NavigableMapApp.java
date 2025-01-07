package programmer.zaman.now.collection;

import java.util.Collections;
import java.util.NavigableMap;
import java.util.TreeMap;

public class NavigableMapApp {
    public static void main(String[] args) {
        NavigableMap<String, String> map = new TreeMap<>();

        map.put("first", "satu");
        map.put("second", "dua");
        map.put("third", "tiga");
        map.put("forth", "empat");
        map.put("fifth", "lima");
        map.put("sixth", "enam");

        for (var key: map.keySet()){
            System.out.println(key);
        }
        System.out.println("ceiling key first: ");
        System.out.println(map.ceilingKey("first"));
        System.out.println("floor key first: ");
        System.out.println(map.floorKey("first"));
        System.out.println("higher key first: ");
        System.out.println(map.higherKey("first"));
        System.out.println("lower key first: ");
        System.out.println(map.lowerKey("first"));

        //descending Navigable Map
        NavigableMap<String, String> descMap = map.descendingMap();

//        for (var key: descMap.keySet()){
//            System.out.println(key);
//        }

        //immutable Navigable Map
        NavigableMap<String, String> empty = Collections.emptyNavigableMap();
        NavigableMap<String, String> immutable = Collections.unmodifiableNavigableMap(descMap);

        //immutable.put("e", "error");//error
    }
}
