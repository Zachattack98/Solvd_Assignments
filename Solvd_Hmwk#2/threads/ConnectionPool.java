package com.solvd.threads;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConnectionPool {
    private static final Logger POOL_LOGGER = LogManager.getLogger(ConnectionPool.class);

    public final int poolSz;
    //store all connections in deque
    private final ConcurrentLinkedDeque<Connection> connectionPool;
    private int availableConnection;

    public ConnectionPool(int poolSz) {
        this.poolSz = poolSz;
        this.availableConnection = 0;
        this.connectionPool = new ConcurrentLinkedDeque<>();
    }

    public int getAvailableConnection() {
        return availableConnection;
    }

    //initialize connection pool and available connection
    public void initial() {
        for (int i = 0; i < poolSz; i++) {
            connectionPool.offer(createConnection());
            availableConnection++;  //increment the number of available connections
        }
    }

    //method retrieving connection from connection pool
    public Connection retrieveConnection() throws InterruptedException {
        //lock connection object using synchronized so that each available connection
        //can be accessed by only one thread.
        synchronized (this) {
            //wait until there's any available connection
            while (availableConnection == 0) {
                wait();
            }
            //Connection is now acquired
            Connection connection = connectionPool.poll();
            availableConnection--; //decrement counter of available connections by 1
            return connection;
        }
    }

    //after execution is complete,
    //release connection back to connection pool.
    public void releaseConnection(Connection connection) {
        synchronized (this) {
            connectionPool.offer(connection);  //add released connection back to connection pool
            availableConnection++;             //increment counter of available connections by 1
            notify();                          //notify a waiting thread (if any)
        }
    }
    private Connection createConnection() {
        return new Connection("New connection has been created");
    }
}
