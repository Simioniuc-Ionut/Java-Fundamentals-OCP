package org.example.Chapter6;

public class Main {
    static { System.out.println("Ceva-");}
    int b = 10;
    {b  = 20;}

    public Main() {

    }
    public int mew(Object mew){
        return 1;
    }
    public static int  MAX =3;
    public static void main(String[] args) {
        A m = new A();
        Object str = new Object();
        String cc = "ccc";
        Main m1 = new Main();
        System.out.println(m1.mew(str) + " \n " + m.mew(cc));
        int b = 10;
        Integer c = b;
        CharSequence seq = "fac";
        Main test = new A();

        System.out.println(test.MAX);
    }
}
