package com.epam.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class MyBlockingQueue {

    private static Logger logger = LogManager.getLogger(MyBlockingQueue.class);

    public static void show() {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(5);

        Thread thread = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    logger.error(Arrays.toString(e.getStackTrace()));
                }
                try {
                    queue.put(i);
                } catch (Exception e) {
                    logger.error(Arrays.toString(e.getStackTrace()));
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
                    logger.error(Arrays.toString(e.getStackTrace()));
                }
                try {
                    value = queue.take();
                } catch (Exception e) {
                    logger.error(Arrays.toString(e.getStackTrace()));
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
            logger.error(Arrays.toString(e.getStackTrace()));
        }
    }
}
