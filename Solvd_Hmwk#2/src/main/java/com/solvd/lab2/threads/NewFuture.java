package com.solvd.lab2.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class NewFuture<T> {
    private ExecutorService executor = Executors.newSingleThreadExecutor();
    public Future<String> printTest(String input) {
        return executor.submit(() -> {
            Thread.sleep(1000);
            return "Printed " + input;
        });
    }

    public void setExecutor(ExecutorService executor) {
        this.executor = executor;
    }

    public ExecutorService getExecutor() {
        return executor;
    }

    public boolean isDone() {
        return true;
    }

}
