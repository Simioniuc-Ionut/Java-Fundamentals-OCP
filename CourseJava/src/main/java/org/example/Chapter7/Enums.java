package org.example.Chapter7;

import java.util.Arrays;

interface Visitors { void printVisitors(); }

enum SeasonWithVisitors implements Visitors
{
    WINTER("Low"), SPRING("Medium"), SUMMER("High"), FALL("Medium");

    private final String visitors;
    public static final String DESCRIPTION = "Weather enum";

    private SeasonWithVisitors(String visitors) {
       System.out.print("constructing, the visitors: " + visitors + '\n');
       this.visitors = visitors;
    }

    @Override public void printVisitors() {
        System.out.println(visitors);
    }

    public static void main(String[] args) {
//         System.out.println(Arrays.toString(SeasonWithVisitors.values()));

    }
}