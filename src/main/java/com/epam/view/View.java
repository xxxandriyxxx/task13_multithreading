package com.epam.view;

import com.epam.controller.Controller;
import com.epam.controller.ControllerImpl;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class View {

    private Controller controller;
    private Map<String, String> menu;
    private Map<String, Printable> methodsMenu;
    private static Scanner input = new Scanner(System.in);

    public View() {
        controller = new ControllerImpl();
        controller = new ControllerImpl();
        menu = new LinkedHashMap<>();
        menu.put("1", " 1 - play ping-pong");
        menu.put("2", " 2 - print sequence of 20 Fibonacci numbers using Thread");
        menu.put("3", " 3 - print sequence of 20 Fibonacci numbers using ExecutorService");
        menu.put("4", " 4 - print sequence of 20 Fibonacci numbers using Callable");
        menu.put("5", " 5 - get sum of 20 Fibonacci numbers using Callable");
        menu.put("6", " 6 - run 5 tasks using ScheduledThreadPool");
        menu.put("7", " 7 - run methods synchronized on the same object");
        menu.put("8", " 8 - run methods synchronized on different objects");
        menu.put("9", " 9 - run methods using the same Lock object");
        menu.put("10", " 10 - run methods using different Lock objects");
        menu.put("11", " 11 - run two tasks using a pipe to communicate");
        menu.put("12", " 12 - run two tasks using BlockingQueue");
        menu.put("Q", " q - exit");
        methodsMenu = new LinkedHashMap<>();
        methodsMenu.put("1", this::playPingPong);
        methodsMenu.put("2", this::runFibonacciByThread);
        methodsMenu.put("3", this::runFibonacciByExecutor);
        methodsMenu.put("4", this::runFibonacciCallable);
        methodsMenu.put("5", this::getSumFibonacci);
        methodsMenu.put("6", this::runScheduledTasks);
        methodsMenu.put("7", this::runSyncSameObj);
        methodsMenu.put("8", this::runSyncDifObj);
        methodsMenu.put("9", this::runLockSameObj);
        methodsMenu.put("10", this::runLockDifObj);
        methodsMenu.put("11", this::runPipe);
        methodsMenu.put("12", this::runBlockingQueue);
    }

    private void playPingPong() {
        controller.playPingPong(100);
    }

    private void runFibonacciByThread() {
        controller.runFibonacciByThread(20);
    }

    private void runFibonacciByExecutor() {
        controller.runFibonacciByExecutor(20);
    }

    private void runFibonacciCallable() {
        controller.runFibonacciCallable(20);
    }

    private void getSumFibonacci() {
        controller.getSumFibonacci(20);
    }

    private void runScheduledTasks() {
        controller.runScheduledTasks(5);
    }

    private void runSyncSameObj() {
        controller.runSync(true);
    }

    private void runSyncDifObj() {
        controller.runSync(false);
    }

    private void runLockSameObj() {
        controller.runLock(true);
    }

    private void runLockDifObj() {
        controller.runLock(false);
    }

    private void runPipe() {
        controller.runPipe();
    }

    private void runBlockingQueue() {
        controller.runBlockingQueue();
    }

    private void outputMenu() {
        System.out.println("\n==================== MENU ====================");
        for (String str : menu.values()) {
            System.out.println(str);
        }
    }

    public void show() {
        String keyMenu;
        do {
            outputMenu();
            System.out.println("----------------------------------------------");
            System.out.println("Enter the menu point:");
            keyMenu = input.nextLine().toUpperCase();
            try {
                methodsMenu.get(keyMenu).print();
            } catch (Exception e) {
            }
        } while (!keyMenu.equals("Q"));
    }
}
