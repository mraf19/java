package programmer.zaman.now.database;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionTest {


    @BeforeAll
    static void beforeAll() {
        try {
            Driver mysqlDriver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(mysqlDriver);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    void TestConnection() {
        String jdbcUrl = "jdbc:mysql://localhost:3306/belajar_java_database";
        String username = "root";
        String password = "";
        try(Connection connection = DriverManager.getConnection(jdbcUrl, username, password);) {
            System.out.println("Sukses Koneksi ke Database");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
