package com.epam.model;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class MyBlockingQueue {

    public static void show() {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(5);

        Thread thread = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    queue.put(i);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " --> add --> " + i);
            }
        }, "Thead-1");

        Thread thread2 = new Thread(() -> {
            Integer value = 0;
            for (int i = 1; i <= 10; i++) {
                try {
                    Thread.sleep(3000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    value = queue.take();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " --> take --> " + value);
            }
        }, "Thead-2");

        thread.start();
        thread2.start();
        try {
            thread.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
