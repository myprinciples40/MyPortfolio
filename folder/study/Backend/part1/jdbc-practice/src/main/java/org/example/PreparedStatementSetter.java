package org.example;

import java.sql.PreparedStatement;
import java.sql.SQLException;

// Interface for handling PreparedStatement in a JdbcTemplate class.
public interface PreparedStatementSetter {
    void setter(PreparedStatement pstmt) throws SQLException; // Setting up with a PreparedStatement
}
