package com.company;

public class Main {
    public static void main(String[] args) throws Exception {
        var add = new AddThread();
        var dec = new DecThread();
        add.start();
        dec.start();
        add.join();
        dec.join();
        System.out.println(Counter.count);
    }
}

class Counter {
    public static Object lock = new Object();
    public static int count = 0;
}

class AddThread extends Thread {
    public void run() {
        synchronized (Counter.lock) {
            for (int i = 0; i < 10000; i++) {
                Counter.count += 1;
            }
        }
    }
}

class DecThread extends Thread {
    public void run() {
        synchronized (Counter.lock) {
            for (int i = 0; i < 10000; i++) {
                Counter.count -= 1;
            }
        }
    }
}
