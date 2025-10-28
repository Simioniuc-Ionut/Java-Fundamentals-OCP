package org.example.Chapter10;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.*;

record Address(String street, String city) {}
record Company(String name, Optional<Address> hqAddress) {}
record User(String username, Optional<Company> employer, Optional<Integer> age) {}

public class Practice {
    static void optionalEx(){
        List<Optional<User>> users = List.of(
                Optional.of(new User("Alex",
                        Optional.of(new Company("Endava", Optional.of(new Address("Palace","Iasi")))),
                        Optional.of(new Integer(22)))),
                Optional.of(new User("Mihai",
                        Optional.of(new Company("Bitdefender", Optional.of(new Address("Palace","Bucharest")))),
                        Optional.of(new Integer(31)))),
                Optional.of(new User("Bancu",
                        Optional.of(new Company("Amazon", Optional.ofNullable(null))),
                        Optional.of(new Integer(42)))));
        System.out.println(users.get(0));
//        Optional<String> user1 = Optional.ofNullable(users.get(2).get().employer().get().hqAddress().orElse(new Address("default","def")).toString());
        Optional<String> user_3Address = Optional.of(2)
                .map((x) -> users.get(x))
                .flatMap(optUser->optUser)
                .flatMap((x) -> x.employer())
                .flatMap((y)-> y.hqAddress())
                .map((z) -> z.toString());
//                        .or(() -> Optional.of(new Address("Default","Default").toString()));

        System.out.println(user_3Address.or(() -> "No Value".describeConstable()));

        int age= 10;
        Optional<Integer> opAge = Optional.ofNullable(age)
                .filter((x) -> x>= 18)
                .map(x -> {
                    System.out.println("Adult");
                    return x;
                })
                .orElseGet(() -> {System.out.println("minor or unknow"); return age;}).describeConstable();

        System.out.println("Age is" + opAge);
    }

    static void streamPractice(){
        List<User> users = List.of(
                new User("Alex", Optional.of(new Company("Endava", Optional.of(new Address("Palace", "Iasi")))), Optional.of(27)),
                new User("Mihai", Optional.of(new Company("Bitdefender", Optional.of(new Address("Palace", "Bucharest")))), Optional.of(33)),
                new User("Bancu", Optional.of(new Company("Amazon", Optional.empty())), Optional.empty()),
                new User("Ana", Optional.empty(), Optional.of(19)),
                new User("Ioana", Optional.of(new Company("UiPath", Optional.of(new Address("Unirii", "Bucharest")))), Optional.of(29))
        );
//        List<String> userNames = users.stream()
//                .filter(x -> x.username().length() >= 4)
//                .map(x -> x.username().toUpperCase())
//                .peek(System.out::println)
//                .collect(Collectors.toList());

        users.stream()
//                .map(x -> x.employer().orElse(new Company("Nan",null)).name())
                .map( x -> x.employer().map(Company::name).orElse("Nan"))
                .filter(x -> !x.equals("Nan"))
                .distinct()
                .sorted()
                .forEach(System.out::println);

    }

    static void primitiveStreamPractice() {
        int[] a = {3, 7, -2, 7, 0};
        IntStream primA = Arrays.stream(a);
        long primB = LongStream.rangeClosed(1, 10_000)
                .limit(10)
                .reduce(0, (x, y) -> x + y);
        //
        Stream<String> nums = Stream.of("1","2","x","10","-3");
        IntStream convertedNums = nums
                .map(x ->{
                    try {
                        return OptionalInt.of(Integer.parseInt(x));
                    } catch (Exception e){
                        return OptionalInt.empty();
                    }
                })
                .filter(x -> x.isPresent() && x.getAsInt() >= 0)
                .map( x ->  IntStream.of(x.getAsInt()))
                .reduce(IntStream.of(0), (x, y) -> IntStream.of(x.sum() + y.sum()));
        System.out.println("Converted sum is : " + convertedNums.sum());

        int[] data = {5, 5, 10, 1, 9};
        double avg = Arrays.stream(data).summaryStatistics().getAverage();
        long sum = Arrays.stream(data).summaryStatistics().getSum();
        long count = Arrays.stream(data).summaryStatistics().getCount();
        Stream.of(avg,sum,count)
                .forEach(System.out::println);

        IntStream.iterate(5, (x) -> 1 + x)
                .limit(10)
                .forEach( x -> System.out.print(" " + x));

        Optional<Integer> numbs = Integer.valueOf(2).describeConstable();
        numbs.ifPresent(x -> System.out.println(x.intValue()));

    }

    static void collectorPractice() {
        var ohMy = Stream.of("lions", "tigers", "bears");
        List<String> mutableList = ohMy.collect(Collectors.toList());
// !!!! Be care, in this point ohMy is already consumed. There are no values.
//        List<String> immutableList =List.copyOf(ohMy.collect(Collectors.toList()));

        List<String> immutableList =List.copyOf(mutableList);

        try{
            mutableList.add("Fox");
            immutableList.add("Wold");
        } catch (Exception e) {
        }
        System.out.println("Mutable list: " + mutableList);
        System.out.println("Immutable list: " + immutableList);
        ohMy = Stream.of("lions", "tigers", "bears");


        Map<Integer, String> map = ohMy.collect(Collectors.toMap(
                (x) ->  x.length() > 5 ? 0 : 1,
                (x) -> x.length() >= 5 ? x.toLowerCase(): x.toUpperCase(),
                (s1, s2) -> s1 + " " + s2,
                TreeMap::new
        ));
        System.out.println(map);
        // ??? But if we want to have a list of values, how we do?
        ohMy = Stream.of("lions", "tigers", "bears");
        Map<Integer, List<String>> mapOfLiST = ohMy.collect(
                Collectors.groupingBy(x-> x.length(),
                        Collectors.toList()));
        ohMy = Stream.of("lions", "tigers", "bears");
        Map<Integer, Set<Integer>> mapOfInt = ohMy.collect(
                Collectors.groupingBy(
                        // Key
                        x -> x.length(),
                        // Value
                        Collectors.mapping(
                                // Map the values to an int
                                x -> x.length() + 10,
                                // Convert to a set
                                Collectors.toSet())));

        System.out.println("Map of list of strings: " + mapOfLiST);
        System.out.println("Map of list of integers " + mapOfInt);

        // Partitioning is a special case of grouping. It s just split in 2 partitions( true and false)
        Map<Boolean, List<String>> mapOfPartition = Stream.of("Banana", "Strawberry", "Gasp")
                .collect(Collectors.partitioningBy(x-> x.length() <=5));
        System.out.println("Map of partitioning: " + mapOfPartition);

        var list = List.of("x", "y", "z");
        Address result = list.stream().collect(Collectors.teeing(
                Collectors.joining("|"),
                Collectors.joining(" "),
                (s,c) -> new Address(s,c)));
        System.out.println(result);

        List<String> aa = List.of("1","2","3");
        Stream.concat(aa.stream(),aa.stream())
                .forEach(System.out::print);

        List<Integer> bbb = List.of(1,2,3);
        bbb.stream().mapToInt(x->x).boxed();
    }
    static void spliterator(){
        var list = List.of("bird-", "bunny-", "cat-", "dog-", "fish-", "lamb-",
                    "mouse-");
        Spliterator<String> spl = list.spliterator();
        Spliterator<String> spl2 = spl.trySplit();
        spl2.forEachRemaining(System.out::println);
        System.out.println("---");
        spl.forEachRemaining(System.out::println);
        //        Spliterator<String> spl3 = spl.trySplit();
//        spl3.forEachRemaining(System.out::println);
        System.out.println("---");

    }
    public static void main(String[] args) {
//        Practice.optionalEx();
//        Practice.streamPractice();
//        Practice.primitiveStreamPractice();
//        Practice.collectorPractice();
        Practice.spliterator();

        Predicate<String> p1 = String::isEmpty;
        Predicate<String> p2 = p1.negate();
        var l = Stream.generate(() -> "").limit(10).filter(p2).collect(Collectors.groupingBy(k->k));
        var l2 = List.of("ana","are","mere").stream().collect(Collectors.groupingBy((a)-> a.charAt(0)));
        System.out.println(l2);
        System.out.println(l);
        Optional<Integer> a = Optional.of(2);
        List<Integer> imutb = List.of(1,2,3,4);
        int sum = imutb.stream().reduce(0,(x,y) -> x + y);
        System.out.println(sum + '\n' + " imutb " + imutb);
    }
}

