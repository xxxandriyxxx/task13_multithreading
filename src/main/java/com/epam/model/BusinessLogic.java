package com.epam.model;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.concurrent.*;

import static java.lang.Thread.MAX_PRIORITY;

public class BusinessLogic implements Model {

    public void playPingPong(int timesNumber) {
        PingPong pingPong = new PingPong();
        pingPong.show(timesNumber);
    }

    public void runFibonacciByThread(int size) {
        System.out.println("Fibonacci sequence of " + size + " numbers (used Thread):");
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

    public void runFibonacciByExecutor(int size) {
        System.out.println("Fibonacci sequence of " + size + " numbers (used Executor):");
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

    public void runFibonacciCallable(int size) {
        try {
            FibonacciCallable f = new FibonacciCallable(size);
            ExecutorService executorService = Executors.newSingleThreadExecutor();
            Future<Long[]> future = executorService.submit(f);
            executorService.shutdown();
            System.out.println("Fibonacci sequence of " + size + " numbers (used Callable):\n"
                    + Arrays.toString(future.get()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getSumFibonacci(int size) {
        try {
            FibonacciCallableSum f = new FibonacciCallableSum(size);
            ExecutorService executorService = Executors.newSingleThreadExecutor();
            Future<Long> future = executorService.submit(f);
            executorService.shutdown();
            System.out.println("Sum of " + size + " Fibonacci numbers (used Callable) = " + future.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void runScheduledTasks(int amount) {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        int delayTime;
        for (int i = 1; i <= amount; i++) {
            MyRunnable task = new MyRunnable(i);
            delayTime = 1 + (int) (Math.random() * 10);
            System.out.println("Task " + i + " --> time before delay: " + LocalTime.now());
            ScheduledFuture<?> future = executor.schedule(task, delayTime, TimeUnit.SECONDS);
            long remainingDelay = future.getDelay(TimeUnit.MILLISECONDS);
            System.out.printf("Task " + i + " --> remaining delay: %s ms\n", remainingDelay);
        }
        executor.shutdown();
    }

    public void runSync(boolean syncBySameObj) {
        SyncClass syncClass1 = new SyncClass(syncBySameObj);
        SyncClass syncClass2 = new SyncClass(syncBySameObj);
        SyncClass syncClass3 = new SyncClass(syncBySameObj);
        Thread thread1 = new Thread(syncClass1);
        Thread thread2 = new Thread(syncClass2);
        Thread thread3 = new Thread(syncClass3);
        thread1.start();
        thread2.start();
        thread3.start();
        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void runLock(boolean lockBySameObj) {
        LockClass lockClass1 = new LockClass(lockBySameObj);
        LockClass lockClass2 = new LockClass(lockBySameObj);
        LockClass lockClass3 = new LockClass(lockBySameObj);
        lockClass1.start();
        lockClass2.start();
        lockClass3.start();
        try {
            lockClass1.join();
            lockClass2.join();
            lockClass3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void runPipe() {
        MyPipe.show();
    }

    public void runBlockingQueue() {
        MyBlockingQueue.show();
    }

}
