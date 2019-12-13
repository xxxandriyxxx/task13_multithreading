package com.epam.controller;

import com.epam.model.BusinessLogic;
import com.epam.model.Model;

public class ControllerImpl implements Controller {

    private Model model;

    public ControllerImpl() {
        model = new BusinessLogic();
    }

    @Override
    public void playPingPong(int timesNumber) {
        model.playPingPong(timesNumber);
    }

    @Override
    public void runFibonacciByThread(int size) {
        model.runFibonacciByThread(size);
    }

    @Override
    public void runFibonacciByExecutor(int size) {
        model.runFibonacciByExecutor(size);
    }

    @Override
    public void runFibonacciCallable(int size) {
        model.runFibonacciCallable(size);
    }

    @Override
    public void getSumFibonacci(int size) {
        model.getSumFibonacci(size);
    }

    @Override
    public void runScheduledTasks(int amount) {
        model.runScheduledTasks(amount);
    }

    @Override
    public void runSync(boolean syncBySameObj) {
        model.runSync(syncBySameObj);
    }

    @Override
    public void runLock(boolean lockBySameObj) {
        model.runLock(lockBySameObj);
    }

    @Override
    public void runPipe() {
        model.runPipe();
    }

    @Override
    public void runBlockingQueue() {
        model.runBlockingQueue();
    }
}
