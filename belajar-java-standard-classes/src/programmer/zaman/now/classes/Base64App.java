package programmer.zaman.now.classes;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Base64App {
    public static void main(String[] args) {
        String original = "Muhammad Rafli";

        String encoded = Base64.getEncoder().encodeToString(original.getBytes());

        System.out.println(encoded);

        byte[] bytes = Base64.getDecoder().decode(encoded);
        System.out.println(bytes);

        String decoded = new String(bytes);

        System.out.println(decoded);
    }
}
