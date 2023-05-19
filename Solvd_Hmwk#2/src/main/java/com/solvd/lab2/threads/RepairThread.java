package com.solvd.lab2.threads;

import java.util.concurrent.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RepairThread {
    private static final Logger THREAD_LOGGER = LogManager.getLogger(RepairThread.class);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                THREAD_LOGGER.info("Runnable message: " + Thread.currentThread().getId());

            }
        };

        Thread runnableThread = new Thread(runnable);

        Thread threadToThread = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            THREAD_LOGGER.info("Thread message: " + Thread.currentThread().getId());
        });
        //start each thread
        runnableThread.start();
        threadToThread.start();

        /*Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                return "Callable is finished";
            }
        };

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.execute(runnableThread);
        executorService.execute(threadToThread);
        executorService.execute(runnableThread);
        executorService.execute(threadToThread);
        executorService.submit(runnableThread);
        executorService.submit(threadToThread);
        NewFuture<String> future = (NewFuture<String>) executorService.submit(callable);
        (while (!future.isDone()) {
            System.out.println("Callable is not finished yet");
            Thread.sleep(500);
        }
        THREAD_LOGGER.info(future.getExecutor());
        THREAD_LOGGER.info("Callable is exceuted on service");
        executorService.shutdown();

        THREAD_LOGGER.info("Main thread message: " + Thread.currentThread().getId());
        */
    }
}
