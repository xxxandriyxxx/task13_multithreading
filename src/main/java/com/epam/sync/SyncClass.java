package com.epam.sync;

public class SyncClass implements Runnable {

    private static long A = 0;
    private static Object obj1 = new Object();
    private static Object obj2 = new Object();
    private static Object obj3 = new Object();
    private boolean syncBySameObj;

    public SyncClass(boolean syncBySameObj) {
        this.syncBySameObj = syncBySameObj;
    }

    @Override
    public void run() {
        if (syncBySameObj) {
            method1(obj1);
            method2(obj1);
            method3(obj1);
        } else {
            method1(obj1);
            method2(obj2);
            method3(obj3);
        }
    }

    private static void method1(Object obj) {
        synchronized (obj) {
            System.out.println(" Start method 1 --- " + Thread.currentThread().getName());
            for (int i = 1; i <= 1000000000; i++) {
                A++;
            }
            System.out.println("Finish method 1 --- " + Thread.currentThread().getName() + " --- A=" + A);
        }
    }

    private static void method2(Object obj) {
        synchronized (obj) {
            System.out.println(" Start method 2 --- " + Thread.currentThread().getName());
            for (int i = 1; i <= 1000000000; i++) {
                A++;
            }
            System.out.println("Finish method 2 --- " + Thread.currentThread().getName() + " --- A=" + A);
        }
    }

    private static void method3(Object obj) {
        synchronized (obj) {
            System.out.println(" Start method 3 --- " + Thread.currentThread().getName());
            for (int i = 1; i <= 1000000000; i++) {
                A++;
            }
            System.out.println("Finish method 3 --- " + Thread.currentThread().getName() + " --- A=" + A);
        }
    }

}
