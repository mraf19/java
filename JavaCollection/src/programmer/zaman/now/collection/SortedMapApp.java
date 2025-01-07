package programmer.zaman.now.collection;

import java.util.Collections;
import java.util.Comparator;
import java.util.SortedMap;
import java.util.TreeMap;

public class SortedMapApp {
    public static void main(String[] args) {
        SortedMap<String, String> sortedMap = new TreeMap<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });

        sortedMap.put("Aku", "Aku");
        sortedMap.put("Seorang", "Seorang");
        sortedMap.put("Programmer", "Programmer");

        for (var key: sortedMap.keySet()){
            System.out.println(key);
        }

        //immutable Sorted Map
        SortedMap<String, String> empty = Collections.emptySortedMap();
        SortedMap<String, String> immutable = Collections.unmodifiableSortedMap(sortedMap);
    }
}
