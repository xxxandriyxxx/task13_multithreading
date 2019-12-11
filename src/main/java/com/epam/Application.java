package com.epam;

import com.epam.fibonacci.FibonacciCallable;
import com.epam.fibonacci.FibonacciRunnable;
import com.epam.ping_pong.PingPong;


import java.util.concurrent.*;

import static java.lang.Thread.MAX_PRIORITY;

public class Application {

    public static void main(String[] args) {

        playPingPong(10000);
        runFibonacciByThread(10);
        runFibonacciByExecutor(10);


    }


    private static void playPingPong(int timesNumber) {
        PingPong pingPong = new PingPong();
        pingPong.show(timesNumber);
    }

    private static void runFibonacciByThread(int size) {
        System.out.println("Fibonacci sequence of " + size + " numbers:");
        try {
            for (int i = 0; i < size; i++) {
                FibonacciRunnable f = new FibonacciRunnable(i);
                Thread thread = new Thread(f);
                thread.start();
                thread.join();
                System.out.println("num" + (i + 1) + " = " + f.number);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void runFibonacciByExecutor(int size) {
        System.out.println("Fibonacci sequence of " + size + " numbers:");
        try {
            for (int i = 0; i < size; i++) {
                FibonacciRunnable f = new FibonacciRunnable(i);
                ExecutorService executorService = Executors.newSingleThreadExecutor();
                executorService.submit(f);
                executorService.shutdown();
                executorService.awaitTermination(MAX_PRIORITY, TimeUnit.HOURS);
                System.out.println("num" + (i + 1) + " = " + f.number);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
