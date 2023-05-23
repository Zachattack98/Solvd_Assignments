package com.solvd.threads;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.*;

public class NewFuture {
    public ExecutorService executor = Executors.newSingleThreadExecutor();
    private static final Logger FUTURE_LOGGER = LogManager.getLogger(NewFuture.class);
    public final int poolSzFuture;
    //store all connections in deque
    private final ConcurrentLinkedDeque<Connection> futureConnectionPool;
    private int availableConnection;

    public NewFuture(int poolSzFuture) {
        this.poolSzFuture = poolSzFuture;
        this.availableConnection = 0;
        this.futureConnectionPool = new ConcurrentLinkedDeque<>();
        this.executor = Executors.newFixedThreadPool(poolSzFuture);
    }

    /*public NewFuture printTest(String input) {
        return (NewFuture) executor.submit(() -> {
            Thread.sleep(1000);
            return "Printed " + input;
        });
    }

    public void setExecutor(ExecutorService executor) {
        this.executor = executor;
    }

    public ExecutorService getExecutor() {
        return executor;
    }*/


    public int getAvailableConnection() {
        return availableConnection;
    }

    //initialize connection pool and available connection
    public void initial() {
        for (int i = 0; i < poolSzFuture; i++) {
            futureConnectionPool.offer(createConnection());
            availableConnection++;  //increment the number of available connections
        }
    }

    //method retrieving connection from connection pool
    public CompletableFuture<Connection> retrieveConnection() {
        //return new CompletableFuture that is asynchronously completed
        return CompletableFuture.supplyAsync(() -> {
            //lock connection object using synchronized so that each available connection
            //can be accessed by only one thread.
            synchronized (this) {
                //wait until there's any available connection
                while (availableConnection == 0) {
                    try {
                        wait();
                    } catch(InterruptedException e) {
                        FUTURE_LOGGER.error(e.getMessage());
                    }
                }
                //Connection is now acquired
                Connection connection = futureConnectionPool.poll();
                availableConnection--; //decrement counter of available connections by 1
                return connection;
            }
        });
    }

    //after execution is complete,
    //release connection back to connection pool.
    public void releaseConnection(Connection connection) {
        synchronized (this) {
            futureConnectionPool.offer(connection);  //add released connection back to connection pool
            availableConnection++;             //increment counter of available connections by 1
            notify();                          //notify a waiting thread (if any)
        }
    }
    private Connection createConnection() {
        return new Connection("New connection has been created");
    }
}
