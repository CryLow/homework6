package org.example.utilites;

import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
@Component
public class ConnectionHolder {
    private final DataSource dataSource;
    private final Map<Thread, Connection> connections =new ConcurrentHashMap<>();

    public ConnectionHolder(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Connection getConnection(){
        Thread thread =Thread.currentThread();
        try {
            if(!connections.containsKey(thread) || connections.get(thread).isClosed()){
            Connection connection =dataSource.getConnection();
            connections.put(thread,connection);
            }
            return connections.get(thread);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
