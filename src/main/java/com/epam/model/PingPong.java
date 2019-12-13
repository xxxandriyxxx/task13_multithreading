package com.epam.model;

import java.time.LocalDateTime;

public class PingPong {

    private volatile static long A = 0;
    // Shared Resource
    private static Object sync = new Object();

    // Monitor
    public void show(int timesNumber) {
        Thread t1 = new Thread(() -> {
            synchronized (sync) {
                for (int i = 1; i <= timesNumber; i++) {
                    try {
                        sync.wait();
                    } catch (InterruptedException e) {
                    }
                    A++;
                    System.out.println("\n" + A + ": " + Thread.currentThread().getName() + " ---> pIng");
                    sync.notify();
                }
                System.out.println("\nfinish " + Thread.currentThread().getName());
            }
        });
        Thread t2 = new Thread(() -> {
            synchronized (sync) {
                for (int i = 1; i <= timesNumber; i++) {
                    sync.notify();
                    try {
                        sync.wait();
                    } catch (InterruptedException e) {
                    }
                    A++;
                    System.out.println("\n" + A + ": " + Thread.currentThread().getName() + " ---> pOng");
                }
                System.out.println("\nfinish " + Thread.currentThread().getName());
            }
        });

        System.out.println("\n" + LocalDateTime.now());
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
        }
        System.out.println("\n" + LocalDateTime.now());
    }

}
