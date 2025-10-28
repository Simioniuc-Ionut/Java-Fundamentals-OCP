package org.example.Chapter10;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;

public class Main {
    public class Exercice{
        public static void ex1() {
            List<Integer> list  = List.of(10,20,30,40);
            IntStream a = list.stream().mapToInt((x) -> x);
            int[] b = a.toArray();
            // Without IntStream, we can't convert from List to a primitive array.
            // int[] c = list.toArray();
        }

    }
    public static void main(String[] args) {
        Exercice.ex1();
    }
}
