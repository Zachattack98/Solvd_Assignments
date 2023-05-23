package com.solvd.threads;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Connection {
    private static final Logger CONNECTION_LOGGER = LogManager.getLogger(Connection.class);
    public final String connectMessage;
    public Connection(String connectMessage) {
        this.connectMessage = connectMessage;
    }

    public String process() {
        return "Still processing...";
    }
}
