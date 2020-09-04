package com.company;

public class Main {
    public static void main(String[] args) {
        var c = new Counter();
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                c.add(1);
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                c.dec(1);
            }
        }).start();
        System.out.println(c.get());
    }
}

class Counter {
    private int count = 0;

    public void add(int n) {
        synchronized (this) {
            count += n;
        }
    }

    public void dec(int n) {
        synchronized (this) {
            count -= n;
        }
    }

    public int get() {
        return count;
    }
}