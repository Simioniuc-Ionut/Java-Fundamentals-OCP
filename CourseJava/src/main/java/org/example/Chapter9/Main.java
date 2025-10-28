package org.example.Chapter9;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;

class MergingData{
    public static void mergeData(){
         BiFunction<String, String, String> mapper = (v1, v2)
            -> v1.length()> v2.length() ? v1: v2;

         Map<String, String> favorites = new HashMap<>();
         favorites.put("Jenny", "Bus Tour");
         favorites.put("Tom", "Tram");

         String jenny = favorites.merge("Jenny", "Skyride", mapper);
         String tom = favorites.merge("Tom", "Skyride", mapper);
         System.out.println(favorites); // {Tom=Skyride, Jenny=Bus Tour}
         System.out.println(jenny);     // Bus Tour
         System.out.println(tom);       // Skyride
    }
}
record Duck(int weight, String name) {}
public class Main {
     static void main(String[] args) {
        List numbers = new ArrayList(List.of(1,2,3));
        Integer element = (Integer)numbers.get(0);  // Required cast to compile
        System.out.println(numbers.getClass());
        numbers.add("Welcome to the zoo!");
        System.out.println(numbers);
        int[] arr = new int[]{1,2,3};
        List<Integer> list = Arrays.stream(arr)
                .boxed()
                .toList();
        MergingData.mergeData();

        // Comparator
        Function<Duck, Integer> functionComparator = (duck) -> duck.weight();
        Function<Duck, Integer> functionComparatorRef = Duck::weight;
        Comparator<Duck> duckComparator = Comparator.comparing(functionComparatorRef);

        // is immutable
        List<Integer> a = Arrays.asList(1, 2, 3);
        List<? super Integer> b = new ArrayList<>();
        b.add(0,0);
//        a.add(0,0); // will change the size, but the size is fixed, so it wouldn't work
        a.replaceAll(x->x+1);
        // Answer is : asList is fixed size list. Can't add or remove, just replace.
        check();

        //Can't combine var type with explicitly types.
//         Fun f = (Wolf w, var c) -> 39;
         Fun f = (var w, var c) -> 39;

         List<? extends Integer> num = new ArrayList<>(List.of(1,2,3));
         System.out.println(num.get(0));
         List<? super Integer> supNum = new ArrayList<>();
         supNum.add(0,0);
         System.out.println(supNum.get(0));
         Integer aca = (Integer)supNum.get(0);
     }
     class Wolf{}
    interface Fun{
        int ceva(Main.Wolf w, int c);
    }
    private static void check(){
         System.out.println("Hi from static");
    }
}
