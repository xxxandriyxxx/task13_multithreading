package com.epam.model;

import java.util.concurrent.Callable;

public class FibonacciCallableSum implements Callable<Long> {

    private int size;

    public FibonacciCallableSum(int size) {
        this.size = size;
    }

    @Override
    public Long call() {
        long num1 = 0;
        long num2 = 1;
        long temp;
        long sum = 0;
        Long[] sequence = new Long[size];
        for (int i = 1; i <= size; i++) {
            sequence[i - 1] = num1;
            temp = num1 + num2;
            num1 = num2;
            num2 = temp;
        }
        for (Long el : sequence) {
            sum += el;
        }
        return sum;
    }
}
