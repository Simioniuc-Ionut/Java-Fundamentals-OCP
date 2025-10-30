package org.example.Chapter8;

import java.util.Arrays;
interface B{}
public class A {
    public int val1() {
        return 1;
    }

    public int val2() {
        return 2;
    }

    static void main(String[] args) {
        A a = new A() {
            public int val2(){return 3;}
            public int val3(){return 3;}
        };
        var b = new B(){
            public boolean isAnonymusMethod() { return true;}
        };
        System.out.println(a.val2());
        System.out.println(b.isAnonymusMethod());

        // but:
        B c = new B(){
            public boolean isAnonymusMethod() { return true;}
        };

        // It doesn't work.
//        System.out.println(c.isAnonymusMethod());

    }

    abstract class C{
        public void meth(){}

        static void main() {
        }
    }
}
