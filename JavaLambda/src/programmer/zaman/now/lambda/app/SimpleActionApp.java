package programmer.zaman.now.lambda.app;

import programmer.zaman.now.lambda.SimpleAction;

public class SimpleActionApp {
    public static void main(String[] args) {
        SimpleAction simpleAction1 = new SimpleAction() {
            @Override
            public String action(String name) {
                return "Simple Action 1 " + name;
            }
        };

        System.out.println(simpleAction1.action("Name"));

        // lambda Ekspresi
        SimpleAction simpleAction2 = (name) -> {
            return "Simple Action 2 " + name;
        };

        System.out.println(simpleAction2.action("Name"));

        SimpleAction simpleAction3 = (name) -> "Simple Action 3 " + name;

        System.out.println(simpleAction3.action("Name"));

        SimpleAction simpleAction4 = name -> "Simple Action 4 " + name;

        System.out.println(simpleAction4.action("Name"));

    }
}
