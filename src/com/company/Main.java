package com.company;

public class Main {
    public static void main(String[] args) throws Exception {
        var add = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                synchronized (Counter.lockA) {
                    Counter.count += 1;
                    synchronized (Counter.lockB) {
                        Counter.count += 1;
                    }
                }
            }
        });
        var dec = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                synchronized (Counter.lockB) {
                    Counter.count -= 1;
                    synchronized (Counter.lockA) {
                        Counter.count -= 1;
                    }
                }
            }
        });
        add.start();
        dec.start();
        add.join();
        dec.join();
        // excepted: 0
        System.out.println(Counter.count);
    }
}

class Counter {
    public static final Object lockA = new Object();
    public static final Object lockB = new Object();
    public static int count = 0;
}
