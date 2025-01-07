package programmer.zaman.now.collection;

import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

public class SpliteratorApp {
    public static void main(String[] args) {
        List<String> list = List.of("Eko", "Kurniawan", "Khannedy", "Programmer", "Zaman", "Now");

        Spliterator<String> spliterator1 = list.spliterator();
        Spliterator<String> spliterator2 = spliterator1.trySplit();
        Spliterator<String> spliterator3 = spliterator2.trySplit();

        System.out.println(spliterator1.estimateSize());
        System.out.println(spliterator2.estimateSize());
        System.out.println(spliterator3.estimateSize());

        spliterator1.forEachRemaining(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println("Spliterator 1: " + s);
            }
        });
        spliterator2.forEachRemaining(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println("Spliterator 2: " + s);
            }
        });
//        spliterator3.forEachRemaining(new Consumer<String>() {
//            @Override
//            public void accept(String s) {
//                System.out.println("Spliterator 3: " + s);
//            }
//        });


    }
}
