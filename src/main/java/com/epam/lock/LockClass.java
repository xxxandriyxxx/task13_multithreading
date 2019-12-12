package com.epam.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockClass extends Thread {

    private static long A = 0;
    private static Lock lock1 = new ReentrantLock();
    private static Lock lock2 = new ReentrantLock();
    private static Lock lock3 = new ReentrantLock();

    private boolean lockBySameObj;

    public LockClass(boolean lockBySameObj) {
        this.lockBySameObj = lockBySameObj;
    }

    @Override
    public void run() {
        if (lockBySameObj) {
            method1(lock1);
            method2(lock1);
            method3(lock1);
        } else {
            method1(lock1);
            method2(lock2);
            method3(lock3);
        }
    }

    private static void method1(Lock lock) {
        lock.lock();
        try {
            System.out.println(" Start method 1 --- " + Thread.currentThread().getName());
            for (int i = 1; i <= 1000000000; i++) {
                A++;
            }
            System.out.println("Finish method 1 --- " + Thread.currentThread().getName() + " --- A=" + A);
        }
        finally {
            lock.unlock();
        }
    }

    private static void method2(Lock lock) {
        lock.lock();
        try {
            System.out.println(" Start method 2 --- " + Thread.currentThread().getName());
            for (int i = 1; i <= 1000000000; i++) {
                A++;
            }
            System.out.println("Finish method 2 --- " + Thread.currentThread().getName() + " --- A=" + A);
        }
        finally {
            lock.unlock();
        }
    }

    private static void method3(Lock lock) {
        lock.lock();
        try {
            System.out.println(" Start method 3 --- " + Thread.currentThread().getName());
            for (int i = 1; i <= 1000000000; i++) {
                A++;
            }
            System.out.println("Finish method 3 --- " + Thread.currentThread().getName() + " --- A=" + A);
        }
        finally {
            lock.unlock();
        }
    }

}
