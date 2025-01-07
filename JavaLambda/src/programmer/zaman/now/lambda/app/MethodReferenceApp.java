package programmer.zaman.now.lambda.app;

import programmer.zaman.now.lambda.util.CheckLowerCase;

import java.util.function.Function;
import java.util.function.Predicate;

public class MethodReferenceApp {
    public static void main(String[] args) {
        Predicate<String> predicateLower = CheckLowerCase::isLowerCase;

        System.out.println(predicateLower.test("Makan"));
        System.out.println(predicateLower.test("makan"));
        MethodReferenceApp app = new MethodReferenceApp();
        app.run();
        app.run2();

        // Method Reference Parameter
        Function<String, String> function = String::toUpperCase;

        System.out.println(function.apply("saya"));
        System.out.println(function.apply("makan"));
        System.out.println(function.apply("ayam"));
    }

    public void run (){
        Predicate<String> predicateLower = this::check;

        System.out.println(predicateLower.test("Makan"));
        System.out.println(predicateLower.test("makan"));
    }
    public void run2 (){
        MethodReferenceApp app = new MethodReferenceApp();
        Predicate<String> predicateLower = app::check;

        System.out.println(predicateLower.test("Makan"));
        System.out.println(predicateLower.test("makan"));
    }
    public boolean check(String value){
        for(var c: value.toCharArray())
            if (!Character.isLowerCase(c)) {
                return false;
            }
        return true;
    };

}
