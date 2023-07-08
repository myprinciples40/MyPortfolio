package org.example;

import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Purpose: Practice for understanding JDBC Programming
 * Features: Programs that use HikariCP's DataSource to connect to a database
 *
 * Author: Jinhwan Kim (Jin)
 * Date created: 2023-06-26
 * Modification Date:
 */

// Collection of responsibilities
public class ConnectionManager {
    // Ctrl + Alt + C: Set Constant
    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:mem://localhost/~/jdbc-practice;MODE=MySQL;DB_CLOSE_DELAY=-1";
    public static final int MAX_POOL_SIZE = 40;
    private static final DataSource ds;

    // Applying a connection pool using HikariCP: initialization - setting to have only one connection pool
    // static {} : Complex initialization of class value (CV) / class initialization blocks
    static {
        // Perform complex initialization of static variables
        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setDriverClassName(DB_DRIVER);
        hikariDataSource.setJdbcUrl(DB_URL);
        hikariDataSource.setUsername("sa");
        hikariDataSource.setPassword("");
        hikariDataSource.setMaximumPoolSize(MAX_POOL_SIZE);
        hikariDataSource.setMinimumIdle(MAX_POOL_SIZE);

        ds = hikariDataSource;
    }

    // Move the getConnection() method from the UserDao
    // Change the code that gets the connection to one that applies the connection pool to get the connection
    public static Connection getConnection() {
//        return getDataResource().getConnection();
        try {
            return ds.getConnection();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }

        // before modification
//        String url = "jdbc:h2:mem://localhost/~/jdbc-practice;MODE=MySQL;DB_CLOSE_DELAY=-1";
//        String id = "sa";
//        String pw = "";
//
//        try {
//            Class.forName("org.h2.Driver");
//            return DriverManager.getConnection(url, id, pw);
//        } catch (Exception ex) {
//            return null;
//        }
    }

    public static DataSource getDataSource() {
        return ds;
    }
}
