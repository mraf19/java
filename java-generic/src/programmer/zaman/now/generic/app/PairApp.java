package programmer.zaman.now.generic.app;

import programmer.zaman.now.generic.Pair;

public class PairApp {
    public static void main(String[] args) {
        Pair<String, Integer> pair = new Pair<>("Rafli", 100);
        System.out.println(pair.getFirst());
        System.out.println(pair.getSecond());
    }
}
