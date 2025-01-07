package programmer.zaman.now.classes;

public class StringBuilderApp {
    public static void main(String[] args) {
        StringBuilder builder = new StringBuilder();

        builder.append("Muhammad");
        builder.append(" ");
        builder.append("Rafli");

        String fullName = builder.toString();

        System.out.println(fullName);

    }
}
