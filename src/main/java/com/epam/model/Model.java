package com.epam.model;

public interface Model {

    void playPingPong(int timesNumber);

    void runFibonacciByThread(int size);

    void runFibonacciByExecutor(int size);

    void runFibonacciCallable(int size);

    void getSumFibonacci(int size);

    void runScheduledTasks(int amount);

    void runSync(boolean syncBySameObj);

    void runLock(boolean lockBySameObj);

    void runPipe();

    void runBlockingQueue();
}
