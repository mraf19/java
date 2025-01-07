package programmer.zaman.now.classes;

import java.util.StringJoiner;

public class StringJoinerApp {
    public static void main(String[] args) {
        StringJoiner joiner = new StringJoiner(", ", "{\n ", "\n}");

        joiner.add("SD");
        joiner.add("SMP");
        joiner.add("SMA");

        String value = joiner.toString();
        System.out.println(value);

    }
}
