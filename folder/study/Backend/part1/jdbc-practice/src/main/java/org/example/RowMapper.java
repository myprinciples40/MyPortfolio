package org.example;

import java.sql.ResultSet;
import java.sql.SQLException;

// Interface for findByUserId2 (which takes the User's information from the ResultSet and processes it as a superclass Object)
public interface RowMapper {
    Object map(ResultSet resultSet) throws SQLException;
}
