package org.example.Chapter10;

sealed class A permits B{
    protected void hey() {
        System.out.println("Hey");
    }
}
non-sealed class B extends A{
    public void heyB(){
        super.hey();
    }
}
public class SealdTest extends B {
    public void heySeald() {
        super.hey();
    }
    public static void main(String[] arg){
        SealdTest a = new SealdTest();
        a.hey();
    }
}
