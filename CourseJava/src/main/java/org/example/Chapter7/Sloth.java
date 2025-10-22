package org.example.Chapter7;

import java.util.ArrayList;

public class Sloth implements Animal{
    public boolean hasHair(){
        return true;
    }
    public static ArrayList ceva() { return new ArrayList();}
    public String whoIsSloth(){
        return "123";
    }
    public String ceva123() {
        Object la = new Object();
        switch (la) {
            case String pp: {
                System.out.println(pp);
            }
            ;
            default: {
                System.out.println("ada");
                int i = 10;
                return switch (i){
                    case 1 -> "1";
                    case 2 -> "2";
                    case 3 -> "3";
                    default -> "4";
                };
            }

        }
    }
    static void main(String[] args) {
        Animal g = new Sloth();
        // System.out.println(g.hasHair() + " - " + g.whoIsSloth());
        System.out.println(g.hasHair());
        System.out.println(((Sloth) g).whoIsSloth());
        String c = new String("G");
        String d = "G";
        String f = d.concat("I");
        StringBuilder gg = new StringBuilder();
        System.out.println(f);
        g.hasHair();

        SeasonWithVisitors e = SeasonWithVisitors.FALL;
        System.out.println(e.toString());
        e.printVisitors();

        Magic m = new Magic();

    }
}
