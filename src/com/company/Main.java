package com.company;

public class Main {
    public static void main(String[] args) throws Exception {
        var c = new Counter();
        var a = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                c.add(1);
            }
        });
        var b = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                c.dec(1);
            }
        });
        a.start();
        b.start();
        a.join();
        b.join();
        System.out.println(c.get());
    }
}

class Counter {
    private int count = 0;

    public synchronized void add(int n) {
        count += n;
    }

    public synchronized void dec(int n) {
        count -= n;
    }

    public int get() {
        return count;
    }
}