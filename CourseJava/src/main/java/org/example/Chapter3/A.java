package org.example.Chapter3;

public abstract class A {
    String name(){
        return "A";
    }
}
class B extends A {
    String name(){
        return "B";
    }
}
class C extends B {
    String name() {
        return "C";
    }
    String name2(){
        return "C";
    }
    public static void main(String[] args){
        A a = new C();
        System.out.println(a.name());
        System.out.println(((C)a).name2());
        byte ticket= 1;
        ticket += (long)128;
        System.out.println(ticket);
    }
}
