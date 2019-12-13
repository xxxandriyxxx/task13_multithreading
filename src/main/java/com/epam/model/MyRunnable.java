package com.epam.model;

import java.time.LocalTime;

public class MyRunnable implements Runnable {

    private int taskNumber;

    public MyRunnable(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void run() {
        System.out.println("Task " + taskNumber + " ---> time of starting: " + LocalTime.now());
    }
}
