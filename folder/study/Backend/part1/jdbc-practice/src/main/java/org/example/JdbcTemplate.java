package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Purpose: Practice for understanding JDBC Programming
 * Features: Programs that use HikariCP's DataSource to connect to a database
 *
 * Author: Jinhwan Kim (Jin)
 * Date created: 2023-06-26
 * Modification Date:
 */

// JdbcTemplate library
public class JdbcTemplate {
    public void executeUpdate(User user, String sql, PreparedStatementSetter pss) throws SQLException { // Fetching changing queries externally
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            // Change the code that gets the connection to the code that applies the connection pool to get the connection
            con = ConnectionManager.getConnection();
            // Change to receive as a parameter because it can be received externally
//            String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?)";
            pstmt = con.prepareStatement(sql); // You need to know the connection object from the outside - so it's better to take only the part you're setting as an argument.
            // Since the parts below are also externally received and can be changed,
            // if I try to change them to be received as parameters,
            // I will have a problem according to the above sentence, so solve it here.
//            pstmt.setString(1, user.getUserId());
//            pstmt.setString(2, user.getPassword());
//            pstmt.setString(3, user.getName());
//            pstmt.setString(4, user.getEmail());
            pss.setter(pstmt); // Just pass it on

            pstmt.executeUpdate();
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }

            if (con != null) {
                con.close();
            }
        }
    }

    // findByUserId in the UserDao class - implemented in JdbcTemplate
    public Object executeQuery(String sql, PreparedStatementSetter pss, RowMapper rowMapper) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        // Declare to get the retrieved value
        ResultSet rs = null;

        try {
            con = ConnectionManager.getConnection();
            pstmt = con.prepareStatement(sql);
//          //pstmt.setString(1, userId); // I don't know how many will be set, so I'm going to change the receive to outside
            pss.setter(pstmt);

            rs = pstmt.executeQuery();

            // This can also change, so modify the code to reflect what I am using.
            Object obj = null;
            if (rs.next()) {
                return rowMapper.map(rs);
            }
            return obj;
            // Releases resources in reverse order from the first one encounter
            // (note: Use try with resource, it will automatically release the resource).
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
}
