package programmer.zaman.now.collection;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ImmutableSetApp {
    public static void main(String[] args) {
        Set<String> empty = Collections.emptySet();
        Set<String> one = Collections.singleton("Rafli");

        Set<String> mutable = new HashSet<>();
        mutable.add("Muhammad");
        mutable.add("Rafli");
        Set<String> immutable = Collections.unmodifiableSet(mutable);

        Set<String> elements = Set.of("Muhammad", "Rafli");

        //elements.add("Programmer"); //error
        //elements.remove("Rafli"); //error
    }
}
