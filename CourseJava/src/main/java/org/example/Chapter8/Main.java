package org.example.Chapter8;

import java.util.List;
import java.util.function.*;

public class Main {
    public static void main(String[] args) {
        //Supplier
        Supplier<StringBuilder> builderFactory = () -> new StringBuilder("Factory Builder:\n");
        StringBuilder b = builderFactory.get().append("Myn New builder");
        System.out.println(b);

        //Consumer
        BiConsumer<StringBuilder, String> t = StringBuilder::append;
        StringBuilder s = new StringBuilder("123 ");
        t.accept(s,"456");
        System.out.println(s);

        //Predicate
        Predicate<String> p = (x) -> x.endsWith("456");
        System.out.println(p.test(s.toString()));

        // Function
        Function<String, Integer> f1 = String::length;
        Function<String, Integer> f2 = x -> x.length();

        System.out.println(f1.apply("cluck"));  // 5
        System.out.println(f2.apply("cluck"));  // 5

        // UnaryOperation
        UnaryOperator<String> u1 = String::toUpperCase;
        UnaryOperator<String> u2 = x -> x.toUpperCase();

        System.out.println(u1.apply("chirp"));  // CHIRP
        System.out.println(u2.apply("chirp"));  // CHIRP

        Predicate<List> ex1 = x -> "".equals(x.get(0));
        Consumer<Long> ex2 = (Long i) -> System.out.println(i);
        BiPredicate<String, String> ex3 = (s1, s2) -> false;
        class Test {
            private static Build builder = new Build();
            static class Build {
                public void print(String a){
                    System.out.println("Hello World" + a);
                }
            }
            public Test(){}
            @Override
            public String toString() {
                return "tEST";
            }
        }
//        Consumer<String> prr = Test.builder::print;
        Consumer<String> prr =  x -> Test.builder.print(x);
        prr.accept(" new Test()");
    }
}
