package com.epam.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.Arrays;

public class MyPipe {

    private static Logger logger = LogManager.getLogger(MyPipe.class);

    public static void show() {
        PipedInputStream pis = new PipedInputStream();
        PipedOutputStream pos = new PipedOutputStream();
        try {
            pos.connect(pis);
        } catch (IOException e) {
            logger.error(Arrays.toString(e.getStackTrace()));
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
                logger.error(Arrays.toString(e.getStackTrace()));
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
                logger.error(Arrays.toString(e.getStackTrace()));
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
