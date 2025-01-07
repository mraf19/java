package programmer.zaman.now.collection;

import java.util.Collections;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

public class NavigableSetApp {
    public static void main(String[] args) {
        NavigableSet<String> strings = new TreeSet<>();

        NavigableSet<String> reversedStrings = strings.descendingSet();
        NavigableSet<String> frontend = strings.headSet("Frontend", true);
        NavigableSet<String> frontendTail = strings.tailSet("Frontend", true);

        strings.addAll(Set.of("Muhammad", "Rafli", "Frontend", "Developer", "Electrindo"));
        for ( var string:
             frontendTail) {
            System.out.println(string);
        }

        NavigableSet<String> immutable = Collections.unmodifiableNavigableSet(strings);
        //immutable.add("Error"); // error
        NavigableSet<String> empty = Collections.emptyNavigableSet();

    }
}
