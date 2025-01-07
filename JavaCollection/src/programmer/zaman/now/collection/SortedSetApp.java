package programmer.zaman.now.collection;

import programmer.zaman.now.collection.data.Person;
import programmer.zaman.now.collection.data.PersonComparator;

import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;

public class SortedSetApp {
    public static void main(String[] args) {
        SortedSet<Person> people = new TreeSet<>(new PersonComparator().reversed());

        people.add(new Person("Rafli"));
        people.add(new Person("Lia"));
        people.add(new Person("Eva"));

        for(var person: people ){
            System.out.println(person.getName());
        }

        SortedSet<Person> immutable = Collections.unmodifiableSortedSet(people);
        // immutable.add(new Person("Anonim")); // error
        SortedSet<Person> empty = Collections.emptySortedSet();
    }
}
