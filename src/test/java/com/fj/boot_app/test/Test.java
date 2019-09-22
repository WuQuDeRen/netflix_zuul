package com.fj.boot_app.test;

public class Test {

    public static void main(String[] args) {
        Child1 child1 = new Child1();
        Child2 child2 = new Child2();
        System.out.println(child1.a == child2.a);
    }
}
