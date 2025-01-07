package programmer.zaman.now.collection;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class QueueApp {
    public static void main(String[] args) {
        //Queue<String> names = new ArrayDeque<>();
        Queue<String> names = new PriorityQueue<>();
        //Queue<String> names = new LinkedList<>();

        names.add("Muhammad");
        names.add("Rafli");

        for (String next = names.poll(); next != null; next = names.poll()){
            System.out.println(next);
        }

        System.out.println(names.size());
    }
}
