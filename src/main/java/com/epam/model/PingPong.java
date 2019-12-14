package com.epam.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;
import java.util.Arrays;

public class PingPong {

    private static Logger logger = LogManager.getLogger(PingPong.class);
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
                        logger.error(Arrays.toString(e.getStackTrace()));
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
                        logger.error(Arrays.toString(e.getStackTrace()));
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
            logger.error(Arrays.toString(e.getStackTrace()));
        }
        System.out.println("\n" + LocalDateTime.now());
    }

}
