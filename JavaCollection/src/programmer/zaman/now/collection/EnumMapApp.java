package programmer.zaman.now.collection;

import java.util.EnumMap;
import java.util.Map;

public class EnumMapApp {
    public static enum Level {
        FREE, STANDARD, PREMIUM, VIP
    }

    public static void main(String[] args) {
        Map<Level, String> map = new EnumMap<>(Level.class);

        map.put(Level.FREE, "Rafli");
        map.put(Level.VIP, "Bejo");

        for (var key: map.keySet()){
            System.out.println(map.get(key));
        }
    }
}
