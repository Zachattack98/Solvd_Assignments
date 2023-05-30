package com.solvd.threads;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NewFutureMain {
    private static final Logger MAIN_FUTURE_LOGGER = LogManager.getLogger(NewFutureMain.class);
    public static void main(String[] args) throws InterruptedException {
        //initialize future connection pool object to size 5
        NewFuture futureConnectionPool = new  NewFuture(5);

        //initialize connection pool
        futureConnectionPool.initial();

        //create threadPool with 7 thread total
        //5 to get the connection and 2 to wait for next available connection.
        ExecutorService threadPool = Executors.newFixedThreadPool(7);

        //list containing all future tasks
        List<CompletableFuture<Void>> listOfFutures = new ArrayList<>();

        //5 threads that represent: get connection
        for (int i = 0; i < futureConnectionPool.poolSzFuture; i++) {
            int threadID = i + 1;       //assign thread id from 1 to 5.
            //increments with every iteration.
            //must be initialized inside for loop to use in logger.

            CompletableFuture<Connection> connectionFuture = futureConnectionPool.retrieveConnection();

            //begin execution on connection
            CompletableFuture<Void> task = connectionFuture.thenAccept(connection -> {
                try {
                    MAIN_FUTURE_LOGGER.info("Thread: {" + threadID + "} - Available connection: {" + futureConnectionPool.getAvailableConnection() + "}");
                    MAIN_FUTURE_LOGGER.info("Thread: {" + threadID + "} - " + connection.process());

                    Thread.sleep(5000);

                    //release the connection back when finished
                    futureConnectionPool.releaseConnection(connection);
                    MAIN_FUTURE_LOGGER.info("Thread: {" + threadID + "} - Complete! - Available connection: {" + futureConnectionPool.getAvailableConnection() + "}");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            //Add task to list
            listOfFutures.add(task);
        }

        //2 threads that represent: wait until next available connection
        //start right after 5th thread
        for (int i = 5; i < 7; i++) {
            int threadID = i + 1;

            CompletableFuture<Connection> connectionFuture = futureConnectionPool.retrieveConnection();

            //begin execution on connection
            CompletableFuture<Void> task = connectionFuture.thenAccept(connection -> {
                while(futureConnectionPool.getAvailableConnection() == 0) {
                    MAIN_FUTURE_LOGGER.info("Thread: {" + threadID + "} is waiting for available connection...");
                }
                //now found available connection
                MAIN_FUTURE_LOGGER.info("Thread: {" + threadID + "} - Available connection: {" + futureConnectionPool.getAvailableConnection() + "}");
                MAIN_FUTURE_LOGGER.info("Thread: {" + threadID + "} - " + connection.process());

                //release the connection back when finished
                futureConnectionPool.releaseConnection(connection);
                MAIN_FUTURE_LOGGER.info("Thread: {" + threadID + "} - Complete! - Available connection: {" + futureConnectionPool.getAvailableConnection() + "}");
            });
            //Add task to list
            listOfFutures.add(task);
        }
        //return a complete list of tasks for CompletableFuture
        CompletableFuture.allOf(listOfFutures.toArray(new CompletableFuture[0])).join();

        //shut down after all threads are completed
        threadPool.shutdown();
        MAIN_FUTURE_LOGGER.info("All threads have now finished.");
    }
}
