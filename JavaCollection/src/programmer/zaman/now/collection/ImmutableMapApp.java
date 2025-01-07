package programmer.zaman.now.collection;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ImmutableMapApp {
    public static void main(String[] args) {
        Map<String, String> empty = Collections.emptyMap();
        Map<String, String> singleton = Collections.singletonMap("name", "rafli");

        Map<String, String> mutable = new HashMap<>();
        mutable.put("first", "Muhammad");
        mutable.put("last", "Rafli");
        Map<String, String> immutable = Collections.unmodifiableMap(mutable);

        Map<String, String> elements = Map.of(
                "first", "Muhammad",
                "last", "Rafli"
        );

        //elements.put("err", "error");//error
    }
}
