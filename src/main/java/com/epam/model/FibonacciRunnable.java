package com.epam.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

public class FibonacciRunnable implements Runnable {

    private static Logger logger = LogManager.getLogger(FibonacciRunnable.class);

    private int x;
    public long number;

    public FibonacciRunnable(int x) {
        this.x = x;
    }

    public void run() {
        if (x == 0) {
            number = 0;
        } else if (x == 1)
            number = 1;
        else {
            try {
                FibonacciRunnable f1 = new FibonacciRunnable(x - 1);
                FibonacciRunnable f2 = new FibonacciRunnable(x - 2);
                Thread thread1 = new Thread(f1);
                Thread thread2 = new Thread(f2);
                thread1.start();
                thread2.start();
                thread1.join();
                thread2.join();
                number = f1.number + f2.number;
            } catch (InterruptedException e) {
                logger.error(Arrays.toString(e.getStackTrace()));
            }
        }
    }

}
