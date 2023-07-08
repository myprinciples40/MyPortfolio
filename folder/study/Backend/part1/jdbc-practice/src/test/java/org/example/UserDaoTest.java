package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Purpose: Practice for understanding JDBC Programming
 * Features: Programs that use HikariCP's DataSource to connect to a database
 *
 * Author: Jinhwan Kim (Jin)
 * Date created: 2023-06-26
 * Modification Date:
 */

public class UserDaoTest {
    // @BeforeEach annotation: Use when there's something you need to do before running test code
    @BeforeEach
    void setUp() {
        // HikariCp and JDBC use SQL to create table data and fetch data, so the code we wrote before testing
        // Use the classes in springframework.jdbc
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        // Before running the test code, I put in the following SQL statement to perform the test (Create table)
        populator.addScript(new ClassPathResource("springboot_basic.sql"));
        DatabasePopulatorUtils.execute(populator, ConnectionManager.getDataSource());
    }

    @Test
    void createTest() throws SQLException {
        // Dao (Data Access Object) - Create to delegate tasks to user Dao when performing DB operations
        UserDao userDao = new UserDao();

        // before modification
//        userDao.create(new User("wizard", "password", "name", "email"));
//        User user = userDao.findByUserId("wizard");
        // after modification
        userDao.create2(new User("wizard", "password", "name", "email"));
        User user = userDao.findByUserId2("wizard");

        assertThat(user).isEqualTo(new User("wizard", "password", "name", "email"));
    }
}
