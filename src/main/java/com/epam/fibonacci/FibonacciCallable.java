package com.epam.fibonacci;

import java.util.concurrent.Callable;

public class FibonacciCallable implements Callable<Long[]> {

    private int size;

    public FibonacciCallable(int size) {
        this.size = size;
    }

    @Override
    public Long[] call() {
        long num1 = 0;
        long num2 = 1;
        long temp;
        Long[] sequence = new Long[size];
        for (int i = 1; i <= size; i++) {
            sequence[i - 1] = num1;
            temp = num1 + num2;
            num1 = num2;
            num2 = temp;
        }
        return sequence;
    }
}
