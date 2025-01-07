package programmer.zaman.now.stream;

import org.junit.jupiter.api.Test;
import org.w3c.dom.ls.LSOutput;

import java.util.*;
import java.util.stream.*;

public class StreamTest {
    @Test
    public void TestCreateSingleOrEmptyStream(){
        Stream<String> empty = Stream.empty();
        empty.forEach(System.out::println);

        Stream<String> stringStream = Stream.of("Rafli");
        stringStream.forEach(System.out::println);

        Stream<String> stringOrNotStream = Stream.ofNullable(null);
        stringOrNotStream.forEach(System.out::println);
    }

    @Test
    public void TestCreateStreamFromArray(){
            Stream<String> stringStream = Stream.of("Muhammad", "Rafli");
            stringStream.forEach(System.out::println);
            Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, 5, 6);
            integerStream.forEach(System.out::println);
            String[] array = new String[]{
                    "Muhammad", "Rafli"
            };

            Stream<String> streamFromArray = Arrays.stream(array);
            streamFromArray.forEach(System.out::println);
    }

    @Test
    void TestCreateStreamFromCollection() {

        Collection<String> collection = List.of("Muhammad", "Rafli");
        Stream<String> stream = collection.stream();
        stream.forEach(System.out::println);
    }

    @Test
    void TestCreateStreamIterate() {
        Stream<String> stream1 = Stream.generate(() -> "Saya");
//        stream1.forEach(System.out::println);

        Stream<Integer> stream2 = Stream.iterate(1, value -> value + 1);
//        stream2.forEach(System.out::println);
    }

    @Test
    void TestStreamMap() {
        List<String> list = List.of("Muhammad", "Rafli");
        Stream<String> names = list.stream();
        Stream<String> upperNames = names.map(String::toUpperCase);
        upperNames.forEach(System.out::println);
//        names.forEach(System.out::println);
    }

    @Test
    void TestStreamPipeline() {
        List.of("Muhammad", "Rafli").stream()
                .map(String::toUpperCase)
                .map(data -> "Mr. " + data)
                .forEach(System.out::println);
    }

    @Test
    void TestFilteringOperation() {
        List<String> names = List.of("Muhammad", "Rafli", "Lia", "Listriani", "Myesha", "Rafanda");

        names.stream().filter(name -> name.length() > 6).forEach(System.out::println);
    }

    @Test
    void TestRetrievingOperation() {
        List<String> names = List.of("Test","Muhammad", "Rafli", "Lia", "Listriani", "Myesha", "Rafanda");

        names.stream().skip(2).forEach(System.out::println);
        names.stream().limit(2).forEach(System.out::println);
        names.stream().takeWhile(name -> name.length() > 4).forEach(System.out::println);
        names.stream().dropWhile(name -> name.length() == 3).forEach(System.out::println);
        names.stream().findAny().ifPresent(System.out::println);
        names.stream().findFirst().ifPresent(System.out::println);

    }

    @Test
    void TestAggregateOperation() {
        List<String> names = List.of("Test","Muhammad", "Rafli", "Lia", "Listriani", "Myesha", "Rafanda");

        names.stream().max(Comparator.naturalOrder()).ifPresent(System.out::println);
        names.stream().min(Comparator.naturalOrder()).ifPresent(System.out::println);
        long count = names.stream().count();
        System.out.println(count);

        List<Integer> numbers = List.of(1,2,3,4,5,6,7,8,9,10);
        Integer sum = numbers.stream().reduce(100, Integer::sum);
        Integer factorial = numbers.stream().reduce(1, (result, item) -> result * item);
        System.out.println(sum);
        System.out.println(factorial);
    }

    @Test
    void TestPeek() {
        List<String> names = List.of("Test","Muhammad", "Rafli", "Lia", "Listriani", "Myesha", "Rafanda");
        //Before
        names.stream()
                .map(name -> {
                    System.out.println("Before Change Name: " + name);
                    String upper = name.toUpperCase();
                    System.out.println("After Change Name: " + upper);
                    return upper;
                })
                .forEach(name -> System.out.println("Final Name: " + name));

        //after
        names.stream()
                .peek(name -> System.out.println("Before Change Name: " + name))
                .map(String::toUpperCase)
                .peek(name -> System.out.println("After Change Name: " + name))
                .forEach(name -> System.out.println("Final Name: " + name));
    }

    @Test
    void CreateStreamBuilder() {
        Stream.Builder<String> stringBuilder = Stream.builder();

        stringBuilder.accept("Muhammad");
        stringBuilder.add("Rafli").add("Lia").add("Listriani");

        Stream<String> stream = stringBuilder.build();

        stream.forEach(System.out::println);
    }

    @Test
    void CreateStreamBuilderSimplify() {
        Stream<Object> stream = Stream.builder().add("Muhammad").add("Rafli").add("Lia").add("Listriani").build();

        stream.forEach(System.out::println);
    }

    @Test
    void TestTransformationMap() {
        List<String> names = List.of("Test","Muhammad", "Rafli", "Lia", "Listriani", "Myesha", "Rafanda");

        names.stream()
                .map(String::toUpperCase)
                .map(String::length)
                .forEach(System.out::println);
    }

    @Test
    void TestTransformationFlatMap() {
        List<String> names = List.of("Test","Muhammad", "Rafli", "Lia", "Listriani", "Myesha", "Rafanda");

        names.stream()
                .map(String::toUpperCase)
                .flatMap(upper -> Stream.of(upper, upper.length()))
                .forEach(System.out::println);
    }

    @Test
    void TestOrdering() {
        List<String> names = List.of("Test","Muhammad", "Rafli", "Lia", "Listriani", "Myesha", "Rafanda");

        names.stream().sorted().forEach(System.out::println);
        names.stream().sorted(Comparator.reverseOrder()).forEach(System.out::println);
    }

    @Test
    void TestCheckOperation() {
        List<String> names = List.of("Test","Muhammad", "Rafli", "Lia", "Listriani", "Myesha", "Rafanda");

        boolean check_one = names.stream().anyMatch(data -> data.length() == 3);
        boolean check_two = names.stream().allMatch(name -> !name.isBlank());
        boolean check_three = names.stream().noneMatch(name -> name.equals(name.toUpperCase()));

        System.out.println(check_one);
        System.out.println(check_two);
        System.out.println(check_three);


    }

    @Test
    void TestCreatePrimitiveStream() {
        LongStream longStream = LongStream.of(1, 2, 3, 4, 5);
        longStream.forEach(System.out::println);
        IntStream intStream = IntStream.range(1, 10);
        intStream.forEach(System.out::println);
        DoubleStream doubleStream = DoubleStream.builder().add(0.1).add(1.2).add(2.3).build();
        doubleStream.forEach(System.out::println);
    }

    @Test
    void TestCollectorsCollection() {
        List<String> names = List.of("Test","Muhammad", "Rafli", "Lia", "Listriani", "Myesha", "Rafanda");
        System.out.println("SET");
        System.out.println();
        Set<String> set = names.stream().collect(Collectors.toSet());
        set.forEach(System.out::println);
        Set<String> immutableSet = names.stream().collect(Collectors.toUnmodifiableSet());
        immutableSet.forEach(System.out::println);
        System.out.println("----------||----------");
        System.out.println("LIST");
        System.out.println();
        List<String> list = names.stream().collect(Collectors.toList());
        list.forEach(System.out::println);
        List<String> immutableList = names.stream().collect(Collectors.toUnmodifiableList());
        immutableList.forEach(System.out::println);
        System.out.println("----------||----------");
        System.out.println("MAP");
        System.out.println();
        Map<String, Integer> map = names.stream().collect(Collectors.toMap(name -> name, String::length));
        map.forEach((key, value) -> System.out.println(key + ": " + value));

    }

    @Test
    void TestGroupingBy() {
        List<String> names = List.of("Test","Muhammad", "Rafli", "Lia", "Listriani", "Myesha", "Rafanda");

        names.stream()
                .collect(Collectors.groupingBy(name -> name.length() > 4 ? "Panjang" : "Pendek"))
                .forEach((s, strings) -> System.out.println(s + ": " + strings));

        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        numbers.stream()
                .collect(Collectors.groupingBy(number -> number > 5 ? "Besar" : "Kecil"))
                .forEach((s, integers) -> System.out.println(s + ": " + integers ));
    }
    @Test
    void TestPartitioningBy() {
        List<String> names = List.of("Test","Muhammad", "Rafli", "Lia", "Listriani", "Myesha", "Rafanda");

        names.stream()
                .collect(Collectors.partitioningBy(name -> name.length() > 4))
                .forEach((s, strings) -> System.out.println(s + ": " + strings));

        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        numbers.stream()
                .collect(Collectors.partitioningBy(number -> number > 5))
                .forEach((s, integers) -> System.out.println(s + ": " + integers ));
    }

    @Test
    void TestParallelStream() {
        List<String> names = List.of("Test","Muhammad", "Rafli", "Lia", "Listriani", "Myesha", "Rafanda");

        names.stream()
                .forEach(name -> System.out.println("Thread " + Thread.currentThread().getName() + ": " + name));

        names.stream()
                .parallel()
                .forEach(name -> System.out.println("Thread " + Thread.currentThread().getName() + ": " + name));
    }
}
