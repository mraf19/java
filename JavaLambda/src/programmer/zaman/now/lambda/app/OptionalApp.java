package programmer.zaman.now.lambda.app;

import java.util.Optional;

public class OptionalApp {
    public static void main(String[] args) {
        sayHello("rafli");
        sayHello(null);
    }
    public static void sayHello(String name){
        //System.out.println("Hello " + name.toUpperCase()); //error ketika name null

        // cara if
        // if(name != null){
        //     System.out.println("Hello " + name.toUpperCase());
        // }

        // Optional class
        //Optional<String> optionalName = Optional.ofNullable(name);
        //Optional<String> optionalUpper = optionalName.map(String::toUpperCase);
        //optionalUpper.ifPresent(value -> System.out.println("Hello " + value));

        //Optional class one line
        //Optional.ofNullable(name)
                //.map(String::toUpperCase)
                //.ifPresent(value -> System.out.println("Hello " + value));

        //Optional class or else
        //Optional.ofNullable(name)
        //.map(String::toUpperCase)
        //.ifPresentOrElse(
                //value -> System.out.println("Hello " + value),
                //() -> System.out.println("Hello TEMAN")
            //);

        String value = Optional.ofNullable(name)
                .map(String::toUpperCase)
                .orElse("TEMAN");

        System.out.println("HELLO " + value);
    }
}
