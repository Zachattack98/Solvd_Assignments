package com.solvd.threads;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConnectionPoolMain {
    private static final Logger MAIN_POOL_LOGGER = LogManager.getLogger(ConnectionPoolMain.class);
    public static void main(String[] args) {
        //initialize connection pool object to size 5
        ConnectionPool connectionPool = new ConnectionPool(5);

        //initialize connection pool
        connectionPool.initial();

        //create threadPool with 7 thread total
        //5 to get the connection and 2 to wait for next available connection.
        ExecutorService threadPool = Executors.newFixedThreadPool(7);

        //5 threads that represent: get connection
        for (int i = 0; i < connectionPool.poolSz; i++) {
            int threadID = i + 1;       //assign thread id from 1 to 5.
            //increments with every iteration.
            //must be initialized inside for loop to use in logger.
            threadPool.submit(() -> {
                try {
                    //retrieve connection from connection pool
                    Connection connection = connectionPool.retrieveConnection();
                    MAIN_POOL_LOGGER.info("Thread: {" + threadID + "} - Available connection: {" + connectionPool.getAvailableConnection() + "}");
                    MAIN_POOL_LOGGER.info("Thread: {" + threadID + "} - " + connection.process());

                    Thread.sleep(5000);

                    //release the connection back when finished
                    connectionPool.releaseConnection(connection);
                    MAIN_POOL_LOGGER.info("Thread: {" + threadID + "} - Complete! - Available connection: {" + connectionPool.getAvailableConnection() + "}");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        //2 threads that represent: wait until next available connection
        //start right after 5th thread
        for (int i = 5; i < 7; i++) {
            int threadID = i + 1;

            threadPool.submit(() -> {
                try {
                    Connection connection = connectionPool.retrieveConnection();
                    MAIN_POOL_LOGGER.info("Thread: {" + threadID + "} - Available connection: {" + connectionPool.getAvailableConnection() + "}");
                    MAIN_POOL_LOGGER.info("Thread: {" + threadID + "} - " + connection.process());

                    connectionPool.releaseConnection(connection);
                    MAIN_POOL_LOGGER.info("Thread: {" + threadID + "} - Complete! - Available connection: {" + connectionPool.getAvailableConnection() + "}");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        //shut down after all threads are completed
        threadPool.shutdown();
        MAIN_POOL_LOGGER.info("All threads have now finished.");
    }
}
