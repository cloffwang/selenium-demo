package com.cliff;

public class Main {
    public static int c=0;
    public Main() {
        c++;
    }
    public static void main(String[] args) {
        Main c1 = new Main();
        Main c2 = new Main();
        Main c3 = new Main();
        System.out.println("nums is: " + c1.c);
    }
}