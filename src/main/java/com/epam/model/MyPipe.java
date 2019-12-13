package com.epam.model;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class MyPipe {

    public static void show() {
        PipedInputStream pis = new PipedInputStream();
        PipedOutputStream pos = new PipedOutputStream();
        try {
            pos.connect(pis);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Thread thread = new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    pos.write((byte) i);
                    pos.flush();
                    System.out.println(Thread.currentThread().getName() + " --> writing --> " + i);
                    Thread.sleep(1000);
                }
                pos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }, "Thead-1");

        Thread thread2 = new Thread(() -> {
            try {
                int num = -1;
                while ((num = pis.read()) != -1) {
                    System.out.println(Thread.currentThread().getName() + " --> reading --> " + num);
                }
                pis.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "Thead-2");

        thread.start();
        thread2.start();
    }
}
