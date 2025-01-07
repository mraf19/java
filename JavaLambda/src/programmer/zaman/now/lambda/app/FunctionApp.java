package programmer.zaman.now.lambda.app;

import java.util.function.Function;

public class FunctionApp {
    public static void main(String[] args) {
        Function<String, Integer> function = value -> value.length();
        System.out.println(function.apply("Muhammad"));
    }
}
